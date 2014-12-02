package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Lugar;

public class ServicioLugar {
	
	public static List<Lugar> buscarlugares() {
		// TODO Auto-generated method stub
		ListModelList<Lugar> listalugar = new ListModelList<Lugar>();
		
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
			try {
				jsResource = resty.json("http://localhost:5000/lugar/todos");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true"))
				{
					String strAct = jsResource.get("lugares").toString();
					JSONArray serLugar = new JSONArray(strAct);
					 for(int i=0; i < serLugar.length(); i++)
					 {
		                  Lugar lugar = new Lugar();
		                  JSONObject obj = serLugar.getJSONObject(i);
		                  
		      			lugar.setIdCiudad(obtenerciudad(obj.getString("ciudad").toString()));
		    			lugar.setDireccion(obj.getString("direccion").toString().toString());
		    			lugar.setId(Integer.parseInt(obj.getString("id").toString()));
		    			lugar.setNombre(obj.getString("nombre").toString());
		                  
		                  listalugar.add(lugar);

					 }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listalugar;
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
