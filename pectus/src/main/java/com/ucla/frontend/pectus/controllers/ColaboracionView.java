package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.controllers.ControladorEvento.ChooseEvent;
import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Patrocinador;
import com.ucla.frontend.pectus.models.TipoColaboracion;
import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.services.ServicioCita;
import com.ucla.frontend.pectus.services.ServicioColaboracion;
import com.ucla.frontend.pectus.services.ServicioEvento;

public class ColaboracionView  extends Div implements IdSpace {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 13231313333L;
	private Colaboracion colaboracionSelected;
	private Double cantidadSelected;
	private Patrocinador patrocinadorSelected;
	private TipoColaboracion tipoColaboracionSelected;
	private Evento eventoSelected;
	
	private ColaboracionFilter colaboracionFilter = new ColaboracionFilter();
	private ListModelList<Colaboracion> listaColaboracionesSeleccionados = new ListModelList<Colaboracion>();
	List<Colaboracion> listaColaboraciones = ServicioColaboracion.buscarColaboraciones();
	List<Evento> listaEventos = ServicioEvento.buscarEventos();
	ListModelList<Evento> listaEventoTodos = new ListModelList<Evento>();
	private ListModelList<Colaboracion> listaColaboracionesTodos;
	List<Patrocinador> listaPatrocinadores = ServicioColaboracion.buscarPatrocinadores();
	List<TipoColaboracion> listaTipoColaboraciones = ServicioColaboracion.buscarTipoColaboracion();
    @Wire
    private Listbox candidateLb = new Listbox();
    @Wire
    private Listbox chosenLb = new Listbox();
	
	
	
	
	public ColaboracionView() {
		super();
		for(Evento evento : listaEventos){
			if(evento.getEstatus().compareTo('V') == 0)
			listaEventoTodos.add(evento);
		}
		
		// TODO Auto-generated constructor stub
		listaColaboracionesTodos = new ListModelList<Colaboracion>();
		if(listaColaboraciones != null){
		for(Colaboracion colaboracion : listaColaboraciones){
			
			listaColaboracionesTodos.add(colaboracion);
	
		}
		}
		
	}

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
	
    @Command
    @NotifyChange({"patrocinadorSelected", "tipoColaboracionSelected", "cantidadSelected"})
	public void limpiarCampos(){
    	patrocinadorSelected = new Patrocinador();
    	tipoColaboracionSelected = new TipoColaboracion();
    	cantidadSelected = null;
		
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
	@NotifyChange({ "modelcolaboracion", "footer", "patrocinadorSelected", "tipoColaboracionSelected", "cantidadSelected", "eventoSelected", "listaEventos" })
	public void guardarColaboracion() throws Exception{
		   String response=null;
//	  if (eventoSelected.getCedula()!= null){
		   if(cantidadSelected != null && patrocinadorSelected.getRif() != null 
					&& tipoColaboracionSelected.getNombre() != null){
			colaboracionSelected = new Colaboracion();
			
			colaboracionSelected.setCantidad(cantidadSelected);
			colaboracionSelected.setEvento(eventoSelected);
			colaboracionSelected.setPatrocinado(patrocinadorSelected);
			colaboracionSelected.setTipoColaboracion(tipoColaboracionSelected);
			
		
			response = ServicioColaboracion.agregarColaboracion(colaboracionSelected);
			if (response.equalsIgnoreCase("true"))
			{
				listaColaboracionesTodos.clear();
				listaColaboraciones = ServicioColaboracion.buscarColaboraciones();
				if(listaColaboraciones != null){
				for(Colaboracion colaboracion : listaColaboraciones){
					
					listaColaboracionesTodos.add(colaboracion);
				}
				}
				List<Evento> listEvent = new ArrayList<Evento>();
				listaEventos.clear();
				listEvent =	ServicioEvento.buscarEventos();
				for(Evento event : listEvent){
					if(event.getEstatus().compareTo('V') == 0)
					listaEventos.add(event);
					if(event.getId() == eventoSelected.getId()){
						eventoSelected.setColaboracion(event.getColaboracion());
					
					}
				}
				
				
				
				
				Clients.showNotification("Colaboracion Guardada", null, null, null, 2000);
				patrocinadorSelected = new Patrocinador();
		    	tipoColaboracionSelected = new TipoColaboracion();
		    	cantidadSelected = null;
			//	x.detach(); ver esto mosca revisar

			}else{
			
				Clients.showNotification("Error al guardar", null, null, null, 2000);
			
			}
			colaboracionSelected = null;
		}
	     else{
	    	 
	    	 Clients.showNotification("No se permiten campos vacios", null, null, null, 2000); 
	     }
	    
//	     
//	    Clients.showNotification("Porfavor ingrese todos los datos validos");
//	}
	}

	public ListModelList<Colaboracion> getListaColaboracionesSeleccionados() {
		return listaColaboracionesSeleccionados;
	}

	public void setListaColaboracionesSeleccionados(
			ListModelList<Colaboracion> listaColaboracionesSeleccionados) {
		this.listaColaboracionesSeleccionados = listaColaboracionesSeleccionados;
	}

	public ListModelList<Colaboracion> getListaColaboracionesTodos() {
		return listaColaboracionesTodos;
	}

	public void setListaColaboracionesTodos(
			ListModelList<Colaboracion> listaColaboracionesTodos) {
		this.listaColaboracionesTodos = listaColaboracionesTodos;
	}
	
	
	@Command
    public void chooseItem() {
    	
        Events.postEvent(new ChooseEvent(this, chooseOne()));
    }
 

@Command
    public void unchooseItem() {
        Events.postEvent(new ChooseEvent(this, unchooseOne()));
    }
 

    @Command
    public void chooseAllItem() {

    	listaColaboracionesSeleccionados.addAll(listaColaboracionesTodos);
    	//chosenLb.setModel(listaColaboracionesSeleccionados);
    	listaColaboracionesTodos.clear();
        //candidateLb.setModel(listaColaboracionesTodos);
        
        
 
    }
 

    @Command
    public void unchooseAll() {

    	listaColaboracionesTodos.addAll(listaColaboracionesSeleccionados);
     //   candidateLb.setModel(listaColaboracionesTodos);
        listaColaboracionesSeleccionados.clear();
   //     chosenLb.setModel(listaColaboracionesSeleccionados);
    }
  
 
    /**
     * Set new candidate ListModelList.
     * 
     * @param candidate
     *            is the data of candidate list model
     */

 
    /**
     * @return current chosen data list
     */

 
    private Set<Colaboracion> chooseOne() {
        Set<Colaboracion> set = listaColaboracionesTodos.getSelection();
        listaColaboracionesSeleccionados.addAll(set);
        listaColaboracionesTodos.removeAll(set);
        return set;
    }
 
    private Set<Colaboracion> unchooseOne() {
        Set<Colaboracion> set = listaColaboracionesSeleccionados.getSelection();
        listaColaboracionesTodos.addAll(set);
        listaColaboracionesSeleccionados.removeAll(set);
        return set;
    }
 
    // Customized Event
    public class ChooseEvent extends Event {
        private static final long serialVersionUID = -7334906383953342976L;
 
        public ChooseEvent(Component target, Set<Colaboracion> data) {
            super("onChoose", target, data);
        }
    }
    
    @Command
    public void actualizarListas(){
    	if(eventoSelected != null){
			if(eventoSelected.getColaboracion() != null){
				if(listaColaboracionesSeleccionados.isEmpty()){
				for(Colaboracion colabora : eventoSelected.getColaboracion()){
				listaColaboracionesSeleccionados.add(colabora);
				}
				}
			}
		}
    }
    @Command
    public void actualiza(){
 	   if(colaboracionSelected.getCantidad() != null && colaboracionSelected.getPatrocinado().getRif() != null 
				&& colaboracionSelected.getTipoColaboracion().getNombre() != null){
    	
    	if(ServicioColaboracion.editColaboracion(colaboracionSelected)){
    	listaColaboracionesTodos.clear();
    	Clients.showNotification("Colaboracion Editada", null, null, null, 2000);
		if(listaColaboraciones != null){
		for(Colaboracion colaboracion : listaColaboraciones){
			
			listaColaboracionesTodos.add(colaboracion);
		}
		}
    	}else{
    		
        	Clients.showNotification("La colaboracion no se pudo guardar correctamente", null, null, null, 2000);
    	}
			
		
			
		}else{
			Clients.showNotification("No se permiten campos vacios", null, null, null, 2000);
		}
    
    }
    
    @Command
    public void cancelar(){
     
        patrocinadorSelected = null;
        tipoColaboracionSelected = null;
    	listaColaboracionesSeleccionados.clear();

    }
    @Command
    public void actualizar2(){
    	
    	if(colaboracionSelected.getCantidad() != null && colaboracionSelected.getPatrocinado() != null 
				&& colaboracionSelected.getTipoColaboracion().getNombre() != null && colaboracionSelected.getEvento() != null){
    	
    	
    	if(ServicioColaboracion.editColaboracion2(colaboracionSelected)){
    	listaColaboracionesTodos.clear();
    	Clients.showNotification("Colaboracion Editada", null, null, null, 2000);
		if(listaColaboraciones != null){
		for(Colaboracion colaboracion : listaColaboraciones){
			
			listaColaboracionesTodos.add(colaboracion);
		}
		}

    }
    else{
    	Clients.showNotification("No se pudo editar la Colaboracion", null, null, null, 2000);
    
 
    }
    	}else{
    		Clients.showNotification("No se permiten campos vacios", null, null, null, 2000);
    	}
    }

	public ListModelList<Evento> getListaEventoTodos() {
		return listaEventoTodos;
	}

	public void setListaEventoTodos(ListModelList<Evento> listaEventoTodos) {
		this.listaEventoTodos = listaEventoTodos;
	}
	
	
	
	@Command
	@NotifyChange("eventoSelected")
	public void terminarC(){
		Clients.showNotification("Colaboraciones agregadas al Evento", null, null, null, 2000);
		eventoSelected.setEstatus('L');
		if(ServicioEvento.cambiarEstatus(eventoSelected)){
			Clients.showNotification("Colaboraciones agregadas al Evento", null, null, null, 2000);
			listaEventos= ServicioEvento.buscarEventos();	
			listaEventoTodos.clear();
			for(Evento event : listaEventos){
				if(event.getEstatus().compareTo('V') == 0)
				listaEventoTodos.add(event);
			}
		 	
	}
		eventoSelected=null;
	}
	
    
}
