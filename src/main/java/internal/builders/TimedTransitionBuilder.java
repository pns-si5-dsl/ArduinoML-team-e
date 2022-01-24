package internal.builders;

import kernel.model.state.transitions.TimeWaiting;

public class TimedTransitionBuilder {
    /**
     * The application builder.
     */
    private ApplicationBuilder applicationBuilder;

    /**
     * The timeout in milliseconds.
     */
    private int timeout;

    /**
     * Constructs a timed transition builder.
     * @param applicationBuilder The application builder.
     * @param timeout The timeout in milliseconds.
     */
    public TimedTransitionBuilder(ApplicationBuilder applicationBuilder, int timeout) {
        this.applicationBuilder = applicationBuilder;
        this.timeout = timeout;
    }

    /**
     * Define the target state of the transition.
     * @param state The target state of the transition.
     */
    public void then(String state) {
        TimeWaiting transition = new TimeWaiting();
        transition.setTimeout(timeout);
        applicationBuilder.addTransitionToCurrentState(transition, state);
    }
}
