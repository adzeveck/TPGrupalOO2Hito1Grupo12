package datos;

import java.time.LocalDate;
import java.util.Set;


public class Festival {

	private int idFestival;
	private String nombre;
	private String temporada;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Double costoSuperficie;
	private Double plusElectricidad;
	private Double costoMontaje;
	private Double sueldoBase;
	private Double valorAnioAntiguedad;
	private Set<UnidadDeVenta> UnidadDeVenta;

	public Festival() {}
	
	
	public Festival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
			Double costoSuperficie, Double plusElectricidad, Double costoMontaje, Double sueldoBase,
			Double valorAnioAntiguedad) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.costoSuperficie = costoSuperficie;
		this.plusElectricidad = plusElectricidad;
		this.costoMontaje = costoMontaje;
		this.sueldoBase = sueldoBase;
		this.valorAnioAntiguedad = valorAnioAntiguedad;
	}


	public long getIdFestival() {
		return idFestival;
	}
	protected void setIdFestival(int idFestival) {
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


	public Double getCostoSuperficie() {
		return costoSuperficie;
	}


	public void setCostoSuperficie(Double costoSuperficie) {
		this.costoSuperficie = costoSuperficie;
	}


	public Double getPlusElectricidad() {
		return plusElectricidad;
	}


	public void setPlusElectricidad(Double plusElectricidad) {
		this.plusElectricidad = plusElectricidad;
	}


	public Double getCostoMontaje() {
		return costoMontaje;
	}


	public void setCostoMontaje(Double costoMontaje) {
		this.costoMontaje = costoMontaje;
	}


	public Double getSueldoBase() {
		return sueldoBase;
	}


	public void setSueldoBase(Double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}


	public Double getValorAnioAntiguedad() {
		return valorAnioAntiguedad;
	}


	public void setValorAnioAntiguedad(Double valorAnioAntiguedad) {
		this.valorAnioAntiguedad = valorAnioAntiguedad;
	}


	


	public Set<UnidadDeVenta> getUnidadDeVenta() {
		return UnidadDeVenta;
	}


	public void setUnidadDeVenta(Set<UnidadDeVenta> unidadDeVenta) {
		UnidadDeVenta = unidadDeVenta;
	}


	@Override
	public String toString() {
		return "Festival [idFestival=" + idFestival + ", nombre=" + nombre + ", temporada=" + temporada
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", costoSuperficie=" + costoSuperficie
				+ ", plusElectricidad=" + plusElectricidad + ", costoMontaje=" + costoMontaje + ", sueldoBase="
				+ sueldoBase + ", valorAnioAntiguedad=" + valorAnioAntiguedad + "]";
	}
}
