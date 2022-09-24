package com.repourquiza.repourquiza.services;

import java.util.List;

import com.repourquiza.repourquiza.entities.Proyecto;

public interface IProyectoService {
	public List<Proyecto> findAll();
	public Proyecto findById(int id_proyecto);
	public Proyecto save(Proyecto proyecto);
	public void deleteById(int id_proyecto);
}
