package com.ucla.frontend.pectus.models;

import java.util.Date;

public class SolicitudRechazada {
	
	private Integer id;
	private String codigo;
	private MotivoRechazo motivoRechazo;
	private Ayuda ayuda;
	private Date fechaRechazo;
	private String descripcion;
	
	public SolicitudRechazada(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public MotivoRechazo getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(MotivoRechazo motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public Ayuda getAyuda() {
		return ayuda;
	}

	public void setAyuda(Ayuda ayuda) {
		this.ayuda = ayuda;
	}

	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
