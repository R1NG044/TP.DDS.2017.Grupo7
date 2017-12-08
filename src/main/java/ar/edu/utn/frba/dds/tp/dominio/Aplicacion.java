package ar.edu.utn.frba.dds.tp.dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ar.edu.utn.frba.dds.tp.antlr.CalculadoraLexer;
import ar.edu.utn.frba.dds.tp.antlr.CalculadoraParser;
import ar.edu.utn.frba.dds.tp.antlr.dds.ParserListener;
import ar.edu.utn.frba.dds.tp.herramientas.AdapterJson;

public final class Aplicacion {

	private static final String INPUT_PATH = null;

	public static void cargaEmpresasDesdeBDaRepo() {
		Repositorio.getInstance().cargarEmpresasDeBD();
		// Repositorio.getInstance().cargarIndicadoresDesdeBD();
	}

	public static void cargaDesdeBDaRepoPorUser(Integer User) throws Exception {
		Repositorio.getInstance().cargarEmpresasDeBD();
		Repositorio.getInstance().cargarIndicadoresDesdeBDPorUser(User);
	}

	public static void cargarEmpresasDesdeJson(String jsonEmpresas) throws FileNotFoundException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);
	}

	public static void persistirEmpresasDesdeJson(String jsonEmpresas) throws FileNotFoundException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);

		// Cargar en memoria las empresas y cuentas. Asi despues Persistir se
		// hace
		// sobre objetos existentes en memoria.
		Repositorio.getInstance().cargarListaDeEmpresas(listaEmpresas);

		Repositorio.getInstance().persistirEmpresas();

		//Repositorio.getInstance().limpiarRepo();
		// cargar ind predefinidos
	}

	public static void persistirActualizarEmpresasDesdeJson(String jsonEmpresas) throws FileNotFoundException {
		List<Empresa> listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = AdapterJson.transformarDeJSONaListaEmpresas(jsonEmpresas);
		Repositorio.getInstance().cargarActualizarListaDeEmpresas(listaEmpresas);

		Repositorio.getInstance().persistirActualizarEmpresas();

		// Repositorio.getInstance().limpiarRepo();
		// cargar ind predefinidos
	}

	public static String probarUnIndicador(String indicador, String empresa, Integer periodo) throws Exception {

		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromString(indicador));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParserListener listener = new ParserListener();
		try {
			Double aux = listener.probarUnIndicadorNuevo(expresionContext, empresa, periodo);
			return aux.toString();

		} catch (Exception e) {

			return ("Error al intentar probar indicador. Por favor verifique que las cuentas o indicadores utilizados se encuentren cargados previamente en el Sistema.");
		}

	}

	public static String evaluarUnIndicador(String indicador, String empresa, Integer periodo) throws Exception {

		try {
			Double aux = Repositorio.getInstance().darIndicadorDeNombre(indicador).calcularResultado(empresa, periodo);
			return aux.toString();

		} catch (Exception e) {

			return ("Error al intentar evaluar indicador. Por favor verifique que las empresa y el periodo seleccionados se encuentren cargados previamente en el Sistema.");

		}

	}

	public static String guardarUnIndicador(String nombreIndicador, String formulaIndicador, Integer usuario) {

		CalculadoraLexer lexer = new CalculadoraLexer(CharStreams.fromString(formulaIndicador));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalculadoraParser parser = new CalculadoraParser(tokens);
		CalculadoraParser.ExpresionContext expresionContext = parser.expresion();
		ParserListener listener = new ParserListener();

		Usuario user;
		try {
			user = Repositorio.getInstance().buscarUserPorId(usuario);
			return listener.guardarUnIndicadorNuevo(expresionContext, nombreIndicador, formulaIndicador, user);
			// return
			// Repositorio.getInstance().persistirIndicador(nombreIndicador);

		} catch (Exception e) {
			System.out.println("Error al intentar guardar indicador");
			return e.getMessage();
		}
	}

	public static void iniciarAppconCargaDeDatosPredefinidos() throws IOException {
		cargarUsers();
		cargarIndicadoresPredefinidos();

	}

	private static void cargarIndicadoresPredefinidos() throws IOException {
		if (!Repositorio.getInstance().existenIndicadoresEnBD())
			Repositorio.getInstance().cargarIndicadoresPredefinidos();

	}

	public static void ActualizarValoresPrecargados() {

		Repositorio.getInstance().ActualizarIndicadoresPrecargados();

	}

	private static void cargarUsers() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("admin", "admin"));
		usuarios.add(new Usuario("brenda.stolarz@gmail.com", "654321"));
		usuarios.add(new Usuario("nadia@utn.edu.ar", "nadia"));
		usuarios.add(new Usuario("ale@utn.edu.ar", "ale"));
		Repositorio.getInstance().persistirUsuarios(usuarios);

	}
	
	

}
