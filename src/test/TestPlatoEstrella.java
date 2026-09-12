package test;

import negocio.PedidoABM;

// CASO DE USO: plato estrella de cada festival (el plato mas vendido, sumando
// las ventas de TODAS las unidades del festival).
//
// Test de SOLO CONSULTA: la base tiene que estar cargada previamente con el
// script epicentro_gourmet.sql. No se da de alta nada por Hibernate; solo se
// consulta lo que ya existe en la base.
public class TestPlatoEstrella {

	public static void main(String[] args) {

		PedidoABM pedidoAbm = new PedidoABM();

		System.out.println("--- CASO DE USO: plato estrella por festival ---");

		// Resultados esperados segun los datos del seed:
		//   Festival 1 (Feria de Otoño)             -> Hamburguesa       (7 vendidas)
		//   Festival 2 (Festival Sabores de Verano) -> Empanada de carne (9 vendidas)
		//   Festival 3 (Feria Primavera Gourmet)    -> Café              (4 vendidas)
		// El festival 2 es el mas interesante: "Empanada de carne" la venden dos
		// unidades distintas (son dos Platos distintos con el mismo nombre), y la
		// consulta las suma por nombre para dar con el plato estrella real.
		System.out.println("Festival 1: " + pedidoAbm.traerPlatoEstrella(1));
		System.out.println("Festival 2: " + pedidoAbm.traerPlatoEstrella(2));
		System.out.println("Festival 3: " + pedidoAbm.traerPlatoEstrella(3));
	}

}
