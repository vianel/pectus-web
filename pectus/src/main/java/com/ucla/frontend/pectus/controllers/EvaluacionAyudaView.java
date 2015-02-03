package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.ucla.frontend.pectus.models.Causa;
import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Diagnostico;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.models.EstudioSolicitud;
import com.ucla.frontend.pectus.models.MotivoRechazo;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.SolicitudRechazada;
import com.ucla.frontend.pectus.services.ServicioCita;
import com.ucla.frontend.pectus.services.ServicioEstudioClinicaMonto;
import com.ucla.frontend.pectus.services.ServicioPaciente;
import com.ucla.frontend.pectus.services.ServicioPatologia;
import com.ucla.frontend.pectus.services.ServicioSolicitudAyuda;

public class EvaluacionAyudaView {

	private List<EstudioClinica> listaNueva = new ArrayList<EstudioClinica>();
	private Diagnostico diagnosticoSelected;
	private Paciente pacienteSelected;
	private String motivoSelected;
	private Ayuda ayudaSelected;
	private MotivoRechazo motivoRechazoSelected;
	private SolicitudRechazada solicitudRechazadaSelected =  new SolicitudRechazada();
	private Cita citaselected = new Cita();
private Causa causaSelected;
	List<Cita> currentCita = ServicioCita.buscarCita(); //servicio cita
	private Set <EstudioClinica> estudioclinicaSelected;
	private Window ventanaregistronuevacita;
	List<Ayuda> currentAyuda = ServicioSolicitudAyuda.buscarAyudas();
	List<Ayuda> currentAyudaAceptada = ServicioSolicitudAyuda.buscarAyudasAceptadas();
	List<Ayuda> currentAyudaSolicitada = ServicioSolicitudAyuda.buscarAyudasSolicitadas();
	List<MotivoRechazo> currenMotivoRechazo = ServicioSolicitudAyuda.buscarMotivosRechazos();
	List<MotivoRechazo> listaMotivoRechazo = ServicioSolicitudAyuda.buscarMotivosRechazos();
	private AyudaFilter ayudaFilter = new AyudaFilter();
	private ListModelList<Causa> listaCausas = ServicioSolicitudAyuda.buscarCausas();
	private Ayuda selected;
	private List<Ayuda> ayudas = ServicioSolicitudAyuda.buscarAyudas();
	private List<Paciente> pacientes = ServicioPaciente.buscarPacientes();
	private List<Diagnostico> diagnosticos = ServicioPatologia.buscarDiagnosticos();
	private List<EstudioSolicitud> estudios = new ArrayList<EstudioSolicitud>();
	
	
//	private ListModelList<EstudioClinica> listaEstudiosTodos = new ListModelList<EstudioClinica>();

	private ListModelList<EstudioClinica> estudiosClinica = ServicioEstudioClinicaMonto.buscarEstudiosXClinica();
	
	private ListModelList<EstudioClinica> estudioClinicaSeleccionado = new ListModelList<EstudioClinica>();
	private EstudioClinica estudioClinicaSeleccionados = new EstudioClinica();
 	
	private static final String footer = "Estas son todas los Ayudas Solicitadas";
	
	List<Paciente> listaPacientes = ServicioPaciente.buscarPacientes();
	
	
	
	
	
	
	@Init
	public void init(){
//		selected = ayudas.get(0);
		getmodelpaciente();
		getmodelayuda();
		getmodelAyudaAceptada();
		getmodelAyudaSolicitada();
		getmodelMotivoRechazo();
		getmodelCita();
		getmodelPaciente();
		
	}
	
	public ListModel<Cita> getmodelCita() {
        return new ListModelList<Cita>(currentCita);
    }
	public ListModel<Ayuda> getmodelayuda() {
        return new ListModelList<Ayuda>(currentAyuda);
    }
	public ListModel<Ayuda> getmodelAyudaAceptada() {
        return new ListModelList<Ayuda>(currentAyudaAceptada);
    }
	public ListModel<Ayuda> getmodelAyudaSolicitada() {
        return new ListModelList<Ayuda>(currentAyudaSolicitada);
    }
	public ListModel<MotivoRechazo> getmodelMotivoRechazo() {
        return new ListModelList<MotivoRechazo>(currenMotivoRechazo);
    }
	public ListModel<Paciente> getmodelPaciente() {
        return new ListModelList<Paciente>(pacientes);
    }
	
	public String getfooter(){
		return String.format(footer, currentAyuda.size());
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


	
	
	public List<MotivoRechazo> getListaMotivoRechazo() {
		return listaMotivoRechazo;
	}


	public void setListaMotivoRechazo(List<MotivoRechazo> listaMotivoRechazo) {
		this.listaMotivoRechazo = listaMotivoRechazo;
	}


	public MotivoRechazo getMotivoRechazoSelected() {
		return motivoRechazoSelected;
	}


	public void setMotivoRechazoSelected(MotivoRechazo motivoRechazoSelected) {
		this.motivoRechazoSelected = motivoRechazoSelected;
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

	public ListModelList<EstudioClinica> getEstudiosClinica() {
		return estudiosClinica;
	}



	public void setEstudiosClinica(ListModelList<EstudioClinica> estudiosClinica) {
		this.estudiosClinica = estudiosClinica;
	}



	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}



	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	


	public ListModelList<EstudioClinica> getEstudioClinicaSeleccionado() {
		return estudioClinicaSeleccionado;
	}


	public void setEstudioClinicaSeleccionado(
			ListModelList<EstudioClinica> estudioClinicaSeleccionado) {
		this.estudioClinicaSeleccionado = estudioClinicaSeleccionado;
	}
	


	public EstudioClinica getEstudioClinicaSeleccionados() {
		return estudioClinicaSeleccionados;
	}
	
	


	public SolicitudRechazada getSolicitudRechazadaSelected() {
		return solicitudRechazadaSelected;
	}


	public void setSolicitudRechazadaSelected(
			SolicitudRechazada solicitudRechazadaSelected) {
		this.solicitudRechazadaSelected = solicitudRechazadaSelected;
	}


	public void setEstudioClinicaSeleccionados(
			EstudioClinica estudioClinicaSeleccionados) {
		this.estudioClinicaSeleccionados = estudioClinicaSeleccionados;
	}


	public ListModel<Paciente> getmodelpaciente() {
        return new ListModelList<Paciente>(listaPacientes);
    }
	public Ayuda getSelected() {
		return selected;
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
	
	
	public Cita getCitaselected() {
		return citaselected;
	}

	public void setCitaselected(Cita citaselected) {
		this.citaselected = citaselected;
	}

	@Command
	public void guardarAyuda() throws Exception{
		String response = null;

		if (motivoSelected == null || diagnosticoSelected == null || causaSelected == null) {
			Clients.showNotification("Debe llenar todos los campos", null, null,null,2000);
		}else{
		
		ServicioSolicitudAyuda.guardarListaEstudio(estudioClinicaSeleccionado, pacienteSelected.getCedula(), motivoSelected, diagnosticoSelected, causaSelected, new Date());
		Clients.showNotification("Ayuda registrada", null, true);
		}


	}

	@Command
	public void aprobarAyuda() throws Exception{

		if(ayudaSelected.getAprobacion() > 100 || ayudaSelected.getAprobacion() < 0){
			Clients.showNotification("Seleccione un valor valido", null, true);
		}else{
			String resp = ServicioSolicitudAyuda.aprobarAyuda(ayudaSelected);
			if (resp.equalsIgnoreCase("true"))
		      {
				Clients.showNotification("Ayuda Aprobada", null, true);
		      }else
		      {
		  		Clients.showNotification("Error al evaluar", true);
		      }
		}
		

	}
	@Command
	public void rechazarAyuda() throws Exception{
		
		
		if (solicitudRechazadaSelected.getDescripcion() == null || motivoRechazoSelected == null) {
			Clients.showNotification("Debe llenar todos los cambios", null, null,null,2000);
		}else{
		String resp = ServicioSolicitudAyuda.RechazarAyuda(ayudaSelected, motivoRechazoSelected, solicitudRechazadaSelected.getDescripcion());
		if (resp.equalsIgnoreCase("true"))
	      {
			Clients.showNotification("Ayuda Rechazada", null, true);
	      }else
	      {
	  		Clients.showNotification("Error al evaluar", true);
	      }
		}
	}

	public Set<EstudioClinica> getestudioclinicaSelected() {
		System.out.println("Entra en getSelected");

		return estudioclinicaSelected;
	}



	public void setestudioclinicaSelected(Set<EstudioClinica> estudioclinicaSelected) {
		System.out.println("Entra en setSelected");
		this.estudioclinicaSelected = estudioclinicaSelected;
	}


	public Ayuda getAyudaSelected() {
		return ayudaSelected;
	}


	public void setAyudaSelected(Ayuda ayudaSelected) {
		this.ayudaSelected = ayudaSelected;
	}
	
	@Command
	@NotifyChange({"modelcita", "footer"})
	public void abrirDialogoRegistrarCita(){

	   
		ventanaregistronuevacita = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarCita.zul", null, null);
		
		ventanaregistronuevacita.doModal();
	}
	
	
	
	
	@Command
    public void chooseItem() {
		Set<EstudioClinica> set = estudiosClinica.getSelection();
    	estudioClinicaSeleccionado.addAll(set);
    	estudiosClinica.removeAll(set);
    }
 
	@Command
    public void unchooseItem() {
		Set<EstudioClinica> set = estudioClinicaSeleccionado.getSelection();
		estudiosClinica.addAll(set);
		estudioClinicaSeleccionado.removeAll(set);
		
    }
 
    @Command
    public void chooseAllItem() {
    	estudioClinicaSeleccionado.addAll(estudiosClinica);
    	estudiosClinica.clear();
    }
 
    @Command
    public void unchooseAll() {
    	estudiosClinica.addAll(estudioClinicaSeleccionado);
    	estudioClinicaSeleccionado.clear();
    }

	public ListModelList<Causa> getListaCausas() {
		return listaCausas;
	}

	public void setListaCausas(ListModelList<Causa> listaCausas) {
		this.listaCausas = listaCausas;
	}

	public Causa getCausaSelected() {
		return causaSelected;
	}

	public void setCausaSelected(Causa causaSelected) {
		this.causaSelected = causaSelected;
	}
    
	public List<EstudioClinica> getListaNueva() {
		return listaNueva;
	}

	public void setListaNueva(List<EstudioClinica> listaNueva) {
		this.listaNueva = listaNueva;
	}

	@NotifyChange("listaNueva")
	@Command
	public void probar() throws Exception{
		
		listaNueva = ServicioSolicitudAyuda.probarLista(ayudaSelected.getId());
		
		
//		System.out.println(ayudaSelected.getPaciente() + "tttttttt");
//		if(ayudaSelected.getListaEstudioClinicas() != null){
//			
//			for(EstudioClinica eestudio: ayudaSelected.getListaEstudioClinicas()){
//				System.out.println(eestudio.getId().toString() + "ffffffffffffffff");
//			}
//		}
		

	}
	@Command
	@NotifyChange("modelAyudaAceptada")
	public void asignarCitaMedicaPaciente(){
		
		String resp = ServicioSolicitudAyuda.asignarCitaMedica(estudioClinicaSeleccionados,citaselected);
		
		
		if (resp.equalsIgnoreCase("true") )
	      {
	  		Clients.showNotification("Se le ha asignado una cita Exitosamente", true);
	  		
	      }else
	      {
	  		Clients.showNotification("Error al modificar", true);
	      }
		currentAyudaAceptada = ServicioSolicitudAyuda.buscarAyudasAceptadas();
		getmodelAyudaAceptada();
	}
}
