package embedded.builders;

import embedded.interfaces.Builder;
import embedded.interfaces.SignalSettable;
import kernel.model.component.Actuator;
import kernel.model.state.actions.OutputAction;
import kernel.model.values.SIGNAL;

public class DigitalActionBuilder implements Builder<OutputAction>, SignalSettable {
    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * The actuator to be controlled.
     */
    private final Actuator actuator;

    /**
     * The signal value to be sent.
     */
    private SIGNAL signal;

    /**
     * Constructs a digital action builder.
     * @param applicationBuilder The application builder.
     * @param actuator The actuator to be controlled.
     */
    public DigitalActionBuilder(ApplicationBuilder applicationBuilder, Actuator actuator) {
        this.applicationBuilder = applicationBuilder;
        this.actuator = actuator;
    }

    /**
     * Defines the signal value to send to the actuator.
     * @param signal The signal value to be sent.
     */
    public void to(SIGNAL signal) {
        this.signal = signal;
    }

    /**
     * Builds the digital action.
     * @return The built digital action.
     */
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
