package ar.edu.utn.frba.dds.tp.jobs;

import java.util.Date;

import javax.persistence.*;


@Entity(name="HistorialCargaBatch")
@Table(name = "HistorialCargaBatch")
public class HistorialCargaBatch {

	public HistorialCargaBatch() {
		super();
	}
	
	
	@Id 
	@GeneratedValue
	private int id;
	private long ultimaFechaModificacion; 
	private String fechaJob;
	private String nombreArchivo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	
	public long getUltimaFechaModificacion() {
		return ultimaFechaModificacion;
	}
	public void setUltimaFechaModificacion(long ultimaFechaModificacion) {
		this.ultimaFechaModificacion = ultimaFechaModificacion;
	}
	public String getFechaJob() {
		return fechaJob;
	}
	public void setFechaJob(String fechaJob) {
		this.fechaJob = fechaJob;
	}
	
	
}
