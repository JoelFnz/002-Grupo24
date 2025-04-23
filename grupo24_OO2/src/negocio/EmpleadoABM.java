package negocio;

import java.time.LocalDate;
import java.util.List;
import dao.EmpleadoDao;
import datos.Contacto;
import datos.Empleado;

public class EmpleadoABM {
    private EmpleadoDao dao = new EmpleadoDao();

    public Empleado traerEmpleado(long id) {
        return dao.traer(id);
    }

    public Empleado traerEmpleadoPorDni(int dni) {
        return dao.traerPorDni(dni);
    }

    public List<Empleado> traerTodos() {
        return dao.traer();
    }

    public long agregarEmpleado(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento,
            Contacto contacto, LocalDate fechaDeIngreso) {
        
        Empleado existente = dao.traerPorDni(dni);
        if (existente != null) {
            throw new RuntimeException("Ya existe un empleado con DNI " + dni);
        }

        Empleado nuevo = new Empleado(dni, nombre, apellido, fechaDeNacimiento, contacto, fechaDeIngreso);
        return dao.agregar(nuevo);
    }

    public void modificarEmpleado(Empleado e) {
        dao.actualizar(e);
    }

    public void eliminarEmpleado(long id) {
        Empleado e = dao.traer(id);
        if (e != null) {
            dao.eliminar(e);
        } else {
            throw new RuntimeException("No existe empleado con id " + id);
        }
    }
}
