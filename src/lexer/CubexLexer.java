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
		IN=9, RETURN=10, FOR=11, THING=12, NOTHING=13, MINUS=14, NEGATE=15, TIMES=16, 
		DIVIDE=17, MODULO=18, PLUS=19, RANGEOP=20, PLUSPLUS=21, LANGLE=22, RANGLE=23, 
		LTE=24, GTE=25, EQ=26, NE=27, AND=28, OR=29, ASSIGN=30, LPAREN=31, RPAREN=32, 
		COMMA=33, COLON=34, EQUAL=35, SEMICOLON=36, LBRACE=37, RBRACE=38, DOT=39, 
		LSQUARE=40, RSQUARE=41, BOOL=42, TYPEPARAM=43, CLASSNAME=44, NAME=45, 
		WS=46, INT=47, COMMENT=48, STRING=49, SINGLELINECOMMENT=50, MULTILINECOMMENT=51, 
		ERRORCHAR=52;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'for'", "'Thing'", "'Nothing'", 
		"'-'", "'!'", "'*'", "'/'", "'%'", "'+'", "RANGEOP", "'++'", "'<'", "'>'", 
		"'<='", "'>='", "'=='", "'!='", "'&'", "'|'", "':='", "'('", "')'", "','", 
		"':'", "'='", "';'", "'{'", "'}'", "'.'", "'['", "']'", "BOOL", "TYPEPARAM", 
		"CLASSNAME", "NAME", "WS", "INT", "COMMENT", "STRING", "SINGLELINECOMMENT", 
		"MULTILINECOMMENT", "ERRORCHAR"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "FOR", "THING", "NOTHING", "MINUS", "NEGATE", "TIMES", 
		"DIVIDE", "MODULO", "PLUS", "RANGEOP", "PLUSPLUS", "LANGLE", "RANGLE", 
		"LTE", "GTE", "EQ", "NE", "AND", "OR", "ASSIGN", "LPAREN", "RPAREN", "COMMA", 
		"COLON", "EQUAL", "SEMICOLON", "LBRACE", "RBRACE", "DOT", "LSQUARE", "RSQUARE", 
		"BOOL", "TYPEPARAM", "CLASSNAME", "NAME", "WS", "INT", "COMMENT", "STRING", 
		"SINGLELINECOMMENT", "MULTILINECOMMENT", "ERRORCHAR"
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
		case 45: WS_action((RuleContext)_localctx, actionIndex); break;

		case 47: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 49: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\66\u0152\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00d2\n\25\3\26\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\""+
		"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\3+\5+\u010d\n+\3,\3,\3-\3-\6-\u0113\n-\r-\16-\u0114\3.\3.\7.\u0119"+
		"\n.\f.\16.\u011c\13.\3/\6/\u011f\n/\r/\16/\u0120\3/\3/\3\60\3\60\7\60"+
		"\u0127\n\60\f\60\16\60\u012a\13\60\3\60\5\60\u012d\n\60\3\61\3\61\5\61"+
		"\u0131\n\61\3\61\3\61\3\62\3\62\7\62\u0137\n\62\f\62\16\62\u013a\13\62"+
		"\3\62\3\62\3\63\3\63\7\63\u0140\n\63\f\63\16\63\u0143\13\63\3\63\3\63"+
		"\3\64\3\64\3\64\7\64\u014a\n\64\f\64\16\64\u014d\13\64\3\64\3\64\3\65"+
		"\3\65\2\66\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1"+
		"\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25"+
		"\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1"+
		"= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60"+
		"\2_\61\1a\62\3c\63\1e\64\4g\65\1i\66\1\3\2\f\3\2C\\\6\2\62;C\\aac|\3\2"+
		"c|\5\2\13\f\17\17\"\"\3\2\63;\3\2\62;\3\2\62\62\5\2\f\f\17\17$$\4\2\f"+
		"\f\17\17\4\2))bb\u0161\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\3k\3\2\2\2\5u\3\2\2\2\7{\3\2\2\2\t\u0083\3\2\2\2\13\u0089\3\2\2\2\r"+
		"\u008d\3\2\2\2\17\u0093\3\2\2\2\21\u0096\3\2\2\2\23\u009b\3\2\2\2\25\u009e"+
		"\3\2\2\2\27\u00a5\3\2\2\2\31\u00a9\3\2\2\2\33\u00af\3\2\2\2\35\u00b7\3"+
		"\2\2\2\37\u00b9\3\2\2\2!\u00bb\3\2\2\2#\u00bd\3\2\2\2%\u00bf\3\2\2\2\'"+
		"\u00c1\3\2\2\2)\u00d1\3\2\2\2+\u00d3\3\2\2\2-\u00d6\3\2\2\2/\u00d8\3\2"+
		"\2\2\61\u00da\3\2\2\2\63\u00dd\3\2\2\2\65\u00e0\3\2\2\2\67\u00e3\3\2\2"+
		"\29\u00e6\3\2\2\2;\u00e8\3\2\2\2=\u00ea\3\2\2\2?\u00ed\3\2\2\2A\u00ef"+
		"\3\2\2\2C\u00f1\3\2\2\2E\u00f3\3\2\2\2G\u00f5\3\2\2\2I\u00f7\3\2\2\2K"+
		"\u00f9\3\2\2\2M\u00fb\3\2\2\2O\u00fd\3\2\2\2Q\u00ff\3\2\2\2S\u0101\3\2"+
		"\2\2U\u010c\3\2\2\2W\u010e\3\2\2\2Y\u0110\3\2\2\2[\u0116\3\2\2\2]\u011e"+
		"\3\2\2\2_\u012c\3\2\2\2a\u0130\3\2\2\2c\u0134\3\2\2\2e\u013d\3\2\2\2g"+
		"\u0146\3\2\2\2i\u0150\3\2\2\2kl\7k\2\2lm\7p\2\2mn\7v\2\2no\7g\2\2op\7"+
		"t\2\2pq\7h\2\2qr\7c\2\2rs\7e\2\2st\7g\2\2t\4\3\2\2\2uv\7e\2\2vw\7n\2\2"+
		"wx\7c\2\2xy\7u\2\2yz\7u\2\2z\6\3\2\2\2{|\7g\2\2|}\7z\2\2}~\7v\2\2~\177"+
		"\7g\2\2\177\u0080\7p\2\2\u0080\u0081\7f\2\2\u0081\u0082\7u\2\2\u0082\b"+
		"\3\2\2\2\u0083\u0084\7u\2\2\u0084\u0085\7w\2\2\u0085\u0086\7r\2\2\u0086"+
		"\u0087\7g\2\2\u0087\u0088\7t\2\2\u0088\n\3\2\2\2\u0089\u008a\7h\2\2\u008a"+
		"\u008b\7w\2\2\u008b\u008c\7p\2\2\u008c\f\3\2\2\2\u008d\u008e\7y\2\2\u008e"+
		"\u008f\7j\2\2\u008f\u0090\7k\2\2\u0090\u0091\7n\2\2\u0091\u0092\7g\2\2"+
		"\u0092\16\3\2\2\2\u0093\u0094\7k\2\2\u0094\u0095\7h\2\2\u0095\20\3\2\2"+
		"\2\u0096\u0097\7g\2\2\u0097\u0098\7n\2\2\u0098\u0099\7u\2\2\u0099\u009a"+
		"\7g\2\2\u009a\22\3\2\2\2\u009b\u009c\7k\2\2\u009c\u009d\7p\2\2\u009d\24"+
		"\3\2\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7v\2\2\u00a1"+
		"\u00a2\7w\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7p\2\2\u00a4\26\3\2\2\2\u00a5"+
		"\u00a6\7h\2\2\u00a6\u00a7\7q\2\2\u00a7\u00a8\7t\2\2\u00a8\30\3\2\2\2\u00a9"+
		"\u00aa\7V\2\2\u00aa\u00ab\7j\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7p\2\2"+
		"\u00ad\u00ae\7i\2\2\u00ae\32\3\2\2\2\u00af\u00b0\7P\2\2\u00b0\u00b1\7"+
		"q\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7j\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5"+
		"\7p\2\2\u00b5\u00b6\7i\2\2\u00b6\34\3\2\2\2\u00b7\u00b8\7/\2\2\u00b8\36"+
		"\3\2\2\2\u00b9\u00ba\7#\2\2\u00ba \3\2\2\2\u00bb\u00bc\7,\2\2\u00bc\""+
		"\3\2\2\2\u00bd\u00be\7\61\2\2\u00be$\3\2\2\2\u00bf\u00c0\7\'\2\2\u00c0"+
		"&\3\2\2\2\u00c1\u00c2\7-\2\2\u00c2(\3\2\2\2\u00c3\u00c4\7\60\2\2\u00c4"+
		"\u00d2\7\60\2\2\u00c5\u00c6\7>\2\2\u00c6\u00d2\7\60\2\2\u00c7\u00c8\7"+
		"\60\2\2\u00c8\u00d2\7>\2\2\u00c9\u00ca\7>\2\2\u00ca\u00d2\7>\2\2\u00cb"+
		"\u00cc\7\60\2\2\u00cc\u00cd\7\60\2\2\u00cd\u00d2\7\60\2\2\u00ce\u00cf"+
		"\7>\2\2\u00cf\u00d0\7\60\2\2\u00d0\u00d2\7\60\2\2\u00d1\u00c3\3\2\2\2"+
		"\u00d1\u00c5\3\2\2\2\u00d1\u00c7\3\2\2\2\u00d1\u00c9\3\2\2\2\u00d1\u00cb"+
		"\3\2\2\2\u00d1\u00ce\3\2\2\2\u00d2*\3\2\2\2\u00d3\u00d4\7-\2\2\u00d4\u00d5"+
		"\7-\2\2\u00d5,\3\2\2\2\u00d6\u00d7\7>\2\2\u00d7.\3\2\2\2\u00d8\u00d9\7"+
		"@\2\2\u00d9\60\3\2\2\2\u00da\u00db\7>\2\2\u00db\u00dc\7?\2\2\u00dc\62"+
		"\3\2\2\2\u00dd\u00de\7@\2\2\u00de\u00df\7?\2\2\u00df\64\3\2\2\2\u00e0"+
		"\u00e1\7?\2\2\u00e1\u00e2\7?\2\2\u00e2\66\3\2\2\2\u00e3\u00e4\7#\2\2\u00e4"+
		"\u00e5\7?\2\2\u00e58\3\2\2\2\u00e6\u00e7\7(\2\2\u00e7:\3\2\2\2\u00e8\u00e9"+
		"\7~\2\2\u00e9<\3\2\2\2\u00ea\u00eb\7<\2\2\u00eb\u00ec\7?\2\2\u00ec>\3"+
		"\2\2\2\u00ed\u00ee\7*\2\2\u00ee@\3\2\2\2\u00ef\u00f0\7+\2\2\u00f0B\3\2"+
		"\2\2\u00f1\u00f2\7.\2\2\u00f2D\3\2\2\2\u00f3\u00f4\7<\2\2\u00f4F\3\2\2"+
		"\2\u00f5\u00f6\7?\2\2\u00f6H\3\2\2\2\u00f7\u00f8\7=\2\2\u00f8J\3\2\2\2"+
		"\u00f9\u00fa\7}\2\2\u00faL\3\2\2\2\u00fb\u00fc\7\177\2\2\u00fcN\3\2\2"+
		"\2\u00fd\u00fe\7\60\2\2\u00feP\3\2\2\2\u00ff\u0100\7]\2\2\u0100R\3\2\2"+
		"\2\u0101\u0102\7_\2\2\u0102T\3\2\2\2\u0103\u0104\7v\2\2\u0104\u0105\7"+
		"t\2\2\u0105\u0106\7w\2\2\u0106\u010d\7g\2\2\u0107\u0108\7h\2\2\u0108\u0109"+
		"\7c\2\2\u0109\u010a\7n\2\2\u010a\u010b\7u\2\2\u010b\u010d\7g\2\2\u010c"+
		"\u0103\3\2\2\2\u010c\u0107\3\2\2\2\u010dV\3\2\2\2\u010e\u010f\t\2\2\2"+
		"\u010fX\3\2\2\2\u0110\u0112\t\2\2\2\u0111\u0113\t\3\2\2\u0112\u0111\3"+
		"\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"Z\3\2\2\2\u0116\u011a\t\4\2\2\u0117\u0119\t\3\2\2\u0118\u0117\3\2\2\2"+
		"\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\\\3"+
		"\2\2\2\u011c\u011a\3\2\2\2\u011d\u011f\t\5\2\2\u011e\u011d\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2"+
		"\2\2\u0122\u0123\b/\2\2\u0123^\3\2\2\2\u0124\u0128\t\6\2\2\u0125\u0127"+
		"\t\7\2\2\u0126\u0125\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129\u012d\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012d\t\b"+
		"\2\2\u012c\u0124\3\2\2\2\u012c\u012b\3\2\2\2\u012d`\3\2\2\2\u012e\u0131"+
		"\5e\63\2\u012f\u0131\5g\64\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0133\b\61\3\2\u0133b\3\2\2\2\u0134\u0138\7$\2\2"+
		"\u0135\u0137\n\t\2\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136"+
		"\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b"+
		"\u013c\7$\2\2\u013cd\3\2\2\2\u013d\u0141\7%\2\2\u013e\u0140\n\n\2\2\u013f"+
		"\u013e\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\b\63\4\2\u0145"+
		"f\3\2\2\2\u0146\u014b\7b\2\2\u0147\u014a\5g\64\2\u0148\u014a\n\13\2\2"+
		"\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149"+
		"\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e"+
		"\u014f\7)\2\2\u014fh\3\2\2\2\u0150\u0151\13\2\2\2\u0151j\3\2\2\2\17\2"+
		"\u00d1\u010c\u0114\u011a\u0120\u0128\u012c\u0130\u0138\u0141\u0149\u014b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}