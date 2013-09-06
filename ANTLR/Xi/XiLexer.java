// Generated from XiLexer.g4 by ANTLR 4.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XiLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, BOOL=2, INT=3, RETURN=4, WHILE=5, IF=6, ELSE=7, LENGTH=8, BREAK=9, 
		TRUE=10, FALSE=11, ID=12, INTEGER=13, LBRACKET=14, RBRACKET=15, COLON=16, 
		EQUAL=17, LPAREN=18, RPAREN=19, COMMA=20, SEMICOLON=21, LBRACE=22, RBRACE=23, 
		STAR=24, SLASH=25, PERCENT=26, PLUS=27, DASH=28, LANGLE=29, RANGLE=30, 
		BANG=31, AMPERSAND=32, PIPE=33, WS=34, COMMENT=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"STRING", "'bool'", "'int'", "'return'", "'while'", "'if'", "'else'", 
		"'length'", "'break'", "'true'", "'false'", "ID", "INTEGER", "'['", "']'", 
		"':'", "'='", "'('", "')'", "','", "';'", "'{'", "'}'", "'*'", "'/'", 
		"'%'", "'+'", "'-'", "'<'", "'>'", "'!'", "'&'", "'|'", "WS", "COMMENT"
	};
	public static final String[] ruleNames = {
		"STRING", "BOOL", "INT", "RETURN", "WHILE", "IF", "ELSE", "LENGTH", "BREAK", 
		"TRUE", "FALSE", "ID", "INTEGER", "LBRACKET", "RBRACKET", "COLON", "EQUAL", 
		"LPAREN", "RPAREN", "COMMA", "SEMICOLON", "LBRACE", "RBRACE", "STAR", 
		"SLASH", "PERCENT", "PLUS", "DASH", "LANGLE", "RANGLE", "BANG", "AMPERSAND", 
		"PIPE", "WS", "COMMENT"
	};


	public XiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XiLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 33: WS_action((RuleContext)_localctx, actionIndex); break;

		case 34: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4%\u00d0\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\7\2L\n\2\f\2\16"+
		"\2O\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\7\r\u008b\n\r\f\r\16\r\u008e\13\r"+
		"\3\16\6\16\u0091\n\16\r\16\16\16\u0092\3\17\3\17\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3\"\3\"\3#\6#\u00be\n#\r#\16#\u00bf\3#\3#\3$\3$\3$\3$\7$\u00c8"+
		"\n$\f$\16$\u00cb\13$\3$\3$\3$\3$\4M\u00c9%\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1"+
		"\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33"+
		"\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\2G%\3\3\2\7\4C\\c|\7"+
		"))\62;C\\aac|\3\62;\5\13\f\17\17\"\"\4\f\f\17\17\u00d4\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\3I\3\2\2\2\5R"+
		"\3\2\2\2\7W\3\2\2\2\t[\3\2\2\2\13b\3\2\2\2\rh\3\2\2\2\17k\3\2\2\2\21p"+
		"\3\2\2\2\23w\3\2\2\2\25}\3\2\2\2\27\u0082\3\2\2\2\31\u0088\3\2\2\2\33"+
		"\u0090\3\2\2\2\35\u0094\3\2\2\2\37\u0096\3\2\2\2!\u0098\3\2\2\2#\u009a"+
		"\3\2\2\2%\u009c\3\2\2\2\'\u009e\3\2\2\2)\u00a0\3\2\2\2+\u00a2\3\2\2\2"+
		"-\u00a4\3\2\2\2/\u00a6\3\2\2\2\61\u00a8\3\2\2\2\63\u00aa\3\2\2\2\65\u00ac"+
		"\3\2\2\2\67\u00ae\3\2\2\29\u00b0\3\2\2\2;\u00b2\3\2\2\2=\u00b4\3\2\2\2"+
		"?\u00b6\3\2\2\2A\u00b8\3\2\2\2C\u00ba\3\2\2\2E\u00bd\3\2\2\2G\u00c3\3"+
		"\2\2\2IM\7$\2\2JL\13\2\2\2KJ\3\2\2\2LO\3\2\2\2MN\3\2\2\2MK\3\2\2\2NP\3"+
		"\2\2\2OM\3\2\2\2PQ\7$\2\2Q\4\3\2\2\2RS\7d\2\2ST\7q\2\2TU\7q\2\2UV\7n\2"+
		"\2V\6\3\2\2\2WX\7k\2\2XY\7p\2\2YZ\7v\2\2Z\b\3\2\2\2[\\\7t\2\2\\]\7g\2"+
		"\2]^\7v\2\2^_\7w\2\2_`\7t\2\2`a\7p\2\2a\n\3\2\2\2bc\7y\2\2cd\7j\2\2de"+
		"\7k\2\2ef\7n\2\2fg\7g\2\2g\f\3\2\2\2hi\7k\2\2ij\7h\2\2j\16\3\2\2\2kl\7"+
		"g\2\2lm\7n\2\2mn\7u\2\2no\7g\2\2o\20\3\2\2\2pq\7n\2\2qr\7g\2\2rs\7p\2"+
		"\2st\7i\2\2tu\7v\2\2uv\7j\2\2v\22\3\2\2\2wx\7d\2\2xy\7t\2\2yz\7g\2\2z"+
		"{\7c\2\2{|\7m\2\2|\24\3\2\2\2}~\7v\2\2~\177\7t\2\2\177\u0080\7w\2\2\u0080"+
		"\u0081\7g\2\2\u0081\26\3\2\2\2\u0082\u0083\7h\2\2\u0083\u0084\7c\2\2\u0084"+
		"\u0085\7n\2\2\u0085\u0086\7u\2\2\u0086\u0087\7g\2\2\u0087\30\3\2\2\2\u0088"+
		"\u008c\t\2\2\2\u0089\u008b\t\3\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\32\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008f\u0091\t\4\2\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\34\3\2\2\2\u0094\u0095\7]\2\2"+
		"\u0095\36\3\2\2\2\u0096\u0097\7_\2\2\u0097 \3\2\2\2\u0098\u0099\7<\2\2"+
		"\u0099\"\3\2\2\2\u009a\u009b\7?\2\2\u009b$\3\2\2\2\u009c\u009d\7*\2\2"+
		"\u009d&\3\2\2\2\u009e\u009f\7+\2\2\u009f(\3\2\2\2\u00a0\u00a1\7.\2\2\u00a1"+
		"*\3\2\2\2\u00a2\u00a3\7=\2\2\u00a3,\3\2\2\2\u00a4\u00a5\7}\2\2\u00a5."+
		"\3\2\2\2\u00a6\u00a7\7\177\2\2\u00a7\60\3\2\2\2\u00a8\u00a9\7,\2\2\u00a9"+
		"\62\3\2\2\2\u00aa\u00ab\7\61\2\2\u00ab\64\3\2\2\2\u00ac\u00ad\7\'\2\2"+
		"\u00ad\66\3\2\2\2\u00ae\u00af\7-\2\2\u00af8\3\2\2\2\u00b0\u00b1\7/\2\2"+
		"\u00b1:\3\2\2\2\u00b2\u00b3\7>\2\2\u00b3<\3\2\2\2\u00b4\u00b5\7@\2\2\u00b5"+
		">\3\2\2\2\u00b6\u00b7\7#\2\2\u00b7@\3\2\2\2\u00b8\u00b9\7(\2\2\u00b9B"+
		"\3\2\2\2\u00ba\u00bb\7~\2\2\u00bbD\3\2\2\2\u00bc\u00be\t\5\2\2\u00bd\u00bc"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c2\b#\2\2\u00c2F\3\2\2\2\u00c3\u00c4\7\61\2\2"+
		"\u00c4\u00c5\7\61\2\2\u00c5\u00c9\3\2\2\2\u00c6\u00c8\13\2\2\2\u00c7\u00c6"+
		"\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00ca\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca"+
		"\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\t\6\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\u00cf\b$\3\2\u00cfH\3\2\2\2\b\2M\u008c\u0092\u00bf\u00c9";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}