package internal.builders;

import kernel.model.App;
import kernel.model.component.Sensor;
import kernel.model.state.transitions.InputWaiting;
import kernel.model.values.SIGNAL;

public class TransitionBuilder {
    /**
     * The application under construction.
     */
    private App application;

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
     * @param application The application under construction.
     * @param sensor The sensor to be monitored.
     * @param signal The signal value that triggers the transition.
     */
    public TransitionBuilder(App application, Sensor sensor, SIGNAL signal) {
        this.application = application;
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
        // TODO: Set the next state and add the transition to the state being built.
    }
}
