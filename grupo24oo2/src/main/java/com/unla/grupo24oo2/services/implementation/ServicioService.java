package com.unla.grupo24oo2.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo24oo2.dtos.ServicioDTO;
import com.unla.grupo24oo2.repositories.IServicioRepository;
import com.unla.grupo24oo2.services.IServicioService;

@Service
public class ServicioService implements IServicioService{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	IServicioRepository servicioRepository;
	
	@Override
	public List<ServicioDTO> traerTodosLosServicios() {
		return servicioRepository.findAll()
	            .stream()
	            .map(entity -> modelMapper.map(entity, ServicioDTO.class))
	            .collect(Collectors.toList());
	}

}
