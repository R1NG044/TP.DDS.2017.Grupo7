// Generated from Calculadora.g4 by ANTLR 4.7
 
	package ar.edu.utn.frba.dds.tp.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculadoraParser}.
 */
public interface CalculadoraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#expresion}.
	 * @param ctx the parse tree
	 * @throws Exception 
	 */
	void enterExpresion(CalculadoraParser.ExpresionContext ctx) throws Exception;
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(CalculadoraParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#termino}.
	 * @param ctx the parse tree
	 */
	void enterTermino(CalculadoraParser.TerminoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#termino}.
	 * @param ctx the parse tree
	 */
	void exitTermino(CalculadoraParser.TerminoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(CalculadoraParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(CalculadoraParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#indicador}.
	 * @param ctx the parse tree
	 */
	void enterIndicador(CalculadoraParser.IndicadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#indicador}.
	 * @param ctx the parse tree
	 */
	void exitIndicador(CalculadoraParser.IndicadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#cuenta}.
	 * @param ctx the parse tree
	 */
	void enterCuenta(CalculadoraParser.CuentaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#cuenta}.
	 * @param ctx the parse tree
	 */
	void exitCuenta(CalculadoraParser.CuentaContext ctx);
}