package internal.builders;

import internal.models.Actuator;
import internal.models.Signal;
import kernel.model.App;

public class InstructionBuilder {
    private App application;
    private Actuator actuator;
    private Signal signal;

    public InstructionBuilder(App application, Actuator actuator) {
        this.application = application;
        this.actuator = actuator;
    }

    public void to(Signal signal) {
        this.signal = signal;
        // TODO: Add the instruction to the application.
    }
}
