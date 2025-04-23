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
		return dao.agregar(contacto);
	}

	public void actualizar(Contacto contacto) throws Exception {
		if (dao.traer(contacto.getIdContacto()) == null) {
			throw new Exception("No existe el contacto con id = " + contacto.getIdContacto());
		}
		dao.actualizar(contacto);
	}

	public void eliminar(long idContacto) throws Exception {
		Contacto c = dao.traer(idContacto);
		if (c == null) throw new Exception("No existe el contacto con id = " + idContacto);
		dao.eliminar(c);
	}
}
