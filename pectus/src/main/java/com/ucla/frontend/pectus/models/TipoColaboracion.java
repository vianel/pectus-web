package com.ucla.frontend.pectus.models;

public class TipoColaboracion {

	private int id;
	private String nombre;
	
	public TipoColaboracion(){
		// TODO Auto-generated constructor stub
	}

	public TipoColaboracion(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		
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
	
	
}
