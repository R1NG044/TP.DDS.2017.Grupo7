package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class Repositorio {
	
	
	List<Empresa> empresas = new ArrayList<Empresa>();
	Empresa empresaACrear;
	

	private static Repositorio REPO = null;

	private Repositorio() {
	}
	
	public static Repositorio getInstance() {

		if (REPO == null) {

		REPO = new Repositorio();

		}

		return REPO;

		}
	
	void agregarEmpresa(Empresa unaEmpresa) {		
		this.empresas.add(unaEmpresa);		
	}
	
	boolean existeEmpresa(String nombreEmpresa) {
		for (Empresa empresa : empresas) {			
			if (empresa.getNombre() == nombreEmpresa) {
				return true;
			}				
		}		
		return false;		
	}
	
	Empresa obtenerEmpresa(String nombreEmpresa) {
		for (Empresa empresa : empresas) {			
			if (empresa.getNombre() == nombreEmpresa) {
				return empresa;
			}				
		}		
		Empresa nuevaEmpresa = new Empresa(nombreEmpresa);
		return nuevaEmpresa;		
	}
	
}
	

