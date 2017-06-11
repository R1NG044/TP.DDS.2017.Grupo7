package dds;

public interface IOperador {

	public String getSimbolo();
	public double calcularOperacion(IExpresion expresion1, IExpresion expresion2);
}
