// Generated from /home/ludovic/Bureau/Ecole/SI-5/DSL/ArduinoML-team-e/src/main/java/external/antlr/ArduinoML.g4 by ANTLR 4.9.2
package external.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArduinoMLParser}.
 */
public interface ArduinoMLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ArduinoMLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ArduinoMLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sensor}
	 * labeled alternative in {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterSensor(ArduinoMLParser.SensorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sensor}
	 * labeled alternative in {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitSensor(ArduinoMLParser.SensorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code actuator}
	 * labeled alternative in {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterActuator(ArduinoMLParser.ActuatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code actuator}
	 * labeled alternative in {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitActuator(ArduinoMLParser.ActuatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initialState}
	 * labeled alternative in {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 */
	void enterInitialState(ArduinoMLParser.InitialStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initialState}
	 * labeled alternative in {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 */
	void exitInitialState(ArduinoMLParser.InitialStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pendingState}
	 * labeled alternative in {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 */
	void enterPendingState(ArduinoMLParser.PendingStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pendingState}
	 * labeled alternative in {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 */
	void exitPendingState(ArduinoMLParser.PendingStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(ArduinoMLParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(ArduinoMLParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code action}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterAction(ArduinoMLParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code action}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitAction(ArduinoMLParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code transition}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterTransition(ArduinoMLParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code transition}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitTransition(ArduinoMLParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code timedTransition}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterTimedTransition(ArduinoMLParser.TimedTransitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code timedTransition}
	 * labeled alternative in {@link ArduinoMLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitTimedTransition(ArduinoMLParser.TimedTransitionContext ctx);
}