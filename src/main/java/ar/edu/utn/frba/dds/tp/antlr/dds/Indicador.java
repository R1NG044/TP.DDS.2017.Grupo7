package ar.edu.utn.frba.dds.tp.antlr.dds;

import java.util.List;

import javax.persistence.*;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;

@Entity(name = "Indicador")
@Table(name = "indicador")
@NamedQuery(name = "buscarIndicadorPorUser", query = "SELECT i FROM Indicador i WHERE i.usuario.id = :pIdUsuario OR i.usuario.id = 1 order by i.idIndicador asc")
public class Indicador implements IExpresion {

	@Id
	@GeneratedValue // (strategy=GenerationType.IDENTITY)
	private Integer idIndicador;

	private String nombre;
	private String formula;

	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	@Transient
	private ExpresionCompuesta expresion;

	@OneToMany(mappedBy = "indicador", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IndicadorEmpresa> indicadoresEmpresas;

	public List<IndicadorEmpresa> getIndicadoresEmpresas() {
		return indicadoresEmpresas;
	}

	public void setIndicadoresEmpresas(List<IndicadorEmpresa> indicadoresEmpresas) {
		this.indicadoresEmpresas = indicadoresEmpresas;
	}

	public Indicador() {
	}

	public Indicador(String nombre, String formula, Usuario idUsuario) {
		this.nombre = nombre;
		this.formula = formula;
		this.usuario = idUsuario;
	}

	public Indicador(Integer idIndicador, String nombre, String formula, Usuario usuario, ExpresionCompuesta expresion,
			List<IndicadorEmpresa> indicadoresEmpresas) {
		super();
		//this.idIndicador = idIndicador;
		this.nombre = nombre;
		this.formula = formula;
		this.usuario = usuario;
		this.expresion = expresion;
		this.indicadoresEmpresas = indicadoresEmpresas;
	}
	public Indicador( String nombre, String formula, Usuario usuario, ExpresionCompuesta expresion,
			List<IndicadorEmpresa> indicadoresEmpresas) {
		super();
		//this.idIndicador = idIndicador;
		this.nombre = nombre;
		this.formula = formula;
		this.usuario = usuario;
		this.expresion = expresion;
		this.indicadoresEmpresas = indicadoresEmpresas;
	}

	public Indicador(String nombre, ExpresionCompuesta expresion, String formula, Usuario user) {
		this.nombre = nombre;
		this.formula = formula;
		this.expresion = expresion;
		this.usuario = user;
	}

	public double evaluarIndicador(String empresa, Integer periodo) throws Exception {
		// Primero, validar que el indicador exista en memoria.
		// Segundo, buscar el indicador en la BD.
		//System.out.printf("El valor del Indicador %s para la Empresa %s y Periodo %d es de: %.2f %n", this.nombre,
		//		empresa, periodo, (expresion.calcularResultado(empresa, periodo)));
		return expresion.calcularResultado(empresa, periodo);

	}

	@Override
	public IExpresion getOperando2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcularResultado(String empresa, Integer periodo) throws Exception {
		if (this.tieneIndicadorEmpresa(empresa, periodo))
			return (this.darValorIndicadorEmpresa(empresa, periodo));
		else
			return (this.expresion.calcularResultado(empresa, periodo));
	}

	private double darValorIndicadorEmpresa(String empresa, Integer periodo) throws Exception {
		for (IndicadorEmpresa ie : indicadoresEmpresas) {
			if (ie.getNombreEmpresa().equals(empresa) && (int) ie.getPeriodo() == (int) periodo)
				return ie.getValorIndicador();
		}
		throw new Exception("No existe la Empresa, o cuenta para el Periodo seleccionados");
	}

	private boolean tieneIndicadorEmpresa(String empresa, Integer periodo) {
		for (IndicadorEmpresa ie : indicadoresEmpresas) {
			if (ie.getNombreEmpresa().equals(empresa) && (int) ie.getPeriodo() == (int) periodo)
				return true;
		}
		return false;
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


	public Integer getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Integer idIndicador) {
		this.idIndicador = idIndicador;
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
