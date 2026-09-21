package test;

import java.time.LocalDate;
import java.util.List;

import datos.Cajero;
import datos.Festival;
import datos.Personal;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.PersonalABM;
import negocio.UnidadDeVentaABM;

public class TestCasoDeUsoPersonal {

	public static void main(String[] args) {

		PersonalABM abm = new PersonalABM();
		UnidadDeVentaABM unidadDeVentaAbm = new UnidadDeVentaABM();
		FestivalABM festivalAbm = new FestivalABM();

		// --- BUSQUEDA POR TURNO
		List<Cajero> cajerosTurno = abm.traerCajerosPorTurno("Mañana");
		System.out.println("\n---BUSQUEDA POR TURNO: ");
		cajerosTurno.forEach(System.out::println);
		System.out.println();

		// --- BUSQUEDA POR FECHA DE INGRESO
		List<Personal> personalPorIngreso = abm.buscarPorFechaDeIngreso(LocalDate.of(2022, 1, 1), LocalDate.now());
		System.out.println("\n---BUSQUEDA POR FECHA DE INGRESO: ");
		personalPorIngreso.forEach(System.out::println);
		System.out.println();

		// --- TOTAL PERSONAL
		System.out.println("TOTAL PERSONAL: "+abm.contarPersonal());
		System.out.println();

		// --- PROMEDIO PLUS POR CATEGORIA
		System.out.println("\n---PROMEDIO PLUS CATEGORIA: "+abm.promedioPlusCocinero());
		System.out.println();


		// --- BUSQUEDA DE CAJERO EN UNIDAD POR TURNO
		try {
			Festival festival = festivalAbm.traer(3);
			UnidadDeVenta unidad = unidadDeVentaAbm.traer(4);
			String turno= "Noche";
			List<Cajero> cajeros = abm.cajerosDeUnidadPorTurnoYFestival(festival, unidad, turno);
			System.out.println("\n--- CAJEROS POR TURNO ** " +turno+"** DE UNIDAD: ***"+unidad.getNombre()+"*** EN FESTIVAL: ***"+festival.getNombre()+"*** ---");
			System.out.println("Cantidad encontrada: " + cajeros.size());			cajeros.forEach(System.out::println);
			
			System.out.println();

		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

		// --- BUSQUEDA DE PERSONAL MÁS INTIGUO DE UNIDAD	
		try {
			Festival festival = festivalAbm.traer(1);
			UnidadDeVenta unidad = unidadDeVentaAbm.traerPorCodigo("FT00000003");
			int años = 2;
			List<Personal> personal = abm.personalAntiguoDeUnidadPorFestival(festival,unidad, años);
			System.out.println("\n--- PERSONAL MÁS INTIGUO DE UNIDAD: ***"+unidad.getNombre()+"*** EN FESTIVAL: ***"+festival.getNombre()+"*** ---");
			System.out.println("Cantidad encontrada: "+ personal.size());
			personal.forEach(System.out::println);
			
			System.out.println();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
