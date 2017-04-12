package org.grupo7.tp.dominio;

import java.util.Scanner;

public class IniciarAplicacion {

	public static void main(String[] args) {
		System.out.print("Ingresar el valor de una cuenta \n");
		
		Scanner sc = new Scanner(System.in);
		
		Repositorio repo = new Repositorio();
		
		do {
		
			float valor = sc.nextFloat();
			
			Cuenta cuenta = new Cuenta();
		
			cuenta.setValor(valor);
		
			repo.agregarCuenta(cuenta);
		
		} while (sc.hasNextFloat());
		
		repo.consultarCuentas();
		
		

	}

}
