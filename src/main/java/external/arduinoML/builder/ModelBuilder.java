package external.arduinoML.builder;

import external.antlr.ArduinoMLBaseListener;
import external.antlr.ArduinoMLParser;
import external.arduinoML.builder.exceptions.*;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.condition.InputWaiting;
import kernel.model.state.transitions.condition.TimeWaiting;
import kernel.model.state.transitions.Transition;
import kernel.model.values.SIGNAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelBuilder extends ArduinoMLBaseListener {

    private App model;

    private final List<String>            variables   = new ArrayList<>();
    private final List<Integer>           usedPins    = new ArrayList<>();
    private final Map<String, Actuator>   actuators   = new HashMap<>();
    private final Map<String, State>      states      = new HashMap<>();
    private final Map<String, Sensor>     sensors     = new HashMap<>();
    private final Map<Transition, TransitionRef> transitions = new HashMap<>();

    private static class TransitionRef {
        private final String ref;
        private final int line;
        private TransitionRef(String ref, int line) {
            this.ref = ref;
            this.line =line;
        }
    }

    private State currentState = null;

    private RuntimeException exception = null;

    public App build() throws Exception {
        if (model != null) return model;
        throw new ModelNotCreatedException();
    }

    private void addError(Exception e) {
        if (exception == null) exception = new RuntimeException();
        exception.addSuppressed(e);
    }

    private void checkVariableDuplication(String name, int line) {
        if (variables.contains(name)) {
            addError(new DuplicateVariableDeclarationException(name, line));
        } else variables.add(name);
    }

    private void checkPinDuplication(int pin, int line) {
        if (usedPins.contains(pin)) {
            addError(new DuplicatePinAssignmentException(pin, line));
        } else usedPins.add(pin);
    }

    private void checkReference(Object ref, ReferenceType type, String name, int line) {
        if (ref == null)
            addError(new UndefinedReference(type,name,line));
    }

    private void checkIncorrectModel() {
        if (model.getInitial() == null) {
            addError(new InitialStateUndefinedException());
        }
    }

    @Override
    public void enterProgram(ArduinoMLParser.ProgramContext ctx) {
        model = new App();
    }

    @Override
    public void exitProgram(ArduinoMLParser.ProgramContext ctx) {
        transitions.forEach((trans, dest) -> {
            State ref = states.get(dest.ref);
            checkReference(ref, ReferenceType.STATE, dest.ref, dest.line);
            trans.setNext(ref);
        });
        checkIncorrectModel();
        if (exception != null) throw exception;
    }

    @Override
    public void enterSensor(ArduinoMLParser.SensorContext ctx) {
        checkVariableDuplication(ctx.id.getText(), ctx.id.getLine());
        Sensor sensor = new Sensor();
        sensor.setName(ctx.id.getText());
        int pin = Integer.parseInt(ctx.pin.getText().substring(3));
        checkPinDuplication(pin, ctx.pin.getLine());
        sensor.setPin(pin);
        sensors.put(sensor.getName(), sensor);
        model.getBricks().add(sensor);
    }

    @Override
    public void enterActuator(ArduinoMLParser.ActuatorContext ctx) {
        checkVariableDuplication(ctx.id.getText(), ctx.id.getLine());
        Actuator actuator = new Actuator();
        actuator.setName(ctx.id.getText());
        int pin = Integer.parseInt(ctx.pin.getText().substring(3));
        checkPinDuplication(pin, ctx.pin.getLine());
        actuator.setPin(pin);
        actuators.put(actuator.getName(), actuator);
        model.getBricks().add(actuator);
    }

    @Override
    public void enterInitialState(ArduinoMLParser.InitialStateContext ctx) {
        checkVariableDuplication(ctx.id.getText(), ctx.id.getLine());
        currentState = new State();
        currentState.setName(ctx.id.getText());
        model.setInitial(currentState);
        model.getStates().add(currentState);
        states.put(currentState.getName(), currentState);
    }

    @Override
    public void enterPendingState(ArduinoMLParser.PendingStateContext ctx) {
        checkVariableDuplication(ctx.id.getText(), ctx.id.getLine());
        currentState = new State();
        currentState.setName(ctx.id.getText());
        model.getStates().add(currentState);
        states.put(currentState.getName(), currentState);
    }

    @Override
    public void enterAction(ArduinoMLParser.ActionContext ctx) {
        OutputAction action = new OutputAction();
        Actuator ref = actuators.get(ctx.id.getText());
        checkReference(ref, ReferenceType.ACTUATOR,ctx.id.getText(),ctx.id.getLine());
        action.setActuator(ref);
        action.setValue(SIGNAL.valueOf(ctx.signal.getText()));
        currentState.getActions().add(action);
    }

    @Override
    public void enterTransition(ArduinoMLParser.TransitionContext ctx) {
        InputWaiting transition = new InputWaiting();
        Sensor ref = sensors.get(ctx.id.getText());
        checkReference(ref, ReferenceType.SENSOR,ctx.id.getText(),ctx.id.getLine());
        transition.setSensor(ref);
        transition.setValue(SIGNAL.valueOf(ctx.signal.getText()));
        transitions.put(transition, new TransitionRef(ctx.to.getText(), ctx.to.getLine()));
        currentState.getTransitions().add(transition);
    }

    @Override
    public void enterTimedTransition(ArduinoMLParser.TimedTransitionContext ctx) {
        TimeWaiting transition = new TimeWaiting();
        transition.setTimeout(Integer.parseInt(ctx.d.getText()));
        transitions.put(transition, new TransitionRef(ctx.to.getText(), ctx.to.getLine()));
        currentState.getTransitions().add(transition);
    }
}
