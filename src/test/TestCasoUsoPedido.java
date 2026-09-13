package test;

import datos.DetallePedido;
import datos.Pedido;
import negocio.PedidoABM;


public class TestCasoUsoPedido {

	public static void main(String[] args) {

		PedidoABM pedidoAbm = new PedidoABM();


		Pedido pedido = pedidoAbm.traerConDetalle(1);

		System.out.println("--- CASO DE USO: pedido con sus detalles y platos ---");
		System.out.println(pedido);


		System.out.println("Detalles:");
		for (DetallePedido d : pedido.getLstDetalle()) {
			System.out.println("  " + d.getCantidad() + " x " + d.getPlato().getNombre());
		}

		// El festival no se guarda en Pedido: se deriva por la unidad
		System.out.println("\nFestival del pedido: "
				+ pedido.getUnidad().getFestival());
	}

}
