package internal.builders;

import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StateBuilder {
    private final State state = new State();

    private final List<Builder<? extends Action>> actionBuilders =  new LinkedList<>();

    private final List<Builder<? extends Transition>> transitionBuilders =  new LinkedList<>();

    public StateBuilder(String stateName){
        state.setName(stateName);
    }



    public State build(){
        state.setActions(
                actionBuilders
                    .stream()
                    .map(Builder::build)
                    .collect(Collectors.toList())
        );
        state.setTransitions(
                transitionBuilders
                    .stream()
                    .map(Builder::build)
                    .collect(Collectors.toList())
        );
        return state;
    }

    public <ActionBuilder extends Builder<? extends Action>> void addAction(ActionBuilder builder) {
        this.actionBuilders.add(builder);
    }

    public <TransitionBuilder extends Builder<? extends Transition>> void addTransition(TransitionBuilder builder) {
        this.transitionBuilders.add(builder);
    }

    public State getState(){
        return state;
    }
}
