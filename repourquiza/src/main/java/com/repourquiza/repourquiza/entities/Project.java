package com.repourquiza.repourquiza.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column(name = "author", nullable = false)
	private String author;

    @Column(name = "created", nullable = false)
	private LocalDate created;

    @Column(name = "project", nullable = false)
	private String project;

    @Column(name = "title", nullable = false)
	private String title;

    @Column(name = "area", nullable = true)
	private String area;

    @Column(name = "year", nullable = false)
	private String year;
    
    @Column(name= "description", nullable = false)
    private String description;
}
