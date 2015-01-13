package com.ucla.frontend.pectus.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.SolicitudActividad;
import com.ucla.frontend.pectus.models.TipoActividad;
import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.reports.TipoestudioReportes;
import com.ucla.frontend.pectus.services.ServicioActividad;
import com.ucla.frontend.pectus.services.ServicioCiudad;
import com.ucla.frontend.pectus.services.ServicioEvento;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;
public class ActividadVM {
	
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
	static List<SolicitudActividad> currentSolActividad;
	private List<TipoActividad> listatipoactividad;
	private TipoActividad tipoactividadSelected;
	private SolicitudActividad solicitudactividadSelected;
	private String recursosutilizadoSelected;
	private int asistentesesperadosSelected;
	private Date fechainicioSelected;
	private String montoesperadoSelected;
	private int duracionSelected;
	private Date fechafinSelected;
	private Date horaSelected;
	private String descripcionactSelected;
	
	
	@Init
	public void init(){
		this.currentActividad = ServicioActividad.buscaractividades();
		this.currentSolActividad = ServicioActividad.buscarsolicitudactividades();
	}
	@Command
	@NotifyChange({"listalugar", "listatipoactividad","currentActividad"})
	public void inicializarAtributos() throws Exception{

		listalugar = ServicioEvento.buscarLugares();
		listatipoactividad = ServicioActividad.buscartipoactividades();
		
	}
	public ActividadFilter getActividadFilter(){
		return actividadFilter;
	}
	
	public List<Actividad> getmodelactividad(){
		return new ListModelList<Actividad>(currentActividad);
	
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
    @NotifyChange({"modelactividad", "modelsolactividad"})
    public void registroActividad()
    {
    	
    	 Actividad act = new Actividad();
    	 act.setLugar(lugarSelected);
    	 act.setIdSolicitudActividad(solactividadSelected);
    	 act.setFechainicio(fechainicioSelected);
    	 act.setFechafin(fechafinSelected);
    	// act.setHora(horaSelected.toString());
    	 act.setRecursosUtilizados(recursosutilizadoSelected);
    	 act.setDuracion(Integer.toString(duracionSelected));
    	 act.setDescripcion(solactividadSelected.getDescripcion());
    	 act.setMontoesperado(Integer.parseInt(montoesperadoSelected));
    	 act.setNroasistentesesperados(asistentesesperadosSelected);
    	 String response = ServicioActividad.agregaractividad(act);
 		if (response.equalsIgnoreCase("true"))
 		{
 			
 			Clients.showNotification("Actividad Aprobada", null, true);
 			currentActividad.add(act);
 			currentSolActividad.remove(solactividadSelected);
 
 			

 		}else{
 			Clients.showNotification("Error", true);
 		}
    	 
   	 
    	
    }
    @Command
    public void registroSolicitudActividad()
    {
 	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 	   Date date = new Date();
    	solicitudactividadSelected = new SolicitudActividad(null, tipoactividadSelected, descripcionSelected, date, nombresolicitanteSelected, tlfsolicitanteSelected, true);
    	String response = ServicioActividad.agregarsolicitudactividad(solicitudactividadSelected);
		if (response.equalsIgnoreCase("true"))
		{
			
			Clients.showNotification("Estudio Guardado", null, true);
			

		}else{
			Clients.showNotification("Error al guardar", true);
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
	public String getRecursosutilizadoSelected() {
		return recursosutilizadoSelected;
	}
	public void setRecursosutilizadoSelected(String recursosutilizadoSelected) {
		this.recursosutilizadoSelected = recursosutilizadoSelected;
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
	
	

}
