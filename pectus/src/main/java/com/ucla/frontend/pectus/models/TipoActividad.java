package com.ucla.frontend.pectus.models;

public class TipoActividad {
	
	private int id;
	private String nombre;
	private String descripcion;
	
	
	
	public TipoActividad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoActividad(int id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
