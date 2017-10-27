package ar.edu.utn.frba.dds.tp.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;


public class TestUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager{

	@Test
	public void cargarUsuariosEnBD(){
		List<Usuario> usuarios =new ArrayList<>();
		usuarios.add(new Usuario("brenda.stolarz@gmail.com", "654321"));
		usuarios.add( new Usuario("nadia@utn.edu.ar", "nadia"));
		usuarios.add(new Usuario("ale@utn.edu.ar", "ale"));
		Repositorio.getInstance().persistirUsuarios(usuarios);				
		
	}
}
