// Generated from Calculadora.g4 by ANTLR 4.7
 
	package ar.edu.utn.frba.dds.tp.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalculadoraParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalculadoraVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(CalculadoraParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#termino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermino(CalculadoraParser.TerminoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(CalculadoraParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#indicador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndicador(CalculadoraParser.IndicadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculadoraParser#cuenta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuenta(CalculadoraParser.CuentaContext ctx);
}