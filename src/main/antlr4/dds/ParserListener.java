package dds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import dds.CalculadoraParser.ExpresionContext;

public class ParserListener extends CalculadoraBaseListener {
	
	private Map<String, IOperador> operadores;
	
	 public ParserListener() {
	        super();
	        
	        this.cargarOperadores();
	 }
	 
	 private Map<String, IOperador> getOperadores(){
		 return operadores;
	 }
	 
	 private void cargarOperadores(){
		 this.operadores = new HashMap<String, IOperador>();
		 this.operadores.put("+", new OperadorSUM());
		 this.operadores.put("-", new OperadorRES());
	 }
	
	@Override
	public void enterExpresion(CalculadoraParser.ExpresionContext ctx){
		// TODO Falta agregar a gramatica parentesis para modificar precedencia y crear clase cuenta
		int i;
		ParseTree child = ctx.getChild(0);
		
		
		
		
        this.iterateNodes(ctx);
       
		
	}
	
	private void iterateNodes(ExpresionContext ctx) {
		// TODO Non binary tree algorithm search;
		// Recorro childs. Cada child es un termino: constante o expresion o indicador o cuenta
		
		int i;

		for (i=0;i<ctx.getChildCount();i++) {
			ParseTree tree = ctx.getChild(i);
			
			//System.out.println(tree.getText()); 
			
			// convertir los terminos a objetos. Si los puede parsear y crear, voy asignandolos a las
			// extensiones.

			
		}
		
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
