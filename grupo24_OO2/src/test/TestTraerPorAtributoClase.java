package test;

import java.util.List;

import negocio.EmpleadoABM;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestTraerPorAtributoClase {
	
	//Solo para legibilidad de los testeos
	private static <T> void imprimirLista(List<T> lista) {
		System.out.println("Lista[");
		for(T elemento: lista) {
			System.out.println(elemento);
		}
		System.out.println("]\n");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmpleadoABM empleadoAbm = new EmpleadoABM();
		UsuarioFinalABM uFinalAbm = new UsuarioFinalABM();
		TicketABM ticketAbm = new TicketABM();
		
		//Debe traer al empleado Joel Fernandez
		try {
			imprimirLista(empleadoAbm.traerPorTicketAsociado(ticketAbm.traer(1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe ser una lista vacia
		try {
			imprimirLista(empleadoAbm.traerPorTicketAsociado(ticketAbm.traer(3)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer al usuarioFinal Diego Aguirre
		try {
			System.out.println(uFinalAbm.traer(ticketAbm.traer(1)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer al usuarioFinal Brisa Aguilera
		try {
			System.out.println(uFinalAbm.traer(ticketAbm.traer(2)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
