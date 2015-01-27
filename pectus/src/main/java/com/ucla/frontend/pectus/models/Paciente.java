package com.ucla.frontend.pectus.models;

import java.util.Date;

import org.zkoss.bind.annotation.DependsOn;

public class Paciente extends Persona{
	
	

	private Estado estado;
	private Ciudad ciudad;
	private Seguro seguro;
	private Integer   nroHijos;
	private String cedulaConyugue;
	private String nombreConyugue;
	private String apellidoConyugue;
	private String ocupacionConyugue;
	private char tipoVivienda;
	private Integer nroHabitantes;
	private char tendenciaVivienda;
	private Double alquiler;
	private String lugarTrabajo;
	private String direccionTrabajo;
	private String telefonoTrabajo;
	private float ingresos;
	private float egresos;
	private boolean estatus;
	// agregados
	private Date fechaNacConyugue;
	private String login;
	
	
	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

		

	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public Integer getNroHijos() {
		return nroHijos;
	}
	public void setNroHijos(Integer nroHijos) {
		this.nroHijos = nroHijos;
	}
	public String getCedulaConyugue() {
		return cedulaConyugue;
	}
	public void setCedulaConyugue(String cedulaConyugue) {
		this.cedulaConyugue = cedulaConyugue;
	}
	public String getNombreConyugue() {
		return nombreConyugue;
	}
	public void setNombreConyugue(String nombreConyugue) {
		this.nombreConyugue = nombreConyugue;
	}
	public String getApellidoConyugue() {
		return apellidoConyugue;
	}
	public void setApellidoConyugue(String apellidoConyugue) {
		this.apellidoConyugue = apellidoConyugue;
	}
	public String getOcupacionConyugue() {
		return ocupacionConyugue;
	}
	public void setOcupacionConyugue(String ocupacionConyugue) {
		this.ocupacionConyugue = ocupacionConyugue;
	}
	public char getTipoVivienda() {
		return tipoVivienda;
	}
	public void setTipoVivienda(char tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
	public Integer getNroHabitantes() {
		return nroHabitantes;
	}
	public void setNroHabitantes(Integer nroHabitantes) {
		this.nroHabitantes = nroHabitantes;
	}
	public char getTendenciaVivienda() {
		return tendenciaVivienda;
	}
	public void setTendenciaVivienda(char tendenciaVivienda) {
		this.tendenciaVivienda = tendenciaVivienda;
	}
	public Double getAlquiler() {
		return alquiler;
	}
	public void setAlquiler(Double alquiler) {
		this.alquiler = alquiler;
	}
	public String getLugarTrabajo() {
		return lugarTrabajo;
	}
	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}
	public String getDireccionTrabajo() {
		return direccionTrabajo;
	}
	public void setDireccionTrabajo(String direccionTrabajo) {
		this.direccionTrabajo = direccionTrabajo;
	}
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	public float getIngresos() {
		return ingresos;
	}
	public void setIngresos(float f) {
		this.ingresos = f;
	}
	public float getEgresos() {
		return egresos;
	}
	public void setEgresos(float f) {
		this.egresos = f;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	// agregados
	public Date getFechaNacConyugue() {
		return fechaNacConyugue;
	}
	public void setFechaNacConyugue(Date fechaNacConyugue) {
		this.fechaNacConyugue = fechaNacConyugue;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	
	
	

	


}
