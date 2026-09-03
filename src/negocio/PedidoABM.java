package negocio;

import dao.PedidoDao;
import datos.Pedido;

public class PedidoABM {

	private PedidoDao dao = new PedidoDao();

	public int agregar(Pedido p) {
		return dao.agregar(p);
	}

	public Pedido traer(long idPedido) {
		return dao.traer(idPedido);
	}

	public Pedido traerConDetalle(long idPedido) {
		return dao.traerConDetalle(idPedido);
	}

	public String traerPlatoEstrella(int idFestival) {
		return dao.traerPlatoEstrella(idFestival);
	}

}
