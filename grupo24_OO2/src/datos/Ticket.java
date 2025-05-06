package datos;

import java.time.LocalDateTime;
import java.util.Set;

public class Ticket {
	private long idTicket;
	private UsuarioFinal usuario;
	private String titulo;
	private String descripcion;
	private String motivo;
	private LocalDateTime fechaDeCreacion;
	private Estado estado;
	private Set<Empleado> empleadoAsignado;
	
	public Ticket() {}

	public Ticket(UsuarioFinal usuario, String titulo, String descripcion, String motivo, LocalDateTime fechaDeCreacion,
			Estado estado) throws Exception {
		super();
		this.usuario = usuario;
		setTitulo(titulo);
		setDescripcion(descripcion);
		setMotivo(motivo);
		this.fechaDeCreacion = fechaDeCreacion;
		this.estado = estado;
	}

	public long getIdTicket() {
		return idTicket;
	}

	protected void setIdTicket(long idTicket) {
		this.idTicket = idTicket;
	}

	public UsuarioFinal getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioFinal usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws Exception {
		if(titulo.isBlank())
			throw new Exception("Error en capa datos. El título no puede estar vacío");
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) throws Exception{
		if(descripcion.isBlank()) 
			throw new Exception("Error en capa datos. La descripción no puede estar vacía");
		this.descripcion = descripcion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) throws Exception {
		if(motivo.isBlank())
			throw new Exception("Error en capa datos. El motivo no puede estar vacío");
		this.motivo = motivo;
	}

	public LocalDateTime getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Set<Empleado> getEmpleadoAsignado() {
		return empleadoAsignado;
	}

	public void setEmpleadoAsignado(Set<Empleado> empleadoAsignado) {
		this.empleadoAsignado = empleadoAsignado;
	}

	@Override
	public String toString() {
		return "Ticket [idTicket=" + idTicket + ", titulo=" + titulo + ", descripcion=" + descripcion + ", motivo="
				+ motivo + ", fechaDeCreacion=" + fechaDeCreacion + ", estado=" + estado + "]";
	}
	
	
}
