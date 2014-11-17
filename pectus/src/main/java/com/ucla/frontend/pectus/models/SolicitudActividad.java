package com.ucla.frontend.pectus.models;

import java.util.Date;

public class SolicitudActividad {

	private Integer id;
	private Integer idVoluntario;
	private Integer idTipoActividad;
	private String  descripcion;
	private Date fecha;
	private Date hora;
	private boolean estatus;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdVoluntario() {
		return idVoluntario;
	}
	public void setIdVoluntario(Integer idVoluntario) {
		this.idVoluntario = idVoluntario;
	}
	public Integer getIdTipoActividad() {
		return idTipoActividad;
	}
	public void setIdTipoActividad(Integer idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
}
