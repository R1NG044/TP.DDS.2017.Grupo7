package ar.edu.utn.frba.dds.tp.antlr.dds;

public class Indicador implements IExpresion {
	String nombre;
	ExpresionCompuesta expresion;

	public Indicador(String nombre, ExpresionCompuesta expresion) {
		this.nombre = nombre;
		this.expresion = expresion;
	}

	public double evaluarIndicador(String empresa, Integer periodo) {
		System.out.printf("El valor del Indicador %s para la Empresa %s y Periodo %d es de: %.2f %n",this.nombre,empresa,periodo,(expresion.calcularResultado(empresa, periodo)));
		return expresion.calcularResultado(empresa, periodo);

	}

	@Override
	public IExpresion getOperando2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcularResultado(String empresa, Integer periodo) {
		return (this.expresion.calcularResultado(empresa, periodo));
	}

	public Indicador(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
