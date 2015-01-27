package com.ucla.frontend.pectus.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

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

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Comision;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Persona;
import com.ucla.frontend.pectus.models.Visita;
import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.models.VoluntarioxComision;
import com.ucla.frontend.pectus.services.ServicioCiudad;
import com.ucla.frontend.pectus.services.ServicioComision;
import com.ucla.frontend.pectus.services.ServicioVoluntario;
import com.ucla.frontend.pectus.controllers.VoluntarioFilter;

public class VoluntarioGVM {
	
	
	private Voluntario voluntarioselected;
    private Persona personaselected;
    private Visita visitaselected;
    private VoluntarioxComision volucomiselected;
	private String cedulaSelected;
	private String nombreSelected;
	private String apellidoSelected;
	private String celularSelected;
	private String fijoSelected;
    private String direccionSelected;
	private String correoSelected; 
	private Date fechanacimientoSelected;
	private String profesionSelected;
	private char edocivilSelected;
	private char sexoSelected;
	
	private Comision comisionSelected;
	
	private Estado estadoSelected;
	private Ciudad ciudadSelected;

	private String lugartrabajoSelected;
	private String cargoSelected;
	private String direcciontrabajoSelected;
	private String tlftrabajoSelected;
	private String referidoSelected;
	

	private List<Ciudad> listaciudad;
    private List<Estado> listaestado;

	private boolean displayEdit = true;
	private List<Comision> listacomision = ServicioComision.buscarComision();
	private Window ventanaregistronuevovoluntarioG;
	private Window ventanaregistroasignarcomision;
	private ListModelList<Voluntario> voluI = new ListModelList<Voluntario>();
	private String resp2;
	private Visita visita = new Visita();
	private VoluntarioxComision  voluntarioxcomision = new VoluntarioxComision();
    private String resp;
	private static final String footerMensaje = "Esto son todos los Voluntario";
	private VoluntarioFilter voluntarioFilter = new VoluntarioFilter();
	private List<Voluntario> currentVoluntarioI;	
	

	ListModelList<String> listaedocivil = new ListModelList<String>();
	ListModelList<String> listasexo = new ListModelList<String>();

	List<Voluntario> listaVoluntariosI = ServicioVoluntario.buscarVoluntarioI();
	List<Voluntario> currentVS = ServicioVoluntario.buscarVoluntarioS();
	List<Voluntario> currentVA = ServicioVoluntario.buscarVoluntarioA();
		
		
/*****************Get modelPersonaVS() ******************************/
  public ListModel<Voluntario> getmodelVoluntarioS() {
	  return new ListModelList<Voluntario>(currentVS);
	    }
  
  /*****************Get modelPersonaVA() ******************************/
  public ListModel<Voluntario> getmodelVoluntarioA() {
	  return new ListModelList<Voluntario>(currentVA);
	    }

  
/*********************DIALOGO REGISTRAR VOLUNTARIOG************************************/  
  @Command
	public void abrirDialogoRegistrarVoluntarioG(){	
		
		ventanaregistronuevovoluntarioG = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarVoluntarioG.zul", null, null);
		ventanaregistronuevovoluntarioG.doModal();
	}

  
/************************SERVICIO ESTADO CIUDAD*********************************************/
	@Command
	@NotifyChange({"listaciudad","listaestado"})
	public void inicializarAtributos() throws Exception{
		 listaedocivil.add("Soltera");
		 listaedocivil.add("Casada");
		 listaedocivil.add("Divorciada");
		 listaedocivil.add("Viuda");
		 listasexo.add("Femenino");
		 listasexo.add("Masculino");
		
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


  /*******************************BUSCAR VOLUNTARIO INGRESADO******************************/
	@Init
	public void init(){
		this.currentVoluntarioI = ServicioVoluntario.buscarVoluntarioI();
		
	}
	
	   public ListModelList<Voluntario>getVoluntarioI(){
			return voluI;
		}

		@NotifyChange("voluntario")
		public void setVoluntarioI(ListModelList<Voluntario> voluI) {
			this.voluI = voluI;
		}

 /******************************* ACEPTAR VOLUNTARIO******************************/
    @Command
    public void aceptarVoluntario()
    {
       resp = ServicioVoluntario.aceptarVoluntario(voluntarioselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El Voluntario ha sido registrado exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }
    
    /*********************************ASIGNAR CITA VOLUNTARIO ********************************/


	@Command
	public void asignarCitaVoluntario(){
		resp = ServicioVoluntario.asignarCitaVoluntario(voluntarioselected);
		
		resp2 = ServicioVoluntario.atenderPostulado(voluntarioselected, visita);
		
		if (resp.equalsIgnoreCase("true") && resp2.equalsIgnoreCase("true"))
	      {
	  		Clients.showNotification("Se le ha asignado una cita Exitosamente", true);
	      }else
	      {
	  		Clients.showNotification("Error al asignar", true);
	      }
		
	}
	   
    
 /*********************************GUARDAR COMISION  ********************************/
	@Command
	@NotifyChange({ "modelvoluntarioxcomision", "footer" })
	public void guardarVoluntarioxComision(@BindingParam("cmp") Window x) throws Exception{
		String response = null;
		if (voluntarioselected.getCedula()!= null) {
	
			volucomiselected = new VoluntarioxComision();
			
			volucomiselected.setVoluntario(voluntarioselected);
			volucomiselected.setComision(comisionSelected);
		
			response = ServicioVoluntario.agregarComision(voluntarioselected, comisionSelected);
			if (response.equalsIgnoreCase("true"))
			{
				
				Clients.showNotification("Comision asignada satisfactoriamente", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos", null, true);
		}

} 

	
	
	
	/*********************************GUARDAR VOLUNTARIO ********************************/
	@Command
	@NotifyChange({ "modelvoluntarioG", "footer" })
	public void guardarVoluntario(@BindingParam("cmp") Window x) throws Exception{
		String response = null;
		if (cedulaSelected!= null) {
	
			voluntarioselected = new Voluntario();
			
			voluntarioselected.setCedula(cedulaSelected);
			voluntarioselected.setCiudad(ciudadSelected);
			voluntarioselected.setNombre(nombreSelected);
			voluntarioselected.setApellido(apellidoSelected);
			voluntarioselected.setCelular(celularSelected);
			voluntarioselected.setFijo(fijoSelected);
			voluntarioselected.setDireccion(direccionSelected);
			voluntarioselected.setCorreo(correoSelected);
			voluntarioselected.setFechaNacimiento(fechanacimientoSelected);
			voluntarioselected.setProfesion(profesionSelected);	
			voluntarioselected.setEstadoCivil(edocivilSelected);
			voluntarioselected.setSexo(sexoSelected);
			voluntarioselected.setLugarTrabajo(lugartrabajoSelected);
			voluntarioselected.setCargo(cargoSelected);
			voluntarioselected.setDireccionTrabajo(direcciontrabajoSelected);
			voluntarioselected.setTelefonoOficina(tlftrabajoSelected);
			voluntarioselected.setReferido(referidoSelected);
		
			response = ServicioVoluntario.agregarVoluntario(voluntarioselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				Clients.showNotification("Voluntario Guardado", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos", null, true);
		}

} 
	
	
/*********************************EDITAR VOLUNTARIO ********************************/

    @Command
    public void editarVoluntario()
    {
       resp = ServicioVoluntario.editarVoluntario(voluntarioselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El Voluntario ha sido modificado exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }

    
 /*****************CONVERSOR ESTADO CIVIL / SEXO******************************/  
    
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
    	else if(s.equalsIgnoreCase("Viuda"))
    	{
    		return 'V';
    	}
    	return 'N';
    }
    public char conversorsexo(String s)
    {
    	if (s.equalsIgnoreCase("Femenino"))
    	{
    		return 'F';
    	}
    	else if (s.equalsIgnoreCase("Masculino"))
    	{
    		return 'M';
    	}

    	return 'N';
    }


	  
/*****************FILTROS y modelvoluntario ******************************/
	  public List<Voluntario> getmodelvoluntarioI() {
	      return currentVoluntarioI;
	    }
	    
	/*  public List<Voluntario> getmodelVoluntarioA() {
	      return currentVoluntarioA;
	    }
	    
	    public String getFooter() {
	        return String.format(footerMensaje, currentVoluntarioI.size());
	    }*/
	    
	    @Command
	    @NotifyChange({"modelvoluntarioG", "footer"})
	    public void changeFilter() {
	        currentVoluntarioI = VoluntarioFilter.getFilterVoluntarios(voluntarioFilter);
	    }
	
	    
		
/************************************Getters  /   Setters ******************************/	
	

	public Comision getComisionSelected() {
		return comisionSelected;
	}


	public void setComisionSelected(Comision comisionSelected) {
		this.comisionSelected = comisionSelected;
	}


	public List<Comision> getListacomision() {
		return listacomision;
	}


	public void setListacomision(List<Comision> listacomision) {
		this.listacomision = listacomision;
	}


	public Voluntario getVoluntarioselected() {
		return voluntarioselected;
	}


	public void setVoluntarioselected(Voluntario voluntarioselected) {
		this.voluntarioselected = voluntarioselected;
	}


	public String getCedulaSelected() {
		return cedulaSelected;
	}


	public void setCedulaSelected(String cedulaSelected) {
		this.cedulaSelected = cedulaSelected;
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


	public String getProfesionSelected() {
		return profesionSelected;
	}


	public void setProfesionSelected(String profesionSelected) {
		this.profesionSelected = profesionSelected;
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


	public char getEdocivilSelected() {
		return edocivilSelected;
	}


	public void setEdocivilSelected(char edocivilSelected) {
		this.edocivilSelected = edocivilSelected;
	}


	public String getLugartrabajoSelected() {
		return lugartrabajoSelected;
	}


	public void setLugartrabajoSelected(String lugartrabajoSelected) {
		this.lugartrabajoSelected = lugartrabajoSelected;
	}


	public String getCargoSelected() {
		return cargoSelected;
	}


	public void setCargoSelected(String cargoSelected) {
		this.cargoSelected = cargoSelected;
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


	public String getReferidoSelected() {
		return referidoSelected;
	}


	public void setReferidoSelected(String referidoSelected) {
		this.referidoSelected = referidoSelected;
	}

	public Persona getPersonaselected() {
		return personaselected;
	}

	public void setPersonaselected(Persona personaselected) {
		this.personaselected = personaselected;
	}

	public Window getVentanaregistronuevovoluntarioG() {
		return ventanaregistronuevovoluntarioG;
	}

	public void setVentanaregistronuevovoluntarioG(
			Window ventanaregistronuevovoluntarioG) {
		this.ventanaregistronuevovoluntarioG = ventanaregistronuevovoluntarioG;
	}

	public VoluntarioFilter getVoluntarioFilter() {
		return voluntarioFilter;
	}

	public void setVoluntarioFilter(VoluntarioFilter voluntarioFilter) {
		this.voluntarioFilter = voluntarioFilter;
	}
	
	public String getResp() {
		return resp;
	}
    public void setResp(String resp) {
		this.resp = resp;
	}

	public List<Estado> getListaestado() {
		return listaestado;
	}

	public void setListaestado(List<Estado> listaestado) {
		this.listaestado = listaestado;
	}

	public Visita getVisitaselected() {
		return visitaselected;
	}

	public void setVisitaselected(Visita visitaselected) {
		this.visitaselected = visitaselected;
	}

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

	public Window getVentanaregistroasignarcomision() {
		return ventanaregistroasignarcomision;
	}

	public void setVentanaregistroasignarcomision(
			Window ventanaregistroasignarcomision) {
		this.ventanaregistroasignarcomision = ventanaregistroasignarcomision;
	}

	public VoluntarioxComision getVoluntarioxcomision() {
		return voluntarioxcomision;
	}

	public void setVoluntarioxcomision(VoluntarioxComision voluntarioxcomision) {
		this.voluntarioxcomision = voluntarioxcomision;
	}

	public VoluntarioxComision getVolucomiselected() {
		return volucomiselected;
	}

	public void setVolucomiselected(VoluntarioxComision volucomiselected) {
		this.volucomiselected = volucomiselected;
	}

	public char getSexoSelected() {
		return sexoSelected;
	}

	public void setSexoSelected(char sexoSelected) {
		this.sexoSelected = sexoSelected;
	}

	public ListModelList<String> getListaedocivil() {
		return listaedocivil;
	}

	public void setListaedocivil(ListModelList<String> listaedocivil) {
		this.listaedocivil = listaedocivil;
	}


	public ListModelList<String> getListasexo() {
		return listasexo;
	}

	public void setListasexo(ListModelList<String> listasexo) {
		this.listasexo = listasexo;
	}

	}


