package datos;

import java.time.LocalDate;
//import java.util.List;


public class Festival {

	private long idFestival;
	private String nombre;
	private String temporada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	//private Costos costos;
	//private List<UnidadDeVenta> lstUnidades;

	public Festival() {}
	
	
	public Festival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public long getIdFestival() {
		return idFestival;
	}
	protected void setIdFestival(long idFestival) {
		this.idFestival = idFestival;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "Festival [idFestival=" + idFestival + ", nombre=" + nombre + ", temporada=" + temporada
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	} 
	
	
	

}
