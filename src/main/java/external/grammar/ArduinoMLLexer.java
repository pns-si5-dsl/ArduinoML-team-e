// Generated from C:/Users/ducch/IdeaProjects/ArduinoML-team-e/src/main/java/external/grammar\ArduinoML.g4 by ANTLR 4.9.2
package external.grammar;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, IDENTIFIER=9, 
		PIN=10, STATE=11, LOWERCASE=12, UPPERCASE=13, NUMBER=14, NEWLINE=15, WHITE_SPACES=16, 
		COMMENTS=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "IDENTIFIER", 
			"PIN", "STATE", "LOWERCASE", "UPPERCASE", "NUMBER", "NEWLINE", "WHITE_SPACES", 
			"COMMENTS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Sensor '", "'='", "'Actuator '", "'{'", "'}'", "'is'", "'when'", 
			"'then'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "IDENTIFIER", "PIN", 
			"STATE", "LOWERCASE", "UPPERCASE", "NUMBER", "NEWLINE", "WHITE_SPACES", 
			"COMMENTS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23\u0084\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\6\nO\n\n\r\n\16\nP\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f^\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\20\5\20g\n\20\3\20\3\20\6\20k\n\20\r\20\16\20l\3\20\3\20\3\21\6\21r\n"+
		"\21\r\21\16\21s\3\21\3\21\3\22\3\22\3\22\5\22{\n\22\3\22\7\22~\n\22\f"+
		"\22\16\22\u0081\13\22\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\7\3\2c|\3\2C\\"+
		"\3\2\62;\4\2\13\13\"\"\4\2\f\f\17\17\2\u008d\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5-\3\2\2\2"+
		"\7/\3\2\2\2\t9\3\2\2\2\13;\3\2\2\2\r=\3\2\2\2\17@\3\2\2\2\21E\3\2\2\2"+
		"\23J\3\2\2\2\25R\3\2\2\2\27]\3\2\2\2\31_\3\2\2\2\33a\3\2\2\2\35c\3\2\2"+
		"\2\37j\3\2\2\2!q\3\2\2\2#z\3\2\2\2%&\7U\2\2&\'\7g\2\2\'(\7p\2\2()\7u\2"+
		"\2)*\7q\2\2*+\7t\2\2+,\7\"\2\2,\4\3\2\2\2-.\7?\2\2.\6\3\2\2\2/\60\7C\2"+
		"\2\60\61\7e\2\2\61\62\7v\2\2\62\63\7w\2\2\63\64\7c\2\2\64\65\7v\2\2\65"+
		"\66\7q\2\2\66\67\7t\2\2\678\7\"\2\28\b\3\2\2\29:\7}\2\2:\n\3\2\2\2;<\7"+
		"\177\2\2<\f\3\2\2\2=>\7k\2\2>?\7u\2\2?\16\3\2\2\2@A\7y\2\2AB\7j\2\2BC"+
		"\7g\2\2CD\7p\2\2D\20\3\2\2\2EF\7v\2\2FG\7j\2\2GH\7g\2\2HI\7p\2\2I\22\3"+
		"\2\2\2JN\5\31\r\2KO\5\31\r\2LO\5\33\16\2MO\5\35\17\2NK\3\2\2\2NL\3\2\2"+
		"\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\24\3\2\2\2RS\7R\2\2ST\7K\2"+
		"\2TU\7P\2\2UV\3\2\2\2VW\5\35\17\2W\26\3\2\2\2XY\7Q\2\2Y^\7P\2\2Z[\7Q\2"+
		"\2[\\\7H\2\2\\^\7H\2\2]X\3\2\2\2]Z\3\2\2\2^\30\3\2\2\2_`\t\2\2\2`\32\3"+
		"\2\2\2ab\t\3\2\2b\34\3\2\2\2cd\t\4\2\2d\36\3\2\2\2eg\7\17\2\2fe\3\2\2"+
		"\2fg\3\2\2\2gh\3\2\2\2hk\7\f\2\2ik\7\17\2\2jf\3\2\2\2ji\3\2\2\2kl\3\2"+
		"\2\2lj\3\2\2\2lm\3\2\2\2mn\3\2\2\2no\b\20\2\2o \3\2\2\2pr\t\5\2\2qp\3"+
		"\2\2\2rs\3\2\2\2sq\3\2\2\2st\3\2\2\2tu\3\2\2\2uv\b\21\2\2v\"\3\2\2\2w"+
		"x\7\61\2\2x{\7\61\2\2y{\7%\2\2zw\3\2\2\2zy\3\2\2\2{\177\3\2\2\2|~\n\6"+
		"\2\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\b\22\2\2\u0083$\3\2\2\2\f\2NP]"+
		"fjlsz\177\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}