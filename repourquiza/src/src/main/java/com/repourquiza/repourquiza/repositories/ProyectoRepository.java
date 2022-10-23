package com.repourquiza.repourquiza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repourquiza.repourquiza.entities.Project;


public interface ProyectoRepository extends JpaRepository<Project, Integer> {
	
}
