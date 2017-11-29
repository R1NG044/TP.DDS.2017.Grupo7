package ar.edu.utn.frba.dds.tp.test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Metodologia;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;

public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	//private Repositorio repo;
	private String representacionJSON;
	private String representacionJSON3;
	private String representacionJSON4;
	private Integer idUsuarioPredefinidos = 1;

	public static String INPUT_PATH;

	@Before
	public void SetUp() {
		this.representacionJSON = "/empresasJson1.txt";
		this.representacionJSON3 = "/empresasJson3.txt";
		this.representacionJSON4 = "/empresasJson4.txt";

		

		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("general", "123"));
		usuarios.add(new Usuario("brenda.stolarz@gmail.com", "654321"));
		usuarios.add(new Usuario("nadia@utn.edu.ar", "nadia"));
		usuarios.add(new Usuario("ale@utn.edu.ar", "ale"));
		Repositorio.getInstance().persistirUsuarios(usuarios);
		
		//Aplicacion.cargaDesdeBDaRepoPorUser(idUsuarioPredefinidos);
		
	}
	@Test
	public void cargarActualizarJSONEmpresasYCuentasABaseDeDatos() throws FileNotFoundException {
		/*
		 * Para la Empresa YPF el Json tienen la cuenta INDICADOR periodo 2017
		 * duplicada, vemos como se guardo una sola.
		 */
		// Aplicacion.cargarEmpresasDesdeJson(getInputFilePath(representacionJSON));

		/*
		 * El Json 2 Tiene duplicada la Empresa IBM respecto del Json1,
		 * cargaremos este al Repo y verificaremos que no se duplican ni la
		 * empresa ni las cuentas. Y se agregan las empresas Axion y Petrobras.
		 */
		Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath(representacionJSON));
		Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath(representacionJSON4));
		Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath(representacionJSON3));
		/*
		 * A su vez la Empresa Axion esta duplicada pero con cuentas distintas
		 * vemos que en el repo solo se cargo una empresa con las 2 cuentas
		 */

	}


	@Test
	public void persistirMetodologias() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		Metodologia m = new Metodologia("Buffet", new Usuario(1, "Brenda"));

		entityManager.persist(m);

		tx.commit();

	}

	@Test()
	public void testGuardarIndicadorINGRESONETO() throws IOException {
		Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath(representacionJSON3));
		INPUT_PATH = "/IngresoNeto.txt";
		InputStream file = this.getInputFilePath();
		String formula = IOUtils.toString(file, StandardCharsets.ISO_8859_1.name());
		String mensaje = Aplicacion.guardarUnIndicador("INGRESONETO", formula, idUsuarioPredefinidos);
		System.out.println(mensaje + " INGRESONETO");

		assertTrue(Repositorio.getInstance().existeIndicadorDeNombreEnBD("INGRESONETO"));
	}

	@Test()
	public void testGuardarIndicadorROE() throws IOException {
		
		Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath(representacionJSON3));
		INPUT_PATH = "/ROE.txt";
		InputStream file2 = this.getInputFilePath();
		String formula2 = IOUtils.toString(file2, StandardCharsets.ISO_8859_1.name());
		String mensaje2 = Aplicacion.guardarUnIndicador("ROE", formula2, idUsuarioPredefinidos);
		System.out.println(mensaje2 + " ROE");
		assertTrue(Repositorio.getInstance().existeIndicadorDeNombreEnBD("ROE"));
	}
	@Test()
	public void testGuardarIndicadorROI() throws IOException {
		
		Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath(representacionJSON3));
		//INPUT_PATH = "/ROE.txt";
		//InputStream file2 = this.getInputFilePath();
		String formula2 = "IND(ROE)+1";
		String mensaje2 = Aplicacion.guardarUnIndicador("ROI", formula2, idUsuarioPredefinidos);
		System.out.println(mensaje2 + " ROI");
		assertTrue(Repositorio.getInstance().existeIndicadorDeNombreEnBD("ROI"));
	}

	/**** Empresas y cuentas ****/

	
	// @Test
	// public void lala(){
	// List<Empresa> empresas =
	// PerThreadEntityManagers.getEntityManager().createQuery("from
	// Empresa").getResultList();
	// for (Empresa empresa : empresas) {
	// for (Cuenta cuenta : empresa.getCuentas()) {
	// System.out.println(cuenta.getEmpresa().getId());
	// }
	// }
	// }
	//

	private String getInputFilePath(String input) {
		return this.getClass().getResource(input).getPath();
	}

	private InputStream getInputFilePath() {
		return this.getClass().getResourceAsStream(INPUT_PATH);

	}

	/** TEST VIEJOS **/
	// @Test
	// public void cargarJSONEmpresasYCuentasABaseDeDatos() throws
	// FileNotFoundException {
	/*
	 * Para la Empresa YPF el Json tienen la cuenta INDICADOR periodo 2017
	 * duplicada, vemos como se guardo una sola.
	 */
	// Aplicacion.cargarEmpresasDesdeJson(getInputFilePath(representacionJSON));

	/*
	 * El Json 2 Tiene duplicada la Empresa IBM respecto del Json1, cargaremos
	 * este al Repo y verificaremos que no se duplican ni la empresa ni las
	 * cuentas. Y se agregan las empresas Axion y Petrobras.
	 */

	// Aplicacion.persistirEmpresasDesdeJson(getInputFilePath(representacionJSON4));

	/*
	 * * A su vez la Empresa Axion esta duplicada pero con cuentas distintas
	 * vemos que en el repo solo se cargo una empresa con las 2 cuentas
	 */
	// }

}
