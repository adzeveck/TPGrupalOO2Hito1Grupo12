package dao;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cajero;
import datos.Festival;
import datos.Personal;
import datos.UnidadDeVenta;

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
		List<Personal> lista = new ArrayList<>();
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

	// Personal por turno
	public List<Cajero> listarPorTurno(String turno) {
		List<Cajero> lista = new ArrayList<>();
	    try {
	    	iniciaOperacion();
	        lista = session
	        		.createQuery("FROM Cajero WHERE turno = :turno", Cajero.class)
	                .setParameter("turno", turno)
	                .list();
	    } finally {
	    	session.close();
	    }
	    return lista;
	}

	// Cantidad total de personal
	public long contarPersonal() {
	    long total;
		try{
	    	iniciaOperacion();
	    	total= session
	    			.createQuery("SELECT COUNT(p) FROM Personal p", Long.class)
	                .uniqueResult();
	    } finally {
	    	session.close();
	    }
	    return total;
	}
	// Promedio de plusCategoria de los Cocineros
	public Double promedioPlusCocinero() {
	    double promedio;
		try {
			iniciaOperacion();
	        promedio = session.createQuery("SELECT AVG(c.plusCategoria) FROM Cocinero c", Double.class)
	                .uniqueResult();
	    } finally {
	    	session.close();
	    }
		return promedio;
	}

	
	// Personal contratado en un rango de fechas
	public List<Personal> buscarPorFechaIngreso(LocalDate desde, LocalDate hasta) {
	    List<Personal> personal = new ArrayList<>();
		try {
	    	iniciaOperacion();
	        personal = session.createQuery(
	                "FROM Personal WHERE fechaIngreso BETWEEN :desde AND :hasta", Personal.class)
	                .setParameter("desde", desde)
	                .setParameter("hasta", hasta)
	                .getResultList();
	    } finally {
	    	session.close();
	    }
		return personal;
	}
	// Cajeros de Unidad por Turno
	public List<Cajero> cajerosDeUnidadPorTurnoYFestival(Festival festival, UnidadDeVenta unidad, String turno) {
	    try {
	    	iniciaOperacion();
	    	List<Personal> resultado = session.createQuery(
	    			"SELECT c FROM Festival f JOIN f.lstUnidad u JOIN TREAT(u.lstPersonal AS Cajero) c " +
	    			"WHERE f = :festival AND u = :unidad AND LOWER(c.turno) = LOWER(:turno)", Personal.class)
	                .setParameter("festival", festival)
	    			.setParameter("unidad", unidad)
	                .setParameter("turno", turno)
	                .getResultList();
	        List<Cajero> cajeros = new ArrayList<Cajero>();
	        for (Personal p : resultado) {
	            cajeros.add((Cajero) p);
	        }
	        return cajeros;
	    }finally {
	    	session.close();
	    }
	}


	public List<Personal> personalAntiguoDeUnidadPorFestival(Festival festival, UnidadDeVenta unidad, int aniosMinimos) {
	    LocalDate fechaLimite = LocalDate.now().minusYears(aniosMinimos);
	    try {
	        iniciaOperacion();
	        return session.createQuery(
	        		"SELECT p FROM Festival f JOIN f.lstUnidad u JOIN u.lstPersonal p " +
	                "WHERE f = :festival AND u = :unidad AND p.fechaIngreso <= :fechaLimite " +
	                "ORDER BY p.fechaIngreso ASC", Personal.class)
	        		.setParameter("festival", festival)
	        		.setParameter("unidad", unidad)
	                .setParameter("fechaLimite", fechaLimite)
	                .getResultList();
	    } finally {
	        session.close();
	    }
	}
}
