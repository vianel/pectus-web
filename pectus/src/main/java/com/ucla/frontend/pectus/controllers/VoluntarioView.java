package com.ucla.frontend.pectus.controllers;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.services.ServicioVoluntario;

public class VoluntarioView {

	private VoluntarioFilter voluntarioFilter;
	List<Voluntario> listaVoluntarios = ServicioVoluntario.buscarVoluntario();
	
	
	  public List<Voluntario> getmodelvoluntario() {
	        return new ListModelList<Voluntario>(listaVoluntarios);
	  
	    }
	
	 public VoluntarioFilter getVoluntarioFilter() {
		return voluntarioFilter;
	}



	public void setVoluntarioFilter(VoluntarioFilter voluntarioFilter) {
		this.voluntarioFilter = voluntarioFilter;
	}



	public List<Voluntario> getListaVoluntarios() {
		return listaVoluntarios;
	}



	public void setListaVoluntarios(List<Voluntario> listaVoluntarios) {
		this.listaVoluntarios = listaVoluntarios;
	}



	@Command
	    @NotifyChange({"modelvoluntario", "footer"})
	    public void changeFilter() {
	        listaVoluntarios = VoluntarioFilter.getFilterVoluntarios(voluntarioFilter);
	    }
}
