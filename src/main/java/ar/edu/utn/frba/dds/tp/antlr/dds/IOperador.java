package ar.edu.utn.frba.dds.tp.antlr.dds;

public interface IOperador {

	public String getSimbolo();
	double calcularOperacion(IExpresion expresion1, IExpresion expresion2, String empresa, Integer periodo) throws Exception;
}
