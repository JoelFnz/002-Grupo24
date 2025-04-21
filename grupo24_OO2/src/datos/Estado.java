package datos;

import java.time.LocalDateTime;

public class Estado {
	private long idEstado;
	private Ticket ticket;
	private TipoDeEstado tipoDeEstado;
	private String descripcionDelEstado;
	private LocalDateTime ultimoCambioEstado;
	
	public Estado() {}

	public Estado(TipoDeEstado tipoDeEstado, String descripcionDelEstado, LocalDateTime ultimoCambioEstado, Ticket ticket) {
		super();
		this.tipoDeEstado = tipoDeEstado;
		this.descripcionDelEstado = descripcionDelEstado;
		this.ultimoCambioEstado = ultimoCambioEstado;
		this.ticket = ticket;
	}

	public long getIdEstado() {
		return idEstado;
	}

	protected void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public TipoDeEstado getTipoDeEstado() {
		return tipoDeEstado;
	}

	public void setTipoDeEstado(TipoDeEstado tipoDeEstado) {
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

	public void setUltimoCambioEstado(LocalDateTime ultimoCambioEstado) {
		this.ultimoCambioEstado = ultimoCambioEstado;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
}
