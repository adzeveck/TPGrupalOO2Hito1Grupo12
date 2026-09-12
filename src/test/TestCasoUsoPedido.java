package test;

import datos.DetallePedido;
import datos.Pedido;
import negocio.PedidoABM;

// CASO DE USO: traer un Pedido con su unidad, sus detalles y el plato de cada
// detalle, todo cargado en una sola consulta (join fetch).
//
// Test de SOLO CONSULTA: la base tiene que estar cargada previamente con el
// script epicentro_gourmet.sql. No se da de alta nada por Hibernate.
public class TestCasoUsoPedido {

	public static void main(String[] args) {

		PedidoABM pedidoAbm = new PedidoABM();

		// Pedido 1 del seed: hecho en "La Birra Truck" (festival Feria de Otoño),
		// con 3 Hamburguesas y 2 Papas fritas.
		Pedido pedido = pedidoAbm.traerConDetalle(1);

		System.out.println("--- CASO DE USO: pedido con sus detalles y platos ---");
		System.out.println(pedido);

		// Los detalles y el plato de cada uno vienen cargados por el join fetch:
		// se pueden recorrer aunque la sesion ya este cerrada, sin que salte
		// LazyInitializationException.
		System.out.println("Detalles:");
		for (DetallePedido d : pedido.getLstDetalle()) {
			System.out.println("  " + d.getCantidad() + " x " + d.getPlato().getNombre());
		}

		// El festival no se guarda en Pedido: se deriva por la unidad.
		System.out.println("\nFestival del pedido (derivado por la unidad): "
				+ pedido.getUnidad().getFestival());
	}

}
