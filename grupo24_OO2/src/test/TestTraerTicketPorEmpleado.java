package test;

import java.util.List;

import datos.Ticket;
import negocio.EmpleadoABM;

public class TestTraerTicketPorEmpleado {
	
	public static void main(String[] args) {
	    EmpleadoABM empleadoAbm = new EmpleadoABM();

	    try {
	        long idEmpleado = 1;
	        List<Ticket> tickets = empleadoAbm.traerTicketsPorEmpleado(idEmpleado);

	        System.out.println("Tickets asociados al empleado con ID " + idEmpleado + ":");
	        for (Ticket t : tickets) {
	            System.out.println(t);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}