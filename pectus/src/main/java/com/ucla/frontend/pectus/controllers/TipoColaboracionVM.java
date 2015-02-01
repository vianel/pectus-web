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

import com.ucla.frontend.pectus.models.TipoColaboracion;
import com.ucla.frontend.pectus.controllers.TipoColaboracionFilter;
import com.ucla.frontend.pectus.services.ServicioTipoColaboracion;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;


public class TipoColaboracionVM {
	
	private TipoColaboracion tipocolaboracionselected;
	private Integer idSelected;
	private String nombreSelected;
	private String resp;
	private ListModelList<TipoColaboracion> tipoc = new ListModelList<TipoColaboracion>();
	private TipoColaboracion selectedItem;
	   
	   
	   public ListModelList<TipoColaboracion>getTipoColaboracion(){
			return tipoc;
		} 

		@NotifyChange("tipocolaboracion")
		public void setTipoColaboracion(ListModelList<TipoColaboracion> tipoc) {
			this.tipoc = tipoc;
		}


		public TipoColaboracion getSelectedItem() {
			return selectedItem;
		}

		@NotifyChange("selectedItem")
		public void setSelectedItem(TipoColaboracion selectedItem) {
			this.selectedItem = selectedItem;
		}

	private Window ventanaregistronuevotipocolaboracion;
	
	private static final String footerMensaje = "Esto son todos las colaboraciones";
	private TipoColaboracionFilter tipocolaboracionFilter = new TipoColaboracionFilter();
	List<TipoColaboracion> currentTipoColaboracion = ServicioTipoColaboracion.buscarTipoColaboracion();
	//private List<TipoColaboracionStatus> tipocolaboracionstatues = generateStatusList(currentTipoColaboracion);
	private boolean displayEdit = true;


	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	    
	    
	
	 
	public TipoColaboracionFilter getTipoColaboracionFilter() {
		return tipocolaboracionFilter;
	}

	public void setTipoColaboracionFilter(TipoColaboracionFilter tipocolaboracionFilter) {
		this.tipocolaboracionFilter = tipocolaboracionFilter;
	}

	@Command
	@NotifyChange({"modeltipocolaboracion", "footer"})
	public void abrirDialogoRegistrarTipoColaboracion(){

	   
		ventanaregistronuevotipocolaboracion = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoColaboracion.zul", null, null);
		
		ventanaregistronuevotipocolaboracion.doModal();
	}
	
	
	
	@Command
	@NotifyChange({"modeltipocolaboracion", "footer"})
	public void abrirDialogoEditarTipoColaboracion(){

	   
		ventanaregistronuevotipocolaboracion = (Window)Executions.createComponents("/vistas/dialogos/dlgEditarTipoColaboracio.zul", null, null);
		
		ventanaregistronuevotipocolaboracion.doModal();
	}
	
	
	//@Command
	/*@NotifyChange({"modelptipocolaboracion", "footer"})
	public void modificarTipoColaboracion(@BindingParam("tipocolaboracionStatus") TipoColaboracionStatus tipoc)
	{
	
		
		String response = null;
		response = ServicioTipoColaboracion.modificarTipoColaboracion(tipoc.getTipoColaboracion());
		if (response.equalsIgnoreCase("true"))
		{
			cambiarestatusedicion(tipoe);
			Clients.showNotification("Estudio con Nombre " + tipoe.getTipoEstudio().getNombre() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		} 
		List<TipoEstudio> tipoestudio = ServicioTipoEstudio.buscarTipoEstudio();
	}*/
	
	 //@Command
	 
	 /*public void cambiarestatusedicion(@BindingParam("tipoestudioStatus") TipoEstudioStatus tipoe) {
	        tipoe.setEditingStatus(!tipoe.getEditingStatus());
	        refreshRowTemplate(tipoe);
	    }*/
	
/*	public void refreshRowTemplate(TipoColaboracionStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	   //     BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	/*    }
    public List<TipoColaboracionStatus> getmodeltipocolaboracion() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return tipocolaboracionstatues;
    }*/
    
	public List<TipoColaboracion> getmodeltipocolaboracion() {
        return currentTipoColaboracion;
      }
    
    public String getFooter() {
        return String.format(footerMensaje, currentTipoColaboracion.size());
    }
    
    @Command
    @NotifyChange({"modeltipocolaboracion", "footer"})
    public void changeFilter() {
        currentTipoColaboracion = TipoColaboracionFilter.getFilterTipoColaboracion(tipocolaboracionFilter);
     //   tipocolaboracionstatues = generateStatusList(currentTipoColaboracion);
    }

    
    @Command
    public void editarTipoColaboracion()
    {
       String resp = ServicioTipoColaboracion.modificarTipoColaboracion(tipocolaboracionselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El tipo estudio ha sido moficado Exitosamente", true);
  		
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }

	@Command
	//@NotifyChange({ "modeltipocolaboracion", "footer" })
	public void guardarTipoColaboracion() throws Exception{
		String response = null;
		if (nombreSelected!= null) {
	
			tipocolaboracionselected = new TipoColaboracion();
			
			tipocolaboracionselected.setNombre(nombreSelected);
			
			

			response = ServicioTipoColaboracion.agregarTipoColaboracion(tipocolaboracionselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTipoColaboracion = ServicioTipoColaboracion.buscarTipoColaboracion();
			//	tipocolaboracionstatues = generateStatusList(currentTipoColaboracion);
				
				Clients.showNotification("Colaboracion Guardada", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}
		List<TipoColaboracion> tipocolaboracion = ServicioTipoColaboracion.buscarTipoColaboracion();


	}
    
	/*public static  List<TipoColaboracionStatus> generateStatusList(List<TipoColaboracion> tipoc)
	{
        List<TipoColaboracionStatus> tipocolaboracion = new ArrayList<TipoColaboracionStatus>();
        for(TipoColaboracion tc : tipoc) {
            tipocolaboracion.add(new TipoColaboracionStatus(tc, false));
        }
		return tipocolaboracion;
	}*/
	
	public TipoColaboracion getTipoColaboracionselected() {
		return tipocolaboracionselected;
	}



	public void setTipoColaboracionselected(TipoColaboracion tipocolaboracionselected) {
		this.tipocolaboracionselected = tipocolaboracionselected;
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


	
	public Window getVentanaregistronuevocolaboracion() {
		return ventanaregistronuevotipocolaboracion;
	}
	
	public void setVentanaregistronuevotipocolaboracion(Window ventanaregistronuevotipocolaboracion) {
		this.ventanaregistronuevotipocolaboracion = ventanaregistronuevotipocolaboracion;
	}

	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}
	
/*	public List<TipoColaboracionStatus> getTipoColaboracionstatues() {
		return tipocolaboracionstatues;
	}

	public void setTipoColaboracionstatues(List<TipoColaboracionStatus> tipocolaboracionstatues) {
		this.tipocolaboracionstatues = tipocolaboracionstatues;
	}*/

	

}

