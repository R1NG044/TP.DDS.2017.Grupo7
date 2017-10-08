package ar.edu.utn.frba.dds.tp.antlr.dds;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.utn.frba.dds.tp.dominio.Usuario;

@Entity(name="Indicador")
@Table(name = "indicador")
public class Indicador implements IExpresion {
	
	@Id
	private String nombre;
	private String formula;
	private Integer userId;
	@Transient
	private ExpresionCompuesta expresion;

	public Indicador(String nombre, ExpresionCompuesta expresion, String formula, Integer userId) {
		this.nombre = nombre;
		this.formula = formula;
		this.expresion = expresion;
		this.userId = userId;
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
