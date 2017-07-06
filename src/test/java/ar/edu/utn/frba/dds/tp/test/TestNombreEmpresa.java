package ar.edu.utn.frba.dds.tp.test;

import ar.edu.utn.frba.dds.tp.dominio.Empresa;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertEquals;

public class TestNombreEmpresa {


    @Test
    public void pruebaNombreEmpresa()
    {
        Empresa e = new Empresa("YPF");

        System.out.println("Arrancando el test...");
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();


        System.out.println( "Nombre de la empresa antes: " + e.getNombre());
        //2) Provide information to the Rule Engine Context
        kSession.insert(e);
        //3) Execute the rules that are matching
        int fired = kSession.fireAllRules();
        System.out.println( "Cantidad de reglas ejecutadas: " + fired );
        System.out.println( "Nombre de la empresa despues: " + e.getNombre());
        assertEquals(2, fired);
        assertEquals(e.getNombre(),"a la grande le puse cuca");
    }

    @Test
    public void pruebaDummy() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();
        int fired = kSession.fireAllRules();
        System.out.println( "Cantidad de reglas ejecutadas: " + fired );
        assertEquals(1, fired);

    }

}
