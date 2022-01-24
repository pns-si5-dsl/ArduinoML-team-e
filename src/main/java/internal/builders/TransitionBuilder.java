package internal.builders;

import kernel.model.component.Sensor;
import kernel.model.state.transitions.InputWaiting;
import kernel.model.values.SIGNAL;

public class TransitionBuilder {
    /**
     * The application builder.
     */
    private ApplicationBuilder applicationBuilder;

    /**
     * The sensor to be monitored.
     */
    private Sensor sensor;

    /**
     * The signal value that triggers the transition.
     */
    private SIGNAL signal;

    /**
     * Constructs a transition builder.
     * @param applicationBuilder The application builder.
     * @param sensor The sensor to be monitored.
     * @param signal The signal value that triggers the transition.
     */
    public TransitionBuilder(ApplicationBuilder applicationBuilder, Sensor sensor, SIGNAL signal) {
        this.applicationBuilder = applicationBuilder;
        this.sensor = sensor;
        this.signal = signal;
    }

    /**
     * Define the target state of the transition.
     * @param state The target state of the transition.
     */
    public void then(String state) {
        InputWaiting transition = new InputWaiting();
        transition.setSensor(sensor);
        transition.setValue(signal);
        applicationBuilder.addTransitionToCurrentState(transition, state);
    }
}
