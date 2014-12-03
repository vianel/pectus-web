package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Patrocinador;

public class ServicioPatrocinador {


    private ListModelList<Patrocinador> listaModelPatrocinador;
    public ServicioPatrocinador(){
    
}
   @Init
    public void init(){
       
	   this.setListaModelPatrocinador(new ListModelList<Patrocinador>(this.buscarPatrocinador()));
    	
    }
   
   
   
   public static String agregarPatrocinador(Patrocinador patrocinador)
  	{

  		Resty resty = new Resty();
  	    JSONResource jsResource = null;
  	    String ok = null;
  		try {
  			jsResource = resty.json("http://localhost:5000/patrocinador/agregar?rif=" + patrocinador.getRif() +
  					"&nombre=" + patrocinador.getNombre() +
  					"&direccion=" + patrocinador.getDireccion() +
  					"&tlfcelular=" + patrocinador.getTlfCelular() +
  					"&tlffijo=" + patrocinador.getTlfFijo()+
  					"&nombrerepresentante=" + patrocinador.getNombreRepresentante() +
  					"&tlfrepresentante=" + patrocinador.getTlfRepresentante() +
  					"&correorepresentante=" + patrocinador.getCorreoRepresentante()
  					
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

		public static List<Patrocinador> buscarPatrocinador()
	    {

	    ListModelList<Patrocinador> listaPatrocinador = new ListModelList<Patrocinador>();
	    
	
	        Resty resty = new Resty();
	        JSONResource jsResource = null;
			try {
				jsResource = resty.json("http://127.0.0.1:5000/patrocinador/todos");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true")) {
				
				String strPa = jsResource.get("patrocinadores").toString();
				JSONArray serPatrocinador = new JSONArray(strPa);
				  for(int i=0; i < serPatrocinador.length(); i++){
					  Patrocinador patrocinador = new Patrocinador();
	                  JSONObject obj = serPatrocinador.getJSONObject(i);
	               
	                  		patrocinador.setCorreoRepresentante(serPatrocinador.getJSONObject(i).get("correorepresentante").toString());
	                        patrocinador.setDireccion(serPatrocinador.getJSONObject(i).get("direccion").toString()); 
	                  		patrocinador.setNombre(serPatrocinador.getJSONObject(i).get("nombre").toString());
	                  		patrocinador.setNombreRepresentante(serPatrocinador.getJSONObject(i).get("nombrerepresentante").toString());
	                  		patrocinador.setRif(serPatrocinador.getJSONObject(i).get("rif").toString());
	                  		patrocinador.setTlfCelular(serPatrocinador.getJSONObject(i).get("tlfcelular").toString());
	                  		patrocinador.setTlfFijo(serPatrocinador.getJSONObject(i).get("tlffijo").toString());
	                  		patrocinador.setTlfRepresentante(serPatrocinador.getJSONObject(i).get("tlfrepresentante").toString());
	                  		 
	                     
	                    
	                        listaPatrocinador.add(patrocinador);
	     
				  
				  } //fin For
				
				} //fin IF
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 

	        return listaPatrocinador;
	        
	    }
	    // fIN DE MOSTRAR CITA
	    	    public ListModelList<Patrocinador> getListaModelPatrocinador() {
			return listaModelPatrocinador;
		}

		public void setListaModelPatrocinador(ListModelList<Patrocinador> listaModelCita) {
			this.listaModelPatrocinador = listaModelCita;
		} 

	}






	
