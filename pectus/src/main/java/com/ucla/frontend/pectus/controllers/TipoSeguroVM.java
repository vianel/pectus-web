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

import com.ucla.frontend.pectus.models.TipoSeguro;
import com.ucla.frontend.pectus.controllers.TipoSeguroFilter;
import com.ucla.frontend.pectus.reports.TipoestudioReportes;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;
//import com.ucla.frontend.pectus.reports.TipoestudioReportes;
import com.ucla.frontend.pectus.services.ServicioTipoSeguro;


public class TipoSeguroVM {
	
	private TipoSeguro tiposeguroselected;
	private Integer idSelected;
	private String nombreSelected;
	private String descripcionSelected;
	private TipoSeguro tiposeguro;
	private HashMap<String, TipoSeguro> tiposeguroConsulta = new HashMap<String, TipoSeguro>();
	private Window ventanaregistronuevotiposeguro;
	private ListModelList<TipoSeguro> tipos = new ListModelList<TipoSeguro>();

	private String resp;
	private static final String footerMensaje = "Esto son todos los Seguros";
	private TipoSeguroFilter tiposeguroFilter = new TipoSeguroFilter();
	private List<TipoSeguro> currentTipoSeguro;
	//private TipoestudioReportes tpr;
	   
	
	@Init
	public void init(){
		this.currentTipoSeguro = ServicioTipoSeguro.buscarTipoSeguro();
		
	}
	   
	public ListModelList<TipoSeguro>getTipoSeguro(){
			return tipos;
		}

		@NotifyChange("tiposeguro")
		public void setTipoSeguro(ListModelList<TipoSeguro> tipos) {
			this.tipos = tipos;
		}


	@Command
	public void abrirDialogoRegistrarTipoSeguro(){	
		
		ventanaregistronuevotiposeguro = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoSeguro.zul", null, null);
		ventanaregistronuevotiposeguro.doModal();
			
	}
	
    public List<TipoSeguro> getmodeltiposeguro() {
      return currentTipoSeguro;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, currentTipoSeguro.size());
    }
    
    @Command
    @NotifyChange({"modeltipoestudio", "footer"})
    public void changeFilter() {
        currentTipoSeguro = TipoSeguroFilter.getFilterTipoSeguro(tiposeguroFilter);
    }
 
    @Command
    public void editarTipoSeguro()
    {
       resp = ServicioTipoSeguro.modificarTipoSeguro(tiposeguroselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El tipo de seguro ha sido moficado Exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }
	@Command
	@NotifyChange({ "modeltiposeguro", "footer" })
	public void guardarTipoSeguro() throws Exception{
		String response = null;
		if (nombreSelected!= null && descripcionSelected != null) {
	
			tiposeguroselected = new TipoSeguro(idSelected,nombreSelected,descripcionSelected);
			

			response = ServicioTipoSeguro.agregarTipoSeguro(tiposeguroselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTipoSeguro = ServicioTipoSeguro.buscarTipoSeguro();
				getmodeltiposeguro();
				Clients.showNotification("Seguro Guardado", null, true);
				

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}
		

	}



	public void setTipoSeguroselected(TipoSeguro tiposeguroselected) {
		this.tiposeguroselected = tiposeguroselected;
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



	
	public TipoSeguro getTiposeguroselected() {
		return tiposeguroselected;
	}



	public void setTiposeguroselected(TipoSeguro tiposeguroselected) {
		this.tiposeguroselected = tiposeguroselected;
	}
	 
	public TipoSeguroFilter getTipoSeguroFilter() {
		return tiposeguroFilter;
	}

	public void setTipoSeguroFilter(TipoSeguroFilter tiposeguroFilter) {
		this.tiposeguroFilter = tiposeguroFilter;
	}


	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevotiposeguro;
	}
	
	public void setVentanaregistronuevotiposeguro(Window ventanaregistronuevotiposeguro) {
		this.ventanaregistronuevotiposeguro = ventanaregistronuevotiposeguro;
	}



	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}
	





	

}

