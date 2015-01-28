package com.ucla.frontend.pectus.models;

public class ActividadRechazada {
	private Integer id;
	private MotivoRechazo motivoRechazo;
	private SolicitudActividad solicitudActividad;
	private String observacion;
	public ActividadRechazada() {
		// TODO Auto-generated constructor stub
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public MotivoRechazo getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(MotivoRechazo motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	public SolicitudActividad getSolicitudActividad() {
		return solicitudActividad;
	}
	public void setSolicitudActividad(SolicitudActividad solicitudActividad) {
		this.solicitudActividad = solicitudActividad;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	

}
