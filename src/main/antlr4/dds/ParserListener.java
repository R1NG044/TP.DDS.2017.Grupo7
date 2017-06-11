package dds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.StringRefAddr;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import dds.CalculadoraParser.ExpresionContext;

public class ParserListener extends CalculadoraBaseListener {
	
	private Map<String, IOperador> operadores;
	private ExpresionCompuesta expresionPadre;
	private ArrayList errores;
	
	 public ParserListener() {
	        super();
	        
	        this.cargarOperadores();
	        this.expresionPadre = new ExpresionCompuesta();
	        this.errores = new ArrayList();
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

		
		if(ctx.children != null){
			this.iterateNodes(ctx);
		}
        
	}
	
	private void iterateNodes(ExpresionContext ctx) {
		// TODO Non binary tree algorithm search;
		// Recorro childs. Cada child es un termino: constante o expresion o indicador o cuenta
		
		int i;
		
		
		for (i=0;i<ctx.getChildCount();i++) {
			ParseTree tree = ctx.getChild(i);
			
			//System.out.println(tree.getText()); 
			
			asignarToken(tree.getText());
			
		}
		
	}
	
	public void asignarToken(String token){
		// convertir terminos a los objetos. Si los puede parsear, los mete en un objeto
		//Por ahora solo se parsean los "-" 
		
		IOperador op = this.operadores.get(token);	
		IExpresion termino = null;
		
		//Es un operador
		if(op != null){
			
			this.expresionPadre.setOperador(op);		
		}else {
			//es una expre compleja, una constante, una cuenta o un indicador
			try{
				termino = new Constante(Double.parseDouble(token));
				
			}catch(NumberFormatException e){
				//No es un numero: es una expresion, indicador o cuenta
				if(token.contains("*") || token.toString().contains("/")){
					//Es una expresion compleja
				}
			}
			
			
			if(this.expresionPadre.getOperando1() != null){
				this.expresionPadre.setOperando1(termino);
			}else{
				this.expresionPadre.setOperando2(termino);
			}
			
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
