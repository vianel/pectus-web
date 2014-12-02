package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ListModel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.models.ActividadData;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.services.ServicioActividad;
import com.ucla.frontend.pectus.services.ServicioCiudad;
import com.ucla.frontend.pectus.services.ServicioLugar;

public class ActividadVM {
	
	private List<Lugar> listalugar;
	private Lugar lugarSelected;
	private Actividad actividadSelected;
    private static final String footerMensajeactividad = "Estas son todas los Actividades";
	private ActividadFilter actividadFilter = new ActividadFilter();
	static List<Actividad> currentActividad = ServicioActividad.buscaractividades();
	
	
	@Command
	@NotifyChange({"listalugar"})
	public void inicializarAtributos() throws Exception{

		
		
		listalugar = ServicioLugar.buscarlugares();
		
	}

	

	public ActividadFilter getActividadFilter(){
		return actividadFilter;
	}
	
	public List<Actividad> getmodelactividad(){
		return new ListModelList<Actividad>(currentActividad);
	
	}
	public String getfooter(){
		return String.format(footerMensajeactividad, currentActividad.size());
	}
    @Command
    @NotifyChange({"modelactividad", "footerMensajeactividad"})
    public void changeFilteractividad() {
        currentActividad = actividadFilter.getFilteractividades(actividadFilter);
    }

    @Command
    public void showRegistroresultado(Event e) {
        //create a window programmatically and use it as a modal dialog.
    	
		        Window window = (Window)Executions.createComponents(
		                "/vistas/gestionactividades/registroresultadoactividad.zul", null, null);
		        window.doModal();
		//       window.setTitle(lblseleccion.getValue().toString());
		    	System.out.println(actividadSelected.getDescripcion());
    	
    }

    public void showResultadoactividad()
    {
    	
    }
	public List<Lugar> getListalugar() {
		return listalugar;
	}

	public void setListalugar(List<Lugar> listalugar) {
		this.listalugar = listalugar;
	}

	public Lugar getLugarSelected() {
		return lugarSelected;
	}

	public void setLugarSelected(Lugar lugarSelected) {
		this.lugarSelected = lugarSelected;
	}



	public static List<Actividad> getCurrentActividad() {
		return currentActividad;
	}



	public static void setCurrentActividad(List<Actividad> currentActividad) {
		ActividadVM.currentActividad = currentActividad;
	}
	
	

}
