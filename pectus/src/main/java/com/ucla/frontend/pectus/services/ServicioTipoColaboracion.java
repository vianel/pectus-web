package com.ucla.frontend.pectus.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.ucla.frontend.pectus.models.TipoColaboracion;


public class ServicioTipoColaboracion {
	
    private ListModelList<TipoColaboracion> listaModelTipoColaboracion;


	public ServicioTipoColaboracion (){

    }
    
	@Init
    public void init(){

    	this.setListaModelTipoColaboracion(new ListModelList<TipoColaboracion>(this.buscarTipoColaboracion()));
    	
    }
		
	public static String agregarTipoColaboracion(TipoColaboracion tc)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-colaboracion/agregar?nombre=" + tc.getNombre().replaceAll(" ", "%20"));
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
	
	
    public static List<TipoColaboracion> buscarTipoColaboracion()
    {
   	
        List<TipoColaboracion> listaTipoColaboracion = new ArrayList<TipoColaboracion>();
     
        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-colaboracion/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tipocolaboracion").toString();
			JSONArray serTipoColaboracion = new JSONArray(strPa);
			  for(int i=0; i < serTipoColaboracion.length(); i++){
				  TipoColaboracion tipocolaboracion = new TipoColaboracion();
                  JSONObject obj = serTipoColaboracion.getJSONObject(i);
                tipocolaboracion.setId(Integer.parseInt(obj.get("id").toString()));
                  tipocolaboracion.setNombre(obj.get("nombre").toString());
                                                   
                  listaTipoColaboracion.add(tipocolaboracion);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        return listaTipoColaboracion;  
    }
    
    
   public static String modificarTipoColaboracion(TipoColaboracion tc)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-colaboracion/editar?id=" +tc.getId()
					+ "&nombre=" + tc.getNombre());
					
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
 

	public ListModelList<TipoColaboracion> getListaModelTipoColaboracion() {
		return listaModelTipoColaboracion;
	}

	public void setListaModelTipoColaboracion(ListModelList<TipoColaboracion> listaModelTipoColaboracion) {
		this.listaModelTipoColaboracion = listaModelTipoColaboracion;
	}
}

