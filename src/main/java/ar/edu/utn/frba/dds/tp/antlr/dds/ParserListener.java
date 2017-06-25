package ar.edu.utn.frba.dds.tp.antlr.dds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraBaseListener;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser.ExpresionContext;

public class ParserListener extends CalculadoraBaseListener {

	private Map<String, IOperador> operadores;
	private ExpresionCompuesta expresionPadre;
	private ExpresionCompuesta expresionAux;
	private ArrayList<String> errores;

	public ParserListener() {
		super();

		this.cargarOperadores();
		this.expresionPadre = new ExpresionCompuesta();
		expresionAux = expresionPadre;
		this.errores = new ArrayList<String>();
	}

	Map<String, IOperador> getOperadores() {
		return operadores;
	}

	private void cargarOperadores() {
		IOperador sumar = new OperadorSUM();
		IOperador restar = new OperadorRES();
		IOperador multiplicar = new OperadorMUL();
		IOperador dividir = new OperadorDIV();

		this.operadores = new HashMap<String, IOperador>();
		this.operadores.put(sumar.getSimbolo(), sumar);
		this.operadores.put(restar.getSimbolo(), restar);
		this.operadores.put(multiplicar.getSimbolo(), multiplicar);
		this.operadores.put(dividir.getSimbolo(), dividir);
	}

	@Override
	public void enterExpresion(CalculadoraParser.ExpresionContext ctx) {
		// TODO Falta agregar a gramatica parentesis para modificar precedencia
		// y crear clase cuenta
		// ParseTree child = ctx.getChild(0);

		if (ctx.children != null) {
			this.iterateNodes(ctx);
		} else {
			System.out.println("No ingreso datos");
		}
	}

	private void iterateNodes(ParserRuleContext ctx) {
		// TODO Non binary tree algorithm search;
		// Recorro childs. Cada child es un termino: constante o expresion o
		// indicador o cuenta

		int i;

		for (i = 0; i < ctx.getChildCount(); i++) {
			ParseTree tree = ctx.getChild(i);
			if (tree instanceof ErrorNode) {
				System.err.printf("Error en nodo: %s\n", tree.getText());

				// todo: manejar el error

			} else {
				System.out.printf("Nodo valido: %s\n", tree.getText());

				// System.out.println(tree.getChildCount());

				descomponerEnObjetos(tree, expresionAux);

			}

		}
		System.out.print(expresionPadre.calcularResultado());
	}

	public void descomponerEnObjetos(ParseTree tree, ExpresionCompuesta expresion) {
		// Convertir terminos a los objetos. Si los puede parsear, los mete en
		// un objeto

		// es un nodo con operacion
		if (tree.getChildCount() == 0) {
			IOperador op = this.operadores.get(tree.getText());

			// Es un operador
			if (op != null) {

				if (expresion.getOperando2() == null) {
					// System.out.print("Setea operando2");
					expresion.setOperador(op);
				} else {
					ExpresionCompuesta exp = new ExpresionCompuesta();
					exp.setOperando1(expresion.getOperando2());
					exp.setOperador(op);
					expresion.setOperando2(exp);
					expresionAux = exp;
				}
			}
		} else {
			if (tree.getChildCount() == 1) {
				// es una constante, indicador o cuenta
				// ParserRuleContext rule = (ParserRuleContext) tree;

				IExpresion termino = null;
				try {

					termino = new Constante(Double.parseDouble(tree.getText()));

				} catch (Exception e) {
					// Input invalido
					System.out.println(e.getMessage());

					/*
					 * if(tree.getText().contains("IND(")){
					 * 
					 * }
					 */

				}

				if (termino != null) {
					if (expresion.getOperando1() == null) {
						// System.out.print("Setea operando1");
						expresion.setOperando1(termino);
					} else if (expresion.getOperando2() == null) {
						// System.out.print("Setea operando2");
						expresion.setOperando2(termino);
					} else {
						// ExpresionCompuesta exp = new
						// ExpresionCompuesta();
						// exp.setOperando1(termino);
					}
				} else {
					if (tree.getText().contains("IND(")) {
						// Crear objeto indicador.
					} else {
						if (tree.getText().contains("CUENTA(")) {
							// Crear objeto cuenta
						}
					}

				}
			} else {
				// hay que iterar, porque es una expresion compuesta
				ExpresionCompuesta exp = new ExpresionCompuesta();
				// ParserRuleContext exp2 = (ParserRuleContext) tree;
				// iterateNodes(exp2, exp);
				int j;
				for (j = 0; j < tree.getChildCount(); j++) {
					descomponerEnObjetos(tree.getChild(j), exp);
				}
			}

		} /*
			 * else{ //Si tiene hijos, es una expresion compuesta
			 * ExpresionCompuesta exp = new ExpresionCompuesta(); int j;
			 * for(j=0;j<tree.getChildCount();j++){
			 * descomponerEnObjetos(tree.getChild(j), exp); } }
			 */

	}
}
