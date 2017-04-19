package org.grupo7.tp.test;

import static org.junit.Assert.*;

import org.grupo7.tp.dominio.AdapterJson;
import org.grupo7.tp.dominio.Cuenta;
import org.grupo7.tp.dominio.Empresa;
import org.grupo7.tp.dominio.Repositorio;
import org.junit.Before;
import org.junit.Test;

public class TestCargaDeJson {
	private Empresa ypf1;
	private Empresa ypf2;
	private Repositorio repo;
	private Cuenta fds15;
	private Cuenta fds16;
	private Cuenta fds17;
	
	private String textoJSON1 = "{\"empresas\":[{\"nombre\":	\"empresa1\",	\"cuentas\":	[{\"nombre\":\"cuentalalala1\",\"periodo\":2014,\"valor\":1000},{\"nombre\":\"cuentalelele2\",\"periodo\":2015,\"valor\":2000},{\"nombre\":\"cuentalilili3\",\"periodo\":2016,\"valor\":3000}]},{\"nombre\":	\"empresa2\",	\"cuentas\":	[{\"nombre\":\"cuentananana1\",\"periodo\":2014,\"valor\":1000},{\"nombre\":\"cuentanenene2\",\"periodo\":2015,\"valor\":2000},{\"nombre\":\"cuentaninini3\",\"periodo\":2016,\"valor\":3000}]},{\"nombre\":	\"empresa2\",	\"cuentas\":	[{\"nombre\":\"cuentapapapa1\",\"periodo\":2014,\"valor\":1000},{\"nombre\":\"cuentapepepe2\",\"periodo\":2015,\"valor\":2000},{\"nombre\":\"cuentapipipi3\",\"periodo\":2016,\"valor\":3000}]}]}";

	@Before
	public void SetUp() {

		this.repo = new Repositorio();
		this.fds15 = new Cuenta("FDS", 2015, 3000025);
		this.fds16 = new Cuenta("FDS", 2016, 3000025);
		this.fds17 = new Cuenta("FDS", 2017, 3000000);
		this.ypf1 = new Empresa("YPF");
		this.ypf1.agregarCuenta(fds15);
		this.ypf1.agregarCuenta(fds16);
		this.ypf2 = new Empresa("YPF");
		this.ypf2.agregarCuenta(fds15);
		this.ypf2.agregarCuenta(fds17);
		
	}

	@Test
	public void debeDevolverLaRepresentacionJSONDeUnaListaDeEmpresas() {
		
		}
	
	
	@Test
	public void seRepiteTodoPeroAndaIgualDePiola() {
		/*	Se agrega 2 veces la misma empresa,
		 * 	la primera tiene 2 cuentas, y la segunda
		 * 	tambien, pero repite una de la anterior,
		 * 	por lo tanto al final va a quedar una sola
		 * 	empresa, con 3 cuentas en total
		 */
		repo.agregarEmpresa(ypf1);
		repo.agregarEmpresa(ypf2);
		assertEquals(repo.cantidadEmpresas(),1);
		int cantidadCuentas = repo.obtenerEmpresa("YPF").cantidadCuentas();
		assertEquals(cantidadCuentas,3);
			
	}

	@Test
	public void debeTomarUnJsonYDevolverUnaListaDeEmpresas() {
		
		AdapterJson.cargarDesdeJSON(textoJSON1,repo);
		repo.mostrarEmpresas();
	}

	@Test
	public void cargarRepoDesdeJSON() {
		/*	La lista tiene 2 empresas repetidas
		 * 	por lo que el JSON tiene 3 pero la cantidad total
		 * 	en el repo es de 2
		 */
		
		AdapterJson.cargarDesdeJSON(textoJSON1,repo);
		assertEquals(repo.cantidadEmpresas(), 2);
		assertTrue(repo.existeEmpresa("empresa1"));
		assertTrue(repo.existeEmpresa("empresa2"));
		assertFalse(repo.existeEmpresa("empresa3"));
		
	}
	

}
