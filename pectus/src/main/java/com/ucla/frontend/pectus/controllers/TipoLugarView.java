
package com.ucla.frontend.pectus.controllers;




import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.TipoLugar;
import com.ucla.frontend.pectus.controllers.TipoLugarFilter;
import com.ucla.frontend.pectus.services.ServicioTipoLugar;


public class TipoLugarView{
	
	private  TipoLugar tipolugarselected;
	private Integer idSelected;
	private String nombreSelected;
	private String descripcionSelected;
	private Window ventanaregistronuevotipolugar;
	private ListModelList< TipoLugar> tipol = new ListModelList< TipoLugar>();

	private String resp;
	private static final String footerMensaje = "Esto son todos los Lugares";
	private TipoLugarFilter tipolugarFilter = new  TipoLugarFilter();
	private List< TipoLugar> currentTipoLugar;

	   
	@Init
	public void init(){
		this.currentTipoLugar = ServicioTipoLugar.buscarTipoLugar();
	}
	
	   public ListModelList<TipoLugar>getTipoLugar(){
			return tipol;
		}

		@NotifyChange("tipolugar")
		public void setTipoLugar(ListModelList<TipoLugar> tipol) {
			this.tipol = tipol;
		}


	@Command
	public void abrirDialogoRegistrarTipoLugar(){	
		
		ventanaregistronuevotipolugar = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoLugar.zul", null, null);
		ventanaregistronuevotipolugar.doModal();
			
	}
	
    public List<TipoLugar> getmodeltipolugar() {
      return currentTipoLugar;
    }
    
    public String getFooter() {
        return String.format(footerMensaje, currentTipoLugar.size());
    }
    
    @Command
    @NotifyChange({"modeltipolugar", "footer"})
    public void changeFilter() {
        currentTipoLugar = TipoLugarFilter.getFilterTipoLugar(tipolugarFilter);
    }
 
    @Command
    public void editarTipoLugar()
    {
       resp = ServicioTipoLugar.modificarTipoLugar(tipolugarselected);
      
      if (resp.equalsIgnoreCase("true"))
      {
  		Clients.showNotification("El tipo lugar ha sido moficado Exitosamente", true);
      }else
      {
  		Clients.showNotification("Error al modificar", true);
      }

    }
	@Command
	@NotifyChange({ "modeltipolugar", "footer" })
	public void guardarTipoLugar() throws Exception{
		String response = null;
		if (nombreSelected!= null && descripcionSelected != null) {
	
			tipolugarselected = new TipoLugar(idSelected,nombreSelected,descripcionSelected);
			

			response = ServicioTipoLugar.agregarTipoLugar(tipolugarselected);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTipoLugar = ServicioTipoLugar.buscarTipoLugar();
				getmodeltipolugar();
				Clients.showNotification("Lugar Guardado", null, true);
				

			}else
			{
				Clients.showNotification("Error al guardar", true);
			}
		}	else{
			
			Clients.showNotification("Porfavor ingrese todos los datos validos");
		}

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



	
	public TipoLugar getTipolugarselected() {
		return tipolugarselected;
	}



	public void setTipolugarselected(TipoLugar tipolugarselected) {
		this.tipolugarselected = tipolugarselected;
	}
	 
	public TipoLugarFilter getTipoLugarFilter() {
		return tipolugarFilter;
	}

	public void setTipoLugarFilter(TipoLugarFilter tipolugarFilter) {
		this.tipolugarFilter = tipolugarFilter;
	}


	public Window getVentanaregistronuevotipolugar() {
		return ventanaregistronuevotipolugar;
	}
	
	public void setVentanaregistronuevotipolugar(Window ventanaregistronuevotipolugar) {
		this.ventanaregistronuevotipolugar = ventanaregistronuevotipolugar;
	}



	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}



}



