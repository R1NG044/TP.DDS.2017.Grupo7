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
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	private Repositorio() {
		this.cargarListaDeEmpresas(TraerEmpresasDeBD());
		//this.cargarIndicadoresDesdeBD(entityManager.createNamedQuery("buscarIndicadorPorUser").setParameter("pIdUsuario", "").getResultList());
		// Instanciar EntityManager
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

	// TODO --Métodos sin persistencia
	public void cargarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarEmpresa(unaEmpresa);
		}

	}
	public void cargarListaDeIndicadores(List<Indicador> indicadores) {
		for (Indicador indicador : indicadores) {
			this.agregarIndicador(indicador);
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
	// TODO--

	/**** Metodos de Bases de datos ****/

	// Metodo dummy mockeado
	public void cargarIndicadoresDesdeBD() {
		 Query query = entityManager.createQuery("SELECT i FROM Indicador i");
		 List<Indicador> indicador = query.getResultList();
		 	 
	}

	@SuppressWarnings("unchecked")
	public List<Indicador> buscarIndicadorPorNombre(String nombre) {
		List<Indicador> indicadores = null;

		indicadores = entityManager.createNamedQuery("buscarIndicadorPorNombre")
				.setParameter("pnombre", "%" + nombre + "%").getResultList();
		return indicadores;
	}

	@SuppressWarnings("unchecked")
	public List<Indicador> buscarIndicadorPorUser(Integer idUsuario) {
		List<Indicador> indicadores = null;

		indicadores = entityManager.createNamedQuery("buscarIndicadorPorUser").setParameter("pIdUsuario", idUsuario)
				.getResultList();
		return indicadores;
	}

	@SuppressWarnings("unchecked")
	private boolean existeEmpresaDeNombreenBD(String nombre) {

		List<Empresa> empresas = null;
		empresas = entityManager.createNamedQuery("buscarEmpresaPorNombre").setParameter("pNombre", nombre)
				.getResultList();
		return !empresas.isEmpty();
	}

	public int persistirEmpresas() {

		EntityTransaction tx = entityManager.getTransaction();

		for (Empresa e : Repositorio.getInstance().getEmpresas()) {
			if (!(existeEmpresaDeNombreenBD(e.getNombre()))) {
				entityManager.persist(e);
			}
		}

		tx.commit();

		return 1; // Success
		
		
	}

	public List<Empresa> TraerEmpresasDeBD() {
		 Query query = entityManager.createQuery("SELECT e FROM Empresa e");
		 List<Empresa> empresas = query.getResultList();
		 System.out.print(empresas.size());
		 return empresas;
	}
	public Usuario buscarUserPorId(Integer pidUsuarioActivo) {
		 Query query = entityManager.createQuery("SELECT u FROM Usuario u where u.id = :pidUsuarioActivo");
		 List<Usuario> users = query.setParameter("pidUsuarioActivo", pidUsuarioActivo).getResultList();
		
		 return users.get(0);
	}
	
	/***** GETTERS Y SETTERS *******/
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	/*** U S U A R I O S ***/
	public Usuario getUsuarioByUserAndPwd(String nombreUsuario, String pwd){
		List<Usuario> listUsers = null;
		
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		listUsers = entityManager.createNamedQuery("buscarUsuario").setParameter("pNombre", nombreUsuario).setParameter("pPassword", pwd).getResultList();
		
		return (listUsers.size() > 0? listUsers.get(0): null);
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	/*
	 * Métodos para implementar KIE public List<IndicadorNodo>
	 * getIndicadoresEvaluados(Integer periodo) { // devuelve lista de
	 * indicadores que han sido cargados en memoria y su // evaluacion para
	 * todas las empresas // cargadas en el json, en el periodo provisto.
	 * 
	 * return new ArrayList<IndicadorNodo>(); }
	 */

	public List<Metodologia> buscarMetodologiaPorUser(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cargarListaDeMetodologias(List<Metodologia> metodologias) {
		// TODO Auto-generated method stub
		
	}

	
}
