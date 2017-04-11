package org.grupo7.tp.dominio;

import java.util.Date;

public class Cuenta {
	
	String	nombreCuenta;
	String	nombreEmpresa;
	float	valor;
	Date inicioPeriodo;
	Date finPeriodo;
	

	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public Date getInicioPeriodo() {
		return inicioPeriodo;
	}
	public void setInicioPeriodo(Date inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}
	public Date getFinPeriodo() {
		return finPeriodo;
	}
	public void setFinPeriodo(Date finPeriodo) {
		this.finPeriodo = finPeriodo;
	}
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
	
	

}
