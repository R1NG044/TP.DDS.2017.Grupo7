package ar.edu.utn.frba.dds.tp.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.time.temporal.*;
import java.util.List;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;
import ar.edu.utn.frba.dds.tp.antlr.dds.IndicadorEmpresa;
import ar.edu.utn.frba.dds.tp.dominio.*;




public class TestRules {
	
//@Test
public void evaluarMetodologiaBuffet(){

	
	String url = "http://localhost:8080/kie-wb/maven2wb/demo/prjInversores/1.9/prjInversores-1.9.jar";
	
	KieServices ks = KieServices.Factory.get();
    ks.getResources().newUrlResource(url);
    ReleaseIdImpl releaseId = new ReleaseIdImpl("demo", "prjInversores","LATEST");

    KieContainer kieContainer = ks.newKieContainer(releaseId);
   // KieContainer kieContainer = ks.newKieContainer( ks.newReleaseId);


    KieSession kieSession = kieContainer.newKieSession();
    
    
   
    List<IndicadorNodo> indicadores = new ArrayList<IndicadorNodo>();//Repositorio.getIndicadoresEvaluados(Calendar.getInstance().get(Calendar.YEAR));
    //IndicadorEmpresa ie = new IndicadorEmpresa("AXION", 0.6, 2017);
    //IndicadorEmpresa ie2 = new IndicadorEmpresa("YPF", 0.8, 2017);
    
    List<IndicadorEmpresa> listaInd = new ArrayList<IndicadorEmpresa>();
    //listaInd.add(ie);
    //listaInd.add(ie2);
    
    IndicadorNodo nodo = new IndicadorNodo();
    
    String i = "ROE";
  
    //setear expresion.
    nodo.setIndicador(i);
    nodo.setIndicadoresEmpresa(listaInd);
    
    
   indicadores.add(nodo);
   kieSession.insert(indicadores);

    
    kieSession.getAgenda().getAgendaGroup("buffet").setFocus();

	int fired = kieSession.fireAllRules();
	

	
	System.out.println( "Cantidad de reglas ejecutadas: " + fired );
    
}
	
	
//@Test
public void testEjemploDrools(){

	
	String url = "http://localhost:8080/kie-wb/maven2wb/demo/prjInversores/1.9/prjInversores-1.9.jar";
	
	KieServices ks = KieServices.Factory.get();
    ks.getResources().newUrlResource(url);
    ReleaseIdImpl releaseId = new ReleaseIdImpl("demo", "prjInversores","LATEST");

    KieContainer kieContainer = ks.newKieContainer(releaseId);
   // KieContainer kieContainer = ks.newKieContainer( ks.newReleaseId);

    final String nombre = "YPF"; 
    final Empresa e = new Empresa(nombre);
    final Empresa e2 = new Empresa("Shell");

    KieSession kieSession = kieContainer.newKieSession();
    
    String nombreEmpresa = e.getNombre();
    kieSession.insert(e);
    kieSession.insert(e2);
    
    System.out.println("Empresa1: " + e.getNombre());
    System.out.println("Empresa2: " + e2.getNombre());
    
    kieSession.getAgenda().getAgendaGroup("Z").setFocus();

	int fired = kieSession.fireAllRules();
	

	
	System.out.println( "Cantidad de reglas ejecutadas: " + fired );



    System.out.println(e.getNombre());
    
}



//@Test
////@RunAsClient
//public void runSimpleRules() throws Exception {
//	String USER = "testuser";
//    String PASSWORD = "test";
//    Set<Class<?>> extraJaxbClasses = new HashSet<Class<?>>(Arrays.asList(JaxbItem.class));
//
//    //Deploy a container in KIE Server
//    KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(
//			"http://localhost:8080/kie-server/services/rest/server", 
//			USER, PASSWORD, 60000);
//	config.addJaxbClasses(extraJaxbClasses);
//	KieServicesClient client = KieServicesFactory.newKieServicesClient(config);
//    KieContainerResource kContainer = new KieContainerResource();
//    ReleaseId releaseId = new ReleaseId();
//    releaseId.setGroupId("ar.utn.tp.inversores");
//    releaseId.setArtifactId("inversores");
//    releaseId.setVersion("0.0.1");
//    kContainer.setReleaseId(releaseId);
//    kContainer.setContainerId("inversores-container");
////    ServiceResponse<KieContainerResource> resp = client.createContainer("inversores-container", kContainer);
////    Assert.assertNotNull(resp);
////    Assert.assertEquals(KieContainerStatus.STARTED, resp.getResult().getStatus());
//    //Server is now available to receive requests
//    final String nombre = "ypf";
//    Empresa e = new Empresa(nombre);
//    
//    KieSession kieSession = kieContainer.newKieSession();
//    String nombreEmpresa = e.getNombre();
//    kieSession.insert(e);
//    System.out.println(e.getNombre());
//	int fired = kieSession.fireAllRules();
//	
//	System.out.println( "Cantidad de reglas ejecutadas: " + fired );
//    
//    System.out.println(e.getNombre());
//    
//}



}
