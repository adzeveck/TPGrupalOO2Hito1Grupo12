package test;

import java.time.LocalDate;

import datos.Festival;
import datos.UnidadDeVenta;
import negocio.FestivalABM;
import negocio.UnidadDeVentaABM;

public class TestCasoDeUsoFestival {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		  
		
				FestivalABM festivalAbm = new FestivalABM();
				UnidadDeVentaABM unidadAbm = new UnidadDeVentaABM();
				int idFestival = 1;
				 idFestival = festivalAbm.agregar("shigoku", "invierno", LocalDate.now(), LocalDate.now().plusDays(2),
						31206.0, 276000.0,90000.0, 725000.0, 357000.0);
				Festival festival = festivalAbm.traer(idFestival);

				
				try {
				
					int idUnidad = unidadAbm.agregarFoodTruck("El vikingo", 10.3, "SB00533769", "AA223AC", true);
					int idUnidad1 = unidadAbm.agregarFoodTruck("El sucio dan", 10.3, "SB54212469", "AA223AC", true);
					UnidadDeVenta unidad = unidadAbm.traer(idUnidad);
					UnidadDeVenta unidad1 = unidadAbm.traer(idUnidad1);
					unidad.setFestival(festival);
					unidad1.setFestival(festival);
					unidadAbm.modificar(unidad);
					unidadAbm.modificar(unidad1);
					Festival fest = festivalAbm.traerFestivalYUnidadDeVenta(festival);
					
					

					
					System.out.println("\nUnidad De Venta Del Festival"+ fest.getLstUnidad());
					/*
					 * System.out.println("\nID Festival: " + fest.getIdFestival()); fest =
					 * festivalAbm.traerFestivaYUnidadConElectricidad(festival, false);
					 * 
					 * System.out.println(fest);
					 * System.out.println("\nUnidad De Venta Del Festival"+ fest.getLstUnidad());
					 */
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				 
				 
	}

}
