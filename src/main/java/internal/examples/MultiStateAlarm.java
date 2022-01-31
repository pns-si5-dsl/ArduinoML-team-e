package internal.examples;

import internal.annotations.ArduinoML;
import internal.annotations.Initial;
import internal.annotations.Input;
import internal.annotations.Output;
import internal.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML()
public class MultiStateAlarm extends EmbeddedApplication {
    @Input(pin = 8)
    Sensor button;

    @Output(pin = 10)
    Actuator buzzer;

    @Output(pin = 12)
    Actuator led;

    @Initial
    void off() {
        set(buzzer).to(LOW);
        set(led).to(LOW);
        when(button).is(HIGH).then("onBuzzer");
    }

    void onBuzzer() {
        set(buzzer).to(HIGH);
        set(led).to(LOW);
        when(button).is(HIGH).then("onLed");
    }

    void onLed() {
        set(buzzer).to(LOW);
        set(led).to(HIGH);
        when(button).is(HIGH).then("off");
    }
}
