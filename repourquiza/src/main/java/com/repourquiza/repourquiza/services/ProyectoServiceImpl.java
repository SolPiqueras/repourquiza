package com.repourquiza.repourquiza.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.repourquiza.repourquiza.entities.Project;
import com.repourquiza.repourquiza.repositories.ProyectoRepository;

@Service
public class ProyectoServiceImpl implements IProyectoService {

	@Autowired
	private ProyectoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Project> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Project findById(int idProject) {
		return repository.findById(idProject).orElse(null);
	}

	@Override
	@Transactional
	public Project save(Project project) {
		return repository.save(project);
	}

	@Override
	@Transactional
	public void deleteById(int idProject) {
		repository.deleteById(idProject);
	}

	public Project convertToProject(MultipartFile project, String author, String title, String area, String year, String description) {

		Project newProject = new Project();

		try {
			newProject.setArea(area);
			newProject.setAuthor(author);
			newProject.setCreated(LocalDate.now());
			newProject.setDescription(description);
			newProject.setTitle(title);
			newProject.setYear(year);
			byte[] byteProject = project.getBytes();
			String stringProject = new String(byteProject);
			newProject.setProject(stringProject);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newProject;
	}
}
