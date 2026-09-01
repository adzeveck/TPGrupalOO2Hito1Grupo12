package datos;

import java.time.LocalDate;


public abstract class Personal {
	protected int id;
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected LocalDate fechaNacimiento;
	protected LocalDate fechaIngreso;
	
	public Personal() {}
	
	public Personal(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	@Override
	public String toString() {
		return "Personal [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", fechaNacimiento=" + fechaNacimiento + ", fechaIngreso=" + fechaIngreso + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Personal)) {
			return false;
		}
		Personal otro = (Personal) obj;
		return dni != null && dni.equalsIgnoreCase(otro.dni);
	}

	@Override
	public int hashCode() {
		return dni == null ? 0 : dni.toLowerCase().hashCode();
	}
	
	//public abstract double calcularSueldo(Costo costo); 
}
