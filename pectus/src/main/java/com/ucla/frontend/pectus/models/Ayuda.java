package com.ucla.frontend.pectus.models;

import java.util.Date;
import java.util.List;

public class Ayuda {
	
	private Paciente paciente;
	private Diagnostico diagnostico;
	private String motivo;
//	private Date fechaAprobacion;
	private Date fechaSolicitud;
//	private Double aprobacion;
//	private List<EstudioSolicitud> listaEstudiosSolictud;
	
	
	public Ayuda(Paciente paciente, Diagnostico diagnostico, String motivo,
				Date fechaSolicitud) {
		super();
		this.paciente = paciente;
		this.diagnostico = diagnostico;
		this.motivo = motivo;
		this.fechaSolicitud = fechaSolicitud;

	}






	public Ayuda(){
		
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
