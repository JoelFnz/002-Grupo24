package test;

import java.util.Scanner;

import datos.UsuarioFinal;
import negocio.UsuarioABM;

public class TestModificarUsuarioFinalRegistrado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioABM abm = new UsuarioABM();

        // Solicitar el username del usuario a modificar
        System.out.println("==> Modificar usuario existente:");

        System.out.print("Ingrese el username del usuario a modificar: ");
        String username = scanner.nextLine();

        // Buscar el usuario por username
        UsuarioFinal usuarioExistente = (UsuarioFinal) abm.traerPorUsername(username);
        if (usuarioExistente != null) {
            // Mostrar los datos actuales del usuario
            System.out.println("\nUsuario actual:");
            System.out.println(usuarioExistente);

            // Solicitar los nuevos datos (Solo Apellido para probar)
            System.out.print("\nNuevo apellido: ");
            String nuevoApellido = scanner.nextLine();

            // Modificar los datos
            usuarioExistente.setApellido(nuevoApellido);

            // Actualizar el usuario
            abm.modificar(usuarioExistente);

            // Mostrar el usuario actualizado
            System.out.println("\nUsuario modificado:");
            System.out.println(abm.traerPorUsername(username));
        } else {
            System.out.println("Usuario con el username '" + username + "' no encontrado.");
        }

        scanner.close();
    }
}
