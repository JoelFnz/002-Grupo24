package test;

import java.util.List;

import datos.UsuarioFinal;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestTraerPorUsuarioFinal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
        UsuarioFinalABM usuarioFinalABM= new UsuarioFinalABM();
		
		usuarioFinalABM.traer();
		
		List<UsuarioFinal> listaAux= usuarioFinalABM.traer();
		
		TicketABM ticketABM= new TicketABM();
		
		try {
			ticketABM.traerPorUsuarioFinal(listaAux.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(ticketABM.traerPorUsuarioFinal(listaAux.get(0)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
