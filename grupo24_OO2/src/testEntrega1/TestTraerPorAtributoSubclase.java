package testEntrega1;

import java.time.LocalDate;
import java.util.List;

import negocio.EmpleadoABM;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;


public class TestTraerPorAtributoSubclase {
	
	public static void main(String[] args) {
		EmpleadoABM empleadoAbm = new EmpleadoABM();
		
		//Debe traer al empleado Joel Fernández
		System.out.println(empleadoAbm.traer(LocalDate.of(2010, 10, 8)));
		
		//El objeto traido debe ser nulo
		System.out.println(empleadoAbm.traer(LocalDate.of(2000, 8, 10)));
		
		//Debe traer al empleado Joel Fernández
		System.out.println(empleadoAbm.traerDesdeIngreso(LocalDate.of(2000, 1, 1)));
		
		//Debe traer una lista vacia
		System.out.println(empleadoAbm.traerHastaIngreso(LocalDate.of(2010,1,1)));
	}
}
