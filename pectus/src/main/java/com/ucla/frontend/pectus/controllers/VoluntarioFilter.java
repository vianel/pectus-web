package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.services.ServicioVoluntario;

public class VoluntarioFilter {
	
	private String nombre = "";
	private String cedula = "";
	static List<Voluntario> voluntarios = ServicioVoluntario.buscarVoluntarioI();
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula ==null?"":cedula.trim();
	}
	public static List<Voluntario> getFilterVoluntarios(VoluntarioFilter voluntarioFilter){
		List<Voluntario> someVoluntarios = new ArrayList<Voluntario>();
		String nombre = voluntarioFilter.getNombre().toLowerCase();
		String cedula = voluntarioFilter.getCedula().toLowerCase();
		
		for(Iterator<Voluntario> i = voluntarios.iterator(); i.hasNext();){
			Voluntario tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getCedula().toLowerCase().contains(cedula)){
			
				someVoluntarios.add(tmp);
			}
				
		}
		return someVoluntarios;
	}

	public static List<Voluntario> getVoluntariosByCedula(String cedula){
		List<Voluntario> someVoluntarios = new ArrayList<Voluntario>();
		for (Iterator<Voluntario> i = voluntarios.iterator(); i.hasNext();){
			Voluntario tmp = i.next();
			if(tmp.getCedula().equalsIgnoreCase(cedula)){
				someVoluntarios.add(tmp);
			}
		}
		return someVoluntarios;
	}
	
	

}
