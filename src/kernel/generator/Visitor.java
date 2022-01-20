package kernel.generator;

import kernel.model.App;

public interface Visitor<T> {
    void visit(App app);
    //TODO for each model class
}
