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
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Patrocinador;
import com.ucla.frontend.pectus.services.ServicioPatrocinador;

public class PatrocinadorViewModel   extends SelectorComposer<Component> {
	
	private List<Patrocinador> listapatrocinador;
	private  ServicioPatrocinador servicioPatrocinador;
		
	
	String txtNombrePatrocinador;
	String txtRifPatrocinador;
	String txtDireccionPatrocinador;
	String txtTlfMovilPatrocinador;
	String txtTlfFijoPatrocinador;
	String txtRepresentantePatrocinador;
	public String getTxtNombrePatrocinador() {
		return txtNombrePatrocinador;
	}


	public void setTxtNombrePatrocinador(String txtNombrePatrocinador) {
		this.txtNombrePatrocinador = txtNombrePatrocinador;
	}


	public String getTxtRifPatrocinador() {
		return txtRifPatrocinador;
	}


	public void setTxtRifPatrocinador(String txtRifPatrocinador) {
		this.txtRifPatrocinador = txtRifPatrocinador;
	}


	public String getTxtDireccionPatrocinador() {
		return txtDireccionPatrocinador;
	}


	public void setTxtDireccionPatrocinador(String txtDireccionPatrocinador) {
		this.txtDireccionPatrocinador = txtDireccionPatrocinador;
	}


	public String getTxtTlfMovilPatrocinador() {
		return txtTlfMovilPatrocinador;
	}


	public void setTxtTlfMovilPatrocinador(String txtTlfMovilPatrocinador) {
		this.txtTlfMovilPatrocinador = txtTlfMovilPatrocinador;
	}


	public String getTxtTlfFijoPatrocinador() {
		return txtTlfFijoPatrocinador;
	}


	public void setTxtTlfFijoPatrocinador(String txtTlfFijoPatrocinador) {
		this.txtTlfFijoPatrocinador = txtTlfFijoPatrocinador;
	}


	public String getTxtRepresentantePatrocinador() {
		return txtRepresentantePatrocinador;
	}


	public void setTxtRepresentantePatrocinador(String txtRepresentantePatrocinador) {
		this.txtRepresentantePatrocinador = txtRepresentantePatrocinador;
	}


	public String getTxtTelfRepresentantePatrocinador() {
		return txtTelfRepresentantePatrocinador;
	}


	public void setTxtTelfRepresentantePatrocinador(
			String txtTelfRepresentantePatrocinador) {
		this.txtTelfRepresentantePatrocinador = txtTelfRepresentantePatrocinador;
	}


	public String getTxtCorreoRepresentantePatrocinador() {
		return txtCorreoRepresentantePatrocinador;
	}


	public void setTxtCorreoRepresentantePatrocinador(
			String txtCorreoRepresentantePatrocinador) {
		this.txtCorreoRepresentantePatrocinador = txtCorreoRepresentantePatrocinador;
	}

	String txtTelfRepresentantePatrocinador;
	String txtCorreoRepresentantePatrocinador;

	
	private static final String footerMensaje = "Esto son todos los Patrocinadores";
	private PacienteFilter pacienteFilter = new PacienteFilter();

	//List<Paciente> currentPaciente = PacienteData.getAllPacientes();
	// ############ AQUI HAGO LA CONEXION CON EL SERVICIO #################
	List<Patrocinador> currentPatrocinador = ServicioPatrocinador.buscarPatrocinador();
	
	
	private CitaFilter citaFilter = new CitaFilter();
	//List<Cita> currentCita = PacienteData.getAllCitas();
	

	@Command
	public void abrirDialogoPaciente(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscarPaciente.zul", null, null);
		window.doModal();
		
	}

	
	@Listen("onClick= #btnRegistrarPatrocinador")
	public void abrirDialogoRegistrarClinica1(Event e){

		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPatrocinador.zul", null, null);
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
 
    public ListModel<Patrocinador> getmodelpatrocinador() {
        return new ListModelList<Patrocinador>(currentPatrocinador);
    }
    
    /*public ListModel<Cita> getFoodModelCita() {
        return new ListModelList<Cita>(currentCita);
    }*/
     
    public String getFooter() {
        return String.format(footerMensaje, currentPatrocinador.size());
    }
    
    @Command
    @NotifyChange({ "modelpatrocinador", "footer" })
    public void guardarPatrocinador1() throws Exception{
		String response = null;
		
    	Patrocinador patrocinador = new Patrocinador();
		
    	patrocinador.setNombre(txtNombrePatrocinador);
		patrocinador.setDireccion(txtDireccionPatrocinador);
		patrocinador.setNombreRepresentante(txtRepresentantePatrocinador);
		patrocinador.setRif(txtRifPatrocinador);
		patrocinador.setTlfCelular(txtTlfMovilPatrocinador);
		patrocinador.setTlfFijo(txtTlfFijoPatrocinador);
		patrocinador.setTlfRepresentante(txtTelfRepresentantePatrocinador);
		patrocinador.setCorreoRepresentante(txtCorreoRepresentantePatrocinador);

		ServicioPatrocinador.agregarPatrocinador(patrocinador);
		//response = servicioPatrocinador.agregarPatrocinador(patrocinador);
		System.out.println("agregado"+patrocinador.getDireccion());
			
    
    }

    
 
    
}