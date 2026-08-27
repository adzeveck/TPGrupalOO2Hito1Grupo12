package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Pedido {

	private long idPedido;
	private LocalDate fecha;

	// private Festival festival;
	// private UnidadDeVenta unidad;

	private Set<DetallePedido> lstDetalle = new HashSet<DetallePedido>();

	public Pedido() {
	}

	public Pedido(LocalDate fecha) {
		this.fecha = fecha;
	}

	public long getIdPedido() {
		return idPedido;
	}

	protected void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<DetallePedido> getLstDetalle() {
		return lstDetalle;
	}

	public void setLstDetalle(Set<DetallePedido> lstDetalle) {
		this.lstDetalle = lstDetalle;
	}

	public boolean agregarDetalle(Plato plato, int cantidad) {
		DetallePedido detalle = new DetallePedido(plato, cantidad);
		detalle.setPedido(this);
		return lstDetalle.add(detalle);
	}

	// TODO: 
	public double calcularRecaudacion() {
		return 0.0;
	}

	// TODO: 
	public double calcularCostoPlatos() {
		return 0.0;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", lstDetalle=" + lstDetalle + "]";
	}
}
