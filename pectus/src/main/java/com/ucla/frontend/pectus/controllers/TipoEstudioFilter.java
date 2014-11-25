package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.TipoEstudio;

import com.ucla.frontend.pectus.services.ServicioTipoEstudio;

public class TipoEstudioFilter {
	private String id = "";
	private String nombre = "";
	private String descripcion = "";
	
	static List<TipoEstudio> tipoEstudio = ServicioTipoEstudio.buscarTipoEstudio();
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion==null?"":descripcion.trim();
	}
	
	
	
	public static List<TipoEstudio> getFilterTipoEstudio(TipoEstudioFilter tipoestudioFilter){
		List<TipoEstudio> tipoe = new ArrayList<TipoEstudio>();
	
		String nombre = tipoestudioFilter.getNombre().toLowerCase();
		String descripcion = tipoestudioFilter.getDescripcion().toLowerCase();
		
		for(Iterator<TipoEstudio> i = tipoEstudio.iterator(); i.hasNext();){
			TipoEstudio tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getDescripcion().toLowerCase().contains(descripcion))
			  
			{
			
				tipoe.add(tmp);
			}
				
		}
		return tipoe;
	}

	

}
