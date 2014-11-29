package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.models.Colaboracion;
import com.ucla.frontend.pectus.services.ServicioColaboracion;

public class ColaboracionFilter {
	
	private String nombreColaboracion;
	private String nombrePatrocinador;
	private String nombreEvento;
	
	static List<Colaboracion> colaboraciones = ServicioColaboracion.buscarColaboraciones();

	public String getNombreColaboracion() {
		return nombreColaboracion;
	}

	public void setNombreColaboracion(String nombreColaboracion) {
		this.nombreColaboracion = nombreColaboracion==null?"":nombreColaboracion.trim();;
	}

	public String getNombrePatrocinador() {
		return nombrePatrocinador;
	}

	public void setNombrePatrocinador(String nombrePatrocinador) {
		this.nombrePatrocinador = nombrePatrocinador==null?"":nombrePatrocinador.trim();
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento==null?"":nombreEvento.trim();
	}
	
	public static List<Colaboracion> getFilterColaboraciones(ColaboracionFilter colaboracionFilter){
		List<Colaboracion> someColaboraciones = new ArrayList<Colaboracion>();
		String nombreEvento = colaboracionFilter.getNombreEvento().toLowerCase();
		String nombrePatrocinador = colaboracionFilter.getNombrePatrocinador().toLowerCase();
		String nombreColaboracion = colaboracionFilter.getNombreColaboracion();
		for(Iterator<Colaboracion> i = colaboraciones.iterator(); i.hasNext();){
			Colaboracion tmp = i.next();
			if(tmp.getEvento().getNombre() .toLowerCase().contains(nombreEvento) &&
			   tmp.getPatrocinado().getNombre().toLowerCase().contains(nombrePatrocinador)&&
			   tmp.getTipoColaboracion().getNombre().toLowerCase().contains(nombreColaboracion)){
			
				someColaboraciones.add(tmp);
			}
				
		}
		return someColaboraciones;
	}

//	public static List<Colaboracion> getPacientesByCedula(String cedula){
//		List<Colaboracion> somePacientes = new ArrayList<Colaboracion>();
//		for (Iterator<Colaboracion> i = pacientes.iterator(); i.hasNext();){
//			Colaboracion tmp = i.next();
//			if(tmp.getCedula().equalsIgnoreCase(cedula)){
//				somePacientes.add(tmp);
//			}
//		}
//		return somePacientes;
//	}
	
	

}
