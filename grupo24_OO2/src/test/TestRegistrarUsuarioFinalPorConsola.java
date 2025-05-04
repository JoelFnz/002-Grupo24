package test;

import java.time.LocalDate;
import java.util.Scanner;

import datos.Contacto;
import datos.UsuarioFinal;
import negocio.UsuarioABM;

public class TestRegistrarUsuarioFinalPorConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioABM abm = new UsuarioABM();

        // Solicitar los datos del usuario
        System.out.println("==> Ingresar datos del nuevo usuario:");

        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Fecha de nacimiento (yyyy-mm-dd): ");
        String fechaNacimiento = scanner.nextLine();
        LocalDate fechaDeNacimiento = LocalDate.parse(fechaNacimiento);

        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        // Crear contacto
        Contacto contacto = new Contacto(correo, telefono, null);

        // Crear usuario
        UsuarioFinal nuevoUsuario = new UsuarioFinal(dni, nombre, apellido, fechaDeNacimiento, contacto, username, password);

        try {
            // Intentar agregar el usuario
            long idNuevoUsuario = abm.agregar(nuevoUsuario);
            System.out.println("Usuario registrado con ID: " + idNuevoUsuario);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
