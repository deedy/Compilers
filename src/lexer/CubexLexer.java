// Generated from /Users/deedy/Dropbox/Fall 2013/CS4120/Compilers/src/lexer/CubexLexer.g4 by ANTLR 4.1
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
		WS=46, INT=47, COMMENT=48, STRING=49, SINGLELINECOMMENT=50, MULTILINECOMMENT=51;
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
		"MULTILINECOMMENT"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "FOR", "THING", "NOTHING", "MINUS", "NEGATE", "TIMES", 
		"DIVIDE", "MODULO", "PLUS", "RANGEOP", "PLUSPLUS", "LANGLE", "RANGLE", 
		"LTE", "GTE", "EQ", "NE", "AND", "OR", "ASSIGN", "LPAREN", "RPAREN", "COMMA", 
		"COLON", "EQUAL", "SEMICOLON", "LBRACE", "RBRACE", "DOT", "LSQUARE", "RSQUARE", 
		"BOOL", "TYPEPARAM", "CLASSNAME", "NAME", "WS", "INT", "COMMENT", "STRING", 
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\65\u014e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00d0\n\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$"+
		"\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\5"+
		"+\u010b\n+\3,\3,\3-\3-\6-\u0111\n-\r-\16-\u0112\3.\3.\7.\u0117\n.\f.\16"+
		".\u011a\13.\3/\6/\u011d\n/\r/\16/\u011e\3/\3/\3\60\3\60\7\60\u0125\n\60"+
		"\f\60\16\60\u0128\13\60\3\60\5\60\u012b\n\60\3\61\3\61\5\61\u012f\n\61"+
		"\3\61\3\61\3\62\3\62\7\62\u0135\n\62\f\62\16\62\u0138\13\62\3\62\3\62"+
		"\3\63\3\63\7\63\u013e\n\63\f\63\16\63\u0141\13\63\3\63\3\63\3\64\3\64"+
		"\3\64\7\64\u0148\n\64\f\64\16\64\u014b\13\64\3\64\3\64\2\65\3\3\1\5\4"+
		"\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16"+
		"\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1"+
		"/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$"+
		"\1G%\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\2_\61\1a\62\3c\63"+
		"\1e\64\4g\65\1\3\2\f\3\2C\\\6\2\62;C\\aac|\3\2c|\5\2\13\f\17\17\"\"\3"+
		"\2\63;\3\2\62;\3\2\62\62\4\2\f\f$$\4\2\f\f\17\17\4\2))bb\u015d\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2"+
		"c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\3i\3\2\2\2\5s\3\2\2\2\7y\3\2\2\2\t\u0081"+
		"\3\2\2\2\13\u0087\3\2\2\2\r\u008b\3\2\2\2\17\u0091\3\2\2\2\21\u0094\3"+
		"\2\2\2\23\u0099\3\2\2\2\25\u009c\3\2\2\2\27\u00a3\3\2\2\2\31\u00a7\3\2"+
		"\2\2\33\u00ad\3\2\2\2\35\u00b5\3\2\2\2\37\u00b7\3\2\2\2!\u00b9\3\2\2\2"+
		"#\u00bb\3\2\2\2%\u00bd\3\2\2\2\'\u00bf\3\2\2\2)\u00cf\3\2\2\2+\u00d1\3"+
		"\2\2\2-\u00d4\3\2\2\2/\u00d6\3\2\2\2\61\u00d8\3\2\2\2\63\u00db\3\2\2\2"+
		"\65\u00de\3\2\2\2\67\u00e1\3\2\2\29\u00e4\3\2\2\2;\u00e6\3\2\2\2=\u00e8"+
		"\3\2\2\2?\u00eb\3\2\2\2A\u00ed\3\2\2\2C\u00ef\3\2\2\2E\u00f1\3\2\2\2G"+
		"\u00f3\3\2\2\2I\u00f5\3\2\2\2K\u00f7\3\2\2\2M\u00f9\3\2\2\2O\u00fb\3\2"+
		"\2\2Q\u00fd\3\2\2\2S\u00ff\3\2\2\2U\u010a\3\2\2\2W\u010c\3\2\2\2Y\u010e"+
		"\3\2\2\2[\u0114\3\2\2\2]\u011c\3\2\2\2_\u012a\3\2\2\2a\u012e\3\2\2\2c"+
		"\u0132\3\2\2\2e\u013b\3\2\2\2g\u0144\3\2\2\2ij\7k\2\2jk\7p\2\2kl\7v\2"+
		"\2lm\7g\2\2mn\7t\2\2no\7h\2\2op\7c\2\2pq\7e\2\2qr\7g\2\2r\4\3\2\2\2st"+
		"\7e\2\2tu\7n\2\2uv\7c\2\2vw\7u\2\2wx\7u\2\2x\6\3\2\2\2yz\7g\2\2z{\7z\2"+
		"\2{|\7v\2\2|}\7g\2\2}~\7p\2\2~\177\7f\2\2\177\u0080\7u\2\2\u0080\b\3\2"+
		"\2\2\u0081\u0082\7u\2\2\u0082\u0083\7w\2\2\u0083\u0084\7r\2\2\u0084\u0085"+
		"\7g\2\2\u0085\u0086\7t\2\2\u0086\n\3\2\2\2\u0087\u0088\7h\2\2\u0088\u0089"+
		"\7w\2\2\u0089\u008a\7p\2\2\u008a\f\3\2\2\2\u008b\u008c\7y\2\2\u008c\u008d"+
		"\7j\2\2\u008d\u008e\7k\2\2\u008e\u008f\7n\2\2\u008f\u0090\7g\2\2\u0090"+
		"\16\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7h\2\2\u0093\20\3\2\2\2\u0094"+
		"\u0095\7g\2\2\u0095\u0096\7n\2\2\u0096\u0097\7u\2\2\u0097\u0098\7g\2\2"+
		"\u0098\22\3\2\2\2\u0099\u009a\7k\2\2\u009a\u009b\7p\2\2\u009b\24\3\2\2"+
		"\2\u009c\u009d\7t\2\2\u009d\u009e\7g\2\2\u009e\u009f\7v\2\2\u009f\u00a0"+
		"\7w\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7p\2\2\u00a2\26\3\2\2\2\u00a3\u00a4"+
		"\7h\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6\7t\2\2\u00a6\30\3\2\2\2\u00a7\u00a8"+
		"\7V\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7p\2\2\u00ab"+
		"\u00ac\7i\2\2\u00ac\32\3\2\2\2\u00ad\u00ae\7P\2\2\u00ae\u00af\7q\2\2\u00af"+
		"\u00b0\7v\2\2\u00b0\u00b1\7j\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3\7p\2\2"+
		"\u00b3\u00b4\7i\2\2\u00b4\34\3\2\2\2\u00b5\u00b6\7/\2\2\u00b6\36\3\2\2"+
		"\2\u00b7\u00b8\7#\2\2\u00b8 \3\2\2\2\u00b9\u00ba\7,\2\2\u00ba\"\3\2\2"+
		"\2\u00bb\u00bc\7\61\2\2\u00bc$\3\2\2\2\u00bd\u00be\7\'\2\2\u00be&\3\2"+
		"\2\2\u00bf\u00c0\7-\2\2\u00c0(\3\2\2\2\u00c1\u00c2\7\60\2\2\u00c2\u00d0"+
		"\7\60\2\2\u00c3\u00c4\7>\2\2\u00c4\u00d0\7\60\2\2\u00c5\u00c6\7\60\2\2"+
		"\u00c6\u00d0\7>\2\2\u00c7\u00c8\7>\2\2\u00c8\u00d0\7>\2\2\u00c9\u00ca"+
		"\7\60\2\2\u00ca\u00cb\7\60\2\2\u00cb\u00d0\7\60\2\2\u00cc\u00cd\7>\2\2"+
		"\u00cd\u00ce\7\60\2\2\u00ce\u00d0\7\60\2\2\u00cf\u00c1\3\2\2\2\u00cf\u00c3"+
		"\3\2\2\2\u00cf\u00c5\3\2\2\2\u00cf\u00c7\3\2\2\2\u00cf\u00c9\3\2\2\2\u00cf"+
		"\u00cc\3\2\2\2\u00d0*\3\2\2\2\u00d1\u00d2\7-\2\2\u00d2\u00d3\7-\2\2\u00d3"+
		",\3\2\2\2\u00d4\u00d5\7>\2\2\u00d5.\3\2\2\2\u00d6\u00d7\7@\2\2\u00d7\60"+
		"\3\2\2\2\u00d8\u00d9\7>\2\2\u00d9\u00da\7?\2\2\u00da\62\3\2\2\2\u00db"+
		"\u00dc\7@\2\2\u00dc\u00dd\7?\2\2\u00dd\64\3\2\2\2\u00de\u00df\7?\2\2\u00df"+
		"\u00e0\7?\2\2\u00e0\66\3\2\2\2\u00e1\u00e2\7#\2\2\u00e2\u00e3\7?\2\2\u00e3"+
		"8\3\2\2\2\u00e4\u00e5\7(\2\2\u00e5:\3\2\2\2\u00e6\u00e7\7~\2\2\u00e7<"+
		"\3\2\2\2\u00e8\u00e9\7<\2\2\u00e9\u00ea\7?\2\2\u00ea>\3\2\2\2\u00eb\u00ec"+
		"\7*\2\2\u00ec@\3\2\2\2\u00ed\u00ee\7+\2\2\u00eeB\3\2\2\2\u00ef\u00f0\7"+
		".\2\2\u00f0D\3\2\2\2\u00f1\u00f2\7<\2\2\u00f2F\3\2\2\2\u00f3\u00f4\7?"+
		"\2\2\u00f4H\3\2\2\2\u00f5\u00f6\7=\2\2\u00f6J\3\2\2\2\u00f7\u00f8\7}\2"+
		"\2\u00f8L\3\2\2\2\u00f9\u00fa\7\177\2\2\u00faN\3\2\2\2\u00fb\u00fc\7\60"+
		"\2\2\u00fcP\3\2\2\2\u00fd\u00fe\7]\2\2\u00feR\3\2\2\2\u00ff\u0100\7_\2"+
		"\2\u0100T\3\2\2\2\u0101\u0102\7v\2\2\u0102\u0103\7t\2\2\u0103\u0104\7"+
		"w\2\2\u0104\u010b\7g\2\2\u0105\u0106\7h\2\2\u0106\u0107\7c\2\2\u0107\u0108"+
		"\7n\2\2\u0108\u0109\7u\2\2\u0109\u010b\7g\2\2\u010a\u0101\3\2\2\2\u010a"+
		"\u0105\3\2\2\2\u010bV\3\2\2\2\u010c\u010d\t\2\2\2\u010dX\3\2\2\2\u010e"+
		"\u0110\t\2\2\2\u010f\u0111\t\3\2\2\u0110\u010f\3\2\2\2\u0111\u0112\3\2"+
		"\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113Z\3\2\2\2\u0114\u0118"+
		"\t\4\2\2\u0115\u0117\t\3\2\2\u0116\u0115\3\2\2\2\u0117\u011a\3\2\2\2\u0118"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\\\3\2\2\2\u011a\u0118\3\2\2\2"+
		"\u011b\u011d\t\5\2\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011c"+
		"\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\b/\2\2\u0121"+
		"^\3\2\2\2\u0122\u0126\t\6\2\2\u0123\u0125\t\7\2\2\u0124\u0123\3\2\2\2"+
		"\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012b"+
		"\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012b\t\b\2\2\u012a\u0122\3\2\2\2\u012a"+
		"\u0129\3\2\2\2\u012b`\3\2\2\2\u012c\u012f\5e\63\2\u012d\u012f\5g\64\2"+
		"\u012e\u012c\3\2\2\2\u012e\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131"+
		"\b\61\3\2\u0131b\3\2\2\2\u0132\u0136\7$\2\2\u0133\u0135\n\t\2\2\u0134"+
		"\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2"+
		"\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\7$\2\2\u013a"+
		"d\3\2\2\2\u013b\u013f\7%\2\2\u013c\u013e\n\n\2\2\u013d\u013c\3\2\2\2\u013e"+
		"\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0142\3\2"+
		"\2\2\u0141\u013f\3\2\2\2\u0142\u0143\b\63\4\2\u0143f\3\2\2\2\u0144\u0149"+
		"\7b\2\2\u0145\u0148\5g\64\2\u0146\u0148\n\13\2\2\u0147\u0145\3\2\2\2\u0147"+
		"\u0146\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014d\7)\2\2\u014d"+
		"h\3\2\2\2\17\2\u00cf\u010a\u0112\u0118\u011e\u0126\u012a\u012e\u0136\u013f"+
		"\u0147\u0149";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}