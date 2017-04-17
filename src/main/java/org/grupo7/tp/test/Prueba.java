package org.grupo7.tp.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.grupo7.tp.dominio.Cuenta;
import org.grupo7.tp.dominio.Empresa;
import org.grupo7.tp.dominio.Repositorio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Prueba {
	private Empresa ibm;
	private Repositorio repo;
	private Cuenta fds16;
	private Cuenta fds17;

	private ArrayList<Cuenta> cuentasIBM;

	@Before
	public void SetUp() {
		this.repo = Repositorio.getInstance();
		this.fds16 = new Cuenta("FDS", 3000025, 2016);
		this.fds17 = new Cuenta("FDS", 3000000, 2017);
		this.cuentasIBM = new ArrayList<Cuenta>();
		this.cuentasIBM.add(fds16);
		this.cuentasIBM.add(fds17);
		this.ibm = new Empresa("IBM", cuentasIBM);
		
	}

	@Test
	public void verCuentasDeEmpresa() {
		this.ibm.consultarCuentas();
	}

}
