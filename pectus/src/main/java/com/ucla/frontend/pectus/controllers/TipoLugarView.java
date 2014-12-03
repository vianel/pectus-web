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
import com.ucla.frontend.pectus.models.TipoLugar;
import com.ucla.frontend.pectus.controllers.TipoEstudioFilter;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;
import com.ucla.frontend.pectus.services.ServicioTipoLugar;


public class TipoLugarView {
	
	private TipoLugar tipolugarselected;
	private Integer idSelected;
	private String nombreSelected;
	private String descripcionSelected;
	
	
	
	private ListModelList<TipoLugar> tipol = new ListModelList<TipoLugar>();
	private TipoLugar selectedItem;

	 
	   
	   
	   public ListModelList<TipoLugar>getTipoLugars(){
			return tipol;
		}
	   
	   

		@NotifyChange("tipolugar")
		public void setTipoLugar(ListModelList<TipoLugar> tipol) {
			this.tipol = tipol;
		}


		public TipoLugar getSelectedItem() {
			return selectedItem;
		}

		@NotifyChange("selectedItem")
		public void setSelectedItem(TipoLugar selectedItem) {
			this.selectedItem = selectedItem;
		}

		
	   

	private Window ventanaregistronuevotipolugar;
	
	private static final String footerMensaje = "Esto son todos los lugares";
	private TipoLugarFilter tipolugarFilter = new TipoLugarFilter();
	List<TipoLugar> currentTipoLugar = ServicioTipoLugar.buscarTipoLugar();
	private List<TipoLugarStatus> tipolugarstatues = generateStatusList(currentTipoLugar);
	private boolean displayEdit = true;


	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	    
	    
	
	 
	public TipoLugarFilter getTipoLugarFilter() {
		return tipolugarFilter;
	}

	public void setTipoLugarFilter(TipoLugarFilter tipolugarFilter) {
		this.tipolugarFilter = tipolugarFilter;
	}

	@Command
	@NotifyChange({"modeltipolugar", "footer"})
	public void abrirDialogoRegistrarTipoLugar(){

	   
		ventanaregistronuevotipolugar = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoLugar.zul", null, null);
		
		ventanaregistronuevotipolugar.doModal();
	}
	
	
	
	@Command
	@NotifyChange({"modeltipolugar", "footer"})
	public void abrirDialogoEditarTipoLugar(){

	   
		ventanaregistronuevotipolugar = (Window)Executions.createComponents("/vistas/dialogos/dlgEditarTipoLugar.zul", null, null);
		
		ventanaregistronuevotipolugar.doModal();
	}
	
	
	@Command
	@NotifyChange({"modelptipolugar", "footer"})
	public void modificarTipoLugar(@BindingParam("tipolugarStatus") TipoLugarStatus tipol)
	{
	
		
		String response = null;
		response = ServicioTipoLugar.modificarTipoLugar(tipol.getTipoLugar());
		if (response.equalsIgnoreCase("true"))
		{
			cambiarestatusedicion(tipol);
			Clients.showNotification("lugar con Nombre " + tipol.getTipoLugar().getNombre() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		} 
		List<TipoLugar> tipolugar = ServicioTipoLugar.buscarTipoLugar();
	}
	
	 @Command
	 
	 public void cambiarestatusedicion(@BindingParam("tipolugarStatus") TipoLugarStatus tipol) {
	        tipol.setEditingStatus(!tipol.getEditingStatus());
	        refreshRowTemplate(tipol);
	    }
	public void refreshRowTemplate(TipoLugarStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	    }
    public List<TipoLugarStatus> getmodeltipolugar() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return tipolugarstatues;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, tipolugarstatues.size());
    }
    
    @Command
    @NotifyChange({"modeltipolugar", "footer"})
    public void changeFilter() {
        currentTipoLugar = TipoLugarFilter.getFilterTipoLugar(tipolugarFilter);
        tipolugarstatues = generateStatusList(currentTipoLugar);
    }


	@Command
	@NotifyChange({ "modeltipolugar", "footer" })
	public void guardarTipoLugar() throws Exception{
		String response = null;
		if (nombreSelected!= null) {
	
			tipolugarselected = new TipoLugar();
			
			tipolugarselected.setId(idSelected);
			tipolugarselected.setNombre(nombreSelected);
			tipolugarselected.setDescripcion(descripcionSelected);
			
			

			response = ServicioTipoLugar.agregarTipoLugar(tipolugarselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTipoLugar = ServicioTipoLugar.buscarTipoLugar();
				tipolugarstatues = generateStatusList(currentTipoLugar);
				
				Clients.showNotification("Lugar Guardado", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}
		List<TipoLugar> tipolugar = ServicioTipoLugar.buscarTipoLugar();
		


	}
    
	public static  List<TipoLugarStatus> generateStatusList(List<TipoLugar> tipol)
	{
        List<TipoLugarStatus> tipolugar = new ArrayList<TipoLugarStatus>();
        for(TipoLugar tl : tipol) {
            tipolugar.add(new TipoLugarStatus(tl, false));
        }
		return tipolugar;
	}
	public TipoLugar getTipoLugarselected() {
		return tipolugarselected;
	}



	public void setTipoLugarselected(TipoLugar tipolugarselected) {
		this.tipolugarselected = tipolugarselected;
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



	
	public Window getVentanaregistronuevotipolugar() {
		return ventanaregistronuevotipolugar;
	}
	
	public void setVentanaregistronuevotipoestudio(Window ventanaregistronuevotipolugar) {
		this.ventanaregistronuevotipolugar = ventanaregistronuevotipolugar;
	}

	public List<TipoLugarStatus> getTipoLugarstatues() {
		return tipolugarstatues;
	}

	public void setTipoLugarstatues(List<TipoLugarStatus> tipolugarstatues) {
		this.tipolugarstatues = tipolugarstatues;
	}

	

}

