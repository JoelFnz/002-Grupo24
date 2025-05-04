package negocio;

import java.time.LocalDate;
import java.util.List;
import dao.EmpleadoDao;
import datos.Contacto;
import datos.Empleado;
import datos.Ticket;

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
    
    public List<Empleado> traer(LocalDate fechaDeIngreso){
    	return dao.traerPorFechaDeIngreso(fechaDeIngreso);
    }
    
    public List<Empleado> traerPorIntervaloDeIngreso(LocalDate desde, LocalDate hasta)
    		throws Exception{
    	if(hasta.isBefore(desde))
    		throw new Exception("Error en capa negocio. La fecha 'desde' debe ser anterior a la fecha 'hasta'");
    	return dao.traerPorIntervaloDeIngreso(desde, hasta);
    }

    public long agregarEmpleado(int dni, String nombre, String apellido, LocalDate fechaDeNacimiento,
            Contacto contacto, LocalDate fechaDeIngreso, String username, String password) {

        Empleado existente = dao.traerPorDni(dni);
        if (existente != null) {
            throw new RuntimeException("Ya existe un empleado con DNI " + dni);
        }

        Empleado nuevo = new Empleado(dni, nombre, apellido, fechaDeNacimiento, contacto, fechaDeIngreso, username, password);
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
    
    public List<Empleado> traerPorTicketAsociado(Ticket ticketAsociado){
    	return dao.traerPorTicketAsociado(ticketAsociado);
    }
    
    public List<Empleado> traerDesdeIngreso(LocalDate desde){
    	return dao.traerDesdeFechaDeIngreso(desde);
    }
    
    public List<Empleado> traerHastaIngreso(LocalDate hasta){
    	return dao.traerhastaFechaDeIngreso(hasta);
    }
    
    public List<Ticket> traerTicketsPorEmpleado(long idEmpleado) {
        return dao.traerTicketsPorEmpleado(idEmpleado);
    }
}
