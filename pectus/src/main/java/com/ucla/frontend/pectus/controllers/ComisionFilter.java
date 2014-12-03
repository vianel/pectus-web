package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.Comision;

import com.ucla.frontend.pectus.services.ServicioComision;

public class ComisionFilter {
	private String id = "";
	private String nombre = "";
	private String descripcion = "";
	
	static List<Comision> comision = ServicioComision.buscarComision();
	
	
	
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
	
	
	
	public static List<Comision> getFilterComision(ComisionFilter comisionFilter){
		List<Comision> comi = new ArrayList<Comision>();
	
		String nombre = comisionFilter.getNombre().toLowerCase();
		String descripcion = comisionFilter.getDescripcion().toLowerCase();
		
		for(Iterator<Comision> i = comision.iterator(); i.hasNext();){
			Comision tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getDescripcion().toLowerCase().contains(descripcion))
			  
			{
			
				comi.add(tmp);
			}
				
		}
		return comi;
	}

	

}
