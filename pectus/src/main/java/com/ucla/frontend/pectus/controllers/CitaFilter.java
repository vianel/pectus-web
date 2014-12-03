package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.services.ServicioCita;


public class CitaFilter {
	
	private String paciente = "";
	private String clinica = "";
	private String tipoestudio = "";
	static List<Cita> citas = ServicioCita.buscarCita();
	

	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getClinica() {
		return clinica;
	}
	public void setClinica(String clinica) {
		this.clinica = clinica;
	}

	
	public String getTipoestudio() {
		return tipoestudio;
	}
	public void setTipoestudio(String tipoestudio) {
		this.tipoestudio = tipoestudio;
	}
	public static List<Cita> getCitas() {
		return citas;
	}
	public static void setCitas(List<Cita> citas) {
		CitaFilter.citas = citas;
	}
	public static List<Cita> getFilterCita(CitaFilter citaFilter){
		List<Cita> someCita = new ArrayList<Cita>();
		String paciente = citaFilter.getPaciente().toLowerCase();
		String clinica = citaFilter.getClinica().toLowerCase();
		String tipoestudio = citaFilter.getTipoestudio().toLowerCase(); 
		
		for(Iterator<Cita> i = citas.iterator(); i.hasNext();){
			Cita tmp = i.next();
			if(tmp.getPaciente().getCedula().toLowerCase().contains(paciente) &&
			   tmp.getEstudioClinica().getClinica().getNombre().toLowerCase().contains(clinica) &&
			   tmp.getEstudioClinica().getEstudio().getNombre().toLowerCase().contains(tipoestudio)
			   )
					{
			
				someCita.add(tmp);
			}
				
		}
		return someCita;
	}


}
