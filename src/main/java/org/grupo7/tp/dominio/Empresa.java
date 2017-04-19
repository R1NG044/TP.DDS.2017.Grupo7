package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;;

public class Empresa {

	String nombreEmpresa;
	List<Cuenta> cuentas;

	public Empresa(final String _nombre) {
		this.nombreEmpresa = _nombre;
		this.cuentas = new ArrayList<Cuenta>();
	}

	public void cargarCuentas(List<Cuenta> listaCuentas) {
		for (Cuenta unaCuenta : listaCuentas) {
			this.agregarCuenta(unaCuenta);
		}

	}

	public void agregarCuenta(Cuenta unaCuenta) {
		if (!this.existeCuentaDelMismoPeriodo(unaCuenta.getNombreCuenta(), unaCuenta.getPeriodo())) {
			this.cuentas.add(unaCuenta);
		}
	}

	boolean existeCuentaDelMismoPeriodo(String nombre, Integer periodo) {
		for (Cuenta cuenta : cuentas) {
			if ((cuenta.getNombreCuenta().equals(nombre))
					& (cuenta.getPeriodo().equals(periodo))) {
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
	
	public int cantidadCuentas() {
		return cuentas.size();
	}

	// Getters y Setters

	public void setCuentas(final ArrayList<Cuenta> _cuentas) {
		for (Cuenta unaCuenta : _cuentas) {
			agregarCuenta(unaCuenta);
		}
	}
	
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public String getNombre() {
		return nombreEmpresa;
	}

	public void setNombre(String nombre) {
		this.nombreEmpresa = nombre;
	}

}
