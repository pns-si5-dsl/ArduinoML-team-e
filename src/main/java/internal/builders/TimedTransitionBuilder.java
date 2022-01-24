package internal.builders;

import kernel.model.App;

public class TimedTransitionBuilder {
    private App application;
    private long millis;
    private String state;

    public TimedTransitionBuilder(App application, long millis) {
        this.application = application;
        this.millis = millis;
    }

    public void then(String state) {
        this.state = state;
        // TODO: Add the timed transition to the application.
    }
}
