package negocio;

import java.util.List;
import dao.UsuarioFinalDao;
import datos.UsuarioFinal;

public class UsuarioFinalABM {
	private UsuarioFinalDao dao = new UsuarioFinalDao();

	public UsuarioFinal traerUsuarioFinal(long idUsuarioFinal) {
		return dao.traer(idUsuarioFinal);
	}

	public UsuarioFinal traerUsuarioFinalPorDni(int dni) {
		return dao.traerPorDni(dni);
	}
	
	public UsuarioFinal traer(long idUsuarioFinal) throws Exception {
	    UsuarioFinal u = dao.traer(idUsuarioFinal);
	    if (u == null) throw new Exception("No existe el usuario con id = " + idUsuarioFinal);
	    return u;
	}


	public List<UsuarioFinal> traer() {
		return dao.traer();
	}

	public List<UsuarioFinal> traerActivos() {
		return dao.traerActivos();
	}

	public long agregar(UsuarioFinal usuario) {
		return dao.agregar(usuario);
	}

	public void modificar(UsuarioFinal usuario) {
		dao.actualizar(usuario);
	}

	public void eliminar(long idUsuarioFinal) {
		UsuarioFinal usuario = dao.traer(idUsuarioFinal);
		if (usuario != null) {
			dao.eliminar(usuario);
		}
	}
	
	public void darDeBaja(long idUsuarioFinal) {
		UsuarioFinal usuario = dao.traer(idUsuarioFinal);
		if (usuario != null && !usuario.isBaja()) {
			usuario.setBaja(true);
			dao.actualizar(usuario);
		}
	}

}
