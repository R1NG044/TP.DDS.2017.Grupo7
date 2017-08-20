package ar.edu.utn.frba.dds.tp.dominio;

public class IndicadorEmpresa {
	
	private String nombreEmpresa;
	private double valorIndicador;
	private Integer periodo;
	
	
	
	public IndicadorEmpresa(String nombreEmpresa, double valorIndicador, Integer periodo) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.valorIndicador = valorIndicador;
		this.periodo = periodo;
	}
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	
	public double getValorIndicador(){
		return 1.0;
	}
	
	
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	
}
