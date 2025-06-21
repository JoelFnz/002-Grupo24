package com.unla.grupo24oo2.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo24oo2.dtos.ClienteRegistroDTO;
import com.unla.grupo24oo2.dtos.EmpleadoRegistroDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

	// aca van los métodos que manejan las excepciones
	@ExceptionHandler(DniClienteDuplicadoException.class)
	public ModelAndView manejarDniClienteDuplicado(DniClienteDuplicadoException ex) {
	    ModelAndView mV = new ModelAndView("registro/registro");
	    mV.addObject("mensajeError", ex.getMessage());
	    mV.addObject("cliente", new ClienteRegistroDTO());
	    return mV;
	}
	
	@ExceptionHandler(EmailClienteDuplicadoException.class)
	public ModelAndView manejarEmailClienteDuplicado(EmailClienteDuplicadoException ex) {
	    ModelAndView mV = new ModelAndView("registro/registro");
	    mV.addObject("mensajeError", ex.getMessage());
	    mV.addObject("cliente", new ClienteRegistroDTO());
	    return mV;
	}
	
	@ExceptionHandler(DniEmpleadoDuplicadoException.class)
	public ModelAndView manejarDniEmpleadoDuplicado(DniEmpleadoDuplicadoException ex) {
	    ModelAndView mV = new ModelAndView("registro/registro_empleado");
	    mV.addObject("mensajeError", ex.getMessage());
	    mV.addObject("empleado", new EmpleadoRegistroDTO());
	    return mV;
	}
	
	@ExceptionHandler(EmailEmpleadoDuplicadoException.class)
	public ModelAndView manejarEmailEmpleadoDuplicado(EmailEmpleadoDuplicadoException ex) {
	    ModelAndView mV = new ModelAndView("registro/registro_empleado");
	    mV.addObject("mensajeError", ex.getMessage());
	    mV.addObject("empleado", new EmpleadoRegistroDTO());
	    return mV;
	}
	
	@ExceptionHandler(ServicioYaAsignadoException.class)
	public ModelAndView manejarServicioYaAsignado(ServicioYaAsignadoException ex) {
	    return new ModelAndView("redirect:/servicio/ofrecer?mensaje=" + ex.getMessage().replace(" ", "%20"));
	}

}