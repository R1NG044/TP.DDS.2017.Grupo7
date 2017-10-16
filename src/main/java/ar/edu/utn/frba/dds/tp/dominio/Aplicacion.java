package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import java.io.FileNotFoundException;
import ar.edu.utn.frba.dds.tp.herramientas.AdapterJson;
import ar.edu.utn.frba.dds.tp.antlr.dds.*;

public final class  Aplicacion {

	

	public static void cargarEmpresasDesdeJson(String jsonEmpresas) throws FileNotFoundException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);
		//cargar ind predefinidos
		}
	
	public static void persistirEmpresasDesdeJson(String jsonEmpresas) throws FileNotFoundException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);
		
		Repositorio.getInstance().persistirEmpresas();
		
		Repositorio.getInstance().limpiarRepo();
		//cargar ind predefinidos
	}
	
	public static void persistirIndicador(Indicador ind){
		
		
		//TODO: Persist indicador
		
	}
	
}
