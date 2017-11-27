package ar.edu.utn.frba.dds.tp.antlr.dds;

import javax.persistence.*;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;

@Entity(name="Indicador")
@Table(name = "indicador")
@NamedQuery(name="buscarIndicadorPorUser",query="SELECT i FROM Indicador i WHERE i.usuario.id = :pIdUsuario OR i.usuario.id = 1")
public class Indicador implements IExpresion {
	
		
	@Id
	private String nombre;
	private String formula;

	@ManyToOne(fetch = FetchType.LAZY)
  	private Usuario usuario;
	
	@Transient
	private ExpresionCompuesta expresion;
	
	public Indicador(){
		
	}
	
	public Indicador(String nombre, String formula, Usuario idUsuario){
		this.nombre = nombre;
		this.formula = formula;
		this.usuario =idUsuario;
	}
	
	public Indicador(String nombre, ExpresionCompuesta expresion, String formula, Usuario user) {
		this.nombre = nombre;
		this.formula = formula;
		this.expresion = expresion;
		this.usuario = user;
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
// GETTERS y SETTERS
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
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ExpresionCompuesta getExpresion() {
		return expresion;
	}

	public void setExpresion(ExpresionCompuesta expresion) {
		this.expresion = expresion;
	}

}
