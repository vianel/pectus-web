package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.ucla.frontend.pectus.models.Causa;
import com.ucla.frontend.pectus.models.Diagnostico;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.models.MotivoRechazo;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Persona;
import com.ucla.frontend.pectus.models.SolicitudRechazada;



public class ServicioSolicitudAyuda {
	
	private ListModelList<Ayuda> listaModelAyudas;
	private ListModelList<Ayuda> listaModelAyudasAceptadas;
	private ListModelList<Ayuda> listaModelAyudasSolicitadas;
	
	public ServicioSolicitudAyuda(){
		
	}
	

	
	@Init
	public void init(){

    	this.setListaModelAyudas(new ListModelList<Ayuda>(this.buscarAyudas()));
    	this.setListaModelAyudasAceptadas(new ListModelList<Ayuda>(this.buscarAyudasAceptadas()));
    	this.setListaModelAyudasSolicitadas(new ListModelList<Ayuda>(this.buscarAyudasSolicitadas()));
    	
	}

	
	
	public ListModelList<Ayuda> getListaModelAyudasSolicitadas() {
		return listaModelAyudasSolicitadas;
	}



	public void setListaModelAyudasSolicitadas(
			ListModelList<Ayuda> listaModelAyudasSolicitadas) {
		this.listaModelAyudasSolicitadas = listaModelAyudasSolicitadas;
	}



	public ListModelList<Ayuda> getListaModelAyudasAceptadas() {
		return listaModelAyudasAceptadas;
	}



	public void setListaModelAyudasAceptadas(
			ListModelList<Ayuda> listaModelAyudasAceptadas) {
		this.listaModelAyudasAceptadas = listaModelAyudasAceptadas;
	}



	public ListModelList<Ayuda> getListaModelAyudas() {
		return listaModelAyudas;
	}

	public void setListaModelAyudas(ListModelList<Ayuda> listaModelAyudas) {
		this.listaModelAyudas = listaModelAyudas;
	}
	
	
	
	public static String agregarAyuda(Ayuda ayuda, List<EstudioClinica> estudioclinica)
	{
		String estudios = " ";
        for (int i = 0; i<estudioclinica.size(); i++)
        {
        	if (i==0)
        	{
        		estudios = estudioclinica.get(i).getId().toString();
        	}else
        	{
        		estudios+= "," + estudioclinica.get(i).getId().toString();
        	}
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date fechadehoy = new Date();
  
		System.out.println(estudios);
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/solicitud-ayuda/agregar?cedula=" + ayuda.getPaciente().getCedula() +
					"&motivosolicitud=" + ayuda.getMotivo() +
					"&idpatologia=" + ayuda.getDiagnostico().getId() +
					"&estudios=" + estudios +
					"&fecsolicitud=" + dateFormat.format(fechadehoy) +
					"&porcaprobacion=" + "0.0" +
					"&estatus=" + "P"
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
	
	
	
	
	public static List<Ayuda> buscarAyudas(){
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
//                  ayuda.setFechaAprobacion(convertirFecha(obj.get("fecaprobacion").toString()));
                  ayuda.setCausa(obtenerCausa(obj.get("causa").toString()));
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
	
	public static List<Ayuda> buscarAyudasAceptadas(){
        List<Ayuda> listaAyudas = new ArrayList<Ayuda>();
        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/solicitud-ayuda/buscar?estatus=A");
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
//                  ayuda.setFechaAprobacion(convertirFecha(obj.get("fecaprobacion").toString()));
                  ayuda.setCausa(obtenerCausa(obj.get("causa").toString()));
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
	
	public static List<Ayuda> buscarAyudasSolicitadas(){
        List<Ayuda> listaAyudas = new ArrayList<Ayuda>();
        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/solicitud-ayuda/buscar?estatus=S");
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
                  ayuda.setId(Integer.parseInt(obj.get("id").toString()));
                  ayuda.setPaciente(obtenerPaciente(obj.get("paciente").toString()));
                  ayuda.setDiagnostico(obtenerDiagnostico(obj.get("patologia").toString()));
                  ayuda.setFechaSolicitud(convertirFecha(obj.get("fecsolicitud").toString()));
//                  ayuda.setFechaAprobacion(convertirFecha(obj.get("fecaprobacion").toString()));
                  ayuda.setCausa(obtenerCausa(obj.get("causa").toString()));
                  ayuda.setMotivo(obj.get("observacion").toString());
//                  ayuda.setAprobacion(Double.parseDouble(obj.get("porcaprobacion").toString()));
                  
                  listaAyudas.add(ayuda);
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        return listaAyudas;
    }
	
	public static List<MotivoRechazo> buscarMotivosRechazos()
    {
        List<MotivoRechazo> listaMotivosRechazos = new ArrayList<MotivoRechazo>();
        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/motivo-rechazo/buscar?tipo=S");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strMot = jsResource.get("motivosrechazos").toString();
			JSONArray serMotivoRechazo = new JSONArray(strMot);
			  for(int i=0; i < serMotivoRechazo.length(); i++){
                  MotivoRechazo motivoRechazo = new MotivoRechazo();
                  JSONObject obj = serMotivoRechazo.getJSONObject(i);
                  motivoRechazo.setId(Integer.parseInt(obj.get("id").toString()));
                  motivoRechazo.setNombre(obj.get("nombre").toString());
                  motivoRechazo.setTipo(obj.get("tipo").toString());
                  
                  listaMotivosRechazos.add(motivoRechazo);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaMotivosRechazos;
        
    }
	public static String aprobarAyuda(Ayuda ayuda)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/solicitud-ayuda/aprobar-estudio?solicitud=" + ayuda.getId() + 
					"&porcaprobacion="+ ayuda.getAprobacion() );
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
	public static String RechazarAyuda(Ayuda ayuda, MotivoRechazo motivoRechazo, String solicitudRechazada )
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/solicitud-ayuda/rechazar?idmotivo=1"+  
					"&idsolicitudayuda="+ ayuda.getId() + "&descripcion="+solicitudRechazada );
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
	
	
	private static Paciente obtenerPaciente(String s) {
		// TODO Auto-generated method stub
		Paciente paciente = new Paciente();
		try {
			JSONObject objjson = new JSONObject(s);
			paciente.setCedula( objjson.getString("cedula"));
			paciente.setNombre(objjson.getString("nombre"));
			paciente.setApellido(objjson.getString("apellido"));
			paciente.setApellidoConyugue(objjson.getString("apeconyugue"));
			paciente.setProfesion(objjson.getString("profesion"));
			paciente.setNombreConyugue(objjson.getString("nombconyugue"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paciente;
	}
	private static Causa obtenerCausa(String s) {
		// TODO Auto-generated method stub
		Causa causa = new Causa();
		try {
			JSONObject objjson = new JSONObject(s);
			causa.setNombre(objjson.getString("nombre"));
			causa.setDescripcion(objjson.getString("descripcion"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return causa;
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
