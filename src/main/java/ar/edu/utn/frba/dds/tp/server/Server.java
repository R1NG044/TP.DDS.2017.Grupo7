package ar.edu.utn.frba.dds.tp.server;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.dominio.Usuario;
import ar.edu.utn.frba.dds.tp.antlr.dds.*;

import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.*;

public class Server {
	
	public static Integer idUsuarioActivo;
	
	public Integer getIdUsuarioActivo() {
		return idUsuarioActivo;
	}
	public void setIdUsuarioActivo(Integer idUsuarioActivo) {
		this.idUsuarioActivo = idUsuarioActivo;
	}
	public static void main(String[] args) {
		Repositorio.getInstance().cargarIndicadoresDesdeBD();
		
		
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		
		/***** E N D P O I N T S *******/
		
		Spark.get("/indicadores/:idUsuario", (req, res) ->{
		
//			List<String> lista = Repositorio.getInstance().buscarNombreIndicadorPorNombre(Integer.parseInt(req.params("idUsuario")));
			
			List<Indicador> lista = Repositorio.getInstance().buscarIndicadorPorUser(Integer.parseInt(req.params("idUsuario")));
			
			//return i.getNombre();
//	System.out.println(lista.get(0).getNombre());
			
			return new ModelAndView(lista, "listaIndicadores.hbs");
		}, engine);
		
		Spark.get("indicador/cargar", (req, res) ->{
			
			return new ModelAndView(null, "cargarIndicador.hbs");
		}, engine);
		
		Spark.get("/login", (req, res) -> {
			return new ModelAndView(null, "login.hbs");
			
		}, engine);
		
		Spark.post("/index", (req, res) ->{
			//System.out.println(req.queryParams("login"));
			//System.out.println(req.queryParams("password"));
			
			//Autenticación contra la BD
			Boolean aut = true;
			
			Usuario u = new Usuario(1, req.queryParams("login"));
			idUsuarioActivo = u.getId();
			
			
			//TODO: Mandar nombre de usuario a Index y grabarlo en label en form, a evaluar.
			if(aut){
				return new ModelAndView(u, "index.hbs");
			}
			else{
				return new ModelAndView(null, "login.hbs");
			}
		}, engine);
		
		Spark.get("/index", (req, res) -> {
			
			return new ModelAndView(null, "index.hbs");
			
		}, engine);
		
		Spark.get("/empresas", (req, res) -> {
			return new ModelAndView(null, "empresas.hbs");
		}, engine);
		
		Spark.get("/empresa/consultaDeValores", (req, res) -> {
			
			return new ModelAndView(null, "consultaDeValores.hbs");
		}, engine);
		
		
		
		Spark.get("/metodologias/:idUsuario", (req, res) -> {
			return new ModelAndView(null, "metodologias.hbs");
		}, engine);
		
		
		/***** E N D ******/
		
	}
public static void loguearUsuario (String usuario, String pass){
		
	}
	private static Object getIndicadoresByUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

}
