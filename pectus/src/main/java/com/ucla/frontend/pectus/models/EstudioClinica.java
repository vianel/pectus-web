package com.ucla.frontend.pectus.models;



public class EstudioClinica {
	
	private Integer id;
	private Clinica clinica;
	private Estudio estudio;
	private Double monto;
//	private boolean estatus;
//	private List<EstudioSolicitud> listaEstudiosSolicitud;
	
	public EstudioClinica(){
		
	}
	
	
	
	

	public EstudioClinica(Integer id, Clinica clinica, Estudio estudio,
			Double monto) {
		super();
		this.id = id;
		this.clinica = clinica;
		this.estudio = estudio;
		this.monto = monto;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	

}
