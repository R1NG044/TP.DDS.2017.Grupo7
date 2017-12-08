package ar.edu.utn.frba.dds.tp.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;

@Entity
@Table(name = "usuario")
@NamedQuery(name="buscarUsuario",query="SELECT u FROM Usuario u WHERE u.nombre = :pNombre AND u.password = :pPassword")
public class Usuario {

	public Usuario() {

	}

	public Usuario(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}

	public Usuario(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;

	}

	@Id
	@GeneratedValue
	private Integer id;
	private String nombre;
	private String password;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Indicador> indicadores;


	//@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Metodologia> metodologias;


	

	// --GETTERS y SETTERS---
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}


	/*
	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}
	*/

}
