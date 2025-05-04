package test;

import negocio.UsuarioABM;

public class TestIniciarSesion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioABM abm = new UsuarioABM();
		
			try {
				System.out.println(abm.iniciarSesion("joelfernandez.relg@gmail.com", "12345"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println(abm.iniciarSesion("", "joel"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println(abm.iniciarSesion("joelfernandez.relg@gmail.com", "contraseniaIncorrecta"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println(abm.iniciarSesion("joelfernandez.relg@gmail.com", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

}
