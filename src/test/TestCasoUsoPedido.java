package test;

import java.time.LocalDate;

import datos.Pedido;
import datos.Plato;
import negocio.PedidoABM;
import negocio.PlatoABM;

public class TestCasoUsoPedido {

	public static void main(String[] args) {

		PlatoABM platoAbm = new PlatoABM();
		PedidoABM pedidoAbm = new PedidoABM();

		// --- Alta de datos de prueba ---
		int idHamburguesa = platoAbm.agregar(new Plato("Hamburguesa", 8000.0, 3500.0));
		int idPapas = platoAbm.agregar(new Plato("Papas fritas", 4000.0, 1200.0));

		Plato hamburguesa = platoAbm.traer(idHamburguesa);
		Plato papas = platoAbm.traer(idPapas);

		Pedido pedido = new Pedido(LocalDate.now());
		pedido.agregarDetalle(hamburguesa, 2);
		pedido.agregarDetalle(papas, 3);

		long idPedido = pedidoAbm.agregar(pedido);
		System.out.println("Pedido creado, id=" + idPedido);


		Pedido pedidoRecuperado = pedidoAbm.traerConDetalle(idPedido);

		System.out.println("\n--- Pedido recuperado ---");
		System.out.println(pedidoRecuperado);
	}

}
