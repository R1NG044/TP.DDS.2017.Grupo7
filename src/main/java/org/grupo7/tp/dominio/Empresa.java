package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;;

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

	public void cargarCuentas(List<Cuenta> listaCuentas) {
		for (Cuenta unaCuenta : listaCuentas) {
			this.agregarCuenta(unaCuenta);
		}

	}

	void agregarCuenta(Cuenta unaCuenta) {
		if (!this.existeCuentaDelMismoPeriodo(unaCuenta)) {
			this.cuentas.add(unaCuenta);
		}
	}

	boolean existeCuentaDelMismoPeriodo(Cuenta unaCuenta) {
		for (Cuenta cuenta : cuentas) {
			if ((cuenta.getNombreCuenta().equals(unaCuenta.getNombreCuenta()))
					& (cuenta.getPeriodo().equals(unaCuenta.getPeriodo()))) {
				return true;
			}
		}
		return false;
	}

	public void consultarCuentas() {
		for (Cuenta cuenta : cuentas) {
			System.out.printf("Para %s el valor de la cuenta %s es: %.0f para el Periodo %s\n", this.getNombre(), cuenta.getNombreCuenta(), cuenta.getValor(),cuenta.getPeriodo());
		}
	}

	
	public boolean existeCuentaDeNombre(String nombreCuenta) {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getNombreCuenta() == (nombreCuenta)) {
				return true;
			}
		}
		return false;
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
