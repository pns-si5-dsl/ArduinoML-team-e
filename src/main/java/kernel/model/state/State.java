package kernel.model.state;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.util.LinkedList;
import java.util.List;

public class State implements Visitable {
    private String name;
    private List<Action> actions = new LinkedList<>();
    private Transition transition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
