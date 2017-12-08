// Generated from Calculadora.g4 by ANTLR 4.7
 
	package ar.edu.utn.frba.dds.tp.antlr;

import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculadoraParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, NUMERO=5, SUM=6, MUL=7, RES=8, DIV=9, 
		NOMBRE=10, WS=11;
	public static final int
		RULE_expresion = 0, RULE_termino = 1, RULE_factor = 2, RULE_indicador = 3, 
		RULE_cuenta = 4;
	public static final String[] ruleNames = {
		"expresion", "termino", "factor", "indicador", "cuenta"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'IND('", "'CUENTA('", null, "'+'", "'*'", "'-'", 
		"'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "NUMERO", "SUM", "MUL", "RES", "DIV", "NOMBRE", 
		"WS"
	};
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
	public String getGrammarFileName() { return "Calculadora.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CalculadoraParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpresionContext extends ParserRuleContext {
		public List<TerminoContext> termino() {
			return getRuleContexts(TerminoContext.class);
		}
		public TerminoContext termino(int i) {
			return getRuleContext(TerminoContext.class,i);
		}
		public List<TerminalNode> SUM() { return getTokens(CalculadoraParser.SUM); }
		public TerminalNode SUM(int i) {
			return getToken(CalculadoraParser.SUM, i);
		}
		public List<TerminalNode> RES() { return getTokens(CalculadoraParser.RES); }
		public TerminalNode RES(int i) {
			return getToken(CalculadoraParser.RES, i);
		}
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener )
				try {
					((CalculadoraListener)listener).enterExpresion(this);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).exitExpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalculadoraVisitor ) return ((CalculadoraVisitor<? extends T>)visitor).visitExpresion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		ExpresionContext _localctx = new ExpresionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expresion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			termino();
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SUM || _la==RES) {
				{
				{
				setState(11);
				_la = _input.LA(1);
				if ( !(_la==SUM || _la==RES) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(12);
				termino();
				}
				}
				setState(17);
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

	public static class TerminoContext extends ParserRuleContext {
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(CalculadoraParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(CalculadoraParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(CalculadoraParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CalculadoraParser.DIV, i);
		}
		public TerminoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).enterTermino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).exitTermino(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalculadoraVisitor ) return ((CalculadoraVisitor<? extends T>)visitor).visitTermino(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminoContext termino() throws RecognitionException {
		TerminoContext _localctx = new TerminoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_termino);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			factor();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(19);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(20);
				factor();
				}
				}
				setState(25);
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

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode NUMERO() { return getToken(CalculadoraParser.NUMERO, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public IndicadorContext indicador() {
			return getRuleContext(IndicadorContext.class,0);
		}
		public CuentaContext cuenta() {
			return getRuleContext(CuentaContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalculadoraVisitor ) return ((CalculadoraVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_factor);
		try {
			setState(33);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMERO:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				match(NUMERO);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(T__0);
				setState(28);
				expresion();
				setState(29);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
				indicador();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(32);
				cuenta();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class IndicadorContext extends ParserRuleContext {
		public TerminalNode NOMBRE() { return getToken(CalculadoraParser.NOMBRE, 0); }
		public IndicadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indicador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).enterIndicador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).exitIndicador(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalculadoraVisitor ) return ((CalculadoraVisitor<? extends T>)visitor).visitIndicador(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndicadorContext indicador() throws RecognitionException {
		IndicadorContext _localctx = new IndicadorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_indicador);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(T__2);
			setState(36);
			match(NOMBRE);
			setState(37);
			match(T__1);
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

	public static class CuentaContext extends ParserRuleContext {
		public TerminalNode NOMBRE() { return getToken(CalculadoraParser.NOMBRE, 0); }
		public CuentaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuenta; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).enterCuenta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculadoraListener ) ((CalculadoraListener)listener).exitCuenta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CalculadoraVisitor ) return ((CalculadoraVisitor<? extends T>)visitor).visitCuenta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CuentaContext cuenta() throws RecognitionException {
		CuentaContext _localctx = new CuentaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cuenta);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__3);
			setState(40);
			match(NOMBRE);
			setState(41);
			match(T__1);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r.\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\7\2\20\n\2\f\2\16\2\23\13\2\3\3"+
		"\3\3\3\3\7\3\30\n\3\f\3\16\3\33\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4$"+
		"\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\2\2\7\2\4\6\b\n\2\4\4\2\b\b\n"+
		"\n\4\2\t\t\13\13\2-\2\f\3\2\2\2\4\24\3\2\2\2\6#\3\2\2\2\b%\3\2\2\2\n)"+
		"\3\2\2\2\f\21\5\4\3\2\r\16\t\2\2\2\16\20\5\4\3\2\17\r\3\2\2\2\20\23\3"+
		"\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\3\3\2\2\2\23\21\3\2\2\2\24\31\5"+
		"\6\4\2\25\26\t\3\2\2\26\30\5\6\4\2\27\25\3\2\2\2\30\33\3\2\2\2\31\27\3"+
		"\2\2\2\31\32\3\2\2\2\32\5\3\2\2\2\33\31\3\2\2\2\34$\7\7\2\2\35\36\7\3"+
		"\2\2\36\37\5\2\2\2\37 \7\4\2\2 $\3\2\2\2!$\5\b\5\2\"$\5\n\6\2#\34\3\2"+
		"\2\2#\35\3\2\2\2#!\3\2\2\2#\"\3\2\2\2$\7\3\2\2\2%&\7\5\2\2&\'\7\f\2\2"+
		"\'(\7\4\2\2(\t\3\2\2\2)*\7\6\2\2*+\7\f\2\2+,\7\4\2\2,\13\3\2\2\2\5\21"+
		"\31#";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}