package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.services.ServicioActividad;

public class ActividadFilter {
	

	private String fecha = "";
	private static List<Actividad> actividades = ServicioActividad.buscaractividades();
	

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha==null?"":fecha.trim();
	}

	
	public static List<Actividad> getFilteractividades(ActividadFilter actividadFilter){
		List<Actividad> someactividades = new ArrayList<Actividad>();
		String fecha = actividadFilter.getFecha().toLowerCase();
		
		
		for(Iterator<Actividad> i = actividades.iterator(); i.hasNext();){
			Actividad tmp = i.next();
			if(tmp.getDescripcion().toLowerCase().contains(fecha) ){
			
				someactividades.add(tmp);
			}
				
		}
		return someactividades;
	}
	

}
