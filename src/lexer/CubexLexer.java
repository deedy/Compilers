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
		LBRACE=33, RBRACE=34, TYPE=35, NAME=36, WS=37, INT=38, BOOL=39, COMMENT=40, 
		STRING=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'interface'", "'class'", "'extends'", "'super'", "'fun'", "'while'", 
		"'if'", "'else'", "'in'", "'return'", "'%'", "'+'", "'-'", "'<'", "'>'", 
		"'<='", "'>='", "'=='", "'!='", "'&'", "'|'", "'!'", "'*'", "'/'", "RANGEOP", 
		"'++'", "':'", "'='", "'('", "')'", "','", "';'", "'{'", "'}'", "TYPE", 
		"NAME", "WS", "INT", "BOOL", "COMMENT", "STRING"
	};
	public static final String[] ruleNames = {
		"INTERFACE", "CLASS", "EXTENDS", "SUPER", "FUN", "WHILE", "IF", "ELSE", 
		"IN", "RETURN", "MODULO", "PLUS", "MINUS", "LT", "GT", "LTE", "GTE", "EQ", 
		"NE", "AND", "OR", "NEGATE", "TIMES", "DIVIDE", "RANGEOP", "PLUSPLUS", 
		"COLON", "EQUAL", "LPAREN", "RPAREN", "COMMA", "SEMICOLON", "LBRACE", 
		"RBRACE", "TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", "STRING"
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
		case 36: WS_action((RuleContext)_localctx, actionIndex); break;

		case 39: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4+\u0110\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4"+
		"(\t(\4)\t)\4*\t*\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00be"+
		"\n\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!"+
		"\3!\3\"\3\"\3#\3#\3$\3$\7$\u00d5\n$\f$\16$\u00d8\13$\3%\3%\7%\u00dc\n"+
		"%\f%\16%\u00df\13%\3&\6&\u00e2\n&\r&\16&\u00e3\3&\3&\3\'\3\'\7\'\u00ea"+
		"\n\'\f\'\16\'\u00ed\13\'\3\'\5\'\u00f0\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3("+
		"\5(\u00fb\n(\3)\3)\7)\u00ff\n)\f)\16)\u0102\13)\3)\3)\3)\3)\3*\3*\7*\u010a"+
		"\n*\f*\16*\u010d\13*\3*\3*\3\u0100+\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b"+
		"\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1"+
		"!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34"+
		"\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\2M(\1O)\1Q*\3S"+
		"+\1\3\2\17\3C\\\6\62;C\\aac|\3c|\6\62;C\\aac|\5\13\f\17\17\"\"\3\63;\3"+
		"\62;\3\62\62\3%%\4\f\f\17\17\3$$\4$$``\3$$\u011c\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\3U\3\2\2\2\5_\3\2\2\2\7"+
		"e\3\2\2\2\tm\3\2\2\2\13s\3\2\2\2\rw\3\2\2\2\17}\3\2\2\2\21\u0080\3\2\2"+
		"\2\23\u0085\3\2\2\2\25\u0088\3\2\2\2\27\u008f\3\2\2\2\31\u0091\3\2\2\2"+
		"\33\u0093\3\2\2\2\35\u0095\3\2\2\2\37\u0097\3\2\2\2!\u0099\3\2\2\2#\u009c"+
		"\3\2\2\2%\u009f\3\2\2\2\'\u00a2\3\2\2\2)\u00a5\3\2\2\2+\u00a7\3\2\2\2"+
		"-\u00a9\3\2\2\2/\u00ab\3\2\2\2\61\u00ad\3\2\2\2\63\u00bd\3\2\2\2\65\u00bf"+
		"\3\2\2\2\67\u00c2\3\2\2\29\u00c4\3\2\2\2;\u00c6\3\2\2\2=\u00c8\3\2\2\2"+
		"?\u00ca\3\2\2\2A\u00cc\3\2\2\2C\u00ce\3\2\2\2E\u00d0\3\2\2\2G\u00d2\3"+
		"\2\2\2I\u00d9\3\2\2\2K\u00e1\3\2\2\2M\u00ef\3\2\2\2O\u00fa\3\2\2\2Q\u00fc"+
		"\3\2\2\2S\u0107\3\2\2\2UV\7k\2\2VW\7p\2\2WX\7v\2\2XY\7g\2\2YZ\7t\2\2Z"+
		"[\7h\2\2[\\\7c\2\2\\]\7e\2\2]^\7g\2\2^\4\3\2\2\2_`\7e\2\2`a\7n\2\2ab\7"+
		"c\2\2bc\7u\2\2cd\7u\2\2d\6\3\2\2\2ef\7g\2\2fg\7z\2\2gh\7v\2\2hi\7g\2\2"+
		"ij\7p\2\2jk\7f\2\2kl\7u\2\2l\b\3\2\2\2mn\7u\2\2no\7w\2\2op\7r\2\2pq\7"+
		"g\2\2qr\7t\2\2r\n\3\2\2\2st\7h\2\2tu\7w\2\2uv\7p\2\2v\f\3\2\2\2wx\7y\2"+
		"\2xy\7j\2\2yz\7k\2\2z{\7n\2\2{|\7g\2\2|\16\3\2\2\2}~\7k\2\2~\177\7h\2"+
		"\2\177\20\3\2\2\2\u0080\u0081\7g\2\2\u0081\u0082\7n\2\2\u0082\u0083\7"+
		"u\2\2\u0083\u0084\7g\2\2\u0084\22\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087"+
		"\7p\2\2\u0087\24\3\2\2\2\u0088\u0089\7t\2\2\u0089\u008a\7g\2\2\u008a\u008b"+
		"\7v\2\2\u008b\u008c\7w\2\2\u008c\u008d\7t\2\2\u008d\u008e\7p\2\2\u008e"+
		"\26\3\2\2\2\u008f\u0090\7\'\2\2\u0090\30\3\2\2\2\u0091\u0092\7-\2\2\u0092"+
		"\32\3\2\2\2\u0093\u0094\7/\2\2\u0094\34\3\2\2\2\u0095\u0096\7>\2\2\u0096"+
		"\36\3\2\2\2\u0097\u0098\7@\2\2\u0098 \3\2\2\2\u0099\u009a\7>\2\2\u009a"+
		"\u009b\7?\2\2\u009b\"\3\2\2\2\u009c\u009d\7@\2\2\u009d\u009e\7?\2\2\u009e"+
		"$\3\2\2\2\u009f\u00a0\7?\2\2\u00a0\u00a1\7?\2\2\u00a1&\3\2\2\2\u00a2\u00a3"+
		"\7#\2\2\u00a3\u00a4\7?\2\2\u00a4(\3\2\2\2\u00a5\u00a6\7(\2\2\u00a6*\3"+
		"\2\2\2\u00a7\u00a8\7~\2\2\u00a8,\3\2\2\2\u00a9\u00aa\7#\2\2\u00aa.\3\2"+
		"\2\2\u00ab\u00ac\7,\2\2\u00ac\60\3\2\2\2\u00ad\u00ae\7\61\2\2\u00ae\62"+
		"\3\2\2\2\u00af\u00b0\7\60\2\2\u00b0\u00be\7\60\2\2\u00b1\u00b2\7>\2\2"+
		"\u00b2\u00be\7\60\2\2\u00b3\u00b4\7\60\2\2\u00b4\u00be\7>\2\2\u00b5\u00b6"+
		"\7>\2\2\u00b6\u00be\7>\2\2\u00b7\u00b8\7\60\2\2\u00b8\u00b9\7\60\2\2\u00b9"+
		"\u00be\7\60\2\2\u00ba\u00bb\7>\2\2\u00bb\u00bc\7\60\2\2\u00bc\u00be\7"+
		"\60\2\2\u00bd\u00af\3\2\2\2\u00bd\u00b1\3\2\2\2\u00bd\u00b3\3\2\2\2\u00bd"+
		"\u00b5\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd\u00ba\3\2\2\2\u00be\64\3\2\2"+
		"\2\u00bf\u00c0\7-\2\2\u00c0\u00c1\7-\2\2\u00c1\66\3\2\2\2\u00c2\u00c3"+
		"\7<\2\2\u00c38\3\2\2\2\u00c4\u00c5\7?\2\2\u00c5:\3\2\2\2\u00c6\u00c7\7"+
		"*\2\2\u00c7<\3\2\2\2\u00c8\u00c9\7+\2\2\u00c9>\3\2\2\2\u00ca\u00cb\7."+
		"\2\2\u00cb@\3\2\2\2\u00cc\u00cd\7=\2\2\u00cdB\3\2\2\2\u00ce\u00cf\7}\2"+
		"\2\u00cfD\3\2\2\2\u00d0\u00d1\7\177\2\2\u00d1F\3\2\2\2\u00d2\u00d6\t\2"+
		"\2\2\u00d3\u00d5\t\3\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7H\3\2\2\2\u00d8\u00d6\3\2\2\2"+
		"\u00d9\u00dd\t\4\2\2\u00da\u00dc\t\5\2\2\u00db\u00da\3\2\2\2\u00dc\u00df"+
		"\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00deJ\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00e0\u00e2\t\6\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\b&\2\2\u00e6L\3\2\2\2\u00e7\u00eb\t\7\2\2\u00e8\u00ea\t\b\2\2\u00e9"+
		"\u00e8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2"+
		"\2\2\u00ec\u00f0\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f0\t\t\2\2\u00ef"+
		"\u00e7\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0N\3\2\2\2\u00f1\u00f2\7v\2\2\u00f2"+
		"\u00f3\7t\2\2\u00f3\u00f4\7w\2\2\u00f4\u00fb\7g\2\2\u00f5\u00f6\7h\2\2"+
		"\u00f6\u00f7\7c\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9\7u\2\2\u00f9\u00fb"+
		"\7g\2\2\u00fa\u00f1\3\2\2\2\u00fa\u00f5\3\2\2\2\u00fbP\3\2\2\2\u00fc\u0100"+
		"\t\n\2\2\u00fd\u00ff\13\2\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2"+
		"\u0100\u0101\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0100"+
		"\3\2\2\2\u0103\u0104\t\13\2\2\u0104\u0105\3\2\2\2\u0105\u0106\b)\3\2\u0106"+
		"R\3\2\2\2\u0107\u010b\t\f\2\2\u0108\u010a\t\r\2\2\u0109\u0108\3\2\2\2"+
		"\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e"+
		"\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\t\16\2\2\u010fT\3\2\2\2\f\2\u00bd"+
		"\u00d6\u00dd\u00e3\u00eb\u00ef\u00fa\u0100\u010b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}