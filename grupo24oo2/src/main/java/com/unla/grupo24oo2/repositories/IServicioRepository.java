package com.unla.grupo24oo2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.grupo24oo2.entities.Servicio;

public interface IServicioRepository extends JpaRepository<Servicio, Long>{
	Optional<Servicio> findByNombreServicio(String nombreServicio);
	List<Servicio> findAll();
}
