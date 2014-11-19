package com.ucla.frontend.pectus.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.ucla.frontend.pectus.models.Seguro;

public class ServicioPaciente {
	


 
    private ListModelList<Paciente> listaModelPaciente;


	public ServicioPaciente (){
    

    }
    
	@Init
    public void init(){

    	this.setListaModelPaciente(new ListModelList<Paciente>(this.buscarPacientes()));
    	
    }
	
	public static void agregarPaciente(Paciente paciente)
	{

		Resty resty = new Resty();
		try {
			resty.json("http://localhost:5000/paciente/agregar?cedula=" + paciente.getCedula() +
					"&nombre=" + paciente.getNombre() +
					"&apellido=" + paciente.getApellido() +
					"&tlfcelular=" + paciente.getCelular() +
					"&tlfijo=" + paciente.getFijo() +
					"&profesion=" + paciente.getProfesion() +
					"&nrohijos=" + paciente.getNroHijos() 
					//"&fecnacimiento=" + paciente.getFechaNacimiento().toString()
	
					
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static List<Paciente> buscarPacientes()
    {


    	
        List<Paciente> listaPaciente = new ArrayList<Paciente>();
     
        

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
                  paciente.setDireccion(obj.get("direccion").toString());
                  paciente.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  paciente.setCelular(obj.get("tlfcelular").toString());
                  paciente.setFijo(obj.get("tlffijo").toString());
                  paciente.setProfesion(obj.get("profesion").toString());
                  paciente.setNroHijos(Integer.parseInt(obj.get("nrohijos").toString()) );
                  paciente.setIngresos(Integer.parseInt(obj.get("ingfamiliares").toString()));
                  paciente.setEgresos(Integer.parseInt(obj.get("egrfamiliares").toString())); 
                  paciente.setSeguro(obtenerSeguro(obj.get("tiposeguro").toString())); 
                  paciente.setCiudad(obtenerciudad(obj.get("ciudad").toString()));

                  paciente.setEstado(obtenerciudad(obj.get("ciudad").toString()).getEstado());  

   
                  
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





}


