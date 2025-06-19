package com.unla.grupo24oo2.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.EmpleadoRegistroDTO;
import com.unla.grupo24oo2.entities.Contacto;
import com.unla.grupo24oo2.entities.Domicilio;
import com.unla.grupo24oo2.entities.Empleado;
import com.unla.grupo24oo2.exceptions.DniEmpleadoDuplicadoException;
import com.unla.grupo24oo2.exceptions.EmailClienteDuplicadoException;
import com.unla.grupo24oo2.exceptions.EmailEmpleadoDuplicadoException;
import com.unla.grupo24oo2.exceptions.NoRecordFoundException;
import com.unla.grupo24oo2.repositories.IClienteRepository;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.services.IEmpleadoService;

import jakarta.transaction.Transactional;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleadoRepository empleadoRepository;
    
    @Autowired
    private IClienteRepository clienteRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;	//<-- Agregado

    public Empleado registrarEmpleado(EmpleadoRegistroDTO dto) {
        if (empleadoRepository.findByDni(dto.getDni()).isPresent()) {
        	throw new DniEmpleadoDuplicadoException("El DNI ya está registrado como empleado.");
        }
        if (empleadoRepository.existsByContactoEmail(dto.getEmail())) {
            throw new EmailEmpleadoDuplicadoException("El email ingresado ya está registrado como empleado.");
        }
        if (clienteRepository.existsByContactoEmail(dto.getEmail())) {
    	    throw new EmailEmpleadoDuplicadoException("El email ingresado ya está registrado como cliente.");
    	}
        
        // Obtener el ultimo número de empleado registrado
        String ultimoNroEmpleado = empleadoRepository.findUltimoNroEmpleado();
        int nuevoNumero = (ultimoNroEmpleado != null) ? Integer.parseInt(ultimoNroEmpleado.substring(3)) + 1 : 1;
        String nroEmpleadoGenerado = String.format("LE-%05d", nuevoNumero);


        // Crear Domicilio
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setLocalidad(dto.getLocalidad());

        // Crear Contacto
        Contacto contacto = new Contacto();
        contacto.setTelefono(dto.getTelefono());
        contacto.setEmail(dto.getEmail());

        // Crear Empleado y asociar domicilio y contacto
        String contraseniaHasheada = passwordEncoder.encode(dto.getContrasenia()); // <-- Agregado
        Empleado nuevo = new Empleado(nroEmpleadoGenerado ,dto.getDni(), dto.getNombre(), contraseniaHasheada, domicilio, contacto);

        domicilio.setUsuario(nuevo);
        contacto.setUsuario(nuevo);

        return empleadoRepository.save(nuevo);
    }

	@Override
	public Empleado traerEmpleadoPorDni(int dni){
		try {
			return empleadoRepository.findByDni(dni).orElseThrow(() -> new NoRecordFoundException("El empleado no existe"));
		} catch (NoRecordFoundException e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	@Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll(); // Recupera todos los empleados desde la base de datos
    }

	@Override
    @Transactional // Para asegurar que la eliminación sea segura
    public void eliminar(Empleado empleado) {
        empleadoRepository.delete(empleado); // Elimina al empleado de la base de datos
    }
	
	// Nuevo metodo para actualizar datos del empleado
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado); // Guarda el empleado actualizado en la base de datos
    }

    @Override
    public Empleado traerEmpleadoPorNro(String nroEmpleado) {
        return empleadoRepository.findByNroEmpleado(nroEmpleado).orElse(null);
    }
	
}