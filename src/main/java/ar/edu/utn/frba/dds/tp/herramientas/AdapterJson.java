package ar.edu.utn.frba.dds.tp.herramientas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import ar.edu.utn.frba.dds.tp.dominio.Empresa;

public class AdapterJson {

		public static List<Empresa> transformarDeJSONaListaEmpresas(String listaEmpresasJson) throws FileNotFoundException  {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		final Gson gson = new Gson();
	 	final Type tipoListaEmpleados = new TypeToken<ArrayList<Empresa>>() {}.getType();
	 	final JsonReader reader = new JsonReader(new FileReader(listaEmpresasJson));
		listaEmpresas = gson.fromJson(reader, tipoListaEmpleados);
		 return listaEmpresas;
	}
}
