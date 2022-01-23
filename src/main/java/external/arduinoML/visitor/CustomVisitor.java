package external.arduinoML.visitor;

import external.antlr.ArduinoMLBaseVisitor;
import external.antlr.ArduinoMLParser;

public class CustomVisitor extends ArduinoMLBaseVisitor<Object> {

    @Override
    public Object visitProgram(ArduinoMLParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }

    @Override
    public Object visitSensor(ArduinoMLParser.SensorContext ctx) {
        return super.visitSensor(ctx);
    }

    @Override
    public Object visitActuator(ArduinoMLParser.ActuatorContext ctx) {
        return super.visitActuator(ctx);
    }

    @Override
    public Object visitInitialState(ArduinoMLParser.InitialStateContext ctx) {
        return super.visitInitialState(ctx);
    }

    @Override
    public Object visitPendingState(ArduinoMLParser.PendingStateContext ctx) {
        return super.visitPendingState(ctx);
    }

    @Override
    public Object visitDeclarations(ArduinoMLParser.DeclarationsContext ctx) {
        return super.visitDeclarations(ctx);
    }

    @Override
    public Object visitAction(ArduinoMLParser.ActionContext ctx) {
        return super.visitAction(ctx);
    }

    @Override
    public Object visitTransition(ArduinoMLParser.TransitionContext ctx) {
        return super.visitTransition(ctx);
    }

    @Override
    public Object visitTimedTransition(ArduinoMLParser.TimedTransitionContext ctx) {
        return super.visitTimedTransition(ctx);
    }
}
