package ar.edu.utn.frba.dds.tp.test;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Priorizada;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;

public class TestMetodologia {
	
	@Before
	public void SetUp() {
		
			
			try {
				Aplicacion.cargaDesdeBDaRepoPorUser(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	
	 @Test 
	 public void testOrdenarMetodologia(){
		 Priorizada priorizada = new Priorizada("INGRESONETO","ASCENDENTE");
		 priorizada.aplicarMetodologia(Repositorio.getInstance().getEmpresas(), (Integer) 2017);

	 }
	
}
