package test;

import java.time.LocalDate;

import java.util.List;

import datos.Cajero;
import datos.Cocinero;
import datos.Festival;
import datos.Personal;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.PersonalABM;
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
			int idFestival = festivalAbm.agregar("Feria de Primavera", "Primavera", LocalDate.now(),
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

		// --- STAFF: el alta de Personal pasa por PersonalABM, que valida DNI unico.
		// Aca solo se asignan a la unidad. ---
		try {
			UnidadDeVenta ft = abm.traerPorCodigo("FT00000001");
			if (ft != null && ft.getLstPersonal().isEmpty()) {
				int idAna = altaOReutiliza(new Cocinero("Ana", "Gomez", "30111222", LocalDate.of(1985, 4, 12),
						LocalDate.of(2020, 3, 1), "Parrilla", 15000));
				int idLuis = altaOReutiliza(new Cocinero("Luis", "Perez", "28999111", LocalDate.of(1982, 9, 5),
						LocalDate.of(2019, 7, 15), "Pastas", 12000));
				int idMarta = altaOReutiliza(new Cajero("Marta", "Diaz", "33444555", LocalDate.of(1990, 1, 20),
						LocalDate.of(2021, 6, 1), "Manana"));
				int idJose = altaOReutiliza(new Cocinero("Jose", "Ruiz", "27000333", LocalDate.of(1980, 11, 2),
						LocalDate.of(2018, 2, 10), "Empanadas", 11000));

				abm.asignarPersonal("FT00000001", idAna);
				abm.asignarPersonal("FT00000001", idLuis);
				abm.asignarPersonal("FT00000001", idMarta);
				abm.asignarPersonal("PD00000001", idJose);
				System.out.println("\nStaff dado de alta por PersonalABM y asignado.");
			} else {
				System.out.println("\nEl staff ya estaba asignado, no se duplica.");
			}
		} catch (Exception e) {
			System.out.println("No se pudo asignar el staff: " + e.getMessage());
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

		// --- CASO DE USO: cocineros de los food trucks con conexion electrica ---
		List<Object[]> filas = abm.traerCocinerosDeFoodTrucksConElectricidad();

		System.out.println("\n--- CASO DE USO: cocineros en food trucks con conexion electrica ---");
		System.out.printf("%-20s | %-20s | %-9s | %-20s | %s%n", "FESTIVAL", "UNIDAD", "PATENTE", "COCINERO",
				"ESPECIALIDAD");
		for (Object[] f : filas) {
			System.out.printf("%-20s | %-20s | %-9s | %-20s | %s%n", f[0], f[1], f[2], f[3] + ", " + f[4], f[5]);
		}
	}

	// PersonalABM.agregar lanza si el DNI ya existe, asi que el test reusa
	// el que ya este cargado. Mantiene el test repetible.
	private static int altaOReutiliza(Personal p) {
		PersonalABM personalAbm = PersonalABM.getInstance();
		Personal existente = personalAbm.traer(p.getDni());
		if (existente != null) {
			return existente.getId();
		}

		return personalAbm.agregar(p);
	}

}
