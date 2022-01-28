package internal.builders;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Brick;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.Transition;
import kernel.model.state.transitions.condition.CompositeCheck;
import kernel.model.state.transitions.condition.InputWaiting;
import kernel.model.state.transitions.condition.TimeWaiting;
import kernel.model.values.SIGNAL;

import java.util.*;
import java.util.stream.Collectors;

class UnityIdentifier implements Visitor<String> {

    private enum Type {
        ACTION,
        TRANSITION
    }

    private final Map<Sensor, SIGNAL> sensorMap = new HashMap<>();
    private final Set<Actuator> actuators = new HashSet<>();
    private int time = -1;
    private Type type;

    public UnityIdentifier(Visitable toIdentify){
        toIdentify.accept(this);
    }

    @Override
    public void visit(App app) {
        // not use here
    }

    @Override
    public void visit(Actuator actuator) {
        actuators.add(actuator);
    }

    @Override
    public void visit(Sensor sensor) {
        // not use here
    }

    @Override
    public void visit(State state) {
        // not use here
    }

    @Override
    public void visit(OutputAction action) {
        action.getActuator().accept(this);
        type = Type.ACTION;
    }

    @Override
    public void visit(Transition transition) {
        type = Type.TRANSITION;
        transition.getCondition().accept(this);
    }

    @Override
    public void visit(InputWaiting check) {
        sensorMap.put(check.getSensor(), check.getValue());
    }

    @Override
    public void visit(TimeWaiting noCheck) {
        time = noCheck.getTimeout();
    }

    @Override
    public void visit(CompositeCheck check) {
        check.getLeft().accept(this);
        check.getRight().accept(this);
    }

    @Override
    public String getGeneratedCode() {
        switch (type){
            case ACTION:
                return actuators.stream()
                        .map(Brick::getName)
                        .collect(Collectors.joining("-"));
            case TRANSITION:
                return sensorMap.entrySet().stream()
                        .map(e -> e.getKey().getName() +"="+ e.getValue())
                        .collect(Collectors.joining("-"));
            default:
                if(isTimeout()) return "TIMEOUT="+time+"ms";
                return "";
        }
    }

    public boolean isTimeout(){
        return time != -1;
    }

    public boolean equivalentTo(UnityIdentifier o){
        if(o == null) return false;
        if(type != o.type) return false;
        if(type == Type.ACTION) return actuators.equals(o.actuators);
        if(type == Type.TRANSITION) return sensorMap.equals(o.sensorMap) && isTimeout() == o.isTimeout();
        return false;
    }
}
