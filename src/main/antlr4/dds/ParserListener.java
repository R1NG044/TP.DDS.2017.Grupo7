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
		 this.operadores.put("*", new OperadorMUL());
		 this.operadores.put("/", new OperadorDIV());
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
			
			System.out.println(tree.getChildCount()); 
			
			descomponerEnObjetos(tree, this.expresionPadre);
			
		}
		
		System.out.print(this.expresionPadre.calcularResultado());
		
	}
	
	public void descomponerEnObjetos(ParseTree tree, ExpresionCompuesta expresion){
		//Convertir terminos a los objetos. Si los puede parsear, los mete en un objeto
		//Por ahora solo se parsean los "-" 
		
		//es un nodo con operacion
		if(tree.getChildCount() == 0){
			IOperador op = this.operadores.get(tree.getText());	
			
			
			//Es un operador
			if(op != null){
				
				this.expresionPadre.setOperador(op);	
			}
		}else {
			if(tree.getChildCount() == 1){
				//es una constante, indicador o cuenta
				
				IExpresion termino = null;
				try{
					
					termino = new Constante(Double.parseDouble(tree.getText()));
					
					
				}catch(Exception e){
					//Input invalido
					System.out.println(e.getMessage());
					
					/*
					if(tree.getText().contains("IND(")){
						
					}*/
					
				}
			
				if(termino != null){
					if(this.expresionPadre.getOperando1() == null){
						//System.out.print("Setea operando1");
						this.expresionPadre.setOperando1(termino);
					}else{
						//System.out.print("Setea operando2");
						this.expresionPadre.setOperando2(termino);
					}
				}else{
					if(tree.getText().contains("IND(")){
						//Crear objeto indicador.
					}
					else{
						if(tree.getText().contains("CUENTA(")){
							//Crear objeto cuenta
						}
					}
					
				}
			}else{
				//hay que iterar, porque es una expresion compuesta
				ExpresionCompuesta exp = new ExpresionCompuesta();
				int j;
				for(j=0;j<tree.getChildCount();j++){
					descomponerEnObjetos(tree.getChild(j), exp);
				}
			}
				
		}/*else{
			//Si tiene hijos, es una expresion compuesta
			ExpresionCompuesta exp = new ExpresionCompuesta();
			int j;
			for(j=0;j<tree.getChildCount();j++){
				descomponerEnObjetos(tree.getChild(j), exp);
			}
		}
		*/
		
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
