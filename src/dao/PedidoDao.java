package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Pedido;

public class PedidoDao {

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

	public int agregar(Pedido objeto) {
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

	public Pedido traer(long idPedido) {
		Pedido objeto = null;
		try {
			iniciaOperacion();
			objeto = (Pedido) session.get(Pedido.class, idPedido);
		} finally {
			session.close();
		}
		return objeto;
	}

	// Caso de uso: trae el Pedido con su lstDetalle y el Plato de cada detalle
	// ya cargados
	public Pedido traerConDetalle(long idPedido) {
		Pedido objeto = null;
		try {
			iniciaOperacion();

			String hql = "select distinct p from Pedido p "
					+ "inner join fetch p.lstDetalle d "
					+ "inner join fetch d.plato "
					+ "inner join fetch p.unidad u "
					+ "left join fetch u.festival "
					+ "where p.idPedido = :idPedido";
			objeto = (Pedido) session.createQuery(hql).setParameter("idPedido", idPedido).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}


	// CASO DE USO: el nombre del plato mas vendido en todo un festival, sumando
	// las ventas de TODAS sus unidades
	public String traerPlatoEstrella(int idFestival) {
		String nombre = null;
		try {
			iniciaOperacion();
			String hql = "select d.plato.nombre "
					+ "from DetallePedido d "
					+ "where d.pedido.unidad.festival.idFestival = :idFestival "
					+ "group by d.plato.nombre "
					+ "order by sum(d.cantidad) desc";
			nombre = (String) session.createQuery(hql)
					.setParameter("idFestival", idFestival)
					.setMaxResults(1)
					.uniqueResult();
		} finally {
			session.close();
		}
		return nombre;
	}

}
