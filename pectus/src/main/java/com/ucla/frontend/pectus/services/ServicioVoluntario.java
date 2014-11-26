package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Voluntario;

public class ServicioVoluntario {

	private ListModelList<Voluntario> listaModelVoluntario;
	
	public ServicioVoluntario(){
		
	}
	
	@Init
	public void init(){
		this.setListaModelVoluntario(new ListModelList<Voluntario>(this.buscarVoluntario()));
	}
	
	public static List<Voluntario> buscarVoluntario(){
		
		List<Voluntario> listaVoluntarios = new ArrayList<Voluntario>();
		Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/voluntario/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strVol = jsResource.get("voluntarios").toString();
			JSONArray serVoluntario = new JSONArray(strVol);
			  for(int i=0; i < serVoluntario.length(); i++){
                  Voluntario voluntario = new Voluntario();
                  JSONObject obj = serVoluntario.getJSONObject(i);
                  
                  voluntario.setApellido(obj.get("apellido").toString());
                  voluntario.setNombre(obj.get("nombre").toString());
                  voluntario.setCedula(obj.get("cedula").toString());
                  voluntario.setDireccion(obj.get("direccion").toString());

   
                  
                  listaVoluntarios.add(voluntario);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaVoluntarios;
		
	}
	

	public ListModelList<Voluntario> getListaModelVoluntario() {
		return listaModelVoluntario;
	}

	public void setListaModelVoluntario(
			ListModelList<Voluntario> listaModelVoluntario) {
		this.listaModelVoluntario = listaModelVoluntario;
	}
	
	
}
