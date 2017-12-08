package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;

public class Priorizada {

	Indicador indicador;
	char orden;
	
	//--Getters y setters
	public Indicador getIndicador() {
		return indicador;
	}
	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
	public char getOrden() {
		return orden;
	}
	public void setOrden(char orden) {
		this.orden = orden;
	}
	
	//--Metodos
	
	//Devuelve lista de empresas con su orden despues de calcular el indicador
	public List<Empresa> aplicarMetodologia(List<Empresa> empresas, int periodo){
		
		List<Empresa> empresasQueCumplenConReglas = new ArrayList<Empresa>();
		
		return empresas;
	
	}
}
