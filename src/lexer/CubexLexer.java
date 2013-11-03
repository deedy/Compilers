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
		DIVIDE=17, MODULO=18, PLUS=19, STRICTSTRICTBINOP=20, OPENSTRICTBINOP=21, 
		STRICTOPENBINOP=22, OPENOPENBINOP=23, OPENONWARDSUNARYOP=24, STRICTONWARDSUNARYOP=25, 
		PLUSPLUS=26, LANGLE=27, RANGLE=28, LTE=29, GTE=30, EQ=31, NE=32, AND=33, 
		OR=34, ASSIGN=35, LPAREN=36, RPAREN=37, COMMA=38, COLON=39, EQUAL=40, 
		SEMICOLON=41, LBRACE=42, RBRACE=43, DOT=44, LSQUARE=45, RSQUARE=46, BOOL=47, 
		TYPEPARAM=48, CLASSNAME=49, NAME=50, WS=51, INT=52, COMMENT=53, STRING=54, 
		SINGLELINECOMMENT=55, MULTILINECOMMENT=56, ERRORCHAR=57;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'for'", "'Thing'", "'Nothing'", 
		"'-'", "'!'", "'*'", "'/'", "'%'", "'+'", "'..'", "'<.'", "'.<'", "'<<'", 
		"OPENONWARDSUNARYOP", "STRICTONWARDSUNARYOP", "'++'", "'<'", "'>'", "'<='", 
		"'>='", "'=='", "'!='", "'&'", "'|'", "':='", "'('", "')'", "','", "':'", 
		"'='", "';'", "'{'", "'}'", "'.'", "'['", "']'", "BOOL", "TYPEPARAM", 
		"CLASSNAME", "NAME", "WS", "INT", "COMMENT", "STRING", "SINGLELINECOMMENT", 
		"MULTILINECOMMENT", "ERRORCHAR"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "FOR", "THING", "NOTHING", "MINUS", "NEGATE", "TIMES", 
		"DIVIDE", "MODULO", "PLUS", "STRICTSTRICTBINOP", "OPENSTRICTBINOP", "STRICTOPENBINOP", 
		"OPENOPENBINOP", "OPENONWARDSUNARYOP", "STRICTONWARDSUNARYOP", "PLUSPLUS", 
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
		case 50: WS_action((RuleContext)_localctx, actionIndex); break;

		case 52: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 54: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2;\u0160\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3"+
		"$\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3."+
		"\3/\3/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60\u011b\n\60\3"+
		"\61\3\61\3\62\3\62\6\62\u0121\n\62\r\62\16\62\u0122\3\63\3\63\7\63\u0127"+
		"\n\63\f\63\16\63\u012a\13\63\3\64\6\64\u012d\n\64\r\64\16\64\u012e\3\64"+
		"\3\64\3\65\3\65\7\65\u0135\n\65\f\65\16\65\u0138\13\65\3\65\5\65\u013b"+
		"\n\65\3\66\3\66\5\66\u013f\n\66\3\66\3\66\3\67\3\67\7\67\u0145\n\67\f"+
		"\67\16\67\u0148\13\67\3\67\3\67\38\38\78\u014e\n8\f8\168\u0151\138\38"+
		"\38\39\39\39\79\u0158\n9\f9\169\u015b\139\39\39\3:\3:\2;\3\3\1\5\4\1\7"+
		"\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33"+
		"\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1"+
		"\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1"+
		"I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\1a\62\1c\63\1e\64"+
		"\1g\65\2i\66\1k\67\3m8\1o9\4q:\1s;\1\3\2\f\3\2C\\\6\2\62;C\\aac|\3\2c"+
		"|\5\2\13\f\17\17\"\"\3\2\63;\3\2\62;\3\2\62\62\5\2\f\f\17\17$$\4\2\f\f"+
		"\17\17\4\2))bb\u016a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2"+
		"\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2"+
		"\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\3u\3\2\2\2\5\177"+
		"\3\2\2\2\7\u0085\3\2\2\2\t\u008d\3\2\2\2\13\u0093\3\2\2\2\r\u0097\3\2"+
		"\2\2\17\u009d\3\2\2\2\21\u00a0\3\2\2\2\23\u00a5\3\2\2\2\25\u00a8\3\2\2"+
		"\2\27\u00af\3\2\2\2\31\u00b3\3\2\2\2\33\u00b9\3\2\2\2\35\u00c1\3\2\2\2"+
		"\37\u00c3\3\2\2\2!\u00c5\3\2\2\2#\u00c7\3\2\2\2%\u00c9\3\2\2\2\'\u00cb"+
		"\3\2\2\2)\u00cd\3\2\2\2+\u00d0\3\2\2\2-\u00d3\3\2\2\2/\u00d6\3\2\2\2\61"+
		"\u00d9\3\2\2\2\63\u00dd\3\2\2\2\65\u00e1\3\2\2\2\67\u00e4\3\2\2\29\u00e6"+
		"\3\2\2\2;\u00e8\3\2\2\2=\u00eb\3\2\2\2?\u00ee\3\2\2\2A\u00f1\3\2\2\2C"+
		"\u00f4\3\2\2\2E\u00f6\3\2\2\2G\u00f8\3\2\2\2I\u00fb\3\2\2\2K\u00fd\3\2"+
		"\2\2M\u00ff\3\2\2\2O\u0101\3\2\2\2Q\u0103\3\2\2\2S\u0105\3\2\2\2U\u0107"+
		"\3\2\2\2W\u0109\3\2\2\2Y\u010b\3\2\2\2[\u010d\3\2\2\2]\u010f\3\2\2\2_"+
		"\u011a\3\2\2\2a\u011c\3\2\2\2c\u011e\3\2\2\2e\u0124\3\2\2\2g\u012c\3\2"+
		"\2\2i\u013a\3\2\2\2k\u013e\3\2\2\2m\u0142\3\2\2\2o\u014b\3\2\2\2q\u0154"+
		"\3\2\2\2s\u015e\3\2\2\2uv\7k\2\2vw\7p\2\2wx\7v\2\2xy\7g\2\2yz\7t\2\2z"+
		"{\7h\2\2{|\7c\2\2|}\7e\2\2}~\7g\2\2~\4\3\2\2\2\177\u0080\7e\2\2\u0080"+
		"\u0081\7n\2\2\u0081\u0082\7c\2\2\u0082\u0083\7u\2\2\u0083\u0084\7u\2\2"+
		"\u0084\6\3\2\2\2\u0085\u0086\7g\2\2\u0086\u0087\7z\2\2\u0087\u0088\7v"+
		"\2\2\u0088\u0089\7g\2\2\u0089\u008a\7p\2\2\u008a\u008b\7f\2\2\u008b\u008c"+
		"\7u\2\2\u008c\b\3\2\2\2\u008d\u008e\7u\2\2\u008e\u008f\7w\2\2\u008f\u0090"+
		"\7r\2\2\u0090\u0091\7g\2\2\u0091\u0092\7t\2\2\u0092\n\3\2\2\2\u0093\u0094"+
		"\7h\2\2\u0094\u0095\7w\2\2\u0095\u0096\7p\2\2\u0096\f\3\2\2\2\u0097\u0098"+
		"\7y\2\2\u0098\u0099\7j\2\2\u0099\u009a\7k\2\2\u009a\u009b\7n\2\2\u009b"+
		"\u009c\7g\2\2\u009c\16\3\2\2\2\u009d\u009e\7k\2\2\u009e\u009f\7h\2\2\u009f"+
		"\20\3\2\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7u\2\2\u00a3"+
		"\u00a4\7g\2\2\u00a4\22\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7p\2\2\u00a7"+
		"\24\3\2\2\2\u00a8\u00a9\7t\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7v\2\2\u00ab"+
		"\u00ac\7w\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7p\2\2\u00ae\26\3\2\2\2\u00af"+
		"\u00b0\7h\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7t\2\2\u00b2\30\3\2\2\2\u00b3"+
		"\u00b4\7V\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7p\2\2"+
		"\u00b7\u00b8\7i\2\2\u00b8\32\3\2\2\2\u00b9\u00ba\7P\2\2\u00ba\u00bb\7"+
		"q\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7j\2\2\u00bd\u00be\7k\2\2\u00be\u00bf"+
		"\7p\2\2\u00bf\u00c0\7i\2\2\u00c0\34\3\2\2\2\u00c1\u00c2\7/\2\2\u00c2\36"+
		"\3\2\2\2\u00c3\u00c4\7#\2\2\u00c4 \3\2\2\2\u00c5\u00c6\7,\2\2\u00c6\""+
		"\3\2\2\2\u00c7\u00c8\7\61\2\2\u00c8$\3\2\2\2\u00c9\u00ca\7\'\2\2\u00ca"+
		"&\3\2\2\2\u00cb\u00cc\7-\2\2\u00cc(\3\2\2\2\u00cd\u00ce\7\60\2\2\u00ce"+
		"\u00cf\7\60\2\2\u00cf*\3\2\2\2\u00d0\u00d1\7>\2\2\u00d1\u00d2\7\60\2\2"+
		"\u00d2,\3\2\2\2\u00d3\u00d4\7\60\2\2\u00d4\u00d5\7>\2\2\u00d5.\3\2\2\2"+
		"\u00d6\u00d7\7>\2\2\u00d7\u00d8\7>\2\2\u00d8\60\3\2\2\2\u00d9\u00da\7"+
		">\2\2\u00da\u00db\7\60\2\2\u00db\u00dc\7\60\2\2\u00dc\62\3\2\2\2\u00dd"+
		"\u00de\7>\2\2\u00de\u00df\7\60\2\2\u00df\u00e0\7\60\2\2\u00e0\64\3\2\2"+
		"\2\u00e1\u00e2\7-\2\2\u00e2\u00e3\7-\2\2\u00e3\66\3\2\2\2\u00e4\u00e5"+
		"\7>\2\2\u00e58\3\2\2\2\u00e6\u00e7\7@\2\2\u00e7:\3\2\2\2\u00e8\u00e9\7"+
		">\2\2\u00e9\u00ea\7?\2\2\u00ea<\3\2\2\2\u00eb\u00ec\7@\2\2\u00ec\u00ed"+
		"\7?\2\2\u00ed>\3\2\2\2\u00ee\u00ef\7?\2\2\u00ef\u00f0\7?\2\2\u00f0@\3"+
		"\2\2\2\u00f1\u00f2\7#\2\2\u00f2\u00f3\7?\2\2\u00f3B\3\2\2\2\u00f4\u00f5"+
		"\7(\2\2\u00f5D\3\2\2\2\u00f6\u00f7\7~\2\2\u00f7F\3\2\2\2\u00f8\u00f9\7"+
		"<\2\2\u00f9\u00fa\7?\2\2\u00faH\3\2\2\2\u00fb\u00fc\7*\2\2\u00fcJ\3\2"+
		"\2\2\u00fd\u00fe\7+\2\2\u00feL\3\2\2\2\u00ff\u0100\7.\2\2\u0100N\3\2\2"+
		"\2\u0101\u0102\7<\2\2\u0102P\3\2\2\2\u0103\u0104\7?\2\2\u0104R\3\2\2\2"+
		"\u0105\u0106\7=\2\2\u0106T\3\2\2\2\u0107\u0108\7}\2\2\u0108V\3\2\2\2\u0109"+
		"\u010a\7\177\2\2\u010aX\3\2\2\2\u010b\u010c\7\60\2\2\u010cZ\3\2\2\2\u010d"+
		"\u010e\7]\2\2\u010e\\\3\2\2\2\u010f\u0110\7_\2\2\u0110^\3\2\2\2\u0111"+
		"\u0112\7v\2\2\u0112\u0113\7t\2\2\u0113\u0114\7w\2\2\u0114\u011b\7g\2\2"+
		"\u0115\u0116\7h\2\2\u0116\u0117\7c\2\2\u0117\u0118\7n\2\2\u0118\u0119"+
		"\7u\2\2\u0119\u011b\7g\2\2\u011a\u0111\3\2\2\2\u011a\u0115\3\2\2\2\u011b"+
		"`\3\2\2\2\u011c\u011d\t\2\2\2\u011db\3\2\2\2\u011e\u0120\t\2\2\2\u011f"+
		"\u0121\t\3\2\2\u0120\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0120\3\2"+
		"\2\2\u0122\u0123\3\2\2\2\u0123d\3\2\2\2\u0124\u0128\t\4\2\2\u0125\u0127"+
		"\t\3\2\2\u0126\u0125\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129f\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012d\t\5\2\2"+
		"\u012c\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b\64\2\2\u0131h\3\2\2\2\u0132"+
		"\u0136\t\6\2\2\u0133\u0135\t\7\2\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2"+
		"\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013b\3\2\2\2\u0138"+
		"\u0136\3\2\2\2\u0139\u013b\t\b\2\2\u013a\u0132\3\2\2\2\u013a\u0139\3\2"+
		"\2\2\u013bj\3\2\2\2\u013c\u013f\5o8\2\u013d\u013f\5q9\2\u013e\u013c\3"+
		"\2\2\2\u013e\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\b\66\3\2\u0141"+
		"l\3\2\2\2\u0142\u0146\7$\2\2\u0143\u0145\n\t\2\2\u0144\u0143\3\2\2\2\u0145"+
		"\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0149\3\2"+
		"\2\2\u0148\u0146\3\2\2\2\u0149\u014a\7$\2\2\u014an\3\2\2\2\u014b\u014f"+
		"\7%\2\2\u014c\u014e\n\n\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f"+
		"\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0152\3\2\2\2\u0151\u014f\3\2"+
		"\2\2\u0152\u0153\b8\4\2\u0153p\3\2\2\2\u0154\u0159\7b\2\2\u0155\u0158"+
		"\5q9\2\u0156\u0158\n\13\2\2\u0157\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158"+
		"\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2"+
		"\2\2\u015b\u0159\3\2\2\2\u015c\u015d\7)\2\2\u015dr\3\2\2\2\u015e\u015f"+
		"\13\2\2\2\u015ft\3\2\2\2\16\2\u011a\u0122\u0128\u012e\u0136\u013a\u013e"+
		"\u0146\u014f\u0157\u0159";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}