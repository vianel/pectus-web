package com.ucla.frontend.pectus.models;

import java.util.Date;

public class EstudioSolicitud {

	private Integer id;
	private Ayuda ayuda;
	private EstudioClinica estudioClinica;
	private Double porcAprobacion;
	private Date fechaAprobacion;
	private boolean estatus;
	
	
	public EstudioSolicitud(){
		
	}



	public EstudioSolicitud(Integer id, Ayuda ayuda,
			EstudioClinica estudioClinica, Double porcAprobacion,
			Date fechaAprobacion, boolean estatus) {
		super();
		this.id = id;
		this.ayuda = ayuda;
		this.estudioClinica = estudioClinica;
		this.porcAprobacion = porcAprobacion;
		this.fechaAprobacion = fechaAprobacion;
		this.estatus = estatus;
	}






	public Double getPorcAprobacion() {
		return porcAprobacion;
	}






	public void setPorcAprobacion(Double porcAprobacion) {
		this.porcAprobacion = porcAprobacion;
	}






	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}






	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}






	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Ayuda getAyuda() {
		return ayuda;
	}


	public void setAyuda(Ayuda ayuda) {
		this.ayuda = ayuda;
	}


	public EstudioClinica getEstudioClinica() {
		return estudioClinica;
	}


	public void setEstudioClinica(EstudioClinica estudioClinica) {
		this.estudioClinica = estudioClinica;
	}


	public boolean isEstatus() {
		return estatus;
	}


	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	
	
}
