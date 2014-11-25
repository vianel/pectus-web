package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.AyudaData;
import com.ucla.frontend.pectus.models.Diagnostico;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.models.EstudioSolicitud;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.services.ServicioEstudioClinicaMonto;
import com.ucla.frontend.pectus.services.ServicioPaciente;
import com.ucla.frontend.pectus.services.ServicioPatologia;
import com.ucla.frontend.pectus.services.ServicioSolicitudAyuda;

public class AyudaView {

	
	


	private Diagnostico diagnosticoSelected;
	private Paciente pacienteSelected;
	private String motivoSelected;
	private Ayuda ayudaSelected;
	
	List<Ayuda> currentAyuda = ServicioSolicitudAyuda.buscarAyudas();
	private AyudaFilter ayudaFilter = new AyudaFilter();
	private Ayuda selected;
	private List<Ayuda> ayudas = ServicioSolicitudAyuda.buscarAyudas();
	private List<Paciente > pacientes = ServicioPaciente.buscarPacientes();
	private List<Diagnostico> diagnosticos = ServicioPatologia.buscarDiagnosticos();
	private List<EstudioSolicitud> estudios = new ArrayList<EstudioSolicitud>(); 
	private List<EstudioClinica> estudiosClinica = ServicioEstudioClinicaMonto.buscarEstudiosXClinica();
	
	List<Paciente> listaPacientes = ServicioPaciente.buscarPacientes();
	
	@Init
	public void init(){
		selected = ayudas.get(0);
		getmodelpaciente();
		getmodelayuda();
	}
	

	
	public ListModel<Ayuda> getmodelayuda() {
        return new ListModelList<Ayuda>(ayudas);
    }
	
	@Command
    @NotifyChange({"modelayuda", "footer"})
    public void changeFilter() {
		currentAyuda = AyudaFilter.getFilterAyudas(ayudaFilter);
    }
	
	public AyudaFilter getAyudaFilter() {
		return ayudaFilter;
	}



	public void setAyudaFilter(AyudaFilter ayudaFilter) {
		this.ayudaFilter = ayudaFilter;
	}



	public Diagnostico getDiagnosticoSelected() {
		return diagnosticoSelected;
	}



	public void setDiagnosticoSelected(Diagnostico diagnosticoSelected) {
		this.diagnosticoSelected = diagnosticoSelected;
	}



	public Paciente getPacienteSelected() {
		return pacienteSelected;
	}



	public void setPacienteSelected(Paciente pacienteSelected) {
		this.pacienteSelected = pacienteSelected;
	}



	public String getMotivoSelected() {
		return motivoSelected;
	}



	public void setMotivoSelected(String motivoSelected) {
		this.motivoSelected = motivoSelected;
	}

	public List<EstudioClinica> getEstudiosClinica() {
		return estudiosClinica;
	}



	public void setEstudiosClinica(List<EstudioClinica> estudiosClinica) {
		this.estudiosClinica = estudiosClinica;
	}



	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}



	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}



	public ListModel<Paciente> getmodelpaciente() {
        return new ListModelList<Paciente>(listaPacientes);
    }
	public Ayuda getSelected() {
		return selected;
	}

	public void setSelected(Ayuda selected) {
		this.selected = selected;
	}

	public List<Ayuda> getAyudas() {
		
		return ayudas;
	}

	public void setAyudas(List<Ayuda> ayudas) {
		this.ayudas = ayudas;
	}
	
	@Command
	public void abrirDialogoRegistrarAyuda(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarAyuda.zul", null, null);
		window.doModal();
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	
	@Command
	public void guardarAyuda() throws Exception{
		String response = null;
		if (motivoSelected!= null) {
	
			ayudaSelected = new Ayuda();
			
			ayudaSelected.setDiagnostico(diagnosticoSelected);
			ayudaSelected.setPaciente(pacienteSelected);
			ayudaSelected.setMotivo(motivoSelected);
			
			response = ServicioSolicitudAyuda.agregarAyuda(ayudaSelected);
			if (response.equalsIgnoreCase("true"))
			{
//				//currentPaciente.add(pacienteselected);
//				currentPaciente = ServicioPaciente.buscarPacientes();
//				pacientestatues = generateStatusList(currentPaciente);
//				
				Clients.showNotification("Ayuda registrada", null, true);
//				x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
//			System.out.println(ciudadSelected.getNombre() + ciudadSelected.getId());
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}



	}
		
}

