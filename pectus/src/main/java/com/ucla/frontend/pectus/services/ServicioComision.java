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

import com.ucla.frontend.pectus.models.Comision;

public class ServicioComision {
	


 
    private ListModelList<Comision> listaModelComision;


	public ServicioComision (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelComision(new ListModelList<Comision>(this.buscarComision()));
    	
    }
	

	
	public static String agregarComision(Comision co)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;	  
		String ok = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/comision/agregar?nombre=" + co.getNombre().replaceAll(" ", "%20")
					+"&descripcion=" + co.getDescripcion().replaceAll(" ", "%20"));
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
	
	
    public static List<Comision> buscarComision()
    {


    	
        List<Comision> listaComision = new ArrayList<Comision>();        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/comision/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("comisiones").toString();
			JSONArray serTipoEstudio = new JSONArray(strPa);
			  for(int i=0; i < serTipoEstudio.length(); i++){
				  Comision comision = new Comision();
                  JSONObject obj = serTipoEstudio.getJSONObject(i);
                comision.setId(Integer.parseInt(obj.get("id").toString()));
                comision.setNombre(obj.get("nombre").toString());
                
                comision.setDescripcion(obj.get("descripcion").toString());
                                   
                  listaComision.add(comision);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaComision;
        
    }
    
    
    
	public static String modificarComision(Comision comi)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/comision/editar?id=" +comi.getId()
					+ "&nombre=" + comi.getNombre() + 
					"&descricpion=" + comi.getDescripcion());
					
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
 

	public ListModelList<Comision> getListaModelComision() {
		return listaModelComision;
	}

	public void setListaModelComision(ListModelList<Comision> listaModelComision) {
		this.listaModelComision = listaModelComision;
	}
	
	




}


