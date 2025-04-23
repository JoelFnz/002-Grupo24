package test;

import java.util.List;
import negocio.*;

public class TestTraerTodos {
	//Solo para legibilidad de los testeos
	private static <T> void imprimirLista(List<T> lista, String tabla) {
		System.out.println(tabla + "[");
		for(T elemento: lista) {
			System.out.println(elemento);
		}
		System.out.println("]\n");
	}
		
	public static void main(String[] args) {
		UsuarioABM usuarioABM = new UsuarioABM();
		TicketABM ticketABM = new TicketABM();
		ContactoABM contactoABM = new ContactoABM();
		EstadoABM estadoABM = new EstadoABM();
		EmpleadoABM empleadoABM = new EmpleadoABM();
		UsuarioFinalABM uFinalABM = new UsuarioFinalABM();
		
		imprimirLista(usuarioABM.traer(), "Usuarios");
		imprimirLista(ticketABM.traer(), "Tickets");
		imprimirLista(uFinalABM.traer(), "Usuarios Finales");
		imprimirLista(contactoABM.traer(), "Contactos");
		imprimirLista(empleadoABM.traerTodos(), "Empleados");
		imprimirLista(estadoABM.traer(), "Estados");
	}

}
