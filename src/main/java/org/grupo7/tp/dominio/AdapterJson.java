package org.grupo7.tp.dominio;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AdapterJson {

	private static ArrayList<Empresa> listaEmpresas;
	
	public static List<Empresa> transformarDeJSONaListaEmpresas(final String listaEmpresasJson) {
	 	final Gson gson = new Gson();
	 	final Type tipoListaEmpleados = new TypeToken<List<Empresa>>() {}.getType();
		listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = gson.fromJson(listaEmpresasJson, tipoListaEmpleados);
		 return listaEmpresas;
	}
}
