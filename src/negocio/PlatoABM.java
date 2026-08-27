package negocio;

import dao.PlatoDao;
import datos.Plato;

public class PlatoABM {

	private PlatoDao dao = new PlatoDao();

	public int agregar(Plato p) {
		if (dao.traer(p.getNombre()) != null) {
			throw new IllegalStateException("Ya existe un plato con el nombre: " + p.getNombre());
		}
		return dao.agregar(p);
	}

	public Plato traer(int id) {
		return dao.traer(id);
	}

}
