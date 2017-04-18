package stubs;

import com.google.gson.Gson;
import interfazAServiciosExternos.InterfazBanco;
import java.util.HashSet;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import stubs.StubBancoJSON;

@Accessors
@SuppressWarnings("all")
public class StubServicioExternoEmpresas implements InterfazBanco {
  private HashSet<StubBancoJSON> bancosExternos = new HashSet<StubBancoJSON>();
  
  public String buscar(final String palabraClave) {
    Gson gson = new Gson();
    return gson.toJson(this.bancosExternos);
  }
  
  public void agregarBancoJson(final StubBancoJSON bancoJson) {
    this.bancosExternos.add(bancoJson);
  }
  
  @Pure
  public HashSet<StubBancoJSON> getBancosExternos() {
    return this.bancosExternos;
  }
  
  public void setBancosExternos(final HashSet<StubBancoJSON> bancosExternos) {
    this.bancosExternos = bancosExternos;
  }
}
