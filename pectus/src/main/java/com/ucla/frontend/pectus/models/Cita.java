package com.ucla.frontend.pectus.models;

import java.util.Date;

public class Cita {
	
	private Integer id;
	private Date fecha;
	private Date hora;
	private Paciente paciente;
	//private Clinica clinica;
	//private TipoEstudio tipoestudio;
	private EstudioClinica estudio;
	private boolean estatus;
	
	public Cita(Integer id, Date fecha, Date hora, Paciente paciente,
			EstudioClinica estudio, boolean estatus) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.paciente = paciente;
		this.estudio = estudio;
		//this.clinica = clinica;
		//this.tipoestudio = tipoestudio;
		this.estatus = estatus;
	}
	
	public Cita(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

/*	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public TipoEstudio getTipoEstudio() {
		return tipoestudio;
	}

	public void setTipoEstudio(TipoEstudio tipoestudio) {
		this.tipoestudio = tipoestudio;
	}
*/
	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	
	public EstudioClinica getEstudioClinica() {
		return estudio;
	}

	public void setEstudioClinica(EstudioClinica estudio) {
		this.estudio = estudio;
	}
	
	

}
