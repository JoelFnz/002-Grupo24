package testEntrega1;

import java.time.LocalDate;

import datos.Contacto;
import datos.UsuarioFinal;
import negocio.UsuarioFinalABM;

public class TestUsuarioFinalABM {
    public static void main(String[] args) {
        UsuarioFinalABM abm = new UsuarioFinalABM();

        // Traer un usuario por ID (verificá que ese ID exista en la base)
        System.out.println("==> Traer por ID:");
        UsuarioFinal u = abm.traerUsuarioFinal(2L);
        if (u != null) {
            System.out.println(u);
        } else {
            System.out.println("Usuario no encontrado.");
        }

        // Crear un contacto para el nuevo usuario
        Contacto contacto = new Contacto("lucas@gmail.com", "1134567890", u);

        // Agregar un nuevo usuario
        System.out.println("\n==> Agregar nuevo usuario:");
        UsuarioFinal nuevo = new UsuarioFinal(12345678, "Lucas", "Perez", LocalDate.of(2000, 1, 1), contacto, "lucasUser", "6789");
        long idNuevo = abm.agregar(nuevo);
        System.out.println("ID generado: " + idNuevo);

        // Modificar usuario
        System.out.println("\n==> Modificar usuario:");
        UsuarioFinal mod = abm.traerUsuarioFinal(idNuevo);
        if (mod != null) {
            mod.setApellido("Gonzalez");
            abm.modificar(mod);
            System.out.println("Usuario modificado: " + abm.traerUsuarioFinal(idNuevo));
        }

        // Dar de baja usuario
        System.out.println("\n==> Dar de baja:");
        abm.darDeBaja(idNuevo);
        System.out.println("Usuario luego de dar de baja: " + abm.traerUsuarioFinal(idNuevo));

        // Eliminar usuario
        System.out.println("\n==> Eliminar:");
        abm.eliminar(idNuevo);
        System.out.println("¿Existe luego de eliminar? " + abm.traerUsuarioFinal(idNuevo));
    }
}

