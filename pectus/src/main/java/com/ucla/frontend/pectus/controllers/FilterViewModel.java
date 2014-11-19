package com.ucla.frontend.pectus.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.impl.Attribute;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.models.ActividadData;
import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.PacienteData;
import com.ucla.frontend.pectus.services.ServicioPaciente;

public class FilterViewModel   {
	


	
	private static final String footerMensaje = "Esto son todos los pacientes";
	private PacienteFilter pacienteFilter = new PacienteFilter();

	//List<Paciente> currentPaciente = PacienteData.getAllPacientes();
	// ############ AQUI HAGO LA CONEXION CON EL SERVICIO #################
	List<Paciente> currentPaciente = ServicioPaciente.buscarPacientes();
	
	
	private CitaFilter citaFilter = new CitaFilter();
	//List<Cita> currentCita = PacienteData.getAllCitas();
	






	

	public CitaFilter getCitaFilter() {
		return citaFilter;
	}

	public void setCitaFilter(CitaFilter citaFilter) {
		this.citaFilter = citaFilter;
	}

	public CitaFilter geCitaFilter(){
		return citaFilter;
	}

	public PacienteFilter getPacienteFilter() {
        return pacienteFilter;
    }
	
	@Command
	public void abrirDialogoPaciente(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscarPaciente.zul", null, null);
		window.doModal();
		
	}

	
	@Command
	public void abrirDialogoRegistrarPaciente(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPaciente.zul", null, null);
		
		window.doModal();
	}
	
	@Command
	public void abrirDialogoRegistrarCita(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscaCita.zul", null, null);
		window.doModal();
	}
 
    public ListModel<Paciente> getmodelpaciente() {
        return new ListModelList<Paciente>(currentPaciente);
    }
    
    /*public ListModel<Cita> getFoodModelCita() {
        return new ListModelList<Cita>(currentCita);
    }*/
     
    public String getFooter() {
        return String.format(footerMensaje, currentPaciente.size());
    }
    
    @Command
    @NotifyChange({"modelpaciente", "footer"})
    public void changeFilter() {
        currentPaciente = PacienteFilter.getFilterPacientes(pacienteFilter);
    }
    
    /*@Command
    @NotifyChange({"foodModelCita", "footer"})
    public void changeFilterCita() {
        currentCita = PacienteData.getFilterCitas(citaFilter);
    }*/
    
  /*  
//    ################ actividad ###################################3
    
    private static final String footerMensajeactividad = "Esto son todas los Actividades";
	private ActividadFilter actividadFilter = new ActividadFilter();
	List<Actividad> currentActividad = ActividadData.getAllactividades();
	
	public ActividadFilter getActividadFilter(){
		return actividadFilter;
	}
	
	public ListModel<Actividad> getmodelactividad(){
		return new ListModelList<Actividad>(currentActividad);
	}
	public String getfooter(){
		return String.format(footerMensajeactividad, currentActividad.size());
	}
    @Command
    @NotifyChange({"modelactividad", "footerMensajeactividad"})
    public void changeFilteractividad() {
        currentActividad = ActividadData.getFilteractividades(actividadFilter);
    }

*/



    
    
    
    
}
