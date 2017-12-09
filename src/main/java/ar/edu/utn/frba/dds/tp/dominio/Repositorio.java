package ar.edu.utn.frba.dds.tp.dominio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraLexer;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;
import ar.edu.utn.frba.dds.tp.antlr.dds.IndicadorEmpresa;
import ar.edu.utn.frba.dds.tp.antlr.dds.ParserListener;
import ar.edu.utn.frba.dds.tp.jobs.HistorialCargaBatch;

public final class Repositorio implements WithGlobalEntityManager {

	private static String INPUT_PATH = null;

	private List<Empresa> empresas = new ArrayList<Empresa>();

	private List<Indicador> indicadores = new ArrayList<Indicador>();
	
	private ArrayList<Metodologia> metodologias = new ArrayList<Metodologia>();

	private static Repositorio REPO = null;

	private Repositorio() {
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

	public void cargarListaDeIndicadores(List<Indicador> indicadores) throws Exception {
		for (Indicador indicador : indicadores) {
			this.agregarIndicador(indicador);
		}

	}

	public String agregarIndicador(Indicador unIndicadorInput) throws Exception {
		if (!(existeIndicadorDeNombre(unIndicadorInput.getNombre()))) {
			this.indicadores.add(unIndicadorInput);
			return this.persistirIndicador(unIndicadorInput);
		} else {
			System.out.print("Ya Existe Indicador en Base");
			return "Ya existe el indicador";

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

	// Agregar Empresa actualizando la cuenta.
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

	public void agregarMetodologia(Metodologia m){
		metodologias.add(m);
	}
	
	public Metodologia buscarMetodologiaPorNombreYTipo(String nombreMetodologia, String tipoMetodologia) {
		
		for (Metodologia m: metodologias){
			if (m.getNombre().equals(nombreMetodologia) && m.getTipo().equals(tipoMetodologia)){
				return m;
			}
		}
		
		return null;
	}
	
	public boolean buscarMetodologiaPorNombre(ArrayList<Metodologia> list, String nombre){
		for(Metodologia m: list){
			if(m.getNombre().equals(nombre)){
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Metodologia> getAllMetodologiasDistinct(){
		ArrayList<Metodologia> listaMetodologiasDistinct = new ArrayList<Metodologia>();
		
		for (Metodologia m: metodologias){
			if(!buscarMetodologiaPorNombre(listaMetodologiasDistinct, m.getNombre())){
				listaMetodologiasDistinct.add(m);
			}
		}
		
		return listaMetodologiasDistinct;
		
	}
	
	public void cargarListaDeMetodologias(List<Metodologia> metodologias) {
		// TODO Auto-generated method stub

	}

	/**** Metodos de Bases de datos ****/

	public void ActualizarIndicadoresPrecargados() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		// Actualizo valores precargados
		for (Indicador i : this.getIndicadores()) {
			try {
				cargarIndicadoresEmpresaParaIndicador(i);
				
				entityManager.merge(i);

				System.out.println( "Se ha guardado el indicador OK"); // Success

			} catch (

			PersistenceException error) {
				System.out.println("No se guardo el indicador - Persistence"); // Error de
																	// persistencia
			}

			catch (IllegalStateException error) {
				System.out.println("No se guardo el indicador - Illegal"); // Error de
																// persistencia
			}
		}
		tx.commit();
	}

	public void cargarIndicadoresDesdeBDPorUser(Integer usuario) {
		try {
			this.buscarIndicadoresPorUser(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cargarIndicadoresDesdeBD() throws Exception {
		// Repositorio.getInstance().limpiarRepoIndicadores();
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		@SuppressWarnings("unchecked")
		List<Indicador> indicadores = entityManager.createQuery("SELECT i FROM Indicador i ORDER BY i.idIndicador ASC")
				.getResultList();
		cargarExpresionesaIndicadores(indicadores);
		this.setIndicadores(indicadores);
	}

	private void cargarExpresionesaIndicadores(List<Indicador> indicadores) throws Exception {
		int i = 1;
		for (Indicador indicador : indicadores) {
			if (indicador.getIdIndicador() == i) {
				CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromString(indicador.getFormula()));
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				CalculadoraParser parser = new CalculadoraParser(tokens);
				CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
				ParserListener listener = new ParserListener();
				listener.cargarExpresionaIndicador(expresionContext, indicador);
				i++;
			}
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
	public void buscarIndicadoresPorUser(Integer idUsuario) throws Exception {
		// List<Indicador> indicadores = null;
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		this.setIndicadores(entityManager.createNamedQuery("buscarIndicadorPorUser")
				.setParameter("pIdUsuario", idUsuario).getResultList());
		this.cargarExpresionesaIndicadores(this.getIndicadores());
		// return indicadores;
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

		try {
			for (Empresa e : Repositorio.getInstance().getEmpresas()) {

				entityManager.persist(e);
			}

			tx.commit();
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
			tx.rollback();
		}
		return 1; // Success

	}

	public String persistirIndicador(Indicador nuevoIndicador) {
		try {
			cargarIndicadoresEmpresaParaIndicador(nuevoIndicador);
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			entityManager.persist(nuevoIndicador);

			tx.commit();

			return "Se ha guardado el indicador OK"; // Success

		} catch (

		PersistenceException error) {
			return "No se guardo el indicador - Persistence"; // Error de
																// persistencia
		}

		catch (IllegalStateException error) {
			return "No se guardo el indicador - Illegal"; // Error de
															// persistencia
		}
	}

	private void cargarIndicadoresEmpresaParaIndicador(Indicador nuevoIndicador) {
		List<IndicadorEmpresa> listaIe = new ArrayList<IndicadorEmpresa>();
		for (Integer periodo = 2017; periodo < 2018; periodo++) {
			for (Empresa e : empresas) {
				try {
					double valor = nuevoIndicador.evaluarIndicador(e.getNombre(), periodo);
					IndicadorEmpresa ie = new IndicadorEmpresa(nuevoIndicador, e.getNombre(), periodo, valor);
					listaIe.add(ie);
				} catch (Exception error) {
					System.out.println(error.getMessage());
				}
			}
		}
		nuevoIndicador.setIndicadoresEmpresas(listaIe);
	}

	public boolean existeIndicadorDeNombreEnBD(String nombre) {
		List<Indicador> indicadores = null;
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("SELECT i FROM Indicador i where i.nombre like :pnombre");
		indicadores = query.setParameter("pnombre", nombre).getResultList();
		return !indicadores.isEmpty();
	}

	public boolean existenIndicadoresEnBD() {
		List<Indicador> indicadores = null;
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("SELECT i FROM Indicador i ");
		indicadores = query.getResultList();
		return !indicadores.isEmpty();
	}

	public void cargarEmpresasDeBD() {

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery(" select e FROM Empresa e");
		List<Empresa> empresas = query.getResultList();
		this.setEmpresas(empresas);
	}

	/*** U S U A R I O S ***/
	public Usuario getUsuarioByUserAndPwd(String nombreUsuario, String pwd) {
		List<Usuario> listUsers = null;

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

		listUsers = entityManager.createNamedQuery("buscarUsuario").setParameter("pNombre", nombreUsuario)
				.setParameter("pPassword", pwd).getResultList();

		return (listUsers.size() > 0 ? listUsers.get(0) : null);
	}

	public Usuario buscarUserPorId(Integer pidUsuarioActivo) throws Exception {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createQuery("SELECT u FROM Usuario u where u.id = :pidUsuarioActivo");
		List<Usuario> users = query.setParameter("pidUsuarioActivo", pidUsuarioActivo).getResultList();
		if (users.isEmpty()) {
			throw new Exception("El usuario no existe en la BD");
			// return new Usuario(0, "GENERICO");
		}

		else
			return users.get(0);
	}

	public void persistirUsuarios(List<Usuario> usuarios) {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		try {
			for (Usuario u : usuarios) {
				if ((Repositorio.getInstance().getUsuarioByUserAndPwd(u.getNombre(), u.getPassword()) == null)) {
					entityManager.persist(u);
				}
			}
			tx.commit();
		} catch (IllegalStateException i) {
			tx.rollback();

		}
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

	// public String persistirIndicadores() {
	// EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	// EntityTransaction tx = entityManager.getTransaction();
	// for (Indicador i : Repositorio.getInstance().getIndicadores()) {
	// if (!(existeIndicadorDeNombreEnBD(i.getNombre()))) {
	// entityManager.persist(i);
	// }
	// }
	// try {
	// tx.commit();
	// return "Se ha guardado el indicador OK"; // Success
	// } catch (PersistenceException error) {
	// return "No se guardo el indicador - Persistence"; // Error de
	// // persistencia
	// }
	//
	// catch (IllegalStateException error) {
	// return "No se guardo el indicador - Illegal"; // Error de
	// // persistencia
	// }
	// }
	/***** PROCESO CARGA BATCH ****/

	// Solo procesa el archivo, si el mismo no ha sido ya procesado (verifica
	// fecha modif)
	public boolean esArchivoYaProcesado(FileTime ultimaFechaModificacion) {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query q = entityManager
				.createQuery("SELECT h from HistorialCargaBatch h ORDER BY h.ultimaFechaModificacion desc");
		q.setMaxResults(1);
		List<HistorialCargaBatch> resultados = q.getResultList();

		if (resultados.size() == 0) {
			return false;
		} else {
			return (ultimaFechaModificacion.toMillis() == resultados.get(0).getUltimaFechaModificacion());
		}
	}

	public void cargarIndicadoresPredefinidos() throws IOException {
		INPUT_PATH = "/IngresoNeto.txt";
		InputStream file = getInputFilePath();
		String formula = IOUtils.toString(file, StandardCharsets.ISO_8859_1.name());
		Integer idUsuarioPredefinidos = 1;
		Aplicacion.guardarUnIndicador("INGRESONETO", formula, idUsuarioPredefinidos);

		INPUT_PATH = "/ROE.txt";
		InputStream file2 = this.getInputFilePath();
		String formula2 = IOUtils.toString(file2, StandardCharsets.ISO_8859_1.name());
		Aplicacion.guardarUnIndicador("ROE", formula2, idUsuarioPredefinidos);

	}

	private InputStream getInputFilePath() {
		return this.getClass().getResourceAsStream(INPUT_PATH);

	}

}
