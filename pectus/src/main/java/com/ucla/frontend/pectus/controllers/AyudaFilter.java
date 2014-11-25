package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioSolicitudAyuda;


public class AyudaFilter {
	private String cedula = "";
	private String patologia = "";

	static List<Ayuda> ayudas = ServicioSolicitudAyuda.buscarAyudas();
	
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula==null?"":cedula.trim();
	}
	public String getPatologia() {
		return patologia;
	}
	public void setPatologia(String patologia) {
		this.patologia = patologia==null?"":patologia.trim();
	}

	public static List<Ayuda> getAyudas() {
		return ayudas;
	}
	public static void setAyudas(List<Ayuda> ayudas) {
		AyudaFilter.ayudas = ayudas;
	}
	
	public static List<Ayuda> getFilterAyudas(AyudaFilter ayudaFilter){
		List<Ayuda> someAyudas = new ArrayList<Ayuda>();
		String cedula = ayudaFilter.getCedula().toLowerCase();
		String patologia = ayudaFilter.getPatologia().toLowerCase();

		
		
		for(Iterator<Ayuda> i = ayudas.iterator(); i.hasNext();){
			Ayuda tmp = i.next();
			if(tmp.getPaciente().getCedula().toLowerCase().contains(cedula) &&
			   tmp.getDiagnostico().getNombre().toLowerCase().contains(patologia)){
			
				someAyudas.add(tmp);
			}
				
		}
		return someAyudas;
	}
	

}
