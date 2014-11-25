package com.ucla.frontend.pectus.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;







import java.util.logging.Level;



import java.util.logging.Logger;








import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonParseException;
import com.ucla.frontend.pectus.exceptions.ExceptionHttpPersonalizada;
import com.ucla.frontend.pectus.models.Paciente;
import com.ucla.frontend.pectus.models.Persona;

import com.ucla.frontend.pectus.services.ServicioPaciente;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;



public class PersonaView {
	



	
		private ListModelList<Persona> listModelPersona;
	    private List<Persona> listaPersonas;
	    private Persona personaSeleccionada;
	    private Paciente pacienteSeleccionado;
	    private ServicioPaciente serviciopaciente;

	

	    private ListModelList<Paciente> listaModelPaciente;
	    private List<Paciente> listaPaciente;
	    
	    
	

		@Init
	    public void init(){
	    //	this.cargarListaPersonas();
		this.cargarListaPaciente();
	    //this.listModelPersona = new ListModelList<Persona>(this.getListaPersonas());
	    	this.listaModelPaciente = new ListModelList<Paciente>(this.getListaPaciente());
	    	
	    }
	    
	    public void cargarListaPaciente(){
	    	

	        this.listaPaciente = new ArrayList<Paciente> (serviciopaciente.buscarPacientes());
	        
	    }
	    
	    public Paciente getPacienteSeleccionado() {
			return pacienteSeleccionado;
		}

		public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
			this.pacienteSeleccionado = pacienteSeleccionado;
		}

		public ListModelList<Paciente> getListaModelPaciente() {
			return listaModelPaciente;
		}

		public void setListaModelPaciente(ListModelList<Paciente> listaModelPaciente) {
			this.listaModelPaciente = listaModelPaciente;
		}

		public List<Paciente> getListaPaciente() {
			if (this.listaPaciente == null) {
			    this.listaPaciente = new ArrayList<Paciente>();
			}
			return listaPaciente;
		}

		public void setListaPaciente(List<Paciente> listaPaciente) {
			this.listaPaciente = listaPaciente;
		}

	 /*   
	    public void cargarListaPersonas(){
	    	try{
	    	this.getListaPersonas().addAll((List<Persona>) this.getProveedorGenericoObjetosREST().getObjeto(ClienteHttp.getInstancia().getCliente(), ServiciosDisponibles.PERSONA, ""));
	    	} catch (JsonMappingException jsonMappingException) {
	    	    log.log(Level.SEVERE, null, jsonMappingException);
	    	} catch (JsonParseException jsonParseException) {
	    	    log.log(Level.SEVERE, null, jsonParseException);
	    	} catch (IOException iOException) {
	    	    log.log(Level.SEVERE, null, iOException);
	    	} catch (ExceptionHttpPersonalizada exceptionHttpPersonalizada) {
	    	    log.log(Level.SEVERE, null, exceptionHttpPersonalizada);
	    	}
	    	}
	    
	    */
	   

		public ListModelList<Persona> getListModelPersona() {
			return listModelPersona;
		}
		public void setListModelPersona(ListModelList<Persona> listModelPersona) {
			this.listModelPersona = listModelPersona;
		}
		public List<Persona> getListaPersonas() {
			if (this.listaPersonas == null) {
			    this.listaPersonas = new ArrayList<Persona>();
			}
		        return listaPersonas;
		}
		public void setListaPersonas(List<Persona> listaPersonas) {
			this.listaPersonas = listaPersonas;
		}
		public Persona getPersonaSeleccionada() {
			return personaSeleccionada;
		}
		public void setPersonaSeleccionada(Persona personaSeleccionada) {
			this.personaSeleccionada = personaSeleccionada;
		}
		
	    private String convertStreamToString(InputStream is) {
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
	    
	    

}
