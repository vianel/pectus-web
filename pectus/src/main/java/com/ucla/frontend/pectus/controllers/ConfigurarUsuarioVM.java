package com.ucla.frontend.pectus.controllers;

import java.util.List;

import org.zkoss.bind.annotation.Init;

import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.reports.TipoestudioReportes;
import com.ucla.frontend.pectus.services.ServicioPaciente;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;

public class ConfigurarUsuarioVM {
	
	private List<Paciente> currentPaciente;
	private Paciente usuarioSelected;
	
	public ConfigurarUsuarioVM() {
		// TODO Auto-generated constructor stub
	}
	
	@Init
	public void init(){
		this.currentPaciente = ServicioPaciente.buscarPacientes();
	
	}
	
	public List<Paciente> getmodelusuario() {
	      return currentPaciente;
	    }

	public Paciente getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(Paciente usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}
	    
	
	


}
