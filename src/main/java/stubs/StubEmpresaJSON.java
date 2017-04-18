package stubs;

import java.util.ArrayList;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class StubEmpresaJSON {
  private String banco;
  
  private double x;
  
  private double y;
  
  private String sucursal;
  
  private String gerente;
  
  private ArrayList<String> servicios;
  
  public StubEmpresaJSON(final String banco, final double x, final double y, final String sucursal, final String gerente, final ArrayList<String> servicios) {
    this.banco = banco;
    this.x = x;
    this.y = y;
    this.sucursal = sucursal;
    this.gerente = gerente;
    this.servicios = servicios;
  }
  
  @Pure
  public String getBanco() {
    return this.banco;
  }
  
  public void setBanco(final String banco) {
    this.banco = banco;
  }
  
  @Pure
  public double getX() {
    return this.x;
  }
  
  public void setX(final double x) {
    this.x = x;
  }
  
  @Pure
  public double getY() {
    return this.y;
  }
  
  public void setY(final double y) {
    this.y = y;
  }
  
  @Pure
  public String getSucursal() {
    return this.sucursal;
  }
  
  public void setSucursal(final String sucursal) {
    this.sucursal = sucursal;
  }
  
  @Pure
  public String getGerente() {
    return this.gerente;
  }
  
  public void setGerente(final String gerente) {
    this.gerente = gerente;
  }
  
  @Pure
  public ArrayList<String> getServicios() {
    return this.servicios;
  }
  
  public void setServicios(final ArrayList<String> servicios) {
    this.servicios = servicios;
  }
}
