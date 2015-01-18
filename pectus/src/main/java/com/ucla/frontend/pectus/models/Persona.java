package com.ucla.frontend.pectus.models;

import java.util.Date;

public class Persona {
    
	private String cedula;
	private String nombre;
	private String apellido;
	private String celular;
	private String fijo;
	private String direccion;
	private String correo;
	private Date fechaNacimiento;
	private String profesion;
	private char estadoCivil;
	private Ciudad ciudad;
	
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Persona(String cedula, String nombre, String apellido,
			String celular, String fijo, String direccion, String correo,
			Date fechaNacimiento, String profesion, char estadoCivil,
			Ciudad ciudad) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.fijo = fijo;
		this.direccion = direccion;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.ciudad = ciudad;
	}


	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getFijo() {
		return fijo;
	}
	public void setFijo(String fijo) {
		this.fijo = fijo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public char getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(char estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	
}
