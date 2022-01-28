package internal.examples;

import internal.annotations.ArduinoML;
import internal.annotations.Initial;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML()
public class SimpleApplication extends EmbeddedApplication {
    @Input(pin = 9)
    Sensor button;

    @Output(pin = 12)
    Actuator led;

    void on() {
        set(led).to(HIGH);
        when(button).is(LOW).then("off");
    }

    @Initial
    void off() {
        set(led).to(LOW);
        when(button).is(HIGH).then("on");
    }
}
