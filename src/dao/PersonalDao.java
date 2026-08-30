package dao;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Personal;

public class PersonalDao {

	private static Session session;
	private Transaction tx;
	private static PersonalDao instancia=null;
	
	protected PersonalDao() {
	}
	
	public static PersonalDao getInstance() {
		if(instancia==null) {
			instancia = new PersonalDao();
		}
	return instancia;
	}
	

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
	public int agregar(Personal personal) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(personal).toString());
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
	
	//****REVISAR****
	// EN GUIA 3 UTILIZABA  TRAER POR ID COMO .uniqueResult()
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
		personal = (Personal) session.createQuery("from Personal p where p.dni= :dni").setParameter("dni", dni).uniqueResult();
		// En este caso :dni es un marcador de posición para el parámetro.
		// Al utilizar el método setParameter para asignar el valor del parámetro dni esto ayuda a prevenir la inyección de SQL.
		} finally {
		session.close();
		}
		return personal;
		}
	
	public List<Personal> traer() throws HibernateException {
		List<Personal> lista = new ArrayList<Personal>();
		try {
			iniciaOperacion();
			lista = session.createQuery("from Personal", Personal.class).list();
		} finally {
			session.close();
		}
		return lista;
	}
}
