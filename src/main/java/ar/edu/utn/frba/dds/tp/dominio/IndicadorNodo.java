package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.tp.antlr.dds.IndicadorEmpresa;

public class IndicadorNodo{
	private String indicador;
	private List<IndicadorEmpresa> indicadoresEmpresa = new ArrayList<IndicadorEmpresa>();
	
	
	public String getIndicador(){
		return indicador;
	}
	
	public void setIndicador(String indicador){
		this.indicador = indicador;
	}
	
	public List<IndicadorEmpresa> getIndicadoresEmpresaEvaluados(){
		return indicadoresEmpresa;
	}

	public void setIndicadoresEmpresa(List<IndicadorEmpresa> indicadoresEmpresa) {
		this.indicadoresEmpresa = indicadoresEmpresa;
	}
	
	 
}