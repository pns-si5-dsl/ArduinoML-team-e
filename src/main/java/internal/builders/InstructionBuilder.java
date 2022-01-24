package internal.builders;

import kernel.model.component.Actuator;
import kernel.model.state.actions.OutputAction;
import kernel.model.values.SIGNAL;

public class InstructionBuilder {
    /**
     * The application builder.
     */
    private ApplicationBuilder applicationBuilder;

    /**
     * The actuator to be controlled.
     */
    private Actuator actuator;

    /**
     * Constructs an instruction builder.
     * @param applicationBuilder The application builder.
     * @param actuator The actuator to be controlled.
     */
    public InstructionBuilder(ApplicationBuilder applicationBuilder, Actuator actuator) {
        this.applicationBuilder = applicationBuilder;
        this.actuator = actuator;
    }

    /**
     * Defines the signal value to send to the actuator.
     * @param signal The signal value to be sent.
     */
    public void to(SIGNAL signal) {
        OutputAction action = new OutputAction();
        action.setActuator(actuator);
        action.setValue(signal);
        applicationBuilder.addActionToCurrentState(action);
    }
}
