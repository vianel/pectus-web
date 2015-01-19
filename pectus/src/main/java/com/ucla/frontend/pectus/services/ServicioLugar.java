package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;


import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.TipoLugar;
import com.ucla.frontend.pectus.models.Lugar;

public class ServicioLugar {
	
		private ListModelList<Lugar> listaModelLugar;
	    public ServicioLugar(){
	    }
	    
	    
	    @Init
	    public void init(){
	    	
	      this.setListaModelLugar(new ListModelList<Lugar>(this.buscarLugar()));
	     	
	     }
/**********************     SERVICIO AGREGAR LUGAR  ****************************/
	    
	    public static String agregarLugar(Lugar lugar)
		{

			Resty resty = new Resty();
		    JSONResource jsResource = null;
		    String ok = null;
		  
		   try {
			    jsResource = resty.json("http://127.0.0.1:5000/lugar/agregar?idtipolugar=" + lugar.getTipoLugar().getId()
			            + "&idciudad=" + lugar.getCiudad().getId()   + "&nombre=" + lugar.getNombre().replaceAll(" ", "%20") 
			            + "&direccion=" + lugar.getDireccion().replaceAll(" ", "%20") + "&tlffijo=" + lugar.getTlffijo());
			   
		    
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
	
/**********************************************************************************************/
	    
	    
/***************************** MOSTRAR LOS LUGARES EN LA GRID **********************************/ 
		
	    public static List<Lugar> buscarLugar()
	    {

	    ListModelList<Lugar> listaLugar = new ListModelList<Lugar>();
	    
	
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
				
				String strLu = jsResource.get("lugares").toString();
				JSONArray serLugar = new JSONArray(strLu);
				  for(int i=0; i < serLugar.length(); i++){
					  Lugar lugar = new Lugar();
	                  JSONObject obj = serLugar.getJSONObject(i);
	                  lugar.setId(obj.getInt("id"));
	                  lugar.setTipoLugar(obtenerTipoLugar(obj.get("tipolugar").toString()));
	                  lugar.setNombre(obj.get("nombre").toString());
	                  lugar.setDireccion(obj.get("direccion").toString());
	                  lugar.setTlffijo(obj.get("tlffijo").toString());
	                  lugar.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
	                 
	               
	               listaLugar.add(lugar);
				  
				  } //fin For
				
				} //fin IF
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 

	        return listaLugar;
	        
	    }
	    
	    
	    // OBTENER TIPO LUGAR
    	
    	public static TipoLugar obtenerTipoLugar (String s)
 	    {
    		TipoLugar tipolugar= new TipoLugar();
 	    	try {
 				JSONObject objjson = new JSONObject(s);
 				
 				tipolugar.setId(Integer.parseInt(objjson.getString("id")));
 				tipolugar.setNombre(objjson.getString("nombre"));
 			} catch (JSONException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 	    	return tipolugar;
 	    }
    	
    	
    	// OBTENER CIUDAD
    	
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
    	
	// OBTENER ESTADO
    	
    	public static Estado obtenerestado(String s) {
    		// TODO Auto-generated method stub
        	Estado estado= new Estado();
        	try {
    			JSONObject objjson = new JSONObject(s);

    			estado.setNombre(objjson.getString("nombre"));
    			estado.setId(Integer.parseInt(objjson.getString("id")));
    		
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return estado;
    	}
/***************************************************************************************************/

	    
	    
	    
	    
/****** GET Y SET *******/
	    
  public ListModelList<Lugar> getListaModelLugar() {
			return listaModelLugar;
	}

  public void setListaModelLugar(ListModelList<Lugar> listaModelLugar) {
			this.listaModelLugar = listaModelLugar;
	} 	    
	    
	    
	    
	    
	    
	    
	    
}