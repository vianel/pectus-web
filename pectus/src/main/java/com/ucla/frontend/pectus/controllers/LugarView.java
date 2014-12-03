package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.TipoLugar;
import com.ucla.frontend.pectus.services.ServicioLugar;
import com.ucla.frontend.pectus.services.ServicioCiudad;
import com.ucla.frontend.pectus.services.ServicioTipoLugar;
import com.ucla.frontend.pectus.controllers.LugarStatus;


public class LugarView {
	
	private Lugar lugarselected;  
	private Integer idselected;
	private TipoLugar tipoluselected;
	private Estado estadoselected;
	private Ciudad ciudadselected;
	private String nombreselected;
	private String direccionselected;
	private String tlffijoselected;
	
	
	private List<Ciudad> listaciudad;
    private List<Estado> listaestado;
	
	private Window ventanaregistronuevolugar;
	private List<TipoLugar> listatipolugar = ServicioTipoLugar.buscarTipoLugar(); //Servicio tipo lugar
	private static final String footerMensaje = "Estas son todos los lugares";
	private LugarFilter lugarFilter = new LugarFilter();
	List<Lugar> currentLugar = ServicioLugar.buscarLugar(); //servicio LUGAR
	private List<LugarStatus> lugarStatus = generateStatusList(currentLugar);
	private boolean displayEdit = true;
	
	
	public boolean isDisplayEdit() {
        return displayEdit;
    }
  
	public void refreshRowTemplate(LugarStatus ls) {
        /*
         * This code is special and notifies ZK that the bean's value
         * has changed as it is used in the template mechanism.
         * This stops the entire Grid's data from being refreshed
         */
        BindUtils.postNotifyChange(null, null, ls, "editingStatus");
      
    }  
	// SERVICIO ESTADO
	@Command
	@NotifyChange({"listaciudad","listaestado"})
	public void inicializarAtributos() throws Exception{
		
		listaestado = ServicioCiudad.buscarEstados();	
	}
	// SERVICIO CIUDAD 
	@Command
	@NotifyChange({"listaciudad"})
	public void cambioEstado() throws Exception{
		listaciudad = ServicioCiudad.buscarCiudades(estadoselected);
    }
	// FILTROS DEL LUGAR
	public LugarFilter getLugarFilter() {
			return lugarFilter;
	}

	public void setLugarFilter(LugarFilter lugarFilter) {
			this.lugarFilter = lugarFilter;
	}
	
	@Command
	@NotifyChange({"modellugar", "footer"})
	public void abrirDialogoRegistrarLugar(){
        
		ventanaregistronuevolugar = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarLugar.zul", null, null);
		ventanaregistronuevolugar.doModal();
	}
	
	
	
/*********************** GUARDAR UN LUGAR ***************************************/
	
	@Command
	@NotifyChange({ "modellugar", "footer" })
	public void guardarLugar(@BindingParam("cmp") Window x) throws Exception{
		   String response=null;
	  if (tipoluselected.getId()!= null){
			
		    lugarselected = new Lugar();
			
		    lugarselected.setTipoLugar(tipoluselected);
		    lugarselected.setNombre(nombreselected);
		    lugarselected.setCiudad(ciudadselected);
		    lugarselected.setDireccion(direccionselected);
		    lugarselected.setTlffijo(tlffijoselected);
			
			response = ServicioLugar.agregarLugar(lugarselected);
			if (response.equalsIgnoreCase("true"))
			{
				Clients.showNotification("Lugar Guardado", null, true);
			//	x.detach(); ver esto mosca revisar

			}else{
			
				Clients.showNotification("Error al guardar", true);
			}
		}
	     else{
	     
	    Clients.showNotification("Porfavor ingrese todos los datos validos");
	}
	}
	
	
	
	
 /*****************    MODEL LUGAR PARA LLAMAR AL DIALOGO   ********************/
	
	  public List<LugarStatus> getmodellugar() {
	    	return lugarStatus;
	    }
	    
	    public String getFooter() {
	        return String.format(footerMensaje, lugarStatus.size());
	    }
	    public static  List<LugarStatus> generateStatusList(List<Lugar> lugr)
		{
	        List<LugarStatus> lugares = new ArrayList<LugarStatus>();
	        for(Lugar lu : lugr) {
	        	lugares.add(new LugarStatus(lu, false));
	        }
			return lugares;
		}
	 
	    // FILTRAR LUGAR
	 @Command
     @NotifyChange({"modellugar", "footer"})
		 public void changeFilter() {
		    currentLugar = lugarFilter.getFilterLugar(lugarFilter);
            lugarStatus = generateStatusList(currentLugar);
         }

	
	
	
	
/*********************** GETTERS Y SETTERS **************************************************/
	 
	 public List<LugarStatus> getLugarstatues() {
			return lugarStatus;
		}

		public void setLugarstatues(List<LugarStatus> lugarstatues) {
			this.lugarStatus = lugarstatues;
		}
	public Lugar getLugarselected() {
		return lugarselected;
	}
	public void setLugarselected(Lugar lugarselected) {
		this.lugarselected = lugarselected;
	}
	public Integer getIdselected() {
		return idselected;
	}
	public void setIdselected(Integer idselected) {
		this.idselected = idselected;
	}
	public TipoLugar getTipoluselected() {
		return tipoluselected;
	}
	public void setTipoluselected(TipoLugar tipoluselected) {
		this.tipoluselected = tipoluselected;
	}
	public Estado getEstadoselected() {
		return estadoselected;
	}

	public void setEstadoselected(Estado estadoselected) {
		this.estadoselected = estadoselected;
	}
	
	public Ciudad getCiudadselected() {
		return ciudadselected;
	}
	public void setCiudadselected(Ciudad ciudadselected) {
		this.ciudadselected = ciudadselected;
	}
	public String getNombreselected() {
		return nombreselected;
	}
	public void setNombreselected(String nombreselected) {
		this.nombreselected = nombreselected;
	}
	public String getDireccionselected() {
		return direccionselected;
	}
	public void setDireccionselected(String direccionselected) {
		this.direccionselected = direccionselected;
	}
	public String getTlffijoselected() {
		return tlffijoselected;
	}
	public void setTlffijoselected(String tlffijoselected) {
		this.tlffijoselected = tlffijoselected;
	}

	public List<Ciudad> getListaciudad() {
		return listaciudad;
	}

	public void setListaciudad(List<Ciudad> listaciudad) {
		this.listaciudad = listaciudad;
	}

	public List<Estado> getListaestado() {
		return listaestado;
	}

	public void setListaestado(List<Estado> listaestado) {
		this.listaestado = listaestado;
	}

	public List<TipoLugar> getListatipolugar() {  
		return listatipolugar;
	}

	public void setListatipolugar(List<TipoLugar> listatipolugar) { 
		this.listatipolugar = listatipolugar;
	}

	
	
	
	
	
}
