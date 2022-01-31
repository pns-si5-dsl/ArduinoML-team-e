package kernel.model.state.transitions.condition;

import kernel.generator.Visitor;
import kernel.model.values.BINARY_OPERATOR;

public class CompositeCheck extends Check {
    private BINARY_OPERATOR operator;
    private Check left;
    private Check right;

    public CompositeCheck() {}

    public CompositeCheck(BINARY_OPERATOR operator, Check left, Check right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Check getLeft() {
        return left;
    }

    public void setLeft(Check left) {
        this.left = left;
    }

    public Check getRight() {
        return right;
    }

    public void setRight(Check right) {
        this.right = right;
    }

    public BINARY_OPERATOR getOperator() {
        return operator;
    }

    public void setOperator(BINARY_OPERATOR operator) {
        this.operator = operator;
    }

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
