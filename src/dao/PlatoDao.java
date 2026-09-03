package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Plato;

public class PlatoDao {

	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}


	public Plato traer(int idPlato) {
		Plato objeto = null;
		try {
			iniciaOperacion();
			objeto = (Plato) session.get(Plato.class, idPlato);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Plato traer(String nombre, int idUnidad) {
		Plato objeto = null;
		try {
			iniciaOperacion();
			objeto = (Plato) session
					.createQuery("from Plato p where p.nombre = :nombre and p.unidad.id = :idUnidad")
					.setParameter("nombre", nombre)
					.setParameter("idUnidad", idUnidad)
					.uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

}
