package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.TipoColaboracion;

import com.ucla.frontend.pectus.services.ServicioTipoColaboracion;

public class TipoColaboracionFilter {
	private String id = "";
	private String nombre = "";
	
	
	static List<TipoColaboracion> tipoColaboracion = ServicioTipoColaboracion.buscarTipoColaboracion();
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}
	
	
	public static List<TipoColaboracion> getFilterTipoColaboracion(TipoColaboracionFilter tipocolaboracionFilter){
		List<TipoColaboracion> tipoc = new ArrayList<TipoColaboracion>();
	
		String nombre = tipocolaboracionFilter.getNombre().toLowerCase();
		
		
		for(Iterator<TipoColaboracion> i = tipoColaboracion.iterator(); i.hasNext();){
			TipoColaboracion tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre))
			  
			{
			
				tipoc.add(tmp);
			}
				
		}
		return tipoc;
	}

	

}
