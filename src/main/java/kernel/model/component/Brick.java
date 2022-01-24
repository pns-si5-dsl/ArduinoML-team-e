package kernel.model.component;

import kernel.generator.Visitable;
import kernel.model.NamedElement;

public abstract class Brick implements NamedElement, Visitable {
    private String name;
    private int pin;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
