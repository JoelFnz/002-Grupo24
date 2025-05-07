package negocio;

import java.util.List;
import dao.ContactoDao;
import datos.Contacto;

public class ContactoABM {
	private ContactoDao dao = new ContactoDao();

	public Contacto traer(long idContacto) throws Exception {
		Contacto c = dao.traer(idContacto);
		if (c == null) throw new Exception("No existe el contacto con id = " + idContacto);
		return c;
	}

	public List<Contacto> traer() {
		return dao.traer();
	}

	public long agregar(Contacto contacto) {
		if (contacto == null || contacto.getEmail() == null || contacto.getEmail().isBlank()) {
			throw new RuntimeException("El contacto o el email no pueden ser nulos o vacíos");
		}
		if (dao.traerPorEmail(contacto.getEmail()) != null) {
			throw new RuntimeException("Ya existe un contacto con ese email");
		}
		return dao.agregar(contacto);
	}


	public void actualizar(Contacto contacto) throws Exception {
		if (contacto == null) throw new Exception("El contacto no puede ser null");

		Contacto existente = dao.traer(contacto.getIdContacto());
		if (existente == null) {
			throw new Exception("No existe el contacto con id = " + contacto.getIdContacto());
		}

		// Validar que no exista otro contacto con el mismo email
		Contacto contactoConMismoEmail = dao.traerPorEmail(contacto.getEmail());
		if (contactoConMismoEmail != null && contactoConMismoEmail.getIdContacto() != contacto.getIdContacto()) {
			throw new Exception("Ya existe otro contacto con el mismo email");
		}

		dao.actualizar(contacto);
	}

	public void eliminar(long idContacto) throws Exception {
		Contacto c = dao.traer(idContacto);
		if (c == null) throw new Exception("No existe el contacto con id = " + idContacto);
		dao.eliminar(c);
	}
	
	public Contacto traerPorEmail(String email) {
		return dao.traerPorEmail(email);
	}
	
	
}
