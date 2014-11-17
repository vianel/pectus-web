package com.ucla.frontend.pectus.models;

public class Lugar {
	
	private Integer id;
	private Integer idTipoLugar;
	private Ciudad idCiudad;
	private String nombre;
	private String direccion;
	private String tlffijo;
	private boolean estatus;
	
	
	public Lugar(int id, int idTipoLugar, Ciudad idCiudad, String nombre, String direccion,
			String tlffijo, boolean estatus) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.idTipoLugar = idTipoLugar;
		this.idCiudad = idCiudad;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tlffijo = tlffijo;
		this.estatus = estatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdTipoLugar() {
		return idTipoLugar;
	}
	public void setIdTipoLugar(Integer idTipoLugar) {
		this.idTipoLugar = idTipoLugar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTlffijo() {
		return tlffijo;
	}
	public void setTlffijo(String tlffijo) {
		this.tlffijo = tlffijo;
	}
	public boolean isEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public Ciudad getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(Ciudad idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	

}
