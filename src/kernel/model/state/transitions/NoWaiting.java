package kernel.model.state.transitions;
import kernel.generator.Visitable;
import kernel.generator.Visitor;

public class NoWaiting implements Visitable {
    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
