package kernel.model.state.actions;
import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.component.Actuator;
import kernel.model.values.SIGNAL;

public class OutputAction extends Action implements Visitable {
    private SIGNAL value;
    private Actuator actuator;

	
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

    public Actuator getActuator() {
        return actuator;
    }

    public void setActuator(Actuator actuator) {
        this.actuator = actuator;
    }
}
