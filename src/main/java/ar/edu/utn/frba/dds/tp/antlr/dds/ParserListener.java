package ar.edu.utn.frba.dds.tp.antlr.dds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraBaseListener;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser.ExpresionContext;
import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class ParserListener extends CalculadoraBaseListener {

	private Map<String, IOperador> operadores;
	private ExpresionCompuesta expresionPadre;
	private ExpresionCompuesta expresionAux;
	private ArrayList<String> errores;

	public ParserListener() {
		super();

		this.cargarOperadores();
		this.errores = new ArrayList<String>();
	}

	public void guardarUnIndicadorNuevo(ExpresionContext ctx, String nombreNuevoIndicador, String formula) {
		this.enterExpresion(ctx);
		Indicador indicador = new Indicador(nombreNuevoIndicador, expresionPadre, formula);
		Repositorio.getInstance().agregarIndicador(indicador);

	}

	public double probarUnIndicadorNuevo(ExpresionContext ctx, String empresa, Integer periodo) {
		this.enterExpresion(ctx);
		Indicador indicador = new Indicador("Prueba", expresionPadre, "");
		return indicador.evaluarIndicador(empresa, periodo);

	}

	public void enterExpresion(CalculadoraParser.ExpresionContext ctx) {
		this.expresionPadre = new ExpresionCompuesta();
		expresionAux = expresionPadre;

		if (ctx.children != null) {
			this.iterateNodes(ctx);
		} else {
			System.out.println("No ingreso datos");
		}

	}

	private void iterateNodes(ParserRuleContext ctx) {
		// Recorro childs. Cada child es un termino: constante o expresion o
		// indicador o cuenta

		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree tree = ctx.getChild(i);
			if (tree instanceof ErrorNode) {
				System.err.printf("Error en nodo: %s\n", tree.getText());

			} else {
				System.out.printf("Nodo valido: %s\n", tree.getText());
				descomponerEnObjetos(tree, expresionAux);
			}
		}

	}

	public void descomponerEnObjetos(ParseTree tree, ExpresionCompuesta expresion) {
		// Convertir en terminos a los objetos. Si los puede parsear, los mete
		// en un objeto
		// Si es un nodo con operacion
		System.out.print(tree.getChildCount());
		IOperador operador = this.operadores.get(tree.getText());
		if (operador != null) {
			if (expresion.getOperando2() == null) {
				// Setea operador en Expresi�n que vino
				expresion.setOperador(operador);
			} else {
				// Setea operador en nueva Expresi�n anidando
				this.setearExpresionCompuesta(expresion, operador);

			}
		} else if (tree.getChildCount() == 1) {
			// es una constante, indicador o cuenta
			// ParserRuleContext rule = (ParserRuleContext) tree;
			IExpresion termino = null;
			try {
				termino = new Constante(Double.parseDouble(tree.getText()));
			} catch (Exception e) {
				// Input invalido
				// System.out.println(e.getMessage());
			}

			if (termino != null) {
				if (expresion.getOperando1() == null) {
					// Setea operando1
					expresion.setOperando1(termino);
				} else if (expresion.getOperando2() == null) {
					// Setea operando2;
					expresion.setOperando2(termino);
				}
			} else {
				if (tree.getText().contains("IND(")) {
					int fin = (tree.getText().length()) - 1;
					String nombreIndicador = (tree.getText().substring(4, fin));
					// String nombreIndicador =
					// child.getChild(0).getChild(1).getText();
					if (Repositorio.getInstance().existeIndicador(nombreIndicador)) {
						if (expresion.getOperando1() == null) {
							// Setea operando1
							expresion.setOperando1(Repositorio.getInstance().darIndicadorDeNombre(nombreIndicador));
						} else if (expresion.getOperando2() == null) {
							// Setea operando2;
							expresion.setOperando2(Repositorio.getInstance().darIndicadorDeNombre(nombreIndicador));
						}
					}

				} else {
					if (tree.getText().contains("CUENTA(")) {
						// ParseTree child = tree.getChild(0);
						int fin = (tree.getText().length()) - 1;
						String nombreCuenta = (tree.getText().substring(7, fin));
						CuentaExp cuenta = new CuentaExp(nombreCuenta);
						if (expresion.getOperando1() == null) {
							// Setea operando1
							expresion.setOperando1(cuenta);
						} else if (expresion.getOperando2() == null) {
							// Setea operando2;
							expresion.setOperando2(cuenta);
						}
					}
				}

			}
		} else if (tree.getChildCount() > 1) {
			// hay que iterar, porque es una expresion compuesta
			if (expresion.getOperando1() == null) {
				iterateNodes((ParserRuleContext) tree);
			} else if (expresion.getOperando2() == null) {
				this.setearExpresionCompuesta(expresion, operador);
				iterateNodes((ParserRuleContext) tree);
			}

		}

	}

	private void setearExpresionCompuesta(ExpresionCompuesta expresion, IOperador operador) {
		ExpresionCompuesta exp = new ExpresionCompuesta();
		exp.setOperando1(expresion.getOperando2());
		exp.setOperador(operador);
		expresion.setOperando2(exp);
		expresionAux = exp;

	}

	/*
	 * else{ //Si tiene hijos, es una expresion compuesta ExpresionCompuesta exp
	 * = new ExpresionCompuesta(); int j; for(j=0;j<tree.getChildCount();j++){
	 * descomponerEnObjetos(tree.getChild(j), exp); } }
	 */

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
}
