package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ciudad;
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
					
					//set
					
					
					evento.setId(Integer.parseInt(obj.get("id").toString())); //Integer.parseInt(obj.get("id").toString())
					evento.setDescripcion(obj.get("descripcion").toString()); //fecha 
					
					//evento.setFecha(new Date(obj.get("fecha").toString()));
					evento.setFecha(new Date());
					
					evento.setNombre(obj.get("nombre").toString());
					
					JSONObject objLugar;
					JSONObject objCiudad;
					JSONObject objEstado;
					
					/*
					objLugar = obj.getJSONObject("lugar");
					
					
					Lugar lugar = new Lugar();
					lugar.setId(Integer.parseInt(objLugar.get("id").toString()));
					lugar.setDireccion(objLugar.get("direccion").toString());					
					lugar.setNombre(objLugar.get("nombre").toString());
					
					
					Ciudad ciudad = new Ciudad();
					objCiudad = objLugar.getJSONObject("ciudad");
					ciudad.setId(Integer.parseInt(objLugar.get("id").toString()));
					ciudad.setNombre(objLugar.get("nombre").toString());
					
					objEstado= objCiudad.getJSONObject("estado");
					Estado estado= new Estado();
					estado.setId(Integer.parseInt(objEstado.get("id").toString()));
					estado.setNombre(objEstado.getString("nombre").toString());
					
					ciudad.setEstado(estado);
										
					lugar.setCiudad(ciudad);
					
					evento.setLugar(lugar);
					*/
					// agregamos el evento
					eventos.add(evento);
					
				}// fin for
				
				
			} // fin if
			
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return eventos;
	}
}
