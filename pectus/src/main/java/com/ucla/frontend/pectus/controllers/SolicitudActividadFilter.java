package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.SolicitudActividad;
import com.ucla.frontend.pectus.models.TipoActividad;
import com.ucla.frontend.pectus.models.TipoEstudio;
import com.ucla.frontend.pectus.services.ServicioActividad;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;

public class SolicitudActividadFilter {

	private TipoActividad idTipoActividad;
	private String  descripcion;
	private Date fecha;
	private String nomsolicitante;
	private String tlfsolicitante;
	static List<SolicitudActividad> solicitudActividad = ServicioActividad.buscarsolicitudactividades();
	
	public SolicitudActividadFilter() {
		// TODO Auto-generated constructor stub
	}



	public TipoActividad getIdTipoActividad() {
		return idTipoActividad;
	}



	public void setIdTipoActividad(TipoActividad idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getNomsolicitante() {
		return nomsolicitante;
	}



	public void setNomsolicitante(String nomsolicitante) {
		this.nomsolicitante = nomsolicitante;
	}



	public String getTlfsolicitante() {
		return tlfsolicitante;
	}



	public void setTlfsolicitante(String tlfsolicitante) {
		this.tlfsolicitante = tlfsolicitante;
	}



	public static List<SolicitudActividad> getFilterSolicitudActiviad(
			SolicitudActividadFilter solicitudActividadFilter) {
		// TODO Auto-generated method stub
		List<SolicitudActividad> solact = new ArrayList<SolicitudActividad>();
		
		String nombre = solicitudActividadFilter.getNomsolicitante().toLowerCase();
		String descripcion = solicitudActividadFilter.getDescripcion().toLowerCase();
		
		for(Iterator<SolicitudActividad> i = solicitudActividad.iterator(); i.hasNext();){
			SolicitudActividad tmp = i.next();
			
			if(tmp.getNomsolicitante().toLowerCase().contains(nombre) &&
			   tmp.getDescripcion().toLowerCase().contains(descripcion))
			  
			{
			
				solact.add(tmp);
			}
				
		}
		return solact;
	}
	

	
	
}
