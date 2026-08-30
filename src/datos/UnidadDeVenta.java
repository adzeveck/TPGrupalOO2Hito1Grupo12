package datos;

public abstract class UnidadDeVenta {
	
	protected int id;
	protected String nombre;
	protected double superficie;
	protected String codigo;
	protected Festival festival;
	
	
	public UnidadDeVenta() {
		
	}//Hibernate necesita el constructor vacio
	
	
	public UnidadDeVenta(String nombre, double superficie, String codigo) {
		
		this.nombre = nombre;
		this.superficie = superficie;
		this.codigo = codigo;
	}
	
	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

}