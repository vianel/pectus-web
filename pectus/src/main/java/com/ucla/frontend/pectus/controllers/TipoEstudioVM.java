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

import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.controllers.TipoEstudioFilter;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;


public class TipoEstudioVM {
	
	private TipoEstudio tipoestudioselected;
	private Integer idSelected;
	private String nombreSelected;
	private String descripcionSelected;
	
	
	
	private ListModelList<TipoEstudio> tipoe = new ListModelList<TipoEstudio>();
	private TipoEstudio selectedItem;

	 
	   
	   
	   public ListModelList<TipoEstudio>getTipoEstudios(){
			return tipoe;
		}
	   
	   

		@NotifyChange("tipoestudio")
		public void setTipoEstudio(ListModelList<TipoEstudio> tipoe) {
			this.tipoe = tipoe;
		}


		public TipoEstudio getSelectedItem() {
			return selectedItem;
		}

		@NotifyChange("selectedItem")
		public void setSelectedItem(TipoEstudio selectedItem) {
			this.selectedItem = selectedItem;
		}

		
	   

	private Window ventanaregistronuevotipoestudio;
	
	private static final String footerMensaje = "Esto son todos los pacientes";
	private TipoEstudioFilter tipoestudioFilter = new TipoEstudioFilter();
	List<TipoEstudio> currentTipoEstudio = ServicioTipoEstudio.buscarTipoEstudio();
	private List<TipoEstudioStatus> tipoestudiostatues = generateStatusList(currentTipoEstudio);
	private boolean displayEdit = true;


	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	    
	    
	
	 
	public TipoEstudioFilter getTipoEstudioFilter() {
		return tipoestudioFilter;
	}

	public void setTipoEstudioFilter(TipoEstudioFilter tipoestudioFilter) {
		this.tipoestudioFilter = tipoestudioFilter;
	}

	@Command
	@NotifyChange({"modeltipoestudio", "footer"})
	public void abrirDialogoRegistrarTipoEstudio(){

	   
		ventanaregistronuevotipoestudio = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoEstudio.zul", null, null);
		
		ventanaregistronuevotipoestudio.doModal();
	}
	
	
	
	@Command
	@NotifyChange({"modeltipoestudio", "footer"})
	public void abrirDialogoEditarTipoEstudio(){

	   
		ventanaregistronuevotipoestudio = (Window)Executions.createComponents("/vistas/dialogos/dlgEditarTipoEstudio.zul", null, null);
		
		ventanaregistronuevotipoestudio.doModal();
	}
	
	
	@Command
	@NotifyChange({"modelptipoestudio", "footer"})
	public void modificarTipoEstudio(@BindingParam("tipoestudioStatus") TipoEstudioStatus tipoe)
	{
	
		
		String response = null;
		response = ServicioTipoEstudio.modificarTipoEstudio(tipoe.getTipoEstudio());
		if (response.equalsIgnoreCase("true"))
		{
			cambiarestatusedicion(tipoe);
			Clients.showNotification("Estudio con Nombre " + tipoe.getTipoEstudio().getNombre() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		} 
		List<TipoEstudio> tipoestudio = ServicioTipoEstudio.buscarTipoEstudio();
	}
	
	 @Command
	 
	 public void cambiarestatusedicion(@BindingParam("tipoestudioStatus") TipoEstudioStatus tipoe) {
	        tipoe.setEditingStatus(!tipoe.getEditingStatus());
	        refreshRowTemplate(tipoe);
	    }
	public void refreshRowTemplate(TipoEstudioStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	    }
    public List<TipoEstudioStatus> getmodeltipoestudio() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return tipoestudiostatues;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, tipoestudiostatues.size());
    }
    
    @Command
    @NotifyChange({"modeltipoestudio", "footer"})
    public void changeFilter() {
        currentTipoEstudio = TipoEstudioFilter.getFilterTipoEstudio(tipoestudioFilter);
        tipoestudiostatues = generateStatusList(currentTipoEstudio);
    }


	@Command
	@NotifyChange({ "modeltipoestudio", "footer" })
	public void guardarTipoEstudio() throws Exception{
		String response = null;
		if (nombreSelected!= null) {
	
			tipoestudioselected = new TipoEstudio();
			
			tipoestudioselected.setId(idSelected);
			tipoestudioselected.setNombre(nombreSelected);
			tipoestudioselected.setDescripcion(descripcionSelected);
			
			

			response = ServicioTipoEstudio.agregarTipoEstudio(tipoestudioselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTipoEstudio = ServicioTipoEstudio.buscarTipoEstudio();
				tipoestudiostatues = generateStatusList(currentTipoEstudio);
				
				Clients.showNotification("Estudio Guardado", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}
		List<TipoEstudio> tipoestudio = ServicioTipoEstudio.buscarTipoEstudio();


	}
    
	public static  List<TipoEstudioStatus> generateStatusList(List<TipoEstudio> tipoe)
	{
        List<TipoEstudioStatus> tipoestudio = new ArrayList<TipoEstudioStatus>();
        for(TipoEstudio te : tipoe) {
            tipoestudio.add(new TipoEstudioStatus(te, false));
        }
		return tipoestudio;
	}
	public TipoEstudio getTipoEstudioselected() {
		return tipoestudioselected;
	}



	public void setTipoEstudioselected(TipoEstudio tipoestudioselected) {
		this.tipoestudioselected = tipoestudioselected;
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



	public String getDescripcionSelected() {
		return descripcionSelected;
	}



	public void setDescripcionSelected(String descripcionSelected) {
		this.descripcionSelected = descripcionSelected;
	}



	
	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevotipoestudio;
	}
	
	public void setVentanaregistronuevotipoestudio(Window ventanaregistronuevotipoestudio) {
		this.ventanaregistronuevotipoestudio = ventanaregistronuevotipoestudio;
	}

	public List<TipoEstudioStatus> getTipoEstudiostatues() {
		return tipoestudiostatues;
	}

	public void setTipoEstudiostatues(List<TipoEstudioStatus> tipoestudiostatues) {
		this.tipoestudiostatues = tipoestudiostatues;
	}

	

}

