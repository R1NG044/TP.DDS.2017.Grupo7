package ar.edu.utn.frba.dds.tp.antlr.dds;

public class Constante implements IExpresion {

	private double valor;

	public Constante(double valor) {
		this.valor = valor;
	}

	@Override
	public double calcularResultado(String empresa, Integer periodo) {
		return this.valor;
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

	@Override
	public IExpresion getOperando2() {
		// TODO Auto-generated method stub
		return null;
	}

}
