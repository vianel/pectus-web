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

import com.ucla.frontend.pectus.models.TipoEstudio;

public class ServicioTipoEstudio {
	


 
    private ListModelList<TipoEstudio> listaModelTipoEstudio;


	public ServicioTipoEstudio (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelTipoEstudio(new ListModelList<TipoEstudio>(this.buscarTipoEstudio()));
    	
    }
	

	
	public static String agregarTipoEstudio(TipoEstudio te)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-estudio/agregar?nombre=" + te.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + te.getDescripcion().replaceAll(" ", "%20"));
			

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
	
	
    public static List<TipoEstudio> buscarTipoEstudio()
    {


    	
        List<TipoEstudio> listaTipoEstudio = new ArrayList<TipoEstudio>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-estudio/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tipoestudios").toString();
			JSONArray serTipoEstudio = new JSONArray(strPa);
			  for(int i=0; i < serTipoEstudio.length(); i++){
				  TipoEstudio tipoestudio = new TipoEstudio();
                  JSONObject obj = serTipoEstudio.getJSONObject(i);
                tipoestudio.setId(Integer.parseInt(obj.get("id").toString()));
                  tipoestudio.setNombre(obj.get("nombre").toString());
                
                  tipoestudio.setDescripcion(obj.get("descripcion").toString());
                                   
                  listaTipoEstudio.add(tipoestudio);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaTipoEstudio;
        
    }
    
    
    
	public static String modificarTipoEstudio(TipoEstudio tip)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/tipo-estudio/editar?id=" + tip.getId().toString()
					+ "&nombre=" + tip.getNombre().toString() + 
					"&descricpion=" + URLEncoder.encode(tip.getDescripcion()));
					
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
 

	public ListModelList<TipoEstudio> getListaModelTipoEstudio() {
		return listaModelTipoEstudio;
	}

	public void setListaModelTipoEstudio(ListModelList<TipoEstudio> listaModelTipoEstudio) {
		this.listaModelTipoEstudio = listaModelTipoEstudio;
	}
	
	




}


