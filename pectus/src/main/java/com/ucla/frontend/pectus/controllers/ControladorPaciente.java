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

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Seguro;
import com.ucla.frontend.pectus.services.ServicioPaciente;

public class ControladorPaciente {
	private Paciente pacienteselected;
	
	private String cedulaSelected;
	private String nombreSelected;
	private String apellidoSelected;
	private String celularSelected;
	private String phoneSelected;
	private Date fechanacimientoSelected;
	private String nrohijosSelected;
	private String profesionSelected;
	private String cedulass;

	private Window ventanaregistronuevopaciente;
	
	private static final String footerMensaje = "Esto son todos los pacientes";
	private PacienteFilter pacienteFilter = new PacienteFilter();
	List<Paciente> currentPaciente = ServicioPaciente.buscarPacientes();
	private List<PacienteStatus> pacientestatues = generateStatusList(currentPaciente);
	private boolean displayEdit = true;
	     
	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	
	    


	public PacienteFilter getPacienteFilter() {
		return pacienteFilter;
	}

	public void setPacienteFilter(PacienteFilter pacienteFilter) {
		this.pacienteFilter = pacienteFilter;
	}

	@Command
	public void abrirDialogoRegistrarPaciente(){

	   
		ventanaregistronuevopaciente = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPaciente.zul", null, null);
		
		ventanaregistronuevopaciente.doModal();
	}
	@Command
	public void modificarPaciente(@BindingParam("pacienteStatus") PacienteStatus pctes)
	{
		

		String response = null;
		response = ServicioPaciente.modificarPaciente(pctes.getPaciente());
		if (response.equalsIgnoreCase("true"))
		{
			cambiarestatusedicion(pctes);
			Clients.showNotification("Paciente con cedula " + pctes.getPaciente().getCedula() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		} 
	}
	
	 @Command
	 public void cambiarestatusedicion(@BindingParam("pacienteStatus") PacienteStatus pctes) {
	        pctes.setEditingStatus(!pctes.getEditingStatus());
	        refreshRowTemplate(pctes);
	    }
	public void refreshRowTemplate(PacienteStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	    }
    public List<PacienteStatus> getmodelpaciente() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return pacientestatues;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, pacientestatues.size());
    }
    
    @Command
    @NotifyChange({"modelpaciente", "footer"})
    public void changeFilter() {
        currentPaciente = PacienteFilter.getFilterPacientes(pacienteFilter);
        pacientestatues = generateStatusList(currentPaciente);
    }

	@Command
	public void guardarPaciente(@BindingParam("cmp") Window x) throws Exception{
		String response = null;
		if (cedulaSelected!= null) {
			pacienteselected = new Paciente();
			pacienteselected.setCedula(cedulaSelected);
			pacienteselected.setNombre(nombreSelected);
			pacienteselected.setApellido(apellidoSelected);
			pacienteselected.setCelular(celularSelected);
			pacienteselected.setFijo(phoneSelected);
			pacienteselected.setFechaNacimiento(fechanacimientoSelected);
			pacienteselected.setNroHijos(Integer.parseInt(nrohijosSelected));
			pacienteselected.setProfesion(profesionSelected);
			
			response = ServicioPaciente.agregarPaciente(pacienteselected);
			if (response.equalsIgnoreCase("true"))
			{
		
				Clients.showNotification("Paciente Guardado", null, true);
				x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}



	}
	private void ControladorPaciente() {
		// TODO Auto-generated method stub
		this.currentPaciente = ServicioPaciente.buscarPacientes();
		this.pacientestatues = pacientestatues;
	}



	public static  List<PacienteStatus> generateStatusList(List<Paciente> pctes)
	{
        List<PacienteStatus> pacientes = new ArrayList<PacienteStatus>();
        for(Paciente pc : pctes) {
            pacientes.add(new PacienteStatus(pc, false));
        }
		return pacientes;
	}
	public Paciente getPacienteselected() {
		return pacienteselected;
	}



	public void setPacienteselected(Paciente pacienteselected) {
		this.pacienteselected = pacienteselected;
	}



	public String getNombreSelected() {
		return nombreSelected;
	}



	public void setNombreSelected(String nombreSelected) {
		this.nombreSelected = nombreSelected;
	}



	public String getApellidoSelected() {
		return apellidoSelected;
	}



	public void setApellidoSelected(String apellidoSelected) {
		this.apellidoSelected = apellidoSelected;
	}



	public String getCelularSelected() {
		return celularSelected;
	}



	public void setCelularSelected(String celularSelected) {
		this.celularSelected = celularSelected;
	}



	public String getPhoneSelected() {
		return phoneSelected;
	}



	public void setPhoneSelected(String phoneSelected) {
		this.phoneSelected = phoneSelected;
	}



	public Date getFechanacimientoSelected() {
		return fechanacimientoSelected;
	}



	public void setFechanacimientoSelected(Date fechanacimientoSelected) {
		this.fechanacimientoSelected = fechanacimientoSelected;
	}



	public String getNrohijosSelected() {
		return nrohijosSelected;
	}



	public void setNrohijosSelected(String nrohijosSelected) {
		this.nrohijosSelected = nrohijosSelected;
	}



	public String getProfesionSelected() {
		return profesionSelected;
	}



	public void setProfesionSelected(String profesionSelected) {
		this.profesionSelected = profesionSelected;
	}



	public String getCedulaSelected() {
		return cedulaSelected;
	}



	public void setCedulaSelected(String cedulaSelected) {
		this.cedulaSelected = cedulaSelected;
	}

	public String getCedulass() {
		return cedulass;
	}

	public void setCedulass(String cedulass) {
		this.cedulass = cedulass;
	}







}
