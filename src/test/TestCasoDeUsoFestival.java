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

				int idFestival = festivalAbm.agregar("shigoku", "invierno", LocalDate.now(), LocalDate.now().plusDays(2),
						31206.0, 276000.0,90000.0, 725000.0, 357000.0);
				Festival festival = festivalAbm.traer(idFestival);

				
				try {
				
					int idUnidad = unidadAbm.agregarFoodTruck("El vikingo", 10.3, "AA00000001", "AA223AC", true);
					UnidadDeVenta unidad = unidadAbm.traer(idUnidad);
					unidad.setFestival(festival);
					unidadAbm.modificar(unidad);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Festival fest = festivalAbm.traerFestivalYUnidadDeVenta(idFestival);
				

				System.out.println("\n---festival---");
				System.out.println(fest);
	}

}
