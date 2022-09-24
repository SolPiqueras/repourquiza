package com.repourquiza.repourquiza.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.repourquiza.repourquiza.entities.Proyecto;
import com.repourquiza.repourquiza.services.IProyectoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/repoUrquiza")
public class ProyectoController {

	@Autowired
	private IProyectoService service;

	@GetMapping("/proyectos")
	public List<Proyecto> getProyectos() {
		return service.findAll();
	}

	@GetMapping("/proyectos/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {

		Proyecto proyecto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			proyecto = service.findById(id);
		} catch (Exception e) {
			response.put("Mensaje", "Error al obtener el proyecto.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (proyecto == null) {
			response.put("Mensaje", "Proyecto no encontrado.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Proyecto>(proyecto, HttpStatus.OK);
	}

	@PostMapping("/proyectos")
	public ResponseEntity<?> saveProyecto(@Valid @RequestBody Proyecto proyecto, BindingResult datos) {
		Proyecto nuevoProyecto = null;
		Map<String, Object> response = new HashMap<>();
		if (datos.hasErrors()) {
			List<String> errors = datos.getFieldErrors().stream()
					.map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("Errores", errors);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			nuevoProyecto = service.save(proyecto);
		}catch(Exception e) {			
			response.put("Mensaje", "Error al guardar el proyecto.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "Proyecto guardado con éxito");
		response.put("Proyecto", nuevoProyecto);
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
		response.put("Mensaje", "Proyecto eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
