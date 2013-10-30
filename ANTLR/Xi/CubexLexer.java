// Generated from CubexLexer.g4 by ANTLR 4.1
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
		TYPE=1, NAME=2, WS=3, INT=4, BOOL=5, COMMENT=6, STRING=7;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", "STRING"
	};
	public static final String[] ruleNames = {
		"TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", "STRING"
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
		case 2: WS_action((RuleContext)_localctx, actionIndex); break;

		case 5: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\tN\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\7\2\24\n\2\f\2"+
		"\16\2\27\13\2\3\3\3\3\7\3\33\n\3\f\3\16\3\36\13\3\3\4\6\4!\n\4\r\4\16"+
		"\4\"\3\4\3\4\3\5\5\5(\n\5\3\5\3\5\7\5,\n\5\f\5\16\5/\13\5\3\5\5\5\62\n"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6=\n\6\3\7\3\7\7\7A\n\7\f\7\16"+
		"\7D\13\7\3\7\3\7\3\7\3\7\3\b\6\bK\n\b\r\b\16\bL\3B\t\3\3\1\5\4\1\7\5\2"+
		"\t\6\1\13\7\1\r\b\3\17\t\1\3\2\r\3\2C\\\6\2\62;C\\aac|\3\2c|\5\2\13\f"+
		"\17\17\"\"\3\2//\3\2\63;\3\2\62;\3\2\62\62\3\2%%\4\2\f\f\17\17\5\2\13"+
		"\f\17\17``V\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\3\21\3\2\2\2\5\30\3\2\2\2\7 \3\2\2\2\t\'\3"+
		"\2\2\2\13<\3\2\2\2\r>\3\2\2\2\17J\3\2\2\2\21\25\t\2\2\2\22\24\t\3\2\2"+
		"\23\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\4\3\2\2\2"+
		"\27\25\3\2\2\2\30\34\t\4\2\2\31\33\t\3\2\2\32\31\3\2\2\2\33\36\3\2\2\2"+
		"\34\32\3\2\2\2\34\35\3\2\2\2\35\6\3\2\2\2\36\34\3\2\2\2\37!\t\5\2\2 \37"+
		"\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#$\3\2\2\2$%\b\4\2\2%\b\3\2\2"+
		"\2&(\t\6\2\2\'&\3\2\2\2\'(\3\2\2\2(\61\3\2\2\2)-\t\7\2\2*,\t\b\2\2+*\3"+
		"\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\62\3\2\2\2/-\3\2\2\2\60\62\t\t\2"+
		"\2\61)\3\2\2\2\61\60\3\2\2\2\62\n\3\2\2\2\63\64\7v\2\2\64\65\7t\2\2\65"+
		"\66\7w\2\2\66=\7g\2\2\678\7h\2\289\7c\2\29:\7n\2\2:;\7u\2\2;=\7g\2\2<"+
		"\63\3\2\2\2<\67\3\2\2\2=\f\3\2\2\2>B\t\n\2\2?A\13\2\2\2@?\3\2\2\2AD\3"+
		"\2\2\2BC\3\2\2\2B@\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\t\13\2\2FG\3\2\2\2GH"+
		"\b\7\3\2H\16\3\2\2\2IK\t\f\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2"+
		"M\20\3\2\2\2\f\2\25\34\"\'-\61<BL";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}