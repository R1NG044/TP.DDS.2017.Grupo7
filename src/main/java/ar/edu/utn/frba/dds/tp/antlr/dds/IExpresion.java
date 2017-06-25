package ar.edu.utn.frba.dds.tp.antlr.dds;

public interface IExpresion {

	public double calcularResultado();

	public IOperador getOperador();

	public IExpresion getOperando1();

	
}
