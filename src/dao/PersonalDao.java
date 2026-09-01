package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.Personal;

public class PersonalDao {

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
	
	public int agregar(Personal objeto) {
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
	
	public void actualizar(Personal personal) {
		try {
			iniciaOperacion();
			session.update(personal);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public void eliminar(Personal personal) {
		try {
			iniciaOperacion();
			session.delete(personal);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}
	
	public Personal traer(int id) {
		Personal objeto = null;
		try {
		iniciaOperacion();
		objeto = (Personal) session.get(Personal.class, id);
		} finally {
		session.close();
		}
		return objeto;
	}
	
	public Personal traer(String dni) {
		Personal personal = null;
		try {
		iniciaOperacion();
		personal = (Personal) session
				.createQuery("from Personal p where p.dni= :dni")
				.setParameter("dni", dni)
				.uniqueResult();
		
		} finally {
		session.close();
		}
		return personal;
		}
	
	public List<Personal> traer(){
		List<Personal> lista = new ArrayList<Personal>();
		try {
			iniciaOperacion();
			lista = session
					.createQuery("from Personal", Personal.class)
					.list();
		} finally {
			session.close();
		}
		return lista;
	}
}
