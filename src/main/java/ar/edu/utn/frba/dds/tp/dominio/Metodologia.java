package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import javax.persistence.*;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;


@Entity(name="Metodologia")
@Table(name = "metodologia")
@NamedQuery(name="buscarMetodologiaPorUser",query="SELECT i FROM Metodologia i WHERE i.usuario.id = :pIdUsuario OR i.usuario.id = 0")
public class Metodologia {
	
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
	@Column(unique = true)
	private String nombre;
		
	public Metodologia(){
		}
	
	public Metodologia(String nombre, Usuario user){
		this.nombre = nombre;
		this.usuario = user;
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
	
	// ---getters y setters---
	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
