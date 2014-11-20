package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.AyudaData;
import com.ucla.frontend.pectus.models.EstudioSolicitud;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioPaciente;
import com.ucla.frontend.pectus.services.ServicioSolicitudAyuda;

public class AyudaView {

	private Ayuda selected;
	private List<Ayuda> ayudas = ServicioSolicitudAyuda.buscarAyudas();
	private List<EstudioSolicitud> estudios = new ArrayList<EstudioSolicitud>(); 
	
	List<Paciente> listaPacientes = ServicioPaciente.buscarPacientes();
	
	@Init
	public void init(){
		selected = ayudas.get(0);
		getmodelpaciente();
		
	}

	public ListModel<Paciente> getmodelpaciente() {
        return new ListModelList<Paciente>(listaPacientes);
    }
	public Ayuda getSelected() {
		return selected;
	}

	public void setSelected(Ayuda selected) {
		this.selected = selected;
	}

	public List<Ayuda> getAyudas() {
		
		return ayudas;
	}

	public void setAyudas(List<Ayuda> ayudas) {
		this.ayudas = ayudas;
	}
	
	@Command
	public void abrirDialogoRegistrarAyuda(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarAyuda.zul", null, null);
		window.doModal();
	}
	
		
}

