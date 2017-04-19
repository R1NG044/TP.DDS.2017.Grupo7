package org.grupo7.tp.dominio;

public class Cuenta {

	String nombreCuenta;
	
long valor;
int periodo;
	
public Cuenta(String _nombre, Integer _periodo, Integer _valor) {
		this.nombreCuenta = _nombre;
		this.setPeriodo(_periodo);
		this.setValor(_valor);
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

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}


}
