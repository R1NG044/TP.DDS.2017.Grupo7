package ar.edu.utn.frba.dds.tp.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;


import ar.edu.utn.frba.dds.tp.dominio.Usuario;


public class TestUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager{

	@Test
	public void cargarUsuariosEnBD(){
		Usuario userBrenda = new Usuario("brenda.stolarz@gmail.com", "654321");
		Usuario userNadia = new Usuario("nadia@utn.edu.ar", "nadia");
		Usuario userAle = new Usuario("ale@utn.edu.ar", "ale");
		
		
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
		
		entityManager.persist(userBrenda);
		entityManager.persist(userNadia);
		entityManager.persist(userAle);
		
		tx.commit();
		
	}
}
