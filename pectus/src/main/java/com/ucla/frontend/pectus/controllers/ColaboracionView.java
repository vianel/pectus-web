package com.ucla.frontend.pectus.controllers;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Patrocinador;
import com.ucla.frontend.pectus.models.TipoColaboracion;
import com.ucla.frontend.pectus.services.ServicioCita;
import com.ucla.frontend.pectus.services.ServicioColaboracion;
import com.ucla.frontend.pectus.services.ServicioEvento;

public class ColaboracionView {
	
	private Colaboracion colaboracionSelected;
	private Double cantidadSelected;
	private Patrocinador patrocinadorSelected;
	private TipoColaboracion tipoColaboracionSelected;
	private Evento eventoSelected;
	
	private ColaboracionFilter colaboracionFilter = new ColaboracionFilter();
	
	List<Colaboracion> listaColaboraciones = ServicioColaboracion.buscarColaboraciones();
	List<Evento> listaEventos = ServicioEvento.buscarEventos();
	List<Patrocinador> listaPatrocinadores = ServicioColaboracion.buscarPatrocinadores();
	List<TipoColaboracion> listaTipoColaboraciones = ServicioColaboracion.buscarTipoColaboracion();
	
	
	public List<TipoColaboracion> getListaTipoColaboraciones() {
		return listaTipoColaboraciones;
	}

	public void setListaTipoColaboraciones(
			List<TipoColaboracion> listaTipoColaboraciones) {
		this.listaTipoColaboraciones = listaTipoColaboraciones;
	}

	public List<Colaboracion> getmodelcolaboracion() {
        return new ListModelList<Colaboracion>(listaColaboraciones);
  
    }

	public ColaboracionFilter getColaboracionFilter() {
		return colaboracionFilter;
	}

	public void setColaboracionFilter(ColaboracionFilter colaboracionFilter) {
		this.colaboracionFilter = colaboracionFilter;
	}

	public List<Colaboracion> getListaColaboraciones() {
		return listaColaboraciones;
	}

	public void setListaColaboraciones(List<Colaboracion> listaColaboraciones) {
		this.listaColaboraciones = listaColaboraciones;
	}
	
	
	
	
	
	public Colaboracion getColaboracionSelected() {
		return colaboracionSelected;
	}

	public void setColaboracionSelected(Colaboracion colaboracionSelected) {
		this.colaboracionSelected = colaboracionSelected;
	}

	public Double getCantidadSelected() {
		return cantidadSelected;
	}

	public void setCantidadSelected(Double cantidadSelected) {
		this.cantidadSelected = cantidadSelected;
	}

	public Patrocinador getPatrocinadorSelected() {
		return patrocinadorSelected;
	}

	public void setPatrocinadorSelected(Patrocinador patrocinadorSelected) {
		this.patrocinadorSelected = patrocinadorSelected;
	}

	public TipoColaboracion getTipoColaboracionSelected() {
		return tipoColaboracionSelected;
	}

	public void setTipoColaboracionSelected(
			TipoColaboracion tipoColaboracionSelected) {
		this.tipoColaboracionSelected = tipoColaboracionSelected;
	}

	public Evento getEventoSelected() {
		return eventoSelected;
	}

	public void setEventoSelected(Evento eventoSelected) {
		this.eventoSelected = eventoSelected;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public List<Patrocinador> getListaPatrocinadores() {
		return listaPatrocinadores;
	}

	public void setListaPatrocinadores(List<Patrocinador> listaPatrocinadores) {
		this.listaPatrocinadores = listaPatrocinadores;
	}

	@Command
    @NotifyChange({"modelcolaboracion"})
    public void changeFilter() {
        listaColaboraciones = ColaboracionFilter.getFilterColaboraciones(colaboracionFilter);
    }
	@Command
	public void abrirDialogoRegistrarColaboracion(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarColaboracion.zul", null, null);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "modelcolaboracion", "footer" })
	public void guardarColaboracion() throws Exception{
		   String response=null;
//	  if (eventoSelected.getCedula()!= null){
			
			colaboracionSelected = new Colaboracion();
			
			colaboracionSelected.setCantidad(cantidadSelected);
			colaboracionSelected.setEvento(eventoSelected);
			colaboracionSelected.setPatrocinado(patrocinadorSelected);
			colaboracionSelected.setTipoColaboracion(tipoColaboracionSelected);
			
		
			response = ServicioColaboracion.agregarColaboracion(colaboracionSelected);
			if (response.equalsIgnoreCase("true"))
			{
			
				Clients.showNotification("Colaboracion Guardada", null, true);
			//	x.detach(); ver esto mosca revisar

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
