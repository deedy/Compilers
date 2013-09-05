// Generated from XiParser.g4 by ANTLR 4.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XiParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INTEGER=13, STAR=24, PERCENT=26, RBRACE=23, DASH=28, WHILE=5, LANGLE=29, 
		ELSE=7, LBRACE=22, BOOL=2, AMPERSAND=32, BANG=31, INT=3, SEMICOLON=21, 
		ID=12, TRUE=10, BREAK=9, LENGTH=8, LPAREN=18, IF=6, LBRACKET=14, COLON=16, 
		RPAREN=19, WS=34, SLASH=25, COMMA=20, EQUAL=17, RETURN=4, PIPE=33, PLUS=27, 
		RANGLE=30, RBRACKET=15, COMMENT=35, FALSE=11, STRING=1;
	public static final String[] tokenNames = {
		"<INVALID>", "STRING", "'bool'", "'int'", "'return'", "'while'", "'if'", 
		"'else'", "'length'", "'break'", "'true'", "'false'", "ID", "INTEGER", 
		"'['", "']'", "':'", "'='", "'('", "')'", "','", "';'", "'{'", "'}'", 
		"'*'", "'/'", "'%'", "'+'", "'-'", "'<'", "'>'", "'!'", "'&'", "'|'", 
		"WS", "COMMENT"
	};
	public static final int
		RULE_file = 0;
	public static final String[] ruleNames = {
		"file"
	};

	@Override
	public String getGrammarFileName() { return "XiParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public XiParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(XiParser.STRING, i);
		}
		public List<TerminalNode> STAR() { return getTokens(XiParser.STAR); }
		public List<TerminalNode> WHILE() { return getTokens(XiParser.WHILE); }
		public TerminalNode WHILE(int i) {
			return getToken(XiParser.WHILE, i);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(XiParser.EQUAL, i);
		}
		public List<TerminalNode> LANGLE() { return getTokens(XiParser.LANGLE); }
		public List<TerminalNode> LBRACE() { return getTokens(XiParser.LBRACE); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(XiParser.SEMICOLON, i);
		}
		public TerminalNode LPAREN(int i) {
			return getToken(XiParser.LPAREN, i);
		}
		public TerminalNode AMPERSAND(int i) {
			return getToken(XiParser.AMPERSAND, i);
		}
		public List<TerminalNode> ID() { return getTokens(XiParser.ID); }
		public List<TerminalNode> BREAK() { return getTokens(XiParser.BREAK); }
		public List<TerminalNode> LENGTH() { return getTokens(XiParser.LENGTH); }
		public List<TerminalNode> LPAREN() { return getTokens(XiParser.LPAREN); }
		public TerminalNode RANGLE(int i) {
			return getToken(XiParser.RANGLE, i);
		}
		public TerminalNode FALSE(int i) {
			return getToken(XiParser.FALSE, i);
		}
		public TerminalNode TRUE(int i) {
			return getToken(XiParser.TRUE, i);
		}
		public List<TerminalNode> IF() { return getTokens(XiParser.IF); }
		public List<TerminalNode> LBRACKET() { return getTokens(XiParser.LBRACKET); }
		public List<TerminalNode> RPAREN() { return getTokens(XiParser.RPAREN); }
		public TerminalNode BREAK(int i) {
			return getToken(XiParser.BREAK, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(XiParser.SLASH); }
		public List<TerminalNode> COMMA() { return getTokens(XiParser.COMMA); }
		public List<TerminalNode> EQUAL() { return getTokens(XiParser.EQUAL); }
		public List<TerminalNode> RETURN() { return getTokens(XiParser.RETURN); }
		public TerminalNode STAR(int i) {
			return getToken(XiParser.STAR, i);
		}
		public List<TerminalNode> PIPE() { return getTokens(XiParser.PIPE); }
		public List<TerminalNode> PLUS() { return getTokens(XiParser.PLUS); }
		public TerminalNode DASH(int i) {
			return getToken(XiParser.DASH, i);
		}
		public List<TerminalNode> RANGLE() { return getTokens(XiParser.RANGLE); }
		public List<TerminalNode> RBRACKET() { return getTokens(XiParser.RBRACKET); }
		public TerminalNode PLUS(int i) {
			return getToken(XiParser.PLUS, i);
		}
		public TerminalNode INTEGER(int i) {
			return getToken(XiParser.INTEGER, i);
		}
		public TerminalNode COLON(int i) {
			return getToken(XiParser.COLON, i);
		}
		public TerminalNode LENGTH(int i) {
			return getToken(XiParser.LENGTH, i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(XiParser.INTEGER); }
		public TerminalNode ID(int i) {
			return getToken(XiParser.ID, i);
		}
		public TerminalNode ELSE(int i) {
			return getToken(XiParser.ELSE, i);
		}
		public TerminalNode IF(int i) {
			return getToken(XiParser.IF, i);
		}
		public TerminalNode BANG(int i) {
			return getToken(XiParser.BANG, i);
		}
		public TerminalNode LANGLE(int i) {
			return getToken(XiParser.LANGLE, i);
		}
		public List<TerminalNode> PERCENT() { return getTokens(XiParser.PERCENT); }
		public List<TerminalNode> RBRACE() { return getTokens(XiParser.RBRACE); }
		public List<TerminalNode> DASH() { return getTokens(XiParser.DASH); }
		public TerminalNode BOOL(int i) {
			return getToken(XiParser.BOOL, i);
		}
		public TerminalNode INT(int i) {
			return getToken(XiParser.INT, i);
		}
		public List<TerminalNode> ELSE() { return getTokens(XiParser.ELSE); }
		public TerminalNode RETURN(int i) {
			return getToken(XiParser.RETURN, i);
		}
		public TerminalNode LBRACKET(int i) {
			return getToken(XiParser.LBRACKET, i);
		}
		public List<TerminalNode> BOOL() { return getTokens(XiParser.BOOL); }
		public List<TerminalNode> AMPERSAND() { return getTokens(XiParser.AMPERSAND); }
		public List<TerminalNode> BANG() { return getTokens(XiParser.BANG); }
		public List<TerminalNode> INT() { return getTokens(XiParser.INT); }
		public TerminalNode COMMA(int i) {
			return getToken(XiParser.COMMA, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(XiParser.SEMICOLON); }
		public TerminalNode SLASH(int i) {
			return getToken(XiParser.SLASH, i);
		}
		public List<TerminalNode> TRUE() { return getTokens(XiParser.TRUE); }
		public List<TerminalNode> COLON() { return getTokens(XiParser.COLON); }
		public TerminalNode RPAREN(int i) {
			return getToken(XiParser.RPAREN, i);
		}
		public TerminalNode LBRACE(int i) {
			return getToken(XiParser.LBRACE, i);
		}
		public TerminalNode PERCENT(int i) {
			return getToken(XiParser.PERCENT, i);
		}
		public TerminalNode RBRACE(int i) {
			return getToken(XiParser.RBRACE, i);
		}
		public TerminalNode PIPE(int i) {
			return getToken(XiParser.PIPE, i);
		}
		public List<TerminalNode> FALSE() { return getTokens(XiParser.FALSE); }
		public TerminalNode RBRACKET(int i) {
			return getToken(XiParser.RBRACKET, i);
		}
		public List<TerminalNode> STRING() { return getTokens(XiParser.STRING); }
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(5);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << BOOL) | (1L << INT) | (1L << RETURN) | (1L << WHILE) | (1L << IF) | (1L << ELSE) | (1L << LENGTH) | (1L << BREAK) | (1L << TRUE) | (1L << FALSE) | (1L << ID) | (1L << INTEGER) | (1L << LBRACKET) | (1L << RBRACKET) | (1L << COLON) | (1L << EQUAL) | (1L << LPAREN) | (1L << RPAREN) | (1L << COMMA) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << RBRACE) | (1L << STAR) | (1L << SLASH) | (1L << PERCENT) | (1L << PLUS) | (1L << DASH) | (1L << LANGLE) | (1L << RANGLE) | (1L << BANG) | (1L << AMPERSAND) | (1L << PIPE))) != 0)) {
				{
				{
				setState(2);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << BOOL) | (1L << INT) | (1L << RETURN) | (1L << WHILE) | (1L << IF) | (1L << ELSE) | (1L << LENGTH) | (1L << BREAK) | (1L << TRUE) | (1L << FALSE) | (1L << ID) | (1L << INTEGER) | (1L << LBRACKET) | (1L << RBRACKET) | (1L << COLON) | (1L << EQUAL) | (1L << LPAREN) | (1L << RPAREN) | (1L << COMMA) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << RBRACE) | (1L << STAR) | (1L << SLASH) | (1L << PERCENT) | (1L << PLUS) | (1L << DASH) | (1L << LANGLE) | (1L << RANGLE) | (1L << BANG) | (1L << AMPERSAND) | (1L << PIPE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(7);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3%\13\4\2\t\2\3\2\7\2\6\n\2\f\2\16\2\t\13\2\3\2\2\3\2\2\3\3\3#\n\2"+
		"\7\3\2\2\2\4\6\t\2\2\2\5\4\3\2\2\2\6\t\3\2\2\2\7\5\3\2\2\2\7\b\3\2\2\2"+
		"\b\3\3\2\2\2\t\7\3\2\2\2\3\7";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}