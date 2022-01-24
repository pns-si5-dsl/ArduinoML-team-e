package internal.builders;

import internal.models.Sensor;
import internal.models.Signal;

public class ConditionalTransitionBuilder {
    private Sensor sensor;

    public ConditionalTransitionBuilder(Sensor sensor) {
        this.sensor = sensor;
    }

    public TransitionBuilder is(Signal signal) {
        return new TransitionBuilder(sensor, signal);
    }
}
