package com.ucla.frontend.pectus.models;

import java.util.Date;
import java.util.List;

import com.ucla.frontend.pectus.controllers.controladoractividad;

public class Ayuda {
	
	private Paciente paciente;
	private Diagnostico diagnostico;
	private Causa causa;
	private String motivo;
	private Date fechaAprobacion;
	private Date fechaSolicitud;
	private Double aprobacion;
	private Character estatus;
	
	public Ayuda(){
		
	}

	


	public Ayuda(Paciente paciente, Diagnostico diagnostico, Causa causa,
			String motivo, Date fechaAprobacion, Date fechaSolicitud,
			Double aprobacion, Character estatus) {
		super();
		this.paciente = paciente;
		this.diagnostico = diagnostico;
		this.causa = causa;
		this.motivo = motivo;
		this.fechaAprobacion = fechaAprobacion;
		this.fechaSolicitud = fechaSolicitud;
		this.aprobacion = aprobacion;
		this.estatus = estatus;
	}




	public Character getEstatus() {
		return estatus;
	}




	public void setEstatus(Character estatus) {
		this.estatus = estatus;
	}




	public Causa getCausa() {
		return causa;
	}






	public void setCausa(Causa causa) {
		this.causa = causa;
	}






	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}



	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}



	public Double getAprobacion() {
		return aprobacion;
	}



	public void setAprobacion(Double aprobacion) {
		this.aprobacion = aprobacion;
	}



	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	
	

}
