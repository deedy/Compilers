// Generated from /home/dominick/workspace/Compilers/src/lexer/CubexLexer.g4 by ANTLR 4.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CubexLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTERFACE=1, CLASS=2, EXTENDS=3, SUPER=4, FUN=5, WHILE=6, IF=7, ELSE=8, 
		IN=9, RETURN=10, MODULO=11, PLUS=12, MINUS=13, LT=14, GT=15, LTE=16, GTE=17, 
		EQ=18, NE=19, AND=20, OR=21, NEGATE=22, TIMES=23, DIVIDE=24, RANGEOP=25, 
		PLUSPLUS=26, COLON=27, EQUAL=28, LPAREN=29, RPAREN=30, COMMA=31, SEMICOLON=32, 
		LBRACE=33, RBRACE=34, DOT=35, TYPE=36, NAME=37, WS=38, INT=39, BOOL=40, 
		COMMENT=41, STRING=42, SINGLELINECOMMENT=43, MULTILINECOMMENT=44;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'%'", "'+'", "'-'", "'<'", "'>'", 
		"'<='", "'>='", "'=='", "'!='", "'&'", "'|'", "'!'", "'*'", "'/'", "RANGEOP", 
		"'++'", "':'", "'='", "'('", "')'", "','", "';'", "'{'", "'}'", "'.'", 
		"TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", "STRING", "SINGLELINECOMMENT", 
		"MULTILINECOMMENT"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "MODULO", "PLUS", "MINUS", "LT", "GT", "LTE", "GTE", "EQ", 
		"NE", "AND", "OR", "NEGATE", "TIMES", "DIVIDE", "RANGEOP", "PLUSPLUS", 
		"COLON", "EQUAL", "LPAREN", "RPAREN", "COMMA", "SEMICOLON", "LBRACE", 
		"RBRACE", "DOT", "TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", "STRING", 
		"SINGLELINECOMMENT", "MULTILINECOMMENT"
	};


	public CubexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CubexLexer.g4"; }

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
		case 37: WS_action((RuleContext)_localctx, actionIndex); break;

		case 40: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 42: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
	private void SINGLELINECOMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4.\u0128\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u00c4\n\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\7%\u00dd\n%\f%\16%\u00e0"+
		"\13%\3&\3&\7&\u00e4\n&\f&\16&\u00e7\13&\3\'\6\'\u00ea\n\'\r\'\16\'\u00eb"+
		"\3\'\3\'\3(\3(\7(\u00f2\n(\f(\16(\u00f5\13(\3(\5(\u00f8\n(\3)\3)\3)\3"+
		")\3)\3)\3)\3)\3)\5)\u0103\n)\3*\3*\5*\u0107\n*\3*\3*\3+\3+\7+\u010d\n"+
		"+\f+\16+\u0110\13+\3+\3+\3,\3,\7,\u0116\n,\f,\16,\u0119\13,\3,\3,\3,\3"+
		",\3-\3-\3-\7-\u0122\n-\f-\16-\u0125\13-\3-\3-\3\u0117.\3\3\1\5\4\1\7\5"+
		"\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17"+
		"\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61"+
		"\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1"+
		"K\'\1M(\2O)\1Q*\1S+\3U,\1W-\4Y.\1\3\2\20\3C\\\6\62;C\\aac|\3c|\6\62;C"+
		"\\aac|\5\13\f\17\17\"\"\3\63;\3\62;\3\62\62\3$$\4$$``\3$$\3%%\4\f\f\17"+
		"\17\3))\u0137\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3[\3\2\2\2\5e\3\2\2\2\7k\3"+
		"\2\2\2\ts\3\2\2\2\13y\3\2\2\2\r}\3\2\2\2\17\u0083\3\2\2\2\21\u0086\3\2"+
		"\2\2\23\u008b\3\2\2\2\25\u008e\3\2\2\2\27\u0095\3\2\2\2\31\u0097\3\2\2"+
		"\2\33\u0099\3\2\2\2\35\u009b\3\2\2\2\37\u009d\3\2\2\2!\u009f\3\2\2\2#"+
		"\u00a2\3\2\2\2%\u00a5\3\2\2\2\'\u00a8\3\2\2\2)\u00ab\3\2\2\2+\u00ad\3"+
		"\2\2\2-\u00af\3\2\2\2/\u00b1\3\2\2\2\61\u00b3\3\2\2\2\63\u00c3\3\2\2\2"+
		"\65\u00c5\3\2\2\2\67\u00c8\3\2\2\29\u00ca\3\2\2\2;\u00cc\3\2\2\2=\u00ce"+
		"\3\2\2\2?\u00d0\3\2\2\2A\u00d2\3\2\2\2C\u00d4\3\2\2\2E\u00d6\3\2\2\2G"+
		"\u00d8\3\2\2\2I\u00da\3\2\2\2K\u00e1\3\2\2\2M\u00e9\3\2\2\2O\u00f7\3\2"+
		"\2\2Q\u0102\3\2\2\2S\u0106\3\2\2\2U\u010a\3\2\2\2W\u0113\3\2\2\2Y\u011e"+
		"\3\2\2\2[\\\7k\2\2\\]\7p\2\2]^\7v\2\2^_\7g\2\2_`\7t\2\2`a\7h\2\2ab\7c"+
		"\2\2bc\7e\2\2cd\7g\2\2d\4\3\2\2\2ef\7e\2\2fg\7n\2\2gh\7c\2\2hi\7u\2\2"+
		"ij\7u\2\2j\6\3\2\2\2kl\7g\2\2lm\7z\2\2mn\7v\2\2no\7g\2\2op\7p\2\2pq\7"+
		"f\2\2qr\7u\2\2r\b\3\2\2\2st\7u\2\2tu\7w\2\2uv\7r\2\2vw\7g\2\2wx\7t\2\2"+
		"x\n\3\2\2\2yz\7h\2\2z{\7w\2\2{|\7p\2\2|\f\3\2\2\2}~\7y\2\2~\177\7j\2\2"+
		"\177\u0080\7k\2\2\u0080\u0081\7n\2\2\u0081\u0082\7g\2\2\u0082\16\3\2\2"+
		"\2\u0083\u0084\7k\2\2\u0084\u0085\7h\2\2\u0085\20\3\2\2\2\u0086\u0087"+
		"\7g\2\2\u0087\u0088\7n\2\2\u0088\u0089\7u\2\2\u0089\u008a\7g\2\2\u008a"+
		"\22\3\2\2\2\u008b\u008c\7k\2\2\u008c\u008d\7p\2\2\u008d\24\3\2\2\2\u008e"+
		"\u008f\7t\2\2\u008f\u0090\7g\2\2\u0090\u0091\7v\2\2\u0091\u0092\7w\2\2"+
		"\u0092\u0093\7t\2\2\u0093\u0094\7p\2\2\u0094\26\3\2\2\2\u0095\u0096\7"+
		"\'\2\2\u0096\30\3\2\2\2\u0097\u0098\7-\2\2\u0098\32\3\2\2\2\u0099\u009a"+
		"\7/\2\2\u009a\34\3\2\2\2\u009b\u009c\7>\2\2\u009c\36\3\2\2\2\u009d\u009e"+
		"\7@\2\2\u009e \3\2\2\2\u009f\u00a0\7>\2\2\u00a0\u00a1\7?\2\2\u00a1\"\3"+
		"\2\2\2\u00a2\u00a3\7@\2\2\u00a3\u00a4\7?\2\2\u00a4$\3\2\2\2\u00a5\u00a6"+
		"\7?\2\2\u00a6\u00a7\7?\2\2\u00a7&\3\2\2\2\u00a8\u00a9\7#\2\2\u00a9\u00aa"+
		"\7?\2\2\u00aa(\3\2\2\2\u00ab\u00ac\7(\2\2\u00ac*\3\2\2\2\u00ad\u00ae\7"+
		"~\2\2\u00ae,\3\2\2\2\u00af\u00b0\7#\2\2\u00b0.\3\2\2\2\u00b1\u00b2\7,"+
		"\2\2\u00b2\60\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\62\3\2\2\2\u00b5\u00b6"+
		"\7\60\2\2\u00b6\u00c4\7\60\2\2\u00b7\u00b8\7>\2\2\u00b8\u00c4\7\60\2\2"+
		"\u00b9\u00ba\7\60\2\2\u00ba\u00c4\7>\2\2\u00bb\u00bc\7>\2\2\u00bc\u00c4"+
		"\7>\2\2\u00bd\u00be\7\60\2\2\u00be\u00bf\7\60\2\2\u00bf\u00c4\7\60\2\2"+
		"\u00c0\u00c1\7>\2\2\u00c1\u00c2\7\60\2\2\u00c2\u00c4\7\60\2\2\u00c3\u00b5"+
		"\3\2\2\2\u00c3\u00b7\3\2\2\2\u00c3\u00b9\3\2\2\2\u00c3\u00bb\3\2\2\2\u00c3"+
		"\u00bd\3\2\2\2\u00c3\u00c0\3\2\2\2\u00c4\64\3\2\2\2\u00c5\u00c6\7-\2\2"+
		"\u00c6\u00c7\7-\2\2\u00c7\66\3\2\2\2\u00c8\u00c9\7<\2\2\u00c98\3\2\2\2"+
		"\u00ca\u00cb\7?\2\2\u00cb:\3\2\2\2\u00cc\u00cd\7*\2\2\u00cd<\3\2\2\2\u00ce"+
		"\u00cf\7+\2\2\u00cf>\3\2\2\2\u00d0\u00d1\7.\2\2\u00d1@\3\2\2\2\u00d2\u00d3"+
		"\7=\2\2\u00d3B\3\2\2\2\u00d4\u00d5\7}\2\2\u00d5D\3\2\2\2\u00d6\u00d7\7"+
		"\177\2\2\u00d7F\3\2\2\2\u00d8\u00d9\7\60\2\2\u00d9H\3\2\2\2\u00da\u00de"+
		"\t\2\2\2\u00db\u00dd\t\3\2\2\u00dc\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00dfJ\3\2\2\2\u00e0\u00de\3\2\2\2"+
		"\u00e1\u00e5\t\4\2\2\u00e2\u00e4\t\5\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7"+
		"\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6L\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e8\u00ea\t\6\2\2\u00e9\u00e8\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\u00ee\b\'\2\2\u00eeN\3\2\2\2\u00ef\u00f3\t\7\2\2\u00f0\u00f2\t\b\2\2"+
		"\u00f1\u00f0\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4"+
		"\3\2\2\2\u00f4\u00f8\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f8\t\t\2\2\u00f7"+
		"\u00ef\3\2\2\2\u00f7\u00f6\3\2\2\2\u00f8P\3\2\2\2\u00f9\u00fa\7v\2\2\u00fa"+
		"\u00fb\7t\2\2\u00fb\u00fc\7w\2\2\u00fc\u0103\7g\2\2\u00fd\u00fe\7h\2\2"+
		"\u00fe\u00ff\7c\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7u\2\2\u0101\u0103"+
		"\7g\2\2\u0102\u00f9\3\2\2\2\u0102\u00fd\3\2\2\2\u0103R\3\2\2\2\u0104\u0107"+
		"\5W,\2\u0105\u0107\5Y-\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2\2\2\u0107"+
		"\u0108\3\2\2\2\u0108\u0109\b*\3\2\u0109T\3\2\2\2\u010a\u010e\t\n\2\2\u010b"+
		"\u010d\t\13\2\2\u010c\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3"+
		"\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111"+
		"\u0112\t\f\2\2\u0112V\3\2\2\2\u0113\u0117\t\r\2\2\u0114\u0116\13\2\2\2"+
		"\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2\u0117\u0118\3\2\2\2\u0117\u0115"+
		"\3\2\2\2\u0118\u011a\3\2\2\2\u0119\u0117\3\2\2\2\u011a\u011b\t\16\2\2"+
		"\u011b\u011c\3\2\2\2\u011c\u011d\b,\4\2\u011dX\3\2\2\2\u011e\u0123\7b"+
		"\2\2\u011f\u0122\5Y-\2\u0120\u0122\n\17\2\2\u0121\u011f\3\2\2\2\u0121"+
		"\u0120\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2"+
		"\2\2\u0124\u0126\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0127\7)\2\2\u0127"+
		"Z\3\2\2\2\17\2\u00c3\u00de\u00e5\u00eb\u00f3\u00f7\u0102\u0106\u010e\u0117"+
		"\u0121\u0123";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}