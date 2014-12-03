package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.TipoLugar;
import com.ucla.frontend.pectus.services.ServicioTipoLugar;



public class TipoLugarFilter {
	private String id = "";
	private String nombre = "";
	private String descripcion = "";
	
	static List<TipoLugar> tipoLugar = ServicioTipoLugar.buscarTipoLugar();
	
	
	
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
	
	
	
	public static List<TipoLugar> getFilterTipoLugar(TipoLugarFilter tipolugarFilter){
		List<TipoLugar> tipol = new ArrayList<TipoLugar>();
	
		String nombre = tipolugarFilter.getNombre().toLowerCase();
		String descripcion = tipolugarFilter.getDescripcion().toLowerCase();
		
		for(Iterator<TipoLugar> i = tipoLugar.iterator(); i.hasNext();){
			TipoLugar tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getDescripcion().toLowerCase().contains(descripcion))
			  
			{
			
				tipol.add(tmp);
			}
				
		}
		return tipol;
	}

	

}
