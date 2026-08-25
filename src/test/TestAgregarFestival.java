package test;

import negocio.FestivalABM;

import java.time.LocalDate;
public class TestAgregarFestival {

	public static void main(String[] args) {

FestivalABM abm = new FestivalABM();
		
		try {
			long ultimoIdFestival;
			ultimoIdFestival = abm.agregar("Apellido", "Nombre", LocalDate.now(), LocalDate.now());
			System.out.printf("Id cliente: %d", ultimoIdFestival);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

		}
	}
