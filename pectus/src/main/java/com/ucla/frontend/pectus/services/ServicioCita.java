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

import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Estudio;
import com.ucla.frontend.pectus.models.EstudioClinica;

public class ServicioCita {


    private ListModelList<Cita> listaModelCita;
    public ServicioCita(){
    
}
   @Init
    public void init(){
       
	   this.setListaModelCita(new ListModelList<Cita>(this.buscarCita()));
    	
    }
		public static String agregarCita(Cita cita)
		{

			Resty resty = new Resty();
		    JSONResource jsResource = null;
		    String ok = null;
		    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String fecha = df.format(cita.getFecha());
			DateFormat hr = new SimpleDateFormat("HH:mm");
			String hora = hr.format(cita.getHora());
	
			
		   try {
			    jsResource = resty.json("http://127.0.0.1:5000/cita/agregar?idestudio=" 
		   + cita.getEstudioClinica().getId() 
		   + "&cedula=" + cita.getPaciente().getCedula() 
		  // + "&rif=" + cita.getClinica().getRif()
		   + "&fecha=" + fecha
		   + "&hora=" + hora);
			   
		    
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
	
	    // MOSTRAR LAS CITAS EN LA GRID 
	
	    public static List<Cita> buscarCita()
	    {

	    ListModelList<Cita> listaCita = new ListModelList<Cita>();
	    
	
	        Resty resty = new Resty();
	        JSONResource jsResource = null;
			try {
				jsResource = resty.json("http://127.0.0.1:5000/cita/todos");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true")) {
				
				String strPa = jsResource.get("citas").toString();
				JSONArray serCita = new JSONArray(strPa);
				  for(int i=0; i < serCita.length(); i++){
					  Cita cita = new Cita();
	                  JSONObject obj = serCita.getJSONObject(i);
	                  cita.setPaciente(obtenerPaciente(obj.get("paciente").toString()));
	                  cita.setEstudioClinica(obtenerEstudioClinica(obj.get("estudio").toString()));
	                  cita.setFecha(convertirFecha(obj.get("fecha").toString()));
	               
	               listaCita.add(cita);
				  
				  } //fin For
				
				} //fin IF
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 

	        return listaCita;
	        
	    }
	    // fIN DE MOSTRAR CITA
	    
	    
	   // OBTENER LA CEDULA DEL PACIENTE
	    
	    public static Paciente obtenerPaciente (String s)
	    {
	    	Paciente paciente = new Paciente();
	    	try {
				JSONObject objjson = new JSONObject(s);
				paciente.setCedula(objjson.getString("cedula"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return paciente;
	    }
	    // OBTENER ESTUDIO-CLINICA 
	    	
	    	public static EstudioClinica obtenerEstudioClinica(String s)
	 	    {
	    		EstudioClinica estudio = new EstudioClinica();
	 	    	
	 	    	try {
	 				JSONObject objjson = new JSONObject(s);
	                estudio.setId(Integer.parseInt(objjson.getString("id")));
	                estudio.setClinica(obtenerClinica(objjson.getString("clinica")));
	                estudio.setEstudio(obtenerTipoEstudio(objjson.getString("tipoestudio")));
	 			} catch (JSONException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	 	    	return estudio;
	 	    }
	    	
	    	// OBTENER LA CLINICA
		    
		    public static Clinica obtenerClinica(String s)
		    {
		    	Clinica clinica = new Clinica();
		    	try {
					JSONObject objjson = new JSONObject(s);
					clinica.setRif(objjson.getString("rif"));
					clinica.setNombre(objjson.getString("nombre"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	return clinica;
		    }
	    	
		    // OBTENER TIPO ESTUDIO	
		    
		    public static Estudio obtenerTipoEstudio(String s)
		    {
		    	  Estudio tipoestudio = new Estudio();
		    	try {
					JSONObject objjson = new JSONObject(s);
					tipoestudio.setId(Integer.parseInt(objjson.getString("id")));
					tipoestudio.setNombre(objjson.getString("nombre"));;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	return tipoestudio;
		    }
	    	
	    	
	    	
	    	
	    	
	    	//CONVERTIR FECHA
	    public static Date convertirFecha(String fecha){
	    	     Calendar calendario = Calendar.getInstance();
	    	     Calendar calendarioActual = Calendar.getInstance();
	    	     String fechaFraccionada[] = fecha.split("-");
	    	     calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
	             calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
	             calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));

	             return calendario.getTime();
	    	    }
	    
	    public ListModelList<Cita> getListaModelCita() {
			return listaModelCita;
		}

		public void setListaModelCita(ListModelList<Cita> listaModelCita) {
			this.listaModelCita = listaModelCita;
		} 
	    

	}






	
