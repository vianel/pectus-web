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






import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Evento;
import com.ucla.frontend.pectus.models.Patrocinador;
import com.ucla.frontend.pectus.models.TipoColaboracion;


public class ServicioColaboracion {

	private ListModelList<Colaboracion> listaModelColaboracion;
	
	public ServicioColaboracion(){
		
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
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
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
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaColaboraciones;
        
    }
	
	private static Evento obtenerEvento(String s) {
		// TODO Auto-generated method stub
		Evento evento = new Evento();
		try {
			JSONObject objjson = new JSONObject(s);
			evento.setNombre(objjson.getString("nombre"));
			evento.setDescripcion(objjson.getString("descripcion"));
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
			tipoColaboracion.setNombre(objjson.getString("nombre"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipoColaboracion;
	}
	
}
