package step1;// Generated from Micro.g4 by ANTLR 4.9.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
	public static final int
			KEYWORD = 1, OPERATOR = 2, IDENTIFIER = 3, INTLITERAL = 4, FLOATLITERAL = 5, STRINGLITERAL = 6,
			COMMENT = 7, WS = 8;
	public static final String[] ruleNames = makeRuleNames();
	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\n\u00a1\b\1\4\2\t" +
					"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2" +
					"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3" +
					"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2" +
					"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3" +
					"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2" +
					"\3\2\3\2\3\2\5\2`\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3" +
					"m\n\3\3\4\3\4\7\4q\n\4\f\4\16\4t\13\4\3\5\6\5w\n\5\r\5\16\5x\3\6\7\6|" +
					"\n\6\f\6\16\6\177\13\6\3\6\3\6\6\6\u0083\n\6\r\6\16\6\u0084\3\7\3\7\7" +
					"\7\u0089\n\7\f\7\16\7\u008c\13\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u0094\n\b" +
					"\f\b\16\b\u0097\13\b\3\b\3\b\3\t\6\t\u009c\n\t\r\t\16\t\u009d\3\t\3\t" +
					"\2\2\n\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\3\2\n\6\2,-//\61\61??\4\2>>" +
					"@@\5\2*+..==\4\2C\\c|\5\2\62;C\\c|\3\2$$\3\2\f\f\5\2\13\f\17\17\"\"\2" +
					"\u00bc\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2" +
					"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3_\3\2\2\2\5l\3\2\2\2\7n\3\2\2\2" +
					"\tv\3\2\2\2\13}\3\2\2\2\r\u0086\3\2\2\2\17\u008f\3\2\2\2\21\u009b\3\2" +
					"\2\2\23\24\7R\2\2\24\25\7T\2\2\25\26\7Q\2\2\26\27\7I\2\2\27\30\7T\2\2" +
					"\30\31\7C\2\2\31`\7O\2\2\32\33\7D\2\2\33\34\7G\2\2\34\35\7I\2\2\35\36" +
					"\7K\2\2\36`\7P\2\2\37 \7G\2\2 !\7P\2\2!`\7F\2\2\"#\7H\2\2#$\7W\2\2$%\7" +
					"P\2\2%&\7E\2\2&\'\7V\2\2\'(\7K\2\2()\7Q\2\2)`\7P\2\2*+\7T\2\2+,\7G\2\2" +
					",-\7V\2\2-.\7W\2\2./\7T\2\2/`\7P\2\2\60\61\7T\2\2\61\62\7G\2\2\62\63\7" +
					"C\2\2\63`\7F\2\2\64\65\7Y\2\2\65\66\7T\2\2\66\67\7K\2\2\678\7V\2\28`\7" +
					"G\2\29:\7K\2\2:`\7H\2\2;<\7G\2\2<=\7N\2\2=>\7U\2\2>`\7G\2\2?@\7G\2\2@" +
					"A\7P\2\2AB\7F\2\2BC\7K\2\2C`\7H\2\2DE\7H\2\2EF\7Q\2\2F`\7T\2\2GH\7G\2" +
					"\2HI\7P\2\2IJ\7F\2\2JK\7H\2\2KL\7Q\2\2L`\7T\2\2MN\7K\2\2NO\7P\2\2O`\7" +
					"V\2\2PQ\7X\2\2QR\7Q\2\2RS\7K\2\2S`\7F\2\2TU\7U\2\2UV\7V\2\2VW\7T\2\2W" +
					"X\7K\2\2XY\7P\2\2Y`\7I\2\2Z[\7H\2\2[\\\7N\2\2\\]\7Q\2\2]^\7C\2\2^`\7V" +
					"\2\2_\23\3\2\2\2_\32\3\2\2\2_\37\3\2\2\2_\"\3\2\2\2_*\3\2\2\2_\60\3\2" +
					"\2\2_\64\3\2\2\2_9\3\2\2\2_;\3\2\2\2_?\3\2\2\2_D\3\2\2\2_G\3\2\2\2_M\3" +
					"\2\2\2_P\3\2\2\2_T\3\2\2\2_Z\3\2\2\2`\4\3\2\2\2ab\7<\2\2bm\7?\2\2cm\t" +
					"\2\2\2de\7#\2\2em\7?\2\2fm\t\3\2\2gh\7>\2\2hm\7?\2\2ij\7@\2\2jm\7?\2\2" +
					"km\t\4\2\2la\3\2\2\2lc\3\2\2\2ld\3\2\2\2lf\3\2\2\2lg\3\2\2\2li\3\2\2\2" +
					"lk\3\2\2\2m\6\3\2\2\2nr\t\5\2\2oq\t\6\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2" +
					"\2rs\3\2\2\2s\b\3\2\2\2tr\3\2\2\2uw\4\62;\2vu\3\2\2\2wx\3\2\2\2xv\3\2" +
					"\2\2xy\3\2\2\2y\n\3\2\2\2z|\4\62;\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}" +
					"~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0082\7\60\2\2\u0081\u0083" +
					"\4\62;\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084" +
					"\u0085\3\2\2\2\u0085\f\3\2\2\2\u0086\u008a\7$\2\2\u0087\u0089\n\7\2\2" +
					"\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b" +
					"\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7$\2\2\u008e" +
					"\16\3\2\2\2\u008f\u0090\7/\2\2\u0090\u0091\7/\2\2\u0091\u0095\3\2\2\2" +
					"\u0092\u0094\n\b\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093" +
					"\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098" +
					"\u0099\b\b\2\2\u0099\20\3\2\2\2\u009a\u009c\t\t\2\2\u009b\u009a\3\2\2" +
					"\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f" +
					"\3\2\2\2\u009f\u00a0\b\t\2\2\u00a0\22\3\2\2\2\f\2_lrx}\u0084\u008a\u0095" +
					"\u009d\3\b\2\2";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};
	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

	static {
		RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION);
	}

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

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}

	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	private static String[] makeRuleNames() {
		return new String[]{
				"KEYWORD", "OPERATOR", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL",
				"COMMENT", "WS"
		};
	}

	private static String[] makeLiteralNames() {
		return new String[]{
		};
	}

	private static String[] makeSymbolicNames() {
		return new String[]{
				null, "KEYWORD", "OPERATOR", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL",
				"STRINGLITERAL", "COMMENT", "WS"
		};
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
	public String getGrammarFileName() {
		return "Micro.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}
}
