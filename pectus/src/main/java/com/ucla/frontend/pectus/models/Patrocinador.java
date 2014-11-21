package com.ucla.frontend.pectus.models;

public class Patrocinador {
	
	private String  rif;
	private String nombre;
	private String direccion;
	private String tlfCelular;
	private String tlfFijo;
	private String nombreRepresentante;
	private String tlfRepresentante;
	private String correoRepresentante;
	
	public Patrocinador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patrocinador(String rif, String nombre, String direccion,
			String tlfCelular, String tlfFijo, String nombreRepresentante,
			String tlfRepresentante, String correoRepresentante) {
		super();
		this.rif = rif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tlfCelular = tlfCelular;
		this.tlfFijo = tlfFijo;
		this.nombreRepresentante = nombreRepresentante;
		this.tlfRepresentante = tlfRepresentante;
		this.correoRepresentante = correoRepresentante;
	}

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTlfCelular() {
		return tlfCelular;
	}

	public void setTlfCelular(String tlfCelular) {
		this.tlfCelular = tlfCelular;
	}

	public String getTlfFijo() {
		return tlfFijo;
	}

	public void setTlfFijo(String tlfFijo) {
		this.tlfFijo = tlfFijo;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getTlfRepresentante() {
		return tlfRepresentante;
	}

	public void setTlfRepresentante(String tlfRepresentante) {
		this.tlfRepresentante = tlfRepresentante;
	}

	public String getCorreoRepresentante() {
		return correoRepresentante;
	}

	public void setCorreoRepresentante(String correoRepresentante) {
		this.correoRepresentante = correoRepresentante;
	}
	
	

}
