package org.grupo7.tp.dominio;

public class Cuenta {

	String nombreCuenta;
	long valor;
	int anio;
	
public Cuenta(final String _nombre, final long _valor, final int _anio) {
		this.nombreCuenta = _nombre;
		this.setValor(_valor);
		this.setAnio(_anio);
	}
	

//Getters y Setters
	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
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
