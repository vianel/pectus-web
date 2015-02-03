package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Persona;
import com.ucla.frontend.pectus.models.ResultadoAyuda;
import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.services.ServicioCita;
import com.ucla.frontend.pectus.services.ServicioEstudioClinicaMonto;
import com.ucla.frontend.pectus.services.ServicioPaciente;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;
import com.ucla.frontend.pectus.services.ServicioClinica;
//import com.ucla.frontend.pectus.services.ServicioEstudioClinica;
import com.ucla.frontend.pectus.controllers.CitaStatus;

public class CitaView {
	
	private Cita citaselected;
	private ResultadoAyuda resultadoSelected = new ResultadoAyuda();
	private Integer idselected;
	private Date fechaselected;
	private Date horaselected;
	ListModelList<String> listaResultados = new ListModelList<String>();
	ListModelList<String> listaGrados = new ListModelList<String>();

	private Paciente pacienteselected;
	private Clinica clinicaselected;
	private TipoEstudio tipoesselected;
	private EstudioClinica estudioselected;
	
	//private Cita selected; 
	private Window ventanaregistronuevacita;
    private List<Clinica> listaclinica = ServicioClinica.buscarClinica(); // servicio clinica
    private List<TipoEstudio> listatipoestudio = ServicioTipoEstudio.buscarTipoEstudio();
    private List<Paciente> listapaciente = ServicioPaciente.buscarPacientes(); // servicio paciente
    private List<EstudioClinica> listaestudioclinica = ServicioEstudioClinicaMonto.buscarEstudiosXClinica(); // servicio estudioclinica
	private static final String footerMensaje = "Estas son todas las citas";
	private CitaFilter citaFilter = new CitaFilter();
	List<Cita> currentCita = ServicioCita.buscarCita(); //servicio cita
	private List<CitaStatus> citaStatus = generateStatusList(currentCita);
	private boolean displayEdit = true;
	List<Cita> currentCitaMedicaAsignada = ServicioCita.buscarCitaMedicaAsignada(); //servicio cita
	
	@Init
	public void init(){
//		selected = citas.get(0);
		getmodelCita();
		getmodelCitaMedicaAsignada();

		listaResultados.add("Muy Satisfactorio");
		listaResultados.add("Satisfactorio");
		listaResultados.add("Regular");
		listaResultados.add("Poco Satisfactorio");
		
		listaGrados.add("Alto");
		listaGrados.add("Medio");
		listaGrados.add("Bajo");
	}
	
	public ListModelList<String> getListaResultados() {
		return listaResultados;
	}

	public void setListaResultados(ListModelList<String> listaResultados) {
		this.listaResultados = listaResultados;
	}

	public ListModelList<String> getListaGrados() {
		return listaGrados;
	}

	public void setListaGrados(ListModelList<String> listaGrados) {
		this.listaGrados = listaGrados;
	}

	public ListModel<Cita> getmodelCita() {
        return new ListModelList<Cita>(currentCita);
    }
	public ListModel<Cita> getmodelCitaMedicaAsignada() {
        return new ListModelList<Cita>(currentCitaMedicaAsignada);
    }
	// Los filtros de las citas
	
	  public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	  
		public void refreshRowTemplate(CitaStatus cs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, cs, "editingStatus");
	      
	    }  
	  
	  
	public CitaFilter getCitaFilter() {
		return citaFilter;
	}

	public void setCitaFilter(CitaFilter citaFilter) {
		this.citaFilter = citaFilter;
	}

	@Command
	@NotifyChange({"modelcita", "footer"})
	public void abrirDialogoRegistrarCita(){

	   
		ventanaregistronuevacita = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarCita.zul", null, null);
		
		ventanaregistronuevacita.doModal();
	}
	
	
	/***** Guardar una cita ***********/
	
//	@Command
//	@NotifyChange({ "modelcita", "footer" })
//	public void guardarCita(@BindingParam("cmp") Window x) throws Exception{
//		   String response=null;
//	  if (pacienteselected.getCedula()!= null){
//			
//			citaselected = new Cita();
//			
//	
//			citaselected.setPaciente(pacienteselected);
//			citaselected.setEstudioClinica(estudioselected);
//			citaselected.setFecha(fechaselected);
//			citaselected.setHora(horaselected);
//			
//		
//			response = ServicioCita.agregarCita(citaselected);
//			if (response.equalsIgnoreCase("true"))
//			{
//			
//				Clients.showNotification("Cita Guardada", null, true);
//			//	x.detach(); ver esto mosca revisar
//
//			}else{
//			
//				Clients.showNotification("Error al guardar", true);
//			
//			}
//		}
//	     else{
//	     
//	    Clients.showNotification("Porfavor ingrese todos los datos validos");
//	}
//	}
	
	

	
	
	/******************* getters y setters **********************/
	
	
	// MODEL CITA PARA LLAMAR AL DIALOGO
    public List<CitaStatus> getmodelcita() {
    	return citaStatus;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, citaStatus.size());
    }
    public static  List<CitaStatus> generateStatusList(List<Cita> cts)
	{
        List<CitaStatus> citas = new ArrayList<CitaStatus>();
        for(Cita ci : cts) {
            citas.add(new CitaStatus(ci, false));
        }
		return citas;
	}
    
	 @Command
	    @NotifyChange({"modelcita", "footer"})
	    public void changeFilter() {
	        currentCita = citaFilter.getFilterCita(citaFilter);
	        citaStatus = generateStatusList(currentCita);
	    }

	
	public int getIdSelected() {
		return idselected;
	}

	public void setIdSelected(int idselected) {
		this.idselected = idselected;
	}

	public Date getFechaSelected() {
		return fechaselected;
	}

	public void setFechaSelected(Date fechaselected) {
		this.fechaselected = fechaselected;
	}
	public Date getHoraSelected() {
		return horaselected;
	}

	public void setHoraSelected(Date horaselected) {
		this.horaselected = horaselected;
	}
	public Paciente getPacienteSelected() {
		return pacienteselected;
	}

	public void setPacienteSelected(Paciente pacienteselected) {
		this.pacienteselected = pacienteselected;
	}
	public Clinica getClinicaSelected() {
		return clinicaselected;
	}

	public void setClinicaSelected(Clinica clinicaselected) {
		this.clinicaselected = clinicaselected;
	}

	
	
	public ResultadoAyuda getResultadoSelected() {
		return resultadoSelected;
	}

	public void setResultadoSelected(ResultadoAyuda resultadoSelected) {
		this.resultadoSelected = resultadoSelected;
	}

	public Window getVentanaregistronuevacita() {
		return ventanaregistronuevacita;
	}
	
	public void setVentanaregistronuevacita(Window ventanaregistronuevacita) {
		this.ventanaregistronuevacita = ventanaregistronuevacita;
	}

	public List<CitaStatus> getCitastatues() {
		return citaStatus;
	}

	public void setCitastatues(List<CitaStatus> citastatues) {
		this.citaStatus = citastatues;
	}

	public List<Paciente> getlistapaciente() {
			return listapaciente;
		}
	public void setListaPaciente(List<Paciente> listapaciente) {
			this.listapaciente = listapaciente;
		}
	public List<TipoEstudio> getlistatipoestudio() {
		return listatipoestudio;
	}
	public void setListaTipoEstudio(List<TipoEstudio> listatipoestudio) {
		this.listatipoestudio= listatipoestudio;
	}
	public List<Clinica> getlistaclinica() {
		return listaclinica;
	}
	public void setListaClinica(List<Clinica> listaclinica) {
		this.listaclinica= listaclinica;
	}

	public TipoEstudio getTipoesselected() {
		return tipoesselected;
	}

	public void setTipoesselected(TipoEstudio tipoesselected) {
		this.tipoesselected = tipoesselected;
	}

	public Cita getCitaselected() {
		return citaselected;
	}

	public void setCitaselected(Cita citaselected) {
		this.citaselected = citaselected;
	}	
   
	public EstudioClinica getEstudioselected() {
		return estudioselected;
	}

	public void setEstudioselected(EstudioClinica estudioselected) {
		this.estudioselected = estudioselected;
	}

	public List<EstudioClinica> getListaestudioclinica() {
		return listaestudioclinica;
	}

	public void setListaestudioclinica(List<EstudioClinica> listaestudioclinica) {
		this.listaestudioclinica = listaestudioclinica;
	}
    
	
	@Command
	public void guardarCita() throws Exception{

		
		Clients.showNotification("Cita registrada", null, true);
	

//		if (motivoSelected!= null) {
//	
//			ayudaSelected = new Ayuda();
//			
//			ayudaSelected.setDiagnostico(diagnosticoSelected);
//			ayudaSelected.setPaciente(pacienteSelected);
//			ayudaSelected.setMotivo(motivoSelected);
//
//			List<EstudioClinica> estudiosclinicas = new ArrayList<EstudioClinica>(estudioclinicaSelected);
//			response = ServicioSolicitudAyuda.agregarAyuda(ayudaSelected,estudiosclinicas);
//			if (response.equalsIgnoreCase("true"))
//			{
////				//currentPaciente.add(pacienteselected);
////				currentPaciente = ServicioPaciente.buscarPacientes();
////				pacientestatues = generateStatusList(currentPaciente);
////				s
//				Clients.showNotification("Ayuda registrada", null, true);
////				x.detach();
//
//			}else
//			{
//				Clients.showNotification("Error al guardar", true);
//			}
//		}	else{
////			System.out.println(ciudadSelected.getNombre() + ciudadSelected.getId());
//			Clients.showNotification("Porfavor ingrese todos los datos validos");
//		}
//


	}
	
	@Command
	public void resultadoCita() throws Exception{
		
		if (resultadoSelected.getGradoPatologia() == ' ' || resultadoSelected.getResultado() == ' ' || resultadoSelected.getObservacion() == null) {
			Clients.showNotification("Debe llenar todos los campos", null,null,null,2000);
		}else{
		int idCita = citaselected.getId();
		String resp = ServicioCita.ResultadoCita(idCita, resultadoSelected);		
		if (resp.equalsIgnoreCase("true"))
	      {
	  		Clients.showNotification("Resultado de la cita agregado exitosamente", true);
	      }else
	      {
	  		Clients.showNotification("Error al modificar", true);
	      }

	}
		}
	
	
}




