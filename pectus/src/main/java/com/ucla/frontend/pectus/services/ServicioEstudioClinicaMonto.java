package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Estudio;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.models.Paciente;


public class ServicioEstudioClinicaMonto {
	
	private ListModelList<EstudioClinica> listaModelEstudiosXClinica;
	
	public ServicioEstudioClinicaMonto(){
		
	}

	public ListModelList<EstudioClinica> getListaModelEstudiosXClinica() {
		return listaModelEstudiosXClinica;
	}

	public void setListaModelEstudiosXClinica(
			ListModelList<EstudioClinica> listaModelEstudiosXClinica) {
		this.listaModelEstudiosXClinica = listaModelEstudiosXClinica;
	}

	@Init
	public void init(){

		this.setListaModelEstudiosXClinica(new ListModelList<EstudioClinica>(this.buscarEstudiosXClinica()));
    }
	
	
	public static ListModelList<EstudioClinica> buscarEstudiosXClinica()
    {

        ListModelList<EstudioClinica> listaEstudiosXClinica = new ListModelList<EstudioClinica>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/estudio/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("estudios").toString();
			JSONArray serEstudio = new JSONArray(strPa);
			  for(int i=0; i < serEstudio.length(); i++){
                  EstudioClinica estudioClinica = new EstudioClinica();
                  JSONObject obj = serEstudio.getJSONObject(i);
                  
                  estudioClinica.setId(Integer.parseInt(obj.get("id").toString()));
                  estudioClinica.setMonto(Double.parseDouble(obj.get("monto").toString()));
                  estudioClinica.setClinica(obtenerClinica(obj.get("clinica").toString()));
                  estudioClinica.setEstudio(obtenerTipoEstudio(obj.get("tipoestudio").toString()));
                  
                  listaEstudiosXClinica.add(estudioClinica);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaEstudiosXClinica;
        
    }
	
	private static Clinica obtenerClinica(String s) {
		// TODO Auto-generated method stub
		Clinica clinica = new Clinica();
		try {
			JSONObject objjson = new JSONObject(s);
			clinica.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clinica;
	}
	
	private static Estudio obtenerTipoEstudio(String s) {
		// TODO Auto-generated method stub
		Estudio estudio = new Estudio();
		try {
			JSONObject objjson = new JSONObject(s);
			estudio.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudio;
	}
}
