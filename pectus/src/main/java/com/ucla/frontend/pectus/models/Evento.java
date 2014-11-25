package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.DependsOn;

public class Evento {

	private int id;
	private String nombre;
	private Date fecha;
	private String descripcion;
	private Lugar lugar;
	private List<Voluntario> voluntarios;
	private List<Patrocinador> patrocinadores;

	public Evento() {
		super();
		this.voluntarios = new ArrayList<Voluntario>();
		this.patrocinadores = new ArrayList<Patrocinador>();
	}

	public Evento(int id,String nombre, Date fecha, String descripcion, Lugar lugar,
			List<Voluntario> voluntarios, List<Patrocinador> patrocinadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.lugar = lugar;
		this.voluntarios = voluntarios;
		this.patrocinadores = patrocinadores;
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

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	public List<Patrocinador> getPatrocinadores() {
		return patrocinadores;
	}

	public void setPatrocinadores(List<Patrocinador> patrocinadores) {
		this.patrocinadores = patrocinadores;
	}

}
