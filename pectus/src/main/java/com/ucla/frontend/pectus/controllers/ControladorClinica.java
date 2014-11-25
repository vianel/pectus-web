package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioClinica;
import com.ucla.frontend.pectus.services.ServicioPaciente;

public class ControladorClinica extends SelectorComposer<Component> {
	
private Clinica clinicaselected;
	
	private String rifSelected;
	private String nombreSelected;
	private String direccionSelected;
	private String telefonoSelected;
	
	
	
	@Wire
	Label lblseleccion;

  
	@Listen("onClick= #btnRegistrarClinica")
	public void abrirDialogoRegistrarClinica1(Event e){

		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarClinica1.zul", null, null);
		window.doModal();
	}
	
	
	private static final String footerMensaje = "Estas son todas las clinicas";
	private ClinicaFilter clinicaFilter = new ClinicaFilter();
	List<Clinica> currentClinica = ServicioClinica.buscarClinica();
	private List<ClinicaStatus> clinicastatues = generateStatusList(currentClinica);
	private boolean displayEdit = true;
	     
	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	
	    public void cambiarestatusedicion(@BindingParam("clinicaStatus") ClinicaStatus pctes) {
	        pctes.setEditingStatus(!pctes.getEditingStatus());
	        refreshRowTemplate(pctes);
	    }
	public void refreshRowTemplate(ClinicaStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	    }
    public List<ClinicaStatus> getmodelclinica() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return clinicastatues;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, clinicastatues.size());
    }
    
    @Command
    @NotifyChange({"modelclinica", "footer"})
    public void changeFilter() {
        currentClinica = ClinicaFilter.getFilterClinica(clinicaFilter);
        clinicastatues = generateStatusList(currentClinica);
    }

	    
	@Command
	public void guardarClinica(@BindingParam("cmp") Window x) throws Exception{
		String response = null;
		if (rifSelected!= null) {
			clinicaselected = new Clinica();
			clinicaselected.setRif(rifSelected);
			clinicaselected.setNombre(nombreSelected);
			clinicaselected.setDireccion(direccionSelected);
			clinicaselected.setTelefono(telefonoSelected);
			
			
			response = ServicioClinica.agregarCLinica(clinicaselected);
			if (response.equalsIgnoreCase("true"))
			{
		
				Clients.showNotification("Clinica Guardada", null, true);
				x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}



	}
	    
	    
	    public static  List<ClinicaStatus> generateStatusList(List<Clinica> pctes)
		{
	        List<ClinicaStatus> clinica = new ArrayList<ClinicaStatus>();
	        for(Clinica pc : pctes) {
	            clinica.add(new ClinicaStatus(pc, false));
	        }
			return clinica;
		}
	    
	    
	    
	public ClinicaFilter getClinicaFilter() {
		return clinicaFilter;
	}
	
	public void setClinicaFilter(ClinicaFilter clinicaFilter) {
		this.clinicaFilter = clinicaFilter;
	}
	
	public Clinica getClinicaselected() {
		return clinicaselected;
	}



	public void setClinicaselected(Clinica clinicaselected) {
		this.clinicaselected = clinicaselected;
	}



	public String getNombreSelected() {
		return nombreSelected;
	}



	public void setNombreSelected(String nombreSelected) {
		this.nombreSelected = nombreSelected;
	}



	public String getRifSelected() {
		return rifSelected;
	}



	public void setRifSelected(String rifSelected) {
		this.rifSelected = rifSelected;
	}
	
		public String getDireccionSelected() {
			return direccionSelected;
		}



		public void setDireccionSelected(String direccionSelected) {
			this.direccionSelected = direccionSelected;
	
		}

		public String getTelefonoSelected() {
			return telefonoSelected;
		}



		public void setTelefonoSelected(String telefonoSelected) {
			this.telefonoSelected = telefonoSelected;
	
		}
		
}
