package internal.builders;

import internal.models.Sensor;
import internal.models.Signal;
import kernel.model.App;

public class TransitionBuilder {
    private App application;
    private Sensor sensor;
    private Signal signal;
    private String state;

    public TransitionBuilder(App application, Sensor sensor, Signal signal) {
        this.application = application;
        this.sensor = sensor;
        this.signal = signal;
    }

    public void then(String state) {
        this.state = state;
        // TODO: Add the transition to the application.
    }
}
