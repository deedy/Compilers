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
		DIVIDE=17, MODULO=18, PLUS=19, STRICTSTRICTBINOP=20, OPENSTRICTBINOP=21, 
		STRICTOPENBINOP=22, OPENOPENBINOP=23, RANGEOPUNARY=24, PLUSPLUS=25, LANGLE=26, 
		RANGLE=27, LTE=28, GTE=29, EQ=30, NE=31, AND=32, OR=33, ASSIGN=34, LPAREN=35, 
		RPAREN=36, COMMA=37, COLON=38, EQUAL=39, SEMICOLON=40, LBRACE=41, RBRACE=42, 
		DOT=43, LSQUARE=44, RSQUARE=45, BOOL=46, TYPEPARAM=47, CLASSNAME=48, NAME=49, 
		WS=50, INT=51, COMMENT=52, STRING=53, SINGLELINECOMMENT=54, MULTILINECOMMENT=55, 
		ERRORCHAR=56;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'for'", "'Thing'", "'Nothing'", 
		"'-'", "'!'", "'*'", "'/'", "'%'", "'+'", "'..'", "'<.'", "'.<'", "'<<'", 
		"RANGEOPUNARY", "'++'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", 
		"'&'", "'|'", "':='", "'('", "')'", "','", "':'", "'='", "';'", "'{'", 
		"'}'", "'.'", "'['", "']'", "BOOL", "TYPEPARAM", "CLASSNAME", "NAME", 
		"WS", "INT", "COMMENT", "STRING", "SINGLELINECOMMENT", "MULTILINECOMMENT", 
		"ERRORCHAR"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "FOR", "THING", "NOTHING", "MINUS", "NEGATE", "TIMES", 
		"DIVIDE", "MODULO", "PLUS", "STRICTSTRICTBINOP", "OPENSTRICTBINOP", "STRICTOPENBINOP", 
		"OPENOPENBINOP", "RANGEOPUNARY", "PLUSPLUS", "LANGLE", "RANGLE", "LTE", 
		"GTE", "EQ", "NE", "AND", "OR", "ASSIGN", "LPAREN", "RPAREN", "COMMA", 
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
		case 49: WS_action((RuleContext)_localctx, actionIndex); break;

		case 51: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 53: SINGLELINECOMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2:\u015e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\5\31\u00de\n\31\3\32\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\""+
		"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,"+
		"\3-\3-\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u0119\n/\3\60\3\60\3\61\3\61"+
		"\6\61\u011f\n\61\r\61\16\61\u0120\3\62\3\62\7\62\u0125\n\62\f\62\16\62"+
		"\u0128\13\62\3\63\6\63\u012b\n\63\r\63\16\63\u012c\3\63\3\63\3\64\3\64"+
		"\7\64\u0133\n\64\f\64\16\64\u0136\13\64\3\64\5\64\u0139\n\64\3\65\3\65"+
		"\5\65\u013d\n\65\3\65\3\65\3\66\3\66\7\66\u0143\n\66\f\66\16\66\u0146"+
		"\13\66\3\66\3\66\3\67\3\67\7\67\u014c\n\67\f\67\16\67\u014f\13\67\3\67"+
		"\3\67\38\38\38\78\u0156\n8\f8\168\u0159\138\38\38\39\39\2:\3\3\1\5\4\1"+
		"\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1"+
		"\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31"+
		"\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%"+
		"\1I&\1K\'\1M(\1O)\1Q*\1S+\1U,\1W-\1Y.\1[/\1]\60\1_\61\1a\62\1c\63\1e\64"+
		"\2g\65\1i\66\3k\67\1m8\4o9\1q:\1\3\2\f\3\2C\\\6\2\62;C\\aac|\3\2c|\5\2"+
		"\13\f\17\17\"\"\3\2\63;\3\2\62;\3\2\62\62\5\2\f\f\17\17$$\4\2\f\f\17\17"+
		"\4\2))bb\u0169\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\3s\3\2\2\2\5}\3\2\2\2\7\u0083"+
		"\3\2\2\2\t\u008b\3\2\2\2\13\u0091\3\2\2\2\r\u0095\3\2\2\2\17\u009b\3\2"+
		"\2\2\21\u009e\3\2\2\2\23\u00a3\3\2\2\2\25\u00a6\3\2\2\2\27\u00ad\3\2\2"+
		"\2\31\u00b1\3\2\2\2\33\u00b7\3\2\2\2\35\u00bf\3\2\2\2\37\u00c1\3\2\2\2"+
		"!\u00c3\3\2\2\2#\u00c5\3\2\2\2%\u00c7\3\2\2\2\'\u00c9\3\2\2\2)\u00cb\3"+
		"\2\2\2+\u00ce\3\2\2\2-\u00d1\3\2\2\2/\u00d4\3\2\2\2\61\u00dd\3\2\2\2\63"+
		"\u00df\3\2\2\2\65\u00e2\3\2\2\2\67\u00e4\3\2\2\29\u00e6\3\2\2\2;\u00e9"+
		"\3\2\2\2=\u00ec\3\2\2\2?\u00ef\3\2\2\2A\u00f2\3\2\2\2C\u00f4\3\2\2\2E"+
		"\u00f6\3\2\2\2G\u00f9\3\2\2\2I\u00fb\3\2\2\2K\u00fd\3\2\2\2M\u00ff\3\2"+
		"\2\2O\u0101\3\2\2\2Q\u0103\3\2\2\2S\u0105\3\2\2\2U\u0107\3\2\2\2W\u0109"+
		"\3\2\2\2Y\u010b\3\2\2\2[\u010d\3\2\2\2]\u0118\3\2\2\2_\u011a\3\2\2\2a"+
		"\u011c\3\2\2\2c\u0122\3\2\2\2e\u012a\3\2\2\2g\u0138\3\2\2\2i\u013c\3\2"+
		"\2\2k\u0140\3\2\2\2m\u0149\3\2\2\2o\u0152\3\2\2\2q\u015c\3\2\2\2st\7k"+
		"\2\2tu\7p\2\2uv\7v\2\2vw\7g\2\2wx\7t\2\2xy\7h\2\2yz\7c\2\2z{\7e\2\2{|"+
		"\7g\2\2|\4\3\2\2\2}~\7e\2\2~\177\7n\2\2\177\u0080\7c\2\2\u0080\u0081\7"+
		"u\2\2\u0081\u0082\7u\2\2\u0082\6\3\2\2\2\u0083\u0084\7g\2\2\u0084\u0085"+
		"\7z\2\2\u0085\u0086\7v\2\2\u0086\u0087\7g\2\2\u0087\u0088\7p\2\2\u0088"+
		"\u0089\7f\2\2\u0089\u008a\7u\2\2\u008a\b\3\2\2\2\u008b\u008c\7u\2\2\u008c"+
		"\u008d\7w\2\2\u008d\u008e\7r\2\2\u008e\u008f\7g\2\2\u008f\u0090\7t\2\2"+
		"\u0090\n\3\2\2\2\u0091\u0092\7h\2\2\u0092\u0093\7w\2\2\u0093\u0094\7p"+
		"\2\2\u0094\f\3\2\2\2\u0095\u0096\7y\2\2\u0096\u0097\7j\2\2\u0097\u0098"+
		"\7k\2\2\u0098\u0099\7n\2\2\u0099\u009a\7g\2\2\u009a\16\3\2\2\2\u009b\u009c"+
		"\7k\2\2\u009c\u009d\7h\2\2\u009d\20\3\2\2\2\u009e\u009f\7g\2\2\u009f\u00a0"+
		"\7n\2\2\u00a0\u00a1\7u\2\2\u00a1\u00a2\7g\2\2\u00a2\22\3\2\2\2\u00a3\u00a4"+
		"\7k\2\2\u00a4\u00a5\7p\2\2\u00a5\24\3\2\2\2\u00a6\u00a7\7t\2\2\u00a7\u00a8"+
		"\7g\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7w\2\2\u00aa\u00ab\7t\2\2\u00ab"+
		"\u00ac\7p\2\2\u00ac\26\3\2\2\2\u00ad\u00ae\7h\2\2\u00ae\u00af\7q\2\2\u00af"+
		"\u00b0\7t\2\2\u00b0\30\3\2\2\2\u00b1\u00b2\7V\2\2\u00b2\u00b3\7j\2\2\u00b3"+
		"\u00b4\7k\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7i\2\2\u00b6\32\3\2\2\2\u00b7"+
		"\u00b8\7P\2\2\u00b8\u00b9\7q\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7j\2\2"+
		"\u00bb\u00bc\7k\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7i\2\2\u00be\34\3\2"+
		"\2\2\u00bf\u00c0\7/\2\2\u00c0\36\3\2\2\2\u00c1\u00c2\7#\2\2\u00c2 \3\2"+
		"\2\2\u00c3\u00c4\7,\2\2\u00c4\"\3\2\2\2\u00c5\u00c6\7\61\2\2\u00c6$\3"+
		"\2\2\2\u00c7\u00c8\7\'\2\2\u00c8&\3\2\2\2\u00c9\u00ca\7-\2\2\u00ca(\3"+
		"\2\2\2\u00cb\u00cc\7\60\2\2\u00cc\u00cd\7\60\2\2\u00cd*\3\2\2\2\u00ce"+
		"\u00cf\7>\2\2\u00cf\u00d0\7\60\2\2\u00d0,\3\2\2\2\u00d1\u00d2\7\60\2\2"+
		"\u00d2\u00d3\7>\2\2\u00d3.\3\2\2\2\u00d4\u00d5\7>\2\2\u00d5\u00d6\7>\2"+
		"\2\u00d6\60\3\2\2\2\u00d7\u00d8\7\60\2\2\u00d8\u00d9\7\60\2\2\u00d9\u00de"+
		"\7\60\2\2\u00da\u00db\7>\2\2\u00db\u00dc\7\60\2\2\u00dc\u00de\7\60\2\2"+
		"\u00dd\u00d7\3\2\2\2\u00dd\u00da\3\2\2\2\u00de\62\3\2\2\2\u00df\u00e0"+
		"\7-\2\2\u00e0\u00e1\7-\2\2\u00e1\64\3\2\2\2\u00e2\u00e3\7>\2\2\u00e3\66"+
		"\3\2\2\2\u00e4\u00e5\7@\2\2\u00e58\3\2\2\2\u00e6\u00e7\7>\2\2\u00e7\u00e8"+
		"\7?\2\2\u00e8:\3\2\2\2\u00e9\u00ea\7@\2\2\u00ea\u00eb\7?\2\2\u00eb<\3"+
		"\2\2\2\u00ec\u00ed\7?\2\2\u00ed\u00ee\7?\2\2\u00ee>\3\2\2\2\u00ef\u00f0"+
		"\7#\2\2\u00f0\u00f1\7?\2\2\u00f1@\3\2\2\2\u00f2\u00f3\7(\2\2\u00f3B\3"+
		"\2\2\2\u00f4\u00f5\7~\2\2\u00f5D\3\2\2\2\u00f6\u00f7\7<\2\2\u00f7\u00f8"+
		"\7?\2\2\u00f8F\3\2\2\2\u00f9\u00fa\7*\2\2\u00faH\3\2\2\2\u00fb\u00fc\7"+
		"+\2\2\u00fcJ\3\2\2\2\u00fd\u00fe\7.\2\2\u00feL\3\2\2\2\u00ff\u0100\7<"+
		"\2\2\u0100N\3\2\2\2\u0101\u0102\7?\2\2\u0102P\3\2\2\2\u0103\u0104\7=\2"+
		"\2\u0104R\3\2\2\2\u0105\u0106\7}\2\2\u0106T\3\2\2\2\u0107\u0108\7\177"+
		"\2\2\u0108V\3\2\2\2\u0109\u010a\7\60\2\2\u010aX\3\2\2\2\u010b\u010c\7"+
		"]\2\2\u010cZ\3\2\2\2\u010d\u010e\7_\2\2\u010e\\\3\2\2\2\u010f\u0110\7"+
		"v\2\2\u0110\u0111\7t\2\2\u0111\u0112\7w\2\2\u0112\u0119\7g\2\2\u0113\u0114"+
		"\7h\2\2\u0114\u0115\7c\2\2\u0115\u0116\7n\2\2\u0116\u0117\7u\2\2\u0117"+
		"\u0119\7g\2\2\u0118\u010f\3\2\2\2\u0118\u0113\3\2\2\2\u0119^\3\2\2\2\u011a"+
		"\u011b\t\2\2\2\u011b`\3\2\2\2\u011c\u011e\t\2\2\2\u011d\u011f\t\3\2\2"+
		"\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121"+
		"\3\2\2\2\u0121b\3\2\2\2\u0122\u0126\t\4\2\2\u0123\u0125\t\3\2\2\u0124"+
		"\u0123\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127d\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012b\t\5\2\2\u012a\u0129"+
		"\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012f\b\63\2\2\u012ff\3\2\2\2\u0130\u0134\t\6\2\2"+
		"\u0131\u0133\t\7\2\2\u0132\u0131\3\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132"+
		"\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0139\3\2\2\2\u0136\u0134\3\2\2\2\u0137"+
		"\u0139\t\b\2\2\u0138\u0130\3\2\2\2\u0138\u0137\3\2\2\2\u0139h\3\2\2\2"+
		"\u013a\u013d\5m\67\2\u013b\u013d\5o8\2\u013c\u013a\3\2\2\2\u013c\u013b"+
		"\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f\b\65\3\2\u013fj\3\2\2\2\u0140"+
		"\u0144\7$\2\2\u0141\u0143\n\t\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2"+
		"\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0147\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0147\u0148\7$\2\2\u0148l\3\2\2\2\u0149\u014d\7%\2\2\u014a"+
		"\u014c\n\n\2\2\u014b\u014a\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2"+
		"\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150"+
		"\u0151\b\67\4\2\u0151n\3\2\2\2\u0152\u0157\7b\2\2\u0153\u0156\5o8\2\u0154"+
		"\u0156\n\13\2\2\u0155\u0153\3\2\2\2\u0155\u0154\3\2\2\2\u0156\u0159\3"+
		"\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015a\3\2\2\2\u0159"+
		"\u0157\3\2\2\2\u015a\u015b\7)\2\2\u015bp\3\2\2\2\u015c\u015d\13\2\2\2"+
		"\u015dr\3\2\2\2\17\2\u00dd\u0118\u0120\u0126\u012c\u0134\u0138\u013c\u0144"+
		"\u014d\u0155\u0157";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}