package internal.builders;

import kernel.model.component.Sensor;
import kernel.model.values.SIGNAL;

public class ConditionalTransitionBuilder {
    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * The sensor to be monitored.
     */
    private final Sensor sensor;

    /**
     * Constructs a conditional transition builder.
     * @param applicationBuilder The application builder.
     * @param sensor The sensor to be monitored.
     */
    public ConditionalTransitionBuilder(ApplicationBuilder applicationBuilder, Sensor sensor) {
        this.applicationBuilder = applicationBuilder;
        this.sensor = sensor;
    }

    /**
     * Defines the condition for triggering the transition.
     * @param signal The signal value that triggers the transition.
     * @return A builder to complete the transition.
     */
    public TransitionBuilder is(SIGNAL signal) {
        return new TransitionBuilder(applicationBuilder, sensor, signal);
    }
}
