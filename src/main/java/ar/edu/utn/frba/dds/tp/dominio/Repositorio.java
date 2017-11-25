package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import javax.persistence.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.tools.ant.taskdefs.Length;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraLexer;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;
import ar.edu.utn.frba.dds.tp.antlr.dds.ParserListener;

public final class Repositorio implements WithGlobalEntityManager{

	private List<Empresa> empresas = new ArrayList<Empresa>();

	private List<Indicador> indicadores = new ArrayList<Indicador>();

	private static Repositorio REPO = null;
	

	private Repositorio() {
		this.empresas = TraerEmpresasDeBD();
		//this.cargarListaDeEmpresas(TraerEmpresasDeBD());

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

	public void limpiarRepoIndicadores() {
		this.indicadores.clear();
	}

	// TODO --Metodos sin persistencia
	public void cargarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarEmpresa(unaEmpresa);
		}
	}
	
	public void cargarActualizarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarActualizarEmpresa(unaEmpresa);
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
			Empresa nuevaEmpresa = new Empresa(unaEmpresaInput.getId(), unaEmpresaInput.getNombre());
			nuevaEmpresa.cargarCuentas(unaEmpresaInput.getCuentas());
			this.empresas.add(nuevaEmpresa);
		}
	}
	
	//Agregar Empresa actualizando la cuenta.
	private void agregarActualizarEmpresa(Empresa unaEmpresaInput) {
		if (existeEmpresaDeNombre(unaEmpresaInput.getNombre())) {
			for (Empresa empresa : empresas) {
				if (empresa.getNombre().equals(unaEmpresaInput.getNombre())) {
					empresa.cargarActualizarCuentas(unaEmpresaInput.getCuentas());
					break;
				}
			}
		} else {
			Empresa nuevaEmpresa = new Empresa(unaEmpresaInput.getId(), unaEmpresaInput.getNombre());
			nuevaEmpresa.cargarActualizarCuentas(unaEmpresaInput.getCuentas());
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

	public List<Cuenta> getCuentasDeEmpresaDeIdPorPeriodo(Integer idEmpresa, Integer periodo) {

		List<Cuenta> listCuentas = null;
		List<Empresa> listEmpresaFiltrada = this.empresas.stream().filter(e -> e.getId() == idEmpresa)
				.collect(Collectors.toList());
		if (listEmpresaFiltrada != null) {
			if (listEmpresaFiltrada.get(0) != null) {
				listCuentas = listEmpresaFiltrada.get(0).getCuentasPorPeriodo(periodo);
			}
		}

		return listCuentas;

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

	public List<Metodologia> buscarMetodologiaPorUser(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}

	public void cargarListaDeMetodologias(List<Metodologia> metodologias) {
		// TODO Auto-generated method stub

	}
	// TODO--

	/**** Metodos de Bases de datos ****/

	public void cargarIndicadoresDesdeBD() {
		Repositorio.getInstance().limpiarRepoIndicadores();
		@SuppressWarnings("unchecked")
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		List<Indicador> indicadores = entityManager.createQuery("SELECT i FROM Indicador i ORDER BY i.nombre DESC")
				.getResultList();
		cargarExpresionesaIndicadores(indicadores);
		Repositorio.getInstance().cargarListaDeIndicadores(indicadores);
	}

	private void cargarExpresionesaIndicadores(List<Indicador> indicadores) {
		for (Indicador indicador : indicadores) {
			CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromString(indicador.getFormula()));
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			CalculadoraParser parser = new CalculadoraParser(tokens);
			CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
			ParserListener listener = new ParserListener();
			listener.cargarExpresionaIndicador(expresionContext, indicador);
		}

	}

	@SuppressWarnings("unchecked")
	public Indicador buscarIndicadorPorNombre(String nombre) {
		List<Indicador> indicadores = null;
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("SELECT i FROM Indicador i where i.nombre like :pnombre");
		indicadores = query.setParameter("pnombre", "%" + nombre + "%").getResultList();

		return indicadores.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Indicador> buscarIndicadorPorUser(Integer idUsuario) {
		List<Indicador> indicadores = null;
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		indicadores = entityManager.createNamedQuery("buscarIndicadorPorUser").setParameter("pIdUsuario", idUsuario)
				.getResultList();
		REPO.cargarExpresionesaIndicadores(indicadores);
		return indicadores;
	}

	@SuppressWarnings("unchecked")
	private boolean existeEmpresaDeNombreenBD(String nombre) {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		List<Empresa> empresas = null;
		empresas = entityManager.createNamedQuery("buscarEmpresaPorNombre").setParameter("pNombre", nombre)
				.getResultList();
		return !empresas.isEmpty();
	}
	
	public int persistirEmpresas() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();

		for (Empresa e : Repositorio.getInstance().getEmpresas()) {
			if (!(existeEmpresaDeNombreenBD(e.getNombre()))) {
				entityManager.persist(e);
			}
		}

		tx.commit();

		return 1; // Success

	}

	public int persistirActualizarEmpresas() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		try{
			for (Empresa e : Repositorio.getInstance().getEmpresas()) {
				if (!(existeEmpresaDeNombreenBD(e.getNombre()))) {
					entityManager.persist(e);
//					tx.commit();
				}else{
					//Si existe, actualizar las cuentas
					for(Cuenta c: e.getCuentas()){
						
						//tx.begin();
						entityManager.persist(c);
						//entityManager.persist(c);
							
					}
				}
					
			}
			tx.commit();
		}catch(Exception exc){
			System.out.println(exc.getMessage());
			tx.rollback();
		}
		return 1; // Success

	}

	public String persistirIndicadores() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		for (Indicador i : Repositorio.getInstance().getIndicadores()) {
			if (!(existeIndicadorDeNombreEnBD(i.getNombre()))) {
				entityManager.persist(i);
			}
		}
		try {
			tx.commit();
			return "Se ha guardado el indicador OK"; // Success
		} catch (PersistenceException error) {
			return "No se guardo el indicador - Persistence"; // Error de
																// persistencia
		}

		// catch(IllegalStateException error){
		// return "No se guardo el indicador - Illegal"; //Error de persistencia
		// }
	}

	public String persistirIndicador(String nombreIndicador) {
		try {
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			
			for (Indicador i : Repositorio.getInstance().getIndicadores()) {
				if (i.getNombre() == nombreIndicador)
					if (!(existeIndicadorDeNombreEnBD(i.getNombre()))) {
						entityManager.persist(i);
					}
			}
			tx.commit();
			return "Se ha guardado el indicador OK"; // Success
		} catch (PersistenceException error) {
			return "No se guardo el indicador - Persistence"; // Error de
																// persistencia
		}

		catch (IllegalStateException error) {
			return "No se guardo el indicador - Illegal"; // Error de
															// persistencia
		}
	}

	private boolean existeIndicadorDeNombreEnBD(String nombre) {
		List<Indicador> indicadores = null;
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("SELECT i FROM Indicador i where i.nombre like :pnombre");
		indicadores = query.setParameter("pnombre", nombre).getResultList();
		return !indicadores.isEmpty();
	}

	public int persistirUsuarios(List<Usuario> usuarios) {
		 EntityManager entityManager =
		 PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();

		for (Usuario u : usuarios) {
			if ((Repositorio.getInstance().getUsuarioByUserAndPwd(u.getNombre(), u.getPassword()) == null)) {
				entityManager.persist(u);
			}
		}

		tx.commit();

		return 1; // Success

	}

	public List<Empresa> TraerEmpresasDeBD() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("FROM Empresa");
		List<Empresa> empresas = query.getResultList();
		System.out.print(empresas.size());
		return empresas;
	}


	public Usuario buscarUserPorId(Integer pidUsuarioActivo) throws Exception {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("SELECT u FROM Usuario u where u.id = :pidUsuarioActivo");
		List<Usuario> users = query.setParameter("pidUsuarioActivo", pidUsuarioActivo).getResultList();
		if (users.isEmpty()){
			throw new Exception("El usuario no existe en la BD");
			//return new Usuario(0, "GENERICO");
		}
			
		else
			return users.get(0);
	}

	/*** U S U A R I O S ***/
	public Usuario getUsuarioByUserAndPwd(String nombreUsuario, String pwd) {
		List<Usuario> listUsers = null;

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

		listUsers = entityManager.createNamedQuery("buscarUsuario").setParameter("pNombre", nombreUsuario)
				.setParameter("pPassword", pwd).getResultList();

		return (listUsers.size() > 0 ? listUsers.get(0) : null);
	}

	/***** GETTERS Y SETTERS *******/
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
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

}
