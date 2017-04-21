package org.grupo7.tp.dominio;

public class Cuenta {

	String nombreCuenta;	
	long valor;
	Integer periodo;

	public Cuenta(final String _nombre, final long _valor, final Integer _periodo) {
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

	public float getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}


}
