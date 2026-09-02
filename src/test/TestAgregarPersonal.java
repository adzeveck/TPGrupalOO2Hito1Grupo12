package test;

import java.time.LocalDate;

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
		try {
			idCajero=abm.agregarCajero("Marcela", "Roa", "44444444", LocalDate.of(1991, 5, 5), LocalDate.of(2020, 2, 15),"mañana");
			System.out.printf("Alta Cajero id: %d%n",idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: "+e.getMessage());
		}
		try {
			idCajero=abm.agregarCajero("Cecilia", "Sandoval", "55555555", LocalDate.of(2000, 7, 2), LocalDate.of(2019, 3, 1),"noche");
			System.out.printf("Alta Cajero id: %d%n",idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: "+e.getMessage());
		}
		try {
			idCajero=abm.agregarCajero("Sergio", "Perez", "66666666", LocalDate.of(2008, 11, 16), LocalDate.of(2024, 5, 15),"mañana");
			System.out.printf("Alta Cajero id: %d%n",idCajero);
		} catch (Exception e) {
			System.out.println("No se pude agragar Cajero: "+e.getMessage());
		}
		
		int idCocinero=-1;
		try {
			idCocinero=abm.agregarCocinero("Mercedes", "Ledesma", "22222222",LocalDate.of(1985, 8, 12),LocalDate.of(2018, 2, 1), "Chef" ,40000 );
			System.out.printf("Alta Cocinero id: ",idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: "+e.getMessage());
		}

		try {
			idCocinero=abm.agregarCocinero("Juan", "Lopez", "11111111",LocalDate.of(2000, 9, 2),LocalDate.of(2022, 7, 1), "Maestro pizzero" ,20000 );
			System.out.printf("Alta Cocinero id: %d%n",idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: "+e.getMessage());
		}
		try {
			idCocinero=abm.agregarCocinero("Jose", "Gutierrez", "77777777",LocalDate.of(1980, 4, 12),LocalDate.of(2025, 5, 19), "Chef" ,40000 );
			System.out.printf("Alta Cocinero id: %d%n",idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: "+e.getMessage());
		}
				

	}

}
