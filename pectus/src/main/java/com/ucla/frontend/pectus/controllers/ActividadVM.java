package com.ucla.frontend.pectus.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ListModel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.models.ActividadRechazada;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.MotivoRechazo;
import com.ucla.frontend.pectus.models.SolicitudActividad;
import com.ucla.frontend.pectus.models.TipoActividad;
import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.reports.TipoestudioReportes;
import com.ucla.frontend.pectus.services.ServicioActividad;
import com.ucla.frontend.pectus.services.ServicioCiudad;
import com.ucla.frontend.pectus.services.ServicioEvento;
import com.ucla.frontend.pectus.services.ServicioSolicitudAyuda;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;
import com.ucla.frontend.pectus.services.ServicioVoluntario;
import com.ucla.frontend.pectus.utils.Email;
public class ActividadVM {
	private ActividadRechazada actividadReSelected;
	private List<Lugar> listalugar;
	private Lugar lugarSelected;
	private Actividad actividadSelected;
	private SolicitudActividad solactividadSelected;
	private String descripcionSelected;
	private String nombresolicitanteSelected;
	private String tlfsolicitanteSelected;
    private static final String footerMensajeactividad = "Estas son todas los Actividades";
	private ActividadFilter actividadFilter = new ActividadFilter();
	private SolicitudActividadFilter solicitudActividadFilter = new SolicitudActividadFilter();
	static List<Actividad> currentActividad;
	static List<Voluntario> currentVoluntario;
	static List<SolicitudActividad> currentSolActividad;
	private List<TipoActividad> listatipoactividad;
	private TipoActividad tipoactividadSelected;
	private SolicitudActividad solicitudactividadSelected;
	private ListModelList<Actividad> listaActividadesV = new ListModelList<Actividad>();
	private ListModelList<Actividad> listaActividadesT = new ListModelList<Actividad>();
    private ListModelList<Actividad> listaActividadesC = new ListModelList<Actividad>();
	private int asistentesesperadosSelected;
	private Email email;
	private Date fechainicioSelected;
	private String montoesperadoSelected;
	private int duracionSelected;
	private Date fechafinSelected;
	private Date horaSelected;
	private String descripcionactSelected;
	private List<Voluntario> voluntarios;
	private ListModelList<Voluntario> listaVoluntariosSeleccionados = new ListModelList<Voluntario>();
	private Voluntario voluntarioSelected;
	private List<Voluntario> auxvoluntario;
	private String tituloSelected;
	private String montoSelected;
	private String nroasistentesSelected;
	private String observacionesSelected;
	private ListModelList<SolicitudActividad> listaSolicitudes = new ListModelList<SolicitudActividad>();
	List<MotivoRechazo> listaMotivos = ServicioActividad.buscarMotivoRechazoTodos();
	
	
	@Init
	public void init(){
		try{
		email = new Email();
		}catch(Exception e){
			
		}
		this.currentActividad = ServicioActividad.buscaractividades();
		actividadReSelected = new ActividadRechazada();
List<MotivoRechazo> listaAux = new ArrayList<MotivoRechazo>();
for(MotivoRechazo mot : listaMotivos){
	if(mot.getTipo().compareToIgnoreCase("A") == 0){
		listaAux.add(mot);
	}
}
	listaMotivos = listaAux;	
		
		this.currentSolActividad = ServicioActividad.buscarsolicitudactividades();
		
		
		for(SolicitudActividad solicitudActividad : currentSolActividad){
			if(solicitudActividad.getEstatus().compareTo('S') == 0){
				this.listaSolicitudes.add(solicitudActividad);
			}
		
		}
		
		if(currentActividad != null){
		for(Actividad actividad : currentActividad){
			if(actividad.getEstatus().compareTo('C') == 0){
				listaActividadesC.add(actividad);
			}
		}
		}
		
		if(currentActividad != null){
			for(Actividad actividad : currentActividad){
				if(actividad.getEstatus().compareTo('V') == 0){
					listaActividadesV.add(actividad);
				}
			}
			}
		if(currentActividad != null){
			for(Actividad actividad : currentActividad){
				if(actividad.getEstatus().compareTo('R') == 0){
					listaActividadesT.add(actividad);
				}
			}
			}
			
		
		
		
		this.voluntarios = ServicioVoluntario.buscarVoluntario();
	}
	@Command
	@NotifyChange({"listalugar", "listatipoactividad","currentActividad"})
	public void inicializarAtributos() throws Exception{

		listalugar = ServicioEvento.buscarLugares();
		listatipoactividad = ServicioActividad.buscartipoactividades();
		
		
	}
	
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void seleccionarunvoluntario()
	{
		if (voluntarioSelected != null)
		{
		currentVoluntario.add(voluntarioSelected);
		voluntarios.remove(voluntarioSelected);
		}
	}
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void removerunvoluntario()
	{
		if (voluntarioSelected != null)
		{
		currentVoluntario.remove(voluntarioSelected);
		voluntarios.add(voluntarioSelected);
		}
	}
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void removertodosvoluntario()
	{

		
		for (Voluntario temp : currentVoluntario) {
			voluntarios.add(temp);
		}
		currentVoluntario.clear();
	}
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void seleccionartodosvoluntario()
	{

		
		for (Voluntario temp : voluntarios) {
			currentVoluntario.add(temp);
		}
		voluntarios.clear();
	}
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void deshacerAsignacionvoluntarios()
	{
		if (voluntarioSelected != null){
		currentVoluntario.clear(); 
		for (Voluntario temp : auxvoluntario) {
			currentVoluntario.add(temp);
		}
		voluntarios = ServicioVoluntario.buscarVoluntario();
		}
		
	}
	@Command
	@NotifyChange("listaActividadesC")
	public void asociarVoluntariosGuardar()
	{
		
    	String response = ServicioActividad.asignarvoluntario(currentVoluntario,actividadSelected);
		if (response.equalsIgnoreCase("true"))
		{
		  	Clients.showNotification("Voluntarios Asignados", null, null, null, 2000);	
		
			buscarvoluntariosasignados();
			listaActividadesC.clear();
			if(currentActividad != null){
				for(Actividad actividad : currentActividad){
					if(actividad.getEstatus().compareTo('C') == 0){
						listaActividadesC.add(actividad);
					}
				}
				}

		}else{
			Clients.showNotification("Error", true);
		}
		
	}
	
	public ActividadFilter getActividadFilter(){
		return actividadFilter;
	}
	
	public List<Actividad> getmodelactividad(){
		return new ListModelList<Actividad>(currentActividad);
	
	}
	
	public List<Voluntario> getmodelvoluntario()
	{
		return currentVoluntario;
	
	}
	
	public List<Voluntario> getmodelvoluntarios()
	{
		return voluntarios;
	}
	
	

	
	public String getfooter(){
		return String.format(footerMensajeactividad, currentActividad.size());
	}
    @Command
    @NotifyChange({"modelactividad", "footerMensajeactividad"})
    public void changeFilteractividad() {
        currentActividad = actividadFilter.getFilteractividades(actividadFilter);
    }
    
    @Command
    public void abrirDialogoRegistrarSolicitudActividad()
    {
        Window window = (Window)Executions.createComponents(
                "/vistas/dialogos/dlgRegistrarSolicitudActividad.zul", null, null);
        window.doModal();
    }

    @Command
    public void showRegistroresultado(Event e) {
        //create a window programmatically and use it as a modal dialog.
    	
		        Window window = (Window)Executions.createComponents(
		        		
		                "/vistas/gestionactividades/registroresultadoactividad.zul", null, null);
		        window.doModal();
		//       window.setTitle(lblseleccion.getValue().toString());
		    	System.out.println(actividadSelected.getDescripcion());
    	
    }
    @Command
    @NotifyChange({"asistentesesperadosSelected", "montoesperadoSelected", "duracionSelected", "fechainicioSelected", "fechafinSelected", "lugarSelected", "tituloSelected"})
    public void cancelarRegistro(){
    	  asistentesesperadosSelected = 0;
		   montoesperadoSelected= null;
		   duracionSelected = 0;
		   fechainicioSelected = null; 
		   fechafinSelected= null;
		   lugarSelected = null; 
		   tituloSelected = null;
    }
    
    
    
    @Command
    @NotifyChange({"solactividadSelected", "modelactividad", "modelsolactividad", "asistentesesperadosSelected", "montoesperadoSelected", "duracionSelected", "fechainicioSelected", "fechafinSelected", "lugarSelected", "tituloSelected"})
    public void registroActividad()
    {
    	
    	 Actividad act = new Actividad();
    	 act.setTitulo(tituloSelected);
    	 act.setLugar(lugarSelected);
    	 act.setIdSolicitudActividad(solactividadSelected);
    	 act.setFechainicio(fechainicioSelected);
    	 act.setFechafin(fechafinSelected);
    	// act.setHora(horaSelected.toString());
    	
    	 act.setDuracion(Integer.toString(duracionSelected));
    	 act.setDescripcion(solactividadSelected.getDescripcion());
    	 act.setMontoesperado(Integer.parseInt(montoesperadoSelected));
    	 act.setNroasistentesesperados(asistentesesperadosSelected);
    	 act.setEstatus('C');
    	 solactividadSelected.setEstatus('A');
    	 ServicioActividad.cambiarEstatusSolicitud(solactividadSelected);
    	 

 				if (ServicioActividad.agregaractividad(act)){ 
 			

 		   	Clients.showNotification("Actividad Aprobada", null, null, null, 2000);	
 		   	
 		   asistentesesperadosSelected = 0;
 		   montoesperadoSelected= null;
 		   duracionSelected = 0;
 		   fechainicioSelected = null; 
 		   fechafinSelected= null;
 		   lugarSelected = null; 
 		   tituloSelected = null;
 		   	
 		  solactividadSelected = null;
 		   	
 		  listaSolicitudes.clear();
 		  for(SolicitudActividad solicitudActividad : currentSolActividad){
 					if(solicitudActividad.getEstatus().compareTo('S') == 0){
 						this.listaSolicitudes.add(solicitudActividad);
 					}
 				
 				}
 			//currentSolActividad.remove(solactividadSelected);
 
 			

 		}else{
 			Clients.showNotification("Error", true);
 		}
    	 
   	 
    	
    }
    
    
    
    @Command
    @NotifyChange({"modelactividad", "modelsolactividad", "listaSolicitudes", "solactividadSelected"})
    public void registroActividadRechazar()
    {
    	
   
    	
    	 actividadReSelected.setSolicitudActividad(solactividadSelected);
    	 
    	// act.setHora(horaSelected.toString());
    	

    	 solactividadSelected.setEstatus('R');
    	 ServicioActividad.cambiarEstatusSolicitud(solactividadSelected);
    	 

 				if (ServicioActividad.rechazarActividad(actividadReSelected)){ 
 			

 		   	Clients.showNotification("Actividad Rechazada", null, null, null, 2000);
 		   listaSolicitudes.clear();
 		   for(SolicitudActividad solicitudActividad : currentSolActividad){
 					if(solicitudActividad.getEstatus().compareTo('S') == 0){
 						this.listaSolicitudes.add(solicitudActividad);
 					}
 		   }
 
 			
 			
 		  solactividadSelected = null;
 		//	currentSolActividad.remove(solactividadSelected);
 
 			

 		}else{
 			Clients.showNotification("Error", true);
 		}
    	 
   	 
    	
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
	@Command
    @NotifyChange({"modelactividad", "modelvoluntario"})
    public void concluirasignacion()
    {
	    if (actividadSelected != null)
	    {
	    	actividadSelected.setEstatus('V');
	    	 ServicioActividad.cambiarEstatusActividad(actividadSelected);
	    	
	        	currentActividad.remove(actividadSelected);
	        	actividadSelected = null;
	        	currentVoluntario.clear();
	        	Clients.showNotification("Actividad Concluida", null, null, null, 2000);	
	  
	    
	    	    
	    }else {
        	Clients.showNotification("Debe seleccionar una Actividad", null, null, null, 2000);
	 
	    }
    }
    



    @Command
    @NotifyChange("listaActividadesV")
    public void registroresultadoActividad()
    {
    	
    	
    	actividadSelected.setMonto(Float.parseFloat(montoSelected));
    	actividadSelected.setNroAsistentes(Integer.parseInt(nroasistentesSelected));
    	actividadSelected.setObservaciones(observacionesSelected);
     	String response = ServicioActividad.modificaractividad(actividadSelected);
     	actividadSelected.setEstatus('R');
         ServicioActividad.cambiarEstatusActividad(actividadSelected);
 		if (response.equalsIgnoreCase("true"))
 		{
 			Clients.showNotification("Resultados Registrados", null, null, null, 2000);
 			
 			terminarResultado();

 		}else{
 			Clients.showNotification("Error al guardar", null, null, null, 2000);
 		}
 	
    }

    @Command
    @NotifyChange("modelvoluntario")
    public void buscarvoluntariosasignados()
    {

    	currentVoluntario = ServicioActividad.buscarvoluntariosactividad(actividadSelected);
    	auxvoluntario = ServicioActividad.buscarvoluntariosactividad(actividadSelected);
    	
    }
    @Command
    @NotifyChange({"solicitudActividad", "tipoactividadSelected", "descripcionSelected", "nombresolicitanteSelected", "tlfsolicitanteSelected"})
    public void cancelarSolicitud(){
    	tipoactividadSelected = null;
		descripcionSelected = null;
		nombresolicitanteSelected = null;
		tlfsolicitanteSelected = null;
    }
    
    @Command
    @NotifyChange({"solicitudActividad", "tipoactividadSelected", "descripcionSelected", "nombresolicitanteSelected", "tlfsolicitanteSelected"})
    public void registroSolicitudActividad()
    {
    
 	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 	   Date date = new Date();
    	SolicitudActividad SOL = new SolicitudActividad(null, tipoactividadSelected, descripcionSelected, date, nombresolicitanteSelected, tlfsolicitanteSelected, 'S');
    	SOL.setEstatus('S');
    	String response = ServicioActividad.agregarsolicitudactividad(SOL);
		if (response.equalsIgnoreCase("true"))
		{
 			Clients.showNotification("Solicitud Guardada Guardado", null, null, null, 2000);
	
			
 			
 			
 			
 			
			

		}else{
        	Clients.showNotification("Error al guardar", null, null, null, 2000);
		
		}
	
		tipoactividadSelected = null;
		descripcionSelected = null;
		nombresolicitanteSelected = null;
		tlfsolicitanteSelected = null;
	
    }

    @Command
    public void modificarSolicitudActividad()
    {
    	String response = ServicioActividad.modificarsolicitudactividad(solicitudactividadSelected);
		if (response.equalsIgnoreCase("true"))
		{
			
			Clients.showNotification("Solicitud Modificada", null, null, null, 2000);
		
			
			

		}else{
			Clients.showNotification("Error al modificar", null, null, null, 2000);
		
		}
    }
    public List<SolicitudActividad> getmodelsolactividad() {
    	return new ListModelList<SolicitudActividad>(currentSolActividad);
      }
    
    @Command
    @NotifyChange({"modelsolactividad", "footer"})
    public void changeFilter() {
    	
    	currentSolActividad = SolicitudActividadFilter.getFilterSolicitudActiviad(solicitudActividadFilter);
    }
    
    
    public void showResultadoactividad()
    {
    	
    }
	public List<Lugar> getListalugar() {
		return listalugar;
	}

	public void setListalugar(List<Lugar> listalugar) {
		this.listalugar = listalugar;
	}

	public Lugar getLugarSelected() {
		return lugarSelected;
	}

	public void setLugarSelected(Lugar lugarSelected) {
		this.lugarSelected = lugarSelected;
	}



	public static List<Actividad> getCurrentActividad() {
		return currentActividad;
	}



	public static List<Voluntario> getCurrentVoluntario() {
		return currentVoluntario;
	}
	public static void setCurrentVoluntario(List<Voluntario> currentVoluntario) {
		ActividadVM.currentVoluntario = currentVoluntario;
	}
	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}
	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}
	public static void setCurrentActividad(List<Actividad> currentActividad) {
		ActividadVM.currentActividad = currentActividad;
	}



	public String getDescripcionSelected() {
		return descripcionSelected;
	}



	public void setDescripcionSelected(String descripcionSelected) {
		this.descripcionSelected = descripcionSelected;
	}



	public String getNombresolicitanteSelected() {
		return nombresolicitanteSelected;
	}



	public void setNombresolicitanteSelected(String nombresolicitanteSelected) {
		this.nombresolicitanteSelected = nombresolicitanteSelected;
	}



	public String getTlfsolicitanteSelected() {
		return tlfsolicitanteSelected;
	}



	public void setTlfsolicitanteSelected(String tlfsolicitanteSelected) {
		this.tlfsolicitanteSelected = tlfsolicitanteSelected;
	}


	public TipoActividad getTipoactividadSelected() {
		return tipoactividadSelected;
	}


	public void setTipoactividadSelected(TipoActividad tipoactividadSelected) {
		this.tipoactividadSelected = tipoactividadSelected;
	}


	public List<TipoActividad> getListatipoactividad() {
		return listatipoactividad;
	}


	public Voluntario getVoluntarioSelected() {
		return voluntarioSelected;
	}
	public void setVoluntarioSelected(Voluntario voluntarioSelected) {
		this.voluntarioSelected = voluntarioSelected;
	}
	public void setListatipoactividad(List<TipoActividad> listatipoactividad) {
		this.listatipoactividad = listatipoactividad;
	}
	public SolicitudActividad getSolicitudactividadSelected() {
		return solicitudactividadSelected;
	}
	public void setSolicitudactividadSelected(
			SolicitudActividad solicitudactividadSelected) {
		this.solicitudactividadSelected = solicitudactividadSelected;
	}
	public SolicitudActividadFilter getSolicitudActividadFilter() {
		return solicitudActividadFilter;
	}
	public void setSolicitudActividadFilter(
			SolicitudActividadFilter solicitudActividadFilter) {
		this.solicitudActividadFilter = solicitudActividadFilter;
	}
	public Actividad getActividadSelected() {
		return actividadSelected;
	}
	public void setActividadSelected(Actividad actividadSelected) {
		this.actividadSelected = actividadSelected;
	}
	public SolicitudActividad getSolactividadSelected() {
		return solactividadSelected;
	}
	public void setSolactividadSelected(SolicitudActividad solactividadSelected) {
		this.solactividadSelected = solactividadSelected;
	}

	public int getAsistentesesperadosSelected() {
		return asistentesesperadosSelected;
	}
	public void setAsistentesesperadosSelected(int asistentesesperadosSelected) {
		this.asistentesesperadosSelected = asistentesesperadosSelected;
	}
	public Date getFechainicioSelected() {
		return fechainicioSelected;
	}
	public void setFechainicioSelected(Date fechainicioSelected) {
		this.fechainicioSelected = fechainicioSelected;
	}
	public String getMontoesperadoSelected() {
		return montoesperadoSelected;
	}
	public void setMontoesperadoSelected(String montoesperadoSelected) {
		this.montoesperadoSelected = montoesperadoSelected;
	}
	public int getDuracionSelected() {
		return duracionSelected;
	}
	public void setDuracionSelected(int duracionSelected) {
		this.duracionSelected = duracionSelected;
	}
	public Date getFechafinSelected() {
		return fechafinSelected;
	}
	public void setFechafinSelected(Date fechafinSelected) {
		this.fechafinSelected = fechafinSelected;
	}
	public Date getHoraSelected() {
		return horaSelected;
	}
	public void setHoraSelected(Date horaSelected) {
		this.horaSelected = horaSelected;
	}
	public String getDescripcionactSelected() {
		return descripcionactSelected;
	}
	public void setDescripcionactSelected(String descripcionactSelected) {
		this.descripcionactSelected = descripcionactSelected;
	}
	public ListModelList<Voluntario> getListaVoluntariosSeleccionados() {
		return listaVoluntariosSeleccionados;
	}
	public void setListaVoluntariosSeleccionados(
			ListModelList<Voluntario> listaVoluntariosSeleccionados) {
		this.listaVoluntariosSeleccionados = listaVoluntariosSeleccionados;
	}
	public String getTituloSelected() {
		return tituloSelected;
	}
	public void setTituloSelected(String tituloSelected) {
		this.tituloSelected = tituloSelected;
	}
	public String getMontoSelected() {
		return montoSelected;
	}
	public void setMontoSelected(String montoSelected) {
		this.montoSelected = montoSelected;
	}
	public String getNroasistentesSelected() {
		return nroasistentesSelected;
	}
	public void setNroasistentesSelected(String nroasistentesSelected) {
		this.nroasistentesSelected = nroasistentesSelected;
	}
	public String getObservacionesSelected() {
		return observacionesSelected;
	}
	public void setObservacionesSelected(String observacionesSelected) {
		this.observacionesSelected = observacionesSelected;
	}
	
	
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void cancelar(){
		 voluntarios.clear();
		 ListModelList<Voluntario> listaAux = new  ListModelList<Voluntario>();
		 listaAux = ServicioVoluntario.buscarVoluntario();
		 voluntarios.addAll(listaAux);
		 currentVoluntario.clear();            
			if(actividadSelected != null){
			
				
				
				List<Voluntario> voluntariosAct = ServicioActividad.buscarvoluntariosactividad(actividadSelected);
				if(voluntariosAct != null){
				
						for(Voluntario voluntario : voluntariosAct){
							currentVoluntario.add(voluntario);
						}
					
				}
				
				
		    	List<Integer>  pos = new ArrayList<Integer>();
		    	for(Voluntario vol : voluntariosAct){
		    	for(int i = 0 ; i < voluntarios.size(); i++){
		    		if(voluntarios.get(i).getCedula().compareToIgnoreCase(vol.getCedula()) == 0){
		    			pos.add(i);
		    		}
		    	}
		    	}
		    	Collections.reverse(pos);
		    for(int posi : pos){
		    	voluntarios.remove(posi);
		    }
		
			
			}
		

	}
	@Command
	@NotifyChange({"modelsolactividad"})
	public void filtrarListaCreada(){
		
		
	}

	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios"})
	public void actualizarListas(){
	 voluntarios.clear();
	 ListModelList<Voluntario> listaAux = new  ListModelList<Voluntario>();
	 listaAux = ServicioVoluntario.buscarVoluntario();
	 voluntarios.addAll(listaAux);
	 currentVoluntario.clear();            
		if(actividadSelected != null){
		
			
			
			List<Voluntario> voluntariosAct = ServicioActividad.buscarvoluntariosactividad(actividadSelected);
			if(voluntariosAct != null){
			
					for(Voluntario voluntario : voluntariosAct){
						currentVoluntario.add(voluntario);
					}
				
			}
			
			
	    	List<Integer>  pos = new ArrayList<Integer>();
	    	for(Voluntario vol : voluntariosAct){
	    	for(int i = 0 ; i < voluntarios.size(); i++){
	    		if(voluntarios.get(i).getCedula().compareToIgnoreCase(vol.getCedula()) == 0){
	    			pos.add(i);
	    		}
	    	}
	    	}
	    	Collections.reverse(pos);
	    for(int posi : pos){
	    	voluntarios.remove(posi);
	    }
	
		
		}
	}
	public ListModelList<Actividad> getListaActividadesV() {
		return listaActividadesV;
	}
	public void setListaActividadesV(ListModelList<Actividad> listaActividadesV) {
		this.listaActividadesV = listaActividadesV;
	}
	public ListModelList<Actividad> getListaActividadesT() {
		return listaActividadesT;
	}
	public void setListaActividadesT(ListModelList<Actividad> listaActividadesT) {
		this.listaActividadesT = listaActividadesT;
	}
	public ListModelList<Actividad> getListaActividadesC() {
		return listaActividadesC;
	}
	public void setListaActividadesC(ListModelList<Actividad> listaActividadesC) {
		this.listaActividadesC = listaActividadesC;
	}
	public ListModelList<SolicitudActividad> getListaSolicitudes() {
		return listaSolicitudes;
	}
	public void setListaSolicitudes(
			ListModelList<SolicitudActividad> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}
	
	@Command
	@NotifyChange({"modelvoluntario", "modelvoluntarios", "listaActividadesC"})
	public void terminar(){
		actividadSelected.setEstatus('V');
		ServicioActividad.cambiarEstatusActividad(actividadSelected);
		
		
		
		for(Voluntario voluntario : currentVoluntario){
		email.llenarCabecera(voluntario.getCorreo(), "Eventos Pectus", "Estos son los datos del evento en la cual usted participara: Evento: "
				+ actividadSelected.getTitulo() + " " + actividadSelected.getDescripcion() + " " + actividadSelected.getLugar().getNombre() + " " + actividadSelected.getLugar().getDireccion()
				);
		
		if(email.sendMail()){
			
		}
		Clients.showNotification("No puede asociar mas voluntarios", null, null, null, 2000);
		}
		
		
		
		listaActividadesC.clear();
		if(currentActividad != null){
			for(Actividad actividad : currentActividad){
				if(actividad.getEstatus().compareTo('C') == 0){
					listaActividadesC.add(actividad);
				}
			}
			}
	}
	
	@Command
	@NotifyChange({"listaActividadesV", "actividadReSelected"})
	public void terminarResultado(){
		actividadSelected.setEstatus('R');
		ServicioActividad.cambiarEstatusActividad(actividadSelected);
		listaActividadesV.clear();
		if(currentActividad != null){
			for(Actividad actividad : currentActividad){
				if(actividad.getEstatus().compareTo('V') == 0){
					listaActividadesV.add(actividad);
				}
			}
			}
		actividadReSelected = null;
	}
	public ActividadRechazada getActividadReSelected() {
		return actividadReSelected;
	}
	public void setActividadReSelected(ActividadRechazada actividadReSelected) {
		this.actividadReSelected = actividadReSelected;
	}
	public List<MotivoRechazo> getListaMotivos() {
		return listaMotivos;
	}
	public void setListaMotivos(List<MotivoRechazo> listaMotivos) {
		this.listaMotivos = listaMotivos;
	}

	

}
