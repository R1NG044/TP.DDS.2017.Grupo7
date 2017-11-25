package ar.edu.utn.frba.dds.tp.server;

import java.util.ArrayList;

import java.util.List;

import ar.edu.utn.frba.dds.tp.antlr.dds.*;
import ar.edu.utn.frba.dds.tp.dominio.*;
import ar.edu.utn.frba.dds.tp.jobs.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.Scheduler.*;

import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.handlebars.*;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.lang.Runnable;


public class Server implements TransactionalOps, WithGlobalEntityManager {
	  
	public static Integer idUsuarioActivo;

	public static void main(String[] args) {
		Repositorio.getInstance().cargarIndicadoresDesdeBD();
		staticFiles.location("/public");
		
		staticFiles.expireTime(600);
		init();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		//Inicia el Job de Carga Batch Empresas
		String[] cargaBatchFrequency = new String[1];
		cargaBatchFrequency[0] = "60000";
		
		/***** E N D P O I N T S *******/

		Spark.get("/indicadores", (req, res) -> {
			
			if( req.cookie("idUsuarioActivo").toString().length() > 0){
				
				if(req.queryParams("mySelectIndicadores") != "" && req.queryParams("mySelectEmpresas") != "" && req.queryParams("mySelectPeriodos") !=""){
					//TODO: Traer metodos de evaluar indicadores de los Tests y pasarlos al módulo Aplicacion
					//TODO: Llamar a evaluarIndicador acá y mostrarlo.
				}
				System.out.println(req.cookie("idUsuarioActivo"));
			//	Repositorio.getInstance().cargarIndicadoresDesdeBD();
				return new ModelAndView(Repositorio.getInstance(), "listaIndicadores.hbs");
			}
			else
			{
				return new ModelAndView(null, "login2.hbs");
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
			Double indicadorAux = Aplicacion.probarUnIndicador(indicador, empresa, Integer.parseInt(periodo));
			resultado.add(indicadorAux.toString());
				return new ModelAndView(resultado, "resultadoIndicadores.hbs");}, engine);

		Spark.get("indicador/cargar", (req, res) -> {

			return new ModelAndView(null, "cargarIndicador.hbs");}, engine);
		
		Spark.post("indicador/cargar", (req, res) -> {
		String nombreIndicador = req.queryParams("nombreIndicador");
		String formulaIndicador = req.queryParams("formulaIndicador");
		String usuarioId = req.cookie("idUsuarioActivo").toString();
		List<String> resultado = new ArrayList<String>();
		resultado.add(nombreIndicador);
		resultado.add(formulaIndicador);
		resultado.add(usuarioId);
		String statusGuardado = Aplicacion.guardarUnIndicador(nombreIndicador, formulaIndicador, Integer.parseInt(usuarioId));
		resultado.add(statusGuardado);
		return new ModelAndView(resultado, "resultadoCargarIndicador.hbs");
		}, engine);
		
		
		Spark.get("/login", (req, res) -> {
			return new ModelAndView(null, "login2.hbs");

		}, engine);

		Spark.post("/index", (req, res) -> {
			String username = req.queryParams("login");
			String pwd = req.queryParams("password");
			

			// Autenticación contra la BD
			Boolean auth = false;
			Usuario u = Repositorio.getInstance().getUsuarioByUserAndPwd(username, pwd);
			
			if(u != null){
				auth = true;
				res.cookie("idUsuarioActivo", u.getId().toString());
			}
			
			if(auth){
				idUsuarioActivo = u.getId();
				return new ModelAndView(u, "index.hbs");
			}
			else {
				return new ModelAndView("El usuario o la contrasena son incorrectos", "login2.hbs");
			}

		}, engine);

		Spark.get("/index", (req, res) ->{
			if( req.cookie("idUsuarioActivo").toString().length() > 0){
				idUsuarioActivo = Integer.parseInt(req.cookie("idUsuarioActivo"));
				Usuario user = new Usuario();
				user = Repositorio.getInstance().buscarUserPorId(idUsuarioActivo);
				Repositorio.getInstance().cargarListaDeIndicadores(Repositorio.getInstance().buscarIndicadorPorUser(user.getId()));
				return new ModelAndView(user, "index.hbs");
			}
			else{
				return new ModelAndView(null, "login2.hbs");
			}
				
			
		}, engine);

		Spark.get("/empresas", (req, res) -> {
			return new ModelAndView(null, "empresas.hbs");
		}, engine);
	
		Spark.get("empresas/resultadoConsultaEmpresas", (req, res) ->{
			List<Cuenta> listCuentas = new ArrayList<Cuenta>();
			if(req.queryParams("mySelectEmpresas") != null && req.queryParams("mySelectPeriodos") != null &&
					req.queryParams("mySelectEmpresas").toString().length() > 0 && req.queryParams("mySelectPeriodos").toString().length() > 0){
				listCuentas = Repositorio.getInstance().getCuentasDeEmpresaDeIdPorPeriodo(Integer.parseInt(req.queryParams("mySelectEmpresas")), 
							Integer.parseInt(req.queryParams("mySelectPeriodos")));
			}
			
			return new ModelAndView(listCuentas, "resultadoConsultaEmpresas.hbs");
			
		}, engine);

		Spark.get("/empresas/consultaDeValores", (req, res) -> {
			
			return new ModelAndView(Repositorio.getInstance(), "filtrosListaEmpresas.hbs");
		
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

			// List<Metodologia> metodologias = Repositorio.getInstance()
			// .buscarMetodologiaPorUser(Integer.parseInt(req.params("idUsuario")));
			//
			// Repositorio.getInstance().cargarListaDeMetodologias(metodologias);

			return new ModelAndView(Repositorio.getInstance(), "metodologias.hbs");
		}, engine);
		
		Spark.get("/metodologias/cargar", (req, res) -> {
			
			return new ModelAndView(Repositorio.getInstance(), "cargarMetodologia.hbs");
		}, engine);

		
		Spark.get("/logout", (req, res) -> {
			res.cookie("idUsuarioActivo", "");
			return new ModelAndView(null, "login2.hbs");

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
