package org.grupo7.tp.dominio;

import java.util.List;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

public class AdapterJson  {
  /**
   * String listaDeBancosEnJson
   * new(String listaDeBancosEnJson){
   * this.listaDeBancosEnJson = listaDeBancosEnJson
   * }
   * override ArrayList<SucursalBanco> buscar(String Json) {
   * return this.transformarDeJSONaClaseBanco()
   * }
   */
  
  public List<Empresa> transformarDeJSONaListaEmpresas(final String json) {
    JsonArray listaJson = Json.parse(json).asArray();
//    final Function1<JsonValue, SucursalBanco> _function = new Function1<JsonValue, SucursalBanco>() {
//      public SucursalBanco apply(final JsonValue unBancoJson) {
//        return AdapterJson.this.convertirASucursalBanco(unBancoJson);
//      }
//    };
    return Lists.<Empresa>newArrayList(IterableExtensions.<JsonValue, Cuenta>map(listaJson, _function));
  }
}
//  
//  public SucursalBanco convertirASucursalBanco(final JsonValue unBancoJson) {
//    String banco = unBancoJson.asObject().get("banco").asString();
//    double x = unBancoJson.asObject().get("x").asDouble();
//    double y = unBancoJson.asObject().get("y").asDouble();
//    String sucursal = unBancoJson.asObject().get("sucursal").asString();
//    JsonArray serviciosJson = unBancoJson.asObject().get("servicios").asArray();
//    final Function1<JsonValue, Servicio> _function = new Function1<JsonValue, Servicio>() {
//      public Servicio apply(final JsonValue unServicioJson) {
//        return AdapterJson.this.convertirAServicioBanco(unServicioJson);
//      }
//    };
//    HashSet<Servicio> serviciosBanco = Sets.<Servicio>newHashSet(IterableExtensions.<JsonValue, Servicio>map(serviciosJson, _function));
//    Direccion _direccionDeBanco = this.direccionDeBanco(x, y);
//    return new SucursalBanco(serviciosBanco, _direccionDeBanco, ((banco + " ") + sucursal));
//  }
//  
//  public Direccion direccionDeBanco(final double x, final double y) {
//    Point coordenadas = new Point(x, y);
//    Point _point = new Point(0, 0);
//    Point _point_1 = new Point(0, 0);
//    Polygon _polygon = new Polygon(Collections.<Point>unmodifiableList(CollectionLiterals.<Point>newArrayList(_point, _point_1)));
//    Comuna _comuna = new Comuna("", _polygon);
//    Direccion direccion = new Direccion("", "", new String[] { "", "" }, coordenadas, "", "", _comuna, "", "", "", "");
//    return direccion;
//  }
//  
//  public Servicio convertirAServicioBanco(final JsonValue unServicioJson) {
//    String servicioJSON = unServicioJson.asString();
//    Horario _horarioBancario = AdapterJson.horarioBancario();
//    Servicio servicio = new Servicio(servicioJSON, _horarioBancario);
//    return servicio;
//  }
//  
//  public static Horario horarioBancario() {
//    HashSet<Turno> turnosDisponiblesBanco = new HashSet<Turno>();
//    HashSet<Dia> diasHabilesBanco = new HashSet<Dia>();
//    LocalTime _localTime = new LocalTime(10, 0);
//    LocalTime _localTime_1 = new LocalTime(15, 0);
//    Turno turnoBanco = new Turno(_localTime, _localTime_1);
//    turnosDisponiblesBanco.add(turnoBanco);
//    CollectionExtensions.<Dia>addAll(diasHabilesBanco, Dia.LUN, Dia.MAR, Dia.MIE, Dia.JUE, Dia.VIE);
//    return new Horario(diasHabilesBanco, turnosDisponiblesBanco);
//  }
//  
//  public List<PuntoDeInteres> buscar(final String str) {
//    return this.transformarDeJSONaClaseBanco(this.origen.buscar(str));
//  }
//  
//  @Pure
//  public InterfazBanco getOrigen() {
//    return this.origen;
//  }
//  
//  public void setOrigen(final InterfazBanco origen) {
//    this.origen = origen;
//  }
//}
