package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Grupo;


public class ServicioGrupo {

	public ServicioGrupo() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Grupo> buscarGrupos()
	{
        List<Grupo> listaGrupo = new ArrayList<Grupo>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/grupo/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("grupos").toString();
			JSONArray serTipoEstudio = new JSONArray(strPa);
			  for(int i=0; i < serTipoEstudio.length(); i++){
				  Grupo grupo = new Grupo();
                  JSONObject obj = serTipoEstudio.getJSONObject(i);
                  grupo.setId(Integer.parseInt(obj.get("id").toString()));
                  grupo.setNombre(obj.get("nombre").toString());
                  grupo.setDescripcion(obj.get("descripcion").toString());
               
                                   
                  listaGrupo.add(grupo);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaGrupo;		
		
		
		
		
	}

	public static String agregarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/grupo/agregar?nombre=" + grupo.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + grupo.getDescripcion().replaceAll(" ", "%20"));
			

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
