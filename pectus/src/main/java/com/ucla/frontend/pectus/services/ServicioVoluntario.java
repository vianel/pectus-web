package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.net.URLEncoder;
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

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Visita;
import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.models.Persona;
import com.ucla.frontend.pectus.models.VoluntarioxComision;
import com.ucla.frontend.pectus.models.Comision;
import com.ucla.frontend.pectus.models.Visita;


public class ServicioVoluntario {

	private ListModelList<Voluntario> listaModelVoluntario;
	
	public ServicioVoluntario(){
		
	}
	
	@Init
	public void init(){
		this.setListaModelVoluntario(new ListModelList<Voluntario>(this.buscarVoluntarioI()));
	}
    public static ListModelList<Voluntario> buscarVoluntario(){
		
		ListModelList<Voluntario> listaVoluntarios = new ListModelList<Voluntario>();
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
	
/***********************************SERVICIO BUSCAR VOLUNTARO INGRESADO********************************/		
	public static ListModelList<Voluntario> buscarVoluntarioI(){
		
		ListModelList<Voluntario> listaVoluntarios = new ListModelList<Voluntario>();
		Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/voluntario/buscar?estatus=I");
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
                  voluntario.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
                  voluntario.setCedula(obj.get("cedula").toString());
                  voluntario.setNombre(obj.get("nombre").toString());
                  voluntario.setApellido(obj.get("apellido").toString());
                  voluntario.setCelular(obj.get("tlfcelular").toString());
                  voluntario.setFijo(obj.get("tlffijo").toString());
                  voluntario.setDireccion(obj.get("direccion").toString());
                  voluntario.setCorreo(obj.get("correo").toString());
                  voluntario.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  voluntario.setProfesion(obj.get("profesion").toString());
                  voluntario.setEstadoCivil(obj.get("edocivil").toString().charAt(0));
                  voluntario.setSexo(obj.get("sexo").toString().charAt(0));
                  voluntario.setLugarTrabajo(obj.get("lugtrabajo").toString());
                  voluntario.setCargo(obj.get("cargo").toString());
                  voluntario.setDireccionTrabajo(obj.get("dirtrabajo").toString());
                  voluntario.setTelefonoOficina(obj.get("tlfoficina").toString());
                  voluntario.setReferido(obj.get("referidopor").toString());
                  listaVoluntarios.add(voluntario);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaVoluntarios;
		
	}
	
/********************************* Servicio BURCAR VOLUNTARIO Solicitante ***************************/
	
	public static ListModelList<Voluntario> buscarVoluntarioS(){
		ListModelList<Voluntario> listaVoluntario = new  ListModelList<Voluntario>();
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/voluntario/buscar?estatus=S");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPer = jsResource.get("voluntarios").toString();
			JSONArray serPersona = new JSONArray(strPer);
			  for(int i=0; i < serPersona.length(); i++){
                  Voluntario voluntario = new Voluntario();
                  JSONObject obj = serPersona.getJSONObject(i);
                  
                  voluntario.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
                  voluntario.setCedula(obj.get("cedula").toString());
                  voluntario.setNombre(obj.get("nombre").toString());
                  voluntario.setApellido(obj.get("apellido").toString());
                  voluntario.setCelular(obj.get("tlfcelular").toString());
                  voluntario.setFijo(obj.get("tlffijo").toString());
                  voluntario.setDireccion(obj.get("direccion").toString());
                  voluntario.setCorreo(obj.get("correo").toString());
                  voluntario.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  voluntario.setProfesion(obj.get("profesion").toString());
                  voluntario.setSexo(obj.get("sexo").toString().charAt(0));
                  voluntario.setLugarTrabajo(obj.get("lugtrabajo").toString());
                  voluntario.setCargo(obj.get("cargo").toString());
                  voluntario.setDireccionTrabajo(obj.get("dirtrabajo").toString());
                  voluntario.setTelefonoOficina(obj.get("tlfoficina").toString());
                  voluntario.setReferido(obj.get("referidopor").toString());
                  listaVoluntario.add(voluntario);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaVoluntario;
	}
	


	 
	/********************************* Servicio BUSCAR VOLUNTARIO Atentido***************************/
	
	public static ListModelList<Voluntario> buscarVoluntarioA(){
		ListModelList<Voluntario> listaVoluntario = new  ListModelList<Voluntario>();
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/voluntario/buscar?estatus=A");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPer = jsResource.get("voluntarios").toString();
			JSONArray serPersona = new JSONArray(strPer);
			  for(int i=0; i < serPersona.length(); i++){
                  Voluntario voluntario = new Voluntario();
                //  Visita visita = new Visita();
                  JSONObject obj = serPersona.getJSONObject(i);

                  voluntario.setCiudad(obtenerciudad(obj.get("ciudad").toString()));
                  voluntario.setCedula(obj.get("cedula").toString());
                  voluntario.setNombre(obj.get("nombre").toString());
                  voluntario.setApellido(obj.get("apellido").toString());
                  voluntario.setCelular(obj.get("tlfcelular").toString());
                  voluntario.setFijo(obj.get("tlffijo").toString());
                  voluntario.setDireccion(obj.get("direccion").toString());
                  voluntario.setCorreo(obj.get("correo").toString());
                  voluntario.setFechaNacimiento(convertirFecha(obj.get("fecnacimiento").toString()));
                  voluntario.setProfesion(obj.get("profesion").toString());
                  voluntario.setSexo(obj.get("sexo").toString().charAt(0));
              //    visita.setHora(obj.get("hora").toString());
               //   voluntario.setLugarTrabajo(obj.get("lugtrabajo").toString());
               //   voluntario.setCargo(obj.get("cargo").toString());
               //   voluntario.setDireccionTrabajo(obj.get("dirtrabajo").toString());
               //   voluntario.setTelefonoOficina(obj.get("tlfoficina").toString());
               //   voluntario.setReferido(obj.get("referidopor").toString());
                  
                  listaVoluntario.add(voluntario);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return listaVoluntario;
	}
	


	
/***********************************SERVICIO ACEPTAR VOLUNTARIO **************************************/		
	public static String aceptarVoluntario(Voluntario voluntario)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/voluntario/editar?cedula=" + voluntario.getCedula().toString() +
			    	"&estatus=" +'I' +
					"&lugtrabajo=" + voluntario.getLugarTrabajo().toString() +
					"&cargo=" + voluntario.getCargo().toString()+
					"&dirtrabajo=" + voluntario.getDireccionTrabajo().toString() +
					"&tlfoficina=" + voluntario.getFijo().toString() +
					"&referidopor=" + voluntario.getReferido().toString()
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
	
/***********************************SERVICIO ASIGNAR CITA / ATENDER POSTULADO*****************************/					

	public static String asignarCitaVoluntario(Voluntario voluntario)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/voluntario/editar?cedula=" + voluntario.getCedula() + 
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
	
	public static String atenderPostulado(Voluntario voluntario, Visita visita)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
	    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = df.format(visita.getFecha());
		try {
			jsResource = resty.json("http://localhost:5000/visita/agregar?cedulapersona=" + voluntario.getCedula() + 
					"&fecha="+ fecha+ "&codigo=VI010" );
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
	
	
	
/***********************************SERVICIO ASIGNAR COMISION A VOLUNTARIO *******************************/
	
	public static String agregarComision(Voluntario voluntario, Comision comision)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
		
		try {
			jsResource = resty.json("http://localhost:5000/voluntario/comision?cedula=" + voluntario.getCedula() 
					+ "&idcomision=" + comision.getId());
	
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
		
/***********************************SERVICIO AGREGAR VOLUNTARIO **********************************/	
	public static String agregarVoluntario(Voluntario voluntario)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
	    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = df.format(voluntario.getFechaNacimiento());
		
		try {
			jsResource = resty.json("http://localhost:5000/voluntario/agregar?cedula=" + voluntario.getCedula() +
					"&idciudad=" +voluntario.getCiudad().getId()  +
					"&nombre=" + voluntario.getNombre() +
					"&apellido=" + voluntario.getApellido() +
					"&tlfcelular=" + voluntario.getCelular() +
					"&tlffijo=" + voluntario.getFijo() +
					"&direccion=" + voluntario.getDireccion().replaceAll(" ", "%20")  +
					"&correo=" + voluntario.getCorreo() +							
					"&fecnacimiento=" + fecha +
					"&profesion=" + voluntario.getProfesion()+
					"&edocivil=" + voluntario.getEstadoCivil()+ 
					"&sexo=" + voluntario.getSexo() + 
					"&estatus=I" +
					"&lugtrabajo=" + voluntario.getLugarTrabajo().replaceAll(" ", "%20")  +
					"&cargo=" + voluntario.getCargo()+
					"&dirtrabajo=" + voluntario.getDireccionTrabajo().replaceAll(" ", "%20")  +
					"&tlfoficina=" + voluntario.getTelefonoOficina() +
					"&referidopor=" + voluntario.getReferido()
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

	
/***********************************SERVICIO EDITAR VOLUNTARIO **********************************/		
	
	public static String editarVoluntario(Voluntario voluntario)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = df.format(voluntario.getFechaNacimiento());
		try {
			jsResource = resty.json("http://localhost:5000/voluntario/editar?cedula=" + voluntario.getCedula().toString() + 
					"&idciudad=" + voluntario.getCiudad().getId() +
					"&nombre=" + voluntario.getNombre().toString() +
					"&apellido=" + voluntario.getApellido().toString() +
					"&tlfcelular=" + voluntario.getCelular().toString() +
					"&tlffijo=" + voluntario.getFijo().toString() +
					"&direccion" +  URLEncoder.encode(voluntario.getDireccion()) +
					"&correo=" + voluntario.getCorreo().toString() +
					"&fecnacimiento=" + fecha +
					"&profesion=" + voluntario.getProfesion().toString() +
					"&edocivil=" + voluntario.getEstadoCivil() +
					"&sexo=" + voluntario.getSexo() + 
					"&lugtrabajo=" + voluntario.getLugarTrabajo().toString() +
					"&cargo=" + voluntario.getCargo().toString() +
					"&dirtrabajo=" + URLEncoder.encode(voluntario.getDireccionTrabajo()) +
					"&tlfoficina=" +voluntario.getTelefonoOficina().toString() +
					"&referidopo=" + voluntario.getReferido().toString() );
			
			
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
	
	
	
	
/***********************************CONVERSOR DE FECHA***********/	
    public static Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));
    return calendario.getTime();
    }
	

/***********************************OBTENER ESTADO Y CIUDAD********************/
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

	
	
	
	
	public ListModelList<Voluntario> getListaModelVoluntario() {
		return listaModelVoluntario;
	}

	public void setListaModelVoluntario(
			ListModelList<Voluntario> listaModelVoluntario) {
		this.listaModelVoluntario = listaModelVoluntario;
	}
	
	
}
