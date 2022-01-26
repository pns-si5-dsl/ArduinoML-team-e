package internal.builders;

import internal.interfaces.NextStateDefinable;
import internal.interfaces.SignalCheckable;
import kernel.model.component.Sensor;
import kernel.model.state.transitions.InputWaiting;
import kernel.model.values.SIGNAL;

public class SensorTransitionBuilder implements Builder<InputWaiting>, SignalCheckable, NextStateDefinable {
    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * The sensor to be monitored.
     */
    private final Sensor sensor;

    /**
     * The signal value that triggers the transition.
     */
    private SIGNAL signal;

    /**
     * The state name associated
     */
    private String nextStateName;

    /**
     * Constructs a transition builder.
     * @param applicationBuilder The application builder.
     * @param sensor The sensor to be monitored.
     */
    public SensorTransitionBuilder(ApplicationBuilder applicationBuilder, Sensor sensor) {
        this.applicationBuilder = applicationBuilder;
        this.sensor = sensor;
    }

    public SensorTransitionBuilder is(SIGNAL value){
        this.signal = value;
        return this;
    }

    /**
     * Define the target state of the transition.
     * @param state The target state of the transition.
     */
    public void then(String state) {
        this.nextStateName = state;
    }

    public InputWaiting build(){
        InputWaiting transition = new InputWaiting();

        // Sensor.
        transition.setSensor(sensor);

        // Signal.
        if (signal == null) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' sensor transition of the '%s' state does not define the signal value that triggers the transition. " +
                    "Please use the 'is()' method to define the expected signal value.",
                    sensor.getName(),
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        transition.setValue(signal);

        // Next state.
        if (nextStateName == null || nextStateName.isEmpty()) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' sensor transition of the '%s' state does not define a next state. " +
                    "Please use the 'then()' method to define the next state of this transition.",
                    sensor.getName(),
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        if (!this.applicationBuilder.hasState(nextStateName)) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' sensor transition of the '%s' state defines a next state '%s' which does not exist. " +
                    "Please make sure the '%s' state exists.",
                    sensor.getName(),
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
