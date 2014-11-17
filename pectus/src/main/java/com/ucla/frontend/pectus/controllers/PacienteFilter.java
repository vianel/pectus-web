package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioPaciente;

public class PacienteFilter {
	private String nombre = "";
	private String apellido = "";
	private String cedula = "";
	static List<Paciente> pacientes = ServicioPaciente.BuscarPacientes();
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido==null?"":apellido.trim();
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula == null?"":nombre.trim();
	}
	
	public static List<Paciente> getFilterPacientes(PacienteFilter pacienteFilter){
		List<Paciente> somePacientes = new ArrayList<Paciente>();
		String nombre = pacienteFilter.getNombre().toLowerCase();
		String apellido = pacienteFilter.getApellido().toLowerCase();
		String cedula = pacienteFilter.getCedula().toLowerCase();
		
		for(Iterator<Paciente> i = pacientes.iterator(); i.hasNext();){
			Paciente tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getApellido().toLowerCase().contains(apellido)&&
			   tmp.getCedula().toLowerCase().contains(cedula)){
			
				somePacientes.add(tmp);
			}
				
		}
		return somePacientes;
	}

	public static List<Paciente> getPacientesByCedula(String cedula){
		List<Paciente> somePacientes = new ArrayList<Paciente>();
		for (Iterator<Paciente> i = pacientes.iterator(); i.hasNext();){
			Paciente tmp = i.next();
			if(tmp.getCedula().equalsIgnoreCase(cedula)){
				somePacientes.add(tmp);
			}
		}
		return somePacientes;
	}

}
