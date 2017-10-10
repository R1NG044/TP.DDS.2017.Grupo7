package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import java.util.List;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;

public final class Repositorio {

	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<Indicador> indicadores = new ArrayList<Indicador>();
	
	private static Repositorio REPO = null;
	//EntityManager como propiedad del repositorio.

	private Repositorio() {
		
		//Instanciar EntityManager 
	}

	public static Repositorio getInstance() {

		if (REPO == null) {

			REPO = new Repositorio();
		}

		return REPO;
	}

	public void limpiarRepo() {
		this.empresas.clear();
	}

	public void cargarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarEmpresa(unaEmpresa);
		}

	}

	public void agregarIndicador(Indicador unIndicadorInput) {
		if (!(existeIndicadorDeNombre(unIndicadorInput.getNombre()))) {
			this.indicadores.add(unIndicadorInput);
		} else {
			System.out.print("Ya Existe Indicador en Base");
		}
	}

	private boolean existeIndicadorDeNombre(String nombreIndicador) {
		for (Indicador indicador : indicadores) {
			if (indicador.getNombre().equals(nombreIndicador)) {
				return true;
			}
		}
		return false;
	}

	private void agregarEmpresa(Empresa unaEmpresaInput) {
		if (existeEmpresaDeNombre(unaEmpresaInput.getNombre())) {
			for (Empresa empresa : empresas) {
				if (empresa.getNombre().equals(unaEmpresaInput.getNombre())) {
					empresa.cargarCuentas(unaEmpresaInput.getCuentas());
					break;
				}
			}
		} else {
			Empresa nuevaEmpresa = new Empresa(unaEmpresaInput.getNombre());
			nuevaEmpresa.cargarCuentas(unaEmpresaInput.getCuentas());
			this.empresas.add(nuevaEmpresa);
		}
	}

	public boolean existeEmpresaDeNombre(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				return true;
			}
		}
		return false;
	}

	public Empresa darEmpresaDeNombre(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				return empresa;
			}
		}
		throw new RuntimeException("No existe la Empresa ");
	}

	public void devolverCuentasDeEmpresaDeNombre(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				empresa.consultarCuentas();
				;
			}
		}
	}

	public int cantidadEmpresas() {
		return empresas.size();
	}

	public int cantidadDeCuentasParaEmpresa(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				return empresa.cantidadDeCuentas();
			}
		}
		return 0;
	}

	public boolean existeEmpresaDeNombreConCuenta(String nombreEmpresa, String unNombreDeCuenta) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				if (empresa.existeCuentaDeNombre(unNombreDeCuenta)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean existeIndicador(String nombreIndicador) {
		for (Indicador indicador : indicadores) {
			if (indicador.getNombre().equals(nombreIndicador)) {
				return true;
			}
		}
			return false;
	}
	public Indicador darIndicadorDeNombre(String nombreIndicador) {
		for (Indicador indicador : indicadores) {
			if (indicador.getNombre().equals(nombreIndicador)) {
				return indicador;
			}
		}
		throw new RuntimeException("No existe el Indicador ");
	}
	
	

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	public List<IndicadorNodo> getIndicadoresEvaluados(Integer periodo){
		//devuelve lista de indicadores que han sido cargados en memoria y su evaluacion para todas las empresas
		//cargadas en el json, en el periodo provisto.
		
		
		return new ArrayList<IndicadorNodo>();
	}
	
	/**** Metodos de Bases de datos ****/
	
	//Metodo dummy mockeado
	public void cargarIndicadoresDesdeBD(){
		Indicador ind1 = new Indicador("ROE");
		Indicador ind2 = new Indicador("SuperIndice");
		
		List<Indicador> indicadores = null;
		//indicadores = e
		
		indicadores.add(ind1);
		indicadores.add(ind2);
		
	}
	
	
	public List<Indicador> buscarIndicadorPorNombre(String nombre) {
		List<Indicador> indicadores = null;
		
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		indicadores = entityManager.createNamedQuery("buscarIndicadorPorNombre").setParameter("pnombre", "%" + nombre +
		"%").getResultList();
		return indicadores;
	}
	
	public int persistirEmpresas(){
		 
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		for(Empresa e:Repositorio.getInstance().getEmpresas()){
			entityManager.persist(e);
			
		}
		
		tx.commit();
		
		return 1; //Success
	}

}