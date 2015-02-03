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

import com.ucla.frontend.pectus.models.TipoSeguro;

public class ServicioTipoSeguro {
	


 
    private ListModelList<TipoSeguro> listaModelTipoSeguro;


	public ServicioTipoSeguro (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelTipoSeguro(new ListModelList<TipoSeguro>(this.buscarTipoSeguro()));
    	
    }
	

	
	public static String agregarTipoSeguro(TipoSeguro ts)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-seguro/agregar?nombre=" + ts.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + ts.getDescripcion().replaceAll(" ", "%20"));
			

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
	
	
    public static List<TipoSeguro> buscarTipoSeguro()
    {


    	
        List<TipoSeguro> listaTipoSeguro = new ArrayList<TipoSeguro>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-seguro/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tiposeguros").toString();
			JSONArray serTipoSeguro = new JSONArray(strPa);
			  for(int i=0; i < serTipoSeguro.length(); i++){
				  TipoSeguro tiposeguro = new TipoSeguro();
                  JSONObject obj = serTipoSeguro.getJSONObject(i);
                  tiposeguro.setId(Integer.parseInt(obj.get("id").toString()));
                  tiposeguro.setNombre(obj.get("nombre").toString());                
                  tiposeguro.setDescripcion(obj.get("descripcion").toString());
                                   
                  listaTipoSeguro.add(tiposeguro);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaTipoSeguro;
        
    }
    
    
    
	public static String modificarTipoSeguro(TipoSeguro ts)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/tipo-seguro/editar?id=" + ts.getId().toString()
					+ "&nombre=" + ts.getNombre().replaceAll(" ", "%20") + 
					"&descripcion=" + URLEncoder.encode(ts.getDescripcion()));
					
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
 

	public ListModelList<TipoSeguro> getListaModelTipoSeguro() {
		return listaModelTipoSeguro;
	}

	public void setListaModelTipoSeguro(ListModelList<TipoSeguro> listaModelTipoSeguro) {
		this.listaModelTipoSeguro = listaModelTipoSeguro;
	}
	
	




}


