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
		T__9=10, T__10=11, ID=12, PIN=13, SIGNAL=14, LONG=15, PIN_NUM=16, LC=17, 
		UC=18, NUM=19, NEWLINE=20, WHITE_SPACES=21, COMMENTS=22;
	public static final int
		RULE_program = 0, RULE_definition = 1, RULE_state = 2, RULE_declarations = 3, 
		RULE_declaration = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "definition", "state", "declarations", "declaration"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Sensor '", "'on'", "'Actuator '", "'initial'", "'{'", "'}'", 
			"'is'", "'when'", "'then'", "'after'", "'ms'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"ID", "PIN", "SIGNAL", "LONG", "PIN_NUM", "LC", "UC", "NUM", "NEWLINE", 
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
			setState(14);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << ID))) != 0)) {
				{
				setState(12);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case T__2:
					{
					setState(10);
					definition();
					}
					break;
				case T__3:
				case ID:
					{
					setState(11);
					state();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(16);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(17);
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
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new SensorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				match(T__0);
				setState(20);
				((SensorContext)_localctx).id = match(ID);
				setState(21);
				match(T__1);
				setState(22);
				((SensorContext)_localctx).pin = match(PIN);
				}
				break;
			case T__2:
				_localctx = new ActuatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(23);
				match(T__2);
				setState(24);
				((ActuatorContext)_localctx).id = match(ID);
				setState(25);
				match(T__1);
				setState(26);
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
			setState(40);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new InitialStateContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				match(T__3);
				setState(30);
				((InitialStateContext)_localctx).id = match(ID);
				setState(31);
				match(T__4);
				setState(32);
				declarations();
				setState(33);
				match(T__5);
				}
				break;
			case ID:
				_localctx = new PendingStateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				((PendingStateContext)_localctx).id = match(ID);
				setState(36);
				match(T__4);
				setState(37);
				declarations();
				setState(38);
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
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				declaration();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__9) | (1L << ID))) != 0) );
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
	public static class TimedTransitionContext extends DeclarationContext {
		public Token d;
		public Token to;
		public TerminalNode LONG() { return getToken(ArduinoMLParser.LONG, 0); }
		public TerminalNode ID() { return getToken(ArduinoMLParser.ID, 0); }
		public TimedTransitionContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).enterTimedTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArduinoMLListener ) ((ArduinoMLListener)listener).exitTimedTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArduinoMLVisitor ) return ((ArduinoMLVisitor<? extends T>)visitor).visitTimedTransition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TransitionContext extends DeclarationContext {
		public Token id;
		public Token signal;
		public Token to;
		public List<TerminalNode> ID() { return getTokens(ArduinoMLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ArduinoMLParser.ID, i);
		}
		public TerminalNode SIGNAL() { return getToken(ArduinoMLParser.SIGNAL, 0); }
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
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new ActionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				((ActionContext)_localctx).id = match(ID);
				setState(48);
				match(T__6);
				setState(49);
				((ActionContext)_localctx).signal = match(SIGNAL);
				}
				break;
			case T__7:
				_localctx = new TransitionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(T__7);
				setState(51);
				((TransitionContext)_localctx).id = match(ID);
				setState(52);
				match(T__6);
				setState(53);
				((TransitionContext)_localctx).signal = match(SIGNAL);
				setState(54);
				match(T__8);
				setState(55);
				((TransitionContext)_localctx).to = match(ID);
				}
				break;
			case T__9:
				_localctx = new TimedTransitionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(T__9);
				setState(57);
				((TimedTransitionContext)_localctx).d = match(LONG);
				setState(58);
				match(T__10);
				setState(59);
				match(T__8);
				setState(60);
				((TimedTransitionContext)_localctx).to = match(ID);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30B\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\7\2\17\n\2\f\2\16\2\22\13\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\36\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4+\n\4\3\5\6\5.\n\5\r\5\16\5/\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6@\n\6\3\6\2\2\7\2\4\6\b\n\2\2"+
		"\2C\2\20\3\2\2\2\4\35\3\2\2\2\6*\3\2\2\2\b-\3\2\2\2\n?\3\2\2\2\f\17\5"+
		"\4\3\2\r\17\5\6\4\2\16\f\3\2\2\2\16\r\3\2\2\2\17\22\3\2\2\2\20\16\3\2"+
		"\2\2\20\21\3\2\2\2\21\23\3\2\2\2\22\20\3\2\2\2\23\24\7\2\2\3\24\3\3\2"+
		"\2\2\25\26\7\3\2\2\26\27\7\16\2\2\27\30\7\4\2\2\30\36\7\17\2\2\31\32\7"+
		"\5\2\2\32\33\7\16\2\2\33\34\7\4\2\2\34\36\7\17\2\2\35\25\3\2\2\2\35\31"+
		"\3\2\2\2\36\5\3\2\2\2\37 \7\6\2\2 !\7\16\2\2!\"\7\7\2\2\"#\5\b\5\2#$\7"+
		"\b\2\2$+\3\2\2\2%&\7\16\2\2&\'\7\7\2\2\'(\5\b\5\2()\7\b\2\2)+\3\2\2\2"+
		"*\37\3\2\2\2*%\3\2\2\2+\7\3\2\2\2,.\5\n\6\2-,\3\2\2\2./\3\2\2\2/-\3\2"+
		"\2\2/\60\3\2\2\2\60\t\3\2\2\2\61\62\7\16\2\2\62\63\7\t\2\2\63@\7\20\2"+
		"\2\64\65\7\n\2\2\65\66\7\16\2\2\66\67\7\t\2\2\678\7\20\2\289\7\13\2\2"+
		"9@\7\16\2\2:;\7\f\2\2;<\7\21\2\2<=\7\r\2\2=>\7\13\2\2>@\7\16\2\2?\61\3"+
		"\2\2\2?\64\3\2\2\2?:\3\2\2\2@\13\3\2\2\2\b\16\20\35*/?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}