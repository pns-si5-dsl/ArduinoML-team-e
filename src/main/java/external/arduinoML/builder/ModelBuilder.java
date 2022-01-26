package external.arduinoML.builder;

import external.antlr.ArduinoMLBaseListener;
import external.antlr.ArduinoMLParser;
import external.arduinoML.builder.exceptions.DuplicateVariableDeclarationException;
import external.arduinoML.builder.exceptions.ModelNotCreatedException;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.InputWaiting;
import kernel.model.state.transitions.TimeWaiting;
import kernel.model.state.transitions.Transition;
import kernel.model.values.SIGNAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelBuilder extends ArduinoMLBaseListener {

    private App model;

    private final List<String>            variables   = new ArrayList<>();
    private final Map<String, Actuator>   actuators   = new HashMap<>();
    private final Map<String, State>      states      = new HashMap<>();
    private final Map<String, Sensor>     sensors     = new HashMap<>();
    private final Map<Transition, String> transitions = new HashMap<>();

    private State currentState = null;

    private RuntimeException exception = null;

    public App build() throws Exception {
        if (model != null) return model;
        throw new ModelNotCreatedException();
    }

    private void checkDuplicate(String name, int line) {
        if (variables.contains(name)) {
            if (exception == null) exception = new RuntimeException();
            exception.addSuppressed(new DuplicateVariableDeclarationException(name, line));
        }
    }

    @Override
    public void enterProgram(ArduinoMLParser.ProgramContext ctx) {
        model = new App();
    }

    @Override
    public void exitProgram(ArduinoMLParser.ProgramContext ctx) {
        if (exception != null) throw exception;
        transitions.forEach((trans, dest) -> trans.setNext(states.get(dest))); //TODO check null state reference
    }

    @Override
    public void enterSensor(ArduinoMLParser.SensorContext ctx) {
        checkDuplicate(ctx.id.getText(), ctx.id.getLine());
        Sensor sensor = new Sensor();
        sensor.setName(ctx.id.getText());
        sensor.setPin(Integer.parseInt(ctx.pin.getText().substring(3)));
        sensors.put(sensor.getName(), sensor);
        variables.add(sensor.getName());
        model.getBricks().add(sensor);
    }

    @Override
    public void enterActuator(ArduinoMLParser.ActuatorContext ctx) {
        checkDuplicate(ctx.id.getText(), ctx.id.getLine());
        Actuator actuator = new Actuator();
        actuator.setName(ctx.id.getText());
        actuator.setPin(Integer.parseInt(ctx.pin.getText().substring(3)));
        actuators.put(actuator.getName(), actuator);
        variables.add(actuator.getName());
        model.getBricks().add(actuator);
    }

    @Override
    public void enterInitialState(ArduinoMLParser.InitialStateContext ctx) {
        checkDuplicate(ctx.id.getText(), ctx.id.getLine());
        currentState = new State();
        currentState.setName(ctx.id.getText());
        variables.add(currentState.getName());
    }

    @Override
    public void exitInitialState(ArduinoMLParser.InitialStateContext ctx) {
        model.setInitial(currentState);
        model.getStates().add(currentState);
        states.put(currentState.getName(), currentState);
    }

    @Override
    public void enterPendingState(ArduinoMLParser.PendingStateContext ctx) {
        checkDuplicate(ctx.id.getText(), ctx.id.getLine());
        currentState = new State();
        currentState.setName(ctx.id.getText());
        variables.add(currentState.getName());
    }

    @Override
    public void exitPendingState(ArduinoMLParser.PendingStateContext ctx) {
        model.getStates().add(currentState);
        states.put(currentState.getName(), currentState);
    }

    @Override
    public void enterAction(ArduinoMLParser.ActionContext ctx) {
        OutputAction action = new OutputAction();
        action.setActuator(actuators.get(ctx.id.getText())); //TODO check null actuator reference
        action.setValue(SIGNAL.valueOf(ctx.signal.getText()));
        currentState.getActions().add(action);
    }

    @Override
    public void enterTransition(ArduinoMLParser.TransitionContext ctx) {
        InputWaiting transition = new InputWaiting();
        transition.setSensor(sensors.get(ctx.id.getText())); //TODO check null sensor reference
        transition.setValue(SIGNAL.valueOf(ctx.signal.getText()));
        transitions.put(transition, ctx.to.getText());
        currentState.getTransitions().add(transition);
    }

    @Override
    public void enterTimedTransition(ArduinoMLParser.TimedTransitionContext ctx) {
        TimeWaiting transition = new TimeWaiting();
        transition.setTimeout(Integer.parseInt(ctx.d.getText()));
        transitions.put(transition, ctx.to.getText());
        currentState.getTransitions().add(transition);
    }
}
