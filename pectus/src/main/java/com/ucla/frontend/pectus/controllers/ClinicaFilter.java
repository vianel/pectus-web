package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.services.ServicioClinica;

public class ClinicaFilter {
	
private String nombre = "";


static List<Clinica> clinica = ServicioClinica.buscarClinica();

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}

	public static List<Clinica> getFilterClinica(ClinicaFilter clinicaFilter){
		List<Clinica> someClinica = new ArrayList<Clinica>();
		String nombre = clinicaFilter.getNombre().toLowerCase();
		
		
		for(Iterator<Clinica> i = clinica.iterator(); i.hasNext();){
			Clinica tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre)){
			
				someClinica.add(tmp);
			}
				
		}
		return someClinica;
	}

	public static List<Clinica> getClinicaByNombre(String nombre){
		List<Clinica> someClinica = new ArrayList<Clinica>();
		for (Iterator<Clinica> i = clinica.iterator(); i.hasNext();){
			Clinica tmp = i.next();
			if(tmp.getNombre().equalsIgnoreCase(nombre)){
				someClinica.add(tmp);
			}
		}
		return someClinica;
	}

}
