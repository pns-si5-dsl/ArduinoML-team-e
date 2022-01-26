package internal.examples;

import internal.annotations.ArduinoML;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.annotations.State;
import internal.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML()
public class MultiStateAlarm extends EmbeddedApplication {
    @Input(pin = 1)
    Sensor button;

    @Output(pin = 2)
    Actuator buzzer;

    @Output(pin = 3)
    Actuator led;

    @State(initial = true)
    void off() {
        set(buzzer).to(LOW);
        set(led).to(LOW);
        when(button).is(HIGH).then("onBuzzer");
    }

    @State()
    void onBuzzer() {
        set(buzzer).to(HIGH);
        set(led).to(LOW);
        when(button).is(HIGH).then("onLed");
    }

    @State()
    void onLed() {
        set(buzzer).to(LOW);
        set(led).to(HIGH);
        when(button).is(LOW).then("off");
    }
}
