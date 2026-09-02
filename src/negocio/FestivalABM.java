package negocio;
import java.time.LocalDate;
import dao.FestivalDao;
import datos.Festival;

public class FestivalABM {
	
	FestivalDao dao = new FestivalDao();
	


	public Festival traer(int idFestival) {
		return dao.traer(idFestival);
	}

	public int agregar(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,Double costoSuperficie, Double plusElectricidad,
			Double costoMontaje, Double sueldoBase,Double valorAnioAntiguedad) {
		Festival f = new Festival(nombre,  temporada,fechaInicio,fechaFin,costoSuperficie, plusElectricidad,
				costoMontaje,sueldoBase, valorAnioAntiguedad);
		return dao.agregar(f);
	}
		
	public void modificar(Festival f) {
		dao.actualizar(f);
	}

	public void eliminar(int idFestival) {		
		Festival f = dao.traer(idFestival);
		dao.eliminar(f);
	}

	public Festival traerFestivalYUnidadDeVenta(int idFestival) {
		// TODO Auto-generated method stub
		return dao.traerFestivalYUnidadDeVenta(idFestival);
	}
	
}
