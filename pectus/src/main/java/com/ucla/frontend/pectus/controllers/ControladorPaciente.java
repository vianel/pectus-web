package com.ucla.frontend.pectus.controllers;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.impl.Attribute;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
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
	
	private static final String footerMensaje = "Esto son todos los pacientes";
	private PacienteFilter pacienteFilter = new PacienteFilter();
	List<Paciente> currentPaciente = ServicioPaciente.buscarPacientes();
	
	public PacienteFilter getPacienteFilter() {
		return pacienteFilter;
	}

	public void setPacienteFilter(PacienteFilter pacienteFilter) {
		this.pacienteFilter = pacienteFilter;
	}

	@Command
	public void abrirDialogoRegistrarPaciente(@BindingParam("paciente") Paciente paciente){

		
		
	    modificarPaciente(paciente);
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPaciente.zul", null, null);
		
		window.doModal();
	}
	
	public void modificarPaciente(Paciente paciente)
	{
		String response = null;
		response = ServicioPaciente.modificarPaciente(paciente);
		if (response.equalsIgnoreCase("true"))
		{
	
			Clients.showNotification("Paciente con cedula " + paciente.getCedula() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		}
	}
	
    public ListModel<Paciente> getmodelpaciente() {
        return new ListModelList<Paciente>(currentPaciente);
    }
    
    public String getFooter() {
        return String.format(footerMensaje, currentPaciente.size());
    }
    
    @Command
    @NotifyChange({"modelpaciente", "footer"})
    public void changeFilter() {
        currentPaciente = PacienteFilter.getFilterPacientes(pacienteFilter);
    }
	
	@Command
	public void guardarPaciente() throws Exception{
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

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}



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





}
