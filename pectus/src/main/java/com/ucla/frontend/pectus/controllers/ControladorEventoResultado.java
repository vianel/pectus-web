package com.ucla.frontend.pectus.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import com.ucla.frontend.pectus.controllers.ColaboracionView.ChooseEvent;
import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Patrocinador;
import com.ucla.frontend.pectus.models.TipoColaboracion;
import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.services.ServicioColaboracion;
import com.ucla.frontend.pectus.services.ServicioEvento;
import com.ucla.frontend.pectus.services.ServicioVoluntario;

public class ControladorEventoResultado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1544551455L;
	private List<Evento> currentEvento = ServicioEvento.buscarEventos();
	private ListModelList<Evento> listaEventoTodos = new ListModelList<Evento>();
	private List<Colaboracion> currentColaboracion = ServicioColaboracion.buscarColaboraciones();
	private ListModelList<Colaboracion> listaColaboracionesTodos = new ListModelList<Colaboracion>();
	private ListModelList<Voluntario> currentVoluntario = ServicioVoluntario.buscarVoluntario();
	private ListModelList<Colaboracion> listaColaboracionesSeleccionados = new ListModelList<Colaboracion>();;
	private ListModelList<Voluntario> listaVoluntariosSeleccionados = new ListModelList<Voluntario>();
	private boolean entro = false;
	private Patrocinador patrocinadorSelected;
	private TipoColaboracion tipoColaboracionSelected;
	private String cantidadSelected;
	List<Patrocinador> listaPatrocinadores = ServicioColaboracion.buscarPatrocinadores();
	List<TipoColaboracion> listaTipoColaboraciones = ServicioColaboracion.buscarTipoColaboracion();
	
	private Evento eventoSelected;
	public ControladorEventoResultado() {
		super();
		// TODO Auto-generated constructor stub
		if(currentEvento != null){
		for(Evento evento : currentEvento){
			listaEventoTodos.add(evento);
		}
		}
		if(currentColaboracion != null){
			for(Colaboracion colaboracion : currentColaboracion){
				listaColaboracionesTodos.add(colaboracion);
			}
		}
	}
	


	public List<Evento> getCurrentEvento() {
		if(currentEvento == null){
			currentEvento = new ArrayList<Evento>();
		}
		return currentEvento;
	}

	public void setCurrentEvento(List<Evento> currentEvento) {
		this.currentEvento = currentEvento;
	}

	public Evento getEventoSelected() {
		return eventoSelected;
	}

	public void setEventoSelected(Evento eventoSelected) {
		this.eventoSelected = eventoSelected;
	}



	public ListModelList<Evento> getListaEventoTodos() {
		return listaEventoTodos;
	}



	public void setListaEventoTodos(ListModelList<Evento> listaEventoTodos) {
		this.listaEventoTodos = listaEventoTodos;
	}



	public List<Colaboracion> getCurrentColaboracion() {
		return currentColaboracion;
	}



	public void setCurrentColaboracion(List<Colaboracion> currentColaboracion) {
		this.currentColaboracion = currentColaboracion;
	}



	public ListModelList<Colaboracion> getListaColaboracionesTodos() {
		return listaColaboracionesTodos;
	}



	public void setListaColaboracionesTodos(
			ListModelList<Colaboracion> listaColaboracionesTodos) {
		this.listaColaboracionesTodos = listaColaboracionesTodos;
	}



	public ListModelList<Voluntario> getCurrentVoluntario() {
		return currentVoluntario;
	}



	public void setCurrentVoluntario(ListModelList<Voluntario> currentVoluntario) {
		this.currentVoluntario = currentVoluntario;
	}



	public ListModelList<Colaboracion> getListaColaboracionesSeleccionados() {
		return listaColaboracionesSeleccionados;
	}



	public void setListaColaboracionesSeleccionados(
			ListModelList<Colaboracion> listaColaboracionesSeleccionados) {
		this.listaColaboracionesSeleccionados = listaColaboracionesSeleccionados;
	}



	public ListModelList<Voluntario> getListaVoluntariosSeleccionados() {
		return listaVoluntariosSeleccionados;
	}



	public void setListaVoluntariosSeleccionados(
			ListModelList<Voluntario> listaVoluntariosSeleccionados) {
		this.listaVoluntariosSeleccionados = listaVoluntariosSeleccionados;
		
	}
	
	 @Command
	    public void actualizarListas(){
		 currentVoluntario.clear();
		 ListModelList<Voluntario> listaAux = new  ListModelList<Voluntario>();
		 listaAux = ServicioVoluntario.buscarVoluntario();
		 currentVoluntario.addAll(listaAux);
		 listaVoluntariosSeleccionados.clear();            
	    	if(eventoSelected != null){
				if(eventoSelected.getColaboracion() != null){
					if(listaColaboracionesSeleccionados.isEmpty()){
					for(Colaboracion colabora : eventoSelected.getColaboracion()){
					listaColaboracionesSeleccionados.add(colabora);
					}
					}
				}
				
				
				
				if(eventoSelected.getVoluntarios() != null){
				
						for(Voluntario voluntario : eventoSelected.getVoluntarios()){
							listaVoluntariosSeleccionados.add(voluntario);
						}
					
				}
				
				
		    	List<Integer>  pos = new ArrayList<Integer>();
		    	for(Voluntario vol : eventoSelected.getVoluntarios()){
		    	for(int i = 0 ; i < currentVoluntario.size(); i++){
		    		if(currentVoluntario.get(i).getCedula().compareToIgnoreCase(vol.getCedula()) == 0){
		    			pos.add(i);
		    		}
		    	}
		    	}
		    	Collections.reverse(pos);
		    for(int posi : pos){
		    	currentVoluntario.remove(posi);
		    }
				setEntro(true);
			
				
				
				
			}
	    }
	    @Command
	    public void guardarAsociar(){
	    	if(listaColaboracionesSeleccionados != null){
	    	for(Colaboracion colaboracion : listaColaboracionesSeleccionados){
	    		if(!eventoSelected.getColaboracion().contains(colaboracion)){
	    			colaboracion.setEvento(eventoSelected);
	    			
	    			ServicioColaboracion.agregarColaboracion(colaboracion);
	    			Clients.showNotification("Colaboraciones registradas correctamente", null, true);
	    		}
	    		
	    	}
	    	}
	    }
	    
	    @Command
	    public void cancelar(){
	    
	    	listaColaboracionesSeleccionados.clear();
	    	

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
	 
	        public ChooseEvent(ControladorEventoResultado controladorEventoResultado, Set<Colaboracion> data) {
	            super("onChoose");
	        }
	    }
	    
		@Command
	    public void chooseItem2() {
	    	
	        Events.postEvent(new ChooseEvent2(this, chooseOne2()));
	    }
	 

	@Command
	    public void unchooseItem2() {
	        Events.postEvent(new ChooseEvent2(this, unchooseOne2()));
	    }
	 

	    @Command
	    public void chooseAllItem2() {

	    	listaVoluntariosSeleccionados.addAll(currentVoluntario);
	    	//chosenLb.setModel(listaColaboracionesSeleccionados);
	    	currentVoluntario.clear();
	        //candidateLb.setModel(listaColaboracionesTodos);
	        
	        
	 
	    }
	 

	    @Command
	    public void unchooseAll2() {

	    	currentVoluntario.addAll(listaVoluntariosSeleccionados);
	     //   candidateLb.setModel(listaColaboracionesTodos);
	        listaVoluntariosSeleccionados.clear();
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

	 
	    private Set<Voluntario> chooseOne2() {
	        Set<Voluntario> set = currentVoluntario.getSelection();
	        listaVoluntariosSeleccionados.addAll(set);
	        currentVoluntario.removeAll(set);
	        return set;
	    }
	 
	    private Set<Voluntario> unchooseOne2() {
	        Set<Voluntario> set = listaVoluntariosSeleccionados.getSelection();
	        currentVoluntario.addAll(set);
	        listaVoluntariosSeleccionados.removeAll(set);
	        return set;
	    }
	 
	    public boolean isEntro() {
			return entro;
		}



		public void setEntro(boolean entro) {
			this.entro = entro;
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



		public void setTipoColaboracionSelected(TipoColaboracion tipoColaboracionSelected) {
			this.tipoColaboracionSelected = tipoColaboracionSelected;
		}

		public String getCantidadSelected() {
			return cantidadSelected;
		}



		public void setCantidadSelected(String cantidadSelected) {
			this.cantidadSelected = cantidadSelected;
		}

		// Customized Event
	    public class ChooseEvent2 extends Event {
	        private static final long serialVersionUID = -7334944383953342976L;
	 
	        public ChooseEvent2(ControladorEventoResultado controladorEventoResultado, Set<Voluntario> data) {
	            super("onChoose");
	        }
	    }

		public List<Patrocinador> getListaPatrocinadores() {
			return listaPatrocinadores;
		}



		public void setListaPatrocinadores(List<Patrocinador> listaPatrocinadores) {
			this.listaPatrocinadores = listaPatrocinadores;
		}



		public List<TipoColaboracion> getListaTipoColaboraciones() {
			return listaTipoColaboraciones;
		}



		public void setListaTipoColaboraciones(
				List<TipoColaboracion> listaTipoColaboraciones) {
			this.listaTipoColaboraciones = listaTipoColaboraciones;
		}
	    
	    
	    

}
