package internal.builders;

import internal.interfaces.NextStateDefinable;
import internal.interfaces.SignalCheckable;
import internal.interfaces.SignalSettable;
import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.values.SIGNAL;

// TODO: Use an annotation processor at compile time to avoid having to inherit this class manually.
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
        this.applicationBuilder = new ApplicationBuilder(this);
    }

    /**
     * Defines an instruction on an actuator.
     * @param actuator The actuator to be controlled.
     * @return A builder to complete the instruction.
     */
    protected SignalSettable set(Actuator actuator) {
        DigitalActionBuilder builder = new DigitalActionBuilder(applicationBuilder, actuator);
        this.applicationBuilder.addActionToCurrentState(builder);
        return builder;
    }

    /**
     * Defines a transition triggered by a sensor.
     * @param sensor The sensor to be monitored.
     * @return A builder to complete the transition.
     */
    protected SignalCheckable when(Sensor sensor) {
        SensorTransitionBuilder builder = new SensorTransitionBuilder(applicationBuilder, sensor);
        this.applicationBuilder.addTransitionToCurrentState(builder);
        return builder;
    }

    /**
     * Defines a transition triggered by a timer.
     * @param timeout The timeout in milliseconds.
     * @return The builder to complete the transition.
     */
    protected NextStateDefinable after(int timeout) {
        TimedTransitionBuilder builder = new TimedTransitionBuilder(applicationBuilder, timeout);
        this.applicationBuilder.addTransitionToCurrentState(builder);
        return builder;
    }

    /**
     * Builds the application using the embedded DSL.
     * @return The application built using the embedded DSL.
     */
    public App build() {
        return this.applicationBuilder.build();
    }
}
