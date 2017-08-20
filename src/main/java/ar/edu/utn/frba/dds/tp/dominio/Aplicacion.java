package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import ar.edu.utn.frba.dds.tp.herramientas.AdapterJson;

public final class  Aplicacion {


	public static void cargarEmpresasDesdeJson(String jsonEmpresas) throws FileNotFoundException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);
		//cargar ind predefinidos
	}
}
