package ar.edu.utn.frba.dds.tp.dominio;

import javax.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {

	@Id
	@GeneratedValue//(strategy=GenerationType.IDENTITY)
	private Integer idCuenta;
	private String nombreCuenta;
	private Long valor;
	private Integer periodo;

	@ManyToOne(fetch = FetchType.LAZY)//,cascade = CascadeType.ALL)
	private Empresa empresa;

	public Cuenta() {
	}

	public Cuenta(final String _nombre, final Long _valor, final Integer _periodo) {
		this.nombreCuenta = _nombre;
		this.setValor(_valor);
		this.setPeriodo(_periodo);
	}

	/**** Getters y Setters ****/
	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Cuenta))
			return false;
		return idCuenta != null && idCuenta.equals(((Cuenta) o).idCuenta);
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
