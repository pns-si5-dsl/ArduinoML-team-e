package internal.builders;

import internal.interfaces.SignalSettable;
import kernel.model.component.Actuator;
import kernel.model.state.actions.OutputAction;
import kernel.model.values.SIGNAL;

public class DigitalActionBuilder implements Builder<OutputAction>, SignalSettable {
    private final ApplicationBuilder applicationBuilder;
    private final Actuator actuator;
    private SIGNAL signal;

    public DigitalActionBuilder(ApplicationBuilder applicationBuilder, Actuator actuator) {
        this.applicationBuilder = applicationBuilder;
        this.actuator = actuator;
    }

    public void to(SIGNAL value){
        this.signal = value;
    }

    public OutputAction build() {
        OutputAction action = new OutputAction();

        // Actuator.
        action.setActuator(actuator);

        // Signal.
        if (signal == null) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' actuator action of the '%s' state does not define the signal value to send to the actuator. " +
                    "Please use the 'to()' method to set the signal value.",
                    actuator.getName(),
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        action.setValue(signal);

        return action;
    }
}
