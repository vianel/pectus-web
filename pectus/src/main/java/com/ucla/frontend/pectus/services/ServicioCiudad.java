package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;


public class ServicioCiudad {
	
    public static List<Ciudad> buscarCiudades(Estado estado)
    {


    	
        List<Ciudad> listaCiudad = new ArrayList<Ciudad>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://localhost:5000/ciudad/buscar?idestado=" + estado.getId());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strCi = jsResource.get("ciudades").toString();
			JSONArray serCiudad = new JSONArray(strCi);
			  for(int i=0; i < serCiudad.length(); i++){
                  Ciudad ciudad = new Ciudad();
                  JSONObject obj = serCiudad.getJSONObject(i);
                
                  ciudad.setId(Integer.parseInt(obj.get("id").toString()));
                  ciudad.setNombre(obj.get("nombre").toString());
                  ciudad.setEstado(obtenerestado(obj.get("estado").toString()));

   
                  
                  listaCiudad.add(ciudad);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaCiudad;
        
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
    public static List<Estado> buscarEstados()
    {


    	
        List<Estado> listaEstado = new ArrayList<Estado>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/estado/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strCi = jsResource.get("estados").toString();
			JSONArray serEstado = new JSONArray(strCi);
			  for(int i=0; i < serEstado.length(); i++){
                  Estado estado = new Estado();
                  JSONObject obj = serEstado.getJSONObject(i);
                
                  estado.setId(Integer.parseInt(obj.get("id").toString()));
                  estado.setNombre(obj.get("nombre").toString());

   
                  
                  listaEstado.add(estado);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaEstado;
        
    }

}
