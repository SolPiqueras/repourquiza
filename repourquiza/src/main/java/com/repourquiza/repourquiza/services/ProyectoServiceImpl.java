package com.repourquiza.repourquiza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.repourquiza.repourquiza.entities.Proyecto;
import com.repourquiza.repourquiza.repositories.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements IProyectoService{

	@Autowired
	private ProyectoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Proyecto> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Proyecto findById(int id_proyecto) {
		return repository.findById(id_proyecto).orElse(null);
	}

	@Override
	@Transactional
	public Proyecto save(Proyecto proyecto) {
		return repository.save(proyecto);
	}

	@Override
	@Transactional
	public void deleteById(int id_proyecto) {
		repository.deleteById(id_proyecto);
	}

}
