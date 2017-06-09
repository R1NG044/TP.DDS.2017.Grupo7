package dds;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import dds.CalculadoraParser.ExpresionContext;

public class ParserListener extends CalculadoraBaseListener {
	
	 public ParserListener() {
	        super();
	 }
	
	@Override
	public void enterExpresion(CalculadoraParser.ExpresionContext ctx){
		
		ParseTree child = ctx.getChild(0);
		System.out.println(child.toStringTree());
		
		
        //this.iterateNodes(ctx);
       
		
	}
	
	private void iterateNodes(ExpresionContext ctx) {
		// TODO Auto-generated method stub
		
	}

	public void exitExpresion(CalculadoraParser.ExpresionContext ctx){}
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#termino}.
	 * @param ctx the parse tree
	 */
	public void enterTermino(CalculadoraParser.TerminoContext ctx){}
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#termino}.
	 * @param ctx the parse tree
	 */
	public void exitTermino(CalculadoraParser.TerminoContext ctx){}
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#factor}.
	 * @param ctx the parse tree
	 */
	public void enterFactor(CalculadoraParser.FactorContext ctx){}
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#factor}.
	 * @param ctx the parse tree
	 */
	public void exitFactor(CalculadoraParser.FactorContext ctx){}
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#indicador}.
	 * @param ctx the parse tree
	 */
	public void enterIndicador(CalculadoraParser.IndicadorContext ctx){}
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#indicador}.
	 * @param ctx the parse tree
	 */
	public void exitIndicador(CalculadoraParser.IndicadorContext ctx){}
	/**
	 * Enter a parse tree produced by {@link CalculadoraParser#cuenta}.
	 * @param ctx the parse tree
	 */
	public void enterCuenta(CalculadoraParser.CuentaContext ctx){}
	/**
	 * Exit a parse tree produced by {@link CalculadoraParser#cuenta}.
	 * @param ctx the parse tree
	 */
	public void exitCuenta(CalculadoraParser.CuentaContext ctx){}

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		
	}

}
