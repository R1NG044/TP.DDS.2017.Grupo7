package dds;

import org.antlr.v4.runtime.tree.ParseTree;

public class GramaticaListener extends CalculadoraBaseListener {
	public GramaticaListener(){
		super();
	}
	
	@Override
	public void enterExpresion(CalculadoraParser.ExpresionContext ctx){
		// TODO Falta agregar a gramatica parentesis para modificar precedencia y crear clase cuenta
		int i;
		ParseTree child = ctx.getChild(0);

		for (i=0;i<ctx.getChildCount();i++) {
			
			System.out.println(ctx.getChild(i).getText());
			
		}
		
        
	}
}
