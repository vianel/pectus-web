package com.ucla.frontend.pectus.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.zkoss.zul.ListModelList;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ucla.frontend.pectus.models.Paciente;

import static org.junit.Assert.assertEquals;


public class Testunirest {
	  private ListModelList<Paciente> listaModelPaciente;
	
	
	public Testunirest() {
		super();
		// TODO Auto-generated constructor stub
		try {
			this.testGetUTF8();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testGetUTF8() throws UnirestException {
	

	    List<Paciente> listaPaciente = new ArrayList<Paciente>();
		
		HttpResponse response = Unirest.get("http://localhost:5000/paciente/todos")
		  .asJson();
		
		
	
		/*JSONArray body = (JsonNode) response.getBody();
        body.getArray().getJSONObject(0).get("paciente");
*/
      //  listaPaciente.addAll((Collection<? extends Paciente>) body.getObject());

	
	}
	
}
