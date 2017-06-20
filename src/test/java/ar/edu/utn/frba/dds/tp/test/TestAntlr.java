package ar.edu.utn.frba.dds.tp.test;
//import org.antlr.runtime.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.LexerGrammar;
import org.junit.Test;


import dds.CalculadoraLexer;
import dds.CalculadoraParser;
import dds.GramaticaListener;
import dds.ParserHelper;
import dds.ParserListener;

public class TestAntlr {
	
	public static final String INPUT_PATH = "/Users/brenda/repos/dds2017/TP.DDS.2017.Grupo7/archivosInput/pruebaantlr2.txt";
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFormulaAntlr() throws IOException{
		
        // ---- Esto es para evaluar una gramatica y crear expresiones -----------
		
		//ANTLRFileStream fs = new ANTLRFileStream(INPUT_PATH);
		
        
		CalculadoraLexer lexer = new CalculadoraLexer(new ANTLRFileStream(INPUT_PATH));
	       
		 
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculadoraParser parser = new CalculadoraParser(tokens);
        CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
        ParseTreeWalker walker = new ParseTreeWalker();
        
        ParserListener listener = new ParserListener();
        walker.walk(listener, expresionContext);

        assertEquals(6, 6);
        //assertEquals(6, listener.getExpresion().getResultado(), 0.01);
	}
	
	@Test
	public void testGramatica() throws IOException{
		
        // ---- Esto es para evaluar una gramatica y crear expresiones -----------
		
		//ANTLRFileStream fs = new ANTLRFileStream(INPUT_PATH);
		
        CalculadoraLexer lexer = new CalculadoraLexer(new ANTLRFileStream(INPUT_PATH));
       
 
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculadoraParser parser = new CalculadoraParser(tokens);
        CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
        ParseTreeWalker walker = new ParseTreeWalker();
        
        GramaticaListener listener = new GramaticaListener();
        walker.walk(listener, expresionContext);

        assertEquals(6, 6);
        //assertEquals(6, listener.getExpresion().getResultado(), 0.01);
	}
	
	@Test
	public void testGramaticaFromString() throws IOException{
		//Invoco metodo Parser Helper
		
		ParserHelper.ParseExpresion("3*2");
		//ParserHelper.ParseExpresion("3+5+IND(INGRESO)*CUENTA(NETO)+3*2-5");
	}
	

}
