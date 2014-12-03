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

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import com.ucla.frontend.pectus.models.TipoLugar;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;


public class ServicioTipoLugar {
	


 
    private ListModelList<TipoLugar> listaModelTipoLugar;


	public ServicioTipoLugar (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelTipoLugar(new ListModelList<TipoLugar>(this.buscarTipoLugar()));
    	
    }
	

	
	public static String agregarTipoLugar(TipoLugar tl)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-lugar/agregar?nombre=" + tl.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + tl.getDescripcion().replaceAll(" ", "%20"));
			

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
	
	
    public static List<TipoLugar> buscarTipoLugar()
    {


    	
        List<TipoLugar> listaTipoLugar = new ArrayList<TipoLugar>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-lugar/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tipolugar").toString();
			JSONArray serTipoLugar = new JSONArray(strPa);
			  for(int i=0; i < serTipoLugar.length(); i++){
				  TipoLugar tipolugar = new TipoLugar();
                  JSONObject obj = serTipoLugar.getJSONObject(i);
                tipolugar.setId(Integer.parseInt(obj.get("id").toString()));
                  tipolugar.setNombre(obj.get("nombre").toString());
                
                  tipolugar.setDescripcion(obj.get("descripcion").toString());
                                   
                  listaTipoLugar.add(tipolugar);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaTipoLugar;
        
    }
    
    
    
	public static String modificarTipoLugar(TipoLugar tip)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-lugar/editar?id=" +tip.getId()
					+ "&nombre=" + tip.getNombre() + 
					"&descricpion=" + tip.getDescripcion());
					
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
 

	public ListModelList<TipoLugar> getListaModelTipoLugar() {
		return listaModelTipoLugar;
	}

	public void setListaModelTipoLugar(ListModelList<TipoLugar> listaModelTipoLugar) {
		this.listaModelTipoLugar = listaModelTipoLugar;
	}
	
	




}


