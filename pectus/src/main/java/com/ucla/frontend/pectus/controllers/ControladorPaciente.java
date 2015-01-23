package com.ucla.frontend.pectus.controllers;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
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
import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Persona;
import com.ucla.frontend.pectus.models.Seguro;
import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.models.Visita;
import com.ucla.frontend.pectus.services.ServicioCiudad;
import com.ucla.frontend.pectus.services.ServicioPaciente;
import com.ucla.frontend.pectus.services.ServicioPersona;


public class ControladorPaciente implements Serializable{
	private String resp;
	private String resp2;
	private Visita visita = new Visita();
	
	private Paciente pacienteselected;
	private Persona personaselected;
	private String cedulaSelected;
	private String nombreSelected;
	private String apellidoSelected;
	private String celularSelected;
	private String fijoSelected;
	private Date fechanacimientoSelected;
	private String nrohijosSelected;
	private String profesionSelected;
	private String direccionSelected;
	private Estado estadoSelected;
	private Ciudad ciudadSelected;
	private String correoSelected;
	private String edocivilSelected;
	private String cedulaconyugueSelected;
	private String nombreconyugueSelected;
	private String apellidoconyugueSelected;
	private Date fechanacimientoconyigueSelected;
	private String profesionconyugueSelected;
	private String viviendaSelected;
	private String condicionviviendaSelected;
	private String nrohabitantesSelected;
	private String precioalquilerSelected;
	private String lugartrabajoSelected;
	private String direcciontrabajoSelected;
	private String tlftrabajoSelected;
	private String ingfamiliaresSelected;
	private String egrfamiliaresSelected;
	private String phoneSelected;
	
	

	private List<Ciudad> listaciudad;

	private List<Estado> listaestado;

	private Window ventanaregistronuevopaciente;
	
	private static final String footerMensaje = "Esto son todos los pacientes";
	private PacienteFilter pacienteFilter = new PacienteFilter();
	List<Paciente> currentPaciente = ServicioPaciente.buscarPacientes();
	private List<Paciente> currentPacientes;
	List<Persona> currentPersona = ServicioPersona.buscarPersonas();
	List<Persona> currentPersonaAceptada = ServicioPersona.buscarPersonasAceptadas();
	private List<PacienteStatus> pacientestatues = generateStatusList(currentPaciente);

	private boolean displayEdit = true;
	List<String> listaedocivil = new ArrayList<String>();

	@Init
	public void init(){
		this.currentPacientes = ServicioPaciente.buscarPacientes();
		getmodelPersona();
//		getmodelPacientesNuevo();
	}
	
//	NUEVOS METODOS
	
	@Command
	@NotifyChange("personaSeleccionada")
	public void asosiarPaciente(@BindingParam("personaSeleccionada") Persona persona){
		Global.personaSeleccionada = persona;
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPaciente.zul", null, null);
		window.doModal();
	}
	
//	@Command
//	public void asignarCita(){
//		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgAsignarCita.zul", null, null);
//		window.doModal();
//	}
	
	
	
	
	
	
	public ListModel<Persona> getmodelPersona() {
        return new ListModelList<Persona>(currentPersona);
    }
	public ListModel<Persona> getmodelPersonaAceptada() {
        return new ListModelList<Persona>(currentPersonaAceptada);
    }
	public List<Paciente> getmodelPacientesNuevo() {
        return currentPacientes;
    }
	
	
	
	@Command
	@NotifyChange({"listaciudad","listaestado"})
	public void inicializarAtributos() throws Exception{
		 listaedocivil.add("Soltera");
		 listaedocivil.add("Casada");
		 listaedocivil.add("Divorciada");
		 listaedocivil.add("Viuda");
		
		
		listaestado = ServicioCiudad.buscarEstados();
		
	}
	    
	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	
	    
		@Command
		@NotifyChange({"listaciudad"})
		public void cambioEstado() throws Exception{
			listaciudad = ServicioCiudad.buscarCiudades(estadoSelected);
	

		}
		
		
	public List<Ciudad> getListaciudad() {
			return listaciudad;
		}


		public void setListaciudad(List<Ciudad> listaciudad) {
			this.listaciudad = listaciudad;
		}





	public PacienteFilter getPacienteFilter() {
		return pacienteFilter;
	}

	public void setPacienteFilter(PacienteFilter pacienteFilter) {
		this.pacienteFilter = pacienteFilter;
	}

	@Command
	@NotifyChange({"modelpaciente", "footer"})
	public void abrirDialogoRegistrarPaciente(){

	   
		ventanaregistronuevopaciente = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPaciente.zul", null, null);
		
		ventanaregistronuevopaciente.doModal();
	}
	@Command
	public void abrirDialogoRegistrarAyuda(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarAyuda.zul", null, null);
		window.doModal();
	}
	@Command
	@NotifyChange({"modelpaciente", "footer"})
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
//	@NotifyChange({ "modelpaciente", "footer" })
	public void guardarPaciente(@BindingParam("cmp") Window x) throws Exception{
		String response = null;
		if (cedulaSelected!= null) {
	
			pacienteselected = new Paciente();
			
			
			pacienteselected.setCedula(cedulaSelected);
			pacienteselected.setNombre(nombreSelected);
			pacienteselected.setApellido(apellidoSelected);
//			pacienteselected.setCelular(celularSelected);
//			pacienteselected.setFijo(fijoSelected);
//			pacienteselected.setFechaNacimiento(fechanacimientoSelected);
//			pacienteselected.setNroHijos(Integer.parseInt(nrohijosSelected));
//			pacienteselected.setProfesion(profesionSelected);
//			
//			
//			pacienteselected.setCedula(cedulaSelected);
//			pacienteselected.setCiudad(ciudadSelected);
			

		/*	
			pacienteselected.setNombre(nombreSelected);
			pacienteselected.setApellido(apellidoSelected);
			pacienteselected.setCelular(celularSelected);
			pacienteselected.setFijo(phoneSelected);
			pacienteselected.setDireccion(direccionSelected);
			pacienteselected.setCorreo(correoSelected);
			pacienteselected.setFechaNacimiento(fechanacimientoSelected);
			pacienteselected.setProfesion(profesionSelected);
			pacienteselected.setEstadoCivil(edocivilSelected.charAt(0));
			//pacienteselected.setSeguro(seguro); chequear
			pacienteselected.setLogin(cedulaSelected);
			// pacienteselected.setSeguro(seguro); chequear
			// seguro particular
			pacienteselected.setNroHijos(Integer.parseInt(nrohijosSelected));
			pacienteselected.setCedulaConyugue(cedulaconyugueSelected);
			pacienteselected.setNombreConyugue(nombreconyugueSelected);
			pacienteselected.setApellidoConyugue(apellidoconyugueSelected);
						
			pacienteselected.setFechaNacConyugue(fechanacimientoconyigueSelected);
			pacienteselected.setOcupacionConyugue(profesionconyugueSelected);
			pacienteselected.setTipoVivienda(viviendaSelected.charAt(0));
			pacienteselected.setNroHabitantes(Integer.parseInt(nrohabitantesSelected));
			pacienteselected.setTendenciaVivienda(viviendaSelected.charAt(0));
			pacienteselected.setAlquiler(Double.parseDouble(precioalquilerSelected));
			pacienteselected.setLugarTrabajo(lugartrabajoSelected);
			pacienteselected.setDireccionTrabajo(direcciontrabajoSelected);
			pacienteselected.setTelefonoTrabajo(tlftrabajoSelected);
			pacienteselected.setIngresos(Float.parseFloat(ingfamiliaresSelected));
			pacienteselected.setEgresos(Float.parseFloat(egrfamiliaresSelected));
			
			*/
			response = ServicioPaciente.agregarPaciente(pacienteselected);
			if (response.equalsIgnoreCase("true"))
			{
				//currentPaciente.add(pacienteselected);
				currentPaciente = ServicioPaciente.buscarPacientes();
				pacientestatues = generateStatusList(currentPaciente);
				
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
    public char conversorestadocivil(String s)
    {
    	if (s.equalsIgnoreCase("Soltera"))
    	{
    		return 'S';
    	}
    	else if (s.equalsIgnoreCase("Casada"))
    	{
    		return 'C';
    	}
    	else if (s.equalsIgnoreCase("Divorciada"))
    	{
    		return 'D';
    	}
    	else if(s.equalsIgnoreCase("Viudad"))
    	{
    		return 'V';
    	}
    	return 'N';
    }
    public char conversortipovivienda(String s)
    {
    	if (s.equalsIgnoreCase("Casa"))
    	{
    		return 'C';
    	}
    	else if (s.equalsIgnoreCase("Apartamento"))
    	{
    		return 'A';
    	}
    	else if (s.equalsIgnoreCase("Rancho"))
    	{
    		return 'R';
    	}

    	return 'N';
    }
    public char conversortendenciavivienda(String s)
    {
    	if (s.equalsIgnoreCase("Alquilada"))
    	{
    		return 'A';
    	}
    	else if (s.equalsIgnoreCase("Propia"))
    	{
    		return 'P';
    	}

    	return 'N';
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



	public String getFijoSelected() {
		return fijoSelected;
	}



	public void setFijoSelected(String fijoSelected) {
		this.fijoSelected = fijoSelected;
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



	public List<Estado> getListaestado() {
		return listaestado;
	}

	public void setListaestado(List<Estado> listaestado) {
		this.listaestado = listaestado;
	}

	public Estado getEstadoSelected() {
		return estadoSelected;
	}

	public void setEstadoSelected(Estado estadoSelected) {
		this.estadoSelected = estadoSelected;
	}
	public Ciudad getCiudadSelected() {
		return ciudadSelected;
	}


	public void setCiudadSelected(Ciudad ciudadSelected) {
		this.ciudadSelected = ciudadSelected;
	}

	public String getDireccionSelected() {
		return direccionSelected;
	}

	public void setDireccionSelected(String direccionSelected) {
		this.direccionSelected = direccionSelected;
	}

	public String getCorreoSelected() {
		return correoSelected;
	}

	public void setCorreoSelected(String correoSelected) {
		this.correoSelected = correoSelected;
	}

	public String getEdocivilSelected() {
		return edocivilSelected;
	}

	public void setEdocivilSelected(String edocivilSelected) {
		this.edocivilSelected = edocivilSelected;
	}

	public String getCedulaconyugueSelected() {
		return cedulaconyugueSelected;
	}

	public void setCedulaconyugueSelected(String cedulaconyugueSelected) {
		this.cedulaconyugueSelected = cedulaconyugueSelected;
	}

	public String getNombreconyugueSelected() {
		return nombreconyugueSelected;
	}

	public void setNombreconyugueSelected(String nombreconyugueSelected) {
		this.nombreconyugueSelected = nombreconyugueSelected;
	}

	public String getApellidoconyugueSelected() {
		return apellidoconyugueSelected;
	}

	public void setApellidoconyugueSelected(String apellidoconyugueSelected) {
		this.apellidoconyugueSelected = apellidoconyugueSelected;
	}

	public Date getFechanacimientoconyigueSelected() {
		return fechanacimientoconyigueSelected;
	}

	public void setFechanacimientoconyigueSelected(
			Date fechanacimientoconyigueSelected) {
		this.fechanacimientoconyigueSelected = fechanacimientoconyigueSelected;
	}

	public String getProfesionconyugueSelected() {
		return profesionconyugueSelected;
	}

	public void setProfesionconyugueSelected(String profesionconyugueSelected) {
		this.profesionconyugueSelected = profesionconyugueSelected;
	}

	public String getViviendaSelected() {
		return viviendaSelected;
	}

	public void setViviendaSelected(String viviendaSelected) {
		this.viviendaSelected = viviendaSelected;
	}

	public String getCondicionviviendaSelected() {
		return condicionviviendaSelected;
	}

	public void setCondicionviviendaSelected(String condicionviviendaSelected) {
		this.condicionviviendaSelected = condicionviviendaSelected;
	}

	public String getNrohabitantesSelected() {
		return nrohabitantesSelected;
	}

	public void setNrohabitantesSelected(String nrohabitantesSelected) {
		this.nrohabitantesSelected = nrohabitantesSelected;
	}

	public String getPrecioalquilerSelected() {
		return precioalquilerSelected;
	}

	public void setPrecioalquilerSelected(String precioalquilerSelected) {
		this.precioalquilerSelected = precioalquilerSelected;
	}

	public String getLugartrabajoSelected() {
		return lugartrabajoSelected;
	}

	public void setLugartrabajoSelected(String lugartrabajoSelected) {
		this.lugartrabajoSelected = lugartrabajoSelected;
	}

	public String getDirecciontrabajoSelected() {
		return direcciontrabajoSelected;
	}

	public void setDirecciontrabajoSelected(String direcciontrabajoSelected) {
		this.direcciontrabajoSelected = direcciontrabajoSelected;
	}

	public String getTlftrabajoSelected() {
		return tlftrabajoSelected;
	}

	public void setTlftrabajoSelected(String tlftrabajoSelected) {
		this.tlftrabajoSelected = tlftrabajoSelected;
	}

	public String getIngfamiliaresSelected() {
		return ingfamiliaresSelected;
	}

	public void setIngfamiliaresSelected(String ingfamiliaresSelected) {
		this.ingfamiliaresSelected = ingfamiliaresSelected;
	}

	public String getEgrfamiliaresSelected() {
		return egrfamiliaresSelected;
	}

	public void setEgrfamiliaresSelected(String egrfamiliaresSelected) {
		this.egrfamiliaresSelected = egrfamiliaresSelected;
	}

	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevopaciente;
	}

	public void setVentanaregistronuevopaciente(Window ventanaregistronuevopaciente) {
		this.ventanaregistronuevopaciente = ventanaregistronuevopaciente;
	}

	public List<PacienteStatus> getPacientestatues() {
		return pacientestatues;
	}

	public void setPacientestatues(List<PacienteStatus> pacientestatues) {
		this.pacientestatues = pacientestatues;
	}

	public List<String> getListaedocivil() {
		return listaedocivil;
	}

	public void setListaedocivil(List<String> listaedocivil) {
		this.listaedocivil = listaedocivil;
	}

	public String getPhoneSelected() {
		return phoneSelected;
	}

	public void setPhoneSelected(String phoneSelected) {
		this.phoneSelected = phoneSelected;
	}

	public Persona getPersonaselected() {
		return personaselected;
	}

	public void setPersonaselected(Persona personaselected) {
		this.personaselected = personaselected;
	}
	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}
	


//	METODOS NUEVOS PARA AGREGAR Y EDITAR
	
	public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public String getResp2() {
		return resp2;
	}

	public void setResp2(String resp2) {
		this.resp2 = resp2;
	}

	@Command
	public void aceptarPaciente(){
		resp = ServicioPaciente.aceptarPaciente(personaselected);
		
		if (resp.equalsIgnoreCase("true"))
	      {
	  		Clients.showNotification("El Paciente ha sido agregado Exitosamente", true);
	      }else
	      {
	  		Clients.showNotification("Error al modificar", true);
	      }
		
	}

	@Command
	public void asignarCitaPaciente(){
		resp = ServicioPaciente.asignarCitaPaciente(personaselected);
		
		resp2 = ServicioPaciente.agregarCitaPostulado(personaselected, visita);
		
		if (resp.equalsIgnoreCase("true") && resp2.equalsIgnoreCase("true"))
	      {
	  		Clients.showNotification("Se le ha asignado una cita Exitosamente", true);
	      }else
	      {
	  		Clients.showNotification("Error al modificar", true);
	      }
		
	}
	




}
