package ar.edu.utn.frba.dds.tp.server;

import static spark.Spark.init;
import static spark.Spark.staticFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.tp.dominio.Aplicacion;
import ar.edu.utn.frba.dds.tp.dominio.Cuenta;
import ar.edu.utn.frba.dds.tp.dominio.Metodologia;
import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;
import ar.edu.utn.frba.dds.tp.jobs.Job;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server implements TransactionalOps, WithGlobalEntityManager {

	public static Integer idUsuarioActivo;

	public static void main(String[] args) {
		staticFiles.location("/public");
		staticFiles.expireTime(600);
		init();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		// Inicia el Job de Carga Batch Empresas
		String[] cargaBatchFrequency = new String[1];
		cargaBatchFrequency[0] = "60000";
		
		try {
			Aplicacion.iniciarAppconCargaDeDatosPredefinidos();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		

		
		/***** E N D P O I N T S *******/

		Spark.get("/indicadores", (req, res) -> {

			if (req.cookie("idUsuarioActivo").toString().length() > 0) {

				if (req.queryParams("mySelectIndicadores") != "" && req.queryParams("mySelectEmpresas") != ""
						&& req.queryParams("mySelectPeriodos") != "") {
					// TODO: Traer metodos de evaluar indicadores de los Tests y
					// pasarlos al módulo Aplicacion
					// TODO: Llamar a evaluarIndicador acá y mostrarlo.
				}

				return new ModelAndView(Repositorio.getInstance(), "listaIndicadores.hbs");
			} else {
				return new ModelAndView(null, "login.hbs");
			}
		}, engine);

		Spark.post("/indicadores", (req, res) -> {
			String indicador = req.queryParams("mySelectIndicadores");
			String empresa = req.queryParams("mySelectEmpresas");
			String periodo = req.queryParams("mySelectPeriodos");
			List<String> resultado = new ArrayList<String>();
			resultado.add(indicador);
			resultado.add(empresa);
			resultado.add(periodo);
			String indicadorAux = Aplicacion.probarUnIndicador(indicador, empresa, Integer.parseInt(periodo));
			resultado.add(indicadorAux);
			return new ModelAndView(resultado, "resultadoIndicadores.hbs");
		}, engine);

		Spark.get("indicador/cargar", (req, res) -> {

			return new ModelAndView(null, "cargarIndicador.hbs");
		}, engine);

		Spark.post("indicador/cargar", (req, res) -> {
			String nombreIndicador = req.queryParams("nombreIndicador");
			String formulaIndicador = req.queryParams("formulaIndicador");
			String usuarioId = req.cookie("idUsuarioActivo").toString();
			List<String> resultado = new ArrayList<String>();
			resultado.add(nombreIndicador);
			resultado.add(formulaIndicador);
			resultado.add(usuarioId);
			String statusGuardado = Aplicacion.guardarUnIndicador(nombreIndicador, formulaIndicador,
					Integer.parseInt(usuarioId));
			resultado.add(statusGuardado);
			return new ModelAndView(resultado, "resultadoCargarIndicador.hbs");
		}, engine);

		Spark.get("/indicador/evaluar", (req, res) -> {

			System.out.println(req.cookie("idUsuarioActivo"));
			return new ModelAndView(Repositorio.getInstance(), "evaluarIndicador.hbs");

		}, engine);

		Spark.post("/indicador/evaluar", (req, res) -> {
			String indicador = req.queryParams("mySelectIndicadores");
			String empresa = req.queryParams("mySelectEmpresas");
			String periodo = req.queryParams("mySelectPeriodos");
			List<String> resultado = new ArrayList<String>();
			resultado.add("IND(" + indicador + ")");
			resultado.add(empresa);
			resultado.add(periodo);
			String indicadorAux = Aplicacion.evaluarUnIndicador(indicador, empresa, Integer.parseInt(periodo));
			resultado.add(indicadorAux);
			return new ModelAndView(resultado, "resultadoEvaluarIndicador.hbs");
		}, engine);

		Spark.get("/login", (req, res) -> {
			return new ModelAndView(null, "login.hbs");

		}, engine);

		Spark.post("/index", (req, res) -> {
			String username = req.queryParams("login");
			String pwd = req.queryParams("password");

			// Autenticación contra la BD
			Boolean auth = false;
			Usuario u = Repositorio.getInstance().getUsuarioByUserAndPwd(username, pwd);

			if (u != null) {
				auth = true;
				res.cookie("idUsuarioActivo", u.getId().toString());

			}

			if (auth) {
				idUsuarioActivo = u.getId();
				Aplicacion.cargaDesdeBDaRepoPorUser(idUsuarioActivo);
				return new ModelAndView(u, "index.hbs");
			} else {
				return new ModelAndView("El usuario o la contrasena son incorrectos", "login.hbs");
			}

		}, engine);

		Spark.get("/index", (req, res) -> {
			if (req.cookie("idUsuarioActivo").toString().length() > 0) {
				idUsuarioActivo = Integer.parseInt(req.cookie("idUsuarioActivo"));
				Usuario user = new Usuario();
				user = Repositorio.getInstance().buscarUserPorId(idUsuarioActivo);
				return new ModelAndView(user, "index.hbs");
			} else {
				return new ModelAndView(null, "login.hbs");
			}

		}, engine);

		Spark.get("/empresas", (req, res) -> {
			return new ModelAndView(Repositorio.getInstance(), "filtrosListaEmpresas.hbs");
		}, engine);

		Spark.get("empresas/resultadoConsultaEmpresas", (req, res) -> {
			List<Cuenta> listCuentas = new ArrayList<Cuenta>();
			if (req.queryParams("mySelectEmpresas") != null && req.queryParams("mySelectPeriodos") != null
					&& req.queryParams("mySelectEmpresas").toString().length() > 0
					&& req.queryParams("mySelectPeriodos").toString().length() > 0) {
				listCuentas = Repositorio.getInstance().getCuentasDeEmpresaDeIdPorPeriodo(
						Integer.parseInt(req.queryParams("mySelectEmpresas")),
						Integer.parseInt(req.queryParams("mySelectPeriodos")));
			}
			String empresa = req.queryParams("mySelectEmpresas");
			String periodo = req.queryParams("mySelectPeriodos");
			if (listCuentas.isEmpty())
				
				return new ModelAndView("No existen Cuentas para la Empresa "+ empresa + " en el Per�odo "+ periodo, "resultadoConsultaEmpresasError.hbs");
			else
				return new ModelAndView(listCuentas, "resultadoConsultaEmpresas.hbs");

		}, engine);

		Spark.get("/empresas/upload", (req, res) -> {
			Boolean resultUpload = true;

			return new ModelAndView(resultUpload, "empresas.hbs");
		}, engine);

		Spark.post("/empresas/upload", (req, res) -> {

			Boolean resultUpload = true;

			return new ModelAndView(resultUpload, "empresas.hbs");
		}, engine);

		Spark.get("/metodologias", (req, res) -> {

			//List<Metodologia> metodologias = Repositorio.getInstance()
				//	.buscarMetodologiaPorUser(Integer.parseInt(req.params("idUsuario")));
			
			// Repositorio.getInstance().cargarListaDeMetodologias(metodologias);

			return new ModelAndView(Repositorio.getInstance().getAllMetodologiasDistinct(), "metodologias.hbs");
		}, engine);
		
		Spark.post("/resultadoMetodologias", (req, res) -> {
			
			ArrayList<String> empresasQueConvieneInvertir = Aplicacion.evaluarMetodologia(req.queryParams("mySelectMetodologias").toString(), Integer.parseInt(req.queryParams("mySelectPeriodos").toString()));
			ArrayList<String> stringsParaDevolverEnFront = new ArrayList<String>();
			
			stringsParaDevolverEnFront.add(req.queryParams("mySelectMetodologias").toString());
			stringsParaDevolverEnFront.add(req.queryParams("mySelectPeriodos").toString());
			if(empresasQueConvieneInvertir != null && empresasQueConvieneInvertir.size() > 0){
				//for(String s: empresasQueConvieneInvertir){
					stringsParaDevolverEnFront.addAll(empresasQueConvieneInvertir);
				//}
			}
			
			return new ModelAndView(stringsParaDevolverEnFront, "resultadoMetodologias.hbs");
			
		}, engine);

		Spark.get("/metodologias/cargar", (req, res) -> {

			return new ModelAndView(Repositorio.getInstance(), "cargarMetodologia.hbs");
		}, engine);

		Spark.get("/logout", (req, res) -> {
			res.cookie("idUsuarioActivo", "");
			return new ModelAndView(null, "login.hbs");

		}, engine);

		/***** E N D ******/

		try {
			Job.main(cargaBatchFrequency); // 6000 ms = 1 min
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param usuario
	 * @param pwd
	 * @returns true si el usuario es autenticado contra la BD.
	 */
	public static Boolean validarUsuario(String usuario, String pwd) {
		Usuario u = Repositorio.getInstance().getUsuarioByUserAndPwd(usuario, pwd);

		return (u != null);

	}

	public static void loguearUsuario(String usuario, String pass) {

	}

	/**** GETTER y SETTER ****/
	public Integer getIdUsuarioActivo() {
		return idUsuarioActivo;
	}

	public void setIdUsuarioActivo(Integer idUsuarioActivo) {
		Server.idUsuarioActivo = idUsuarioActivo;
	}
}
