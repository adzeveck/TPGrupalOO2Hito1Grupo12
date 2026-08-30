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

	public int agregar(Plato objeto) {
		int idPlato = 0;
		try {
			iniciaOperacion();
			idPlato = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return idPlato;
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

	public Plato traer(String nombre) {
		Plato objeto = null;
		try {
			iniciaOperacion();
			objeto = (Plato) session.createQuery("from Plato p where p.nombre=:nombre")
						.setParameter("nombre", nombre).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

}
