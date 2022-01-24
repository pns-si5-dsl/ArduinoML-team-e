package internal.demo;

import internal.interfaces.Actuator;
import internal.interfaces.Sensor;
import internal.annotations.*;

import static internal.interfaces.SensorUtils.*;
import static kernel.model.values.SIGNAL.*;

@App
public class MonApp {
    @Input(pin=1)
    Sensor button;

    @Output(pin=1, init= LOW)
    Actuator led;

    @State()
    void on(){
        led.is(HIGH);
        when(button).is(LOW).switchTo("off");
    }

    @State(initial=true)
    void off(){
        led.is(LOW);
        when(button).is(HIGH).switchTo("on");
    }
}