package kernel.model.state.transitions.condition;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.component.Sensor;
import kernel.model.state.transitions.Transition;
import kernel.model.values.SIGNAL;

public class InputWaiting extends Check implements Visitable {
    private SIGNAL value;
    private Sensor sensor;

    public InputWaiting(){}

    public InputWaiting(SIGNAL value, Sensor sensor) {
        this.value = value;
        this.sensor = sensor;
    }

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }

    public SIGNAL getValue() {
        return value;
    }

    public void setValue(SIGNAL value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
