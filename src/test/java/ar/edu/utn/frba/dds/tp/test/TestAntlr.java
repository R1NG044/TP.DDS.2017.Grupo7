package ar.edu.utn.frba.dds.tp.test;
//import org.antlr.runtime.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.types.Path;
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
import ar.edu.utn.frba.dds.tp.dominio.Empresa;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class TestAntlr extends AbstractPersistenceTest implements WithGlobalEntityManager {
	private Repositorio repo;
	private String representacionJSON;
	
	public static String INPUT_PATH ;

	
	@Before
	public void SetUp() throws IOException {
		this.representacionJSON = "/empresasjson3.txt";
		
		this.repo = Repositorio.getInstance();
		Aplicacion.cargarEmpresasDesdeJson(getInputFilePath(representacionJSON));
		
		
	}

	@Test
	public void testGuardarIndicador() throws IOException {
		
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		
		INPUT_PATH = "/IngresoNeto.txt";
		InputStream file = this.getInputFilePath();
		
	/*
	    String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
	
*/
	
		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromStream(file));
		
		file = this.getInputFilePath();
		String formula1 = IOUtils.toString(file, StandardCharsets.ISO_8859_1.name());
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParserListener listener = new ParserListener();
		listener.guardarUnIndicadorNuevo(expresionContext, "INGRESONETO", formula1);
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
		listener2.guardarUnIndicadorNuevo(expresionContext, "ROE", formula2);
		
		assertTrue(repo.existeIndicador("ROE"));
		assertTrue(repo.existeIndicador("INGRESONETO"));
		System.out.println(listener.probarUnIndicadorNuevo(expresionContext, "AXION", 2017));
		

		//Persist indicador

		for(Indicador i:repo.getIndicadores()){
			entityManager.persist(i);
		}

		tx.commit();
		
	}

	@Test
	public void testProbarIndicador() throws IOException {
		INPUT_PATH = "/pruebaantlr2.txt";
		InputStream file = this.getInputFilePath();
		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromStream(file));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParserListener listener = new ParserListener();
		
		
		
		assertTrue(listener.probarUnIndicadorNuevo(expresionContext, "AXION", 2017) ==101);
		
	}

	private InputStream getInputFilePath() {
		return this.getClass().getResourceAsStream(INPUT_PATH);

	}
	private String getInputFilePath(String input) {
        return this.getClass().getResource(input).getPath();
    }
}
