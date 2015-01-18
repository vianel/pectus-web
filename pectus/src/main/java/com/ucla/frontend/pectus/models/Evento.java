package com.ucla.frontend.pectus.models;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.DependsOn;

public class Evento {

	private int id;
	private String nombre;
	private Date fecha;
	private Time hora;
	private Integer cantEntradas;
	private Double costoEntrada;
	private Double montoRecaudado;
	private String observacion;
	private String descripcion;
	private Lugar lugar;
	private List<Voluntario> voluntarios;
	private List<Colaboracion> colaboracion;
	private Double montoEsperado;
	private Integer cantEntradasEsperadas;

	public Evento() {
		super();
		this.voluntarios = new ArrayList<Voluntario>();
		this.colaboracion = new ArrayList<Colaboracion>();
	}


	public Evento(int id, String nombre, Date fecha, Time hora,
			int cantEntradas, Double costoEntrada, Double montoRecaudado,
			String observacion, String descripcion, Lugar lugar,
			List<Voluntario> voluntarios, List<Colaboracion> colaboracion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.cantEntradas = cantEntradas;
		this.costoEntrada = costoEntrada;
		this.montoRecaudado = montoRecaudado;
		this.observacion = observacion;
		this.descripcion = descripcion;
		this.lugar = lugar;
		this.voluntarios = voluntarios;
		this.colaboracion = colaboracion;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}




	public List<Colaboracion> getColaboracion() {
		return colaboracion;
	}


	public void setColaboracion(List<Colaboracion> colaboracion) {
		this.colaboracion = colaboracion;
	}


	public Time getHora() {
		return hora;
	}


	public void setHora(Time hora) {
		this.hora = hora;
	}


	public int getCantEntradas() {
		return cantEntradas;
	}


	public void setCantEntradas(int cantEntradas) {
		this.cantEntradas = cantEntradas;
	}


	public Double getCostoEntrada() {
		return costoEntrada;
	}


	public void setCostoEntrada(Double costoEntrada) {
		this.costoEntrada = costoEntrada;
	}


	public Double getMontoRecaudado() {
		return montoRecaudado;
	}


	public void setMontoRecaudado(Double montoRecaudado) {
		this.montoRecaudado = montoRecaudado;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public Double getMontoEsperado() {
		return montoEsperado;
	}


	public void setMontoEsperado(Double montoEsperado) {
		this.montoEsperado = montoEsperado;
	}


	public Integer getCantEntradasEsperadas() {
		return cantEntradasEsperadas;
	}


	public void setCantEntradasEsperadas(Integer cantEntradasEsperadas) {
		this.cantEntradasEsperadas = cantEntradasEsperadas;
	}


	public void setCantEntradas(Integer cantEntradas) {
		this.cantEntradas = cantEntradas;
	}

	
	
}
