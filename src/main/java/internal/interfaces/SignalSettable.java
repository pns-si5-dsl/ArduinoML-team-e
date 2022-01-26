package internal.interfaces;

import kernel.model.values.SIGNAL;

public interface SignalSettable {
    /**
     * Defines the signal value to send to the actuator.
     * @param signal The signal value to be sent.
     */
    void to(SIGNAL signal);
}
