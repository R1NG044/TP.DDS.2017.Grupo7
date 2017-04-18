package stubs;

import java.util.ArrayList;
import org.eclipse.xtend.lib.annotations.Accessors;
import stubs.StubBancoJSON;

@Accessors
@SuppressWarnings("all")
public class FixtureEmpresaJson {
  public StubBancoJSON obtenerBancoJson1() {
    ArrayList<String> serviciosExt = new ArrayList<String>();
    serviciosExt.add("cobro cheques");
    serviciosExt.add("depósitos");
    serviciosExt.add("extracciones");
    serviciosExt.add("transferencias");
    serviciosExt.add("créditos");
    return new StubBancoJSON("Banco de la Plaza", 35.9338322, 72.348353, "Avellaneda", "Javier Loeschbor", serviciosExt);
  }
  
  public StubBancoJSON obtenerBancoJson2() {
    ArrayList<String> serviciosExt2 = new ArrayList<String>();
    serviciosExt2.add("atención al cliente");
    serviciosExt2.add("depósitos");
    serviciosExt2.add("extracciones");
    serviciosExt2.add("transferencias");
    serviciosExt2.add("créditos");
    serviciosExt2.add("plazos fijo");
    return new StubBancoJSON("Banco Santander", 56.9338322, 42.348353, "Quilmes", "Adrian Fanego", serviciosExt2);
  }
  
  public StubBancoJSON obtenerBancoJson3() {
    ArrayList<String> serviciosExt3 = new ArrayList<String>();
    serviciosExt3.add("cobro cheques");
    serviciosExt3.add("depósitos");
    serviciosExt3.add("transferencias");
    serviciosExt3.add("créditos");
    serviciosExt3.add("atención al cliente");
    serviciosExt3.add("depósitos");
    serviciosExt3.add("extracciones");
    return new StubBancoJSON("Banco Santander", 13.5555555, 54.765432, "CABA", "Lucas Soriano", serviciosExt3);
  }
}
