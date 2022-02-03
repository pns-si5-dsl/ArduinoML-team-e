package external.arduinoML.builder;

import kernel.model.App;
import kernel.model.state.State;
import kernel.model.state.transitions.Transition;
import kernel.model.state.transitions.condition.Check;
import kernel.model.state.transitions.condition.CompositeCheck;
import kernel.model.state.transitions.condition.TimeWaiting;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class SyntacticalErrorHandler {

    private final List<String> variables = new ArrayList<>();
    private final List<String> usedPins  = new ArrayList<>();

    private RuntimeException exception = null;

    void validate() {
        if (exception != null) throw exception;
    }

    void addError(Token token, String msg) {
        addError("line "+token.getLine()+":"+token.getCharPositionInLine()+" "+msg);
    }

    void addError(String msg) {
        if (exception == null) exception = new RuntimeException();
        exception.addSuppressed(
                new RuntimeException(msg)
        );
    }

    void checkVariableDuplication(Token token) {
        if (variables.contains(token.getText())) {
            addError(token, "'"+token.getText()+"' already defined!");
        } else variables.add(token.getText());
    }

    void checkPinDuplication(Token token) {
        if (usedPins.contains(token.getText())) {
            addError(token, "'"+token.getText()+"' already assigned!");
        } else usedPins.add(token.getText());
    }

    void checkReference(Object ref, ReferenceType type, Token token) {
        if (ref == null)
            addError(token, type.toString()+" '"+token.getText()+" undefined!");
    }

    public void checkDuplicateInitial(App model, Token token) {
        if (model.getInitial() != null) {
            addError(token, "only one initial state must be defined.");
        }
    }

    void checkIncorrectModel(App model) {
        if (model.getInitial() == null) {
            addError("No initial state was found in the program.");
        }
    }

    void checkStateTransitions(Token token, State state) {
        int temporalCount = 0;
        for (Transition t : state.getTransitions()) {
            if (t.getCondition().getClass().equals(TimeWaiting.class)) temporalCount++;
        }
        if (temporalCount > 1) {
            addError(token, "multiple temporal transitions found in state '"+state.getName()+"'!");
        }
    }

    void checkTransition(Token token, Transition transition) {
        List<Check> checks = new ArrayList<>();
        retrieveChecks(transition.getCondition(), checks);
        int temporalCount = 0;
        for (Check check : checks) {
            if (check.getClass().equals(TimeWaiting.class)) temporalCount++;
        }
        if (temporalCount > 1) {
            addError(token, "multiple temporal conditions found in transition!");
        }
        // TODO check for check duplication
    }

    private void retrieveChecks(Check check, List<Check> checks) {
        if (check.getClass().equals(CompositeCheck.class)) {
            CompositeCheck compositeCheck = (CompositeCheck) check;
            retrieveChecks(compositeCheck.getLeft(), checks);
            retrieveChecks(compositeCheck.getRight(), checks);
        } else checks.add(check);
    }
}
