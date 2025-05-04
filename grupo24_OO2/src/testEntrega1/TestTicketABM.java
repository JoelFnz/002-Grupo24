package testEntrega1;

import java.time.LocalDateTime;
import java.util.List;

import datos.Estado;
import datos.Ticket;
import datos.UsuarioFinal;
import negocio.EstadoABM;
import negocio.TicketABM;
import negocio.UsuarioFinalABM;

public class TestTicketABM {
    public static void main(String[] args) {
        try {
            TicketABM abm = new TicketABM();
            UsuarioFinalABM usuarioABM = new UsuarioFinalABM();
            EstadoABM estadoABM = new EstadoABM();

            // Suponiendo que ya existe un usuario con ID 2 y un estado con ID 2
            UsuarioFinal usuario = usuarioABM.traer(2);
            Estado estado = estadoABM.traer(1);

            // Crear nuevo ticket
            Ticket ticket = new Ticket(
                usuario,
                "Problema con login",
                "No puedo ingresar a la cuenta desde ayer",
                "Acceso",
                LocalDateTime.now(),
                estado
            );

            long idTicket = abm.agregar(ticket);
            System.out.println("Ticket creado con ID: " + idTicket);

            // Traer y mostrar el ticket
            Ticket traido = abm.traer(idTicket);
            System.out.println("Ticket traído: " + traido);

            // Modificar descripción
            traido.setDescripcion("El problema persiste luego de reiniciar el sistema.");
            abm.actualizar(traido);
            System.out.println("Ticket actualizado: " + abm.traer(idTicket));

            // Listar todos los tickets
            List<Ticket> lista = abm.traer();
            System.out.println("Tickets en el sistema:");
            for (Ticket t : lista) {
                System.out.println(t);
            }

            // Eliminar el ticket
            abm.eliminar(idTicket);
            System.out.println("Ticket eliminado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
