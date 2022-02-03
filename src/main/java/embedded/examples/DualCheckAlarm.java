package embedded.examples;

import embedded.annotations.ArduinoML;
import embedded.annotations.Input;
import embedded.annotations.Output;
import embedded.annotations.Initial;
import embedded.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML()
public class DualCheckAlarm extends EmbeddedApplication {
    @Input(pin = 8)
    Sensor button1;
    @Input(pin = 9)
    Sensor button2;

    @Output(pin = 12)
    Actuator led;

    @Initial
    void off(){
        set(led).to(LOW);

        when(button1).is(HIGH).and(button2).is(HIGH).then("on");
    }

    void on(){
        set(led).to(HIGH);

        when(button1).is(LOW).then("off");
        when(button2).is(LOW).then("off");
    }
}
