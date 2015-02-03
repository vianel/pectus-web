package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.services.ServicioClinica;

public class ClinicaFilter {
	
private String nombre = "";
private String rif = "";


static List<Clinica> clinica = ServicioClinica.buscarClinica();

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}	

	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	public static List<Clinica> getFilterClinica(ClinicaFilter clinicaFilter){
		List<Clinica> someClinica = new ArrayList<Clinica>();
		String nombre = clinicaFilter.getNombre().toLowerCase();
		String rif = clinicaFilter.getRif().toLowerCase();
		
		
		for(Iterator<Clinica> i = clinica.iterator(); i.hasNext();){
			Clinica tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) && tmp.getRif().toLowerCase().contains(rif)){
			
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
	public static List<Clinica> getClinicaByRif(String rif){
		List<Clinica> someClinica = new ArrayList<Clinica>();
		for (Iterator<Clinica> i = clinica.iterator(); i.hasNext();){
			Clinica tmp = i.next();
			if(tmp.getRif().equalsIgnoreCase(rif)){
				someClinica.add(tmp);
			}
		}
		return someClinica;
	}

}
