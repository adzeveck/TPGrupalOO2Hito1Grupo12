package test;

import java.time.LocalDate;
import java.util.List;

import datos.Festival;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.UnidadDeVentaABM;

public class TestUnidadDeVenta {

	public static void main(String[] args) {

		UnidadDeVentaABM abm = new UnidadDeVentaABM();
		FestivalABM festivalAbm = new FestivalABM();

		// --- ALTA de una unidad de cada tipo ---
		int idFoodTruck = -1;
		try {
			idFoodTruck = abm.agregarFoodTruck("La Birra Truck", 12.5, "FT00000001", "AB123CD", true);
			System.out.printf("Alta FoodTruck id: %d%n", idFoodTruck);
		} catch (Exception e) {
			System.out.println("No se pudo agregar el Food Truck: " + e.getMessage());
		}

		int idPuesto = -1;
		try {
			idPuesto = abm.agregarPuestoDesarmable("Empanadas del Norte", 8.0, "PD00000001", 2, 45);
			System.out.printf("Alta PuestoDesarmable id: %d%n", idPuesto);
		} catch (Exception e) {
			System.out.println("No se pudo agregar el Puesto Desarmable: " + e.getMessage());
		}

		// --- RELACION CON FESTIVAL: ambas unidades pertenecen al mismo festival ---
		try {
			long idFestival = festivalAbm.agregar("Feria de Primavera", "Primavera", LocalDate.now(),
					LocalDate.now().plusDays(2), 800.0, 400.0, 1500.0, 250000.0, 4000.0);
			Festival festival = festivalAbm.traer(idFestival);

			if (idFoodTruck != -1) {
				UnidadDeVenta foodTruck = abm.traer(idFoodTruck);
				foodTruck.setFestival(festival);
				abm.modificar(foodTruck);
				System.out.printf("%nFoodTruck asociado al festival: %s%n", abm.traer(idFoodTruck));
			}

			if (idPuesto != -1) {
				UnidadDeVenta puesto = abm.traer(idPuesto);
				puesto.setFestival(festival);
				abm.modificar(puesto);
				System.out.printf("%nPuestoDesarmable asociado al festival: %s%n", abm.traer(idPuesto));
			}
		} catch (Exception e) {
			System.out.println("No se pudo asociar el festival: " + e.getMessage());
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
