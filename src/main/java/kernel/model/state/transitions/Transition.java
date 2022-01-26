package kernel.model.state.transitions;


import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.state.State;

public class Transition implements Visitable {
    private State next;

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
