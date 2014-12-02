package com.ucla.frontend.pectus.models;



public class Actividad {
	
	private Integer id;
	private Lugar lugar;
	private Integer idSolicitudActividad;
	private String fecha;
	private String hora;
	private String recursosUtilizados;
	private Float monto;
	private String duracion;
	private Integer nroAsistentes;
	private String descripcion;
	private String observaciones;
	private boolean estatus;
	
	
	
	
	public Actividad() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Actividad(int id, Lugar lugar, int idSolicitudActividad, String fecha, String hora, String recursosUtilizados, 
			Float monto, String duracion, int nroAsistentes, String descripcion, String observaciones, boolean estatus) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.lugar = lugar;
		this.idSolicitudActividad = idSolicitudActividad;
		this.fecha = fecha;
		this.hora = hora;
		this.recursosUtilizados = recursosUtilizados;
		this.monto = monto;
		this.duracion = duracion;
		this.nroAsistentes = nroAsistentes;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.estatus = estatus;
		
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
		lugar = lugar;
	}


	public Integer getIdSolicitudActividad() {
		return idSolicitudActividad;
	}
	public void setIdSolicitudActividad(Integer idSolicitudActividad) {
		this.idSolicitudActividad = idSolicitudActividad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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

	
	
	
}
