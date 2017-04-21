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
