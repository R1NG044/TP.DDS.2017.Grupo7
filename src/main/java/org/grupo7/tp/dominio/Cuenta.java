package org.grupo7.tp.dominio;

public class Cuenta {

	String nombreCuenta;
	long valor;
	int a�o;
	
public Cuenta(final String _nombre, final long _valor, final int _a�o) {
		this.nombreCuenta = _nombre;
		this.setValor(_valor);
		this.setA�o(_a�o);
	}
	

//Getters y Setters
	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}
}


// String nombreEmpresa;
// Date inicioPeriodo;
// Date finPeriodo;

// TODO: Revisar el tema de los períodos
// public String getNombreEmpresa() {
// return nombreEmpresa;
// }
// public void setNombreEmpresa(String nombreEmpresa) {
// this.nombreEmpresa = nombreEmpresa;
// }
// public Date getInicioPeriodo() {
// return inicioPeriodo;
// }
// public void setInicioPeriodo(Date inicioPeriodo) {
// this.inicioPeriodo = inicioPeriodo;
// }
// public Date getFinPeriodo() {
// return finPeriodo;
// }
// public void setFinPeriodo(Date finPeriodo) {
// this.finPeriodo = finPeriodo;
// }
