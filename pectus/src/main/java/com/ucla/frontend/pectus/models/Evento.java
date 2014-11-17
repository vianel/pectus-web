package com.ucla.frontend.pectus.models;

import java.util.Date;

import org.zkoss.bind.annotation.DependsOn;

public class Evento {

	
	private String nombre;
	private Date fecha;
	private String descripcion;
	private String lugar;
	private String solicitudEvento;
	
	
	
	public Evento() {
		super();
	}

	public Evento(String nombre, Date fecha, String descripcion, String lugar,
			String solicitudEvento) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.lugar = lugar;
		this.solicitudEvento = solicitudEvento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getSolicitudEvento() {
		return solicitudEvento;
	}

	public void setSolicitudEvento(String solicitudEvento) {
		this.solicitudEvento = solicitudEvento;
	}
	

	
	
}
