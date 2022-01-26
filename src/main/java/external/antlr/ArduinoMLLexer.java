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
		T__9=10, T__10=11, T__11=12, ID=13, PIN=14, SIGNAL=15, LONG=16, PIN_NUM=17, 
		LC=18, UC=19, NUM=20, NEWLINE=21, WHITE_SPACES=22, COMMENTS=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "ID", "PIN", "SIGNAL", "LONG", "PIN_NUM", "LC", 
			"UC", "NUM", "NEWLINE", "WHITE_SPACES", "COMMENTS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00b4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16q\n\16\f\16\16\16"+
		"t\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\5\20\u0083\n\20\3\21\6\21\u0086\n\21\r\21\16\21\u0087\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\5\22\u0091\n\22\3\23\3\23\3\24\3\24\3\25\3\25"+
		"\3\26\5\26\u009a\n\26\3\26\6\26\u009d\n\26\r\26\16\26\u009e\3\26\3\26"+
		"\3\27\6\27\u00a4\n\27\r\27\16\27\u00a5\3\27\3\27\3\30\3\30\3\30\3\30\7"+
		"\30\u00ae\n\30\f\30\16\30\u00b1\13\30\3\30\3\30\2\2\31\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\3\2\7\3\2c|\3\2C\\\3\2\62;\4\2\f\f\17\17\4\2\13"+
		"\f\"\"\2\u00bf\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\3\61\3\2\2\2\59\3\2\2\2\7<\3\2\2\2\tF\3\2\2\2\13N\3\2"+
		"\2\2\rP\3\2\2\2\17R\3\2\2\2\21U\3\2\2\2\23Z\3\2\2\2\25^\3\2\2\2\27c\3"+
		"\2\2\2\31i\3\2\2\2\33l\3\2\2\2\35u\3\2\2\2\37\u0082\3\2\2\2!\u0085\3\2"+
		"\2\2#\u0090\3\2\2\2%\u0092\3\2\2\2\'\u0094\3\2\2\2)\u0096\3\2\2\2+\u0099"+
		"\3\2\2\2-\u00a3\3\2\2\2/\u00a9\3\2\2\2\61\62\7U\2\2\62\63\7g\2\2\63\64"+
		"\7p\2\2\64\65\7u\2\2\65\66\7q\2\2\66\67\7t\2\2\678\7\"\2\28\4\3\2\2\2"+
		"9:\7q\2\2:;\7p\2\2;\6\3\2\2\2<=\7C\2\2=>\7e\2\2>?\7v\2\2?@\7w\2\2@A\7"+
		"c\2\2AB\7v\2\2BC\7q\2\2CD\7t\2\2DE\7\"\2\2E\b\3\2\2\2FG\7k\2\2GH\7p\2"+
		"\2HI\7k\2\2IJ\7v\2\2JK\7k\2\2KL\7c\2\2LM\7n\2\2M\n\3\2\2\2NO\7}\2\2O\f"+
		"\3\2\2\2PQ\7\177\2\2Q\16\3\2\2\2RS\7k\2\2ST\7u\2\2T\20\3\2\2\2UV\7v\2"+
		"\2VW\7j\2\2WX\7g\2\2XY\7p\2\2Y\22\3\2\2\2Z[\7c\2\2[\\\7p\2\2\\]\7f\2\2"+
		"]\24\3\2\2\2^_\7y\2\2_`\7j\2\2`a\7g\2\2ab\7p\2\2b\26\3\2\2\2cd\7c\2\2"+
		"de\7h\2\2ef\7v\2\2fg\7g\2\2gh\7t\2\2h\30\3\2\2\2ij\7o\2\2jk\7u\2\2k\32"+
		"\3\2\2\2lr\5%\23\2mq\5%\23\2nq\5\'\24\2oq\5)\25\2pm\3\2\2\2pn\3\2\2\2"+
		"po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\34\3\2\2\2tr\3\2\2\2uv\7R\2"+
		"\2vw\7K\2\2wx\7P\2\2xy\3\2\2\2yz\5#\22\2z\36\3\2\2\2{|\7J\2\2|}\7K\2\2"+
		"}~\7I\2\2~\u0083\7J\2\2\177\u0080\7N\2\2\u0080\u0081\7Q\2\2\u0081\u0083"+
		"\7Y\2\2\u0082{\3\2\2\2\u0082\177\3\2\2\2\u0083 \3\2\2\2\u0084\u0086\5"+
		")\25\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\"\3\2\2\2\u0089\u0091\5)\25\2\u008a\u008b\7\63\2"+
		"\2\u008b\u0091\7\62\2\2\u008c\u008d\7\63\2\2\u008d\u0091\7\63\2\2\u008e"+
		"\u008f\7\63\2\2\u008f\u0091\7\64\2\2\u0090\u0089\3\2\2\2\u0090\u008a\3"+
		"\2\2\2\u0090\u008c\3\2\2\2\u0090\u008e\3\2\2\2\u0091$\3\2\2\2\u0092\u0093"+
		"\t\2\2\2\u0093&\3\2\2\2\u0094\u0095\t\3\2\2\u0095(\3\2\2\2\u0096\u0097"+
		"\t\4\2\2\u0097*\3\2\2\2\u0098\u009a\7\17\2\2\u0099\u0098\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u009d\t\5\2\2\u009c\u009b\3\2"+
		"\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a0\3\2\2\2\u00a0\u00a1\b\26\2\2\u00a1,\3\2\2\2\u00a2\u00a4\t\6\2\2"+
		"\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\27\2\2\u00a8.\3\2\2\2\u00a9"+
		"\u00aa\7\61\2\2\u00aa\u00ab\7\61\2\2\u00ab\u00af\3\2\2\2\u00ac\u00ae\n"+
		"\5\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\b\30"+
		"\2\2\u00b3\60\3\2\2\2\f\2pr\u0082\u0087\u0090\u0099\u009e\u00a5\u00af"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}