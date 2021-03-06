package kernel.model.component;
import kernel.generator.Visitable;
import kernel.generator.Visitor;

public class Actuator extends Brick implements Visitable {
    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
