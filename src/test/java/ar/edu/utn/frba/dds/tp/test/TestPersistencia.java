package ar.edu.utn.frba.dds.tp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraLexer;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;
import ar.edu.utn.frba.dds.tp.antlr.dds.ParserListener;
import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Metodologia;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;

public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager  {

	private Repositorio repo;
	private String representacionJSON;
	private String representacionJSON3;
	public static String INPUT_PATH ;


	@Before
	public void SetUp() {
		this.representacionJSON = "/empresasjson1.txt";
		this.representacionJSON3 = "/empresasjson3.txt";
		this.repo = Repositorio.getInstance();

	}
	
	/*
	@After
	public void CloseEm(){
		
		EntityTransaction tx = entityManager.getTransaction();
		tx.commit();
		
		if(entityManager.isOpen()){
			entityManager.close();
		}			
	}*/
	
	
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
	
	@Test
	public void persistirMetodologias(){
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		
		EntityTransaction tx = entityManager.getTransaction();
		
		Metodologia m = new Metodologia("Buffet", new Usuario(1, "Brenda")); // 0000 es el id de usuario de prueba
		
		entityManager.persist(m);
		
		tx.commit();
		
	}
	
	@Test()
		public void testGuardarIndicador() throws IOException {
		repo.limpiarRepo();			
		Aplicacion.cargarEmpresasDesdeJson(getInputFilePath(representacionJSON3));
		
		
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		INPUT_PATH = "/IngresoNeto.txt";
		InputStream file = this.getInputFilePath();
		
	
		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromStream(file));
		
		file = this.getInputFilePath();
		String formula1 = IOUtils.toString(file, StandardCharsets.ISO_8859_1.name());
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParserListener listener = new ParserListener();
		listener.guardarUnIndicadorNuevo(expresionContext, "INGRESONETO", formula1, new Usuario(1, "Brenda"));
		assertTrue(repo.existeIndicador("INGRESONETO"));
		INPUT_PATH = "/ROE.txt";
		file = this.getInputFilePath();
		lexer = new CalculadoraLexer(CharStreams.fromStream(file));
		tokens = new CommonTokenStream(lexer);
		parser = new CalculadoraParser(tokens);
		expresionContext = parser.expresion();
		ParserListener listener2 = new ParserListener();
		
		//Persisto formula indicador ROE
		file = this.getInputFilePath();
		String formula2 = IOUtils.toString(file, StandardCharsets.ISO_8859_1.name());
		listener2.guardarUnIndicadorNuevo(expresionContext, "ROE", formula2, new Usuario(2, "Nadia"));
		
		assertTrue(repo.existeIndicador("ROE"));
		assertTrue(repo.existeIndicador("INGRESONETO"));
		System.out.println(listener.probarUnIndicadorNuevo(expresionContext, "AXION", 2017));
		//Persist indicador

				for(Indicador i:repo.getIndicadores()){
					entityManager.persist(i);
				}

				tx.commit();
	
	}
	
	private String getInputFilePath(String input) {
        return this.getClass().getResource(input).getPath();
    }

private InputStream getInputFilePath() {
	return this.getClass().getResourceAsStream(INPUT_PATH);

}
}
