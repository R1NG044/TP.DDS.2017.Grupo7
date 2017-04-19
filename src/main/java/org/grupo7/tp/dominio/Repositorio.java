package org.grupo7.tp.dominio;

import java.util.ArrayList;
import java.util.List;

public final class Repositorio {

	public List<Empresa> empresas = new ArrayList<Empresa>();
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

	public void cargarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarEmpresa(unaEmpresa);
		}

	}

	public void agregarEmpresa(Empresa unaEmpresaInput) {
		if (existeEmpresaDeNombre(unaEmpresaInput.getNombre())) {
			for (Empresa empresa : empresas) {
				if (empresa.getNombre() == unaEmpresaInput.getNombre()) {
					empresa.cargarCuentas(unaEmpresaInput.cuentas);
					break;
				}
			}
		} else {
			Empresa nuevaEmpresa = new Empresa(unaEmpresaInput.getNombre());
			nuevaEmpresa.cargarCuentas(unaEmpresaInput.cuentas);
			this.empresas.add(nuevaEmpresa);
		}
	}

	public boolean existeEmpresaDeNombre(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				return true;
			}
		}
		return false;
	}
	// public boolean existeEmpresaDeNombre(String nombreEmpresa) {
	// for (Empresa empresa : empresas) {
	// if (empresa.getNombre().equals(nombreEmpresa)) {
	// return true;
	// }
	// }
	// return false;
	// }

}
