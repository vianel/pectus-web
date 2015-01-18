package com.ucla.frontend.pectus.models;

import java.util.Date;

public class Cita {
	
	private Integer id;
	private EstudioSolicitud estudioSolicitud;
	private Date fechaAsignacion;
	private Date fechaCita;
	private Date fechaEntregaComprobante;
	private Date hora;	
	private boolean estatus;
	

	
	public Cita(){
		
	}

	
	public Cita(Integer id, EstudioSolicitud estudioSolicitud,
			Date fechaAsignacion, Date fechaCita, Date fechaEntregaComprobante, Date hora,
			boolean estatus) {
		super();
		this.id = id;
		this.estudioSolicitud = estudioSolicitud;
		this.fechaAsignacion = fechaAsignacion;
		this.fechaCita = fechaCita;
		this.fechaEntregaComprobante = fechaEntregaComprobante;
		this.hora = hora;
		this.estatus = estatus;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public EstudioSolicitud getEstudioSolicitud() {
		return estudioSolicitud;
	}

	public void setEstudioSolicitud(EstudioSolicitud estudioSolicitud) {
		this.estudioSolicitud = estudioSolicitud;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Date getFechaEntregaComprobante() {
		return fechaEntregaComprobante;
	}

	public void setFechaEntregaComprobante(Date fechaEntregaComprobante) {
		this.fechaEntregaComprobante = fechaEntregaComprobante;
	}
	
	
	
	

}
