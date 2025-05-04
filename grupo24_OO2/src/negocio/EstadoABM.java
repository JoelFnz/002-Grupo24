package negocio;

import java.util.List;
import dao.EstadoDao;
import datos.Estado;

public class EstadoABM {
	private EstadoDao dao = new EstadoDao();

	public Estado traer(long idEstado) throws Exception {
		Estado e = dao.traer(idEstado);
		if (e == null) throw new Exception("No existe el estado con id = " + idEstado);
		return e;
	}

	public List<Estado> traer() {
		return dao.traer();
	}

	public long agregar(Estado estado) {
		return dao.agregar(estado);
	}

	public void actualizar(Estado estado) throws Exception {
		if (dao.traer(estado.getIdEstado()) == null) {
			throw new Exception("No existe el estado con id = " + estado.getIdEstado());
		}
		dao.actualizar(estado);
	}

	public void eliminar(long idEstado) throws Exception {
		Estado e = dao.traer(idEstado);
		if (e == null) throw new Exception("No existe el estado con id = " + idEstado);
		dao.eliminar(e);
	}
	
	public void cambiarEstado(long idTicket, String tipoDeEstado, String descripcionDelEstado) {
	    dao.cambiarEstado(idTicket, tipoDeEstado, descripcionDelEstado);
	}
}
