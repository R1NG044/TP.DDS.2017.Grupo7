package ar.edu.utn.frba.dds.tp.dominio;

public class Cuenta {

	String nombreCuenta;
	Long valor;
	Integer periodo;

	public Cuenta(final String _nombre, final Long _valor, final Integer _periodo) {
		this.nombreCuenta = _nombre;
		this.setValor(_valor);
		this.setPeriodo(_periodo);
	}


	//Getters y Setters
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


}
