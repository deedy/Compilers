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
		IN=9, RETURN=10, MINUS=11, NEGATE=12, TIMES=13, DIVIDE=14, MODULO=15, 
		PLUS=16, RANGEOP=17, PLUSPLUS=18, LT=19, GT=20, LTE=21, GTE=22, EQ=23, 
		NE=24, AND=25, OR=26, ASSIGN=27, LPAREN=28, RPAREN=29, COMMA=30, COLON=31, 
		EQUAL=32, SEMICOLON=33, LBRACE=34, RBRACE=35, DOT=36, LSQUARE=37, RSQUARE=38, 
		TYPE=39, NAME=40, WS=41, INT=42, BOOL=43, COMMENT=44, STRING=45, SINGLELINECOMMENT=46, 
		MULTILINECOMMENT=47;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'-'", "'!'", "'*'", "'/'", "'%'", 
		"'+'", "RANGEOP", "'++'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", 
		"'&'", "'|'", "':='", "'('", "')'", "','", "':'", "'='", "';'", "'{'", 
		"'}'", "'.'", "'['", "']'", "TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", 
		"STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "MINUS", "NEGATE", "TIMES", "DIVIDE", "MODULO", "PLUS", 
		"RANGEOP", "PLUSPLUS", "LT", "GT", "LTE", "GTE", "EQ", "NE", "AND", "OR", 
		"ASSIGN", "LPAREN", "RPAREN", "COMMA", "COLON", "EQUAL", "SEMICOLON", 
		"LBRACE", "RBRACE", "DOT", "LSQUARE", "RSQUARE", "TYPE", "NAME", "WS", 
		"INT", "BOOL", "COMMENT", "STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT"
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
		case 40: WS_action((RuleContext)_localctx, actionIndex); break;

		case 43: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 45: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\61\u0135\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u00b6\n\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\7(\u00ea\n(\f(\16(\u00ed\13(\3)\3)\7)\u00f1\n"+
		")\f)\16)\u00f4\13)\3*\6*\u00f7\n*\r*\16*\u00f8\3*\3*\3+\3+\7+\u00ff\n"+
		"+\f+\16+\u0102\13+\3+\5+\u0105\n+\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u0110"+
		"\n,\3-\3-\5-\u0114\n-\3-\3-\3.\3.\7.\u011a\n.\f.\16.\u011d\13.\3.\3.\3"+
		"/\3/\7/\u0123\n/\f/\16/\u0126\13/\3/\3/\3/\3/\3\60\3\60\3\60\7\60\u012f"+
		"\n\60\f\60\16\60\u0132\13\60\3\60\3\60\3\u0124\61\3\3\1\5\4\1\7\5\1\t"+
		"\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1"+
		"\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32"+
		"\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'"+
		"\1M(\1O)\1Q*\1S+\2U,\1W-\1Y.\3[/\1]\60\4_\61\1\3\2\16\3C\\\6\62;C\\aa"+
		"c|\3c|\6\62;C\\aac|\5\13\f\17\17\"\"\3\63;\3\62;\3\62\62\3$$\3%%\4\f\f"+
		"\17\17\4))bb\u0144\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\3a\3\2\2\2\5k\3\2\2\2\7q\3\2\2\2\ty\3\2\2\2\13\177\3\2\2\2"+
		"\r\u0083\3\2\2\2\17\u0089\3\2\2\2\21\u008c\3\2\2\2\23\u0091\3\2\2\2\25"+
		"\u0094\3\2\2\2\27\u009b\3\2\2\2\31\u009d\3\2\2\2\33\u009f\3\2\2\2\35\u00a1"+
		"\3\2\2\2\37\u00a3\3\2\2\2!\u00a5\3\2\2\2#\u00b5\3\2\2\2%\u00b7\3\2\2\2"+
		"\'\u00ba\3\2\2\2)\u00bc\3\2\2\2+\u00be\3\2\2\2-\u00c1\3\2\2\2/\u00c4\3"+
		"\2\2\2\61\u00c7\3\2\2\2\63\u00ca\3\2\2\2\65\u00cc\3\2\2\2\67\u00ce\3\2"+
		"\2\29\u00d1\3\2\2\2;\u00d3\3\2\2\2=\u00d5\3\2\2\2?\u00d7\3\2\2\2A\u00d9"+
		"\3\2\2\2C\u00db\3\2\2\2E\u00dd\3\2\2\2G\u00df\3\2\2\2I\u00e1\3\2\2\2K"+
		"\u00e3\3\2\2\2M\u00e5\3\2\2\2O\u00e7\3\2\2\2Q\u00ee\3\2\2\2S\u00f6\3\2"+
		"\2\2U\u0104\3\2\2\2W\u010f\3\2\2\2Y\u0113\3\2\2\2[\u0117\3\2\2\2]\u0120"+
		"\3\2\2\2_\u012b\3\2\2\2ab\7k\2\2bc\7p\2\2cd\7v\2\2de\7g\2\2ef\7t\2\2f"+
		"g\7h\2\2gh\7c\2\2hi\7e\2\2ij\7g\2\2j\4\3\2\2\2kl\7e\2\2lm\7n\2\2mn\7c"+
		"\2\2no\7u\2\2op\7u\2\2p\6\3\2\2\2qr\7g\2\2rs\7z\2\2st\7v\2\2tu\7g\2\2"+
		"uv\7p\2\2vw\7f\2\2wx\7u\2\2x\b\3\2\2\2yz\7u\2\2z{\7w\2\2{|\7r\2\2|}\7"+
		"g\2\2}~\7t\2\2~\n\3\2\2\2\177\u0080\7h\2\2\u0080\u0081\7w\2\2\u0081\u0082"+
		"\7p\2\2\u0082\f\3\2\2\2\u0083\u0084\7y\2\2\u0084\u0085\7j\2\2\u0085\u0086"+
		"\7k\2\2\u0086\u0087\7n\2\2\u0087\u0088\7g\2\2\u0088\16\3\2\2\2\u0089\u008a"+
		"\7k\2\2\u008a\u008b\7h\2\2\u008b\20\3\2\2\2\u008c\u008d\7g\2\2\u008d\u008e"+
		"\7n\2\2\u008e\u008f\7u\2\2\u008f\u0090\7g\2\2\u0090\22\3\2\2\2\u0091\u0092"+
		"\7k\2\2\u0092\u0093\7p\2\2\u0093\24\3\2\2\2\u0094\u0095\7t\2\2\u0095\u0096"+
		"\7g\2\2\u0096\u0097\7v\2\2\u0097\u0098\7w\2\2\u0098\u0099\7t\2\2\u0099"+
		"\u009a\7p\2\2\u009a\26\3\2\2\2\u009b\u009c\7/\2\2\u009c\30\3\2\2\2\u009d"+
		"\u009e\7#\2\2\u009e\32\3\2\2\2\u009f\u00a0\7,\2\2\u00a0\34\3\2\2\2\u00a1"+
		"\u00a2\7\61\2\2\u00a2\36\3\2\2\2\u00a3\u00a4\7\'\2\2\u00a4 \3\2\2\2\u00a5"+
		"\u00a6\7-\2\2\u00a6\"\3\2\2\2\u00a7\u00a8\7\60\2\2\u00a8\u00b6\7\60\2"+
		"\2\u00a9\u00aa\7>\2\2\u00aa\u00b6\7\60\2\2\u00ab\u00ac\7\60\2\2\u00ac"+
		"\u00b6\7>\2\2\u00ad\u00ae\7>\2\2\u00ae\u00b6\7>\2\2\u00af\u00b0\7\60\2"+
		"\2\u00b0\u00b1\7\60\2\2\u00b1\u00b6\7\60\2\2\u00b2\u00b3\7>\2\2\u00b3"+
		"\u00b4\7\60\2\2\u00b4\u00b6\7\60\2\2\u00b5\u00a7\3\2\2\2\u00b5\u00a9\3"+
		"\2\2\2\u00b5\u00ab\3\2\2\2\u00b5\u00ad\3\2\2\2\u00b5\u00af\3\2\2\2\u00b5"+
		"\u00b2\3\2\2\2\u00b6$\3\2\2\2\u00b7\u00b8\7-\2\2\u00b8\u00b9\7-\2\2\u00b9"+
		"&\3\2\2\2\u00ba\u00bb\7>\2\2\u00bb(\3\2\2\2\u00bc\u00bd\7@\2\2\u00bd*"+
		"\3\2\2\2\u00be\u00bf\7>\2\2\u00bf\u00c0\7?\2\2\u00c0,\3\2\2\2\u00c1\u00c2"+
		"\7@\2\2\u00c2\u00c3\7?\2\2\u00c3.\3\2\2\2\u00c4\u00c5\7?\2\2\u00c5\u00c6"+
		"\7?\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7#\2\2\u00c8\u00c9\7?\2\2\u00c9\62"+
		"\3\2\2\2\u00ca\u00cb\7(\2\2\u00cb\64\3\2\2\2\u00cc\u00cd\7~\2\2\u00cd"+
		"\66\3\2\2\2\u00ce\u00cf\7<\2\2\u00cf\u00d0\7?\2\2\u00d08\3\2\2\2\u00d1"+
		"\u00d2\7*\2\2\u00d2:\3\2\2\2\u00d3\u00d4\7+\2\2\u00d4<\3\2\2\2\u00d5\u00d6"+
		"\7.\2\2\u00d6>\3\2\2\2\u00d7\u00d8\7<\2\2\u00d8@\3\2\2\2\u00d9\u00da\7"+
		"?\2\2\u00daB\3\2\2\2\u00db\u00dc\7=\2\2\u00dcD\3\2\2\2\u00dd\u00de\7}"+
		"\2\2\u00deF\3\2\2\2\u00df\u00e0\7\177\2\2\u00e0H\3\2\2\2\u00e1\u00e2\7"+
		"\60\2\2\u00e2J\3\2\2\2\u00e3\u00e4\7]\2\2\u00e4L\3\2\2\2\u00e5\u00e6\7"+
		"_\2\2\u00e6N\3\2\2\2\u00e7\u00eb\t\2\2\2\u00e8\u00ea\t\3\2\2\u00e9\u00e8"+
		"\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"P\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f2\t\4\2\2\u00ef\u00f1\t\5\2\2"+
		"\u00f0\u00ef\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3"+
		"\3\2\2\2\u00f3R\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f7\t\6\2\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2"+
		"\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\b*\2\2\u00fbT\3\2\2\2\u00fc\u0100"+
		"\t\7\2\2\u00fd\u00ff\t\b\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100"+
		"\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0105\3\2\2\2\u0102\u0100\3\2"+
		"\2\2\u0103\u0105\t\t\2\2\u0104\u00fc\3\2\2\2\u0104\u0103\3\2\2\2\u0105"+
		"V\3\2\2\2\u0106\u0107\7v\2\2\u0107\u0108\7t\2\2\u0108\u0109\7w\2\2\u0109"+
		"\u0110\7g\2\2\u010a\u010b\7h\2\2\u010b\u010c\7c\2\2\u010c\u010d\7n\2\2"+
		"\u010d\u010e\7u\2\2\u010e\u0110\7g\2\2\u010f\u0106\3\2\2\2\u010f\u010a"+
		"\3\2\2\2\u0110X\3\2\2\2\u0111\u0114\5]/\2\u0112\u0114\5_\60\2\u0113\u0111"+
		"\3\2\2\2\u0113\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\b-\3\2\u0116"+
		"Z\3\2\2\2\u0117\u011b\7$\2\2\u0118\u011a\n\n\2\2\u0119\u0118\3\2\2\2\u011a"+
		"\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2"+
		"\2\2\u011d\u011b\3\2\2\2\u011e\u011f\7$\2\2\u011f\\\3\2\2\2\u0120\u0124"+
		"\t\13\2\2\u0121\u0123\13\2\2\2\u0122\u0121\3\2\2\2\u0123\u0126\3\2\2\2"+
		"\u0124\u0125\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u0124"+
		"\3\2\2\2\u0127\u0128\t\f\2\2\u0128\u0129\3\2\2\2\u0129\u012a\b/\4\2\u012a"+
		"^\3\2\2\2\u012b\u0130\7b\2\2\u012c\u012f\5_\60\2\u012d\u012f\n\r\2\2\u012e"+
		"\u012c\3\2\2\2\u012e\u012d\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2"+
		"\2\2\u0130\u0131\3\2\2\2\u0131\u0133\3\2\2\2\u0132\u0130\3\2\2\2\u0133"+
		"\u0134\7)\2\2\u0134`\3\2\2\2\17\2\u00b5\u00eb\u00f2\u00f8\u0100\u0104"+
		"\u010f\u0113\u011b\u0124\u012e\u0130";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}