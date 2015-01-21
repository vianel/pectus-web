package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;


import com.ucla.frontend.pectus.models.Tarea;

public class ServicioTarea {

	public ServicioTarea() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Tarea> buscarTareas()
	{
        List<Tarea> listaTarea = new ArrayList<Tarea>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tarea/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tarea").toString();
			JSONArray ser = new JSONArray(strPa);
			  for(int i=0; i < ser.length(); i++){
				  Tarea tarea = new Tarea();
                  JSONObject obj = ser.getJSONObject(i);
                  tarea.setId(Integer.parseInt(obj.get("id").toString()));
                  tarea.setNombre(obj.get("nombre").toString());
                  tarea.setDescripcion(obj.get("descripcion").toString());
               
                                   
                  listaTarea.add(tarea);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaTarea;		
		
		
		
		
	}

}
