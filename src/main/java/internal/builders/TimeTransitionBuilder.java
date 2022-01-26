package internal.builders;

import internal.interfaces.Builder;
import internal.interfaces.NextStateDefinable;
import kernel.model.state.transitions.Transition;
import kernel.model.state.transitions.condition.TimeWaiting;

public class TimeTransitionBuilder implements Builder<Transition>, NextStateDefinable {
    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * The timeout of the transition (in ms).
     */
    private final int timeout;

    /**
     * The next state of the transition.
     */
    private String nextStateName;

    /**
     * Constructs a time transition builder.
     * @param applicationBuilder The application builder.
     * @param timeout The timeout of the transition (in ms).
     */
    public TimeTransitionBuilder(ApplicationBuilder applicationBuilder, int timeout) {
        this.applicationBuilder = applicationBuilder;
        this.timeout = timeout;
    }

    /**
     * Defines the next state of the transition.
     * @param nextStateName The next state of the transition.
     */
    public void then(String nextStateName) {
        this.nextStateName = nextStateName;
    }

    /**
     * Builds the time transition.
     * @return The built time transition.
     */
    public Transition build() {
        Transition transition = new Transition();
        TimeWaiting check = new TimeWaiting();

        // Transition.
        if (timeout <= 0) {
            throw new IllegalArgumentException(
                String.format(
                    "The provided timeout (%d) for the time transition of the '%s' state is invalid. " +
                    "Please make sure to enter a positive value.",
                    timeout,
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        check.setTimeout(timeout);

        // Next state.
        if (nextStateName == null || nextStateName.isEmpty()) {
            throw new IllegalArgumentException(
                String.format(
                    "The time transition of the '%s' state does not define a next state. " +
                    "Please use the 'then()' method to define the next state of this transition.",
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        if (!applicationBuilder.hasState(nextStateName)) {
            throw new IllegalArgumentException(
                String.format(
                    "The time transition of the '%s' state defines a next state '%s' which does not exist. " +
                    "Please make sure the '%s' state exists.",
                    applicationBuilder.getCurrentStateBuilder().getName(),
                    nextStateName,
                    nextStateName
                )
            );
        }
        transition.setNext(applicationBuilder.getState(nextStateName));
        transition.setCondition(check);

        return transition;
    }
}
