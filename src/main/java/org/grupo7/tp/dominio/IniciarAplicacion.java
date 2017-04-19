/*
 * 
 * CLASE DE PRUEBA
 * 
 */

package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class  IniciarAplicacion {
	

//	public static void cargarEmpresasDesdeJson(String jsonEmpresas) {
//		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
//		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
//		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);
//
//	}
//}

	public static void main(String[] args) {
		
		String textoJSON = "{\"empresas\":[{\"nombre\":	\"empresa2\",	\"cuentas\":	[{\"nombre\":\"cuentalalala1\",\"periodo\":2014,\"valor\":1000},{\"nombre\":\"cuentalelele2\",\"periodo\":2015,\"valor\":2000},{\"nombre\":\"cuentalilili3\",\"periodo\":2016,\"valor\":3000}]},{\"nombre\":	\"empresa2\",	\"cuentas\":	[{\"nombre\":\"cuentananana1\",\"periodo\":2014,\"valor\":1000},{\"nombre\":\"cuentanenene2\",\"periodo\":2015,\"valor\":2000},{\"nombre\":\"cuentaninini3\",\"periodo\":2016,\"valor\":3000}]},{\"nombre\":	\"empresa3\",	\"cuentas\":	[{\"nombre\":\"cuentapapapa1\",\"periodo\":2014,\"valor\":1000},{\"nombre\":\"cuentapepepe2\",\"periodo\":2015,\"valor\":2000},{\"nombre\":\"cuentapipipi3\",\"periodo\":2016,\"valor\":3000}]}]}";
		Repositorio repo = Repositorio.getInstance();
		AdapterJson.cargarDesdeJSON(textoJSON,repo);
		
		repo.mostrarEmpresas();
		System.out.print(repo.existeEmpresa("empresa1"));
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
	}
//
}
