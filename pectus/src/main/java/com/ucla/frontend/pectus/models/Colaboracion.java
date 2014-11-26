package com.ucla.frontend.pectus.models;

public class Colaboracion {

	private int id;
	private Evento evento;
	private Patrocinador patrocinado;
	private TipoColaboracion tipoColaboracion;
	private Double cantidad;
	
	public Colaboracion(){
		
	}
	
	public Colaboracion(int id, Evento evento, Patrocinador patrocinado,
			TipoColaboracion tipoColaboracion, Double cantidad) {
		super();
		this.id = id;
		this.evento = evento;
		this.patrocinado = patrocinado;
		this.tipoColaboracion = tipoColaboracion;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Patrocinador getPatrocinado() {
		return patrocinado;
	}

	public void setPatrocinado(Patrocinador patrocinado) {
		this.patrocinado = patrocinado;
	}

	public TipoColaboracion getTipoColaboracion() {
		return tipoColaboracion;
	}

	public void setTipoColaboracion(TipoColaboracion tipoColaboracion) {
		this.tipoColaboracion = tipoColaboracion;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
