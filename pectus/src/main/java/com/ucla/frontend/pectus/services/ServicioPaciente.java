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

import com.ucla.frontend.pectus.models.Ciudad;
import com.ucla.frontend.pectus.models.Estado;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Seguro;

public class ServicioPaciente {
	
	  // static AsyncHttpClient httpclient = new AsyncHttpClient();

    static String BASE_URL = "http://localhost:5000/paciente/todos";
    private ListModelList<Paciente> listaModelPaciente;
    private Paciente pacienteSeleccionado;
    
    public Paciente getPacienteSeleccionado() {
		return pacienteSeleccionado;
	}

	public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
		this.pacienteSeleccionado = pacienteSeleccionado;
	}

	public ServicioPaciente (){
    	this.BuscarPacientes();

    }
    
	@Init
    public void init(){
    //	this.cargarListaPersonas();
	
    //this.listModelPersona = new ListModelList<Persona>(this.getListaPersonas());
    	this.setListaModelPaciente(new ListModelList<Paciente>(this.BuscarPacientes()));
    	
    }
	

    public static List<Paciente> BuscarPacientes()
    {

    	
        List<Paciente> listaPaciente = new ArrayList<Paciente>();
        HttpClient httpclient = new DefaultHttpClient();
        
        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://localhost:5000/paciente/todos");
        HttpResponse response;
        try {
            // Ejecutar el request
            response = httpclient.execute(httpget);
            // Obtener la entidad del response
            HttpEntity entity = response.getEntity();
            // Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
            if (entity != null) {
                // el JSON Response es leido
                InputStream instream = entity.getContent();
                String resultado = convertStreamToString(instream);
                // Un objeto JSONObject se crea
                try{
                    JSONObject json = new JSONObject(resultado);
                    JSONArray serPaciente = json.getJSONArray("paciente");
                   
                    for(int i=0; i < serPaciente.length(); i++){
                        Paciente paciente = new Paciente();
                        
                    
                        paciente.setNombre((String) serPaciente.getJSONObject(i).get("nombre"));
                   //     Log.v("codigoPago",(String) serPaciente.getJSONObject(i).get("nombre"));
                        paciente.setApellido((String) serPaciente.getJSONObject(i).get("apellido"));
                        paciente.setCedula((String) serPaciente.getJSONObject(i).get("cedula"));
                        paciente.setDireccion((String) serPaciente.getJSONObject(i).get("direccion"));
                        paciente.setFechaNacimiento((String) serPaciente.getJSONObject(i).get("fecnacimiento"));
                        paciente.setCelular((String) serPaciente.getJSONObject(i).getString("tlfcelular"));
                        paciente.setFijo((String) serPaciente.getJSONObject(i).getString("tlffijo"));
                        paciente.setProfesion((String) serPaciente.getJSONObject(i).getString("profesion"));
                        paciente.setNroHijos((Integer.parseInt((String) serPaciente.getJSONObject(i).getString("nrohijos"))));
                        paciente.setIngresos((Float.parseFloat(serPaciente.getJSONObject(i).getString("ingfamiliares"))));
                        paciente.setEgresos((Float.parseFloat(serPaciente.getJSONObject(i).getString("egrfamiliares"))));
                        paciente.setSeguro(obtenerSeguro(serPaciente.getJSONObject(i).getString("tiposeguro"))); 
                        paciente.setCiudad(obtenerciudad(serPaciente.getJSONObject(i).getString("ciudad")));

                        paciente.setEstado(obtenerciudad(serPaciente.getJSONObject(i).getString("ciudad")).getEstado());  

                        paciente.setEstado(obtenerciudad(serPaciente.getJSONObject(i).getString("ciudad")).getEstado());

                     
                    
                        listaPaciente.add(paciente);
                    }
                }catch(JSONException  e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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

	public ListModelList<Paciente> getListaModelPaciente() {
		return listaModelPaciente;
	}

	public void setListaModelPaciente(ListModelList<Paciente> listaModelPaciente) {
		this.listaModelPaciente = listaModelPaciente;
	}
	
	//································································································





}


