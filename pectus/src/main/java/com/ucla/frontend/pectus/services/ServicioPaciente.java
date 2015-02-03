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

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Persona;
import com.ucla.frontend.pectus.models.Seguro;
import com.ucla.frontend.pectus.models.Visita;

public class ServicioPaciente {
	
    private ListModelList<Paciente> listaModelPaciente;
    private ListModelList<Paciente> listModelPacienteCandidato;
	
    public ServicioPaciente (){
    
    }
    
	@Init
    public void init(){

    	this.setListaModelPaciente(new ListModelList<Paciente>(this.buscarPacientes()));
//    	this.setListModelPacienteCandidato(new ListModelList<Paciente>(this.buscarPacientesCandidato()));
    }
	
	public ListModelList<Paciente> getListModelPacienteCandidato() {
		return listModelPacienteCandidato;
	}

	public void setListModelPacienteCandidato(
			ListModelList<Paciente> listModelPacienteCandidato) {
		this.listModelPacienteCandidato = listModelPacienteCandidato;
	}

	public static String modificarPaciente(Paciente paciente)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = df.format(paciente.getFechaNacimiento());
		try {
			jsResource = resty.json("http://localhost:5000/paciente/editar?cedula=" + paciente.getCedula() + 
					"&nombre=" + paciente.getNombre() +
					"&apellido=" + paciente.getApellido() +
					"&tlfcelular=" + paciente.getCelular() +
					"&tlfijo=" + paciente.getFijo() +
					"&fecnacimiento=" + fecha);
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
	
	public static String agregarPaciente(Paciente paciente)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = df.format(paciente.getFechaNacimiento());
		try {
			jsResource = resty.json("http://localhost:5000/paciente/agregar?cedula=" + paciente.getCedula() +
					"&nombre=" + paciente.getNombre() +
					"&apellido=" + paciente.getApellido() //+
//					"&tlfcelular=" + paciente.getCelular() +
//					"&tlfijo=" + paciente.getFijo() +
//					"&profesion=" + paciente.getProfesion() +
//					"&nrohijos=" + paciente.getNroHijos() + 
//					"&fecnacimiento=" + fecha +
//					"&idciudad=" + paciente.getCiudad().getId()
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

    public static ListModelList<Paciente> buscarPacientes()
    {	
    	ListModelList<Paciente> listaPaciente = new ListModelList<Paciente>();        
        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/paciente/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("paciente").toString();
			JSONArray serPaciente = new JSONArray(strPa);
			  for(int i=0; i < serPaciente.length(); i++){
				 
                  Paciente paciente = new Paciente();
                  JSONObject obj = serPaciente.getJSONObject(i);
                  paciente.setNombre(obj.get("nombre").toString());
                  paciente.setApellido(obj.get("apellido").toString());
                  paciente.setCedula(obj.get("cedula").toString());
                  paciente.setCorreo(obj.get("correo").toString());
                  paciente.setDireccion(obj.get("direccion").toString());
                  paciente.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  paciente.setCelular(obj.get("tlfcelular").toString());
                  paciente.setFijo(obj.get("tlffijo").toString());
                  paciente.setProfesion(obj.get("profesion").toString());
                  paciente.setNroHijos(Integer.parseInt(obj.get("nrohijos").toString()) );
                  paciente.setIngresos(Float.parseFloat(obj.get("ingfamiliares").toString()));
                  paciente.setEgresos(Float.parseFloat(obj.get("egrfamiliares").toString()));
//                  paciente.setSeguro(obtenerSeguro(obj.get("tiposeguro").toString())); 
                  paciente.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
                  paciente.setEstado(obtenerciudad(obj.get("ciudad").toString()).getEstado());
                  paciente.setCedulaConyugue(obj.get("cedconyugue").toString());
                  paciente.setNombreConyugue(obj.get("nombconyugue").toString());
                  paciente.setApellidoConyugue(obj.get("apeconyugue").toString());
                  paciente.setOcupacionConyugue(obj.get("ocupconyugue").toString());
                  paciente.setFechaNacConyugue(convertirFecha(obj.get("fecnacconyugue").toString()));
                  paciente.setLugarTrabajo(obj.get("lugtrabajo").toString());
                  paciente.setDireccionTrabajo(obj.get("dirtrabajo").toString());
                  paciente.setTelefonoTrabajo(obj.get("tlftrabajo").toString());
//                  paciente.setLogin(obj.get("login").toString());
                  
                  listaPaciente.add(paciente);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaPaciente;
        
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

	public static Seguro obtenerSeguro (String s)
    {
    	Seguro seguro = new Seguro();
    	try {
			JSONObject objjson = new JSONObject(s);
		  	seguro.setId(Integer.parseInt(objjson.getString("id")));
		  	seguro.setNombre(objjson.getString("nombre"));
		  	seguro.setDescripcion(objjson.getString("descripcion"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
  
    	
    	
    	return seguro;
    }
    public static Date convertirFecha(String fecha){
    	if(fecha == null){
    		return null;
    	}
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));


        return calendario.getTime();
    }

 

	public ListModelList<Paciente> getListaModelPaciente() {
		return listaModelPaciente;
	}

	public void setListaModelPaciente(ListModelList<Paciente> listaModelPaciente) {
		this.listaModelPaciente = listaModelPaciente;
	}
	
	//································································································

	public static String aceptarPaciente(Paciente paciente){
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
	    DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			jsResource = resty.json("http://localhost:5000/paciente/editar?cedula=" + paciente.getCedula() + "&nrohijos=" + paciente.getNroHijos() + "&edocivil=" + paciente.getEstadoCivil() +
					"&nombconyugue=" + paciente.getNombreConyugue() +"&apeconyugue=" + paciente.getApellidoConyugue() +"&cedconyugue=" + paciente.getCedulaConyugue() +"&ocupconyugue=" + paciente.getOcupacionConyugue() +"&fecnacconyugue=" + fecha.format(paciente.getFechaNacConyugue()) +
					"&nrohabitantes=" + paciente.getNroHabitantes() +"&precalquiler=" + paciente.getAlquiler() + "&lugtrabajo=" + paciente.getLugarTrabajo() + "&dirtrabajo=" + paciente.getDireccionTrabajo() +"&tlftrabajo=" + paciente.getTelefonoTrabajo() + "&ingfamiliares=" + paciente.getIngresos() +"&egrfamiliares=" + paciente.getEgresos() + "&tenvivienda=" + paciente.getTendenciaVivienda() +"&tipovivienda=" + paciente.getTipoVivienda() +"&segurosocial=S"  + "&idtiposeguro=" + paciente.getSeguro().getId() +  
					
					"&estatus=I" );
			
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
	public static String aceptarPacienteSinConyugue(Paciente paciente){
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
	    DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			jsResource = resty.json("http://localhost:5000/paciente/editar?cedula=" + paciente.getCedula() + "&nrohijos=" + paciente.getNroHijos() + "&edocivil=" + paciente.getEstadoCivil() +
					
					"&nrohabitantes=" + paciente.getNroHabitantes() +"&precalquiler=" + paciente.getAlquiler() + "&lugtrabajo=" + paciente.getLugarTrabajo() + "&dirtrabajo=" + paciente.getDireccionTrabajo() +"&tlftrabajo=" + paciente.getTelefonoTrabajo() + "&ingfamiliares=" + paciente.getIngresos() +"&egrfamiliares=" + paciente.getEgresos() + "&tenvivienda=" + paciente.getTendenciaVivienda() +"&tipovivienda=" + paciente.getTipoVivienda() +"&segurosocial=S"  + "&idtiposeguro=" + paciente.getSeguro().getId() +  
					
					"&estatus=I" );
			
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
	
//	public static ListModelList<Paciente> buscarPacienteCandidato(){ 
//		ListModelList<Paciente> listaPacientesCandidatos = new  ListModelList<Paciente>();
//		Resty resty = new Resty();
//		JSONResource jsResource = null;
//		
//		try {
//			jsResource = resty.json("http://127.0.0.1:5000/paciente/buscar?type=paciente&estatus=A");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			String ok = jsResource.get("ok").toString();
//			if (ok.equalsIgnoreCase("true")) {
//			
//			String strPer = jsResource.get("personas").toString();
//			JSONArray serPersona = new JSONArray(strPer);
//			  for(int i=0; i < serPersona.length(); i++){
//                  Persona persona = new Persona();
//                  JSONObject obj = serPersona.getJSONObject(i);
//                  
//                  persona.setCedula(obj.get("cedula").toString());
//                  persona.setNombre(obj.get("nombre").toString());
//                  persona.setApellido(obj.get("apellido").toString());
//                  persona.setCelular(obj.get("tlfcelular").toString());
//                  persona.setFijo(obj.get("tlffijo").toString());
//                  persona.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
//                  persona.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
//                  persona.setDireccion(obj.get("direccion").toString());
//                  persona.setProfesion(obj.get("profesion").toString());
//                  persona.setCorreo(obj.get("correo").toString());
//                  listaPersonas.add(persona);
//			  
//			  } //fin For
//			
//			} //fin IF
//			
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return listaPersonas;
//	}
	

	public static String asignarCitaPaciente(Persona persona)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/paciente/editar?cedula=" + persona.getCedula() + 
					"&estatus=A" );
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
	public static String agregarCitaPostulado(Persona persona, Visita visita)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		try {
			jsResource = resty.json("http://localhost:5000/visita/agregar?cedulapersona=" + persona.getCedula() + 
					"&fecha="+ fecha.format(visita.getFecha())+"&codigo=VI005" );
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
	
	
	public static ListModelList<Seguro> buscarSeguros()
    {	
    	ListModelList<Seguro> listaSeguros = new ListModelList<Seguro>();        
        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-seguro/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("tiposeguros").toString();
			JSONArray serPaciente = new JSONArray(strPa);
			  for(int i=0; i < serPaciente.length(); i++){
				 
                  Seguro seguro = new Seguro();
                  JSONObject obj = serPaciente.getJSONObject(i);
                  seguro.setId(Integer.parseInt(obj.get("id").toString()) );
                  seguro.setNombre(obj.get("nombre").toString());
                  seguro.setDescripcion(obj.get("descripcion").toString());
                  
                  
                  listaSeguros.add(seguro);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return listaSeguros;
        
    }
	
	

}


