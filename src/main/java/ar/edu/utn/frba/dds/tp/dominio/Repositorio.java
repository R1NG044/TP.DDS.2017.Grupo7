package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.tp.antlr.dds.Indicador;

public final class Repositorio {

	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<Indicador> indicadores = new ArrayList<Indicador>();

	private static Repositorio REPO = null;

	private Repositorio() {
	}

	public static Repositorio getInstance() {

		if (REPO == null) {

			REPO = new Repositorio();
		}

		return REPO;
	}

	public void limpiarRepo() {
		this.empresas.clear();
	}

	public void cargarListaDeEmpresas(List<Empresa> listaEmpresas) {
		for (Empresa unaEmpresa : listaEmpresas) {
			this.agregarEmpresa(unaEmpresa);
		}

	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public void agregarIndicador(Indicador unIndicadorInput) {
		if (!(existeIndicadorDeNombre(unIndicadorInput.getNombre()))) {
			this.indicadores.add(unIndicadorInput);
		} else {
			System.out.print("Ya Existe Indicador en Base");
		}
	}

	private boolean existeIndicadorDeNombre(String nombreIndicador) {
		for (Indicador indicador : indicadores) {
			if (indicador.getNombre().equals(nombreIndicador)) {
				return true;
			}
		}
		return false;
	}

	private void agregarEmpresa(Empresa unaEmpresaInput) {
		if (existeEmpresaDeNombre(unaEmpresaInput.getNombre())) {
			for (Empresa empresa : empresas) {
				if (empresa.getNombre().equals(unaEmpresaInput.getNombre())) {
					empresa.cargarCuentas(unaEmpresaInput.getCuentas());
					break;
				}
			}
		} else {
			Empresa nuevaEmpresa = new Empresa(unaEmpresaInput.getNombre());
			nuevaEmpresa.cargarCuentas(unaEmpresaInput.getCuentas());
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

	public void devolverCuentasDeEmpresaDeNombre(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				empresa.consultarCuentas();
				;
			}
		}
	}

	public int cantidadEmpresas() {
		return empresas.size();
	}

	public int cantidadDeCuentasParaEmpresa(String nombreEmpresa) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				return empresa.cantidadDeCuentas();
			}
		}
		return 0;
	}

	public boolean existeEmpresaDeNombreConCuenta(String nombreEmpresa, String unNombreDeCuenta) {
		for (Empresa empresa : empresas) {
			if (empresa.getNombre().equals(nombreEmpresa)) {
				if (empresa.existeCuentaDeNombre(unNombreDeCuenta)) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

}