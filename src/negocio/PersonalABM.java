package negocio;

import java.util.List;
import dao.PersonalDao;
import datos.Personal;

public class PersonalABM {
	
	private static PersonalABM instancia = null;
	protected PersonalABM() {
	}
	public static PersonalABM getInstance() {
	if (instancia == null)
	instancia = new PersonalABM();
	return instancia;
	}
	
	public Personal traer(int id) {
        return PersonalDao.getInstance().traer(id);
    }

    public Personal traer(String dni) {
        return PersonalDao.getInstance().traer(dni);
    }

    public int agregar(Personal personal) {
        Personal p = PersonalDao.getInstance().traer(personal.getDni());
        if (p != null) {
            throw new RuntimeException("Ya existe un Personal con ese DNI");
        }
        return PersonalDao.getInstance().agregar(personal);
    }

    public void modificar(Personal personal) {
        Personal p = PersonalDao.getInstance().traer(personal.getDni());
        if (p != null && p.getId() != personal.getId()) {
            throw new RuntimeException("Ya existe otro Personal con ese DNI");
        }
        PersonalDao.getInstance().actualizar(personal);
    }

    public void eliminar(int id) {
        Personal p = PersonalDao.getInstance().traer(id);
        if (p == null) {
            throw new RuntimeException("El Personal no existe");
        }
        PersonalDao.getInstance().eliminar(p);
    }

    public List<Personal> traer() {
        return PersonalDao.getInstance().traer();
    }
}
