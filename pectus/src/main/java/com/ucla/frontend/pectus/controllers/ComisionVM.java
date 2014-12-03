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

import com.ucla.frontend.pectus.models.Comision;
import com.ucla.frontend.pectus.controllers.ComisionFilter;
import com.ucla.frontend.pectus.services.ServicioComision;


public class ComisionVM {
	
	private Comision comisionselected;
	private Integer idSelected;
	private String nombreSelected;
	private String descripcionSelected;
	
	
	
	private ListModelList<Comision> comi = new ListModelList<Comision>();
	private Comision selectedItem;

	 
	   
	   
	   public ListModelList<Comision>getComision(){
			return comi;
		}
	   
	   

		@NotifyChange("comision")
		public void setComision(ListModelList<Comision> comi) {
			this.comi = comi;
		}


		public Comision getSelectedItem() {
			return selectedItem;
		}

		@NotifyChange("selectedItem")
		public void setSelectedItem(Comision selectedItem) {
			this.selectedItem = selectedItem;
		}

		
	   

	private Window ventanaregistronuevocomision;
	
	private static final String footerMensaje = "Esta son todas las comisiones";
	private ComisionFilter comisionFilter = new ComisionFilter();
	List<Comision> currentComision = ServicioComision.buscarComision();
	private List<ComisionStatus> comisionstatues = generateStatusList(currentComision);
	private boolean displayEdit = true;


	    public boolean isDisplayEdit() {
	        return displayEdit;
	    }
	    
	    
	
	 
	public ComisionFilter getComisionFilter() {
		return comisionFilter;
	}

	public void setComisionFilter(ComisionFilter comisionFilter) {
		this.comisionFilter = comisionFilter;
	}

	@Command
	@NotifyChange({"modelcomision", "footer"})
	public void abrirDialogoRegistrarComision(){

	   
		ventanaregistronuevocomision = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarComision.zul", null, null);
		
		ventanaregistronuevocomision.doModal();
	}
	
	
	
	@Command
	@NotifyChange({"modelcomision", "footer"})
	public void abrirDialogoEditarComision(){

	   
		ventanaregistronuevocomision = (Window)Executions.createComponents("/vistas/dialogos/dlgEditarComision.zul", null, null);
		
		ventanaregistronuevocomision.doModal();
	}
	
	
	@Command
	@NotifyChange({"modelpcomision", "footer"})
	public void modificarComision(@BindingParam("comisionStatus") ComisionStatus comi)
	{
	
		
		String response = null;
		response = ServicioComision.modificarComision(comi.getComision());
		if (response.equalsIgnoreCase("true"))
		{
			cambiarestatusedicion(comi);
			Clients.showNotification("Comision con Nombre " + comi.getComision().getNombre() + " Modificado Exitosamente", null, true);

		}else
		{
			Clients.showNotification("Error al modificar", true);
		} 
		List<Comision> comision = ServicioComision.buscarComision();
	}
	
	 @Command
	 
	 public void cambiarestatusedicion(@BindingParam("comisionStatus") ComisionStatus comi) {
	        comi.setEditingStatus(!comi.getEditingStatus());
	        refreshRowTemplate(comi);
	    }
	public void refreshRowTemplate(ComisionStatus lcs) {
	        /*
	         * This code is special and notifies ZK that the bean's value
	         * has changed as it is used in the template mechanism.
	         * This stops the entire Grid's data from being refreshed
	         */
	        BindUtils.postNotifyChange(null, null, lcs, "editingStatus");
	      
	    }
    public List<ComisionStatus> getmodelcomision() {
       // return new ListModelList<Paciente>(currentPaciente);
    	return comisionstatues;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, comisionstatues.size());
    }
    
    @Command
    @NotifyChange({"modelcomision", "footer"})
    public void changeFilter() {
        currentComision = ComisionFilter.getFilterComision(comisionFilter);
        comisionstatues = generateStatusList(currentComision);
    }


	@Command
	@NotifyChange({ "modelcomision", "footer" })
	public void guardarComision() throws Exception{
		String response = null;
		if (nombreSelected!= null) {
	
			comisionselected = new Comision();
			
			//comisionselected.setId(idSelected);
			comisionselected.setNombre(nombreSelected);
			comisionselected.setDescripcion(descripcionSelected);
			
			

			response = ServicioComision.agregarComision(comisionselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentComision= ServicioComision.buscarComision();
				comisionstatues = generateStatusList(currentComision);
				
				Clients.showNotification("Comision Guardada", null, true);
				//x.detach();

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}
		List<Comision> comision = ServicioComision.buscarComision();


	}
    
	public static  List<ComisionStatus> generateStatusList(List<Comision> comi)
	{
        List<ComisionStatus> comision = new ArrayList<ComisionStatus>();
        for(Comision co : comi) {
            comision.add(new ComisionStatus(co, false));
        }
		return comision;
	}
	public Comision getComisionselected() {
		return comisionselected;
	}



	public void setComisionselected(Comision comisionselected) {
		this.comisionselected = comisionselected;
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
		return ventanaregistronuevocomision;
	}
	
	public void setVentanaregistronuevocomision(Window ventanaregistronuevocomision) {
		this.ventanaregistronuevocomision = ventanaregistronuevocomision;
	}

	public List<ComisionStatus> getComisionstatues() {
		return comisionstatues;
	}

	public void setComisionstatues(List<ComisionStatus> comisionstatues) {
		this.comisionstatues = comisionstatues;
	}

	

}

