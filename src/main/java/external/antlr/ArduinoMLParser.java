// Generated from /home/ludovic/Bureau/Ecole/SI-5/DSL/ArduinoML-team-e/src/main/java/external/antlr/ArduinoML.g4 by ANTLR 4.9.2
package external.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArduinoMLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, ID=13, PIN=14, SIGNAL=15, LONG=16, PIN_NUM=17, 
		LC=18, UC=19, NUM=20, NEWLINE=21, WHITE_SPACES=22, COMMENTS=23;
	public static final int
		RULE_program = 0, RULE_definition = 1, RULE_state = 2, RULE_declarations = 3, 
		RULE_declaration = 4, RULE_conditions = 5, RULE_condition = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definition", "state", "declarations", "declaration", "conditions", 
			"condition"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Sensor '", "'on'", "'Actuator '", "'initial'", "'{'", "'}'", 
			"'is'", "'then'", "'and'", "'when'", "'after'", "'ms'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "ID", "PIN", "SIGNAL", "LONG", "PIN_NUM", "LC", "UC", "NUM", "NEWLINE", 
			"WHITE_SPACES", "COMMENTS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ArduinoML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArduinoMLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ArduinoMLParser.EOF, 0); }
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public List<StateContext> state() {
			return getRuleContexts(StateContext.class);
		}
		public StateContext state(int i) {
			return getRuleContext(StateContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==T__2) {
				{
				{
				setState(14);
				definition();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==ID) {
				{
				{
				setState(20);
				state();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(26);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
	 
		public DefinitionContext() { }
		public void copyFrom(DefinitionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ActuatorContext extends DefinitionContext {
		public Token id;
		public Token pin;
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public TerminalNode PIN() { return getToken(ArduinoMLParser.PIN, 0); }
		public ActuatorContext(DefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterActuator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitActuator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitActuator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SensorContext extends DefinitionContext {
		public Token id;
		public Token pin;
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public TerminalNode PIN() { return getToken(ArduinoMLParser.PIN, 0); }
		public SensorContext(DefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterSensor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitSensor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitSensor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definition);
		try {
			setState(36);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new SensorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				match(T__0);
				setState(29);
				((SensorContext)_localctx).id = match(ID);
				setState(30);
				match(T__1);
				setState(31);
				((SensorContext)_localctx).pin = match(PIN);
				}
				break;
			case T__2:
				_localctx = new ActuatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				match(T__2);
				setState(33);
				((ActuatorContext)_localctx).id = match(ID);
				setState(34);
				match(T__1);
				setState(35);
				((ActuatorContext)_localctx).pin = match(PIN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StateContext extends ParserRuleContext {
		public StateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_state; }
	 
		public StateContext() { }
		public void copyFrom(StateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class InitialStateContext extends StateContext {
		public Token id;
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public InitialStateContext(StateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterInitialState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitInitialState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitInitialState(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PendingStateContext extends StateContext {
		public Token id;
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public PendingStateContext(StateContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterPendingState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitPendingState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitPendingState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StateContext state() throws RecognitionException {
		StateContext _localctx = new StateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_state);
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new InitialStateContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				match(T__3);
				setState(39);
				((InitialStateContext)_localctx).id = match(ID);
				setState(40);
				match(T__4);
				setState(41);
				declarations();
				setState(42);
				match(T__5);
				}
				break;
			case ID:
				_localctx = new PendingStateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				((PendingStateContext)_localctx).id = match(ID);
				setState(45);
				match(T__4);
				setState(46);
				declarations();
				setState(47);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationsContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitDeclarations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(51);
				declaration();
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ActionContext extends DeclarationContext {
		public Token id;
		public Token signal;
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public TerminalNode SIGNAL() { return getToken(ArduinoMLParser.SIGNAL, 0); }
		public ActionContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TransitionContext extends DeclarationContext {
		public Token to;
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public TransitionContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_declaration);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new ActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				((ActionContext)_localctx).id = match(ID);
				setState(57);
				match(T__6);
				setState(58);
				((ActionContext)_localctx).signal = match(SIGNAL);
				}
				break;
			case T__9:
			case T__10:
				_localctx = new TransitionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				conditions();
				setState(60);
				match(T__7);
				setState(61);
				((TransitionContext)_localctx).to = match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionsContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConditionsContext conditions() {
			return getRuleContext(ConditionsContext.class,0);
		}
		public ConditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitConditions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitConditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionsContext conditions() throws RecognitionException {
		ConditionsContext _localctx = new ConditionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			condition();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(66);
				match(T__8);
				setState(67);
				conditions();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TimedConditionContext extends ConditionContext {
		public Token d;
		public TerminalNode LONG() { return getToken(ArduinoMLParser.LONG, 0); }
		public TimedConditionContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterTimedCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitTimedCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitTimedCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SensorConditionContext extends ConditionContext {
		public Token id;
		public Token signal;
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public TerminalNode SIGNAL() { return getToken(ArduinoMLParser.SIGNAL, 0); }
		public SensorConditionContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterSensorCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitSensorCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitSensorCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_condition);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				_localctx = new SensorConditionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__9);
				setState(71);
				((SensorConditionContext)_localctx).id = match(ID);
				setState(72);
				match(T__6);
				setState(73);
				((SensorConditionContext)_localctx).signal = match(SIGNAL);
				}
				break;
			case T__10:
				_localctx = new TimedConditionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(T__10);
				setState(75);
				((TimedConditionContext)_localctx).d = match(LONG);
				setState(76);
				match(T__11);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31R\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\7\2\22\n\2\f\2\16\2\25"+
		"\13\2\3\2\7\2\30\n\2\f\2\16\2\33\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\5\3\'\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\64"+
		"\n\4\3\5\6\5\67\n\5\r\5\16\58\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6B\n\6\3\7"+
		"\3\7\3\7\5\7G\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bP\n\b\3\b\2\2\t\2\4\6"+
		"\b\n\f\16\2\2\2R\2\23\3\2\2\2\4&\3\2\2\2\6\63\3\2\2\2\b\66\3\2\2\2\nA"+
		"\3\2\2\2\fC\3\2\2\2\16O\3\2\2\2\20\22\5\4\3\2\21\20\3\2\2\2\22\25\3\2"+
		"\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\31\3\2\2\2\25\23\3\2\2\2\26\30\5\6"+
		"\4\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\34\3\2"+
		"\2\2\33\31\3\2\2\2\34\35\7\2\2\3\35\3\3\2\2\2\36\37\7\3\2\2\37 \7\17\2"+
		"\2 !\7\4\2\2!\'\7\20\2\2\"#\7\5\2\2#$\7\17\2\2$%\7\4\2\2%\'\7\20\2\2&"+
		"\36\3\2\2\2&\"\3\2\2\2\'\5\3\2\2\2()\7\6\2\2)*\7\17\2\2*+\7\7\2\2+,\5"+
		"\b\5\2,-\7\b\2\2-\64\3\2\2\2./\7\17\2\2/\60\7\7\2\2\60\61\5\b\5\2\61\62"+
		"\7\b\2\2\62\64\3\2\2\2\63(\3\2\2\2\63.\3\2\2\2\64\7\3\2\2\2\65\67\5\n"+
		"\6\2\66\65\3\2\2\2\678\3\2\2\28\66\3\2\2\289\3\2\2\29\t\3\2\2\2:;\7\17"+
		"\2\2;<\7\t\2\2<B\7\21\2\2=>\5\f\7\2>?\7\n\2\2?@\7\17\2\2@B\3\2\2\2A:\3"+
		"\2\2\2A=\3\2\2\2B\13\3\2\2\2CF\5\16\b\2DE\7\13\2\2EG\5\f\7\2FD\3\2\2\2"+
		"FG\3\2\2\2G\r\3\2\2\2HI\7\f\2\2IJ\7\17\2\2JK\7\t\2\2KP\7\21\2\2LM\7\r"+
		"\2\2MN\7\22\2\2NP\7\16\2\2OH\3\2\2\2OL\3\2\2\2P\17\3\2\2\2\n\23\31&\63"+
		"8AFO";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}