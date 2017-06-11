package dds;

public class Constante implements IExpresion{

	private double valor;
	
	@Override
	public double calcularResultado() {
		// TODO Auto-generated method stub
		return this.valor;
	}
	
	public Constante(double valor){
		this.valor = valor;
	}
	
	

}
