package ar.edu.utn.frba.dds.tp.test;
//import org.antlr.runtime.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraLexer;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.dds.ParserListener;
import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class TestAntlr {
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
		INPUT_PATH = "/ROE.txt";
		InputStream file = this.getInputFilePath();
		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromStream(file));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParserListener listener = new ParserListener();
		
		listener.guardarUnIndicadorNuevo(expresionContext, "ROE");
		assertTrue(repo.existeIndicador("ROE"));
		
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
		
		
		
		assertTrue(listener.probarUnIndicadorNuevo(expresionContext, "YPF", 2017) ==2001);
		
	}

	private InputStream getInputFilePath() {
		return this.getClass().getResourceAsStream(INPUT_PATH);

	}
	private String getInputFilePath(String input) {
        return this.getClass().getResource(input).getPath();
    }
}
