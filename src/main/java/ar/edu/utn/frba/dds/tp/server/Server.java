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
		
		Spark.get("/indicadores/:indicadorNombre", (req, res) ->{
		
			Indicador i = Repositorio.getInstance().darIndicadorDeNombre(req.params("indicadorNombre"));
			
			return i.getNombre();
		});
		
		
	}

	private static Object getIndicadoresByUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

}
