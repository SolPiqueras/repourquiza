package com.repourquiza.repourquiza.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.repourquiza.repourquiza.entities.Project;

public interface IProyectoService {
	public List<Project> findAll();
	public Project findById(int idProject);
	public Project save(Project project);
	public void deleteById(int idProject);
	}
