package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;;

public class Empresa {

	String nombreEmpresa;
	List<Cuenta> cuentas = new ArrayList<Cuenta>();

	public Empresa(final String _nombre, final ArrayList<Cuenta> _cuentas) {
		this.nombreEmpresa = _nombre;
		this.setCuentas(_cuentas);
	}

	 public Empresa(final String _nombre) {
	 this.nombreEmpresa = _nombre;
	 this.cuentas = new ArrayList<Cuenta>();
	 }

	void agregarCuenta(Cuenta unaCuenta) {
		if (!this.existeCuentaDelMismoPeriodo(unaCuenta)) {
			this.cuentas.add(unaCuenta);
		}
	}

	boolean existeCuentaDelMismoPeriodo(Cuenta unaCuenta) {
		for (Cuenta cuenta : cuentas) {
			if ((cuenta.getNombreCuenta() == unaCuenta.getNombreCuenta()) & (cuenta.getPeriodo() == unaCuenta.getPeriodo())) {
				return true;
			}
		}
		return false;
	}

	public void consultarCuentas() {
		Set<Cuenta> set = new HashSet<Cuenta>(this.cuentas);
		for (Cuenta cuenta : set) {
			System.out.printf("El valor de la cuenta %s es: %.0f\n", cuenta.getNombreCuenta(), cuenta.getValor());
		}

	}

	// Getters y Setters
	public void setCuentas(final ArrayList<Cuenta> _cuentas) {
		for (Cuenta unaCuenta : _cuentas) {
			agregarCuenta(unaCuenta);
		}
	}

	public String getNombre() {
		return nombreEmpresa;
	}

	public void setNombre(String nombre) {
		this.nombreEmpresa = nombre;
	}
}