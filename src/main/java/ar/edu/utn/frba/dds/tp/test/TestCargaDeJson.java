package ar.edu.utn.frba.dds.tp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import ar.edu.utn.frba.dds.tp.dominio.AdapterJson;
import ar.edu.utn.frba.dds.tp.dominio.Cuenta;
import ar.edu.utn.frba.dds.tp.dominio.Empresa;
import ar.edu.utn.frba.dds.tp.dominio.IniciarAplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class TestCargaDeJson {
	private Empresa ibm;
	private Empresa ypf;
	private Empresa axion;
	private Repositorio repo;
	private Cuenta fds16;
	private Cuenta fds17;
	private Cuenta ind16;
	private Cuenta ind17;
	private Cuenta ind17v2;

	private ArrayList<Cuenta> cuentasIBM;
	private ArrayList<Cuenta> cuentasYPF;
	private ArrayList<Cuenta> cuentasAxion;
	private ArrayList<Empresa> listaEmpresas;
	private ArrayList<Empresa> listaEmpresas2;

	private Gson gson;
	private String representacionJSON;
	private String representacionJSON2;

	@Before
	public void SetUp() {

		this.repo = Repositorio.getInstance();
		this.fds16 = new Cuenta("FDS", (long) 3000025, 2016);
		this.fds17 = new Cuenta("FDS", (long) 3000000, 2017);
		this.ind16 = new Cuenta("INDICADOR", (long) 1000, 2016);
		this.ind17 = new Cuenta("INDICADOR", (long) 2000, 2017);
		this.ind17v2 = new Cuenta("INDICADOR", (long) 5620, 2014);

		this.cuentasIBM = new ArrayList<Cuenta>();

		this.cuentasIBM.add(fds16);
		this.cuentasIBM.add(fds17);
		this.cuentasYPF = new ArrayList<Cuenta>();
		this.cuentasYPF.add(ind16);
		this.cuentasYPF.add(ind17);
		this.cuentasYPF.add(ind17v2);
		this.cuentasAxion = new ArrayList<Cuenta>();
		this.cuentasAxion.add(ind17v2);
		this.cuentasAxion.add(ind17v2);

		this.ibm = new Empresa("IBM", cuentasIBM);
		this.ypf = new Empresa("YPF", cuentasYPF);
		this.axion = new Empresa("AXION", cuentasAxion);

		this.listaEmpresas = new ArrayList<Empresa>();
		this.listaEmpresas.add(ibm);
		this.listaEmpresas.add(ypf);

		this.listaEmpresas2 = new ArrayList<Empresa>();
		this.listaEmpresas2.add(ibm);
		this.listaEmpresas2.add(axion);

		this.gson = new Gson();
		representacionJSON = gson.toJson(listaEmpresas);
		representacionJSON2 = gson.toJson(listaEmpresas2);
	}

	@Test
	public void debeDevolverLaRepresentacionJSONDeUnaListaDeEmpresas() {
		// System.out.println(representacionJSON);
	}

	@Test
	public void debeTomarUnJsonYDevolverUnaListaDeEmpresas() {

		final String listaEmpresasJson = representacionJSON;
		List<Empresa> listaEmpresasTest = new ArrayList<Empresa>();

		listaEmpresasTest = AdapterJson.transformarDeJSONaListaEmpresas(listaEmpresasJson);
		assertEquals(listaEmpresasTest.size(), 2);

		// for (Empresa unaEmpresa : listaEmpresasTest) {
		// unaEmpresa.consultarCuentas();
		// }
	}

	@Test
	public void debeProbarMetodoExisteCuentaDeNombre() {
		assertTrue(ibm.existeCuentaDeNombre("FDS"));
		assertFalse(ibm.existeCuentaDeNombre("INDICADOR"));
		assertTrue(ypf.existeCuentaDeNombre("INDICADOR"));
	}
	
	@Test
	public void debePasarJsonAListadeEmpresasYContenerLasCuentasSinDuplicar() {
		repo.limpiarRepo();
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON);
		assertEquals(repo.cantidadEmpresas(), (Integer) 2);
		assertTrue(repo.existeEmpresaDeNombreConCuenta("IBM", "FDS"));
		assertTrue(repo.existeEmpresaDeNombreConCuenta("IBM", "FDS"));
	}
	
	@Test
	public void debePasarJsonAListadeEmpresasYCargarloEnReposinDuplicar() {
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON);
		assertEquals(repo.cantidadEmpresas(), (Integer) 2);
		assertTrue(repo.existeEmpresaDeNombre("IBM"));
		assertTrue(repo.existeEmpresaDeNombre("YPF"));
		assertFalse(repo.existeEmpresaDeNombre("AXION"));

		repo.devolverCuentasDeEmpresaDeNombre("IBM");
		/*
		 * Las cuentas de la lista cuentasYPF tienen dos cuentas del mismo
		 * nombre y distinto periodo vemos como se guardaron las 2
		 */
		repo.devolverCuentasDeEmpresaDeNombre("YPF");

		/*
		 * El Json 2 Tiene duplicada la Empresa IBM respecto del Json1,
		 * cargaremos este al Repo y verificaremos que no se duplican
		 */
		IniciarAplicacion.cargarEmpresasDesdeJson(representacionJSON2);
		assertEquals(repo.cantidadEmpresas(), (Integer) 3);
		assertTrue(repo.existeEmpresaDeNombre("IBM"));
		assertTrue(repo.existeEmpresaDeNombre("YPF"));
		assertTrue(repo.existeEmpresaDeNombre("AXION"));

		/*
		 * A su vez las cuentas de la lista cuentasAxion estan duplicadas pero
		 * vemos que en el repo solo se cargo una instancia
		 */

		repo.devolverCuentasDeEmpresaDeNombre("AXION");

	}
	
	
}