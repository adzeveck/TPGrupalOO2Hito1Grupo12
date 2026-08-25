package test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import datos.FoodTruck;
import datos.PuestoDesarmable;
import datos.UnidadDeVenta;

public class TestUnidadDeVenta {

	public static void main(String[] args) {

		// --- ALTA de una unidad de cada tipo ---
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(new FoodTruck("La Birra Truck", 12.5, "FT00000001", "AB123CD", true));
		session.save(new PuestoDesarmable("Empanadas del Norte", 8.0, "PD00000001", 2, 45));
		tx.commit();
		session.close();

		// --- CONSULTA POLIMORFICA: pido la clase padre y traigo las dos hijas ---
		session = HibernateUtil.getSessionFactory().openSession();
		List<UnidadDeVenta> unidades = session
				.createQuery("from UnidadDeVenta u order by u.nombre", UnidadDeVenta.class).getResultList();
		session.close();

		System.out.println("\n--- Unidades de venta en la base ---");
		for (UnidadDeVenta u : unidades) {
			System.out.println(u);
		}
	}
}
