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

import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioColaboracion;
import com.ucla.frontend.pectus.services.ServicioEvento;



public class ControladorEvento {

	private static final String footerMensaje = "Esto son todos los eventos";
	private EventoFilter eventoFilter = new EventoFilter();
	List<Evento> currentEvento = ServicioEvento.buscarEventos();
	List<Lugar> lugares = ServicioEvento.buscarLugares();
	
	private Evento eventoSelected;
	private Lugar lugarSelected;
	private String nombreSelected;
	private String descripcionSelected;
	private String cantidadEntradasSelected;
	private String costoEntradasSelected;
	private String montoRecaudadoSelected;
	private String observacionesSelected;
	
	
	
	public Evento getEventoSelected() {
		return eventoSelected;
	}



	public void setEventoSelected(Evento eventoSelected) {
		this.eventoSelected = eventoSelected;
	}



	public String getNombreSelected() {
		return nombreSelected;
	}



	public void setNombreSelected(String nombreSelected) {
		this.nombreSelected = nombreSelected;
	}



	public String getDescripcionSelected() {
		return descripcionSelected;
	}



	public void setDescripcionSelected(String descripcionSelected) {
		this.descripcionSelected = descripcionSelected;
	}



	public String getCantidadEntradasSelected() {
		return cantidadEntradasSelected;
	}



	public void setCantidadEntradasSelected(String cantidadEntradasSelected) {
		this.cantidadEntradasSelected = cantidadEntradasSelected;
	}



	public String getCostoEntradasSelected() {
		return costoEntradasSelected;
	}



	public void setCostoEntradasSelected(String costoEntradasSelected) {
		this.costoEntradasSelected = costoEntradasSelected;
	}



	public String getMontoRecaudadoSelected() {
		return montoRecaudadoSelected;
	}



	public void setMontoRecaudadoSelected(String montoRecaudadoSelected) {
		this.montoRecaudadoSelected = montoRecaudadoSelected;
	}



	public String getObservacionesSelected() {
		return observacionesSelected;
	}



	public void setObservacionesSelected(String observacionesSelected) {
		this.observacionesSelected = observacionesSelected;
	}



	public String getFooter() {
		return String.format(footerMensaje, currentEvento.size());
	}

	
	
	public Lugar getLugarSelected() {
		return lugarSelected;
	}



	public void setLugarSelected(Lugar lugarSelected) {
		this.lugarSelected = lugarSelected;
	}



	public List<Lugar> getLugares() {
		return lugares;
	}


	public void setLugares(List<Lugar> lugares) {
		this.lugares = lugares;
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
	@Command
//	@NotifyChange({ "modelcolaboracion", "footer" })
	public void guardarEvento() throws Exception{
		   String response=null;
//	  if (eventoSelected.getCedula()!= null){
			
		   eventoSelected = new Evento();
		   eventoSelected.setCantEntradas(Integer.parseInt(cantidadEntradasSelected));
		   eventoSelected.setCostoEntrada(Double.parseDouble(costoEntradasSelected));
		   eventoSelected.setDescripcion(descripcionSelected);
//		   eventoSelected.setFecha(new Date());
		   eventoSelected.setLugar(lugarSelected);
		   eventoSelected.setMontoRecaudado(Double.parseDouble(montoRecaudadoSelected));
		   eventoSelected.setNombre(nombreSelected);
		   eventoSelected.setObservacion(observacionesSelected);
		
		   
			response = ServicioEvento.agregarEvento(eventoSelected);
			if (response.equalsIgnoreCase("true"))
			{
			
				Clients.showNotification("Evento Guardado", null, true);
			//	x.detach(); ver esto mosca revisar
				currentEvento = ServicioEvento.buscarEventos();

			}else{
			
				Clients.showNotification("Error al guardar", true);
			
			}
//		}
//	     else{
//	     
//	    Clients.showNotification("Porfavor ingrese todos los datos validos");
//	}
	}
	
	
	
}
