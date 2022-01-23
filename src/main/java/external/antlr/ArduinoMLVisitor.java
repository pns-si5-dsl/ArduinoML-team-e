// Generated from /home/ludovic/Bureau/Ecole/SI-5/DSL/ArduinoML-team-e/src/main/java/external/antlr/ArduinoML.g4 by ANTLR 4.9.2
package external.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ArduinoMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ArduinoMLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ArduinoMLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sensor}
	 * labeled alternative in {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSensor(ArduinoMLParser.SensorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code actuator}
	 * labeled alternative in {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActuator(ArduinoMLParser.ActuatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initialState}
	 * labeled alternative in {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialState(ArduinoMLParser.InitialStateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pendingState}
	 * labeled alternative in {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPendingState(ArduinoMLParser.PendingStateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(ArduinoMLParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code action}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(ArduinoMLParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code transition}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(ArduinoMLParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code timedTransition}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimedTransition(ArduinoMLParser.TimedTransitionContext ctx);
}