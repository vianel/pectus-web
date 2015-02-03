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

import com.ucla.frontend.pectus.models.MotivoSolicitud;
import com.ucla.frontend.pectus.controllers.MotivoSolicitudFilter;
import com.ucla.frontend.pectus.reports.TipoestudioReportes;
//import com.ucla.frontend.pectus.reports.TipoestudioReportes;
import com.ucla.frontend.pectus.services.ServicioMotivoSolicitud;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;


public class MotivoSolicitudVM {
	
	private MotivoSolicitud motivosolicitudselected;
	private Integer idSelected;
	private String nombreSelected;
	private String descripcionSelected;
	private MotivoSolicitud motivosolicitud;
	private HashMap<String, MotivoSolicitud> motivosolicitudConsulta = new HashMap<String, MotivoSolicitud>();
	private Window ventanaregistronuevomotivosolicitud;
	private ListModelList<MotivoSolicitud> ms = new ListModelList<MotivoSolicitud>();

	private String resp;
	private static final String footerMensaje = "Esto son todos los Motivo de Solicitud";
	private MotivoSolicitudFilter motivosolicitudFilter = new MotivoSolicitudFilter();
	private List<MotivoSolicitud> currentMotivoSolicitud;
	
	   
	
	@Init
	public void init(){
		this.currentMotivoSolicitud = ServicioMotivoSolicitud.buscarMotivoSolicitud();
	
	}
	   public ListModelList<MotivoSolicitud>getMotivoSolicitud(){
			return ms;
		}

		@NotifyChange("motivosolicitud")
		public void setMotivoSolicitud(ListModelList<MotivoSolicitud> ms) {
			this.ms = ms;
		}


	@Command
	public void abrirDialogoRegistrarMotivoSolicitud(){	
		
		ventanaregistronuevomotivosolicitud = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarMotivoSolicitud.zul", null, null);
		ventanaregistronuevomotivosolicitud.doModal();
			
	}
	
    public List<MotivoSolicitud> getmodelmotivosolicitud() {
      return currentMotivoSolicitud;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, currentMotivoSolicitud.size());
    }
    
    @Command
    @NotifyChange({"modelmotivosolicitud", "footer"})
    public void changeFilter() {
        currentMotivoSolicitud = MotivoSolicitudFilter.getFilterMotivosolicitud(motivosolicitudFilter);
    }
 
    @Command
    public void editarMotivoSolicitud()
    {
       resp = ServicioMotivoSolicitud.modificarMotivoSolicitud(motivosolicitudselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El motivo de la solicitud ha sido moficado exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }
	@Command
	@NotifyChange({ "modelmotivosolicitud", "footer" })
	public void guardarMotivoSolicitud() throws Exception{
		
		String response = null;
		if (nombreSelected!= null && descripcionSelected != null) {
	
			motivosolicitudselected = new MotivoSolicitud(idSelected,nombreSelected,descripcionSelected);
			

			response = ServicioMotivoSolicitud.agregarMotivoSolicitud(motivosolicitudselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentMotivoSolicitud = ServicioMotivoSolicitud.buscarMotivoSolicitud();
				getmodelmotivosolicitud();
				Clients.showNotification("Solicitud Guardado", null, true);
				

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}

	}



	public void setMotivoSolicitudselected(MotivoSolicitud motivosolicitudselected) {
		this.motivosolicitudselected = motivosolicitudselected;
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



	
	public MotivoSolicitud getMotivoSolicitudselected() {
		return motivosolicitudselected;
	}



	public void setMotivoRechazoselected(MotivoSolicitud motivosolicitudselected) {
		this.motivosolicitudselected = motivosolicitudselected;
	}
	 
	public MotivoSolicitudFilter getMotivoSolicitudFilter() {
		return motivosolicitudFilter;
	}

	public void setMotivoSolicitudFilter(MotivoSolicitudFilter motivosolicitudFilter) {
		this.motivosolicitudFilter = motivosolicitudFilter;
	}


	public Window getVentanaregistronuevopaciente() {
		return ventanaregistronuevomotivosolicitud;
	}
	
	public void setVentanaregistronuevomotivosolicitud(Window ventanaregistronuevomotivosolicitud) {
		this.ventanaregistronuevomotivosolicitud = ventanaregistronuevomotivosolicitud;
	}



	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}
	





	

}

