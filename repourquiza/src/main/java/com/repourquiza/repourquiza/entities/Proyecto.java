package com.repourquiza.repourquiza.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Proyecto;

    @Column(name = "nombre_autor", nullable = false)
	private String nombre_autor;

    @Column(name = "fecha_creacion", nullable = false)
	private Date fecha_creacion;

    @Column(name = "proyecto", nullable = false)
	private String proyecto;

    @Column(name = "titulo_proyecto", nullable = false)
	private String titulo_proyecto;

    @Column(name = "materia", nullable = true)
	private String materia;

    @Column(name = "anio_pedagogico", nullable = false)
	private String anio_pedagogico;
}
