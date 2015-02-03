
package com.ucla.frontend.pectus.models;



public class MotivoSolicitud {
	
	private Integer id;
	
	private String nombre;
	private String descripcion;
	
	public MotivoSolicitud(Integer id,String nombre, String descripcion) {
		super();
		
		this.id = id;

		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	

	public MotivoSolicitud() {
		// TODO Auto-generated constructor stub
	}

	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
