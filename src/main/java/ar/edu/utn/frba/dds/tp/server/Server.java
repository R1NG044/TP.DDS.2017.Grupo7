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

	public static void main(String[] args) {
		Repositorio.getInstance().cargarIndicadoresDesdeBD();
		
		
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		Spark.get("/indicadores/:nombre", (req, res) ->{
		
			List<Indicador> lista = Repositorio.getInstance().buscarIndicadorPorNombre(req.params("nombre"));
			
			//return i.getNombre();
			return new ModelAndView(lista.get(0), "listaIndicadores.hbs");
		}, engine);
		
		
	}
public static void loguearUsuario (String usuario, String pass){
		
	}
	private static Object getIndicadoresByUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

}
