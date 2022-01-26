package internal.builders;

import internal.interfaces.Builder;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StateBuilder {
    /**
     * The state under construction.
     */
    private final State state;

    /**
     * The action builders of the state.
     */
    private final List<Builder<? extends Action>> actionBuilders =  new LinkedList<>();

    /**
     * The transition builders of the state.
     */
    private final List<Builder<? extends Transition>> transitionBuilders =  new LinkedList<>();

    /**
     * Constructs a state builder.
     * @param stateName The name of the state.
     */
    public StateBuilder(String stateName) {
        state = new State();
        state.setName(stateName);
    }

    /**
     * Returns the state under construction.
     * @return The state under construction.
     */
    public State getState(){
        return state;
    }

    /**
     * Returns the name of the state under construction.
     * @return the name of the state under construction.
     */
    public String getName() {
        return state.getName();
    }

    /**
     * Adds an action builder.
     * @param actionBuilder The action builder to be added.
     */
    public <T extends Builder<? extends Action>> void addAction(T actionBuilder) {
        actionBuilders.add(actionBuilder);
    }

    /**
     * Adds a transition builder.
     * @param transitionBuilder The transition builder to be added.
     */
    public <T extends Builder<? extends Transition>> void addTransition(T transitionBuilder) {
        transitionBuilders.add(transitionBuilder);
    }

    /**
     * Builds the state.
     * @return The built state.
     */
    public State build() {
        // Build the actions.
        state.setActions(
            actionBuilders
                .stream()
                .map(Builder::build)
                .collect(Collectors.toList())
        );

        // Build the transitions.
        state.setTransitions(
            transitionBuilders
                .stream()
                .map(Builder::build)
                .collect(Collectors.toList())
        );

        return state;
    }
}
