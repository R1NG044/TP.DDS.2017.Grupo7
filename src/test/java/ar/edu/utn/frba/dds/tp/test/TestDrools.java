package ar.edu.utn.frba.dds.tp.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import ar.edu.utn.frba.dds.tp.dominio.Item;
import ar.edu.utn.frba.dds.tp.dominio.Item.Category;


public class TestDrools {
	
@Test
public void testEjemploDrools(){
	
	System.out.println( "Bootstrapping the Rule Engine ..." );
	// Bootstrapping a Rule Engine Session
	//KieServices ks = KieServices.Factory.get();
	//KieContainer kContainer = ks.getKieClasspathContainer();
	KieSession kSession =  createDefaultSession(); //kContainer.newKieSession();

	Item item = new Item("A", 123.0,234.0);
	System.out.println( "Item Category: " + item.getCategory()); 
	kSession.insert(item);
	int fired = kSession.fireAllRules();
	System.out.println( "Number of Rules executed = " + fired );
	System.out.println( "Item Category: " + item.getCategory());
}



}
