package negocio;

import java.util.List;
import dao.TipoDeEstadoDao;
import datos.TipoDeEstado;

public class TipoDeEstadoABM {
	private TipoDeEstadoDao dao = new TipoDeEstadoDao();

	public List<TipoDeEstado> traerTodos() {
		return dao.traerTodos();
	}

	public TipoDeEstado traerPorNombre(String nombre) {
		return dao.traerPorNombre(nombre);
	}

	public TipoDeEstado traerPorIndice(int indice) {
		return dao.traerPorIndice(indice);
	}
}
