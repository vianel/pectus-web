package com.ucla.frontend.pectus.controllers;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Patologia;
import com.ucla.frontend.pectus.controllers.PatologiaFilter;
//
import com.ucla.frontend.pectus.services.ServicioPatologia;



public class PatologiaVM {
	
	private Patologia patologiaselected;
	private Integer idSelected;
	private String nombreSelected;
	private String observacionSelected;
	private Patologia patologia;
	private HashMap<String, Patologia> patologiaConsulta = new HashMap<String, Patologia>();
	private Window ventanaregistronuevopatologia;
	private ListModelList<Patologia> pato = new ListModelList<Patologia>();

	private String resp;
	private static final String footerMensaje = "Esto son todas las paologias";
	private PatologiaFilter patologiaFilter = new PatologiaFilter();
	private List<Patologia> currentPatologia;
	
	   
	
	@Init
	public void init(){
		this.currentPatologia = ServicioPatologia.buscarPatologia();
	
	}
	   public ListModelList<Patologia>getPatologia(){
			return pato;
		}

		@NotifyChange("patologia")
		public void setPatologia(ListModelList<Patologia> pato) {
			this.pato = pato;
		}


	@Command
	public void abrirDialogoRegistrarPatologia(){	
		
		ventanaregistronuevopatologia = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPatologia.zul", null, null);
		ventanaregistronuevopatologia.doModal();
			
	}
	
    public List<Patologia> getmodelpatologia() {
      return currentPatologia;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, currentPatologia.size());
    }
    
    @Command
    @NotifyChange({"modelpatologia", "footer"})
    public void changeFilter() {
        currentPatologia = PatologiaFilter.getFilterPatologia(patologiaFilter);
    }
 
    @Command
    public void editarPatologia()
    {
       resp = ServicioPatologia.modificarPatologia(patologiaselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("La patologia ha sido moficado exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }
	@Command
	@NotifyChange({ "modelpatologia", "footer" })
	public void guardarPatologia() throws Exception{
		
		String response = null;
		if (nombreSelected!= null && observacionSelected != null) {
	
			patologiaselected = new Patologia(idSelected,nombreSelected,observacionSelected);
			

			response = ServicioPatologia.agregarPatologia(patologiaselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentPatologia = ServicioPatologia.buscarPatologia();
				getmodelpatologia();
				Clients.showNotification("Patologia Guardada", null, true);
				

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}

	}



	public void setPatologiaselected(Patologia patologiaselected) {
		this.patologiaselected = patologiaselected;
	}


	public Integer getIdSelected() {
		return idSelected;
	}


	public void setIdSelected(Integer idSelected) {
		this.idSelected = idSelected;
	}


	public String getNombreSelected() {
		return nombreSelected;
	}



	public void setNombreSelected(String nombreSelected) {
		this.nombreSelected = nombreSelected;
	}



	public String getObservacionSelected() {
		return observacionSelected;
	}



	public void setObservacionSelected(String observacionSelected) {
		this.observacionSelected = observacionSelected;
	}



	
	public Patologia getPatologiaselected() {
		return patologiaselected;
	}	
	 
	public PatologiaFilter getPatologiaFilter() {
		return patologiaFilter;
	}

	public void setPatologiaFilter(PatologiaFilter patologiaFilter) {
		this.patologiaFilter = patologiaFilter;
	}


	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevopatologia;
	}
	
	public void setVentanaregistronuevopatologia(Window ventanaregistronuevopatologia) {
		this.ventanaregistronuevopatologia = ventanaregistronuevopatologia;
	}



	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}
	





	

}

