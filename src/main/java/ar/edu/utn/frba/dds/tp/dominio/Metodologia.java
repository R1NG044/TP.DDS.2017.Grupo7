package ar.edu.utn.frba.dds.tp.dominio;

//@Entity(name="Metodologia")
//@Table(name = "metodologia")
//@NamedQuery(name="buscarMetodologiaPorUser",query="SELECT i FROM Metodologia i WHERE i.usuario.id = :pIdUsuario OR i.usuario.id = 0")
public abstract class Metodologia {
	
	//@Id @GeneratedValue
	//private Integer id;
	
	//@ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
	private String nombre;
		
	public Metodologia(){
		}
	
	public Metodologia(String nombre, Usuario user){
		this.nombre = nombre;
		this.usuario = user;
	}
	
	/*public ArrayList<Empresa> evaluarMetodologia(ArrayList<Empresa> empresas){
		
		//Mandar la lista de empresas al workbench para que filtre las que no
		//conviene invertir HOY y devuelva ordenadas en orden de prioridad.
		
		//Hay que mandar lista de empresas con indicadores evaluados al dia de hoy
		//y las cuentas del ultimo periodo cargado
		//El workbench utilizará esos numeros y evaluará con sus reglas si vale la 
		//pena invertir o no.
		
		ArrayList<Empresa> empresasOrdered = new ArrayList<Empresa>();
		
		return null;
		
	}*/
	
	// ---getters y setters---
//	public long getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// ---Metodos---
	public double aplicarMetodologia(Integer periodo) throws Exception {
		
		
		return periodo;
	}

}
