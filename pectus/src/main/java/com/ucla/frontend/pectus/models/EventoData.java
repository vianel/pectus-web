package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.controllers.EventoFilter;
import com.ucla.frontend.pectus.controllers.PacienteFilter;

public class EventoData {

	private static List<Evento> eventos = new ArrayList<Evento>();
	
	static{
		//eventos.add(new Evento("Barquisimeto ejercita",new Date("2014/01/01"),"Caminata, festival, musical","Barquisimeto","Barquisimeto"));
		//eventos.add(new Evento("Pro-Fondos lara",new Date("2014/01/01"),"Caminata, festival, musical","Barquisimeto","Barquisimeto"));
		//eventos.add(new Evento("Cantamos todos",new Date("2014/01/01"),"Caminata, festival, musical","Barquisimeto","Barquisimeto"));
		//eventos.add(new Evento("Orientando hogares",new Date("2014/01/01"),"Caminata, festival, musical","Barquisimeto","Barquisimeto"));
		
	}
	
	public static List<Evento> getAllEventos(){
		return new ArrayList<Evento>(eventos);
	}
	
	public static List<Evento> getEventoByNombre(String nombre){
		List<Evento> lista= new ArrayList<Evento>();
		for (Iterator<Evento> i = eventos.iterator(); i.hasNext();){
			Evento tmp = i.next();
			if(tmp.getNombre().equalsIgnoreCase(nombre)){
				lista.add(tmp);
			}
		}
		return lista;
	}
	
	public static List<Evento> getFilterEventos(EventoFilter eventoFilter){
		List<Evento> lista = new ArrayList<Evento>();
		String nombre = eventoFilter.getNombre().toLowerCase();
		String fecha = eventoFilter.getFecha().toLowerCase();
		String descripcion = eventoFilter.getDescripcion().toLowerCase();
		
		for(Iterator<Evento> i = eventos.iterator(); i.hasNext();){
			Evento tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getFecha().toGMTString().contains(fecha)&&
			   tmp.getDescripcion().toLowerCase().contains(descripcion)){
			
				lista.add(tmp);
			}
				
		}
		return lista;
	}
	
}
