package ar.edu.utn.frba.dds.tp.antlr.dds;

public class Cuenta implements IExpresion {

	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cuenta(String nombre){
		this.nombre = nombre;
	}

	@Override
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
