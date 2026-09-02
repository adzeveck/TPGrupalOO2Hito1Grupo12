package datos;


import java.util.HashSet;
import java.util.Set;

public abstract class UnidadDeVenta {

	protected int id;
	protected String nombre;
	protected double superficie;
	protected String codigo;
	protected Festival festival;
	protected Personal responsable;
	protected Set<Personal> lstPersonal =new HashSet<Personal>();

	public UnidadDeVenta() {

	}// Hibernate necesita el constructor vacio

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

	public Personal getResponsable() {
		return responsable;
	}

	public void setResponsable(Personal responsable) {
		this.responsable = responsable;
	}


	public Set<Personal> getLstPersonal() {
		return lstPersonal;
	}

	public void setLstPersonal(Set<Personal> lstPersonal) {
		this.lstPersonal = lstPersonal;
	}

	public void agregarPersonal(Personal p) {
		this.lstPersonal.add(p);
	}

}
