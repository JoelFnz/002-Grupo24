package negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import dao.TicketDao;
import datos.Ticket;
import datos.Usuario;
import datos.UsuarioFinal;
import datos.Estado;
import negocio.EstadoABM;

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
	
	public long agregar(UsuarioFinal usuario, String titulo, String descripcion, String motivo) throws Exception {
		Ticket aux = new Ticket(usuario, titulo , descripcion, motivo, LocalDateTime.now(), null);
		aux.setEstado(new Estado(
				"enviado", 
				"Tu ticket ha sido enviado. Pronto un empleado se encargará de tu ticket.",
				LocalDateTime.now(),
				aux)
				);
		return dao.traer((new EstadoABM()).agregar(aux.getEstado())).getIdTicket();
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
	
	public List<Ticket> traerPorUsuarioYDesdeCreacion(UsuarioFinal usuarioFinal, LocalDateTime desdeCreacion){
		return dao.traerDesde(usuarioFinal, desdeCreacion);
	}
	
	public List<Ticket> traerPorUsuarioYHastaCreacion(UsuarioFinal usuarioFinal, LocalDateTime hastaCreacion){
		return dao.traerHasta(usuarioFinal, hastaCreacion);
	}
	
	
	//to do 
	public List<Ticket> traerPorUsuarioFinal(UsuarioFinal usuarioFinal)throws Exception{////
			
		return dao.traerPorUsuarioFinal(usuarioFinal);
			
	}
	
	
	
}
