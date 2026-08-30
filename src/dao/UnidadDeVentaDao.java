package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.UnidadDeVenta;
import datos.Personal;

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

	// festival se trae con left join fetch (no session.get() simple): es
	// nullable y queda lazy por default, y el Dao cierra la sesion antes de
	// que el toString() de FoodTruck/PuestoDesarmable lo necesite.
	public UnidadDeVenta traer(int id) {
		UnidadDeVenta objeto = null;
		try {
			iniciaOperacion();
			objeto = (UnidadDeVenta) session
					.createQuery("from UnidadDeVenta u left join fetch u.festival where u.id = :id")
					.setParameter("id", id)
					.uniqueResult();
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
					.createQuery("from UnidadDeVenta u left join fetch u.festival where u.codigo = :codigo")
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
					.createQuery("from UnidadDeVenta u left join fetch u.festival order by u.nombre asc",
							UnidadDeVenta.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	// Toda la operacion ocurre dentro de una misma sesion a proposito.
	// Si trajera la unidad con traer() y le agregara el personal afuera, la
	// coleccion estaria lazy sobre un objeto detached y saltaria
	// LazyInitializationException.
	public void asignarPersonal(int idUnidad, Personal p) {
		try {
			iniciaOperacion();
			UnidadDeVenta u = session.get(UnidadDeVenta.class, idUnidad);
			u.agregarPersonal(p);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	// CASO DE USO: cocineros asignados a los food trucks que requieren
	// conexion electrica, con el festival en el que estan.
	// Atraviesa Festival -> UnidadDeVenta -> FoodTruck -> Personal -> Cocinero.
	public List<Object[]> traerCocinerosDeFoodTrucksConElectricidad() {
		List<Object[]> lista = new ArrayList<Object[]>();
		try {
			iniciaOperacion();
			Query<Object[]> query = session.createQuery(
					"select f.nombre, ft.nombre, ft.patente, c.apellido, c.nombre, c.especialidad "
					+ "from FoodTruck ft "
					+ "left join ft.festival f "
					+ "join ft.lstPersonal c "
					+ "where type(c) = Cocinero "
					+ "and ft.requiereElectricidad = true "
					+ "order by f.nombre asc, ft.nombre asc, c.apellido asc",
					Object[].class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

	
	
}
