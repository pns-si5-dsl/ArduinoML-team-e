package kernel.model.state.transitions;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.component.Sensor;
import kernel.model.values.SIGNAL;

public class InputWaiting extends Transition implements Visitable {
    private SIGNAL value;
    private Sensor sensor;

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
