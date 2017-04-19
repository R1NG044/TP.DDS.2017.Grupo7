package org.grupo7.tp.test;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.grupo7.tp.dominio.AdapterJson;
import org.grupo7.tp.dominio.Cuenta;
import org.grupo7.tp.dominio.Empresa;
import org.grupo7.tp.dominio.Repositorio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestCargaDeJson {
	private Empresa ibm;
	private Empresa ypf;
	private Repositorio repo;
	private Cuenta fds16;
	private Cuenta fds17;
	private Cuenta ind16;
	private Cuenta ind17;

	private ArrayList<Cuenta> cuentasIBM;
	private ArrayList<Cuenta> cuentasYPF;
	private ArrayList<Empresa> listaEmpresas;

	@Before
	public void SetUp() {
		this.repo = Repositorio.getInstance();
		this.fds16 = new Cuenta("FDS", 3000025, 2016);
		this.fds17 = new Cuenta("FDS", 3000000, 2017);
		this.ind16 = new Cuenta("INDICADOR", 1000, 2016);
		this.ind17 = new Cuenta("INDICADOR", 2000, 2017);
		this.cuentasIBM = new ArrayList<Cuenta>();

		this.cuentasIBM.add(fds16);
		this.cuentasIBM.add(fds17);
		this.cuentasYPF = new ArrayList<Cuenta>();
		this.cuentasYPF.add(ind16);
		this.cuentasYPF.add(ind17);

		this.ibm = new Empresa("IBM", cuentasIBM);
		this.ypf = new Empresa("YPF", cuentasYPF);
		this.listaEmpresas = new ArrayList<Empresa>();
		this.listaEmpresas.add(ibm);
		this.listaEmpresas.add(ypf);
	}

	// @Test
	// public void verCuentasDeEmpresa() {
	// this.ibm.consultarCuentas();
	// }

	// @Test
	// public void debeDevolverLaRepresentacionJSONDeUnObjeto() {
	// final Gson gson = new Gson();
	// final String representacionJSON = gson.toJson(listaEmpresas);
	// System.out.println(representacionJSON);
	//
	// final String listaEmpresasJson = representacionJSON;
	//
	// final Type tipoListaEmpleados = new TypeToken<List<Empresa>>() {
	// }.getType();
	// final List<Empresa> empresas = gson.fromJson(listaEmpresasJson,
	// tipoListaEmpleados);
	// for (Empresa unaEmpresa : empresas) {
	// unaEmpresa.consultarCuentas();
	// assertEquals(empresas.size() , 2);
	// }
	// }
	@Test
	public void debeTomarUnJsonYDevolverUnaListaDeEmpresas() {
		final Gson gson = new Gson();
		final String representacionJSON = gson.toJson(listaEmpresas);
		// System.out.println(representacionJSON);

		final String listaEmpresasJson = representacionJSON;
		List<Empresa> listaEmpresasTest = new ArrayList<Empresa>();

		listaEmpresasTest = AdapterJson.transformarDeJSONaListaEmpresas(listaEmpresasJson);

		for (Empresa unaEmpresa : listaEmpresasTest) {
			unaEmpresa.consultarCuentas();
			assertEquals(listaEmpresasTest.size(), 2);
		}
	}
}
