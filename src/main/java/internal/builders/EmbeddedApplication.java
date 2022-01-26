package internal.builders;

import internal.interfaces.NextStateDefinable;
import internal.interfaces.SignalCheckable;
import internal.interfaces.SignalSettable;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.values.SIGNAL;

public abstract class EmbeddedApplication {
    /**
     * The low signal constant.
     * This attribute avoids the need to import the signal enum.
     */
    protected static final SIGNAL LOW = SIGNAL.LOW;

    /**
     * The high signal constant.
     * This attribute avoids the need to import the signal enum.
     */
    protected static final SIGNAL HIGH = SIGNAL.HIGH;

    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * Constructs an embedded application.
     */
    protected EmbeddedApplication() {
        applicationBuilder = new ApplicationBuilder(this);
    }

    /**
     * Defines a digital action.
     * @param actuator The actuator to be controlled.
     * @return The builder to complete the action.
     */
    protected SignalSettable set(Actuator actuator) {
        DigitalActionBuilder builder = new DigitalActionBuilder(applicationBuilder, actuator);
        applicationBuilder.addActionToCurrentState(builder);
        return builder;
    }

    /**
     * Defines a sensor transition.
     * @param sensor The sensor to be monitored.
     * @return The builder to complete the transition.
     */
    protected SignalCheckable when(Sensor sensor) {
        SensorTransitionBuilder builder = new SensorTransitionBuilder(applicationBuilder, sensor);
        applicationBuilder.addTransitionToCurrentState(builder);
        return builder;
    }

    /**
     * Defines a time transition.
     * @param timeout The timeout of the transition (in ms).
     * @return The builder to complete the transition.
     */
    protected NextStateDefinable after(int timeout) {
        TimeTransitionBuilder builder = new TimeTransitionBuilder(applicationBuilder, timeout);
        applicationBuilder.addTransitionToCurrentState(builder);
        return builder;
    }

    /**
     * Builds the application.
     * @return The built application.
     */
    public App build() {
        return applicationBuilder.build();
    }
}
