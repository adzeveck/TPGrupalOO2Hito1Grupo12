package test;

import java.time.LocalDate;
import java.util.List;

import datos.Cajero;
import datos.Personal;
import negocio.PersonalABM;

public class TestAgregarPersonal {

	public static void main(String[] args) {

		PersonalABM abm = new PersonalABM();
		
		int idCajero=-1;
		
		try {
			idCajero=abm.agregarCajero("Roberto", "Carlos", "33333333", LocalDate.of(1998, 3, 25), LocalDate.of(2021, 5, 1),"noche");
			System.out.printf("Alta Cajero id: %d%n",idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: "+e.getMessage());
		}
		
		int idCocinero=-1;
		try {
			idCocinero=abm.agregarCocinero("Mercedes", "Ledesma", "22222222",LocalDate.of(1985, 8, 12),LocalDate.of(2018, 2, 1), "Chef" ,40000 );
			System.out.printf("Alta Cocinero id: %d%n",idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: "+e.getMessage());
			e.printStackTrace();
		}
		// --- BUSQUEDA POR TURNO
		List<Cajero> nocheros = abm.traerCajerosPorTurno("noche");
		nocheros.forEach(System.out::println);
		
		
		// --- BUSQUEDA POR FECHA DE INGRESO
		List<Personal> personalPorIngreso= abm.buscarPorFechaDeIngreso(LocalDate.of(2020, 1, 1), LocalDate.now());
		personalPorIngreso.forEach(System.out::println);
		
		// --- TOTAL PERSONAL
		System.out.println(abm.contarPersonal());
		
		//--- PROMEDIO PLUS POR CATEGORIA
		System.out.println(abm.promedioPlusCocinero());
		
		
	}

}
