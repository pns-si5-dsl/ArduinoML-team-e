package kernel.model.state.transitions;


import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.state.State;
import kernel.model.state.transitions.condition.Check;

public class Transition implements Visitable {
    private State next;
    private Check condition;

    public Check getCondition() {
        return condition;
    }

    public void setCondition(Check condition) {
        this.condition = condition;
    }

    public State getNext() {
        return next;
    }

    public void setNext(State next) {
        this.next = next;
    }

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
