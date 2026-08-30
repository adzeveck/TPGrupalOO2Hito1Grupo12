package datos;

import java.time.LocalDate;

public class Cocinero extends Personal{
	private String especialidad;
	private double plusCategoria;
	
	public Cocinero() {}
	public Cocinero(String nombre, String apellido, String dni, LocalDate fechaNacimiento,LocalDate fechaIngreso,String especialidad, double plusCategoria) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso);
		this.especialidad = especialidad;
		this.plusCategoria = plusCategoria;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public double getPlusCategoria() {
		return plusCategoria;
	}
	public void setPlusCategoria(double plusCategoria) {
		this.plusCategoria = plusCategoria;
	}
	@Override
	public String toString() {
		return "Personal ["+super.toString()+"Cocinero especialidad=" + especialidad + ", plusCategoria=" + plusCategoria + "]";
	}
	
	
	
}
