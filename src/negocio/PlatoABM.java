package negocio;

import dao.PlatoDao;
import datos.Plato;

public class PlatoABM {

	private PlatoDao dao = new PlatoDao();


	public Plato traer(int idPlato) {
		return dao.traer(idPlato);
	}

	public Plato traer(String nombre, int idUnidad) {
		return dao.traer(nombre, idUnidad);
	}

}
