package com.unla.grupo24oo2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.unla.grupo24oo2.entities.Servicio;
import com.unla.grupo24oo2.entities.Ticket;
import com.unla.grupo24oo2.repositories.IEmpleadoRepository;
import com.unla.grupo24oo2.repositories.IServicioRepository;
import com.unla.grupo24oo2.repositories.ITicketRepository;

import com.unla.grupo24oo2.entities.Ticket;

@SpringBootTest
public class TestAsociarTicketConEmpleado {

		@Autowired
		private ITicketRepository tr;
		@Autowired
		private IEmpleadoRepository er;
	
		@BeforeEach
		public void setUp() {
			List<Ticket> tickets = tr.findAll();
			for(Ticket t : tickets){
				t.getEmpleadosAsignados().add(er.findByNroEmpleado("1").get());
				tr.save(t);
			}
		}
		
		@Test
	    public void test() {
	        
	    }
}
