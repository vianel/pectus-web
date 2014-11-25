package com.ucla.frontend.pectus.models;



public class Patologia {
	
	private Integer id;
	private String nombre;
	private String observacion;
	
	public Patologia(Integer id, String nombre, String observacion) {
		super();
		
		this.id = id;
		this.nombre = nombre;
		this.observacion = observacion;
	}
	

	public Patologia() {
		// TODO Auto-generated constructor stub
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

	public String getobservacion() {
		return observacion;
	}

	public void setobservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	
}
