package com.ucla.frontend.pectus.models;

import java.util.Date;

public class Voluntario extends Persona{
	
	private String login;
	private String lugarTrabajo;
	private String cargo;
	private String direccionTrabajo;
	private String telefonoOficina;
	private String referido;
	
	public Voluntario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voluntario(String login, String lugarTrabajo, String cargo,
			String direccionTrabajo, String telefonoOficina, String referido) {
		super();
		this.login = login;
		this.lugarTrabajo = lugarTrabajo;
		this.cargo = cargo;
		this.direccionTrabajo = direccionTrabajo;
		this.telefonoOficina = telefonoOficina;
		this.referido = referido;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLugarTrabajo() {
		return lugarTrabajo;
	}
	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getDireccionTrabajo() {
		return direccionTrabajo;
	}
	public void setDireccionTrabajo(String direccionTrabajo) {
		this.direccionTrabajo = direccionTrabajo;
	}
	public String getTelefonoOficina() {
		return telefonoOficina;
	}
	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}
	public String getReferido() {
		return referido;
	}
	public void setReferido(String referido) {
		this.referido = referido;
	}
	
	
	


}
