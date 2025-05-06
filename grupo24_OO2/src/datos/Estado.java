package datos;

import java.time.LocalDateTime;

public class Estado {
	private long idEstado;
	private Ticket ticket;
	private String tipoDeEstado;
	private String descripcionDelEstado;
	private LocalDateTime ultimoCambioEstado;
	
	public Estado() {}

	public Estado(String tipoDeEstado, String descripcionDelEstado, LocalDateTime ultimoCambioEstado, Ticket ticket) throws Exception {
		super();
		setTipoDeEstado(tipoDeEstado);
		this.descripcionDelEstado = descripcionDelEstado;
		setUltimoCambioEstado(ultimoCambioEstado);
		this.ticket = ticket;
	}

	public long getIdEstado() {
		return idEstado;
	}

	protected void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public String getTipoDeEstado() {
		return tipoDeEstado;
	}

	public void setTipoDeEstado(String tipoDeEstado) throws Exception{
		if(tipoDeEstado.isBlank())
			throw new Exception("Error en capa datos. El tipoDeEstado no puede estar vacío");
		this.tipoDeEstado = tipoDeEstado;
	}

	public String getDescripcionDelEstado() {
		return descripcionDelEstado;
	}

	public void setDescripcionDelEstado(String descripcionDelEstado) {
		this.descripcionDelEstado = descripcionDelEstado;
	}

	public LocalDateTime getUltimoCambioEstado() {
		return ultimoCambioEstado;
	}

	public void setUltimoCambioEstado(LocalDateTime ultimoCambioEstado) throws Exception{
		if(this.ultimoCambioEstado != null && ultimoCambioEstado.isBefore(this.ultimoCambioEstado))
			throw new Exception("Error en capa datos. El nuevo ultimoCambioEstado debe ser posterior al actual");
		this.ultimoCambioEstado = ultimoCambioEstado;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", tipoDeEstado=" + tipoDeEstado + ", descripcionDelEstado="
				+ descripcionDelEstado + ", ultimoCambioEstado=" + ultimoCambioEstado + "]";
	}
	
}
