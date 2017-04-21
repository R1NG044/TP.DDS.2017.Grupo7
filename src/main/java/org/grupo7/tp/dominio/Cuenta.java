package org.grupo7.tp.dominio;

public class Cuenta {

	String nombreCuenta;	
	Integer valor;
	Integer periodo;

	public Cuenta(final String _nombre, final Integer _valor, final Integer _periodo) {
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

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}


}
