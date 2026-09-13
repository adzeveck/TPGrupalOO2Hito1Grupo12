package test;

import java.time.LocalDate;
import java.util.List;

import negocio.UnidadDeVentaABM;

public class TestUnidadDeVenta {

	// Los datos los carga epicentro_gourmet.sql (raiz del repo).
	// Este test solo consulta: no da de alta nada.
	public static void main(String[] args) {

		UnidadDeVentaABM abm = new UnidadDeVentaABM();

		System.out.println("--- CASO DE USO: dotacion de cocineros en food trucks con conexion electrica ---");
		System.out.println("Sirve para dimensionar el tendido electrico del predio y ver si las");
		System.out.println("unidades criticas tienen personal con experiencia.");
		System.out.println();

		mostrar(abm, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 12, 31));
		mostrar(abm, LocalDate.of(2026, 7, 1), LocalDate.of(2026, 12, 31));
	}

	private static void mostrar(UnidadDeVentaABM abm, LocalDate desde, LocalDate hasta) {
		List<Object[]> filas = abm.traerDotacionCocinerosFoodTrucksConElectricidad(desde, hasta);

		System.out.printf("Festivales que arrancan entre %s y %s:%n", desde, hasta);

		if (filas.isEmpty()) {
			System.out.println("  (ningun food truck con conexion electrica en ese periodo)");
			System.out.println();
			return;
		}

		System.out.printf("  %-22s | %-22s | %-9s | %-9s | %s%n",
				"FESTIVAL", "UNIDAD", "PATENTE", "COCINEROS", "INGRESO MAS ANTIGUO");
		for (Object[] f : filas) {
			System.out.printf("  %-22s | %-22s | %-9s | %-9s | %s%n", f[0], f[1], f[2], f[3], f[4]);
		}
		System.out.println();
	}
}
