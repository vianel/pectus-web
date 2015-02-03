package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.TipoSeguro;
import com.ucla.frontend.pectus.services.ServicioTipoSeguro;

public class TipoSeguroFilter {
	private String id = "";
	private String nombre = "";
	private String descripcion = "";
	
	static List<TipoSeguro> tiposeguro = ServicioTipoSeguro.buscarTipoSeguro();
	
	
	
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
	
	
	
	public static List<TipoSeguro> getFilterTipoSeguro(TipoSeguroFilter tiposeguroFilter){
		List<TipoSeguro> ts = new ArrayList<TipoSeguro>();
	
		String nombre = tiposeguroFilter.getNombre().toLowerCase();
		String descripcion = tiposeguroFilter.getDescripcion().toLowerCase();
		
		for(Iterator<TipoSeguro> i = tiposeguro.iterator(); i.hasNext();){
			TipoSeguro tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&  tmp.getDescripcion().toLowerCase().contains(descripcion))
			  
			{
			
				ts.add(tmp);
			}
				
		}
		return ts;
	}

	public static List<TipoSeguro> getTipoSeguroByNombre(String nombre){
		List<TipoSeguro> someTipoSeguro = new ArrayList<TipoSeguro>();
		for (Iterator<TipoSeguro> i = tiposeguro.iterator(); i.hasNext();){
			TipoSeguro tmp = i.next();
			if(tmp.getNombre().equalsIgnoreCase(nombre)){
				someTipoSeguro.add(tmp);
			}
		}
		return someTipoSeguro;
	}
	public static List<TipoSeguro> getTipoSeguroByDescripcion(String descripcion){
		List<TipoSeguro> someTipoSeguro = new ArrayList<TipoSeguro>();
		for (Iterator<TipoSeguro> i = tiposeguro.iterator(); i.hasNext();){
			TipoSeguro tmp = i.next();
			if(tmp.getDescripcion().equalsIgnoreCase(descripcion)){
				someTipoSeguro.add(tmp);
			}
		}
		return someTipoSeguro;
	}

}