package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Grupo;
import com.ucla.frontend.pectus.models.Usuario;

public class ServicioUsuario {

	public ServicioUsuario() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Usuario> buscarUsuarios()
	{
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/usuario/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("usuario").toString();
			JSONArray serTipoEstudio = new JSONArray(strPa);
			  for(int i=0; i < serTipoEstudio.length(); i++){
				  Usuario usuario = new Usuario();
                  JSONObject obj = serTipoEstudio.getJSONObject(i);
                usuario.setLogin(obj.get("login").toString());
                  usuario.setContrasenna(obj.get("clave").toString());
                
               
                                   
                  listaUsuario.add(usuario);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaUsuario;			
		
	}
	
	
	public static List<Grupo> buscarGrupos(Usuario usr)
	{
        List<Grupo> listaGrupo = new ArrayList<Grupo>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://localhost:5000/usuario/grupo/todos?login=" + usr.getLogin());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("grupos").toString();
			JSONArray ser = new JSONArray(strPa);
			  for(int i=0; i < ser.length(); i++){
				  Grupo grupo = new Grupo();
                  JSONObject obj = ser.getJSONObject(i);
                  grupo.setId(Integer.parseInt(obj.getString("id")));
                  grupo.setNombre(obj.getString("nombre"));
                  grupo.setDescripcion(obj.getString("descripcion"));
               
                                   
                  listaGrupo.add(grupo);
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 

        return listaGrupo;			
		
	}

	public static String asociarGrupos(Usuario usuario, List<Grupo> grupos) {
		// TODO Auto-generated method stub
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	  
		String ok = null;
	    
	   
		
		try {
			jsResource = resty.json("http://127.0.0.1:5000/usuario/grupo?login=" + usuario.getLogin()
					+"&idgrupo=" + grupos.get(0).getId());
			

		}
			
			catch (IOException e) {
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

}
