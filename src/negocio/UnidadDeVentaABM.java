package negocio;

import java.util.List;

import dao.UnidadDeVentaDao;
import datos.FoodTruck;
import datos.PuestoDesarmable;
import datos.UnidadDeVenta;

public class UnidadDeVentaABM {

	UnidadDeVentaDao dao = new UnidadDeVentaDao();

	public int agregarFoodTruck(String nombre, double superficie, String codigo, String patente,
			boolean requiereElectricidad) throws Exception {
		validarCodigo(codigo);
		FoodTruck ft = new FoodTruck(nombre, superficie, codigo, patente, requiereElectricidad);
		return dao.agregar(ft);
	}

	public int agregarPuestoDesarmable(String nombre, double superficie, String codigo, int cantCarpas,
			int tiempoMontaje) throws Exception {
		validarCodigo(codigo);
		PuestoDesarmable pd = new PuestoDesarmable(nombre, superficie, codigo, cantCarpas, tiempoMontaje);
		return dao.agregar(pd);
	}

	// Logica de validacion propia del codigo, segun el enunciado:
	// 10 caracteres y unico en todo el predio.
	private void validarCodigo(String codigo) throws Exception {
		if (codigo == null || codigo.length() != 10) {
			throw new Exception("El codigo debe tener exactamente 10 caracteres");
		}
		if (dao.traerPorCodigo(codigo) != null) {
			throw new Exception("Ya existe una unidad de venta con el codigo " + codigo);
		}
	}

	public UnidadDeVenta traer(long id) {
		return dao.traer(id);
	}

	public UnidadDeVenta traerPorCodigo(String codigo) {
		return dao.traerPorCodigo(codigo);
	}

	public List<UnidadDeVenta> traer() {
		return dao.traer();
	}

	public void modificar(UnidadDeVenta u) throws Exception {
		UnidadDeVenta otra = dao.traerPorCodigo(u.getCodigo());
		if (otra != null && otra.getId() != u.getId()) {
			throw new Exception("Ya existe otra unidad de venta con el codigo " + u.getCodigo());
		}
		dao.actualizar(u);
	}

	public void eliminar(long id) throws Exception {
		UnidadDeVenta u = dao.traer(id);
		if (u == null) {
			throw new Exception("No existe la unidad de venta con id " + id);
		}
		dao.eliminar(u);
	}
}
