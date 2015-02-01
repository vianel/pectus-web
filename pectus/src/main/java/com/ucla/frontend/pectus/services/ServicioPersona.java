package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Persona;

public class ServicioPersona {

	private ListModelList<Persona> listaModelPersona;
	private ListModelList<Persona> listaModelPersonaAceptada;
	
	public ServicioPersona(){
		
	}
	 @Init
	 public void init(){
		 this.setListaModelPersona(new ListModelList<Persona>(this.buscarPersonas()));
		 this.setListaModelPersonaAceptada(new ListModelList<Persona>(this.buscarPersonasAceptadas()) );
	 }
	 
	
	 public ListModelList<Persona> getListaModelPersonaAceptada() {
		return listaModelPersonaAceptada;
	}
	public void setListaModelPersonaAceptada(
			ListModelList<Persona> listaModelPersonaAceptada) {
		this.listaModelPersonaAceptada = listaModelPersonaAceptada;
	}
	public ListModelList<Persona> getListaModelPersona() {
		return listaModelPersona;
	}
	public void setListaModelPersona(ListModelList<Persona> listaModelPersona) {
		this.listaModelPersona = listaModelPersona;
	}
	
	public static ListModelList<Persona> buscarPersonas(){
		ListModelList<Persona> listaPersonas = new  ListModelList<Persona>();
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/persona/buscar?type=paciente&estatus=S");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPer = jsResource.get("personas").toString();
			JSONArray serPersona = new JSONArray(strPer);
			  for(int i=0; i < serPersona.length(); i++){
                  Persona persona = new Persona();
                  JSONObject obj = serPersona.getJSONObject(i);
                  
                  persona.setCedula(obj.get("cedula").toString());
                  persona.setNombre(obj.get("nombre").toString());
                  persona.setApellido(obj.get("apellido").toString());
                  persona.setCelular(obj.get("tlfcelular").toString());
                  persona.setFijo(obj.get("tlffijo").toString());
                  persona.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  persona.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
                  persona.setDireccion(obj.get("direccion").toString());
                  persona.setProfesion(obj.get("profesion").toString());
                  persona.setCorreo(obj.get("correo").toString());
                  listaPersonas.add(persona);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaPersonas;
	}
	public static ListModelList<Persona> buscarPersonasAceptadas(){
		ListModelList<Persona> listaPersonas = new  ListModelList<Persona>();
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/persona/buscar?type=paciente&estatus=A");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPer = jsResource.get("personas").toString();
			JSONArray serPersona = new JSONArray(strPer);
			  for(int i=0; i < serPersona.length(); i++){
                  Persona persona = new Persona();
                  JSONObject obj = serPersona.getJSONObject(i);
                  
                  persona.setCedula(obj.get("cedula").toString());
                  persona.setNombre(obj.get("nombre").toString());
                  persona.setApellido(obj.get("apellido").toString());
                  persona.setCelular(obj.get("tlfcelular").toString());
                  persona.setFijo(obj.get("tlffijo").toString());
                  persona.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  persona.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
                  persona.setDireccion(obj.get("direccion").toString());
                  persona.setProfesion(obj.get("profesion").toString());
                  persona.setCorreo(obj.get("correo").toString());
                  persona.setEstadoCivil(obj.get("edocivil").toString().charAt(0));
                  listaPersonas.add(persona);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaPersonas;
	}
	 
	public static Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));


        return calendario.getTime();
    }
	public static Ciudad obtenerciudad(String s) {
		// TODO Auto-generated method stub
    	Ciudad ciudad = new Ciudad();
    	try {
			JSONObject objjson = new JSONObject(s);

			ciudad.setNombre(objjson.getString("nombre"));
			ciudad.setId(Integer.parseInt(objjson.getString("id")));
			ciudad.setEstado(obtenerestado(objjson.getString("estado")));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciudad;
	}
	private static Estado obtenerestado(String s) {
		// TODO Auto-generated method stub
		Estado estado = new Estado();
		try {
			JSONObject objjson = new JSONObject(s);
			estado.setId(Integer.parseInt(objjson.getString("id")));
			estado.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estado;
	}
}
