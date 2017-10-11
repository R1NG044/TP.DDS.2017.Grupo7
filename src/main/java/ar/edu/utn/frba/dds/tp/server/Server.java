package ar.edu.utn.frba.dds.tp.server;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.tp.dominio.Repositorio;
import ar.edu.utn.frba.dds.tp.antlr.dds.*;

import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.*;

public class Server {
	
	private Integer idUsuarioActivo;
	
	public static void main(String[] args) {
		Repositorio.getInstance().cargarIndicadoresDesdeBD();
		
		
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		
		Spark.get("/indicadores/:idUsuario", (req, res) ->{
		
			List<String> lista = Repositorio.getInstance().buscarNombreIndicadorPorNombre(Integer.parseInt(req.params("idUsuario")));
			
			//return i.getNombre();
			//System.out.println(lista.get(0).getNombre());
			
			return new ModelAndView(lista, "listaIndicadores.hbs");
		}, engine);
		
		Spark.get("indicador/cargar", (req, res) ->{
			
			return new ModelAndView(null, "cargarIndicador.hbs");
		}, engine);
		
		Spark.get("/home", (req, res) -> {
			return new ModelAndView(null, "home.hbs");
			
		}, engine);
		
		Spark.post("/login", (req, res) ->{
			System.out.println(req.queryParams("login"));
			System.out.println(req.queryParams("password"));
			
			return new ModelAndView(null, "index.hbs");
		});
		
	}
public static void loguearUsuario (String usuario, String pass){
		
	}
	private static Object getIndicadoresByUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

}
