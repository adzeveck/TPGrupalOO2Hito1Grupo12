package test;

import java.util.List;

import datos.UnidadDeVenta;
import negocio.UnidadDeVentaABM;

public class TestUnidadDeVenta {

	public static void main(String[] args) {

		UnidadDeVentaABM abm = new UnidadDeVentaABM();

		// --- ALTA de una unidad de cada tipo ---
		try {
			int idFoodTruck = abm.agregarFoodTruck("La Birra Truck", 12.5, "FT00000001", "AB123CD", true);
			System.out.printf("Alta FoodTruck id: %d%n", idFoodTruck);
		} catch (Exception e) {
			System.out.println("No se pudo agregar el Food Truck: " + e.getMessage());
		}

		try {
			int idPuesto = abm.agregarPuestoDesarmable("Empanadas del Norte", 8.0, "PD00000001", 2, 45);
			System.out.printf("Alta PuestoDesarmable id: %d%n", idPuesto);
		} catch (Exception e) {
			System.out.println("No se pudo agregar el Puesto Desarmable: " + e.getMessage());
		}

		// --- CONSULTA POLIMORFICA: pido la clase padre y traigo las dos hijas ---
		List<UnidadDeVenta> unidades = abm.traer();

		System.out.println("\n--- Unidades de venta en la base ---");
		for (UnidadDeVenta u : unidades) {
			System.out.println(u);
		}

		// --- BUSQUEDA POR CODIGO (metodo reusable, lo va a necesitar Pedido) ---
		UnidadDeVenta buscada = abm.traerPorCodigo("FT00000001");
		System.out.printf("%nBuscada por codigo FT00000001: %s%n", buscada);
	}
}
