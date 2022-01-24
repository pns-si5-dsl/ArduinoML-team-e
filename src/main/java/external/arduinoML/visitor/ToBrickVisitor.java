package external.arduinoML.visitor;

import external.antlr.ArduinoMLBaseVisitor;
import external.antlr.ArduinoMLParser;
import kernel.model.component.Actuator;
import kernel.model.component.Brick;
import kernel.model.component.Sensor;

public class ToBrickVisitor extends ArduinoMLBaseVisitor<Brick> {

    @Override
    public Brick visitSensor(ArduinoMLParser.SensorContext ctx) {
        Sensor sensor = new Sensor();
        sensor.setName(ctx.id.getText());
        sensor.setName(ctx.pin.getText().substring(3));
        return sensor;
    }

    @Override
    public Brick visitActuator(ArduinoMLParser.ActuatorContext ctx) {
        Actuator actuator = new Actuator();
        actuator.setName(ctx.id.getText());
        actuator.setName(ctx.pin.getText().substring(3));
        return actuator;
    }
}
