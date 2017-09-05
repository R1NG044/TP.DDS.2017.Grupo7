package ar.edu.utn.frba.dds.tp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager  {

	private Repositorio repo;
	private String representacionJSON;
	private String representacionJSON2;

	@Before
	public void SetUp() {
		this.representacionJSON = "/empresasjson1.txt";
		this.representacionJSON2 = "/empresasjson2.txt";
		this.repo = Repositorio.getInstance();
	}

	
	@Test
	public void cargarJSONEmpresasYCuentasABaseDeDatos() throws FileNotFoundException {
		/*
		 * Para la Empresa YPF el Json tienen la cuenta INDICADOR periodo 2017
		 * duplicada, vemos como se guardo una sola.
		 */
		//Aplicacion.cargarEmpresasDesdeJson(getInputFilePath(representacionJSON));
		

		/*
		 * El Json 2 Tiene duplicada la Empresa IBM respecto del Json1,
		 * cargaremos este al Repo y verificaremos que no se duplican ni la
		 * empresa ni las cuentas. Y se agregan las empresas Axion y Petrobras.
		 */
		Aplicacion.persistirEmpresasDesdeJson(getInputFilePath(representacionJSON));

		/*
		 * A su vez la Empresa Axion esta duplicada pero con cuentas distintas
		 * vemos que en el repo solo se cargo una empresa con las 2 cuentas
		 */

		

		
	}
	
	private String getInputFilePath(String input) {
        return this.getClass().getResource(input).getPath();
    }
}
