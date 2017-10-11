package ar.edu.utn.frba.dds.tp.antlr.dds;

import javax.persistence.*;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;

@Entity(name="Indicador")
@Table(name = "indicador")
@NamedQuery(name="buscarIndicadorPorNombre",query="SELECT i.nombre FROM Indicador i WHERE i.nombre LIKE :pnombre")
public class Indicador implements IExpresion {
	
	//@Id @GeneratedValue
	//private Integer id;
	
	@Id
	private String nombre;
	private String formula;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Transient
	private ExpresionCompuesta expresion;
	
	public Indicador(String nombre, String formula, Integer idUsuario){
		this.nombre = nombre;
		this.formula = formula;
		this.usuario = new Usuario(idUsuario, "");
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
