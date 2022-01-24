package internal.builders;

import kernel.model.App;
import kernel.model.component.Sensor;
import kernel.model.values.SIGNAL;

public class ConditionalTransitionBuilder {
    /**
     * The application under construction.
     */
    private App application;

    /**
     * The sensor to be monitored.
     */
    private Sensor sensor;

    /**
     * Constructs a conditional transition builder.
     * @param application The application under construction.
     * @param sensor The sensor to be monitored.
     */
    public ConditionalTransitionBuilder(App application, Sensor sensor) {
        this.application = application;
        this.sensor = sensor;
    }

    /**
     * Defines the condition for triggering the transition.
     * @param signal The signal value that triggers the transition.
     * @return A builder to complete the transition.
     */
    public TransitionBuilder is(SIGNAL signal) {
        return new TransitionBuilder(application, sensor, signal);
    }
}
