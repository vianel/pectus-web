package com.ucla.frontend.pectus.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jfree.util.Log;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.MotivoSolicitud;

public class ServicioMotivoSolicitud {
	


 
    private ListModelList<MotivoSolicitud> listaModelMotivoSolicitud;


	public ServicioMotivoSolicitud (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelMotivoSolicitud(new ListModelList<MotivoSolicitud>(this.buscarMotivoSolicitud()));
    	
    }
	

	
	public static String agregarMotivoSolicitud(MotivoSolicitud ms)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/causa/agregar?nombre=" + ms.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + ms.getDescripcion().replaceAll(" ", "%20"));
			

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
	
	
    public static List<MotivoSolicitud> buscarMotivoSolicitud()
    {


    	
        List<MotivoSolicitud> listaMotivoSolicitud = new ArrayList<MotivoSolicitud>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/causa/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("causas").toString();
			JSONArray serMotivoSolicitud = new JSONArray(strPa);
			  for(int i=0; i < serMotivoSolicitud.length(); i++){
				  MotivoSolicitud ms = new MotivoSolicitud();
                  JSONObject obj = serMotivoSolicitud.getJSONObject(i);
                ms.setId(Integer.parseInt(obj.get("id").toString()));
                  ms.setNombre(obj.get("nombre").toString());
                
                  ms.setDescripcion(obj.get("descripcion").toString());
                                   
                  listaMotivoSolicitud.add(ms);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaMotivoSolicitud;
        
    }
    
    
    
	public static String modificarMotivoSolicitud(MotivoSolicitud ms)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/causa/editar?id=" + ms.getId().toString()
					+ "&nombre=" + ms.getNombre().replaceAll(" ", "%20") + 
					"&descripcion=" + URLEncoder.encode(ms.getDescripcion()));
					
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
 

	public ListModelList<MotivoSolicitud> getListaModelMotivoSolicitud() {
		return listaModelMotivoSolicitud;
	}

	public void setListaModelMotivoSolicitud(ListModelList<MotivoSolicitud> listaModelMotivoSolicitud) {
		this.listaModelMotivoSolicitud = listaModelMotivoSolicitud;
	}
	
	




}


