package com.ucla.frontend.pectus.controllers;


import java.util.ArrayList;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Patologia;
import com.ucla.frontend.pectus.controllers.PatologiaFilter;
import com.ucla.frontend.pectus.services.ServicioPatologia;


public class PatologiaVM {
	
	private Patologia patologiaselected;
	private Integer idSelected;
	private String nombreSelected;
	private String observacionSelected;
	
	
	
	private ListModelList<Patologia> patolo = new ListModelList<Patologia>();
	private Patologia selectedItem;

	 
	   
	   
	   public ListModelList<Patologia>getPatologia(){
			return patolo;
		}
	   
	   

		@NotifyChange("patologia")
		public void setPatologia(ListModelList<Patologia> patolo) {
			this.patolo = patolo;
		}


		public Patologia getSelectedItem() {
			return selectedItem;
		}

		@NotifyChange("selectedItem")
		public void setSelectedItem(Patologia selectedItem) {
			this.selectedItem = selectedItem;
		}

		
	   

	private Window ventanaregistronuevopatologia;	
	
	private static final String footerMensaje = "Esto son todos los pacientes";
	private PatologiaFilter patologiaFilter = new PatologiaFilter();
	List<Patologia> currentPatologia = ServicioPatologia.buscarPatologia();
	private List<PatologiaStatus> patologiastatues = generateStatusList(currentPatologia);
	private boolean displayEdit = true;


	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	    
	    
	
	 
	public PatologiaFilter getPatologiaFilter() {
		return patologiaFilter;
	}

	public void setPatologiaFilter(PatologiaFilter patologiaFilter) {
		this.patologiaFilter = patologiaFilter;
	}

	@Command
	@NotifyChange({"modelpatologia", "footer"})
	public void abrirDialogoRegistrarPatologia(){

	   
		ventanaregistronuevopatologia = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPatologia.zul", null, null);
		
		ventanaregistronuevopatologia.doModal();
	}
	
	
	
	@Command
	@NotifyChange({"modelpatologia", "footer"})
	public void abrirDialogoEditarPatologia(){

	   
		ventanaregistronuevopatologia = (Window)Executions.createComponents("/vistas/dialogos/dlgEditarPatologia.zul", null, null);
		
		ventanaregistronuevopatologia.doModal();
	}
	
	
	@Command
	@NotifyChange({"modelppatologia", "footer"})
	public void modificarPatologia(@BindingParam("patologiaStatus") PatologiaStatus patolo)
	{
	
		
		String response = null;
		response = ServicioPatologia.modificarPatologia(patolo.getPatologia());
		if (response.equalsIgnoreCase("true"))
		{
			cambiarestatusedicion(patolo);
			Clients.showNotification("Patologia con Nombre " + patolo.getPatologia().getNombre() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		} 
		List<Patologia> patologia = ServicioPatologia.buscarPatologia();
	}
	
	 @Command
	 
	 public void cambiarestatusedicion(@BindingParam("patologiaStatus") PatologiaStatus patolo) {
	        patolo.setEditingStatus(!patolo.getEditingStatus());
	        refreshRowTemplate(patolo);
	    }
	public void refreshRowTemplate(PatologiaStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	    }
    public List<PatologiaStatus> getmodelpatologia() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return patologiastatues;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, patologiastatues.size());
    }
    
    @Command
    @NotifyChange({"modelpatologia", "footer"})
    public void changeFilter() {
        currentPatologia = PatologiaFilter.getFilterPatologia(patologiaFilter);
        patologiastatues = generateStatusList(currentPatologia);
    }


	@Command
	@NotifyChange({ "modelpatologia", "footer" })
	public void guardarPatologia() throws Exception{
		String response = null;
		if (nombreSelected!= null) {
	
			patologiaselected = new Patologia();
			
			patologiaselected.setId(idSelected);
			patologiaselected.setNombre(nombreSelected);
			patologiaselected.setObservacion(observacionSelected);
			
			

			response = ServicioPatologia.agregarPatologia(patologiaselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentPatologia = ServicioPatologia.buscarPatologia();
				patologiastatues = generateStatusList(currentPatologia);
				
				Clients.showNotification("Patologia Guardado", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}
		List<Patologia> tipoestudio = ServicioPatologia.buscarPatologia();


	}
    
	public static  List<PatologiaStatus> generateStatusList(List<Patologia> patolo)
	{
        List<PatologiaStatus> patologia = new ArrayList<PatologiaStatus>();
        for(Patologia patolog : patolo) {
            patologia.add(new PatologiaStatus(patolog, false));
        }
		return patologia;
	}
	public Patologia getPatologiaselected() {
		return patologiaselected;
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



	
	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevopatologia;
	}
	
	public void setVentanaregistronuevopatologia(Window ventanaregistronuevopatologia) {
		this.ventanaregistronuevopatologia = ventanaregistronuevopatologia;
	}

	public List<PatologiaStatus> getPatologiastatues() {
		return patologiastatues;
	}

	public void setPatologiastatues(List<PatologiaStatus> patologiastatues) {
		this.patologiastatues = patologiastatues;
	}

	

}

