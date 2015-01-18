package com.ucla.frontend.pectus.models;

public class ResultadoAyuda {

	private Integer id ;
	private Cita cita;
	private Character resultado;
	private Character gradoPatologia;
	private String observacion;
	
	
	public ResultadoAyuda() {
		
	}


	public ResultadoAyuda(Integer id, Cita cita, Character resultado,
			Character gradoPatologia, String observacion) {
		super();
		this.id = id;
		this.cita = cita;
		this.resultado = resultado;
		this.gradoPatologia = gradoPatologia;
		this.observacion = observacion;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Cita getCita() {
		return cita;
	}


	public void setCita(Cita cita) {
		this.cita = cita;
	}


	public Character getResultado() {
		return resultado;
	}


	public void setResultado(Character resultado) {
		this.resultado = resultado;
	}


	public Character getGradoPatologia() {
		return gradoPatologia;
	}


	public void setGradoPatologia(Character gradoPatologia) {
		this.gradoPatologia = gradoPatologia;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
