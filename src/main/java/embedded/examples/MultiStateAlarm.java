package embedded.examples;

import embedded.annotations.ArduinoML;
import embedded.annotations.Initial;
import embedded.annotations.Input;
import embedded.annotations.Output;
import embedded.builders.EmbeddedApplication;
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
