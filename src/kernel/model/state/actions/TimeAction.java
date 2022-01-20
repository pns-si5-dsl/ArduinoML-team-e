package kernel.model.state.actions;
import kernel.generator.Visitable;
import kernel.generator.Visitor;

public class TimeAction implements Visitable {
    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
