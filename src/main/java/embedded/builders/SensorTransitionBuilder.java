package embedded.builders;

import embedded.interfaces.Builder;
import embedded.interfaces.Composable;
import embedded.interfaces.SignalCheckable;
import kernel.model.component.Sensor;
import kernel.model.state.transitions.Transition;
import kernel.model.state.transitions.condition.Check;
import kernel.model.state.transitions.condition.CompositeCheck;
import kernel.model.state.transitions.condition.InputWaiting;
import kernel.model.values.BINARY_OPERATOR;
import kernel.model.values.SIGNAL;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SensorTransitionBuilder implements Builder<Transition>, SignalCheckable, Composable {
    /**
     * The application builder.
     */
    private final ApplicationBuilder applicationBuilder;

    /**
     * KEY : The sensor to be monitored.
     * VALUE : The signal value that triggers the transition.
     */
    private final Map<Sensor, SIGNAL> sensors = new HashMap<>();
    private Sensor lastSensor;

    /**
     * The next state of the transition.
     */
    private String nextStateName;

    /**
     * Constructs a sensor transition builder.
     * @param applicationBuilder The application builder.
     * @param sensor The sensor to be monitored.
     */
    public SensorTransitionBuilder(ApplicationBuilder applicationBuilder, Sensor sensor) {
        this.applicationBuilder = applicationBuilder;
        this.lastSensor = sensor;
    }

    /**
     * Defines the signal value that triggers the transition.
     * @param signal The signal value that triggers the transition.
     * @return The builder to complete the transition.
     */
    public Composable is(SIGNAL signal) {
        if(this.sensors.put(lastSensor, signal) != null)
            throw new IllegalArgumentException(
                    String.format(
                            "A transition of the state '%s' use two time or more the sensor '%s'.",
                            sensors.keySet().stream().map(Sensor::getName).collect(Collectors.joining("-")),
                            applicationBuilder.getCurrentStateBuilder().getName(),
                            lastSensor.getName()
                    )
            );
        return this;
    }

    /**
     * Continue the definition condition of the transition by adding a new condition to check
     *
     * @param sensor the sensor to check
     */
    @Override
    public SignalCheckable and(Sensor sensor) {
        this.lastSensor = sensor;
        return this;
    }

    /**
     * Define the next state of the transition.
     * @param nextStateName The next state of the transition.
     */
    public void then(String nextStateName) {
        this.nextStateName = nextStateName;
    }

    /**
     * Builds the sensor transition.
     * @return The built sensor transition.
     */
    public Transition build() {
        Transition transition = new Transition();
        Check check = null;

        // last sensor assign to a signal ?
        if (!this.sensors.containsKey(lastSensor)) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' sensor transition of the '%s' state does not define the signal value that triggers the transition. " +
                    "Please use the 'is()' method to define the expected signal value.",
                    lastSensor.getName(),
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }

        // Sensors.
        for (Sensor sensor : this.sensors.keySet()){
            if(check == null) {
                check = new InputWaiting(sensors.get(sensor), sensor);
            } else {
                check = new CompositeCheck(
                        BINARY_OPERATOR.AND,
                        new InputWaiting(sensors.get(sensor), sensor),
                        check
                );
            }
        }

        // Next state.
        if (nextStateName == null || nextStateName.isEmpty()) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' sensor transition of the '%s' state does not define a next state. " +
                    "Please use the 'then()' method to define the next state of this transition.",
                    sensors.keySet().stream().map(Sensor::getName).collect(Collectors.joining("-")),
                    applicationBuilder.getCurrentStateBuilder().getName()
                )
            );
        }
        if (!applicationBuilder.hasState(nextStateName)) {
            throw new IllegalArgumentException(
                String.format(
                    "The '%s' sensor transition of the '%s' state defines a next state '%s' which does not exist. " +
                    "Please make sure the '%s' state exists.",
                    sensors.keySet().stream().map(Sensor::getName).collect(Collectors.joining("-")),
                    applicationBuilder.getCurrentStateBuilder().getName(),
                    nextStateName,
                    nextStateName
                )
            );
        }
        transition.setCondition(check);
        transition.setNext(applicationBuilder.getState(nextStateName));

        return transition;
    }
}
