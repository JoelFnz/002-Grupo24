package datos;

import java.time.LocalDate;
import java.util.Set;

public class UsuarioFinal extends Usuario{
	private Set<Ticket> lstTickets;

	public UsuarioFinal() {
		super();
	}

	public UsuarioFinal(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, 
			Contacto contacto, String username, String password) {
		super(dni, nombre, apellido, fechaDeNacimiento, contacto, username, password);
	}


	public Set<Ticket> getLstTickets() {
		return lstTickets;
	}

	public void setLstTickets(Set<Ticket> lstTickets) {
		this.lstTickets = lstTickets;
	}

	@Override
	public String toString() {
	    return "UsuarioFinal [idUsuario=" + idUsuario + ", dni=" + dni + ", nombre=" + nombre +
	           ", apellido=" + apellido + ", username=" + getUsername() + "]";
	}

	
}
