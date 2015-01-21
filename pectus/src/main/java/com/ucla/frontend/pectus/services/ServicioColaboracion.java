package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;









import com.ucla.frontend.pectus.models.Cita;
import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Patrocinador;
import com.ucla.frontend.pectus.models.TipoColaboracion;


public class ServicioColaboracion {

	private ListModelList<Colaboracion> listaModelColaboracion;
	
	private ListModelList<Patrocinador> listaModelPatrocinador;
	private ListModelList<TipoColaboracion> listaModelTipoColaboracion;
	
	public ServicioColaboracion(){
		
	}
	
	

	public ListModelList<Patrocinador> getListaModelPatrocinador() {
		return listaModelPatrocinador;
	}



	public void setListaModelPatrocinador(
			ListModelList<Patrocinador> listaModelPatrocinador) {
		this.listaModelPatrocinador = listaModelPatrocinador;
	}



	public ListModelList<TipoColaboracion> getListaModelTipoColaboracion() {
		return listaModelTipoColaboracion;
	}



	public void setListaModelTipoColaboracion(
			ListModelList<TipoColaboracion> listaModelTipoColaboracion) {
		this.listaModelTipoColaboracion = listaModelTipoColaboracion;
	}



	public ListModelList<Colaboracion> getListaModelColaboracion() {
		return listaModelColaboracion;
	}

	public void setListaModelColaboracion(
			ListModelList<Colaboracion> listaModelColaboracion) {
		this.listaModelColaboracion = listaModelColaboracion;
	}
	@Init
	public void init(){
		this.setListaModelColaboracion(new ListModelList<Colaboracion>(this.buscarColaboraciones()));
		this.setListaModelPatrocinador(new ListModelList<Patrocinador>(this.buscarPatrocinadores()));
		this.setListaModelTipoColaboracion(new ListModelList<TipoColaboracion>(this.buscarTipoColaboracion()));
	}
	
	
	public static String agregarColaboracion(Colaboracion colaboracion){

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
	    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
	   try {
		    jsResource = resty.json("http://127.0.0.1:5000/colaboracion/agregar?idevento=" + colaboracion.getEvento().getId()
	   + "&rif=" + colaboracion.getPatrocinado().getRif() + "&cantidad=" + colaboracion.getCantidad() + "&idtipocolaboracion="+colaboracion.getTipoColaboracion().getId() + "&fecha=" + df.format(new Date()));
		   
	    
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
	
	
	
	public static List<Colaboracion> buscarColaboraciones(){

        List<Colaboracion> listaColaboraciones = new ArrayList<Colaboracion>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/colaboracion/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			
			String strCol = jsResource.get("colaboraciones").toString();
			JSONArray serColaboracion = new JSONArray(strCol);
			  for(int i=0; i < serColaboracion.length(); i++){
                  Colaboracion colaboracion = new Colaboracion();
                  JSONObject obj = serColaboracion.getJSONObject(i);
                  
                  colaboracion.setEvento(obtenerEvento(obj.get("evento").toString()));
                  colaboracion.setPatrocinado( obtenerPatrocinador(obj.get("patrocinador").toString()));
                  colaboracion.setTipoColaboracion(obtenerTipoColaboracion(obj.get("tipocolaboracion").toString()));
                  colaboracion.setCantidad(Double.parseDouble(obj.get("cantidad").toString()));
                  colaboracion.setId(Integer.parseInt(obj.get("id").toString()));
                  
                  listaColaboraciones.add(colaboracion);
                  
       
			  
			  } //fin For
			
	
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaColaboraciones;
        
    }
	public static List<TipoColaboracion> buscarTipoColaboracion(){

        List<TipoColaboracion> listaTipoColaboraciones = new ArrayList<TipoColaboracion>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/tipo-colaboracion/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strCol = jsResource.get("tipocolaboracion").toString();
			JSONArray serColaboracion = new JSONArray(strCol);
			  for(int i=0; i < serColaboracion.length(); i++){
                  TipoColaboracion tipoColaboracion = new TipoColaboracion();
                  JSONObject obj = serColaboracion.getJSONObject(i);
                  
                  tipoColaboracion.setId(Integer.parseInt(obj.get("id").toString()));
                  tipoColaboracion.setNombre(obj.get("nombre").toString());
                  listaTipoColaboraciones.add(tipoColaboracion);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaTipoColaboraciones;
        
    }
	public static List<Patrocinador> buscarPatrocinadores(){

        List<Patrocinador> listaPatrocinadores = new ArrayList<Patrocinador>();
     
        

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
			
			String strPatr = jsResource.get("patrocinadores").toString();
			JSONArray serPatrocinador = new JSONArray(strPatr);
			  for(int i=0; i < serPatrocinador.length(); i++){
                  Patrocinador patrocinador = new Patrocinador();
                  JSONObject obj = serPatrocinador.getJSONObject(i);
                  
                  patrocinador.setRif(obj.get("rif").toString());
                  patrocinador.setNombre(obj.get("nombre").toString());
                  patrocinador.setDireccion(obj.get("direccion").toString());    
   
                  listaPatrocinadores.add(patrocinador);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaPatrocinadores;
        
    }
	
	private static Evento obtenerEvento(String s) {
		// TODO Auto-generated method stub
		Evento evento = new Evento();
		try {
			JSONObject objjson = new JSONObject(s);
			evento.setNombre(objjson.getString("nombre"));
			evento.setDescripcion(objjson.getString("descripcion"));
			evento.setId(objjson.getInt("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return evento;
	}
	private static Patrocinador obtenerPatrocinador(String s) {
		// TODO Auto-generated method stub
		Patrocinador patrocinador = new Patrocinador();
		try {
			JSONObject objjson = new JSONObject(s);
			patrocinador.setNombre(objjson.getString("nombre"));
			patrocinador.setRif(objjson.getString("rif"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patrocinador;
	}
	private static TipoColaboracion obtenerTipoColaboracion(String s) {
		// TODO Auto-generated method stub
		TipoColaboracion tipoColaboracion = new TipoColaboracion();
		try {
			JSONObject objjson = new JSONObject(s);
			tipoColaboracion.setId(Integer.parseInt(objjson.getString("id")));
			tipoColaboracion.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoColaboracion;
	}
	
	 public static void editColaboracion(Colaboracion colaboracion){
	        try {
	        	Resty resty = new Resty();
	    		JSONResource jsResource = null;
	    	

	    		try {
	    			jsResource = resty.json("http://127.0.0.1:5000/colaboracion/editar?id=" + colaboracion.getId() + "&rif=" + colaboracion.getPatrocinado().getRif() + "&idtipocolaboracion=" + colaboracion.getTipoColaboracion().getId() + "&cantidad=" + colaboracion.getCantidad());
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	      
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        }
	 public static void editColaboracion2(Colaboracion colaboracion){
	        try {
	        	Resty resty = new Resty();
	    		JSONResource jsResource = null;
	    	

	    		try {
	    			jsResource = resty.json("http://127.0.0.1:5000/colaboracion/editar?id=" + colaboracion.getId() + "&rif=" + colaboracion.getPatrocinado().getRif() + "&idtipocolaboracion=" + colaboracion.getTipoColaboracion().getId() + "&cantidad=" + colaboracion.getCantidad() + "&idevento=" + colaboracion.getEvento().getId());
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	      
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        }
	
}
