package com.ucla.frontend.pectus.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.controllers.ActividadFilter;

public class ActividadData {

	private static List<Actividad> actividades = new ArrayList<Actividad>();
	private static List<Lugar> lugares = new ArrayList<Lugar>();
	private static List<Ciudad> ciudades = new ArrayList<Ciudad>();
	private static List<Estado> estados = new ArrayList<Estado>();

	static{
		estados.add(new Estado(1,"LARA",true));
		estados.add(new Estado(2, "TRUJILLO", true));
	}
	
	static{
		ciudades.add(new Ciudad(1, "Barquisimeto", estados.get(0), true));
		ciudades.add(new Ciudad(2, "Cabudare", estados.get(0), true));
	}
	
	static{
		lugares.add(new Lugar(1,1,ciudades.get(0),"Hotel Tiffany","Urb del este","04204 29493", true));
		lugares.add(new Lugar(2,2,ciudades.get(1),"Clinica Canabal","Urb del este","53454 29493", true));

		
	}
	

	static{
		actividades.add(new Actividad(1, lugares.get(0), 1, "03-04-2014", "08:00", "Ninguno", (float) 10.00, "6 horas", 42, "Charla informativa", "muy importante",true));
		actividades.add(new Actividad(1, lugares.get(1), 1, "12-08-2014", "08:00", "Ninguno", (float) 10.00, "6 horas", 42, "Charla prevencion", "contactar expertos faltantes",true));
		
	}


	public static List<Actividad> getAllactividades(){
		return new ArrayList<Actividad>(actividades);
	}
	
	public static Actividad[] getAllActividadArray(){
		return actividades.toArray(new Actividad[actividades.size()]);
	}
	
	public static List<Actividad> getFilteractividades(ActividadFilter actividadFilter){
		List<Actividad> someactividades = new ArrayList<Actividad>();
		String fecha = actividadFilter.getFecha().toLowerCase();
		
		
		for(Iterator<Actividad> i = actividades.iterator(); i.hasNext();){
			Actividad tmp = i.next();
			if(tmp.getFecha().toLowerCase().contains(fecha) ){
			
				someactividades.add(tmp);
			}
				
		}
		return someactividades;
	}
	
	public static List<Actividad> getactividadesByFecha(String fecha){
		List<Actividad> someactividades = new ArrayList<Actividad>();
		for (Iterator<Actividad> i = actividades.iterator(); i.hasNext();){
			Actividad tmp = i.next();
			if(tmp.getFecha().equalsIgnoreCase(fecha)){
				someactividades.add(tmp);
			}
		}
		return someactividades;
	
	
	}
	
}
