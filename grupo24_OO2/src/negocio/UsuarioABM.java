package negocio;

import java.util.List;
import dao.UsuarioDao;
import datos.Usuario;

public class UsuarioABM {
	private UsuarioDao dao = new UsuarioDao();

	public Usuario traer(long idUsuario) {
		return dao.traer(idUsuario);
	}

	public Usuario traerPorDni(int dni) {
		return dao.traerPorDni(dni);
	}

	public long agregar(Usuario usuario) {
		// Validar que no exista otro usuario con el mismo DNI
		Usuario existente = dao.traerPorDni(usuario.getDni());
		if (existente != null) {
			throw new RuntimeException("Ya existe un usuario con DNI: " + usuario.getDni());
		}
		return dao.agregar(usuario);
	}

	public void modificar(Usuario usuario) {
		// Validar que no se repita el DNI con otro usuario
		Usuario existente = dao.traerPorDni(usuario.getDni());
		if (existente != null && existente.getIdUsuario() != usuario.getIdUsuario()) {
			throw new RuntimeException("Ya existe un usuario con DNI: " + usuario.getDni());
		}
		dao.actualizar(usuario);
	}

	public void eliminar(long idUsuario) {
		Usuario usuario = dao.traer(idUsuario);
		if (usuario == null) {
			throw new RuntimeException("No existe un usuario con ID: " + idUsuario);
		}
		dao.eliminar(usuario);
	}

	public List<Usuario> traer() {
		return dao.traer();
	}

	public List<Usuario> traerActivos() {
		return dao.traerActivos();
	}

	public List<Usuario> traerPorTipo(Class<? extends Usuario> tipoClase) {
		return dao.traerPorTipo(tipoClase);
	}
}
