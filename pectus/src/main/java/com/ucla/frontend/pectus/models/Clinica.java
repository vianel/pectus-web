package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.List;

public class Clinica {
	
	private String nombre;
	private String rif;
	private String direccion;
	private String telefono;
	
	public Clinica(){
		// TODO Auto-generated constructor stub
	}
	
	public Clinica(String nombre, String rif, String direccion,
			String telefono) {
		super();
		this.nombre = nombre;
		this.rif = rif;
		this.direccion = direccion;
		this.telefono = telefono;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	

}
