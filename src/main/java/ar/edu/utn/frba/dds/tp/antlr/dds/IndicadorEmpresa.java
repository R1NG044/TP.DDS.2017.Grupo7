
package ar.edu.utn.frba.dds.tp.antlr.dds;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import ar.edu.utn.frba.dds.tp.antlr.dds.*;
import ar.edu.utn.frba.dds.tp.dominio.Empresa;


@Entity(name="IndicadorEmpresa")
@Table(name = "indicadorEmpresa")
public class IndicadorEmpresa implements IExpresion{
	
	@Id
	@GeneratedValue
	private Integer idIndicadorEmpresa;
	private String nombreEmpresa;
	private double valorIndicador;
	private Integer periodo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Indicador indicador;
	
	public IndicadorEmpresa(){
	}
	
	public IndicadorEmpresa(Indicador nuevoIndicador, String e, Integer periodo2, double valor) {
		this.setIndicador(nuevoIndicador);
		this.setNombreEmpresa(e);
		this.setPeriodo(periodo2);
		this.setValorIndicador(valor);
	}

	public Integer getIdIndicadorEmpresa() {
		return idIndicadorEmpresa;
	}

	public void setIdIndicadorEmpresa(Integer idIndicadorEmpresa) {
		this.idIndicadorEmpresa = idIndicadorEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public double getValorIndicador() {
		return valorIndicador;
	}

	public void setValorIndicador(double valorIndicador) {
		this.valorIndicador = valorIndicador;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
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
