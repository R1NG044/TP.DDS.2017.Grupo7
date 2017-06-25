package ar.edu.utn.frba.dds.tp.test;
//import org.antlr.runtime.*;

import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import ar.edu.utn.frba.dds.tp.antlr.*;
import ar.edu.utn.frba.dds.tp.antlr.dds.*;

public class TestAntlr {

	public static final String INPUT_PATH = "/pruebaantlr.txt";

	@Test
	public void testFormulaAntlr() throws IOException {
		InputStream file = this.getInputFilePath();

		// ---- Esto es para evaluar una gramatica y crear expresiones
		// -----------

		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromStream(file));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParseTreeWalker walker = new ParseTreeWalker();

		ParserListener listener = new ParserListener();
		walker.walk(listener, expresionContext);

		// assertEquals(6, 6);
		// assertEquals(6, listener.getExpresion().getResultado(), 0.01);
	}

	// @Test
	// public void testGramatica() throws IOException{
	//
	// // ---- Esto es para evaluar una gramatica y crear expresiones
	// -----------
	//
	// //ANTLRFileStream fs = new ANTLRFileStream(INPUT_PATH);
	//
	// CalculadoraLexer lexer = new CalculadoraLexer(new
	// ANTLRFileStream(INPUT_PATH));
	//
	//
	// CommonTokenStream tokens = new CommonTokenStream(lexer);
	// CalculadoraParser parser = new CalculadoraParser(tokens);
	// CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
	// ParseTreeWalker walker = new ParseTreeWalker();
	//
	// GramaticaListener listener = new GramaticaListener();
	// walker.walk(listener, expresionContext);
	//
	// assertEquals(6, 6);
	// //assertEquals(6, listener.getExpresion().getResultado(), 0.01);
	// }
	//
	// @Test
	// public void testGramaticaFromString() throws IOException{
	// //Invoco metodo Parser Helper
	//
	// ParserHelper.ParseExpresion("3*2");
	// //ParserHelper.ParseExpresion("3+5+IND(INGRESO)*CUENTA(NETO)+3*2-5");
	// }
	//

	private InputStream getInputFilePath() {
		return this.getClass().getResourceAsStream(INPUT_PATH);

	}
}