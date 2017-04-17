package org.grupo7.tp.dominio;

public class Cuenta {

	String nombreCuenta;
	long valor;
	int año;
	
public Cuenta(final String _nombre, final long _valor, final int _año) {
		this.nombreCuenta = _nombre;
		this.setValor(_valor);
		this.setAño(_año);
	}
	

//Getters y Setters
	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
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

// TODO: Revisar el tema de los perÃ­odos
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
