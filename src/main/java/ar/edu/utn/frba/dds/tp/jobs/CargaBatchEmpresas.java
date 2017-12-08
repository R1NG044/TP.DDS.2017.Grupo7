package ar.edu.utn.frba.dds.tp.jobs;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class CargaBatchEmpresas implements org.quartz.Job{

	
	public CargaBatchEmpresas(){
		
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		System.out.println("CargaBatchEmpresas está corriendo!");
		
		try{
			
			Path path = Paths.get(getInputFilePath("/empresasJson.txt"));//esta es la ruta de tu archivo 
			System.out.println(Files.getLastModifiedTime(path));
			FileTime ultimaFechaModificacion = Files.getLastModifiedTime(path);
			
			
			if(!Repositorio.getInstance().esArchivoYaProcesado(ultimaFechaModificacion)){
				Aplicacion.cargaEmpresasDesdeBDaRepo();
				Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath("/empresasJson.txt"));
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				HistorialCargaBatch historial = new HistorialCargaBatch();
				historial.setNombreArchivo("empresasJson.txt");
				historial.setUltimaFechaModificacion(ultimaFechaModificacion.toMillis());
				historial.setFechaJob(dateFormat.format(date));
				entityManager.getTransaction().begin();
				entityManager.persist(historial);
				entityManager.getTransaction().commit();
				Aplicacion.ActualizarValoresPrecargados();
				System.out.println("El archivo empresasJson.txt ha sido procesado - "+ dateFormat.format(date));
				
			}else{
				System.out.println("El archivo empresasJson.txt no ser� procesado porque ya ha sido procesado anteriormente.");
			}
				
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private String getInputFilePath(String input) {
		return this.getClass().getResource(input).getPath();
	}

}
