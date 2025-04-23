package test;

import java.time.LocalDate;

import datos.Contacto;
import datos.Empleado;
import negocio.EmpleadoABM;

public class TestEmpleadoABM {
    public static void main(String[] args) {
        EmpleadoABM abm = new EmpleadoABM();

        // Paso 1: Crear el empleado SIN contacto
        Empleado empleado = new Empleado(
                12345678,
                "Juan",
                "Pérez",
                LocalDate.of(1990, 5, 15),
                null,
                LocalDate.of(2020, 1, 10)
        );

        // Paso 2: Crear el contacto con referencia al empleado
        Contacto contacto = new Contacto("correo@ejemplo.com", "1112345678", empleado);
        empleado.setContacto(contacto);

        // Paso 3: Guardar el empleado
        long idEmpleado = abm.agregarEmpleado(
                empleado.getDni(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getFechaDeNacimiento(),
                empleado.getContacto(),
                empleado.getFechaDeIngreso()
        );
        System.out.println("Empleado agregado con ID: " + idEmpleado);

        // Traer por ID
        Empleado empTraido = abm.traerEmpleado(idEmpleado);
        System.out.println("Empleado traído: " + empTraido);

        // Modificar apellido
        empTraido.setApellido("Gómez");
        abm.modificarEmpleado(empTraido);
        System.out.println("Empleado modificado: " + abm.traerEmpleado(idEmpleado));

        // Eliminar empleado
        abm.eliminarEmpleado(idEmpleado);
        System.out.println("Empleado eliminado.");

        // Verificar que fue eliminado
        Empleado eliminado = abm.traerEmpleado(idEmpleado);
        System.out.println("¿Empleado después de eliminar? " + eliminado);
    }
}
