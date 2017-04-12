package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class Repositorio {
	
	
	List<Cuenta> cuentas = new ArrayList<Cuenta>();
	

	
	void agregarCuenta(Cuenta unaCuenta) {		
		cuentas.add(unaCuenta);		
	}
	
	void consultarCuentas() {
		for (Cuenta cuenta : cuentas) {
			System.out.printf("El valor de esta cuenta es: %.2f\n",cuenta.getValor());
		}
		
	
	}
	
}
	

