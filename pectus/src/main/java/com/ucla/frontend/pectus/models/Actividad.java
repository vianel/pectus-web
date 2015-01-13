package com.ucla.frontend.pectus.models;

import java.util.Date;



public class Actividad {
	
	private Integer id;
	private Lugar lugar;
	private SolicitudActividad idSolicitudActividad;
	private Date fechainicio;
	private Date fechafin;
	private String hora;
	private String recursosUtilizados;
	private Float monto;
	private String duracion;
	private Integer nroAsistentes;
	private String descripcion;
	private String observaciones;
	private boolean estatus;
	private int montoesperado;
	private int nroasistentesesperados;
	
	
	
	
	public Actividad() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public Actividad(Integer id, Lugar lugar,
			SolicitudActividad idSolicitudActividad, Date fechainicio,
			Date fechafin, String hora, String recursosUtilizados,
			Float monto, String duracion, Integer nroAsistentes,
			String descripcion, String observaciones, boolean estatus,
			int montoesperado, int nroasistentesesperados) {
		super();
		this.id = id;
		this.lugar = lugar;
		this.idSolicitudActividad = idSolicitudActividad;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.hora = hora;
		this.recursosUtilizados = recursosUtilizados;
		this.monto = monto;
		this.duracion = duracion;
		this.nroAsistentes = nroAsistentes;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.estatus = estatus;
		this.montoesperado = montoesperado;
		this.nroasistentesesperados = nroasistentesesperados;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Lugar getLugar() {
		return lugar;
	}


	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}


	public SolicitudActividad getIdSolicitudActividad() {
		return idSolicitudActividad;
	}
	public void setIdSolicitudActividad(SolicitudActividad idSolicitudActividad) {
		this.idSolicitudActividad = idSolicitudActividad;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getRecursosUtilizados() {
		return recursosUtilizados;
	}
	public void setRecursosUtilizados(String recursosUtilizados) {
		this.recursosUtilizados = recursosUtilizados;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public Integer getNroAsistentes() {
		return nroAsistentes;
	}
	public void setNroAsistentes(Integer nroAsistentes) {
		this.nroAsistentes = nroAsistentes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}


	public Date getFechafin() {
		return fechafin;
	}


	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}


	public int getMontoesperado() {
		return montoesperado;
	}


	public void setMontoesperado(int montoesperado) {
		this.montoesperado = montoesperado;
	}


	public int getNroasistentesesperados() {
		return nroasistentesesperados;
	}


	public void setNroasistentesesperados(int nroasistentesesperados) {
		this.nroasistentesesperados = nroasistentesesperados;
	}

	
	
	
}
