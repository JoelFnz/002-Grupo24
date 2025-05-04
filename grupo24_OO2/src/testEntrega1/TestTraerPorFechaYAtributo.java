package testEntrega1;

import java.time.LocalDateTime;
import java.util.List;

import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestTraerPorFechaYAtributo {
	
	//Esto es solo para legibilidad de los testeos
	private static <T> void imprimirLista(List<T> lista) {
		System.out.println("Lista[");
		for(T elemento: lista)
			System.out.println(elemento);
		System.out.println("]\n");
	}
		
	
	public static void main(String[] args) {
		TicketABM ticketAbm = new TicketABM();
		UsuarioFinalABM uFinalAbm = new UsuarioFinalABM();
		
		//Debe traer los tickets con id 1 y 3
		try {
			imprimirLista(ticketAbm.traerPorUsuarioYDesdeCreacion(uFinalAbm.traer(2), LocalDateTime.of(2020, 1, 1, 12, 00)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer una lista vacia
		try {
			imprimirLista(ticketAbm.traerPorUsuarioYDesdeCreacion(uFinalAbm.traer(2),LocalDateTime.of(2025,12,1,12,00)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer el ticket con id 1
		try {
			imprimirLista(ticketAbm.traerPorUsuarioYHastaCreacion(uFinalAbm.traer(2), LocalDateTime.of(2025, 1, 1, 12, 00)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer una lista vacia
		try {
			imprimirLista(ticketAbm.traerPorUsuarioYHastaCreacion(uFinalAbm.traer(3), LocalDateTime.of(1999, 1, 1, 12, 00)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
