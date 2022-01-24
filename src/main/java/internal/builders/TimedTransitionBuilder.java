package internal.builders;

public class TimedTransitionBuilder {
    private long millis;
    private String state;

    public TimedTransitionBuilder(long millis) {
        this.millis = millis;
    }

    public void then(String state) {
        this.state = state;
    }
}
