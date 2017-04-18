package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;;

public class Empresa {

	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	String nombre;

	public Empresa(final String _nombre, final ArrayList<Cuenta> _cuentas) {
		this.nombre = _nombre;
		this.setCuentas(_cuentas);
	}
	
//	public Empresa(final String _nombre) {
//		this.nombre = _nombre;
//		this.cuentas = new ArrayList<Cuenta>();
//	}

	void agregarCuenta(Cuenta unaCuenta) {
		this.cuentas.add(unaCuenta);
	}
	
	boolean existeCuentaDelMismoPeriodo(Cuenta unaCuenta) {
		for (Cuenta cuenta : cuentas) {			
			if ((cuenta.getNombreCuenta() == unaCuenta.getNombreCuenta()) & (cuenta.getAnio() == unaCuenta.getAnio())) {
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
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
