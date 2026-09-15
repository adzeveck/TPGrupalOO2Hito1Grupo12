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

public class TestCasoDeIUsoPersonal {

	public static void main(String[] args) {

		PersonalABM abm = new PersonalABM();
		UnidadDeVentaABM unidadDeVentaAbm = new UnidadDeVentaABM();
		FestivalABM festival = new FestivalABM();

		// --- BUSQUEDA POR TURNO
		List<Cajero> nocheros = abm.traerCajerosPorTurno("Mañana");
		System.out.println("\n---BUSQUEDA POR TURNO: ");
		nocheros.forEach(System.out::println);
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

		//BUSQUEDA DE PERSONAL QUE CUMPLEN AÑOS DURANTE UN FESTIVAL
		try {
			Festival fest = festival.traer(2);
			List<Personal> personalCumpleañero = abm.personalCumpleañeroPorFestival(fest);
			System.out.println("\n---PERSONAL CUMPLEAÑERO DURANTE FESTIVAL: "+fest.getNombre()+"---");
			System.out.println("Cantidad encontrada: " + personalCumpleañero.size());
			personalCumpleañero.forEach(System.out::println);
			
			System.out.println();
		}catch(Exception e){
			e.getMessage();
		}


		// --- BUSQUEDA DE CAJERO EN UNIDAD POR TURNO
		try {
			UnidadDeVenta unidad = unidadDeVentaAbm.traer(4);
			String turno= "Noche";
			List<Cajero> cajeros = abm.cajerosDeUnidadPorTurno(unidad, turno);
			System.out.println("\n--- CAJEROS POR TURNO " +turno+" DE UNIDAD: "+unidad.getNombre()+" ---");
			System.out.println("Cantidad encontrada: " + cajeros.size());
			cajeros.forEach(System.out::println);
			
			System.out.println();

		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}

		// --- BUSQUEDA DE PERSONAL MÁS INTIGUO DE UNIDAD	
		try {
			UnidadDeVenta unidad = unidadDeVentaAbm.traerPorCodigo("FT00000003");
			int años = 2;
			List<Personal> personal = abm.personalAntiguoDeUnidad(unidad, años);
			System.out.println("\n--- PERSONAL MÁS INTIGUO DE UNIDAD : "+unidad.getNombre()+"---");
			System.out.println("Cantidad encontrada: "+ personal.size());
			personal.forEach(System.out::println);
			
			System.out.println();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
