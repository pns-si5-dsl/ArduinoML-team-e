package internal.builders;

import internal.interfaces.NextStateDefinable;
import kernel.model.state.transitions.TimeWaiting;

public class TimedTransitionBuilder implements Builder<TimeWaiting>, NextStateDefinable {
    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * The timeout in milliseconds.
     */
    private final int timeout;

    /***
     * the state name
     */
    private String nextStateName;

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
        this.nextStateName = state;
    }

    public TimeWaiting build(){
        TimeWaiting transition = new TimeWaiting();

        // Transition.
        if (timeout <= 0) {
            throw new IllegalArgumentException(
                String.format(
                    "The provided timeout (%d) of a timed transition of the '%s' state is invalid. " +
                    "Please make sure to enter a positive or zero value.",
                    timeout,
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        transition.setTimeout(timeout);

        // Next state.
        if (nextStateName == null || nextStateName.isEmpty()) {
            throw new IllegalArgumentException(
                String.format(
                    "A timed transition of the '%s' state does not define a next state. " +
                    "Please use the 'then()' method to define the next state of this transition.",
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        if (!this.applicationBuilder.hasState(nextStateName)) {
            throw new IllegalArgumentException(
                String.format(
                    "A timed transition of the '%s' state defines a next state '%s' which does not exist. " +
                    "Please make sure the '%s' state exists.",
                    applicationBuilder.getCurrentStateBuilder().getName(),
                    nextStateName,
                    nextStateName
                )
            );
        }
        transition.setNext(this.applicationBuilder.getState(nextStateName));

        return transition;
    }
}
