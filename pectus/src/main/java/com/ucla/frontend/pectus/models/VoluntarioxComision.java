package com.ucla.frontend.pectus.models;


public class VoluntarioxComision {




	private Integer id;
	private Comision comision;
	private Voluntario voluntario;
	

	public VoluntarioxComision() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VoluntarioxComision(Integer id, Comision comision,
			Voluntario voluntario) {
		super();
		this.id = id;
		this.comision = comision;
		this.voluntario = voluntario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Comision getComision() {
		return comision;
	}

	public void setComision(Comision comision) {
		this.comision = comision;
	}

	public Voluntario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}
	
}
