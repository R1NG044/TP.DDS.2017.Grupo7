package ar.edu.utn.frba.dds.tp.antlr.dds;

public interface IOperador {

	public String getSimbolo();
	public double calcularOperacion(IExpresion operando1, IExpresion operando2);
	//public double calcularOperacion(ExpresionCompuesta expresion1, ExpresionCompuesta expresion2);
}
