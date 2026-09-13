package test;

import negocio.PedidoABM;


public class TestPlatoEstrella {

	public static void main(String[] args) {

		PedidoABM pedidoAbm = new PedidoABM();

		System.out.println("--- CASO DE USO: plato estrella por festival ---");

		System.out.println("Festival 1: " + pedidoAbm.traerPlatoEstrella(1));
		System.out.println("Festival 2: " + pedidoAbm.traerPlatoEstrella(2));
		System.out.println("Festival 3: " + pedidoAbm.traerPlatoEstrella(3));
	}

}
