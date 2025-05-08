package test;

import java.time.LocalDateTime;
import java.util.List;


import datos.UsuarioFinal;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestTicketPorFechaCreacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
        UsuarioFinalABM usuarioFinalABM= new UsuarioFinalABM();
		
		System.out.println(usuarioFinalABM.traer());
		
		System.out.println("-----");

		List<UsuarioFinal> listaAux= usuarioFinalABM.traer();
		
		TicketABM ticketABM= new TicketABM();
		
		LocalDateTime fecha=LocalDateTime.of(2024, 12, 10, 0, 0);
		try {
			System.out.println(ticketABM.traer(fecha,listaAux.get(0))); 
			System.out.println("-----");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(ticketABM.traerPorUsuarioFinal(listaAux.get(0)));
			System.out.println("-----");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
