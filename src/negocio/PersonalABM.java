package negocio;

import java.time.LocalDate;
import java.util.List;
import dao.PersonalDao;
import datos.Cajero;
import datos.Cocinero;
import datos.Personal;

public class PersonalABM {
	
	private PersonalDao dao = new PersonalDao();
	
	public int agregarCocinero(String nombre, String apellido, String dni,LocalDate fechaNacimiento,
			LocalDate fechaIngreso,String especialidad, double plusCategoria) {
		Cocinero cocinero = new Cocinero(nombre,apellido,dni,fechaNacimiento,fechaIngreso,especialidad,plusCategoria);
		return dao.agregar(cocinero);
	}
	public int agregarCajero(String nombre, String apellido, String dni,LocalDate fechaNacimiento,
			LocalDate fechaIngreso,String turno) {
		Cajero cajero = new Cajero(nombre,apellido,dni,fechaNacimiento,fechaIngreso,turno);
		return dao.agregar(cajero);
	}
	public Personal traer(int id) {
        return dao.traer(id);
    }

    public Personal traer(String dni) {
        return dao.traer(dni);
    }

    public int agregar(Personal personal) throws Exception {
        Personal p =dao.traer(personal.getDni());
        if (p != null) {
            throw new Exception("Ya existe un Personal con ese DNI");
        }
        return dao.agregar(personal);
    }

    public void modificar(Personal personal) throws Exception {
        Personal p = dao.traer(personal.getDni());
        if (p != null && p.getId() != personal.getId()) {
            throw new Exception("Ya existe otro Personal con ese DNI");
        }
        dao.actualizar(personal);
    }

    public void eliminar(int id) throws Exception {
        Personal p = dao.traer(id);
        if (p == null) {
            throw new Exception("El Personal no existe");
        }
        dao.eliminar(p);
    }

    public List<Personal> traer() {
        return dao.traer();
    }
    
    public List<Cajero> traerCajerosPorTurno(String turno) {
        return dao.listarPorTurno(turno);
    }
    
    public long contarPersonal() {
    	return dao.contarPersonal();
    }
    
    public Double promedioPlusCocinero() {
    	return dao.promedioPlusCocinero();
    }
    
    public List<Personal> buscarPorFechaDeIngreso(LocalDate desde,LocalDate hasta){
    	return dao.buscarPorFechaIngreso(desde, hasta);
    }
    public List<Cajero> cajerosDeUnidadPorTurno(int idUnidad, String turno){
    	return dao.cajerosDeUnidadPorTurno(idUnidad, turno);
    }
	public List<Personal> personalAntiguoDeUnidad(int idUnidad, int aniosMinimos) {
		return dao.personalAntiguoDeUnidad(idUnidad, aniosMinimos);
	}
}
