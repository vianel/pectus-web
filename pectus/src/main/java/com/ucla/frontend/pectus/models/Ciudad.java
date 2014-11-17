package com.ucla.frontend.pectus.models;

public class Ciudad {

	private Integer id;
	private String nombre;
	private Estado estado;
	private boolean estatus;
	
	public Ciudad(){
		
	}
	
	public Ciudad(Integer id, String nombre, Estado estado, boolean estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
}
