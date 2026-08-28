package dao;

import java.util.ArrayList;



import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Festival;

public class FestivalDao {
	
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

	public int agregar(Festival objeto) {
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

	public void actualizar(Festival objeto) {
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

	public void eliminar(Festival objeto) {
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

	public Festival traer(long idFestival) {
		Festival objeto = null;
		try {
			iniciaOperacion();
			objeto = (Festival) session.get(Festival.class, idFestival);
		} finally {
			session.close();
		}
		return objeto;
	}

	

	public List<Festival> traer() {
		List<Festival> lista = new ArrayList<Festival>();
		try {
			iniciaOperacion();
			Query<Festival> query = session.createQuery("from Festival f order by f.nombre asc", Festival.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public Festival traerFestivalYCosto(long idFestival) throws HibernateException{
		Festival objeto = null;
		try {
		iniciaOperacion();
		String hql ="from Festival f inner join fetch f.costo where f.idFestival = idFestival";
		objeto = (Festival) session.createQuery(hql).setParameter("idFestival", idFestival).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}


}