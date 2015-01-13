package com.ucla.frontend.pectus.models;

import java.util.Date;

public class SolicitudActividad {

	private Integer id;

	private TipoActividad idTipoActividad;
	private String  descripcion;
	private Date fecha;
	private String nomsolicitante;
	private String tlfsolicitante;
	private boolean estatus;
	
	
	
	public SolicitudActividad(Integer id, TipoActividad idTipoActividad,
			String descripcion, Date fecha, String nomsolicitante,
			String tlfsolicitante, boolean estatus) {
		super();
		this.id = id;
		this.idTipoActividad = idTipoActividad;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.nomsolicitante = nomsolicitante;
		this.tlfsolicitante = tlfsolicitante;
		this.estatus = estatus;
	}
	public SolicitudActividad() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoActividad getIdTipoActividad() {
		return idTipoActividad;
	}
	public void setIdTipoActividad(TipoActividad idTipoActividad) {
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
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public String getNomsolicitante() {
		return nomsolicitante;
	}
	public void setNomsolicitante(String nomsolicitante) {
		this.nomsolicitante = nomsolicitante;
	}
	public String getTlfsolicitante() {
		return tlfsolicitante;
	}
	public void setTlfsolicitante(String tlfsolicitante) {
		this.tlfsolicitante = tlfsolicitante;
	}
	
	
	
}
