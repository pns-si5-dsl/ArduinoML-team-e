package internal.examples;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.annotations.State;
import internal.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML()
public class BlinkApplication extends EmbeddedApplication {
    @Input(pin=9)
    Sensor button;

    @Output(pin=12)
    Actuator led;

    @State(initial=true)
    void stopped() {
        set(led).to(LOW);
        when(button).is(HIGH).then("blink");
    }

    @State()
    void blink() {
        set(led).to(HIGH);
        when(button).is(HIGH).then("stopped");
        after(100).then("blink2");
    }

    @State()
    void blink2() {
        set(led).to(HIGH);
        when(button).is(HIGH).then("stopped");
        after(100).then("blink");
    }
}
