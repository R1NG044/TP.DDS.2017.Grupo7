package org.drools.devguide.chapter02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.drools.compiler.kproject.ReleaseIdImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.drools.devguide.BaseTest;
import org.drools.devguide.eshop.model.Item;
import org.drools.devguide.eshop.model.Item.Category;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


/**
 *
 * @author salaboy
 */



public class ClassifyItemsTest extends BaseTest {

    @Test
    public void lowRangeItemClassificationTest() {
        KieSession kSession = createDefaultSession();
        Item item = new Item("A", 123.0, 234.0);
        kSession.insert(item);
        int fired = kSession.fireAllRules();
        System.out.println(item.getCategory());
        assertThat(1, is(fired));
        assertThat(Category.LOW_RANGE, is(item.getCategory()));
    }
    
    @Test
    public void testWorkbench(){
    	String url = "http://localhost:8080/drools-wb/maven2/com/myorganization/myprojects/inversores/1.0/inversores-1.0.jar";
    	
    	KieServices ks = KieServices.Factory.get();
        ks.getResources().newUrlResource(url);
        ReleaseIdImpl releaseId = new ReleaseIdImpl("com.myorganization.myprojects", "inversores","LATEST");
        KieContainer kieContainer = ks.newKieContainer(releaseId);
       // KieContainer kieContainer = ks.newKieContainer( ks.newReleaseId);
        
        final String nombre = "ypf";
        Empresa e = new Empresa(nombre);
        
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(e);
        System.out.println(e.getNombre());
        kieSession.fireAllRules();
        
        System.out.println(e.getNombre());
        
    }
    
//    @Test
//    public void midRangeItemClassificationTest() {
//    }
    
//    @Test
//    public void highRangeItemClassificationTest() {
//    }
}
