package com.ucla.frontend.pectus.controllers;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.services.ServicioColaboracion;

public class ColaboracionView {
	
	private ColaboracionFilter colaboracionFilter = new ColaboracionFilter();
	List<Colaboracion> listaColaboraciones = ServicioColaboracion.buscarColaboraciones();
	
	public List<Colaboracion> getmodelcolaboracion() {
        return new ListModelList<Colaboracion>(listaColaboraciones);
  
    }

	public ColaboracionFilter getColaboracionFilter() {
		return colaboracionFilter;
	}

	public void setColaboracionFilter(ColaboracionFilter colaboracionFilter) {
		this.colaboracionFilter = colaboracionFilter;
	}

	public List<Colaboracion> getListaColaboraciones() {
		return listaColaboraciones;
	}

	public void setListaColaboraciones(List<Colaboracion> listaColaboraciones) {
		this.listaColaboraciones = listaColaboraciones;
	}
	@Command
    @NotifyChange({"modelcolaboracion"})
    public void changeFilter() {
        listaColaboraciones = ColaboracionFilter.getFilterColaboraciones(colaboracionFilter);
    }
	

}
