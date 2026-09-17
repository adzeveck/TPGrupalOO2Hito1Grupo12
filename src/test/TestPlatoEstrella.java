package test;

import datos.Festival;
import negocio.FestivalABM;
import negocio.PedidoABM;


public class TestPlatoEstrella {

	public static void main(String[] args) {

		FestivalABM festivalAbm = new FestivalABM();
		PedidoABM pedidoAbm = new PedidoABM();

		System.out.println("--- CASO DE USO: plato estrella por festival ---");

		Festival otonio = festivalAbm.traer("Feria de Otoño");
		Festival verano = festivalAbm.traer("Festival Sabores de Verano");
		Festival primavera = festivalAbm.traer("Feria Primavera Gourmet");

		System.out.println(otonio.getNombre() + ": " + pedidoAbm.traerPlatoEstrella(otonio));
		System.out.println(verano.getNombre() + ": " + pedidoAbm.traerPlatoEstrella(verano));
		System.out.println(primavera.getNombre() + ": " + pedidoAbm.traerPlatoEstrella(primavera));
	}

}
