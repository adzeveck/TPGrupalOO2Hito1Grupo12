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
		
		int idCocinero=-1;
		try {
			idCocinero=abm.agregarCocinero("Mercedes", "Ledesma", "22222222",LocalDate.of(1985, 8, 12),LocalDate.of(2018, 2, 1), "Chef" ,40000 );
			System.out.printf("Alta Cocinero id: ",idCocinero);
		} catch (Exception e) {
			System.out.println("No se puede cargar Cocinero: "+e.getMessage());
		}
	}

}
