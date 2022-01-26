package internal.examples;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.annotations.State;
import internal.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML()
public class DualCheckAlarm extends EmbeddedApplication {
    @Input(pin = 1)
    Sensor button1;
    @Input(pin = 2)
    Sensor button2;

    @Output(pin = 3)
    Actuator led;

    @State(initial = true)
    void off(){
        set(led).to(LOW);

        when(button1).is(HIGH).and(button2).is(HIGH).then("on");
    }

    @State
    void on(){
        set(led).to(HIGH);

        when(button1).is(LOW).then("off");
        when(button2).is(LOW).then("off");
    }
}
