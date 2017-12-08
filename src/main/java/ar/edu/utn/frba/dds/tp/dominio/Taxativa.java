package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public class Taxativa extends Metodologia {

	public Taxativa(List<Regla> reglas, String operacion) {
		super();
		this.reglas = reglas;
		this.operacion = operacion;
	}

	List<Regla> reglas;
	String operacion;
	
	//--- Getters y setters
	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	//--- Metodos ---
	
	//Devuelve las empresas que cumplen con las reglas segun el tipo de operacion AND/OR
	public ArrayList<Empresa> aplicarMetodologia(List<Empresa> empresas, int periodo) throws Exception{
		ArrayList<Empresa> empresasQueCumplenConReglas = new ArrayList<Empresa>();
		boolean cumpleLasReglas;
		
		for(Empresa e: empresas){
			if(operacion == "AND"){
				cumpleLasReglas = true;
				for (Regla r: this.reglas){
					if (!evaluarMetodologiaAEmpresa(e, periodo, r)){
						cumpleLasReglas = false;
						break;
					}
				}
				
			}
			else{
				cumpleLasReglas = false;
				for (Regla r: this.reglas){
					if (evaluarMetodologiaAEmpresa(e, periodo, r)){
						cumpleLasReglas = true;
						break;
					}
				}
			}
			
			if(cumpleLasReglas){
				empresasQueCumplenConReglas.add(e);	
			}
			
		}
		
		return empresasQueCumplenConReglas;
		
	}
	
	public boolean evaluarMetodologiaAEmpresa(Empresa empresa, int periodo, Regla r) throws Exception{
		
		return r.aplicarRegla(empresa, periodo);
	}
	
	
}
