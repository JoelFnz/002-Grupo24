package testEntrega1;

import java.time.LocalDate;
import java.util.List;

import negocio.EmpleadoABM;
import negocio.UsuarioABM;

public class TestTraerPorIntervaloDeFecha {
	
	//Esto es solo para legibilidad de los testeos
	private static <T> void imprimirLista(List<T> lista) {
		System.out.println("Lista[");
		for(T elemento: lista)
			System.out.println(elemento);
		System.out.println("]\n");
	}
	
	//Acá comienzan los testeos
	public static void main(String[] args) {
		UsuarioABM usuarioAbm = new UsuarioABM();
		EmpleadoABM empleadoAbm = new EmpleadoABM();
		
		//Debe traer los registros usuarioFinal Brisa Aguilera y empleado Joel Fernandez
		try {
			imprimirLista(usuarioAbm.traer(LocalDate.of(2000, 1, 1), LocalDate.of(2002, 5, 1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer los registros usuarioFinal Diego Aguirre y empleado Joel Fernández
		try {
			imprimirLista(usuarioAbm.traer(LocalDate.of(1999, 1, 1), LocalDate.of(2001, 10, 1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe lanzar excepción. Si no ocurre es porque se agregaron nuevos registros
		try {
			imprimirLista(usuarioAbm.traer(LocalDate.of(1999, 1, 1), LocalDate.of(1998,1,1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer una lista vacía
		try {
			imprimirLista(usuarioAbm.traer(LocalDate.of(1970, 1, 1), LocalDate.of(1971,1,1)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer al empleado Joel Fernandez
		try {
			imprimirLista(empleadoAbm.traerPorIntervaloDeIngreso(LocalDate.of(1990, 1, 1), LocalDate.of(2025, 1, 1)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Debe lanzar Excepción
		try {
			imprimirLista(empleadoAbm.traerPorIntervaloDeIngreso(LocalDate.of(2026, 1, 1), LocalDate.of(2025, 1, 1)));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//Debe traer una lista vacía
		try {
			imprimirLista(empleadoAbm.traerPorIntervaloDeIngreso(LocalDate.of(1970, 1, 1), LocalDate.of(1971, 1, 1)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
