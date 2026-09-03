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

public class TestPlatoEstrella {

	public static void main(String[] args) throws Exception {

		FestivalABM festivalAbm = new FestivalABM();
		UnidadDeVentaABM unidadAbm = new UnidadDeVentaABM();
		PlatoABM platoAbm = new PlatoABM();
		PedidoABM pedidoAbm = new PedidoABM();

		// --- Festival con dos unidades de venta distintas adentro ---
		int idFestival = festivalAbm.agregar("Feria Gourmet", "Verano", LocalDate.now(), LocalDate.now().plusDays(3),
				1200.0, 600.0, 2500.0, 350000.0, 6000.0);
		Festival festival = festivalAbm.traer(idFestival);

		int idUnidad1 = unidadAbm.agregarFoodTruck("La Popular", 10.0, "FT00000003", "AC456DE", true);
		UnidadDeVenta unidad1 = unidadAbm.traer(idUnidad1);
		unidad1.setFestival(festival);
		unidadAbm.modificar(unidad1);

		int idUnidad2 = unidadAbm.agregarPuestoDesarmable("El Rincon", 6.0, "PD00000002", 1, 30);
		UnidadDeVenta unidad2 = unidadAbm.traer(idUnidad2);
		unidad2.setFestival(festival);
		unidadAbm.modificar(unidad2);

		// --- Cada unidad tiene su propia "Hamburguesa" (Plato distinto, precio
		// propio) mas un plato exclusivo. El caso de uso tiene que sumar las
		// ventas entre unidades para dar con el plato estrella real. ---
		int idHamburguesa1 = unidadAbm.agregarPlato(unidad1.getCodigo(), "Hamburguesa", 8000.0, 3500.0);
		int idPapas = unidadAbm.agregarPlato(unidad1.getCodigo(), "Papas", 4000.0, 1200.0);

		int idHamburguesa2 = unidadAbm.agregarPlato(unidad2.getCodigo(), "Hamburguesa", 8500.0, 3700.0);
		int idChoripan = unidadAbm.agregarPlato(unidad2.getCodigo(), "Choripan", 3500.0, 1200.0);

		Plato hamburguesa1 = platoAbm.traer(idHamburguesa1);
		Plato papas = platoAbm.traer(idPapas);
		Plato hamburguesa2 = platoAbm.traer(idHamburguesa2);
		Plato choripan = platoAbm.traer(idChoripan);

		// --- Pedidos repartidos entre las dos unidades ---
		Pedido pedido1 = new Pedido(LocalDate.now(), unidad1);
		pedido1.agregarDetalle(hamburguesa1, 3);
		pedido1.agregarDetalle(papas, 2);
		pedidoAbm.agregar(pedido1);

		Pedido pedido2 = new Pedido(LocalDate.now(), unidad1);
		pedido2.agregarDetalle(hamburguesa1, 2);
		pedido2.agregarDetalle(papas, 2);
		pedidoAbm.agregar(pedido2);

		Pedido pedido3 = new Pedido(LocalDate.now(), unidad2);
		pedido3.agregarDetalle(hamburguesa2, 4);
		pedido3.agregarDetalle(choripan, 6);
		pedidoAbm.agregar(pedido3);

	

		// --- Caso de uso: plato estrella del festival ---
		String platoEstrella = pedidoAbm.traerPlatoEstrella(idFestival);

		System.out.println("\n--- CASO DE USO: plato estrella del festival ---");
		System.out.println("El plato mas vendido en todo el festival es: " + platoEstrella);
	}

}
