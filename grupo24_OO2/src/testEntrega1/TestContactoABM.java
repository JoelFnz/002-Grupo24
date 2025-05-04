package testEntrega1;

import java.time.LocalDate;
import negocio.ContactoABM;
import datos.Contacto;
import datos.UsuarioFinal;

public class TestContactoABM {
    public static void main(String[] args) {
        try {
            ContactoABM abm = new ContactoABM();

            // Crear un usuario de prueba
            UsuarioFinal usuario = new UsuarioFinal(12345678, "Juan", "Pérez", LocalDate.of(1990, 5, 15), null, "juanUser", "1234");

            // Crear contacto y asociarlo al usuario
            Contacto contacto = new Contacto("juan@example.com", "123456789", usuario);
            usuario.setContacto(contacto); // si el mapeo es bidireccional

            // Agregar el contacto
            long idContacto = abm.agregar(contacto);
            System.out.println("Contacto agregado con id: " + idContacto);

            // Traer el contacto
            Contacto c = abm.traer(idContacto);
            System.out.println("Contacto traído: " + c);

            // Eliminar el contacto
            abm.eliminar(idContacto);
            System.out.println("Contacto eliminado con id: " + idContacto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
