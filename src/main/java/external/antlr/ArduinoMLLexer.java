// Generated from /home/ludovic/Bureau/Ecole/SI-5/DSL/ArduinoML-team-e/src/main/java/external/antlr/ArduinoML.g4 by ANTLR 4.9.2
package external.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArduinoMLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, ID=12, PIN=13, SIGNAL=14, LONG=15, PIN_NUM=16, LC=17, 
		UC=18, NUM=19, NEWLINE=20, WHITE_SPACES=21, COMMENTS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "ID", "PIN", "SIGNAL", "LONG", "PIN_NUM", "LC", "UC", 
			"NUM", "NEWLINE", "WHITE_SPACES", "COMMENTS"
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


	public ArduinoMLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ArduinoML.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00ae\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\7\rk\n\r\f\r\16\rn\13\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17}\n\17\3\20\6\20\u0080\n\20"+
		"\r\20\16\20\u0081\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u008b\n\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\5\25\u0094\n\25\3\25\6\25\u0097\n\25"+
		"\r\25\16\25\u0098\3\25\3\25\3\26\6\26\u009e\n\26\r\26\16\26\u009f\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\7\27\u00a8\n\27\f\27\16\27\u00ab\13\27\3\27"+
		"\3\27\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\7\3\2c|\3\2C\\\3\2"+
		"\62;\4\2\f\f\17\17\4\2\13\f\"\"\2\u00b9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\67\3\2\2\2\7:\3\2\2\2\tD\3"+
		"\2\2\2\13L\3\2\2\2\rN\3\2\2\2\17P\3\2\2\2\21S\3\2\2\2\23X\3\2\2\2\25]"+
		"\3\2\2\2\27c\3\2\2\2\31f\3\2\2\2\33o\3\2\2\2\35|\3\2\2\2\37\177\3\2\2"+
		"\2!\u008a\3\2\2\2#\u008c\3\2\2\2%\u008e\3\2\2\2\'\u0090\3\2\2\2)\u0093"+
		"\3\2\2\2+\u009d\3\2\2\2-\u00a3\3\2\2\2/\60\7U\2\2\60\61\7g\2\2\61\62\7"+
		"p\2\2\62\63\7u\2\2\63\64\7q\2\2\64\65\7t\2\2\65\66\7\"\2\2\66\4\3\2\2"+
		"\2\678\7q\2\289\7p\2\29\6\3\2\2\2:;\7C\2\2;<\7e\2\2<=\7v\2\2=>\7w\2\2"+
		">?\7c\2\2?@\7v\2\2@A\7q\2\2AB\7t\2\2BC\7\"\2\2C\b\3\2\2\2DE\7k\2\2EF\7"+
		"p\2\2FG\7k\2\2GH\7v\2\2HI\7k\2\2IJ\7c\2\2JK\7n\2\2K\n\3\2\2\2LM\7}\2\2"+
		"M\f\3\2\2\2NO\7\177\2\2O\16\3\2\2\2PQ\7k\2\2QR\7u\2\2R\20\3\2\2\2ST\7"+
		"y\2\2TU\7j\2\2UV\7g\2\2VW\7p\2\2W\22\3\2\2\2XY\7v\2\2YZ\7j\2\2Z[\7g\2"+
		"\2[\\\7p\2\2\\\24\3\2\2\2]^\7c\2\2^_\7h\2\2_`\7v\2\2`a\7g\2\2ab\7t\2\2"+
		"b\26\3\2\2\2cd\7o\2\2de\7u\2\2e\30\3\2\2\2fl\5#\22\2gk\5#\22\2hk\5%\23"+
		"\2ik\5\'\24\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2"+
		"\2\2m\32\3\2\2\2nl\3\2\2\2op\7R\2\2pq\7K\2\2qr\7P\2\2rs\3\2\2\2st\5!\21"+
		"\2t\34\3\2\2\2uv\7J\2\2vw\7K\2\2wx\7I\2\2x}\7J\2\2yz\7N\2\2z{\7Q\2\2{"+
		"}\7Y\2\2|u\3\2\2\2|y\3\2\2\2}\36\3\2\2\2~\u0080\5\'\24\2\177~\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082 \3\2"+
		"\2\2\u0083\u008b\5\'\24\2\u0084\u0085\7\63\2\2\u0085\u008b\7\62\2\2\u0086"+
		"\u0087\7\63\2\2\u0087\u008b\7\63\2\2\u0088\u0089\7\63\2\2\u0089\u008b"+
		"\7\64\2\2\u008a\u0083\3\2\2\2\u008a\u0084\3\2\2\2\u008a\u0086\3\2\2\2"+
		"\u008a\u0088\3\2\2\2\u008b\"\3\2\2\2\u008c\u008d\t\2\2\2\u008d$\3\2\2"+
		"\2\u008e\u008f\t\3\2\2\u008f&\3\2\2\2\u0090\u0091\t\4\2\2\u0091(\3\2\2"+
		"\2\u0092\u0094\7\17\2\2\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0096\3\2\2\2\u0095\u0097\t\5\2\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2"+
		"\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009b\b\25\2\2\u009b*\3\2\2\2\u009c\u009e\t\6\2\2\u009d\u009c\3\2\2\2"+
		"\u009e\u009f\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\u00a2\b\26\2\2\u00a2,\3\2\2\2\u00a3\u00a4\7\61\2\2\u00a4"+
		"\u00a5\7\61\2\2\u00a5\u00a9\3\2\2\2\u00a6\u00a8\n\5\2\2\u00a7\u00a6\3"+
		"\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\b\27\2\2\u00ad.\3\2\2\2"+
		"\f\2jl|\u0081\u008a\u0093\u0098\u009f\u00a9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}