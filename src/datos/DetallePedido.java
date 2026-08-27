package datos;

public class DetallePedido {

	private long idDetalle;
	private int cantidad;
	private Pedido pedido;
	private Plato plato;

	public DetallePedido() {
	}

	public DetallePedido(Plato plato, int cantidad) {
		this.plato = plato;
		this.cantidad = cantidad;
	}

	public long getIdDetalle() {
		return idDetalle;
	}

	protected void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	@Override
	public String toString() {
		return "DetallePedido [idDetalle=" + idDetalle + ", cantidad=" + cantidad + ", plato=" + plato + "]";
	}
}
