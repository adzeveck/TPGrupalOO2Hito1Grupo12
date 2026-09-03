package test;

import java.time.LocalDate;

import datos.Festival;
import datos.Pedido;
import datos.Plato;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.PedidoABM;
import negocio.PlatoABM;
import negocio.UnidadDeVentaABM;

public class TestCasoUsoPedido {

	public static void main(String[] args) throws Exception {

		FestivalABM festivalAbm = new FestivalABM();
		UnidadDeVentaABM unidadAbm = new UnidadDeVentaABM();
		PlatoABM platoAbm = new PlatoABM();
		PedidoABM pedidoAbm = new PedidoABM();

		// --- Alta de datos de prueba ---
		int idFestival = festivalAbm.agregar("Feria de Otoño", "Otoño", LocalDate.now(), LocalDate.now().plusDays(3),
				1000.0, 500.0, 2000.0, 300000.0, 5000.0);
		Festival festival = festivalAbm.traer(idFestival);

		int idUnidad = unidadAbm.agregarFoodTruck("La Birra Truck", 12.5, "FT00000002", "AB123CD", true);
		UnidadDeVenta unidad = unidadAbm.traer(idUnidad);

		// La unidad queda asociada a su festival
		unidad.setFestival(festival);
		unidadAbm.modificar(unidad);

		// El alta de Plato pasa por UnidadDeVentaABM: Plato es composicion de
		// UnidadDeVenta. Se pasan los datos sueltos y el ABM construye el Plato.
		int idHamburguesa = unidadAbm.agregarPlato(unidad.getCodigo(), "Hamburguesa", 8000.0, 3500.0);
		int idPapas = unidadAbm.agregarPlato(unidad.getCodigo(), "Papas fritas", 4000.0, 1200.0);

		Plato hamburguesa = platoAbm.traer(idHamburguesa);
		Plato papas = platoAbm.traer(idPapas);

		Pedido pedido = new Pedido(LocalDate.now(), unidad);
		pedido.agregarDetalle(hamburguesa, 2);
		pedido.agregarDetalle(papas, 3);

		long idPedido = pedidoAbm.agregar(pedido);
		System.out.println("Pedido creado, id=" + idPedido);

		// --- Caso de uso: traer el Pedido con unidad, detalles y platos ---
		Pedido pedidoRecuperado = pedidoAbm.traerConDetalle(idPedido);

		System.out.println("\n--- Pedido recuperado ---");
		System.out.println(pedidoRecuperado);

		// Festival derivado via unidad, sin que Pedido lo guarde directo
		System.out.println("\nFestival del pedido (derivado por la unidad): "
				+ pedidoRecuperado.getUnidad().getFestival());
	}

}
