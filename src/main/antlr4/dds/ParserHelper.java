package dds;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ParserHelper {
	
	private CommonTokenStream tokenStream;
	private CalculadoraParser parser;
	private CalculadoraParser.ExpresionContext expresionContext;
	private ParseTreeWalker walker;
	
	

	public ParserHelper(){
		
	}
	
	
	public static int ParseExpresion(String expresion){
		
		
		InputStream stream = new ByteArrayInputStream(expresion.getBytes(StandardCharsets.UTF_8));
		
		CalculadoraLexer lexer = null;
		try{
			lexer = new CalculadoraLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		}catch(Exception ex){
			
		}
	
		
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculadoraParser parser = new CalculadoraParser(tokens);
        
    	parser.setInputStream(new CommonTokenStream(lexer));
        CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
        ParseTreeWalker walker = new ParseTreeWalker();
        
        GramaticaListener listener = new GramaticaListener();
        walker.walk(listener, expresionContext);

		return 1;
	}
}
