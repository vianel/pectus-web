package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.Paciente;

public class ServicioActividad {

	public static List<Actividad> buscaractividades() {
		// TODO Auto-generated method stub
		ListModelList<Actividad> listaActividad = new ListModelList<Actividad>();
		
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
			try {
				jsResource = resty.json("http://localhost:5000/actividad/todos");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true"))
				{
					String strAct = jsResource.get("actividades").toString();
					JSONArray serActividad = new JSONArray(strAct);
					 for(int i=0; i < serActividad.length(); i++)
					 {
		                  Actividad actividad = new Actividad();
		                  JSONObject obj = serActividad.getJSONObject(i);

		                  actividad.setDescripcion(obj.getString("descripcion".toString()));
		                  actividad.setFecha(obj.getString("fecha").toString());
		                  actividad.setLugar(obtenerlugar(obj.getString("solicitudactividad").toString()));
		                  actividad.setObservaciones(obj.getString("observaciones").toString());
		                  
		                  listaActividad.add(actividad);

					 }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listaActividad;
	}
	
    public static Lugar obtenerlugar(String s) {
		// TODO Auto-generated method stub
    	String jsonlugar = null;
    	try {
			JSONObject mijsonjbj = new JSONObject(s);
			jsonlugar = mijsonjbj.getString("lugar").toString();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Lugar lugar = new Lugar();
    	try {
			JSONObject objjson = new JSONObject(jsonlugar);
		//	lugar.setIdCiudad(obtenerciudad(objjson.getString("ciudad").toString()));
			lugar.setDireccion(objjson.getString("direccion").toString().toString());
			lugar.setId(Integer.parseInt(objjson.getString("id").toString()));
			lugar.setNombre(objjson.getString("nombre").toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lugar;
	}
    
    public static Ciudad obtenerciudad(String s) {
		// TODO Auto-generated method stub
    	Ciudad ciudad = new Ciudad();
    	try {
			JSONObject objjson = new JSONObject(s);

			ciudad.setNombre(objjson.getString("nombre"));
			ciudad.setId(Integer.parseInt(objjson.getString("id")));
			ciudad.setEstado(obtenerestado(objjson.getString("estado")));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciudad;
	}
	private static Estado obtenerestado(String s) {
		// TODO Auto-generated method stub
		Estado estado = new Estado();
		try {
			JSONObject objjson = new JSONObject(s);
			estado.setId(Integer.parseInt(objjson.getString("id")));
			estado.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estado;
	}

}
