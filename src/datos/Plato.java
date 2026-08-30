package datos;

public class Plato {

	private int idPlato;
	private String nombre;
	private double precio;
	private double costo;

	public Plato() {
	}

	public Plato(String nombre, double precio, double costo) {
		this.nombre = nombre;
		this.precio = precio;
		this.costo = costo;
	}

	public int getIdPlato() {
		return idPlato;
	}

	protected void setIdPlato(int idPlato) {
		this.idPlato = idPlato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Plato [idPlato=" + idPlato + ", nombre=" + nombre + ", precio=" + precio + ", costo=" + costo + "]";
	}
}
