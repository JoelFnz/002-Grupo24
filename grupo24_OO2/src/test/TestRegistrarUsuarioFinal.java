package test;

import java.time.LocalDate;

import datos.Contacto;
import datos.UsuarioFinal;
import negocio.ContactoABM;
import negocio.UsuarioABM;

public class TestRegistrarUsuarioFinal {
    public static void main(String[] args) {
        UsuarioABM usuarioABM = new UsuarioABM();
        ContactoABM contactoABM = new ContactoABM();

        System.out.println("Registrar usuario con email:");
        UsuarioFinal usuario = new UsuarioFinal(55558888, "María", "González", LocalDate.of(1992, 3, 15), null, "Maria1992", "MariaClave");

        Contacto contacto = new Contacto("maria@gmail.com", "999999999", usuario);
        usuario.setContacto(contacto);

        try {
            // VALIDAR primero sin insertar
            if (usuarioABM.traerPorDni(usuario.getDni()) != null)
                throw new RuntimeException("DNI ya registrado");

            if (usuarioABM.traerPorUsername(usuario.getUsername()) != null)
                throw new RuntimeException("Username ya registrado");

            if (contactoABM.traerPorEmail(contacto.getEmail()) != null)
                throw new RuntimeException("Email ya registrado");

            // Si pasó todas las validaciones, recién insertamos
            long idUsuario = usuarioABM.agregar(usuario);
            long idContacto = contactoABM.agregar(contacto);

            System.out.println("Usuario y contacto registrados correctamente:");
            System.out.println("Usuario ID: " + idUsuario);
            System.out.println("Contacto ID: " + idContacto);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
