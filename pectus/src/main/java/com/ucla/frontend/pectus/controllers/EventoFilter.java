package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioEvento;

public class EventoFilter {

	private String nombre = "";
	private String fecha = "";
	private String descripcion = "";
	static List<Evento> eventos = ServicioEvento.buscarEventos();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre == null ? "" : nombre.trim();
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha == null ? "" : fecha.trim();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descaripcion) {
		this.descripcion = descaripcion == null ? "" : descaripcion.trim();
		;
	}

	public static List<Evento> getFilterEventos(EventoFilter eventoFilter) {

		List<Evento> someEventos = new ArrayList<Evento>();

		String descripcion = eventoFilter.getDescripcion().toLowerCase();
		String fecha = eventoFilter.getFecha().toLowerCase();

		String nombre = eventoFilter.getNombre().toLowerCase();

		for (Iterator<Evento> i = eventos.iterator(); i.hasNext();) {
			Evento tmp = new Evento();

			if (tmp.getNombre().toLowerCase().contains(nombre)
					&& tmp.getDescripcion().toLowerCase().contains(descripcion)
					&& tmp.getFecha().toString().toLowerCase().contains(fecha)) {

				someEventos.add(tmp);
			}

		}

		return someEventos;

	}

}
