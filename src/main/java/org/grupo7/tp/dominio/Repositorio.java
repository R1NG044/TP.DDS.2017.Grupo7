package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class Repositorio {

	public List<Empresa> empresas = new ArrayList<Empresa>();

//	private static Repositorio REPO = null;
//
//	private Repositorio() {
//	}
//
//	public static Repositorio getInstance() {
//
//		if (REPO == null) {
//
//			REPO = new Repositorio();
//			Repositorio.empresas = new ArrayList<Empresa>();
//		}
//		
//		return REPO;
//	}

	public void agregarEmpresa(Empresa unaEmpresa) {
		for (Empresa empresa : empresas) {
			if (this.existeEmpresa(unaEmpresa.getNombre())) {
				for (Cuenta cuenta : unaEmpresa.getCuentas()){
					empresa.agregarCuenta(cuenta);
				}
				return;
			}
		}
		this.empresas.add(unaEmpresa);
	}

	public Empresa obtenerEmpresa(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (nombreEmpresa.equals(empresa.getNombre())) {
				return empresa;
			}
		}
		Empresa empresa = new Empresa(nombreEmpresa);
		this.agregarEmpresa(empresa);
		return empresa;
	}
	
	public int cantidadEmpresas() {
		return empresas.size();
	}
	
	public boolean existeEmpresa(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (nombreEmpresa.equals(empresa.getNombre())) {
				return true;
			}
		}
		return false;
		
	}
	
	public void mostrarEmpresas() {
		for (Empresa empresa: empresas) {
			System.out.println(empresa.getNombre());
		}
		
	}

}
