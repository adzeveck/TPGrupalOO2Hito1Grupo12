package test;

import java.time.LocalDate;

import datos.FoodTruck;
import negocio.UnidadDeVentaABM;

public class TestUnidadDeVenta {

	// Los datos los carga epicentro_gourmet.sql (raiz del repo).
	// Este test solo consulta: no da de alta nada.
	public static void main(String[] args) throws Exception {

		UnidadDeVentaABM abm = new UnidadDeVentaABM();
		LocalDate desde = LocalDate.of(2026, 1, 1);
		LocalDate hasta = LocalDate.of(2026, 12, 31);

		System.out.println("--- CASO DE USO: unidades de venta con dotacion de cocina insuficiente ---");
		System.out.println("Food trucks de los festivales del periodo con menos cocineros que el minimo,");
		System.out.println("para saber a cuales hay que reforzar antes de que arranque el festival.");

		System.out.println("\nCon conexion electrica, festivales entre " + desde + " y " + hasta + ", minimo 3:");
		for (FoodTruck ft : abm.traerFoodTrucksConDotacionInsuficiente(true, desde, hasta, 3)) {
			System.out.println("  " + ft.getNombre() + " (" + ft.getFestival().getNombre() + "): "
					+ ft.cantidadCocineros() + " cocineros");
		}

		System.out.println("\nSin conexion electrica, mismo periodo y mismo minimo:");
		for (FoodTruck ft : abm.traerFoodTrucksConDotacionInsuficiente(false, desde, hasta, 3)) {
			System.out.println("  " + ft.getNombre() + " (" + ft.getFestival().getNombre() + "): "
					+ ft.cantidadCocineros() + " cocineros");
		}

		// Sin festivales que arranquen en el segundo semestre: tiene que dar vacio.
		System.out.println("\nCon conexion electrica, pero solo el segundo semestre:");
		System.out.println("  " + abm.traerFoodTrucksConDotacionInsuficiente(true,
				LocalDate.of(2026, 7, 1), hasta, 3));
	}
}
