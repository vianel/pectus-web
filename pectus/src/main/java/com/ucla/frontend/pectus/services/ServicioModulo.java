package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Modulo;

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
	
}
