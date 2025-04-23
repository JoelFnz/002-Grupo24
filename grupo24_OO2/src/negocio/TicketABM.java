package negocio;

import java.util.List;
import dao.TicketDao;
import datos.Ticket;

public class TicketABM {
	private TicketDao dao = new TicketDao();

	public Ticket traer(long idTicket) throws Exception {
		Ticket t = dao.traer(idTicket);
		if (t == null) throw new Exception("No existe el ticket con id = " + idTicket);
		return t;
	}

	public List<Ticket> traer() {
		return dao.traer();
	}

	public long agregar(Ticket ticket) {
		return dao.agregar(ticket);
	}

	public void actualizar(Ticket ticket) throws Exception {
		if (dao.traer(ticket.getIdTicket()) == null) {
			throw new Exception("No existe el ticket con id = " + ticket.getIdTicket());
		}
		dao.actualizar(ticket);
	}

	public void eliminar(long idTicket) throws Exception {
		Ticket t = dao.traer(idTicket);
		if (t == null) throw new Exception("No existe el ticket con id = " + idTicket);
		dao.eliminar(t);
	}
}
