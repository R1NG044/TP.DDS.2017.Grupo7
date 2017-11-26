package ar.edu.utn.frba.dds.tp.jobs;
import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static org.quartz.SimpleScheduleBuilder.*;

public class CargaBatchEmpresas implements org.quartz.Job{

	
	public CargaBatchEmpresas(){
		
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		System.out.println("CargaBatchEmpresas está corriendo!");
		
		try{
			
			Path path = Paths.get(getInputFilePath("/empresasJson4.txt"));//esta es la ruta de tu archivo en mi caso estoy utilizando GNU/Linux
			System.out.println(Files.getLastModifiedTime(path));
			FileTime ultimaFechaModificacion = Files.getLastModifiedTime(path);
			
			
			if(!Repositorio.getInstance().esArchivoYaProcesado(ultimaFechaModificacion)){
				
				//Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath("/empresasJson4.txt"));
				HistorialCargaBatch historial = new HistorialCargaBatch();
				historial.setNombreArchivo("/empresasJson4.txt");
				historial.setUltimaFechaModificacion(ultimaFechaModificacion.toMillis());
				entityManager.getTransaction().begin();
				entityManager.persist(historial);
				entityManager.getTransaction().commit();
				
			}else{
				System.out.println("El archivo no será procesado porque ya ha sido procesado anteriormente.");
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
