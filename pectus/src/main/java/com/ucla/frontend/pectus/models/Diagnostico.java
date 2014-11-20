package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.List;

public class Diagnostico {

	private Integer id;
	private String nombre;
	private String observacion;
//	private List<Ayuda> listaAyudas;
	
	public Diagnostico(){
		
	}

	

	public Diagnostico(Integer id, String nombre, String observacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.observacion = observacion;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	
	
	
}
