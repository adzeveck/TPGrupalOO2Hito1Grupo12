package test;

import datos.Festival;
import negocio.FestivalABM;


public class TestCasoDeUsoFestival {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
				FestivalABM festivalAbm = new FestivalABM();

		        Festival festival = festivalAbm.traer(1);

		        Festival festivalConUnidades = festivalAbm.traerFestivaYUnidadConElectricidad(festival, true);

		        System.out.println("\n--- Unidades de Venta ---");

		        System.out.println("\nUnidad De Venta Del Festival"+ festivalConUnidades.getLstUnidad());

		    }
	}



