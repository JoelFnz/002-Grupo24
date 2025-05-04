package datos;

import java.time.LocalDate;
import java.util.Set;

public class Empleado extends Usuario{
	private LocalDate fechaDeIngreso;
	private Set<Ticket> ticketAsignado;
	
	public Empleado() {
		super();
	}

	public Empleado(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento, Contacto contacto,
			LocalDate fechaDeIngreso, String username, String password) {
		super(dni, nombre, apellido, fechaDeNacimiento, contacto, username, password);
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public LocalDate getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	public Set<Ticket> getTicketAsignado() {
		return ticketAsignado;
	}

	public void setTicketAsignado(Set<Ticket> ticketAsignado) {
		this.ticketAsignado = ticketAsignado;
	}

	@Override
	public String toString() {
		return "Empleado [fechaDeIngreso=" + fechaDeIngreso + ", ticketAsignado=" + ticketAsignado + ", idUsuario="
				+ idUsuario + ", dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", fechaDeNacimiento="
				+ fechaDeNacimiento + ", baja=" + baja + ", contacto=" + contacto + "]";
	}
	
}
