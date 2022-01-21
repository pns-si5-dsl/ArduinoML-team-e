// Generated from C:/Users/ducch/IdeaProjects/ArduinoML-team-e/src/main/java/external/grammar\ArduinoML.g4 by ANTLR 4.9.2
package external.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArduinoMLParser}.
 */
public interface ArduinoMLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(ArduinoMLParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(ArduinoMLParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#definitions}.
	 * @param ctx the parse tree
	 */
	void enterDefinitions(ArduinoMLParser.DefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#definitions}.
	 * @param ctx the parse tree
	 */
	void exitDefinitions(ArduinoMLParser.DefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(ArduinoMLParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(ArduinoMLParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#sensor}.
	 * @param ctx the parse tree
	 */
	void enterSensor(ArduinoMLParser.SensorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#sensor}.
	 * @param ctx the parse tree
	 */
	void exitSensor(ArduinoMLParser.SensorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#actuator}.
	 * @param ctx the parse tree
	 */
	void enterActuator(ArduinoMLParser.ActuatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#actuator}.
	 * @param ctx the parse tree
	 */
	void exitActuator(ArduinoMLParser.ActuatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#states}.
	 * @param ctx the parse tree
	 */
	void enterStates(ArduinoMLParser.StatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#states}.
	 * @param ctx the parse tree
	 */
	void exitStates(ArduinoMLParser.StatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 */
	void enterState(ArduinoMLParser.StateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 */
	void exitState(ArduinoMLParser.StateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(ArduinoMLParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(ArduinoMLParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(ArduinoMLParser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(ArduinoMLParser.TransitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArduinoMLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ArduinoMLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArduinoMLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ArduinoMLParser.ConditionContext ctx);
}