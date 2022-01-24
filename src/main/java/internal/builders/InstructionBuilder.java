package internal.builders;

import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.state.actions.OutputAction;
import kernel.model.values.SIGNAL;

public class InstructionBuilder {
    /**
     * The application under construction.
     */
    private App application;

    /**
     * The actuator to be controlled.
     */
    private Actuator actuator;

    /**
     * Constructs an instruction builder.
     * @param application The application under construction.
     * @param actuator The actuator to be controlled.
     */
    public InstructionBuilder(App application, Actuator actuator) {
        this.application = application;
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
        // TODO: Add the action to the state being built.
    }
}
