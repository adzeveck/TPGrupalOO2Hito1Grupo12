package datos;

public class PuestoDesarmable extends UnidadDeVenta {

	private int cantCarpas;
	private int tiempoMontaje;

	public PuestoDesarmable() {
	}

	public PuestoDesarmable(String nombre, double superficie, String codigo, int cantCarpas, int tiempoMontaje) {
		super(nombre, superficie, codigo);
		this.cantCarpas = cantCarpas;
		this.tiempoMontaje = tiempoMontaje;
	}

	public int getCantCarpas() {
		return cantCarpas;
	}

	public void setCantCarpas(int cantCarpas) {
		this.cantCarpas = cantCarpas;
	}

	public int getTiempoMontaje() {
		return tiempoMontaje;
	}

	public void setTiempoMontaje(int tiempoMontaje) {
		this.tiempoMontaje = tiempoMontaje;
	}

	@Override
	public String toString() {
		return "PuestoDesarmable [id=" + id + ", nombre=" + nombre + ", superficie=" + superficie + ", codigo="
				+ codigo + ", festival=" + festival + ", cantCarpas=" + cantCarpas + ", tiempoMontaje=" + tiempoMontaje
				+ "]";
	}
}
