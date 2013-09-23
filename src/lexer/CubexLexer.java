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
		IN=9, RETURN=10, FOR=11, THING=12, NOTHING=13, MINUS=14, NEGATE=15, TIMES=16, 
		DIVIDE=17, MODULO=18, PLUS=19, RANGEOPBINARY=20, RANGEOPUNARY=21, PLUSPLUS=22, 
		LANGLE=23, RANGLE=24, LTE=25, GTE=26, EQ=27, NE=28, AND=29, OR=30, ASSIGN=31, 
		LPAREN=32, RPAREN=33, COMMA=34, COLON=35, EQUAL=36, SEMICOLON=37, LBRACE=38, 
		RBRACE=39, DOT=40, LSQUARE=41, RSQUARE=42, BOOL=43, TYPEPARAM=44, CLASSNAME=45, 
		NAME=46, WS=47, INT=48, COMMENT=49, STRING=50, SINGLELINECOMMENT=51, MULTILINECOMMENT=52, 
		ERRORCHAR=53;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'for'", "'Thing'", "'Nothing'", 
		"'-'", "'!'", "'*'", "'/'", "'%'", "'+'", "RANGEOPBINARY", "RANGEOPUNARY", 
		"'++'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&'", "'|'", "':='", 
		"'('", "')'", "','", "':'", "'='", "';'", "'{'", "'}'", "'.'", "'['", 
		"']'", "BOOL", "TYPEPARAM", "CLASSNAME", "NAME", "WS", "INT", "COMMENT", 
		"STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT", "ERRORCHAR"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "FOR", "THING", "NOTHING", "MINUS", "NEGATE", "TIMES", 
		"DIVIDE", "MODULO", "PLUS", "RANGEOPBINARY", "RANGEOPUNARY", "PLUSPLUS", 
		"LANGLE", "RANGLE", "LTE", "GTE", "EQ", "NE", "AND", "OR", "ASSIGN", "LPAREN", 
		"RPAREN", "COMMA", "COLON", "EQUAL", "SEMICOLON", "LBRACE", "RBRACE", 
		"DOT", "LSQUARE", "RSQUARE", "BOOL", "TYPEPARAM", "CLASSNAME", "NAME", 
		"WS", "INT", "COMMENT", "STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT", 
		"ERRORCHAR"
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
		case 46: WS_action((RuleContext)_localctx, actionIndex); break;

		case 48: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 50: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\67\u0156\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\5\25\u00ce\n\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u00d6\n\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 "+
		"\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3"+
		"+\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u0111\n,\3-\3-\3.\3.\6.\u0117\n.\r.\16"+
		".\u0118\3/\3/\7/\u011d\n/\f/\16/\u0120\13/\3\60\6\60\u0123\n\60\r\60\16"+
		"\60\u0124\3\60\3\60\3\61\3\61\7\61\u012b\n\61\f\61\16\61\u012e\13\61\3"+
		"\61\5\61\u0131\n\61\3\62\3\62\5\62\u0135\n\62\3\62\3\62\3\63\3\63\7\63"+
		"\u013b\n\63\f\63\16\63\u013e\13\63\3\63\3\63\3\64\3\64\7\64\u0144\n\64"+
		"\f\64\16\64\u0147\13\64\3\64\3\64\3\65\3\65\3\65\7\65\u014e\n\65\f\65"+
		"\16\65\u0151\13\65\3\65\3\65\3\66\3\66\2\67\3\3\1\5\4\1\7\5\1\t\6\1\13"+
		"\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1"+
		"\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33"+
		"\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\1O"+
		")\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\2a\62\1c\63\3e\64\1g\65\4i\66\1"+
		"k\67\1\3\2\f\3\2C\\\6\2\62;C\\aac|\3\2c|\5\2\13\f\17\17\"\"\3\2\63;\3"+
		"\2\62;\3\2\62\62\5\2\f\f\17\17$$\4\2\f\f\17\17\4\2))bb\u0164\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3"+
		"\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5w\3\2\2"+
		"\2\7}\3\2\2\2\t\u0085\3\2\2\2\13\u008b\3\2\2\2\r\u008f\3\2\2\2\17\u0095"+
		"\3\2\2\2\21\u0098\3\2\2\2\23\u009d\3\2\2\2\25\u00a0\3\2\2\2\27\u00a7\3"+
		"\2\2\2\31\u00ab\3\2\2\2\33\u00b1\3\2\2\2\35\u00b9\3\2\2\2\37\u00bb\3\2"+
		"\2\2!\u00bd\3\2\2\2#\u00bf\3\2\2\2%\u00c1\3\2\2\2\'\u00c3\3\2\2\2)\u00cd"+
		"\3\2\2\2+\u00d5\3\2\2\2-\u00d7\3\2\2\2/\u00da\3\2\2\2\61\u00dc\3\2\2\2"+
		"\63\u00de\3\2\2\2\65\u00e1\3\2\2\2\67\u00e4\3\2\2\29\u00e7\3\2\2\2;\u00ea"+
		"\3\2\2\2=\u00ec\3\2\2\2?\u00ee\3\2\2\2A\u00f1\3\2\2\2C\u00f3\3\2\2\2E"+
		"\u00f5\3\2\2\2G\u00f7\3\2\2\2I\u00f9\3\2\2\2K\u00fb\3\2\2\2M\u00fd\3\2"+
		"\2\2O\u00ff\3\2\2\2Q\u0101\3\2\2\2S\u0103\3\2\2\2U\u0105\3\2\2\2W\u0110"+
		"\3\2\2\2Y\u0112\3\2\2\2[\u0114\3\2\2\2]\u011a\3\2\2\2_\u0122\3\2\2\2a"+
		"\u0130\3\2\2\2c\u0134\3\2\2\2e\u0138\3\2\2\2g\u0141\3\2\2\2i\u014a\3\2"+
		"\2\2k\u0154\3\2\2\2mn\7k\2\2no\7p\2\2op\7v\2\2pq\7g\2\2qr\7t\2\2rs\7h"+
		"\2\2st\7c\2\2tu\7e\2\2uv\7g\2\2v\4\3\2\2\2wx\7e\2\2xy\7n\2\2yz\7c\2\2"+
		"z{\7u\2\2{|\7u\2\2|\6\3\2\2\2}~\7g\2\2~\177\7z\2\2\177\u0080\7v\2\2\u0080"+
		"\u0081\7g\2\2\u0081\u0082\7p\2\2\u0082\u0083\7f\2\2\u0083\u0084\7u\2\2"+
		"\u0084\b\3\2\2\2\u0085\u0086\7u\2\2\u0086\u0087\7w\2\2\u0087\u0088\7r"+
		"\2\2\u0088\u0089\7g\2\2\u0089\u008a\7t\2\2\u008a\n\3\2\2\2\u008b\u008c"+
		"\7h\2\2\u008c\u008d\7w\2\2\u008d\u008e\7p\2\2\u008e\f\3\2\2\2\u008f\u0090"+
		"\7y\2\2\u0090\u0091\7j\2\2\u0091\u0092\7k\2\2\u0092\u0093\7n\2\2\u0093"+
		"\u0094\7g\2\2\u0094\16\3\2\2\2\u0095\u0096\7k\2\2\u0096\u0097\7h\2\2\u0097"+
		"\20\3\2\2\2\u0098\u0099\7g\2\2\u0099\u009a\7n\2\2\u009a\u009b\7u\2\2\u009b"+
		"\u009c\7g\2\2\u009c\22\3\2\2\2\u009d\u009e\7k\2\2\u009e\u009f\7p\2\2\u009f"+
		"\24\3\2\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7v\2\2\u00a3"+
		"\u00a4\7w\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7p\2\2\u00a6\26\3\2\2\2\u00a7"+
		"\u00a8\7h\2\2\u00a8\u00a9\7q\2\2\u00a9\u00aa\7t\2\2\u00aa\30\3\2\2\2\u00ab"+
		"\u00ac\7V\2\2\u00ac\u00ad\7j\2\2\u00ad\u00ae\7k\2\2\u00ae\u00af\7p\2\2"+
		"\u00af\u00b0\7i\2\2\u00b0\32\3\2\2\2\u00b1\u00b2\7P\2\2\u00b2\u00b3\7"+
		"q\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7"+
		"\7p\2\2\u00b7\u00b8\7i\2\2\u00b8\34\3\2\2\2\u00b9\u00ba\7/\2\2\u00ba\36"+
		"\3\2\2\2\u00bb\u00bc\7#\2\2\u00bc \3\2\2\2\u00bd\u00be\7,\2\2\u00be\""+
		"\3\2\2\2\u00bf\u00c0\7\61\2\2\u00c0$\3\2\2\2\u00c1\u00c2\7\'\2\2\u00c2"+
		"&\3\2\2\2\u00c3\u00c4\7-\2\2\u00c4(\3\2\2\2\u00c5\u00c6\7\60\2\2\u00c6"+
		"\u00ce\7\60\2\2\u00c7\u00c8\7>\2\2\u00c8\u00ce\7\60\2\2\u00c9\u00ca\7"+
		"\60\2\2\u00ca\u00ce\7>\2\2\u00cb\u00cc\7>\2\2\u00cc\u00ce\7>\2\2\u00cd"+
		"\u00c5\3\2\2\2\u00cd\u00c7\3\2\2\2\u00cd\u00c9\3\2\2\2\u00cd\u00cb\3\2"+
		"\2\2\u00ce*\3\2\2\2\u00cf\u00d0\7\60\2\2\u00d0\u00d1\7\60\2\2\u00d1\u00d6"+
		"\7\60\2\2\u00d2\u00d3\7>\2\2\u00d3\u00d4\7\60\2\2\u00d4\u00d6\7\60\2\2"+
		"\u00d5\u00cf\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d6,\3\2\2\2\u00d7\u00d8\7"+
		"-\2\2\u00d8\u00d9\7-\2\2\u00d9.\3\2\2\2\u00da\u00db\7>\2\2\u00db\60\3"+
		"\2\2\2\u00dc\u00dd\7@\2\2\u00dd\62\3\2\2\2\u00de\u00df\7>\2\2\u00df\u00e0"+
		"\7?\2\2\u00e0\64\3\2\2\2\u00e1\u00e2\7@\2\2\u00e2\u00e3\7?\2\2\u00e3\66"+
		"\3\2\2\2\u00e4\u00e5\7?\2\2\u00e5\u00e6\7?\2\2\u00e68\3\2\2\2\u00e7\u00e8"+
		"\7#\2\2\u00e8\u00e9\7?\2\2\u00e9:\3\2\2\2\u00ea\u00eb\7(\2\2\u00eb<\3"+
		"\2\2\2\u00ec\u00ed\7~\2\2\u00ed>\3\2\2\2\u00ee\u00ef\7<\2\2\u00ef\u00f0"+
		"\7?\2\2\u00f0@\3\2\2\2\u00f1\u00f2\7*\2\2\u00f2B\3\2\2\2\u00f3\u00f4\7"+
		"+\2\2\u00f4D\3\2\2\2\u00f5\u00f6\7.\2\2\u00f6F\3\2\2\2\u00f7\u00f8\7<"+
		"\2\2\u00f8H\3\2\2\2\u00f9\u00fa\7?\2\2\u00faJ\3\2\2\2\u00fb\u00fc\7=\2"+
		"\2\u00fcL\3\2\2\2\u00fd\u00fe\7}\2\2\u00feN\3\2\2\2\u00ff\u0100\7\177"+
		"\2\2\u0100P\3\2\2\2\u0101\u0102\7\60\2\2\u0102R\3\2\2\2\u0103\u0104\7"+
		"]\2\2\u0104T\3\2\2\2\u0105\u0106\7_\2\2\u0106V\3\2\2\2\u0107\u0108\7v"+
		"\2\2\u0108\u0109\7t\2\2\u0109\u010a\7w\2\2\u010a\u0111\7g\2\2\u010b\u010c"+
		"\7h\2\2\u010c\u010d\7c\2\2\u010d\u010e\7n\2\2\u010e\u010f\7u\2\2\u010f"+
		"\u0111\7g\2\2\u0110\u0107\3\2\2\2\u0110\u010b\3\2\2\2\u0111X\3\2\2\2\u0112"+
		"\u0113\t\2\2\2\u0113Z\3\2\2\2\u0114\u0116\t\2\2\2\u0115\u0117\t\3\2\2"+
		"\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119"+
		"\3\2\2\2\u0119\\\3\2\2\2\u011a\u011e\t\4\2\2\u011b\u011d\t\3\2\2\u011c"+
		"\u011b\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2"+
		"\2\2\u011f^\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0123\t\5\2\2\u0122\u0121"+
		"\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\u0127\b\60\2\2\u0127`\3\2\2\2\u0128\u012c\t\6\2\2"+
		"\u0129\u012b\t\7\2\2\u012a\u0129\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u0131\3\2\2\2\u012e\u012c\3\2\2\2\u012f"+
		"\u0131\t\b\2\2\u0130\u0128\3\2\2\2\u0130\u012f\3\2\2\2\u0131b\3\2\2\2"+
		"\u0132\u0135\5g\64\2\u0133\u0135\5i\65\2\u0134\u0132\3\2\2\2\u0134\u0133"+
		"\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\b\62\3\2\u0137d\3\2\2\2\u0138"+
		"\u013c\7$\2\2\u0139\u013b\n\t\2\2\u013a\u0139\3\2\2\2\u013b\u013e\3\2"+
		"\2\2\u013c\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013f\3\2\2\2\u013e"+
		"\u013c\3\2\2\2\u013f\u0140\7$\2\2\u0140f\3\2\2\2\u0141\u0145\7%\2\2\u0142"+
		"\u0144\n\n\2\2\u0143\u0142\3\2\2\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2"+
		"\2\2\u0145\u0146\3\2\2\2\u0146\u0148\3\2\2\2\u0147\u0145\3\2\2\2\u0148"+
		"\u0149\b\64\4\2\u0149h\3\2\2\2\u014a\u014f\7b\2\2\u014b\u014e\5i\65\2"+
		"\u014c\u014e\n\13\2\2\u014d\u014b\3\2\2\2\u014d\u014c\3\2\2\2\u014e\u0151"+
		"\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0152\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0152\u0153\7)\2\2\u0153j\3\2\2\2\u0154\u0155\13\2\2\2"+
		"\u0155l\3\2\2\2\20\2\u00cd\u00d5\u0110\u0118\u011e\u0124\u012c\u0130\u0134"+
		"\u013c\u0145\u014d\u014f";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}