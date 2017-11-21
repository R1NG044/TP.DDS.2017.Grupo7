package ar.edu.utn.frba.dds.tp.dominio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity(name = "Empresa")
@Table(name = "empresa")
@NamedQuery(name = "buscarEmpresaPorNombre", query = "SELECT e FROM Empresa e WHERE e.nombreEmpresa = :pNombre")
public class Empresa {

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
	private String nombreEmpresa;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cuenta> cuentas;
	
	 /**** Builders ****/
	public Empresa() {
	}

	public Empresa(final String _nombre, final ArrayList<Cuenta> _cuentas) {
		this.nombreEmpresa = _nombre;
		this.cuentas = new ArrayList<Cuenta>();
		this.setCuentas(_cuentas);
	}

	public Empresa(String _nombre) {
		this.nombreEmpresa = _nombre;
		this.cuentas = new ArrayList<Cuenta>();
	}
	
	public Empresa(Integer _id, String _nombre) {
		this.nombreEmpresa = _nombre;
//		this.id = _id;
		this.cuentas = new ArrayList<Cuenta>();
	}
	
	
 /***** Metodos sin persistencia *****/
	public void cargarCuentas(List<Cuenta> listaCuentas) {
		for (Cuenta unaCuenta : listaCuentas) {
			this.agregarCuenta(unaCuenta);
		}

	}

	private void agregarCuenta(Cuenta unaCuenta) {
		if (!this.existeCuentaDelMismoPeriodo(unaCuenta)) {
			unaCuenta.setEmpresa(this);
			this.cuentas.add(unaCuenta);
		}
	}

	private boolean existeCuentaDelMismoPeriodo(Cuenta unaCuenta) {
		for (Cuenta cuenta : cuentas) {
			if ((cuenta.getNombreCuenta().equals(unaCuenta.getNombreCuenta()))
					& (cuenta.getPeriodo().equals(unaCuenta.getPeriodo()))) {
				return true;
			}
		}
		return false;
	}

	public int cantidadDeCuentas() {
		return cuentas.size();
	}

	public void consultarCuentas() {
		for (Cuenta cuenta : cuentas) {
			System.out.printf("Para %s el valor de la cuenta %s es: %d para el Periodo %s\n", this.getNombre(),
					cuenta.getNombreCuenta(), cuenta.getValor(), cuenta.getPeriodo());
		}
	}

	public Boolean existeCuentaDeNombre(String nombreCuenta) {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getNombreCuenta().equals((nombreCuenta))) {
				return true;
			}
		}
		return false;
	}
	

	public List<Cuenta> getCuentasPorPeriodo(Integer periodo){
		List<Cuenta> cuentasPorPeriodo = new ArrayList<Cuenta>();
		
		for(Cuenta cuenta: cuentas){
			if(cuenta.getPeriodo().equals(periodo)){
				cuentasPorPeriodo.add(cuenta);
			}
		}
		
		return cuentasPorPeriodo;
	}
	

	/**** Getters y Setters ****/

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(final ArrayList<Cuenta> _cuentas) {
		for (Cuenta unaCuenta : _cuentas) {
			agregarCuenta(unaCuenta);
		}
	}

	public String getNombre() {
		return nombreEmpresa;
	}

	public void setNombre(String nombre) {
		this.nombreEmpresa = nombre;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}


}
