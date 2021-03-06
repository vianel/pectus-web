package com.ucla.frontend.pectus.models;

import java.util.Date;
import java.util.List;

import com.ucla.frontend.pectus.controllers.controladoractividad;

public class Ayuda {
	
	private Integer id;
	private Paciente paciente;
	private Diagnostico diagnostico;
	private Causa causa;
	private String motivo;
	private Date fechaAprobacion;
	private Date fechaSolicitud;
	private Double aprobacion;
	private List<EstudioClinica> listaEstudioClinicas;
	private Character estatus;
	
	public Ayuda(){
		
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public List<EstudioClinica> getListaEstudioClinicas() {
		return listaEstudioClinicas;
	}


	public void setListaEstudioClinicas(List<EstudioClinica> listaEstudioClinicas) {
		this.listaEstudioClinicas = listaEstudioClinicas;
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
