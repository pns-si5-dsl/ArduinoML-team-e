package embedded.examples;

import embedded.annotations.ArduinoML;
import embedded.annotations.Initial;
import embedded.annotations.Input;
import embedded.annotations.Output;
import embedded.builders.EmbeddedApplication;
import kernel.model.component.Actuator;
import kernel.model.component.Sensor;

@ArduinoML( name = "my_blink_application" )
public class BlinkApplication extends EmbeddedApplication {
    ////  INPUT & OUTPUT DEFINITION  ////
    @Input(pin = 8)
    Sensor button;

    @Output(pin = 12)
    Actuator led;


    ////  STATES DEFINITION  ////
    @Initial
    void stopped() {
        /// action definition ///
        set(led).to(LOW);

        /// transition definition ///
        when(button).is(HIGH).then("blink");
    }

    void blink() {
        /// action definition ///
        set(led).to(HIGH);

        /// transition definition ///
        when(button).is(HIGH).then("stopped");

        /// time definition ///
        after(100).then("blink2");
    }

    void blink2() {
        /// action definition ///
        set(led).to(LOW);

        /// transition definition ///
        when(button).is(HIGH).then("stopped");

        /// time definition ///
        after(100).then("blink");
    }
}
