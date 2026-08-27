package datos;

import java.time.LocalDate;


public class Cajero extends Personal {
	private String turno;

	public Cajero() {}
	public Cajero(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,String turno) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso);
		this.turno = turno;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	@Override
	public String toString() {
		return "Personal ["+super.toString() +"Cajero [turno=" + turno + "]";
		
	}
	
	
	
}
