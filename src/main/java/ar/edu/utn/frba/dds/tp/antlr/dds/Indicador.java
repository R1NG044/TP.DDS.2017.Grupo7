package ar.edu.utn.frba.dds.tp.antlr.dds;

import ar.edu.utn.frba.dds.tp.dominio.Empresa;

public class Indicador implements IExpresion {
	String nombre;
	ExpresionCompuesta expresion;

	public Indicador(String nombre, ExpresionCompuesta expresion) {
		this.nombre = nombre;
		this.expresion = expresion;
	}

	public Indicador(String nombre) {
		this.nombre = nombre;
	}

//	public double evaluarIndicador(Empresa empresa, Integer periodo) {
//
//		return expresion.calcularResultado(empresa, periodo);
//	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double calcularResultado() {
		// TODO Llamo a clase Indicador con empresa y periodo
		return 0;
	}

	@Override
	public IOperador getOperador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExpresion getOperando1() {
		// TODO Auto-generated method stub
		return null;
	}

}
