package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.List;

public class Estudio {

	private Integer id;
	private String nombre;
	private String descripcion;
//	private List<EstudioClinica> listaEstudioClinica;
//	private boolean estatus;
	
	public Estudio(){
		
	}

	public Estudio(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
	

}
