package external.arduinoML.builder;

import external.antlr.ArduinoMLBaseListener;
import external.antlr.ArduinoMLParser;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.condition.Check;
import kernel.model.state.transitions.condition.CompositeCheck;
import kernel.model.state.transitions.condition.InputWaiting;
import kernel.model.state.transitions.condition.TimeWaiting;
import kernel.model.state.transitions.Transition;
import kernel.model.values.SIGNAL;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Map;

import static kernel.model.values.BINARY_OPERATOR.AND;

public class ModelBuilder extends ArduinoMLBaseListener {

    private App model;

    private final Map<String, Actuator>   actuators    = new HashMap<>();
    private final Map<String, State>      states       = new HashMap<>();
    private final Map<String, Sensor>     sensors      = new HashMap<>();
    private final Map<Transition, Token>  transitions  = new HashMap<>();
    private final SyntacticalErrorHandler errorHandler = new SyntacticalErrorHandler();

    private State      currentState      = null;
    private Transition currentTransition = null;

    public App build() throws Exception {
        if (model != null) return model;
        throw new RuntimeException("Model not created!");
    }

    @Override
    public void enterProgram(ArduinoMLParser.ProgramContext ctx) {
        model = new App();
    }

    @Override
    public void exitProgram(ArduinoMLParser.ProgramContext ctx) {
        transitions.forEach((trans, dest) -> {
            State ref = states.get(dest.getText());
            errorHandler.checkReference(ref, ReferenceType.STATE, dest);
            trans.setNext(ref);
        });
        errorHandler.checkIncorrectModel(model);
        errorHandler.validate();
    }

    @Override
    public void enterSensor(ArduinoMLParser.SensorContext ctx) {
        errorHandler.checkVariableDuplication(ctx.id);
        Sensor sensor = new Sensor();
        sensor.setName(ctx.id.getText());
        int pin = Integer.parseInt(ctx.pin.getText().substring(3));
        errorHandler.checkPinDuplication(ctx.pin);
        sensor.setPin(pin);
        sensors.put(sensor.getName(), sensor);
        model.getBricks().add(sensor);
    }

    @Override
    public void enterActuator(ArduinoMLParser.ActuatorContext ctx) {
        errorHandler.checkVariableDuplication(ctx.id);
        Actuator actuator = new Actuator();
        actuator.setName(ctx.id.getText());
        int pin = Integer.parseInt(ctx.pin.getText().substring(3));
        errorHandler.checkPinDuplication(ctx.pin);
        actuator.setPin(pin);
        actuators.put(actuator.getName(), actuator);
        model.getBricks().add(actuator);
    }

    @Override
    public void enterInitialState(ArduinoMLParser.InitialStateContext ctx) {
        errorHandler.checkDuplicateInitial(model, ctx.id);
        errorHandler.checkVariableDuplication(ctx.id);
        currentState = new State();
        currentState.setName(ctx.id.getText());
        model.setInitial(currentState);
        model.getStates().add(currentState);
        states.put(currentState.getName(), currentState);
    }

    @Override
    public void exitInitialState(ArduinoMLParser.InitialStateContext ctx) {
        errorHandler.checkStateTransitions(ctx.id, currentState);
    }

    @Override
    public void enterPendingState(ArduinoMLParser.PendingStateContext ctx) {
        errorHandler.checkVariableDuplication(ctx.id);
        currentState = new State();
        currentState.setName(ctx.id.getText());
        model.getStates().add(currentState);
        states.put(currentState.getName(), currentState);
    }

    @Override
    public void exitPendingState(ArduinoMLParser.PendingStateContext ctx) {
        errorHandler.checkStateTransitions(ctx.id, currentState);
    }

    @Override
    public void enterAction(ArduinoMLParser.ActionContext ctx) {
        OutputAction action = new OutputAction();
        Actuator ref = actuators.get(ctx.id.getText());
        errorHandler.checkReference(ref, ReferenceType.ACTUATOR, ctx.id);
        action.setActuator(ref);
        action.setValue(SIGNAL.valueOf(ctx.signal.getText()));
        currentState.getActions().add(action);
    }

    @Override
    public void enterTransition(ArduinoMLParser.TransitionContext ctx) {
        currentTransition = new Transition();
        transitions.put(currentTransition, ctx.to);
        currentState.getTransitions().add(currentTransition);
    }

    @Override
    public void exitTransition(ArduinoMLParser.TransitionContext ctx) {
        errorHandler.checkTransition(ctx.to, currentTransition);
    }

    @Override
    public void enterSensorCondition(ArduinoMLParser.SensorConditionContext ctx) {
        InputWaiting condition = new InputWaiting();
        Sensor ref = sensors.get(ctx.id.getText());
        errorHandler.checkReference(ref, ReferenceType.SENSOR, ctx.id);
        condition.setSensor(ref);
        condition.setValue(SIGNAL.valueOf(ctx.signal.getText()));
        composeCondition(condition);
    }

    @Override
    public void enterTimedCondition(ArduinoMLParser.TimedConditionContext ctx) {
        TimeWaiting condition = new TimeWaiting();
        condition.setTimeout(Integer.parseInt(ctx.d.getText()));
        composeCondition(condition);
    }

    private void composeCondition(Check condition) {
        if (currentTransition.getCondition() == null) {
            currentTransition.setCondition(condition);
        } else {
            currentTransition.setCondition(
                    new CompositeCheck(AND,currentTransition.getCondition(), condition)
            );
        }
    }
}
