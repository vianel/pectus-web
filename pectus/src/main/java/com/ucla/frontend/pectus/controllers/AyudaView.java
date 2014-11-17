package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Init;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.AyudaData;
import com.ucla.frontend.pectus.models.EstudioSolicitud;

public class AyudaView {

	private Ayuda selected;
	private List<Ayuda> ayudas = new ArrayList<Ayuda>(new AyudaData().getAllAyudas());
	private List<EstudioSolicitud> estudios = new ArrayList<EstudioSolicitud>(); 
	
	@Init
	public void init(){
		selected = ayudas.get(0);
	}

	public Ayuda getSelected() {
		List<EstudioSolicitud> estudios = new ArrayList<EstudioSolicitud>(selected.getListaEstudiosSolictud());
		return selected;
	}

	public void setSelected(Ayuda selected) {
		this.selected = selected;
	}

	public List<Ayuda> getAyudas() {
		
		return ayudas;
	}

	public void setAyudas(List<Ayuda> ayudas) {
		this.ayudas = ayudas;
	}
	
	
		
}
