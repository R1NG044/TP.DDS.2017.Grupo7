package ar.edu.utn.frba.dds.tp.jobs;
import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static org.quartz.SimpleScheduleBuilder.*;

public class CargaBatchEmpresas implements org.quartz.Job{

	
	public CargaBatchEmpresas(){
		
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException{
		System.out.println("CargaBatchEmpresas está corriendo!");
		
		try{
			Aplicacion.persistirActualizarEmpresasDesdeJson(getInputFilePath("/empresasJson4.txt"));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private String getInputFilePath(String input) {
		return this.getClass().getResource(input).getPath();
	}

}
