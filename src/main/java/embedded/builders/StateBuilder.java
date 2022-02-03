package embedded.builders;

import embedded.interfaces.Builder;
import kernel.model.state.State;
import kernel.model.state.actions.Action;
import kernel.model.state.transitions.Transition;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
        checkActionUnity(state.getActions());
        if(state.getActions().isEmpty())
            System.out.printf(
                "#### WARNING : The state '%s' define no action. %n",
                state.getName()
            );

        // Build the transitions.
        state.setTransitions(
            transitionBuilders
                .stream()
                .map(Builder::build)
                .collect(Collectors.toList())
        );
        checkTransitionUnity(state.getTransitions());
        if(state.getTransitions().isEmpty())
            System.out.printf(
                "#### WARNING : The state '%s' define no transition. %n",
                state.getName()
            );

        return state;
    }

    private void checkTransitionUnity(List<Transition> transitions) {
        List<UnityIdentifier> identifiers = transitions
                .stream()
                .map(UnityIdentifier::new)
                .collect(Collectors.toList());

        while (!identifiers.isEmpty()) {
            UnityIdentifier identifier = identifiers.remove(0);
            Optional<?> match = identifiers.stream()
                    .filter(id -> id.equivalentTo(identifier))
                    .findAny();

            // if the same condition if define many times
            if(match.isPresent()){
                throw new IllegalArgumentException(
                        identifier.isTimeout() ?
                        String.format(
                                "There is two or more time transition in the '%s' state. ",
                                state.getName()
                        ):
                        String.format(
                                "The transition based on values '%s' is define two or more time in the '%s' state. ",
                                identifier.getGeneratedCode(),
                                state.getName()
                        )
                );
            }
        }
    }

    private void checkActionUnity(List<Action> actions) {
        List<UnityIdentifier> identifiers = actions
                .stream()
                .map(UnityIdentifier::new)
                .collect(Collectors.toList());

        while (!identifiers.isEmpty()) {
            UnityIdentifier identifier = identifiers.remove(0);
            Optional<?> match = identifiers.stream()
                    .filter(id -> id.equivalentTo(identifier))
                    .findAny();

            // if the same action if define many times
            if(match.isPresent()){
                throw new IllegalArgumentException(
                    String.format(
                        "The '%s' actuator is set two or more time in the '%s' state. ",
                        identifier.getGeneratedCode(),
                        state.getName()
                    )
                );
            }
        }
    }
}
