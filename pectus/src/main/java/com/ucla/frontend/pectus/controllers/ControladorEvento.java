package com.ucla.frontend.pectus.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.impl.Attribute;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Listbox;

import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.services.ServicioColaboracion;
import com.ucla.frontend.pectus.services.ServicioEvento;
import com.ucla.frontend.pectus.services.ServicioVoluntario;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;


public class ControladorEvento extends Div implements IdSpace{
	 private static final long serialVersionUID = 1183321127706483216L;
	private static final String footerMensaje = "Esto son todos los eventos";
	private EventoFilter eventoFilter = new EventoFilter();
	List<Evento> currentEvento = ServicioEvento.buscarEventos();
	List<Lugar> lugares = ServicioEvento.buscarLugares();
	ListModelList<Voluntario> currentVoluntario = ServicioVoluntario.buscarVoluntario();
	ListModelList<Voluntario> currentVoluntario2 = ServicioVoluntario.buscarVoluntario();
	ListModelList<Voluntario> listaVoluntariosSeleccionados = new ListModelList<Voluntario>();
	private Evento eventoSelected;
	ListModelList<Evento> currentEventoModel = new ListModelList<Evento>(); 
	private Lugar lugarSelected;
	private String nombreSelected;
	private String descripcionSelected;
	private String cantidadEntradasSelected;
	private String costoEntradasSelected;
	private String montoRecaudadoSelected;
	private String observacionesSelected;
	private static ControladorEvento instance;
    @Wire
    private Listbox candidateLb = new Listbox();
    @Wire
    private Listbox chosenLb = new Listbox();
    @Wire
    private Label actualiza = new Label();
    private ListModelList<Voluntario> chosenDataModel;
    private ListModelList<Voluntario> candidateModel;
    private String fechaMostrar;
    public ControladorEvento(){
    	if(currentEvento != null){
    		for(Evento event: currentEvento){
    			currentEventoModel.add(event);
    		}
    	
    	}
    	currentVoluntario = ServicioVoluntario.buscarVoluntario();
    	candidateModel = currentVoluntario;
    	if(Global.eventoSeleccionado != null){
    		eventoSelected = Global.eventoSeleccionado;
    		  SimpleDateFormat formatoFecha = new SimpleDateFormat(" EEEE dd 'de' MMMM 'del' yyyy ",new Locale("es_ES"));
    	
    		  this.setFechaMostrar(formatoFecha.format(eventoSelected.getFecha()));
    		
    	
    	if(Global.eventoSeleccionado.getVoluntarios() != null){
    	Set<Voluntario> voluntariosTransformacion = new HashSet<Voluntario>(Global.eventoSeleccionado.getVoluntarios());
    	ListModelList<Voluntario> listaSeleccionados = new ListModelList<Voluntario>();
    	listaSeleccionados.addAll(voluntariosTransformacion);
    	listaVoluntariosSeleccionados = listaSeleccionados;
    	chosenLb.setModel(listaVoluntariosSeleccionados);
    	//Set<Voluntario> voluntariosRemover = new HashSet<Voluntario>(Global.eventoSeleccionado.getVoluntarios());
    	List<Integer>  pos = new ArrayList<Integer>();
    	for(Voluntario vol : Global.eventoSeleccionado.getVoluntarios()){
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
	candidateLb.setModel(currentVoluntario);
    	
    	
    	
    	}
    	}

    }
	
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
	    @NotifyChange("eventoSelected")
	    public void asociarVoluntarios(@BindingParam("eventoSeleccionado") Evento evento) {
	       this.setEventoSelected(evento);
	   Global.eventoSeleccionado = evento;
	        currentVoluntario = filtrarListaVoluntario(currentVoluntario);
	    	

	        Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgAsociarVoluntariosEvento.zul", null, null);
		    
	        window.doModal();

	        
	        
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
			
		//   eventoSelected = new Evento();
		//   eventoSelected.setCantEntradas(Integer.parseInt(cantidadEntradasSelected));
		//   eventoSelected.setCostoEntrada(Double.parseDouble(costoEntradasSelected));
		//   eventoSelected.setDescripcion(descripcionSelected);
//		   eventoSelected.setFecha(new Date());
		 //  eventoSelected.setLugar(lugarSelected);
	//	   eventoSelected.setMontoRecaudado(Double.parseDouble(montoRecaudadoSelected));
	//	   eventoSelected.setNombre(nombreSelected);
		 //  eventoSelected.setObservacion(observacionesSelected);
		
		   
			response = ServicioEvento.agregarEvento(eventoSelected);
			if (response.equalsIgnoreCase("true"))
			{
			
				Clients.showNotification("Evento Guardado", null, true);
			//	x.detach(); ver esto mosca revisar
				currentEvento = ServicioEvento.buscarEventos();

			}else{
			
				Clients.showNotification("Error al guardar", true);
			
			}

	}



	public ListModelList<Voluntario> getCurrentVoluntario() {
		return currentVoluntario;
	}



	public void setCurrentVoluntario(ListModelList<Voluntario> currentVoluntario) {
		this.currentVoluntario = currentVoluntario;
	}
	
	
	// para la vista metodos

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

	    	listaVoluntariosSeleccionados.addAll(currentVoluntario);
	    	chosenLb.setModel(listaVoluntariosSeleccionados);
	        currentVoluntario.clear();
	        candidateLb.setModel(currentVoluntario);
	        
	        
	 
	    }
	 

	    @Command
	    public void unchooseAll() {

	        currentVoluntario.addAll(listaVoluntariosSeleccionados);
	        candidateLb.setModel(currentVoluntario);
	        listaVoluntariosSeleccionados.clear();
	        chosenLb.setModel(listaVoluntariosSeleccionados);
	    }
	  
	 
	    /**
	     * Set new candidate ListModelList.
	     * 
	     * @param candidate
	     *            is the data of candidate list model
	     */
	    public void setModel(List<Voluntario> candidate) {
	        candidateLb.setModel(this.candidateModel = new ListModelList<Voluntario>(candidate));
	        this.candidateModel.setMultiple(true);
	        chosenDataModel.clear();
	    }
	 
	    /**
	     * @return current chosen data list
	     */
	    public List<Voluntario> getChosenDataList() {
	        return new ArrayList<Voluntario>(chosenDataModel);
	    }
	 
	    private Set<Voluntario> chooseOne() {
	        Set<Voluntario> set = currentVoluntario.getSelection();
	        listaVoluntariosSeleccionados.addAll(set);
	        currentVoluntario.removeAll(set);
	        return set;
	    }
	 
	    private Set<Voluntario> unchooseOne() {
	        Set<Voluntario> set = listaVoluntariosSeleccionados.getSelection();
	        currentVoluntario.addAll(set);
	        listaVoluntariosSeleccionados.removeAll(set);
	        return set;
	    }
	 
	    // Customized Event
	    public class ChooseEvent extends Event {
	        private static final long serialVersionUID = -7334906383953342976L;
	 
	        public ChooseEvent(Component target, Set<Voluntario> data) {
	            super("onChoose", target, data);
	        }
	    }

		public ListModelList<Voluntario> getListaVoluntariosSeleccionados() {
			return listaVoluntariosSeleccionados;
		}

		public void setListaVoluntariosSeleccionados(
				ListModelList<Voluntario> listaVoluntariosSeleccionados) {
			this.listaVoluntariosSeleccionados = listaVoluntariosSeleccionados;
		}
	
@Command
	public void asociarVoluntariosGuardar(){

		if(listaVoluntariosSeleccionados != null){
			for(Voluntario voluntario : listaVoluntariosSeleccionados){
				if(!Global.eventoSeleccionado.getVoluntarios().contains(voluntario)){
				
			ServicioEvento.asociarVoluntarios(Global.eventoSeleccionado.getId(), voluntario.getCedula());
			Global.eventoSeleccionado.getVoluntarios().add(voluntario);
				}
			
			}
			Clients.showNotification("Voluntarios asociados correctamente", null, true);
			
		}
		
		
	}
@Command
public void prueba(){
	chosenLb.setModel(listaVoluntariosSeleccionados);
}

public ListModelList<Voluntario> filtrarListaVoluntario(ListModelList<Voluntario> listaVoluntario){
	
	//ListModelList<Voluntario> listaAux = new ListModelList<Voluntario>();
	if(Global.eventoSeleccionado != null){
		if(Global.eventoSeleccionado.getVoluntarios() != null){
			
			ListModelList<Voluntario> listaVoluntario2 = new ListModelList<Voluntario>();
				Set<Voluntario> voluntariosRemover = new HashSet<Voluntario>(Global.eventoSeleccionado.getVoluntarios());
				listaVoluntario.remove(voluntariosRemover);
		
				
				if(candidateLb != null)
				candidateLb.setModel(listaVoluntario);
	
		}
	}
	return listaVoluntario;
	
	
	
}

public ListModelList<Evento> getCurrentEventoModel() {
	return currentEventoModel;
}

public void setCurrentEventoModel(ListModelList<Evento> currentEventoModel) {
	this.currentEventoModel = currentEventoModel;
}

public static ControladorEvento getInstance() {
	if(instance == null){
		instance = new ControladorEvento();
	}
	return instance;
}

public static void setInstance(ControladorEvento instance) {
	ControladorEvento.instance = instance;
}

public String getFechaMostrar() {
	return fechaMostrar;
}

public void setFechaMostrar(String fechaMostrar) {
	this.fechaMostrar = fechaMostrar;
}

	

}
