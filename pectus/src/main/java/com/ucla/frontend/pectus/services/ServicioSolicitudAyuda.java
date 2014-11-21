package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
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

import com.ucla.frontend.pectus.models.Ayuda;
import com.ucla.frontend.pectus.models.Diagnostico;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Paciente;



public class ServicioSolicitudAyuda {
	
	private ListModelList<Ayuda> listaModelAyudas;
	
	public ServicioSolicitudAyuda(){
		
	}
	
	@Init
	public void init(){

    	this.setListaModelAyudas(new ListModelList<Ayuda>(this.buscarAyudas()));
    }

	public ListModelList<Ayuda> getListaModelAyudas() {
		return listaModelAyudas;
	}

	public void setListaModelAyudas(ListModelList<Ayuda> listaModelAyudas) {
		this.listaModelAyudas = listaModelAyudas;
	}
	
	public static List<Ayuda> buscarAyudas()
    {


    	
        List<Ayuda> listaAyudas = new ArrayList<Ayuda>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/solicitud-ayuda/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("solicitudes").toString();
			JSONArray serAyuda = new JSONArray(strPa);
			  for(int i=0; i < serAyuda.length(); i++){
                  Ayuda ayuda = new Ayuda();
                  JSONObject obj = serAyuda.getJSONObject(i);
                  
                  ayuda.setPaciente(obtenerPaciente(obj.get("paciente").toString()));
                  ayuda.setDiagnostico(obtenerDiagnostico(obj.get("patologia").toString()));
                  ayuda.setFechaSolicitud(convertirFecha(obj.get("fecsolicitud").toString()));
                  ayuda.setMotivo(obj.get("motivosolicitud").toString());
                  ayuda.setAprobacion(Double.parseDouble(obj.get("porcaprobacion").toString()));
                  
                  listaAyudas.add(ayuda);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaAyudas;
        
    }
	
	private static Paciente obtenerPaciente(String s) {
		// TODO Auto-generated method stub
		Paciente paciente = new Paciente();
		try {
			JSONObject objjson = new JSONObject(s);
			paciente.setCedula( objjson.getString("cedula"));
			paciente.setApellido(objjson.getString("apellido"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paciente;
	}
	private static Diagnostico obtenerDiagnostico(String s) {
		// TODO Auto-generated method stub
		Diagnostico diagnostico = new Diagnostico();
		try {
			JSONObject objjson = new JSONObject(s);
			diagnostico.setId(Integer.parseInt(objjson.getString("id")));
			diagnostico.setNombre(objjson.getString("nombre"));
			diagnostico.setObservacion(objjson.getString("observacion"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diagnostico;
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
	

}
