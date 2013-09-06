// Generated from CubexParser.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CubexParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		RANGEOP=25, MODULO=11, CLASS=2, LT=14, RBRACE=34, WHILE=6, MULTILINECOMMENT=44, 
		PLUSPLUS=26, LBRACE=33, ELSE=8, GTE=17, BOOL=40, NEGATE=22, INT=39, SEMICOLON=32, 
		MINUS=13, AND=20, SINGLELINECOMMENT=43, LTE=16, LPAREN=29, TYPE=36, IF=7, 
		COLON=27, RPAREN=30, NAME=37, WS=38, IN=9, COMMA=31, EQUAL=28, OR=21, 
		RETURN=10, GT=15, PLUS=12, INTERFACE=1, SUPER=4, EQ=18, COMMENT=41, DOT=35, 
		FUN=5, TIMES=23, EXTENDS=3, DIVIDE=24, STRING=42, NE=19;
	public static final String[] tokenNames = {
		"<INVALID>", "'interface'", "'class'", "'extends'", "'super'", "'fun'", 
		"'while'", "'if'", "'else'", "'in'", "'return'", "'%'", "'+'", "'-'", 
		"'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&'", "'|'", "'!'", "'*'", 
		"'/'", "RANGEOP", "'++'", "':'", "'='", "'('", "')'", "','", "';'", "'{'", 
		"'}'", "'.'", "TYPE", "NAME", "WS", "INT", "BOOL", "COMMENT", "STRING", 
		"SINGLELINECOMMENT", "MULTILINECOMMENT"
	};
	public static final int
		RULE_string = 0, RULE_comment = 1, RULE_type = 2, RULE_name = 3, RULE_bool = 4, 
		RULE_integer = 5, RULE_ws = 6, RULE_keyword = 7, RULE_symbol = 8, RULE_other = 9, 
		RULE_file = 10;
	public static final String[] ruleNames = {
		"string", "comment", "type", "name", "bool", "integer", "ws", "keyword", 
		"symbol", "other", "file"
	};

	@Override
	public String getGrammarFileName() { return "CubexParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public CubexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CubexParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); match(STRING);
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

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(CubexParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24); match(COMMENT);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(CubexParser.TYPE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26); match(TYPE);
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

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(CubexParser.NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); match(NAME);
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

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(CubexParser.BOOL, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bool);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(BOOL);
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

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CubexParser.INT, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_integer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); match(INT);
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

	public static class WsContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(CubexParser.WS, 0); }
		public WsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ws; }
	}

	public final WsContext ws() throws RecognitionException {
		WsContext _localctx = new WsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ws);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(WS);
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

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(CubexParser.IN, 0); }
		public TerminalNode WHILE() { return getToken(CubexParser.WHILE, 0); }
		public TerminalNode IF() { return getToken(CubexParser.IF, 0); }
		public TerminalNode SUPER() { return getToken(CubexParser.SUPER, 0); }
		public TerminalNode ELSE() { return getToken(CubexParser.ELSE, 0); }
		public TerminalNode FUN() { return getToken(CubexParser.FUN, 0); }
		public TerminalNode RETURN() { return getToken(CubexParser.RETURN, 0); }
		public TerminalNode INTERFACE() { return getToken(CubexParser.INTERFACE, 0); }
		public TerminalNode EXTENDS() { return getToken(CubexParser.EXTENDS, 0); }
		public TerminalNode CLASS() { return getToken(CubexParser.CLASS, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTERFACE) | (1L << CLASS) | (1L << EXTENDS) | (1L << SUPER) | (1L << FUN) | (1L << WHILE) | (1L << IF) | (1L << ELSE) | (1L << IN) | (1L << RETURN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class SymbolContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(CubexParser.LBRACE, 0); }
		public TerminalNode AND() { return getToken(CubexParser.AND, 0); }
		public TerminalNode PLUSPLUS() { return getToken(CubexParser.PLUSPLUS, 0); }
		public TerminalNode DIVIDE() { return getToken(CubexParser.DIVIDE, 0); }
		public TerminalNode EQ() { return getToken(CubexParser.EQ, 0); }
		public TerminalNode PLUS() { return getToken(CubexParser.PLUS, 0); }
		public TerminalNode TIMES() { return getToken(CubexParser.TIMES, 0); }
		public TerminalNode GTE() { return getToken(CubexParser.GTE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CubexParser.SEMICOLON, 0); }
		public TerminalNode MODULO() { return getToken(CubexParser.MODULO, 0); }
		public TerminalNode OR() { return getToken(CubexParser.OR, 0); }
		public TerminalNode LTE() { return getToken(CubexParser.LTE, 0); }
		public TerminalNode MINUS() { return getToken(CubexParser.MINUS, 0); }
		public TerminalNode EQUAL() { return getToken(CubexParser.EQUAL, 0); }
		public TerminalNode LPAREN() { return getToken(CubexParser.LPAREN, 0); }
		public TerminalNode RBRACE() { return getToken(CubexParser.RBRACE, 0); }
		public TerminalNode DOT() { return getToken(CubexParser.DOT, 0); }
		public TerminalNode LT() { return getToken(CubexParser.LT, 0); }
		public TerminalNode COMMA() { return getToken(CubexParser.COMMA, 0); }
		public TerminalNode RPAREN() { return getToken(CubexParser.RPAREN, 0); }
		public TerminalNode NE() { return getToken(CubexParser.NE, 0); }
		public TerminalNode RANGEOP() { return getToken(CubexParser.RANGEOP, 0); }
		public TerminalNode GT() { return getToken(CubexParser.GT, 0); }
		public TerminalNode COLON() { return getToken(CubexParser.COLON, 0); }
		public TerminalNode NEGATE() { return getToken(CubexParser.NEGATE, 0); }
		public SymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbol; }
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_symbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MODULO) | (1L << PLUS) | (1L << MINUS) | (1L << LT) | (1L << GT) | (1L << LTE) | (1L << GTE) | (1L << EQ) | (1L << NE) | (1L << AND) | (1L << OR) | (1L << NEGATE) | (1L << TIMES) | (1L << DIVIDE) | (1L << RANGEOP) | (1L << PLUSPLUS) | (1L << COLON) | (1L << EQUAL) | (1L << LPAREN) | (1L << RPAREN) | (1L << COMMA) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << RBRACE) | (1L << DOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class OtherContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public OtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_other; }
	}

	public final OtherContext other() throws RecognitionException {
		OtherContext _localctx = new OtherContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_other);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			switch (_input.LA(1)) {
			case INTERFACE:
			case CLASS:
			case EXTENDS:
			case SUPER:
			case FUN:
			case WHILE:
			case IF:
			case ELSE:
			case IN:
			case RETURN:
				{
				setState(40); keyword();
				}
				break;
			case MODULO:
			case PLUS:
			case MINUS:
			case LT:
			case GT:
			case LTE:
			case GTE:
			case EQ:
			case NE:
			case AND:
			case OR:
			case NEGATE:
			case TIMES:
			case DIVIDE:
			case RANGEOP:
			case PLUSPLUS:
			case COLON:
			case EQUAL:
			case LPAREN:
			case RPAREN:
			case COMMA:
			case SEMICOLON:
			case LBRACE:
			case RBRACE:
			case DOT:
				{
				setState(41); symbol();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class FileContext extends ParserRuleContext {
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public List<BoolContext> bool() {
			return getRuleContexts(BoolContext.class);
		}
		public BoolContext bool(int i) {
			return getRuleContext(BoolContext.class,i);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public IntegerContext integer(int i) {
			return getRuleContext(IntegerContext.class,i);
		}
		public List<IntegerContext> integer() {
			return getRuleContexts(IntegerContext.class);
		}
		public OtherContext other(int i) {
			return getRuleContext(OtherContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public List<OtherContext> other() {
			return getRuleContexts(OtherContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTERFACE) | (1L << CLASS) | (1L << EXTENDS) | (1L << SUPER) | (1L << FUN) | (1L << WHILE) | (1L << IF) | (1L << ELSE) | (1L << IN) | (1L << RETURN) | (1L << MODULO) | (1L << PLUS) | (1L << MINUS) | (1L << LT) | (1L << GT) | (1L << LTE) | (1L << GTE) | (1L << EQ) | (1L << NE) | (1L << AND) | (1L << OR) | (1L << NEGATE) | (1L << TIMES) | (1L << DIVIDE) | (1L << RANGEOP) | (1L << PLUSPLUS) | (1L << COLON) | (1L << EQUAL) | (1L << LPAREN) | (1L << RPAREN) | (1L << COMMA) | (1L << SEMICOLON) | (1L << LBRACE) | (1L << RBRACE) | (1L << DOT) | (1L << TYPE) | (1L << NAME) | (1L << WS) | (1L << INT) | (1L << BOOL) | (1L << COMMENT) | (1L << STRING))) != 0)) {
				{
				setState(52);
				switch (_input.LA(1)) {
				case STRING:
					{
					setState(44); string();
					}
					break;
				case COMMENT:
					{
					setState(45); comment();
					}
					break;
				case TYPE:
					{
					setState(46); type();
					}
					break;
				case NAME:
					{
					setState(47); name();
					}
					break;
				case BOOL:
					{
					setState(48); bool();
					}
					break;
				case INT:
					{
					setState(49); integer();
					}
					break;
				case WS:
					{
					setState(50); ws();
					}
					break;
				case INTERFACE:
				case CLASS:
				case EXTENDS:
				case SUPER:
				case FUN:
				case WHILE:
				case IF:
				case ELSE:
				case IN:
				case RETURN:
				case MODULO:
				case PLUS:
				case MINUS:
				case LT:
				case GT:
				case LTE:
				case GTE:
				case EQ:
				case NE:
				case AND:
				case OR:
				case NEGATE:
				case TIMES:
				case DIVIDE:
				case RANGEOP:
				case PLUSPLUS:
				case COLON:
				case EQUAL:
				case LPAREN:
				case RPAREN:
				case COMMA:
				case SEMICOLON:
				case LBRACE:
				case RBRACE:
				case DOT:
					{
					setState(51); other();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(56);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3.<\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\5\13-\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\67\n\f"+
		"\f\f\16\f:\13\f\3\f\2\r\2\4\6\b\n\f\16\20\22\24\26\2\4\3\2\3\f\3\2\r%"+
		"9\2\30\3\2\2\2\4\32\3\2\2\2\6\34\3\2\2\2\b\36\3\2\2\2\n \3\2\2\2\f\"\3"+
		"\2\2\2\16$\3\2\2\2\20&\3\2\2\2\22(\3\2\2\2\24,\3\2\2\2\268\3\2\2\2\30"+
		"\31\7,\2\2\31\3\3\2\2\2\32\33\7+\2\2\33\5\3\2\2\2\34\35\7&\2\2\35\7\3"+
		"\2\2\2\36\37\7\'\2\2\37\t\3\2\2\2 !\7*\2\2!\13\3\2\2\2\"#\7)\2\2#\r\3"+
		"\2\2\2$%\7(\2\2%\17\3\2\2\2&\'\t\2\2\2\'\21\3\2\2\2()\t\3\2\2)\23\3\2"+
		"\2\2*-\5\20\t\2+-\5\22\n\2,*\3\2\2\2,+\3\2\2\2-\25\3\2\2\2.\67\5\2\2\2"+
		"/\67\5\4\3\2\60\67\5\6\4\2\61\67\5\b\5\2\62\67\5\n\6\2\63\67\5\f\7\2\64"+
		"\67\5\16\b\2\65\67\5\24\13\2\66.\3\2\2\2\66/\3\2\2\2\66\60\3\2\2\2\66"+
		"\61\3\2\2\2\66\62\3\2\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67"+
		":\3\2\2\28\66\3\2\2\289\3\2\2\29\27\3\2\2\2:8\3\2\2\2\5,\668";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}