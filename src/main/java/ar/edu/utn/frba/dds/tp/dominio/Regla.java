package ar.edu.utn.frba.dds.tp.dominio;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;

public class Regla {
	public Regla(String indicador, String operacion, double valor) {
		super();
		
		this.indicador = indicador;
		this.operacion = operacion;
		this.valor = valor;
	}

	
	private String indicador;
	private String operacion; // >, <, ==
	private double valor;
	
	//--- Getter y setter

	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	//--- Metodos ---
	public boolean aplicarRegla(Empresa empresa, int periodo) throws Exception{
		boolean resultado;
		
		Indicador indicador = Repositorio.getInstance().darIndicadorDeNombre(this.indicador);
		switch(operacion.toLowerCase()){
			case "mayor a":
				//Get indicador from Repo.
				
				try{
					resultado = indicador.calcularResultado(empresa.getNombre(), periodo) > valor;	
					return resultado;
				}catch (Exception e){
					
					return false;
				}
		case "menor a":
				try{
					resultado = indicador.calcularResultado(empresa.getNombre(), periodo) < valor;	
					return resultado;
				}catch (Exception e){
					
					return false;
				}
		case "igual a":
			try{
				resultado = indicador.calcularResultado(empresa.getNombre(), periodo) == valor;	
				return resultado;
			}catch (Exception e){
				
				return false;
			}
		}
		
		return false;
		
	}
}
