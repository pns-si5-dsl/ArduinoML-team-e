package internal.builders;

import kernel.model.component.Actuator;
import kernel.model.state.actions.Action;
import kernel.model.state.actions.OutputAction;
import kernel.model.values.SIGNAL;

public class DigitalActionBuilder implements Builder<OutputAction> {
    private final Actuator actuator;
    private SIGNAL signal;

    public DigitalActionBuilder(Actuator actuator) {
        this.actuator = actuator;
    }

    public void to(SIGNAL value){
        this.signal = value;
    }

    public OutputAction build() {
        OutputAction action = new OutputAction();

        action.setActuator(actuator);

        if(signal == null) throw new IllegalArgumentException("no signal"); //TODO
        action.setValue(signal);

        return action;
    }
}
