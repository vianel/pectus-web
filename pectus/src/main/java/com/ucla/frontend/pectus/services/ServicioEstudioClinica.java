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

import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.models.Estudio;
import com.ucla.frontend.pectus.models.Paciente;

public class ServicioEstudioClinica {
	
	  // static AsyncHttpClient httpclient = new AsyncHttpClient();

    static String BASE_URL = "http://localhost:5000/tipo-estudio/todos";//aqui va Estudio
    private EstudioClinica modelEstudioClinica;
    private EstudioClinica EstudioClinicaSeleccionada;
    private String rif,idTipoEstudio,id;
    private ListModelList<Estudio> listaModelEstudio;
    private Estudio EstudioSeleccionada;
    private ListModelList<Clinica> listaModelClinica;
    private Clinica clinicaSeleccionada;
    
    
    public EstudioClinica getPacienteSeleccionado() {
		return EstudioClinicaSeleccionada;
	}

	public void setPacienteSeleccionado(EstudioClinica EstudioSeleccionada) {
		this.EstudioClinicaSeleccionada = EstudioClinicaSeleccionada;
	}

	public ServicioEstudioClinica (){
    	this.BuscarEstudioClinica(rif,idTipoEstudio);
    	this.BuscarClinicas();
    	this.BuscarEstudios();
    	this.BuscarClinica(rif);
    	this.BuscarEstudio(id);
    	
    }
    
	@Init
    public void init(){
    //	this.cargarListaPersonas();
	
    //this.listModelPersona = new ListModelList<Persona>(this.getListaPersonas());
 //   	this.setModelEstudioClinica(new EstudioClinica(this.BuscarEstudioClinica()));
		//this.setListaModelClinica(new ListModelList<Clinica>(this.BuscarClinicas()));
	    
    }
	

    public static EstudioClinica BuscarEstudioClinica(String rif,String idTipoEstudio)
    {

    	   EstudioClinica estudioClinica = new EstudioClinica();
    	   
        Resty resty = new Resty();
        JSONResource jsResource = null;
        try {
            jsResource = resty.json("http://127.0.0.1:5000/estudio/buscar?rif="+rif+"&idtipoestudio="+idTipoEstudio);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        try {
            
        	String strPa = jsResource.get("estudios").toString();
            
        			JSONArray obj = new JSONArray(strPa);
        			JSONObject objjson = obj.getJSONObject(0);
              
                          estudioClinica.setId((Integer) objjson.get("id"));
                          estudioClinica.setClinica(obtenerClinica(objjson.get("clinica").toString()));
                          estudioClinica.setEstudio(obtenerTipoEstudio(objjson.get("tipoestudio").toString()));
                          estudioClinica.setMonto(Double.parseDouble(objjson.get("monto").toString()));
             
                                     
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

		
        return estudioClinica;
        
        
    }



	public static List<EstudioClinica> buscarEstudioClinica()
    {

        List<EstudioClinica> listaEstudiosXClinica = new ArrayList<EstudioClinica>();
     
        

        Resty resty = new Resty();
        JSONResource jsResource = null;
		try {
			jsResource = resty.json("http://127.0.0.1:5000/estudio/todos");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			String ok = jsResource.get("ok").toString();
			if (ok.equalsIgnoreCase("true")) {
			
			String strPa = jsResource.get("estudios").toString();
			JSONArray serEstudio = new JSONArray(strPa);
			  for(int i=0; i < serEstudio.length(); i++){
                  EstudioClinica estudioClinica = new EstudioClinica();
                  JSONObject obj = serEstudio.getJSONObject(i);
                
                  estudioClinica.setId((Integer) obj.get("id"));
                  estudioClinica.setClinica(obtenerClinica(obj.get("clinica").toString()));
                  estudioClinica.setEstudio(obtenerTipoEstudio(obj.get("tipoestudio").toString()));
                  estudioClinica.setMonto(Double.parseDouble(obj.get("monto").toString()));
                  
                  listaEstudiosXClinica.add(estudioClinica);
                  
       
			  
			  } //fin For
			
			} //fin IF
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		System.out.println(listaEstudiosXClinica.get(0).getClinica().getNombre());
        return listaEstudiosXClinica;
        
    }

    





    public Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));


        return calendario.getTime();
    }

    private static String convertStreamToString(InputStream is) {
        /*
         * Para convertir un InputStream a String se usa el metodo BufferedReader.readLine()
         * Iteramos hasta que el BufferedReader retone null lo cual significa
         * que no hay mas datos para leer. Cada linea sera agregada al StringBuilder
         * y sera retornado como un String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return sb.toString();
    }

	public EstudioClinica getModelEstudioClinica() {
		return modelEstudioClinica;
	}

	public void setModelEstudioClinica(EstudioClinica modelEstudioClinica) {
		this.modelEstudioClinica = modelEstudioClinica;
	}

	
	

	private static Clinica obtenerClinica(String s) {
		// TODO Auto-generated method stub
		Clinica clinica = new Clinica();
				
		
		try {
			JSONObject objjson = new JSONObject(s);
			//JSONObject objjson = serClinica.getJSONObject(0);
			 
				clinica.setDireccion( objjson.get("direccion").toString());
	            clinica.setNombre( objjson.get("nombre").toString());
	            clinica.setRif( objjson.get("rif").toString());
	            clinica.setTelefono(objjson.get("tlffijo").toString());
	            
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clinica;
	}
	
	private static Estudio obtenerTipoEstudio(String s) {
		// TODO Auto-generated method stub
		Estudio estudio = new Estudio();
		try {
			JSONObject objjson = new JSONObject(s);
				estudio.setDescripcion((objjson.get("descripcion").toString()));
				estudio.setId(Integer.parseInt(objjson.get("id").toString()));
				estudio.setNombre(objjson.get("nombre").toString());
              
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estudio;
	}

	public static String agregarEstudioClinica(EstudioClinica estudioClinica)
	{

		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/estudio/agregar?id=" +""+
					"&idtipoestudio=" + estudioClinica.getEstudio().getId() +
					"&rif=" + estudioClinica.getClinica().getRif() +
					"&monto=" + estudioClinica.getMonto()
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
	public static String editarEstudioClinica(EstudioClinica estudioClinica)
	{
		System.out.println(estudioClinica.getMonto());
		
		Resty resty = new Resty();
	    JSONResource jsResource = null;
	    String ok = null;
		try {
			jsResource = resty.json("http://localhost:5000/estudio/editar?id=" +estudioClinica.getId()+
					"&idtipoestudio=" + estudioClinica.getEstudio().getId() +
					"&rif=" + estudioClinica.getClinica().getRif() +
					"&monto=" + estudioClinica.getMonto()
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

	
//**********************************ESTUDIO******************************
	
    public Estudio getEstudioSeleccionado() {
		return EstudioSeleccionada;
	}

	public void setEstudioSeleccionado(Estudio EstudioSeleccionada) {
		this.EstudioSeleccionada = EstudioSeleccionada;
	}

    public static List<Estudio> BuscarEstudios()
    {

    	
        List<Estudio> listaEstudio = new ArrayList<Estudio>();
        Resty resty = new Resty();
        JSONResource jsResource = null;
        try {
            jsResource = resty.json("http://127.0.0.1:5000/tipo-estudio/todos");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        try {
            String ok = jsResource.get("ok").toString();
            if (ok.equalsIgnoreCase("true")) {
            
            String strPa = jsResource.get("tipoestudios").toString();
            JSONArray serEstudio = new JSONArray(strPa);
              for(int i=0; i < serEstudio.length(); i++){
                  Estudio estudio = new Estudio();
                  JSONObject obj = serEstudio.getJSONObject(i);
               
                         estudio.setDescripcion((String) serEstudio.getJSONObject(i).get("descripcion"));
                         estudio.setId((Integer) serEstudio.getJSONObject(i).get("id"));
                         estudio.setNombre((String) serEstudio.getJSONObject(i).get("nombre"));
                         
                     
                    
                        listaEstudio.add(estudio);
     
              
              } //fin For
            
            } //fin IF
            
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
 

        return listaEstudio;
        
    }




    

	public ListModelList<Estudio> getListaModelEstudio() {
		return listaModelEstudio;
	}

	public void setListaModelEstudio(ListModelList<Estudio> listaModelPaciente) {
		this.listaModelEstudio = listaModelEstudio;
	}
	
	//································································································

    public static Estudio BuscarEstudio(String id)
    {

    	   Estudio estudio = new Estudio();
    	   
        Resty resty = new Resty();
        JSONResource jsResource = null;
        try {
            jsResource = resty.json("http://127.0.0.1:5000/tipo-estudio/buscar?id="+id);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        try {
            
        	String strPa = jsResource.get("tipoestudio").toString();
        	JSONArray obj = new JSONArray(strPa);
        			JSONObject objjson = obj.getJSONObject(0);
                
			      		 estudio.setDescripcion(objjson.get("descripcion").toString());
			      		 estudio.setId(Integer.parseInt(objjson.get("id").toString()));
                         estudio.setNombre(objjson.get("nombre").toString());
                         
                     
                                     
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return estudio;
        
        
    }

//********************************Clinica*****************************
    

    public Clinica getClinicaSeleccionado() {
		return clinicaSeleccionada;
	}

	public void setClinicaSeleccionado(Clinica clinicaSeleccionada) {
		this.clinicaSeleccionada = clinicaSeleccionada;
	}
	
    public static Clinica BuscarClinica(String rif)
    {

    	   Clinica clinica = new Clinica();
    	   
        Resty resty = new Resty();
        JSONResource jsResource = null;
        try {
            jsResource = resty.json("http://127.0.0.1:5000/clinica/buscar?rif="+rif);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        try {
        	System.out.println("holaaa");
        	    
        	String strPa = jsResource.get("clinicas").toString();
        	JSONArray obj = new JSONArray(strPa);
        			JSONObject objjson = obj.getJSONObject(0);
            
    		
        	
			      		 clinica.setDireccion(objjson.get("direccion").toString());
			      		 clinica.setNombre(objjson.get("nombre").toString());
                         clinica.setRif(objjson.get("rif").toString());
                         clinica.setTelefono(objjson.get("tlffijo").toString());
                         
                                    
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return clinica;
        
        
    }
    

    public static List<Clinica> BuscarClinicas()
    {

    	
        List<Clinica> listaClinica = new ArrayList<Clinica>();
        Resty resty = new Resty();
        JSONResource jsResource = null;
        try {
            jsResource = resty.json("http://127.0.0.1:5000/clinica/todos");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 
        try {
            String ok = jsResource.get("ok").toString();
            if (ok.equalsIgnoreCase("true")) {
            
            String strPa = jsResource.get("clinicas").toString();
            JSONArray serClinica = new JSONArray(strPa);
              for(int i=0; i < serClinica.length(); i++){
                  Clinica clinica = new Clinica();
                  JSONObject obj = serClinica.getJSONObject(i);
                  
                            clinica.setDireccion((String) serClinica.getJSONObject(i).get("direccion"));
                            clinica.setNombre((String) serClinica.getJSONObject(i).get("nombre"));
                            clinica.setRif((String) serClinica.getJSONObject(i).get("rif"));
                            clinica.setTelefono((String) serClinica.getJSONObject(i).get("tlffijo"));

   
                  
                  listaClinica.add(clinica);
              
              } //fin For
            
            } //fin IF
            
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
 

        return listaClinica;
        
    }



	public ListModelList<Clinica> getListaModelClinica() {
		return listaModelClinica;
	}

	public void setListaModelClinica(ListModelList<Clinica> listaModelPaciente) {
		this.listaModelClinica = listaModelClinica;
	}



}