package ar.edu.utn.frba.dds.tp.antlr.dds;

import ar.edu.utn.frba.dds.tp.dominio.*;

public class CuentaExp implements IExpresion {

	String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CuentaExp(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public double calcularResultado(String empresa, Integer periodo) {
		if (Repositorio.getInstance().existeEmpresaDeNombreConCuenta(empresa, this.nombre)) {
			for (Cuenta cuenta : Repositorio.getInstance().darEmpresaDeNombre(empresa).getCuentas()) {
				if (cuenta.getNombreCuenta().equals(this.nombre) && cuenta.getPeriodo().equals(periodo)) {
					return cuenta.getValor();
				}
			}
		}
		throw new RuntimeException("No existe la Empresa o Cuenta para Empresa");
	}	

	@Override
	public IOperador getOperador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExpresion getOperando1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IExpresion getOperando2() {
		// TODO Auto-generated method stub
		return null;
	}

}
