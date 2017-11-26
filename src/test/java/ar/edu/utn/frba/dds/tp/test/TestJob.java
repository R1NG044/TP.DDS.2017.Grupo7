package ar.edu.utn.frba.dds.tp.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class TestJob {
	public static String INPUT_PATH ;	

	
	
	@Test
	public void testFechaUltModJson() throws IOException, FileNotFoundException  {
//		
		Path path = Paths.get(getInputFilePath("/IngresoNeto.txt"));//esta es la ruta de tu archivo en mi caso estoy utilizando GNU/Linux
		System.out.println(Files.getLastModifiedTime(path));
		File fichero = new File("/empresasJson1.txt");
		String fecha = fechaModificado(fichero);
		System.out.println(fecha);
		System.out.println(fichero.lastModified());
		
		
	}
	private String fechaModificado(File fichero){
	       long ms = fichero.lastModified();
	            Date d = new Date(ms);
	            Calendar c = new GregorianCalendar();
	            c.setTime(d);
	            String dia = Integer.toString(c.get(Calendar.DATE));
	            String  mes = Integer.toString((c.get(Calendar.MONTH))) ;
	            String  annio = Integer.toString(c.get(Calendar.YEAR));
	            String  hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
	            String  minuto = Integer.toString(c.get(Calendar.MINUTE));
	            String segundo = Integer.toString(c.get(Calendar.SECOND));
	       return annio +"-"+ mes +"-"+ dia +" "+ hora +":"+ minuto +":"+ segundo ;
	   }

	private InputStream getInputFilePath() {
		return this.getClass().getResourceAsStream(INPUT_PATH);

	}
	private String getInputFilePath(String input) {
        return this.getClass().getResource(input).getPath();
    }
}
