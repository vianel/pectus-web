package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.services.ServicioLugar;

public class LugarFilter {

	private String tipolugar = "";
	private String nombre = "";
	static List<Lugar> lugares = ServicioLugar.buscarLugar();
	

	public String getTipolugar() {
		return tipolugar;
	}
	public void setTipolugar(String tipolugar) {
		this.tipolugar = tipolugar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static List<Lugar> getLugares() {
		return lugares;
	}
	public static void setLugares(List<Lugar> lugares) {
		LugarFilter.lugares = lugares;
	}
	public static List<Lugar> getFilterLugar(LugarFilter lugarFilter){
		List<Lugar> someLugar = new ArrayList<Lugar>();
		String tipolugar = lugarFilter.getTipolugar().toLowerCase();
		String nombre = lugarFilter.getNombre().toLowerCase();
		
		for(Iterator<Lugar> i = lugares.iterator(); i.hasNext();){
			Lugar tmp = i.next();
			if(tmp.getTipoLugar().getNombre().toLowerCase().contains(tipolugar) &&
			   tmp.getNombre().toLowerCase().contains(nombre)){
			
				someLugar.add(tmp);
			}
				
		}
		return someLugar;
		
}

}