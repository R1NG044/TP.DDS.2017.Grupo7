package ar.edu.utn.frba.dds.tp.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tp.dominio.Empresa;
import ar.edu.utn.frba.dds.tp.dominio.IniciarAplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.herramientas.AdapterJson;

public class TestCargaDeJson {
	private Repositorio repo;
	private String representacionJSON;
	private String representacionJSON2;

	@Before
	public void SetUp() {
		this.representacionJSON = "H:\\descargas\\DDS\\TP.DDS.2017.Grupo7\\archivosInput\\empresasjson1.txt";
		this.representacionJSON2 = "H:\\descargas\\DDS\\TP.DDS.2017.Grupo7\\archivosInput\\empresasjson2.txt";
		this.repo = Repositorio.getInstance();
	}

	//

	@Test
	public void debeTomarUnJsonYDevolverUnaListaDeEmpresas() throws FileNotFoundException {

		// IMPORTANTE: Para que no arroje error se debe modificar el path segun
		// donde se encuentre guardado tu Proyecto. Si el path es erroneo arroja
		// FileNotFound Error

		List<Empresa> listaEmpresasTest1 = new ArrayList<Empresa>();
		List<Empresa> listaEmpresasTest2 = new ArrayList<Empresa>();

		// Se cargan los dos archivos txt de Json con el Adapter a las listas
		// auxiliares
		listaEmpresasTest1 = AdapterJson.transformarDeJSONaListaEmpresas(representacionJSON);
		listaEmpresasTest2 = AdapterJson.transformarDeJSONaListaEmpresas(representacionJSON2);

		// Se verifica que carga las Empresas en cada lista
		assertEquals(listaEmpresasTest1.size(), 2);
		assertEquals(listaEmpresasTest2.size(), 4);

	}

	@Test
	public void debePasarJsonAListadeEmpresasYContenerLasEmpresasSinDuplicar() throws FileNotFoundException {
		// El Json1 contiene 2 Empresas IBM e YPF
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON);
		assertEquals(repo.cantidadEmpresas(), 2);
		assertTrue(repo.existeEmpresaDeNombre("IBM"));
		assertTrue(repo.existeEmpresaDeNombre("YPF"));
		assertFalse(repo.existeEmpresaDeNombre("AXION"));
		assertFalse(repo.existeEmpresaDeNombre("PETROBRAS"));

		// El Json2 contiene 3 Empresas IBM y Axion. Cuando se cargue en el Repo
		// no debe duplicarse IBM.
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON2);
		assertEquals(repo.cantidadEmpresas(), 4);
		assertTrue(repo.existeEmpresaDeNombre("IBM"));
		assertTrue(repo.existeEmpresaDeNombre("YPF"));
		assertTrue(repo.existeEmpresaDeNombre("AXION"));
		assertTrue(repo.existeEmpresaDeNombre("PETROBRAS"));
		repo.limpiarRepo();
	}

	@Test
	public void debePasarJsonAListadeEmpresasYCargarloEnReposinDuplicarCuentas() throws FileNotFoundException {
		/*
		 * Para la Empresa YPF el Json tienen la cuenta INDICADOR periodo 2017
		 * duplicada, vemos como se guardo una sola.
		 */
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON);
		assertEquals(repo.cantidadEmpresas(), 2);
		assertEquals(repo.cantidadDeCuentasParaEmpresa("IBM"), 2);
		assertEquals(repo.cantidadDeCuentasParaEmpresa("YPF"), 3);
		assertTrue(repo.existeEmpresaDeNombreConCuenta("IBM", "FDS"));
		assertTrue(repo.existeEmpresaDeNombreConCuenta("YPF", "INDICADOR"));

		// repo.devolverCuentasDeEmpresaDeNombre("IBM");
		// repo.devolverCuentasDeEmpresaDeNombre("YPF");

		/*
		 * El Json 2 Tiene duplicada la Empresa IBM respecto del Json1,
		 * cargaremos este al Repo y verificaremos que no se duplican ni la
		 * empresa ni las cuentas. Y se agregan las empresas Axion y Petrobras.
		 */
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON2);
		assertEquals(repo.cantidadEmpresas(), 4);
		assertEquals(repo.cantidadDeCuentasParaEmpresa("IBM"), 3);
		assertEquals(repo.cantidadDeCuentasParaEmpresa("YPF"), 3);
		assertTrue(repo.existeEmpresaDeNombreConCuenta("IBM", "DIVIDENDOS"));
		assertTrue(repo.existeEmpresaDeNombreConCuenta("YPF", "INDICADOR"));
		/*
		 * A su vez la Empresa Axion esta duplicada pero con cuentas distintas
		 * vemos que en el repo solo se cargo una empresa con las 2 cuentas
		 */
		assertEquals(repo.cantidadDeCuentasParaEmpresa("AXION"), 2);
		assertEquals(repo.cantidadDeCuentasParaEmpresa("PETROBRAS"), 2);

		// repo.devolverCuentasDeEmpresaDeNombre("IBM");
		// repo.devolverCuentasDeEmpresaDeNombre("YPF");
		// repo.devolverCuentasDeEmpresaDeNombre("AXION");
		// repo.devolverCuentasDeEmpresaDeNombre("PETROBRAS");
		repo.limpiarRepo();
	}

	// @Test
	// public void debeDevolverLaRepresentacionJSONDeUnaListaDeEmpresas() {
	// System.out.println(representacionJSON2);
	// }

}