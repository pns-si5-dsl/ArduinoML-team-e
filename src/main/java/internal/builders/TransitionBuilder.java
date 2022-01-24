package internal.builders;

import internal.models.Sensor;
import internal.models.Signal;

public class TransitionBuilder {
    private Sensor sensor;
    private Signal signal;
    private String state;

    public TransitionBuilder(Sensor sensor, Signal signal) {
        this.sensor = sensor;
        this.signal = signal;
    }

    public void then(String state) {
        this.state = state;
    }
}
