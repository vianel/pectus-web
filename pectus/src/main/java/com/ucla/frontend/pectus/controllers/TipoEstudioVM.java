package com.ucla.frontend.pectus.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
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
	private TipoEstudio tipoEstudio;
	private HashMap<String, TipoEstudio> tipoestaudioConsulta = new HashMap<String, TipoEstudio>();
	private Window ventanaregistronuevotipoestudio;
	private ListModelList<TipoEstudio> tipoe = new ListModelList<TipoEstudio>();

	private String resp;
	private static final String footerMensaje = "Esto son todos los Estudios";
	private TipoEstudioFilter tipoestudioFilter = new TipoEstudioFilter();
	private List<TipoEstudio> currentTipoEstudio;

	   
	@Init
	public void init(){
		this.currentTipoEstudio = ServicioTipoEstudio.buscarTipoEstudio();
	}
	
	   public ListModelList<TipoEstudio>getTipoEstudios(){
			return tipoe;
		}

		@NotifyChange("tipoestudio")
		public void setTipoEstudio(ListModelList<TipoEstudio> tipoe) {
			this.tipoe = tipoe;
		}


	@Command
	public void abrirDialogoRegistrarTipoEstudio(){	
		
		ventanaregistronuevotipoestudio = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoEstudio.zul", null, null);
		ventanaregistronuevotipoestudio.doModal();
			
	}
	
    public List<TipoEstudio> getmodeltipoestudio() {
      return currentTipoEstudio;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, currentTipoEstudio.size());
    }
    
    @Command
    @NotifyChange({"modeltipoestudio", "footer"})
    public void changeFilter() {
        currentTipoEstudio = TipoEstudioFilter.getFilterTipoEstudio(tipoestudioFilter);
    }
 
    @Command
    public void editarTipoEstudio()
    {
       resp = ServicioTipoEstudio.modificarTipoEstudio(tipoestudioselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El tipo estudio ha sido moficado Exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }
	@Command
	@NotifyChange({ "modeltipoestudio", "footer" })
	public void guardarTipoEstudio() throws Exception{
		String response = null;
		if (nombreSelected!= null && descripcionSelected != null) {
	
			tipoestudioselected = new TipoEstudio(idSelected,nombreSelected,descripcionSelected);
			

			response = ServicioTipoEstudio.agregarTipoEstudio(tipoestudioselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTipoEstudio = ServicioTipoEstudio.buscarTipoEstudio();
				getmodeltipoestudio();
				Clients.showNotification("Estudio Guardado", null, true);
				

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}

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



	
	public TipoEstudio getTipoestudioselected() {
		return tipoestudioselected;
	}



	public void setTipoestudioselected(TipoEstudio tipoestudioselected) {
		this.tipoestudioselected = tipoestudioselected;
	}
	 
	public TipoEstudioFilter getTipoEstudioFilter() {
		return tipoestudioFilter;
	}

	public void setTipoEstudioFilter(TipoEstudioFilter tipoestudioFilter) {
		this.tipoestudioFilter = tipoestudioFilter;
	}


	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevotipoestudio;
	}
	
	public void setVentanaregistronuevotipoestudio(Window ventanaregistronuevotipoestudio) {
		this.ventanaregistronuevotipoestudio = ventanaregistronuevotipoestudio;
	}



	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}






	

}

