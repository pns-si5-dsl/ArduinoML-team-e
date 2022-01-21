package external;

import external.grammar.ArduinoMLBaseListener;
import external.grammar.ArduinoMLParser;
import kernel.model.App;
import kernel.model.component.Sensor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ModelBuilder extends ArduinoMLBaseListener {

    private App model;

    public App build() throws Exception {
        if (model != null) return model;
        throw new Exception("Model not created!");
    }

    @Override
    public void enterMain(ArduinoMLParser.MainContext ctx) {
        model = new App();
    }

    @Override
    public void exitMain(ArduinoMLParser.MainContext ctx) {
        super.exitMain(ctx);
    }

    @Override
    public void enterSensor(ArduinoMLParser.SensorContext ctx) {
        Sensor sensor = new Sensor();
        sensor.setName(ctx.id.getText());
        super.enterSensor(ctx);
    }

    @Override
    public void exitSensor(ArduinoMLParser.SensorContext ctx) {
        super.exitSensor(ctx);
    }

    @Override
    public void enterActuator(ArduinoMLParser.ActuatorContext ctx) {
        super.enterActuator(ctx);
    }

    @Override
    public void exitActuator(ArduinoMLParser.ActuatorContext ctx) {
        super.exitActuator(ctx);
    }

    @Override
    public void enterState(ArduinoMLParser.StateContext ctx) {
        super.enterState(ctx);
    }

    @Override
    public void exitState(ArduinoMLParser.StateContext ctx) {
        super.exitState(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }
}
