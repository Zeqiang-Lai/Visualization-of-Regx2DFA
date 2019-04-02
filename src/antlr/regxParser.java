package antlr;
// Generated from regx.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class regxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Or=1, Closure=2, LeftParen=3, RightParen=4, Concat=5, Identifier=6, Whitespace=7, 
		Newline=8;
	public static final int
		RULE_primaryExp = 0, RULE_closureExp = 1, RULE_concatExp = 2, RULE_orExp = 3, 
		RULE_expression = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"primaryExp", "closureExp", "concatExp", "orExp", "expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'|'", "'*'", "'('", "')'", "'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Or", "Closure", "LeftParen", "RightParen", "Concat", "Identifier", 
			"Whitespace", "Newline"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "regx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public regxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PrimaryExpContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(regxParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(regxParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(regxParser.RightParen, 0); }
		public PrimaryExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).enterPrimaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).exitPrimaryExp(this);
		}
	}

	public final PrimaryExpContext primaryExp() throws RecognitionException {
		PrimaryExpContext _localctx = new PrimaryExpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_primaryExp);
		try {
			setState(16);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				match(LeftParen);
				setState(12);
				expression();
				setState(13);
				match(RightParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
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

	public static class ClosureExpContext extends ParserRuleContext {
		public PrimaryExpContext primaryExp() {
			return getRuleContext(PrimaryExpContext.class,0);
		}
		public ClosureExpContext closureExp() {
			return getRuleContext(ClosureExpContext.class,0);
		}
		public TerminalNode Closure() { return getToken(regxParser.Closure, 0); }
		public ClosureExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closureExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).enterClosureExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).exitClosureExp(this);
		}
	}

	public final ClosureExpContext closureExp() throws RecognitionException {
		return closureExp(0);
	}

	private ClosureExpContext closureExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ClosureExpContext _localctx = new ClosureExpContext(_ctx, _parentState);
		ClosureExpContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_closureExp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(19);
			primaryExp();
			}
			_ctx.stop = _input.LT(-1);
			setState(25);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ClosureExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_closureExp);
					setState(21);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(22);
					match(Closure);
					}
					} 
				}
				setState(27);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ConcatExpContext extends ParserRuleContext {
		public ClosureExpContext closureExp() {
			return getRuleContext(ClosureExpContext.class,0);
		}
		public ConcatExpContext concatExp() {
			return getRuleContext(ConcatExpContext.class,0);
		}
		public TerminalNode Concat() { return getToken(regxParser.Concat, 0); }
		public ConcatExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_concatExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).enterConcatExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).exitConcatExp(this);
		}
	}

	public final ConcatExpContext concatExp() throws RecognitionException {
		return concatExp(0);
	}

	private ConcatExpContext concatExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConcatExpContext _localctx = new ConcatExpContext(_ctx, _parentState);
		ConcatExpContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_concatExp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(29);
			closureExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConcatExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_concatExp);
					setState(31);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(32);
					match(Concat);
					setState(33);
					closureExp(0);
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OrExpContext extends ParserRuleContext {
		public ConcatExpContext concatExp() {
			return getRuleContext(ConcatExpContext.class,0);
		}
		public OrExpContext orExp() {
			return getRuleContext(OrExpContext.class,0);
		}
		public TerminalNode Or() { return getToken(regxParser.Or, 0); }
		public OrExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).enterOrExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).exitOrExp(this);
		}
	}

	public final OrExpContext orExp() throws RecognitionException {
		return orExp(0);
	}

	private OrExpContext orExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		OrExpContext _localctx = new OrExpContext(_ctx, _parentState);
		OrExpContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_orExp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(40);
			concatExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(47);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new OrExpContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_orExp);
					setState(42);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(43);
					match(Or);
					setState(44);
					concatExp(0);
					}
					} 
				}
				setState(49);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public OrExpContext orExp() {
			return getRuleContext(OrExpContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof regxListener ) ((regxListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			orExp(0);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return closureExp_sempred((ClosureExpContext)_localctx, predIndex);
		case 2:
			return concatExp_sempred((ConcatExpContext)_localctx, predIndex);
		case 3:
			return orExp_sempred((OrExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean closureExp_sempred(ClosureExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean concatExp_sempred(ConcatExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean orExp_sempred(OrExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n\67\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\5\2\23\n\2\3\3\3"+
		"\3\3\3\3\3\3\3\7\3\32\n\3\f\3\16\3\35\13\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"%\n\4\f\4\16\4(\13\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5\60\n\5\f\5\16\5\63\13"+
		"\5\3\6\3\6\3\6\2\5\4\6\b\7\2\4\6\b\n\2\2\2\66\2\22\3\2\2\2\4\24\3\2\2"+
		"\2\6\36\3\2\2\2\b)\3\2\2\2\n\64\3\2\2\2\f\23\7\b\2\2\r\16\7\5\2\2\16\17"+
		"\5\n\6\2\17\20\7\6\2\2\20\23\3\2\2\2\21\23\3\2\2\2\22\f\3\2\2\2\22\r\3"+
		"\2\2\2\22\21\3\2\2\2\23\3\3\2\2\2\24\25\b\3\1\2\25\26\5\2\2\2\26\33\3"+
		"\2\2\2\27\30\f\3\2\2\30\32\7\4\2\2\31\27\3\2\2\2\32\35\3\2\2\2\33\31\3"+
		"\2\2\2\33\34\3\2\2\2\34\5\3\2\2\2\35\33\3\2\2\2\36\37\b\4\1\2\37 \5\4"+
		"\3\2 &\3\2\2\2!\"\f\3\2\2\"#\7\7\2\2#%\5\4\3\2$!\3\2\2\2%(\3\2\2\2&$\3"+
		"\2\2\2&\'\3\2\2\2\'\7\3\2\2\2(&\3\2\2\2)*\b\5\1\2*+\5\6\4\2+\61\3\2\2"+
		"\2,-\f\3\2\2-.\7\3\2\2.\60\5\6\4\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2"+
		"\61\62\3\2\2\2\62\t\3\2\2\2\63\61\3\2\2\2\64\65\5\b\5\2\65\13\3\2\2\2"+
		"\6\22\33&\61";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}