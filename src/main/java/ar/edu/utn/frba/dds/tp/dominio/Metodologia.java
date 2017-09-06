package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import ar.edu.utn.frba.dds.tp.antlr.dds.*;
import javax.persistence.*;

import java.util.List;


@Entity(name="Metodologia")
@Table(name = "metodologia")
public class Metodologia {
	
	@Id
	private String nombre;
	
	
	public Metodologia(String nombre){
		this.nombre = nombre;
		
	}
	
	public ArrayList<Empresa> evaluarMetodologia(ArrayList<Empresa> empresas){
		
		//Mandar la lista de empresas al workbench para que filtre las que no
		//conviene invertir HOY y devuelva ordenadas en orden de prioridad.
		
		//Hay que mandar lista de empresas con indicadores evaluados al dia de hoy
		//y las cuentas del ultimo periodo cargado
		//El workbench utilizará esos numeros y evaluará con sus reglas si vale la 
		//pena invertir o no.
		
		ArrayList<Empresa> empresasOrdered = new ArrayList<Empresa>();
		
		return null;
		
	}
	
	public ArrayList<Empresa> cargarIndicadores(ArrayList<Empresa> empresas, ArrayList<Indicador> indicadores){
		
		for(Empresa empr:empresas){
			//La empresa ya posee sus cuentas con sus periodos:  
			//Pregunta: Filtrar periodo en Kie server o en la aplicacion?
			//Evaluo y agrego Indicadores a una lista, evaluados en el periodo
		}
		return empresas;
		
	}

}
