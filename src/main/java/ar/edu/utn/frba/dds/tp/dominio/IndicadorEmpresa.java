
package ar.edu.utn.frba.dds.tp.dominio;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ar.edu.utn.frba.dds.tp.antlr.dds.*;


//@Entity(name="IndicadorEmpresa")
//@Table(name = "indicadorEmpresa")
public class IndicadorEmpresa implements IExpresion{
	
	private String nombreEmpresa;
	private double valorIndicador;
	private Integer periodo;
	
	public IndicadorEmpresa() {
	}
	
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

	@Override
	public double calcularResultado(String empresa, Integer periodo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IOperador getOperador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExpresion getOperando1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExpresion getOperando2() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
