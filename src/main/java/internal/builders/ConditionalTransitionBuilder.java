package internal.builders;

import internal.models.Sensor;
import internal.models.Signal;
import kernel.model.App;

public class ConditionalTransitionBuilder {
    private App application;
    private Sensor sensor;

    public ConditionalTransitionBuilder(App application, Sensor sensor) {
        this.application = application;
        this.sensor = sensor;
    }

    public TransitionBuilder is(Signal signal) {
        return new TransitionBuilder(application, sensor, signal);
    }
}
