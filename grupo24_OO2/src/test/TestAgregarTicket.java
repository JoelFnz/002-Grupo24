package test;

import datos.Ticket;
import datos.UsuarioFinal;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestAgregarTicket {
	public static void main(String[] args) {
		UsuarioFinalABM usuarioAbm = new UsuarioFinalABM();
		TicketABM ticketAbm = new TicketABM();
		UsuarioFinal usuario = usuarioAbm.traer().get(0);
		
		//Se crea
		try {
			ticketAbm.agregar(usuario, "Titulo 1", "Descripcion 1", "Motivo 1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Lanza exception
		try {
			ticketAbm.agregar(usuario, "", "Descripcion 2", "Motivo 2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Lanza exception
		try {
			ticketAbm.agregar(usuario, "Titulo 3", " ", "Motivo 3");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Lanza exception
		try {
			ticketAbm.agregar(usuario, "Titulo 4", "Descripcion 4", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Se crea
		try {
			ticketAbm.agregar(usuario, "Titulo 5", "Descripcion 5", "Motivo 5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Para mostrar los tickets creados
		try {
			for(Ticket t : usuarioAbm.traer(usuario.getIdUsuario()).getLstTickets())
				System.out.println(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Para eliminar los tickets creados
		try {
			for(Ticket t : usuarioAbm.traer(usuario.getIdUsuario()).getLstTickets()) {
				if(t.getIdTicket() != 1) //1 es el id de un ticket ya cargado
					ticketAbm.eliminar(t.getIdTicket());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nTickets después del borrado:\n");
		//Para mostrar los tickets despues del borrado
		try {
			for(Ticket t : usuarioAbm.traer(usuario.getIdUsuario()).getLstTickets())
				System.out.println(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
