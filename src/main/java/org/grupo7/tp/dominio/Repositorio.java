package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class Repositorio {
	
	
	List<Empresa> empresas = new ArrayList<Empresa>();
	

	
	void agregarEmpresa(Empresa unaEmpresa) {		
		empresas.add(unaEmpresa);		
	}
	
	void consultarCuentasPorEmpresa(Empresa unaEmpresa) {
		for (Empresa empresa : empresas) {
		}		
	
	}
	
}
	

