/*
 * 
 * CLASE DE PRUEBA
 * 
 */

package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class  IniciarAplicacion {
	

	public static void cargarEmpresasDesdeJson(String jsonEmpresas) {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);

	}
}

//	public static void main(String[] args) {
//		System.out.print("Ingresar el valor de una cuenta \n");
//
//		Scanner sc = new Scanner(System.in);
//
//		Repositorio repo = Repositorio.getInstance();
//
//		do {
//
//			float valor = sc.nextFloat();
//
//			Cuenta cuenta = new Cuenta();
//
//			cuenta.setValor(valor);
//
//			repo.agregarCuenta(cuenta);
//
//		} while (sc.hasNextFloat());
//
//		repo.consultarCuentas();
//
//		sc.close();
//
//	}
//
//}
