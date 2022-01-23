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
    private List<Transition> transitions = new LinkedList<>();

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

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

    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }
}
