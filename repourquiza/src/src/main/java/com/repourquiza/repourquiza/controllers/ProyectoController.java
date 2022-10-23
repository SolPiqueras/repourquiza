package com.repourquiza.repourquiza.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.repourquiza.repourquiza.entities.Project;
import com.repourquiza.repourquiza.services.IProyectoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/repoUrquiza")
public class ProyectoController {

	@Autowired
	private IProyectoService service;
	

	@GetMapping("/proyectos")
	public List<Project> getProyectos() {
		return service.findAll();
	}

	@GetMapping("/proyectos/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {

		Project project = null;
		Map<String, Object> response = new HashMap<>();

		try {
			project = service.findById(id);
		} catch (Exception e) {
			response.put("Mensaje", "Error al obtener el proyecto.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (project == null) {
			response.put("Mensaje", "Project no encontrado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}

	@PostMapping("/proyectos")
	public ResponseEntity<?> saveProyecto(@RequestParam("project") MultipartFile project, 
			@RequestParam("author") String author,
			@RequestParam("title") String title, @RequestParam("area") String area, 
			@RequestParam("year") String year, @RequestParam("description") String description) {
		Project newProject = null;
		Map<String, Object> response = new HashMap<>();
		try {
			newProject = service.save(service.convertToProject(project, author, title, area, year, description));
		}catch(Exception e) {			
			response.put("Mensaje", "Error al guardar el proyecto.");
			System.out.println(e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "Project guardado con éxito");
		response.put("Project", newProject);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/proyectos/{id}")
	public ResponseEntity<?> deleteProyecto(@PathVariable int id){
		Map<String, Object> response = new HashMap<>();
		try {
			service.deleteById(id);
		}catch(Exception e) {
			response.put("Mensaje", "Error al eliminar el proyecto.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "Project eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
