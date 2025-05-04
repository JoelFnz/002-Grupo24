package test;

import negocio.EstadoABM;

public class TestCambiarEstado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EstadoABM estadoABM = new EstadoABM();
	    try {
	        estadoABM.cambiarEstado(1, "finalizado", "Ticket finalizado por el área técnica");
	        System.out.println("Estado actualizado con éxito.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	}

}
