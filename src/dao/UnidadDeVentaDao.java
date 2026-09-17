package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.FoodTruck;
import datos.Personal;
import datos.Plato;
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
		List<UnidadDeVenta> lista = new ArrayList<>();
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

	// Recibe ids y no entidades a proposito: asi la unidad y el personal se
		// cargan en la misma sesion.
	public void asignarPersonal(int idUnidad, int idPersonal) {
		try {
			iniciaOperacion();
			UnidadDeVenta u = session.get(UnidadDeVenta.class, idUnidad);
			Personal p = session.get(Personal.class, idPersonal);
			u.agregarPersonal(p);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public int agregarPlato(int idUnidad, Plato plato) {
		int idPlato = 0;
		try {
			iniciaOperacion();
			UnidadDeVenta u = session.get(UnidadDeVenta.class, idUnidad);
			u.agregarPlato(plato);
			tx.commit();
			idPlato = plato.getIdPlato();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return idPlato;
	}

	// CASO DE USO: unidades de venta con dotacion de cocina insuficiente.
	//
	// Devuelve los food trucks que participan de los festivales que arrancan
	// dentro del periodo [desde, hasta] y que tienen MENOS de "minimoCocineros"
	// cocineros asignados, filtrando por si requieren o no conexion electrica.
	//
	// Para que sirve: detectar a que unidades hay que reforzar con personal de
	// cocina antes de que arranque el festival. Se consulta por separado las que
	// requieren corriente (equipamiento pesado, necesitan mas gente) de las que no.
	//
	// Atraviesa Festival -> UnidadDeVenta -> FoodTruck -> Personal -> Cocinero,
	// y cubre las dos relaciones del enunciado: herencia (dos veces: "from
	// FoodTruck" y "type(c) = Cocinero") y uno a muchos ("ft.lstPersonal").
	//
	// Por que LEFT join y no join:
	//   con un inner join, una unidad sin ningun cocinero no produce ninguna fila,
	//   nunca entra al group by y queda afuera del resultado. Seria justo la peor
	//   dotada. El left join la conserva.
	// Por que el filtro de Cocinero va en el having y no en el where:
	//   el where descartaria las filas donde c es NULL (las unidades sin personal),
	//   anulando el left join. Por eso se cuenta condicionalmente:
	//   sum(case when type(c) = Cocinero then 1 else 0 end).
	public List<FoodTruck> traerFoodTrucksConDotacionInsuficiente(boolean requiereElectricidad,
			LocalDate desde, LocalDate hasta, long minimoCocineros) {
		List<FoodTruck> lista = new ArrayList<>();
		try {
			iniciaOperacion();
			Query<FoodTruck> query = session.createQuery(
					"select ft "
					+ "from FoodTruck ft "
					+ "join ft.festival f "
					+ "left join ft.lstPersonal c "
					+ "where ft.requiereElectricidad = :requiereElectricidad "
					+ "and f.fechaInicio between :desde and :hasta "
					+ "group by ft "
					+ "having sum(case when type(c) = Cocinero then 1 else 0 end) < :minimoCocineros "
					+ "order by sum(case when type(c) = Cocinero then 1 else 0 end) asc, ft.nombre asc",
					FoodTruck.class);
			query.setParameter("requiereElectricidad", requiereElectricidad);
			query.setParameter("desde", desde);
			query.setParameter("hasta", hasta);
			query.setParameter("minimoCocineros", minimoCocineros);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}




}
