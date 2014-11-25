package com.ucla.frontend.pectus.models;

public class Estado {
	
	private Integer id;
	private String nombre;
	private boolean estatus;
	
	public Estado(){
		
	}
	
	public Estado(Integer id, String nombre, boolean estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estatus = estatus;
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
	public boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
	

}
