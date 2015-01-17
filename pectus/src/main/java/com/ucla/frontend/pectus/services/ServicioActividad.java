package com.ucla.frontend.pectus.services;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.zul.ListModelList;

import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import com.ucla.frontend.pectus.models.Actividad;
import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Lugar;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Seguro;
import com.ucla.frontend.pectus.models.SolicitudActividad;
import com.ucla.frontend.pectus.models.TipoActividad;
import com.ucla.frontend.pectus.models.Voluntario;

public class ServicioActividad {

	public static List<Actividad> buscaractividades() {
		// TODO Auto-generated method stub
		ListModelList<Actividad> listaActividad = new ListModelList<Actividad>();
		
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
			try {
				jsResource = resty.json("http://localhost:5000/actividad/todos");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true"))
				{
					String strAct = jsResource.get("actividades").toString();
					JSONArray serActividad = new JSONArray(strAct);
					 for(int i=0; i < serActividad.length(); i++)
					 {
		                  Actividad actividad = new Actividad();
		                  JSONObject obj = serActividad.getJSONObject(i);
		                  actividad.setId(Integer.parseInt(obj.getString("id")));
		                  actividad.setTitulo(obj.getString("titulo"));
		                  actividad.setDescripcion(obj.getString("descripcion").toString());
		                  actividad.setFechainicio(convertirFecha(obj.getString("fechainicio").toString()));
		                  actividad.setFechafin(convertirFecha(obj.getString("fechafin").toString()));
		                  actividad.setLugar(obtenerlugar(obj.getString("lugar").toString()));
		                  actividad.setObservaciones(obj.getString("observaciones").toString());
		                  
		                  listaActividad.add(actividad);

					 }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listaActividad;
	}
	
    public static Lugar obtenerlugar(String s) {
		// TODO Auto-generated method stub

    	Lugar lugar = new Lugar();
    	try {
			JSONObject objjson = new JSONObject(s);
    	//	lugar.setIdCiudad(obtenerciudad(objjson.getString("ciudad").toString()));
			lugar.setDireccion(objjson.getString("direccion").toString().toString());
			lugar.setId(Integer.parseInt(objjson.getString("id").toString()));
			lugar.setNombre(objjson.getString("nombre").toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lugar;
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

	public static List<TipoActividad> buscartipoactividades() {
		// TODO Auto-generated method stub
		ListModelList<TipoActividad> listaTipoActividad = new ListModelList<TipoActividad>();
		
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
			try {
				jsResource = resty.json("http://localhost:5000/tipo-actividad/todos");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true"))
				{
					String strAct = jsResource.get("tipoactividades").toString();
					JSONArray serActividad = new JSONArray(strAct);
					 for(int i=0; i < serActividad.length(); i++)
					 {
		                  TipoActividad actividad = new TipoActividad();
		                  JSONObject obj = serActividad.getJSONObject(i);

		                  actividad.setDescripcion(obj.getString("descripcion".toString()));
		                  actividad.setNombre(obj.getString("nombre").toString());
		                  actividad.setId(Integer.parseInt(obj.getString("id").toString()));
		                  
		                  listaTipoActividad.add(actividad);

					 }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listaTipoActividad;
	}
	
	public static String agregarsolicitudactividad(SolicitudActividad SA)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
			try {
				jsResource = resty.json("http://localhost:5000/solicitud-actividad/agregar?idtipoactividad=" + SA.getIdTipoActividad().getId()
						+"&descripcion=" + SA.getDescripcion().replaceAll(" ", "%20")
						+"&fecsolicitud=" + dateFormat.format(SA.getFecha()) 
						+"&nombsolicitante="+ SA.getNomsolicitante() 
						+"&telfsolicitante="+ SA.getTlfsolicitante());
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

	public static List<SolicitudActividad> buscarsolicitudactividades() {
		// TODO Auto-generated method stub
		ListModelList<SolicitudActividad> listasolactividad = new ListModelList<SolicitudActividad>();
		
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
			try {
				jsResource = resty.json("http://localhost:5000/solicitud-actividad/todos");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true"))
				{
					String strAct = jsResource.get("solicitudactividad").toString();
					JSONArray serActividad = new JSONArray(strAct);
					 for(int i=0; i < serActividad.length(); i++)
					 {
						 SolicitudActividad actividad = new SolicitudActividad();
		                  JSONObject obj = serActividad.getJSONObject(i);
		                  
		                  actividad.setId(Integer.parseInt(obj.getString("id")));
		                  actividad.setFecha(convertirFecha(obj.getString("fecsolicitud")));
		                  actividad.setNomsolicitante(obj.getString("nombsolicitante"));
		                  actividad.setDescripcion(obj.getString("descripcion".toString()));
		              //    actividad.setTlfsolicitante(obj.getString("telfsolicitante"));
		                  actividad.setIdTipoActividad(obtenertipoactividad(obj.get("tipoactividad").toString()));
		                  listasolactividad.add(actividad);

					 }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return listasolactividad;
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
	private static TipoActividad obtenertipoactividad(String s) {
		// TODO Auto-generated method stub
		TipoActividad tipoactividad = new TipoActividad();
    	try {
			JSONObject objjson = new JSONObject(s);
			tipoactividad.setId(Integer.parseInt(objjson.getString("id")));
			tipoactividad.setNombre(objjson.getString("nombre"));
			tipoactividad.setDescripcion(objjson.getString("descripcion"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return tipoactividad;
	}

	public static String agregaractividad(Actividad act) {
		String ok = null;
		Resty resty = new Resty();
		
	    JSONResource jsResource = null;
	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
		try {
			jsResource = resty.json("http://127.0.0.1:5000/actividad/agregar?idsolicitudactividad=" + act.getIdSolicitudActividad().getId()
					+"&titulo=" + act.getTitulo()
					+"&fechainicio=" + dateFormat.format(act.getFechainicio())
					+"&fechafin=" + dateFormat.format(act.getFechafin())
					//+"&hora=" + act.getHora()
					+"&recursosutilizados=" + act.getRecursosUtilizados()
					+"&montoesperado=" + act.getMontoesperado()
					+"&nroasistentesesperados=" + act.getNroasistentesesperados()
				//	+"&duracion=" + act.getDuracion()
					+"&idlugar=" + act.getLugar().getId().toString() 
					+"&descripcion=" + act.getDescripcion().replaceAll(" ", "%20"));
			

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

	@SuppressWarnings("null")
	public static List<Voluntario> buscarvoluntariosactividad(Actividad act)
	{
		ListModelList<Voluntario> voluntarios = new ListModelList<Voluntario>();
	
		Resty resty = new Resty();
		JSONResource jsResource = null;
		
		
			try {
				jsResource = resty.json("http://localhost:5000/actividad/voluntarios?id=" + act.getId());
				String ok = jsResource.get("ok").toString();
				if (ok.equalsIgnoreCase("true"))
				{
					String strVol = jsResource.get("voluntarios").toString();
					JSONArray serVoluntario = new JSONArray(strVol);
					 for(int i=0; i < serVoluntario.length(); i++)
					 {
		                  Voluntario voluntario = new Voluntario();
		                  JSONObject obj = serVoluntario.getJSONObject(i);
		                  
		                  voluntario.setCedula(obj.getString("cedula"));
		                  voluntario.setNombre(obj.getString("nombre"));
		                  voluntario.setApellido(obj.getString("apellido"));
		                  voluntario.setDireccion(obj.getString("direccion"));
		                  
		                  voluntarios.add(voluntario);

					 }
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return voluntarios;
	}
	
	public static String modificarsolicitudactividad(SolicitudActividad sol)
	{
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/solicitud-actividad/editar?id=" + sol.getId().toString()
					+ "&idtipoactividad=" + sol.getIdTipoActividad().getId()
					+ "&nombsolicitante=" + sol.getNomsolicitante().toString().replaceAll(" ", "%20") + 
					"&descricpion=" + sol.getDescripcion().replaceAll(" ", "%20"));
			
					
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

	public static String asignarvoluntario(List<Voluntario> currentVoluntario,
			Actividad actividadSelected) {
		// TODO Auto-generated method stub
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		String cedulas = "";
		for (Voluntario temp : currentVoluntario) {
			cedulas += temp.getCedula() + ",";
		} //Elimina la ultima coma de la cadena anterior
		if (cedulas.endsWith(","))
		{
			cedulas = cedulas.substring(0, cedulas.length()-1);
		}
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/actividad/asingar-voluntario?idactividad="+ actividadSelected.getId() 
					+"&cedula=" + cedulas.trim());
			
					
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

	public static String modificaractividad(Actividad act) {
		// TODO Auto-generated method stub
		Resty resty = new Resty();
		JSONResource jsResource = null;
		String ok = null;
		
		try {
		
			jsResource = resty.json("http://127.0.0.1:5000/actividad/editar?id=" + act.getId().toString()
					+ "&monto=" + act.getMonto()
					+ "&nroasistentes=" + act.getNroAsistentes() + 
					"&observaciones=" + act.getObservaciones().replaceAll(" ", "%20"));
			
					
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

	
}
