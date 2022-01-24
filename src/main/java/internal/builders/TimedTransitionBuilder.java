package internal.builders;

import kernel.model.App;
import kernel.model.state.transitions.TimeWaiting;

public class TimedTransitionBuilder {
    /**
     * The application under construction.
     */
    private App application;

    /**
     * The timeout in milliseconds.
     */
    private int timeout;

    /**
     * Constructs a timed transition builder.
     * @param application The application under construction.
     * @param timeout The timeout in milliseconds.
     */
    public TimedTransitionBuilder(App application, int timeout) {
        this.application = application;
        this.timeout = timeout;
    }

    /**
     * Define the target state of the transition.
     * @param state The target state of the transition.
     */
    public void then(String state) {
        TimeWaiting transition = new TimeWaiting();
        transition.setTimeout(timeout);
        // TODO: Set the next state and add the transition to the state being built.
    }
}
