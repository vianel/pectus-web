package com.ucla.frontend.pectus.models;

import java.util.Date;
import java.util.List;

public class Ayuda {
	
	private Paciente paciente;
	private Diagnostico diagnostico;
	private String motivo;
	private Date fechaAprobacion;
	private Date fechaSolicitud;
	private Double aprobacion;
//	private List<EstudioSolicitud> listaEstudiosSolictud;
	
	







	public Ayuda(){
		
	}



	public Ayuda(Paciente paciente, Diagnostico diagnostico, String motivo,
		Date fechaAprobacion, Date fechaSolicitud, Double aprobacion) {
	super();
	this.paciente = paciente;
	this.diagnostico = diagnostico;
	this.motivo = motivo;
	this.fechaAprobacion = fechaAprobacion;
	this.fechaSolicitud = fechaSolicitud;
	this.aprobacion = aprobacion;
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
