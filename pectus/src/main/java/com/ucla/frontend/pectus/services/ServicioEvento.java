package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Lugar;

public class ServicioEvento {

	public static List<Evento> buscarEventos() {

		List<Evento> eventos = new ArrayList<Evento>();

		Resty resty = new Resty();
		JSONResource jsResource = null;

		try {
			jsResource = resty.json("http://127.0.0.1:5000/evento/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			String ok = jsResource.get("ok").toString();
			
			if (ok.equalsIgnoreCase("true")) {
				String strEv = jsResource.get("eventos").toString();
				
				JSONArray arrEventos = new JSONArray(strEv);
				
				for(int i=0; i < arrEventos.length(); i++){
					
					Evento evento = new Evento();
					
					JSONObject obj = arrEventos.getJSONObject(i);

					evento.setId(Integer.parseInt(obj.get("id").toString())); //Integer.parseInt(obj.get("id").toString())
					evento.setDescripcion(obj.get("descripcion").toString()); //fecha
					evento.setLugar(obtenerLugar(obj.get("lugar").toString()));
					evento.setFecha(convertirFecha(obj.getString("fecha").toString()));
//					evento.setFecha(new Date());
					evento.setNombre(obj.get("nombre").toString());
					
					
					eventos.add(evento);
					
				}// fin for
				
				
			} // fin if
			
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return eventos;
	}
	
	private static Lugar obtenerLugar(String s) {
		// TODO Auto-generated method stub
		Lugar lugar = new Lugar();
		try {
			JSONObject objjson = new JSONObject(s);
			lugar.setNombre(objjson.getString("nombre"));
			lugar.setDireccion(objjson.getString("direccion"));
			lugar.setCiudad(obtenerCiudad(objjson.get("ciudad").toString()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lugar;
	}
	
	
	
	private static Ciudad obtenerCiudad(String s) {
		// TODO Auto-generated method stub
		Ciudad ciudad = new Ciudad();
		try {
			JSONObject objjson = new JSONObject(s);
			
			ciudad.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciudad;
	}
	
	public static List<Lugar> buscarLugares() {

		List<Lugar> listaLugares = new ArrayList<Lugar>();

		Resty resty = new Resty();
		JSONResource jsResource = null;

		try {
			jsResource = resty.json("http://127.0.0.1:5000/lugar/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			String ok = jsResource.get("ok").toString();
			
			if (ok.equalsIgnoreCase("true")) {
				String strLug = jsResource.get("lugares").toString();
				
				JSONArray arrLugares = new JSONArray(strLug);
				
				for(int i=0; i < arrLugares.length(); i++){
					
					Lugar lugar = new Lugar();
					
					JSONObject obj = arrLugares.getJSONObject(i);

					
					lugar.setId(Integer.parseInt(obj.get("id").toString())); //Integer.parseInt(obj.get("id").toString())
					lugar.setDireccion(obj.get("direccion").toString()); //fecha
					lugar.setCiudad(obtenerCiudad(obj.get("ciudad").toString()));
					lugar.setNombre(obj.get("nombre").toString());
					
					
					listaLugares.add(lugar);
					
				}// fin for
				
				
			} // fin if
			
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return listaLugares;
	}
	
	public static String agregarEvento(Evento evento){

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
	    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
	   try {
		    jsResource = resty.json("http://127.0.0.1:5000/evento/agregar?idlugar=" + evento.getLugar().getId()
	   + "&nombre=" + evento.getNombre() + "&fecha=" +  "&cantentradasvendidas="+evento.getCantEntradas()+ "&costoentradas="+evento.getCostoEntrada()+ "&montorecaudado="+evento.getMontoRecaudado()+ "&descripcion="+evento.getDescripcion()+ "&observaciones="+evento.getObservacion() );
		   
	    
	   } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 ok = jsResource.get("ok").toString();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
		
	}
	
	
	
	public static Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));


        return calendario.getTime();
    }
	
	
	
}
