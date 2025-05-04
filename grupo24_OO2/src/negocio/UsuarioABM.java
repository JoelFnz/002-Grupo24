package negocio;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

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

	public Usuario traerPorUsername(String username) {
	    return dao.traerPorUsername(username);
	}
	
	public long agregar(Usuario usuario) {
	    // Validar que no exista otro usuario con el mismo DNI
	    Usuario existente = dao.traerPorDni(usuario.getDni());
	    if (existente != null) {
	        throw new RuntimeException("Ya existe un usuario con DNI: " + usuario.getDni());
	    }
	    
	    // Validar que el username no se repita
	    Usuario existenteUsername = dao.traerPorUsername(usuario.getUsername());
	    if (existenteUsername != null) {
	        throw new RuntimeException("Ya existe un usuario con el username: " + usuario.getUsername());
	    }

	    return dao.agregar(usuario);
	}

	public void modificar(Usuario usuario) {
	    // Validar que no exista otro usuario con el mismo DNI
	    Usuario existenteDni = dao.traerPorDni(usuario.getDni());
	    if (existenteDni != null && existenteDni.getIdUsuario() != usuario.getIdUsuario()) {
	        throw new RuntimeException("Ya existe un usuario con DNI: " + usuario.getDni());
	    }

	    // Validar que el username no se repita con otro usuario
	    Usuario existenteUsername = dao.traerPorUsername(usuario.getUsername());
	    if (existenteUsername != null && existenteUsername.getIdUsuario() != usuario.getIdUsuario()) {
	        throw new RuntimeException("Ya existe un usuario con el username: " + usuario.getUsername());
	    }

	    // Si las validaciones pasaron, actualizamos el usuario
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
	
	public List<Usuario> traerPorNombre(String nombre){
		return dao.traerPorNombre(nombre);
	}
	
	public List<Usuario> traerPorApellido(String apellido){
		return dao.traerPorApellido(apellido);
	}

	public List<Usuario> traerActivos() {
		return dao.traerActivos();
	}

	public List<Usuario> traerPorTipo(Class<? extends Usuario> tipoClase) {
		return dao.traerPorTipo(tipoClase);
	}
	
	public List<Usuario> traer(LocalDate fechaDeNacimiento) {
		return dao.traerPorNacimiento(fechaDeNacimiento);
	}
	
	public List<Usuario> traer(LocalDate desde, LocalDate hasta) throws Exception{
		if(hasta.isBefore(desde))
			throw new Exception("Error en capa negocio. La fecha 'desde' debe ser anterior a la fecha 'hasta'");
		return dao.traerPorIntervaloDeNacimiento(desde, hasta);
	}
	
	public Usuario traerPorEmail(String email) throws Exception{
		if(email.isBlank())
			throw new Exception("Error en capa negocio. El email ingresado no puede ser nulo o estar vacio");
		return dao.traerPorEmail(email);
	}
	
	public String iniciarSesion(String email, String contrasenia) throws Exception {
		if(contrasenia.isBlank())
			throw new Exception("Error en capa negocio. La contraseña no puede ser nula o estar vacia");
		
		String retorno;
		Usuario u = traerPorEmail(email);
		
		if(u != null && u.getPassword().equals(contrasenia)) {
			retorno = "Inicio de sesión exitoso";
		} else {
			retorno = "Datos ingresados incorrectos";
		}
		
		return retorno;
	}
}
