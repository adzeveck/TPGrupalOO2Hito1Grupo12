package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.UnidadDeVentaDao;
import datos.FoodTruck;
import datos.Personal;
import datos.Plato;
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

	public UnidadDeVenta traer(int id) {
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

	public void eliminar(int id) throws Exception {
		UnidadDeVenta u = dao.traer(id);
		if (u == null) {
			throw new Exception("No existe la unidad de venta con id " + id);
		}
		dao.eliminar(u);
	}


	public void asignarPersonal(String codigo, int idPersonal) throws Exception {
		UnidadDeVenta u = dao.traerPorCodigo(codigo);
		PersonalABM personalAbm = new PersonalABM();
		Personal existente = personalAbm.traer(idPersonal);
		if (u == null) {
			throw new Exception("No existe una unidad de venta con el codigo " + codigo);
		}
		// El alta de Personal es responsabilidad de PersonalABM. Aca solo se
		// asigna uno que ya existe.
		if (existente == null) {
			throw new Exception("No existe el personal con id " + idPersonal);
		}
		dao.asignarPersonal(u.getId(), idPersonal);
	}


	// CASO DE USO: unidades de venta con dotacion de cocina insuficiente.
	// El ABM valida los criterios; el Dao solo los traduce a HQL.
	public List<FoodTruck> traerFoodTrucksConDotacionInsuficiente(boolean requiereElectricidad,
			LocalDate desde, LocalDate hasta, long minimoCocineros) throws Exception {
		if (desde == null || hasta == null) {
			throw new Exception("Hay que indicar el periodo completo: desde y hasta");
		}
		if (desde.isAfter(hasta)) {
			throw new Exception("El periodo esta invertido: 'desde' (" + desde
					+ ") es posterior a 'hasta' (" + hasta + ")");
		}
		if (minimoCocineros < 1) {
			throw new Exception("El minimo de cocineros tiene que ser al menos 1, "
					+ "si no la consulta no puede devolver nada");
		}
		return dao.traerFoodTrucksConDotacionInsuficiente(requiereElectricidad, desde, hasta,
				minimoCocineros);
	}


	// Unico alta valida de un Plato: composicion de UnidadDeVenta
	public int agregarPlato(String codigo, String nombre, double precio, double costo) throws Exception {
		UnidadDeVenta u = dao.traerPorCodigo(codigo);
		if (u == null) {
			throw new Exception("No existe una unidad de venta con el codigo " + codigo);
		}
		PlatoABM platoAbm = new PlatoABM();
		if (platoAbm.traer(nombre, u.getId()) != null) {
			throw new Exception(
					"Ya existe un plato con el nombre " + nombre + " en esta unidad de venta");
		}
		Plato plato = new Plato(nombre, precio, costo);
		return dao.agregarPlato(u.getId(), plato);
	}



}
