package kernel.generator;

import kernel.model.App;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;
import kernel.model.state.State;
import kernel.model.state.actions.OutputAction;
import kernel.model.state.actions.TimeAction;
import kernel.model.state.transitions.InputWaiting;
import kernel.model.state.transitions.NoWaiting;
import kernel.model.state.transitions.Transition;

public interface Visitor<T> {
    void visit(App app);

    void visit(Actuator actuator);
    void visit(Sensor sensor);

    void visit(State state);
    void visit(OutputAction action);
    void visit(TimeAction action);
    void visit(Transition transition);
    void visit(InputWaiting check);
    void visit(NoWaiting noCheck);
}
