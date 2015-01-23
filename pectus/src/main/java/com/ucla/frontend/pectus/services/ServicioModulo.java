package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Grupo;
import com.ucla.frontend.pectus.models.Modulo;
import com.ucla.frontend.pectus.models.Tarea;

public class ServicioModulo {

	public ServicioModulo() {
		// TODO Auto-generated constructor stub
	}

	
	public static List<Modulo> buscarModulos()
	{
        List<Modulo> listaModulo = new ArrayList<Modulo>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/modulo/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("modulos").toString();
			JSONArray serTipoEstudio = new JSONArray(strPa);
			  for(int i=0; i < serTipoEstudio.length(); i++){
				  Modulo modulo = new Modulo();
                  JSONObject obj = serTipoEstudio.getJSONObject(i);
                  modulo.setId(Integer.parseInt(obj.get("id").toString()));
                  modulo.setNombre(obj.get("nombre").toString());
                  modulo.setDescripcion(obj.get("descripcion").toString());
               
                                   
                  listaModulo.add(modulo);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaModulo;		
		
		
		
		
	}


	public static List<Tarea> buscarTareas(Modulo modulo) {
		// TODO Auto-generated method stub
        List<Tarea> listatarea = new ArrayList<Tarea>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://localhost:5000/modulo/tarea/todos?idmodulo=" + modulo.getId());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tareas").toString();
			JSONArray ser = new JSONArray(strPa);
			  for(int i=0; i < ser.length(); i++){
				  Tarea tarea = new Tarea();
                  JSONObject obj = ser.getJSONObject(i);
                  tarea.setId(Integer.parseInt(obj.getString("id")));
                  tarea.setNombre(obj.getString("nombre"));
                  tarea.setDescripcion(obj.getString("descripcion"));
               
                                   
                  listatarea.add(tarea);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listatarea;		
	}


	public static String agregarModulo(Modulo modulo) {
		// TODO Auto-generated method stub
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/modulo/agregar?nombre=" + modulo.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + modulo.getDescripcion().replaceAll(" ", "%20"));
			

		}
			
			catch (IOException e) {
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


	public static String asociarTareas(Modulo modulo,
			List<Tarea> tarea) {
		// TODO Auto-generated method stub
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/modulo/tarea?idmodulo=" + modulo.getId()
					+"&idtarea=" + tarea.get(0).getId());
			

		}
			
			catch (IOException e) {
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
	
}
