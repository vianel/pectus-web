package com.ucla.frontend.pectus.models;

import java.util.List;

public class Comision {
	
	private Integer id;
	private String nombre;
	private String descripcion;



	public Comision(){
		
	}
	
	
	
	public Comision(Integer id, String nombre, String descripcion) {
		super();
		this.setId(id);
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
