package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.Patologia;

import com.ucla.frontend.pectus.services.ServicioPatologia;

public class PatologiaFilter {
	private String id = "";
	private String nombre = "";
	private String observacion = "";
	
	static List<Patologia> patologia = ServicioPatologia.buscarPatologia();
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion==null?"":observacion.trim();
	}
	
	
	
	public static List<Patologia> getFilterPatologia(PatologiaFilter patologiaFilter){
		List<Patologia> patolo = new ArrayList<Patologia>();
	
		String nombre = patologiaFilter.getNombre().toLowerCase();
		String observacion = patologiaFilter.getObservacion().toLowerCase();
		
		for(Iterator<Patologia> i = patologia.iterator(); i.hasNext();){
			Patologia tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getObservacion().toLowerCase().contains(observacion))
			  
			{
			
				patolo.add(tmp);
			}
				
		}
		return patolo;
	}

	

}
