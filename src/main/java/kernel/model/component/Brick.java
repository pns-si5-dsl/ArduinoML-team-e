package kernel.model.component;

import kernel.generator.Visitable;
import kernel.model.NamedElement;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brick brick = (Brick) o;

        if (pin != brick.pin) return false;
        return Objects.equals(name, brick.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + pin;
        return result;
    }
}
