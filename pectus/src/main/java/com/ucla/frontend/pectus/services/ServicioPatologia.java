package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.Diagnostico;
import com.ucla.frontend.pectus.models.Patologia;
import com.ucla.frontend.pectus.models.TipoEstudio;

public class ServicioPatologia {
	
private ListModelList<Diagnostico> listaModelDiagnosticos;
	
	public ServicioPatologia(){
		
	}

	public ListModelList<Diagnostico> getListaModelDiagnosticos() {
		return listaModelDiagnosticos;
	}

	public void setListaModelDiagnosticos(
			ListModelList<Diagnostico> listaModelDiagnosticos) {
		this.listaModelDiagnosticos = listaModelDiagnosticos;
	}
	
	@Init
	public void init(){

    	this.setListaModelDiagnosticos(new ListModelList<Diagnostico>(this.buscarDiagnosticos()));
    }
	
	public static List<Diagnostico> buscarDiagnosticos()
    {


    	
        List<Diagnostico> listaDiagnosticos = new ArrayList<Diagnostico>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/patologia/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("patologias").toString();
			JSONArray serPalogia = new JSONArray(strPa);
			  for(int i=0; i < serPalogia.length(); i++){
                  Diagnostico diagnostico = new Diagnostico();
                  JSONObject obj = serPalogia.getJSONObject(i);
                  
                  diagnostico.setId(Integer.parseInt(obj.get("id").toString()));
                  diagnostico.setNombre(obj.get("nombre").toString());
                  diagnostico.setObservacion(obj.get("observacion").toString());
                  
                  listaDiagnosticos.add(diagnostico);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaDiagnosticos;
        
    }
	
//	SERVICIOS DE JOSNER
	public static String agregarPatologia(Patologia p)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/patologia/agregar?nombre=" + p.getNombre().replaceAll(" ", "%20")
					+"&observacion=" + p.getObservacion().replaceAll(" ", "%20"));
			

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
	
	
    public static List<Patologia> buscarPatologia()
    {


    	
        List<Patologia> listaPatologia = new ArrayList<Patologia>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/patologia/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("patologias").toString();
			JSONArray serPatologia = new JSONArray(strPa);
			  for(int i=0; i < serPatologia.length(); i++){
				  Patologia patologia = new Patologia();
                  JSONObject obj = serPatologia.getJSONObject(i);
                  patologia.setId(Integer.parseInt(obj.get("id").toString()));
                  patologia.setNombre(obj.get("nombre").toString());                
                  patologia.setObservacion((obj.get("observacion").toString()));
                                   
                  listaPatologia.add(patologia);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaPatologia;
        
    }
    
    /////////////////////////////MODIFICAR//////////////
    public static String modificarPatologia(Patologia patolo)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/patologia/editar?id=" +patolo.getId()
					+ "&nombre=" + patolo.getNombre().replaceAll(" ", "%20") + 
					"&observacion=" + URLEncoder.encode(patolo.getObservacion()));
					
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
    
    
    
    
    
    
    
    
    
    
    

}
