package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.UnidadDeVenta;

public class UnidadDeVentaDao {

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

	public int agregar(UnidadDeVenta objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(UnidadDeVenta objeto) {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public void eliminar(UnidadDeVenta objeto) {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public UnidadDeVenta traer(long id) {
		UnidadDeVenta objeto = null;
		try {
			iniciaOperacion();
			objeto = (UnidadDeVenta) session.get(UnidadDeVenta.class, id);
		} finally {
			session.close();
		}
		return objeto;
	}

	public UnidadDeVenta traerPorCodigo(String codigo) {
		UnidadDeVenta objeto = null;
		try {
			iniciaOperacion();
			objeto = (UnidadDeVenta) session
					.createQuery("from UnidadDeVenta u where u.codigo = :codigo")
					.setParameter("codigo", codigo)
					.uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<UnidadDeVenta> traer() {
		List<UnidadDeVenta> lista = new ArrayList<UnidadDeVenta>();
		try {
			iniciaOperacion();
			Query<UnidadDeVenta> query = session
					.createQuery("from UnidadDeVenta u order by u.nombre asc", UnidadDeVenta.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
}
