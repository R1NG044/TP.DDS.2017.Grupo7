package org.grupo7.tp.dominio;

import org.json.*;

public class AdapterJson {
	
	

public static void cargarDesdeJSON(String textoJSON, Repositorio repo) {
	
	String json = textoJSON;
	
	JSONObject arrEmpresas = new JSONObject(json);
		
		JSONArray empresas = arrEmpresas.getJSONArray("empresas");
		
		for (int i=0; i<=empresas.length()-1; i++) { 
			JSONObject empresa = empresas.getJSONObject(i);
			Empresa newEmpresa = repo.obtenerEmpresa(empresa.getString("nombre"));		
			
			JSONArray cuentas = empresa.getJSONArray("cuentas");
			for (int j=0; j<=cuentas.length()-1; j++) {
				
				JSONObject unaCuenta = cuentas.getJSONObject(j);
				String nombreCuenta = unaCuenta.getString("nombre");
				int periodoCuenta = unaCuenta.getInt("periodo");
				int valorCuenta = unaCuenta.getInt("valor");
				
				Cuenta newCuenta = new Cuenta(nombreCuenta, periodoCuenta, valorCuenta);				
				newEmpresa.agregarCuenta(newCuenta);
				
			}
		}
	}
}
