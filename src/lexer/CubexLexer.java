// Generated from /home/dominick/workspace/Compilers/src/lexer/CubexLexer.g4 by ANTLR 4.1
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
		IN=9, RETURN=10, FOR=11, MINUS=12, NEGATE=13, TIMES=14, DIVIDE=15, MODULO=16, 
		PLUS=17, RANGEOP=18, PLUSPLUS=19, LT=20, GT=21, LTE=22, GTE=23, EQ=24, 
		NE=25, AND=26, OR=27, ASSIGN=28, LPAREN=29, RPAREN=30, COMMA=31, COLON=32, 
		EQUAL=33, SEMICOLON=34, LBRACE=35, RBRACE=36, DOT=37, LSQUARE=38, RSQUARE=39, 
		BOOL=40, TYPE=41, NAME=42, WS=43, INT=44, COMMENT=45, STRING=46, SINGLELINECOMMENT=47, 
		MULTILINECOMMENT=48;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'for'", "'-'", "'!'", "'*'", "'/'", 
		"'%'", "'+'", "RANGEOP", "'++'", "'<'", "'>'", "'<='", "'>='", "'=='", 
		"'!='", "'&'", "'|'", "':='", "'('", "')'", "','", "':'", "'='", "';'", 
		"'{'", "'}'", "'.'", "'['", "']'", "BOOL", "TYPE", "NAME", "WS", "INT", 
		"COMMENT", "STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "FOR", "MINUS", "NEGATE", "TIMES", "DIVIDE", "MODULO", 
		"PLUS", "RANGEOP", "PLUSPLUS", "LT", "GT", "LTE", "GTE", "EQ", "NE", "AND", 
		"OR", "ASSIGN", "LPAREN", "RPAREN", "COMMA", "COLON", "EQUAL", "SEMICOLON", 
		"LBRACE", "RBRACE", "DOT", "LSQUARE", "RSQUARE", "BOOL", "TYPE", "NAME", 
		"WS", "INT", "COMMENT", "STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT"
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
		case 42: WS_action((RuleContext)_localctx, actionIndex); break;

		case 44: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 46: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\62\u0139\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\5\23\u00bc\n\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3"+
		"%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u00f7\n)\3*\3*"+
		"\7*\u00fb\n*\f*\16*\u00fe\13*\3+\3+\7+\u0102\n+\f+\16+\u0105\13+\3,\6"+
		",\u0108\n,\r,\16,\u0109\3,\3,\3-\3-\7-\u0110\n-\f-\16-\u0113\13-\3-\5"+
		"-\u0116\n-\3.\3.\5.\u011a\n.\3.\3.\3/\3/\7/\u0120\n/\f/\16/\u0123\13/"+
		"\3/\3/\3\60\3\60\7\60\u0129\n\60\f\60\16\60\u012c\13\60\3\60\3\60\3\61"+
		"\3\61\3\61\7\61\u0133\n\61\f\61\16\61\u0136\13\61\3\61\3\61\2\62\3\3\1"+
		"\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31"+
		"\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30"+
		"\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1"+
		"E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\2Y.\1[/\3]\60\1_\61\4a\62\1\3"+
		"\2\f\3\2C\\\6\2\62;C\\aac|\3\2c|\5\2\13\f\17\17\"\"\3\2\63;\3\2\62;\3"+
		"\2\62\62\3\2$$\4\2\f\f\17\17\4\2))bb\u0148\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\3c\3\2\2\2\5m\3\2\2\2"+
		"\7s\3\2\2\2\t{\3\2\2\2\13\u0081\3\2\2\2\r\u0085\3\2\2\2\17\u008b\3\2\2"+
		"\2\21\u008e\3\2\2\2\23\u0093\3\2\2\2\25\u0096\3\2\2\2\27\u009d\3\2\2\2"+
		"\31\u00a1\3\2\2\2\33\u00a3\3\2\2\2\35\u00a5\3\2\2\2\37\u00a7\3\2\2\2!"+
		"\u00a9\3\2\2\2#\u00ab\3\2\2\2%\u00bb\3\2\2\2\'\u00bd\3\2\2\2)\u00c0\3"+
		"\2\2\2+\u00c2\3\2\2\2-\u00c4\3\2\2\2/\u00c7\3\2\2\2\61\u00ca\3\2\2\2\63"+
		"\u00cd\3\2\2\2\65\u00d0\3\2\2\2\67\u00d2\3\2\2\29\u00d4\3\2\2\2;\u00d7"+
		"\3\2\2\2=\u00d9\3\2\2\2?\u00db\3\2\2\2A\u00dd\3\2\2\2C\u00df\3\2\2\2E"+
		"\u00e1\3\2\2\2G\u00e3\3\2\2\2I\u00e5\3\2\2\2K\u00e7\3\2\2\2M\u00e9\3\2"+
		"\2\2O\u00eb\3\2\2\2Q\u00f6\3\2\2\2S\u00f8\3\2\2\2U\u00ff\3\2\2\2W\u0107"+
		"\3\2\2\2Y\u0115\3\2\2\2[\u0119\3\2\2\2]\u011d\3\2\2\2_\u0126\3\2\2\2a"+
		"\u012f\3\2\2\2cd\7k\2\2de\7p\2\2ef\7v\2\2fg\7g\2\2gh\7t\2\2hi\7h\2\2i"+
		"j\7c\2\2jk\7e\2\2kl\7g\2\2l\4\3\2\2\2mn\7e\2\2no\7n\2\2op\7c\2\2pq\7u"+
		"\2\2qr\7u\2\2r\6\3\2\2\2st\7g\2\2tu\7z\2\2uv\7v\2\2vw\7g\2\2wx\7p\2\2"+
		"xy\7f\2\2yz\7u\2\2z\b\3\2\2\2{|\7u\2\2|}\7w\2\2}~\7r\2\2~\177\7g\2\2\177"+
		"\u0080\7t\2\2\u0080\n\3\2\2\2\u0081\u0082\7h\2\2\u0082\u0083\7w\2\2\u0083"+
		"\u0084\7p\2\2\u0084\f\3\2\2\2\u0085\u0086\7y\2\2\u0086\u0087\7j\2\2\u0087"+
		"\u0088\7k\2\2\u0088\u0089\7n\2\2\u0089\u008a\7g\2\2\u008a\16\3\2\2\2\u008b"+
		"\u008c\7k\2\2\u008c\u008d\7h\2\2\u008d\20\3\2\2\2\u008e\u008f\7g\2\2\u008f"+
		"\u0090\7n\2\2\u0090\u0091\7u\2\2\u0091\u0092\7g\2\2\u0092\22\3\2\2\2\u0093"+
		"\u0094\7k\2\2\u0094\u0095\7p\2\2\u0095\24\3\2\2\2\u0096\u0097\7t\2\2\u0097"+
		"\u0098\7g\2\2\u0098\u0099\7v\2\2\u0099\u009a\7w\2\2\u009a\u009b\7t\2\2"+
		"\u009b\u009c\7p\2\2\u009c\26\3\2\2\2\u009d\u009e\7h\2\2\u009e\u009f\7"+
		"q\2\2\u009f\u00a0\7t\2\2\u00a0\30\3\2\2\2\u00a1\u00a2\7/\2\2\u00a2\32"+
		"\3\2\2\2\u00a3\u00a4\7#\2\2\u00a4\34\3\2\2\2\u00a5\u00a6\7,\2\2\u00a6"+
		"\36\3\2\2\2\u00a7\u00a8\7\61\2\2\u00a8 \3\2\2\2\u00a9\u00aa\7\'\2\2\u00aa"+
		"\"\3\2\2\2\u00ab\u00ac\7-\2\2\u00ac$\3\2\2\2\u00ad\u00ae\7\60\2\2\u00ae"+
		"\u00bc\7\60\2\2\u00af\u00b0\7>\2\2\u00b0\u00bc\7\60\2\2\u00b1\u00b2\7"+
		"\60\2\2\u00b2\u00bc\7>\2\2\u00b3\u00b4\7>\2\2\u00b4\u00bc\7>\2\2\u00b5"+
		"\u00b6\7\60\2\2\u00b6\u00b7\7\60\2\2\u00b7\u00bc\7\60\2\2\u00b8\u00b9"+
		"\7>\2\2\u00b9\u00ba\7\60\2\2\u00ba\u00bc\7\60\2\2\u00bb\u00ad\3\2\2\2"+
		"\u00bb\u00af\3\2\2\2\u00bb\u00b1\3\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00b5"+
		"\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bc&\3\2\2\2\u00bd\u00be\7-\2\2\u00be\u00bf"+
		"\7-\2\2\u00bf(\3\2\2\2\u00c0\u00c1\7>\2\2\u00c1*\3\2\2\2\u00c2\u00c3\7"+
		"@\2\2\u00c3,\3\2\2\2\u00c4\u00c5\7>\2\2\u00c5\u00c6\7?\2\2\u00c6.\3\2"+
		"\2\2\u00c7\u00c8\7@\2\2\u00c8\u00c9\7?\2\2\u00c9\60\3\2\2\2\u00ca\u00cb"+
		"\7?\2\2\u00cb\u00cc\7?\2\2\u00cc\62\3\2\2\2\u00cd\u00ce\7#\2\2\u00ce\u00cf"+
		"\7?\2\2\u00cf\64\3\2\2\2\u00d0\u00d1\7(\2\2\u00d1\66\3\2\2\2\u00d2\u00d3"+
		"\7~\2\2\u00d38\3\2\2\2\u00d4\u00d5\7<\2\2\u00d5\u00d6\7?\2\2\u00d6:\3"+
		"\2\2\2\u00d7\u00d8\7*\2\2\u00d8<\3\2\2\2\u00d9\u00da\7+\2\2\u00da>\3\2"+
		"\2\2\u00db\u00dc\7.\2\2\u00dc@\3\2\2\2\u00dd\u00de\7<\2\2\u00deB\3\2\2"+
		"\2\u00df\u00e0\7?\2\2\u00e0D\3\2\2\2\u00e1\u00e2\7=\2\2\u00e2F\3\2\2\2"+
		"\u00e3\u00e4\7}\2\2\u00e4H\3\2\2\2\u00e5\u00e6\7\177\2\2\u00e6J\3\2\2"+
		"\2\u00e7\u00e8\7\60\2\2\u00e8L\3\2\2\2\u00e9\u00ea\7]\2\2\u00eaN\3\2\2"+
		"\2\u00eb\u00ec\7_\2\2\u00ecP\3\2\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7"+
		"t\2\2\u00ef\u00f0\7w\2\2\u00f0\u00f7\7g\2\2\u00f1\u00f2\7h\2\2\u00f2\u00f3"+
		"\7c\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f7\7g\2\2\u00f6"+
		"\u00ed\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f7R\3\2\2\2\u00f8\u00fc\t\2\2\2"+
		"\u00f9\u00fb\t\3\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa"+
		"\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fdT\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff"+
		"\u0103\t\4\2\2\u0100\u0102\t\3\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2"+
		"\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104V\3\2\2\2\u0105\u0103"+
		"\3\2\2\2\u0106\u0108\t\5\2\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\b,"+
		"\2\2\u010cX\3\2\2\2\u010d\u0111\t\6\2\2\u010e\u0110\t\7\2\2\u010f\u010e"+
		"\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		"\u0116\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0116\t\b\2\2\u0115\u010d\3\2"+
		"\2\2\u0115\u0114\3\2\2\2\u0116Z\3\2\2\2\u0117\u011a\5_\60\2\u0118\u011a"+
		"\5a\61\2\u0119\u0117\3\2\2\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b"+
		"\u011c\b.\3\2\u011c\\\3\2\2\2\u011d\u0121\7$\2\2\u011e\u0120\n\t\2\2\u011f"+
		"\u011e\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2"+
		"\2\2\u0122\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0125\7$\2\2\u0125"+
		"^\3\2\2\2\u0126\u012a\7%\2\2\u0127\u0129\n\n\2\2\u0128\u0127\3\2\2\2\u0129"+
		"\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012d\3\2"+
		"\2\2\u012c\u012a\3\2\2\2\u012d\u012e\b\60\4\2\u012e`\3\2\2\2\u012f\u0134"+
		"\7b\2\2\u0130\u0133\5a\61\2\u0131\u0133\n\13\2\2\u0132\u0130\3\2\2\2\u0132"+
		"\u0131\3\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134\u0135\3\2"+
		"\2\2\u0135\u0137\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u0138\7)\2\2\u0138"+
		"b\3\2\2\2\17\2\u00bb\u00f6\u00fc\u0103\u0109\u0111\u0115\u0119\u0121\u012a"+
		"\u0132\u0134";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}