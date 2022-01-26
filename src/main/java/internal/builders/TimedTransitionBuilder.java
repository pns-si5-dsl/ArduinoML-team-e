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

        if(timeout <=0) throw new IllegalArgumentException("bad timeout in state \"" +"\""); //TODO
        transition.setTimeout(timeout);

        if(nextStateName == null || nextStateName.isEmpty()) throw new IllegalArgumentException("no next state"); //TODO
        if(!this.applicationBuilder.hasState(nextStateName)) throw new IllegalArgumentException("bad next state"); //TODO
        transition.setNext(this.applicationBuilder.getState(nextStateName));

        return transition;
    }
}
