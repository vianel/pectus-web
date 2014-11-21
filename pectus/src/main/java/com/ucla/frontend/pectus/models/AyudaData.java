package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AyudaData {

	//PACIENTE
	private static List<Paciente> pacientes = new ArrayList<Paciente>();
	private static List<Estado> estados = new ArrayList<Estado>();
	private static List<Ciudad> ciudades = new ArrayList<Ciudad>();
	private static List<Seguro> seguros = new ArrayList<Seguro>();
	//AYUDA
	private static List<Ayuda> ayudas = new ArrayList<Ayuda>();
	private static List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
	private static List<Estudio> estudios = new ArrayList<Estudio>();
	private static List<Clinica> clinicas = new ArrayList<Clinica>();
	private static List<EstudioClinica> estudiosClinicas = new ArrayList<EstudioClinica>();
	private static List<EstudioSolicitud> estudiosSolicitud = new ArrayList<EstudioSolicitud>();
	
	
	static{
		seguros.add(new Seguro(1,"social","publico"));
		seguros.add(new Seguro(2,"social","privado"));
		
	}
	
	static{
		estados.add(new Estado(1,"LARA",true));
		estados.add(new Estado(2, "TRUJILLO", true));
	}
	
	static{
		ciudades.add(new Ciudad(1, "Barquisimeto", estados.get(0), true));
		ciudades.add(new Ciudad(2, "Cabudare", estados.get(0), true));
	}
	
	static{
		pacientes.add(new Paciente(estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", new Date(), "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
//		pacientes.add(new Paciente(2, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
	}
	
	
	
	
	static{
		clinicas.add(new Clinica("Policlina", "2011ac2", "Centro", "0251"));
		clinicas.add(new Clinica("Canaval", "asdasdasd223", "Cabudare", "0252"));
		clinicas.add(new Clinica("Mamas", "F.asdasd", "Av. Vargas", "0251"));
	}
	
	static{
		estudios.add(new Estudio(1, "Resonancion magnetica de mamas", "Para ayudar a identificar mejor la tumoraci�n mamaria o evaluar un cambio anormal en una mamograf�a."));
		estudios.add(new Estudio(2, "Ecograf�a de las mamas", "para mostrar si la tumoraci�n es s�lida o est� llena de l�quido.Biopsia de mama: usando m�todos como biopsia aspirativa, guiada por ecograf�a, estereot�ctica o abierta."));
	}
	
	static{
		estudiosClinicas.add(new EstudioClinica(1, clinicas.get(0), estudios.get(0), 200.0));
		estudiosClinicas.add(new EstudioClinica(2, clinicas.get(1), estudios.get(1), 100.0));
	}
	
	static{
		estudiosSolicitud.add(new EstudioSolicitud(1, null, estudiosClinicas.get(0),0.0, new Date(), true));
		estudiosSolicitud.add(new EstudioSolicitud(2, null, estudiosClinicas.get(1),0.0,new Date(), true));
	}

	static{
		diagnosticos.add(new Diagnostico(1, "Cancer", "asdasd"));
	}
	
	static{
		//ayudas.add(new Ayuda(pacientes.get(0), diagnosticos.get(0), "NECESIDAD", new Date()));
//		ayudas.add(new Ayuda(pacientes.get(1), diagnosticos.get(0), "Falta de plata", new Date(), estudiosSolicitud));
	}
	public static List<Ayuda> getAllAyudas(){
		return new ArrayList<Ayuda>(ayudas);
	}
	
}