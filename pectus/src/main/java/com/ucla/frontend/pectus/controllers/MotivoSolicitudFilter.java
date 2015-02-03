package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.MotivoSolicitud;

import com.ucla.frontend.pectus.services.ServicioMotivoSolicitud;

public class MotivoSolicitudFilter {
	private String id = "";
	private String nombre = "";
	private String descripcion = "";
	
	static List<MotivoSolicitud> motivosolicitud = ServicioMotivoSolicitud.buscarMotivoSolicitud();
	
	
	
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
	
	
	
	public static List<MotivoSolicitud> getFilterMotivosolicitud(MotivoSolicitudFilter motivosolicitudFilter){
		List<MotivoSolicitud> ms = new ArrayList<MotivoSolicitud>();
	
		String nombre = motivosolicitudFilter.getNombre().toLowerCase();
		String descripcion = motivosolicitudFilter.getDescripcion().toLowerCase();
		
		for(Iterator<MotivoSolicitud> i = motivosolicitud.iterator(); i.hasNext();){
			MotivoSolicitud tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getDescripcion().toLowerCase().contains(descripcion))
			  
			{
			
				ms.add(tmp);
			}
				
		}
		return ms;
	}

	

}
