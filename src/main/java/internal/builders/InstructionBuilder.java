package internal.builders;

import internal.models.Actuator;
import internal.models.Signal;

public class InstructionBuilder {
    private Actuator actuator;
    private Signal signal;

    public InstructionBuilder(Actuator actuator) {
        this.actuator = actuator;
    }

    public void to(Signal signal) {
        this.signal = signal;
    }
}
