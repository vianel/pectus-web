package com.ucla.frontend.pectus.controllers;

public class EventoFilter {

	private String nombre = "";
	private String fecha = "";
	private String descripcion = "";
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha==null?"":fecha.trim();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descaripcion) {
		this.descripcion = descaripcion==null?"":descaripcion.trim();;
	}
	
	
}
