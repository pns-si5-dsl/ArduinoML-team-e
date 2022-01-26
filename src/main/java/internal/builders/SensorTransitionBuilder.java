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

        transition.setSensor(sensor);

        if(signal == null) throw new IllegalArgumentException("no signal"); //TODO
        transition.setValue(signal);

        if(nextStateName == null || nextStateName.isEmpty()) throw new IllegalArgumentException("no next state"); //TODO
        if(!this.applicationBuilder.hasState(nextStateName)) {
            throw new IllegalArgumentException("bad next state"); //TODO
        }
        transition.setNext(this.applicationBuilder.getState(nextStateName));

        return transition;
    }
}
