package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Priorizada extends Metodologia {

	String indicador;
	String orden;

	public Priorizada(String indicador, String orden, String nombre) {
		super();
		this.indicador = indicador;
		this.orden = orden;
		super.setNombre(nombre);
		super.setTipo("priorizada");
	}

	// --Metodos

	// Devuelve lista de empresas con su orden despues de calcular el indicador
	public ArrayList<Empresa> aplicarMetodologia(ArrayList<Empresa> empresas, int periodo) {
		if (orden.equals("Descendente")) {
			SortedMap<Double, Empresa> map = new TreeMap<Double, Empresa>(java.util.Collections.reverseOrder());
			return cargarMap(map, empresas, periodo);

		} else {
			SortedMap<Double, Empresa> map = new TreeMap<Double, Empresa>();
			return cargarMap(map, empresas, periodo);
		}
	}

	private ArrayList<Empresa> cargarMap(SortedMap<Double, Empresa> map, List<Empresa> empresas, Integer periodo) {
		for (Empresa e : empresas) {

			try {
				double valorInd = Repositorio.getInstance().darIndicadorDeNombre(getIndicador())
						.evaluarIndicador(e.getNombre(), periodo);
				map.put(valorInd, e);

			} catch (Exception noSePuedeEvaluarIndicador) {
				System.out.println(
						"No se puede evaluar el Indicador " + getIndicador() + " para la Empresa " + e.getNombre());
			}
		}

		Iterator<Double> iterator = map.keySet().iterator();
		ArrayList<Empresa> empresasEvaluadasyOrdenadas = new ArrayList<Empresa>();
		while (iterator.hasNext()) {
			Double key = iterator.next();
			System.out.println("Clave : " + key + " Valor :" + map.get(key).getNombre());
			empresasEvaluadasyOrdenadas.add(map.get(key));
		}
		return empresasEvaluadasyOrdenadas;
	}

	// --Getters y setters
	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

}
