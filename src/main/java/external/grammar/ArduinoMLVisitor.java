// Generated from C:/Users/ducch/IdeaProjects/ArduinoML-team-e/src/main/java/external/grammar\ArduinoML.g4 by ANTLR 4.9.2
package external.grammar;
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
	 * Visit a parse tree produced by {@link ArduinoMLParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(ArduinoMLParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitions(ArduinoMLParser.DefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(ArduinoMLParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#sensor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSensor(ArduinoMLParser.SensorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#actuator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActuator(ArduinoMLParser.ActuatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(ArduinoMLParser.StatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#state}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitState(ArduinoMLParser.StateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(ArduinoMLParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(ArduinoMLParser.TransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArduinoMLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(ArduinoMLParser.ConditionContext ctx);
}