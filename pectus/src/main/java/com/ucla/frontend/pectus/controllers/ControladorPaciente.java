package com.ucla.frontend.pectus.controllers;

import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.impl.Attribute;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;

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
	

	
	@Command
	public void guardarPaciente() throws Exception{
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
			
			ServicioPaciente.agregarPaciente(pacienteselected);
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
