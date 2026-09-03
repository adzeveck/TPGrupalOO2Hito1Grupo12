package test;

import java.time.LocalDate;
import java.util.List;
import datos.Cajero;
import datos.Cocinero;
import datos.Personal;
import datos.UnidadDeVenta;
import negocio.PersonalABM;
import negocio.UnidadDeVentaABM;

public class TestAgregarPersonal {

	public static void main(String[] args) {

		PersonalABM abm = new PersonalABM();
		UnidadDeVentaABM unidadDeVentaAbm = new UnidadDeVentaABM();

		// --- ALTA de una unidad de cada tipo ---
		int idFoodTruck = -1;
		try {
			idFoodTruck = unidadDeVentaAbm.agregarFoodTruck("La Birra Truck", 12.5, "FT00000001", "AB123CD", true);
			System.out.printf("Alta FoodTruck id: %d%n", idFoodTruck);
		} catch (Exception e) {
			System.out.println("No se pudo agregar el Food Truck: " + e.getMessage());
		}

		int idPuesto = -1;
		try {
			idPuesto = unidadDeVentaAbm.agregarPuestoDesarmable("Empanadas del Norte", 8.0, "PD00000001", 2, 45);
			System.out.printf("Alta PuestoDesarmable id: %d%n", idPuesto);
		} catch (Exception e) {
			System.out.println("No se pudo agregar el Puesto Desarmable: " + e.getMessage());
		}
		// Agregar Cajero
		int idCajero = -1;

		try {
			idCajero = abm.agregarCajero("Roberto", "Carlos", "33333333", LocalDate.of(1998, 3, 25),
					LocalDate.of(2021, 5, 1), "noche");
			System.out.printf("Alta Cajero id: %d%n", idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: " + e.getMessage());
		}

		try {
			idCajero = abm.agregarCajero("Marcela", "Roa", "44444444", LocalDate.of(1991, 5, 5),
					LocalDate.of(2020, 2, 15), "mañana");
			System.out.printf("Alta Cajero id: %d%n", idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: " + e.getMessage());
		}
		try {
			idCajero = abm.agregarCajero("Cecilia", "Sandoval", "55555555", LocalDate.of(2000, 7, 2),
					LocalDate.of(2019, 3, 1), "noche");
			System.out.printf("Alta Cajero id: %d%n", idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: " + e.getMessage());
		}
		try {
			idCajero = abm.agregarCajero("Sergio", "Perez", "66666666", LocalDate.of(2008, 11, 16),
					LocalDate.of(2024, 5, 15), "mañana");
			System.out.printf("Alta Cajero id: %d%n", idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: " + e.getMessage());
		}
		
		//Agregar Cocinero
		int idCocinero = -1;

		try {
			idCocinero = abm.agregarCocinero("Mercedes", "Ledesma", "22222222", LocalDate.of(1985, 8, 12),
					LocalDate.of(2018, 2, 1), "Chef", 40000);
			System.out.printf("Alta Cocinero id: %d%n", idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			idCocinero = abm.agregarCocinero("Jose", "Mitre", "88888888", LocalDate.of(1998, 7, 4),
					LocalDate.of(2020, 7, 1), "Parrilero", 30000);
			System.out.printf("Alta Cocinero id: %d%n", idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: " + e.getMessage());
		}

		try {
			idCocinero = abm.agregarCocinero("Juan", "Lopez", "11111111", LocalDate.of(2000, 9, 2),
					LocalDate.of(2022, 7, 1), "Maestro pizzero", 20000);
			System.out.printf("Alta Cocinero id: %d%n", idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: " + e.getMessage());
		}
		try {
			idCocinero = abm.agregarCocinero("Jose", "Gutierrez", "77777777", LocalDate.of(1980, 4, 12),
					LocalDate.of(2025, 5, 19), "Chef", 40000);
			System.out.printf("Alta Cocinero id: %d%n", idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: " + e.getMessage());
		}

		// --- BUSQUEDA POR TURNO
		List<Cajero> nocheros = abm.traerCajerosPorTurno("noche");
		nocheros.forEach(System.out::println);

		// --- BUSQUEDA POR FECHA DE INGRESO
		List<Personal> personalPorIngreso = abm.buscarPorFechaDeIngreso(LocalDate.of(2020, 1, 1), LocalDate.now());
		personalPorIngreso.forEach(System.out::println);

		// --- TOTAL PERSONAL
		System.out.println(abm.contarPersonal());

		// --- PROMEDIO PLUS POR CATEGORIA
		System.out.println(abm.promedioPlusCocinero());

		
		// Aca se asigna el personal a las unidades. ---
		try {
			UnidadDeVenta ft1 = unidadDeVentaAbm.traerPorCodigo("FT00000001");
			
			UnidadDeVenta ft2 = unidadDeVentaAbm.traerPorCodigo("PD00000001");
			
			Personal p1 = abm.traer("33333333");
			Personal p2 = abm.traer("22222222");
			Personal p3 = abm.traer("11111111");
			Personal p4 = abm.traer("44444444");
			Personal p5 = abm.traer("88888888");
			Personal p6 = abm.traer("77777777");

			ft1.agregarPersonal(p1);
			ft1.agregarPersonal(p2);
			ft1.agregarPersonal(p3);

			ft2.agregarPersonal(p4);
			ft2.agregarPersonal(p5);
			ft2.agregarPersonal(p6);
			System.out.println("\nStaff dado de alta por PersonalABM y asignado.");
		} finally {
		}
		// --- BUSQUEDA DE CAJERO EN UNIDAD POR TURNO
		System.out.println("\n--- Cajeros turno Mañana ---");
		try {
			UnidadDeVenta ft1 = unidadDeVentaAbm.traerPorCodigo("FT00000001");
			int idFt1=ft1.getId();
			List<Cajero> manana = abm.cajerosDeUnidadPorTurno(idFt1, "Mañana");
			manana.forEach(System.out::println);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
