package test;

import java.util.List;

import datos.Estado;
import datos.UsuarioFinal;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestTicketPorTitulo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
        UsuarioFinalABM usuarioFinalABM= new UsuarioFinalABM();
		
		System.out.println(usuarioFinalABM.traer());
		
		System.out.println("-----");

		List<UsuarioFinal> listaAux= usuarioFinalABM.traer();
		
		TicketABM ticketABM= new TicketABM();
		
		
		try {
			System.out.println(ticketABM.traer("Problema con Siu Guarani",listaAux.get(0))); 
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
