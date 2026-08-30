package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Pedido {

	private long idPedido;
	private LocalDate fecha;
	private UnidadDeVenta unidad;

	private Set<DetallePedido> lstDetalle = new HashSet<DetallePedido>();

	public Pedido() {
	}

	public Pedido(LocalDate fecha, UnidadDeVenta unidad) {
		this.fecha = fecha;
		this.unidad = unidad;
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

	// No hay campo "festival" a proposito: UnidadDeVenta ya sabe a que Festival
	// pertenece (UnidadDeVenta.festival), asi que guardarlo tambien aca seria
	// un dato redundante/derivable (pedido.getUnidad().getFestival()) con
	// riesgo de quedar inconsistente. Decision confirmada con la catedra.

	public UnidadDeVenta getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadDeVenta unidad) {
		this.unidad = unidad;
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
		return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", unidad=" + unidad + ", lstDetalle="
				+ lstDetalle + "]";
	}
}
