package internal.examples;

import internal.annotations.*;
import internal.models.Actuator;
import internal.builders.EmbeddedApplication;
import internal.models.Sensor;

import static internal.models.Signal.HIGH;
import static internal.models.Signal.LOW;

@ArduinoML()
public class SimpleApplication extends EmbeddedApplication {
    @Input(pin=9)
    Sensor button;

    @Output(pin=12)
    Actuator led;

    @State()
    void on() {
        set(led).to(HIGH);
        when(button).is(LOW).then("off");
    }

    @State(initial=true)
    void off() {
        set(led).to(LOW);
        when(button).is(HIGH).then("on");
    }
}