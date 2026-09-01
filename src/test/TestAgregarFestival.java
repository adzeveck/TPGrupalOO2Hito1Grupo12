package test;

import negocio.FestivalABM;


import java.time.LocalDate;
public class TestAgregarFestival {

	public static void main(String[] args) {

FestivalABM abm = new FestivalABM();
		
		try {
			int ultimoIdFestival;
			ultimoIdFestival = abm.agregar("Nombre","temporada" , LocalDate.now(), LocalDate.now(),
					1111.111,2222.222,3333.333,4444.444,5555.555);
			System.out.printf("Id cliente: %d", ultimoIdFestival);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

		}
	}
