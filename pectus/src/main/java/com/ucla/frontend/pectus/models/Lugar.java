package com.ucla.frontend.pectus.models;

public class Lugar {
	
	private Integer id;
	private TipoLugar tipoLugar;
	private Ciudad ciudad;
	private String nombre;
	private String direccion;
	private String tlffijo;
	
	public Lugar(){
		
	}

	public Lugar(Integer id, TipoLugar tipoLugar, Ciudad ciudad, String nombre,
			String direccion, String tlffijo) {
		super();
		this.id = id;
		this.tipoLugar = tipoLugar;
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tlffijo = tlffijo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoLugar getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(TipoLugar tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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
	
	
}
