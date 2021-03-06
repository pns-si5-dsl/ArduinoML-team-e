package embedded.interfaces;

import kernel.model.values.SIGNAL;

public interface SignalCheckable {
    /**
     * Defines the signal value that triggers the transition.
     * @param signal The signal value that triggers the transition.
     * @return The builder to complete the transition.
     */
    Composable is(SIGNAL signal);
}
