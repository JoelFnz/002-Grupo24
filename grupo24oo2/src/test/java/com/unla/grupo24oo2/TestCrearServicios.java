package com.unla.grupo24oo2;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.unla.grupo24oo2.entities.Servicio;
import com.unla.grupo24oo2.repositories.IServicioRepository;



import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestCrearServicios {

	@Autowired
    private IServicioRepository servicioRepository;

	@BeforeEach
	public void setUp() {
		//Registros hardcodeados.
		
		if(servicioRepository.findByNombreServicio("Servicio técnico").isEmpty()) {
			Servicio s1 = new Servicio();
			s1.setNombreServicio("Servicio técnico");
	    	s1.setDescripcion("Servicio de reparación y mantenimiento para computadoras y servidores");
	    	servicioRepository.save(s1);
    	}
    	
    	if(servicioRepository.findByNombreServicio("Asistencia ténica").isEmpty()) {
    		Servicio s2 = new Servicio();
    		s2.setNombreServicio("Asistencia técnica");
        	s2.setDescripcion("Servicio para ayuda al usuario");
        	servicioRepository.save(s2);
    	}
 
	}
	
	@Test
    public void testFindAllServicios() {
        List<Servicio> servicios = servicioRepository.findAll();
        assertThat(servicios).hasSize(2);
    }
}
