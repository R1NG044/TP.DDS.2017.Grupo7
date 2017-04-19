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

	public  void cargarEmpresasDesdeJson(String jsonEmpresas) {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		this.cargarListaDeEmpresas(listaEmpresas);

	}
	public void cargarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarEmpresa(unaEmpresa);
		}

	}

	void agregarEmpresa(Empresa unaEmpresaInput) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre() == unaEmpresaInput.getNombre()) {
				empresa.cargarCuentas(unaEmpresaInput.cuentas);
				return;
			}
		}
		Empresa nuevaEmpresa = new Empresa(unaEmpresaInput.getNombre());
		nuevaEmpresa.cargarCuentas(unaEmpresaInput.cuentas);
		this.empresas.add(nuevaEmpresa);
	}

	public boolean existeEmpresaDeNombre(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre() == nombreEmpresa) {
				return true;
			}
		}
		return false;
	}

}
