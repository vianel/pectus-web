package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.TipoEstudio;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;




public class ServicioClinica {
	
	private ListModelList<Clinica> listaModelClinica;


	public ServicioClinica (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelClinica(new ListModelList<Clinica>(this.buscarClinica()));
    	
    }


    public static List<Clinica> buscarClinica()
    {


    	
        List<Clinica> listaClinica = new ArrayList<Clinica>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/clinica/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strCli = jsResource.get("clinicas").toString();
			JSONArray serClinica = new JSONArray(strCli);
			  for(int i=0; i < serClinica.length(); i++){
                  Clinica clinica = new Clinica();
                  JSONObject obj = serClinica.getJSONObject(i);
                  
                 clinica.setRif(obj.get("rif").toString());
                 clinica.setNombre(obj.get("nombre").toString());
                 clinica.setDireccion(obj.get("direccion").toString());
                 clinica.setTelefono(obj.get("tlffijo").toString());
                 

   
                  
                  listaClinica.add(clinica);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaClinica;
        
    }
    
    public static String modificarClinica(Clinica clinica)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/clinica/editar?rif=" + clinica.getRif().toString()
					+ "&nombre=" + clinica.getNombre().toString() + 
					"&direccion=" + URLEncoder.encode(clinica.getDireccion()));
					
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
    
    public static String agregarClinica(Clinica clinica)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/clinica/agregar?rif=" + clinica.getRif() +
					"&nombre=" + clinica.getNombre().replaceAll(" ", "%20") +
					"&direccion=" + clinica.getDireccion().replaceAll(" ", "%20") +
					"&tlffijo=" + clinica.getTelefono() 
					
					);
	    
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
 

	public ListModelList<Clinica> getListaModelClinica() {
		return listaModelClinica;
	}

	public void setListaModelClinica(ListModelList<Clinica> listaModelClinica) {
		this.listaModelClinica = listaModelClinica;
	}
	
	
	

}
