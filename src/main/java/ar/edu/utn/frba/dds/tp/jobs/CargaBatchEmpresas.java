package ar.edu.utn.frba.dds.tp.jobs;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
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
		
		
	}

}
