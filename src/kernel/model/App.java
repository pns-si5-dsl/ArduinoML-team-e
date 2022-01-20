package kernel.model;
import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.model.component.Brick;
import kernel.model.state.State;

import java.util.LinkedList;
import java.util.List;

public class App implements Visitable, NamedElement {


    private String name;
    private List<Brick> bricks = new LinkedList<>();
    private List<State> states = new LinkedList<>();
    private State initial;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public State getInitial() {
        return initial;
    }

    public void setInitial(State initial) {
        this.initial = initial;
    }
	
    @Override
    public void accept(Visitor<?> visitor) {
        visitor.visit(this);
    }

}
