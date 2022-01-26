package kernel.generator;

import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.transitions.condition.CompositeCheck;
import kernel.model.state.transitions.condition.InputWaiting;
import kernel.model.state.transitions.condition.TimeWaiting;
import kernel.model.state.transitions.Transition;

public interface Visitor<T> {
    void visit(App app);

    void visit(Actuator actuator);
    void visit(Sensor sensor);

    void visit(State state);
    void visit(OutputAction action);
    void visit(Transition transition);

    void visit(InputWaiting check);
    void visit(TimeWaiting noCheck);
    void visit(CompositeCheck check);

    // The Generator have to implement a method that return a results => The generated code
    T getGeneratedCode();
}
