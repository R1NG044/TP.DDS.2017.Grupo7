package ar.edu.utn.frba.dds.tp.jobs;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;

import org.apache.commons.math3.stat.Frequency;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Job {
	public static void main(String[] args) throws InterruptedException {
		// args[0]: milisegundos de frequencia para correr job.

		// Levantar de la carpeta X el archivo con fecha mas reciente
		// y llamar metodo de cargar empresas
		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// Correr el Job ahora y schedulearlo cada X tiempos
			JobDetail job = newJob(CargaBatchEmpresas.class).withIdentity("job1", "group1").build();

			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow()
					.withSchedule(simpleSchedule().withIntervalInSeconds(40)// Integer.parseInt(args[0]))
							.repeatForever())
					.forJob(job)
					.build();

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);

			// and start it off
			scheduler.start();
			Thread.sleep(240000); // 4 minutos hasta desactivarse
			scheduler.shutdown();

		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}
}
