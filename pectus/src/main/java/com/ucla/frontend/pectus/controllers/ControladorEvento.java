package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.impl.Attribute;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioEvento;



public class ControladorEvento {

	private static final String footerMensaje = "Esto son todos los eventos";
	private EventoFilter eventoFilter = new EventoFilter();
	List<Evento> currentEvento = ServicioEvento.buscarEventos();
	
	public String getFooter() {
		return String.format(footerMensaje, currentEvento.size());
	}

	public EventoFilter getEventoFilter() {
		return eventoFilter;
	}

	public void setEventoFilter(EventoFilter eventoFilter) {
		this.eventoFilter = eventoFilter;
	}
	
	@Command
	@NotifyChange({ "modelevento", "footer" })
	public void changeFilter() {
		currentEvento = (List<Evento>) EventoFilter.getFilterEventos(eventoFilter);
		
	}

	public List<Evento> getCurrentEvento() {
		return currentEvento;
	}
	
	@Command
	public void abrirDialogoRegistrarEvento(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarEvento.zul", null, null);
		window.doModal();
	}
	
	
	
	
}
