package org.grupo7.tp.dominio;

import java.util.Scanner;

public class IniciarAplicacion {

	public static void main(String[] args) {
		System.out.print("Ingresar el valor de una cuenta \n");
		
		Scanner sc = new Scanner(System.in);
		
		Cuenta cuenta = new Cuenta();
		
		Repositorio repo = new Repositorio();
		
		float valor = sc.nextFloat();
		
		cuenta.setValor(valor);
		
		repo.agregarCuenta(cuenta);
		
		repo.consultarCuentas();
		
		

	}

}
