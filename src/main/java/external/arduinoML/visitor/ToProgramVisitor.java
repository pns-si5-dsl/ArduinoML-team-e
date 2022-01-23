package external.arduinoML.visitor;

import external.antlr.ArduinoMLBaseVisitor;
import external.antlr.ArduinoMLParser;
import kernel.model.App;

public class ToProgramVisitor extends ArduinoMLBaseVisitor<App> {

    @Override
    public App visitProgram(ArduinoMLParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }
}
