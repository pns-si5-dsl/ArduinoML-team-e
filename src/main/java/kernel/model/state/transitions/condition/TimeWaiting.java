package kernel.model.state.transitions.condition;
import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.state.transitions.Transition;

public class TimeWaiting extends Check {
    /**
     * time in ms to wait
     */
    private int timeout;

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
