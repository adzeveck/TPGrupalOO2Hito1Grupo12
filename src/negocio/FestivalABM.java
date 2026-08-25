package negocio;
import java.time.LocalDate;
import java.util.List;


import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {
	
	FestivalDao dao = new FestivalDao();
	


	public Festival traer(long idFestival) {
		return dao.traer(idFestival);
	}

	public int agregar(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin) {
		//Excepcion por cliente duplicado
		Festival f = new Festival(nombre, temporada, fechaInicio,fechaFin);
		return dao.agregar(f);
	}
		
	public void modificar(Festival f) {
		/*
		 * En caso de editar el dni, antes de actualizar, validar que no exista un cliente con el mismo dni
		 * y si eso pasa lanzar la Exception
		 */
		dao.actualizar(f);
	}

	public void eliminar(long idCliente) {
		/*
		 * En este caso la baja es física y sabemos que la entidad no tiene relaciones
		 * pero en caso de tenerlas, hay que validar que el cliente no tenga dependencias que generen errores al borrarlo.
		 */
		Festival f = dao.traer(idCliente);
		// Implementar que si es null que arroje la excepción la Excepción de que el cliente no existe
		dao.eliminar(f);
	}

	public List<Festival> traer() {
		return dao.traer();
	}
}
