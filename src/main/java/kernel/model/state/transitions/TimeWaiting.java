package kernel.model.state.transitions;
import kernel.generator.Visitable;
import kernel.generator.Visitor;

public class TimeWaiting extends Check implements Visitable {
    /**
     * time in ms to wait
     */
    int timeout;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
