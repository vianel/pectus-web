package com.ucla.frontend.pectus.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.ucla.frontend.pectus.controllers.CitaFilter;
import com.ucla.frontend.pectus.controllers.PacienteFilter;

public class PacienteData {
	
	private static List<Paciente> pacientes = new ArrayList<Paciente>();
	private static List<Estado> estados = new ArrayList<Estado>();
	private static List<Ciudad> ciudades = new ArrayList<Ciudad>();
	private static List<Seguro> seguros = new ArrayList<Seguro>();
	
	private static List<Estudio> estudios = new ArrayList<Estudio>();
	private static List<Clinica> clinicas = new ArrayList<Clinica>();
	private static List<Cita> citas = new ArrayList<Cita>();
	
	
	
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
/*		pacientes.add(new Paciente(1, estados.get(0), ciudades.get(0), seguros.get(0), "14745213", "Maria", "Perez", "0424", "0251", "Brisas", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(2, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(3, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(4, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(5, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(6, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(7, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(8, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(9, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(10, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(11, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
		pacientes.add(new Paciente(12, estados.get(0), ciudades.get(1), seguros.get(1), "13252025", "Josefa", "Martinez", "0424", "0251", "Centro", "maria@.com", "09/78/2014", "Maestra", 'c', 10, "12587456", "Jose", "Briceno", "Nada", 'v', 8, 'v', 10.000, "Escuela", "Centro", "0251", 50000, 20000, true));
*/
	}
	
	
	static{
		clinicas.add(new Clinica(1, "Policlina", "2011ac2", "Centro", "0251", null, true));
		clinicas.add(new Clinica(2, "Canaval", "asdasdasd223", "Cabudare", "0252", null, true));
		clinicas.add(new Clinica(3, "Mamas", "F.asdasd", "Av. Vargas", "0251", null, true));
	}
	
	static{
		estudios.add(new Estudio(1, "Resonancion magnetica de mamas", "Para ayudar a identificar mejor la tumoraci�n mamaria o evaluar un cambio anormal en una mamograf�a.", null, true));
		estudios.add(new Estudio(2, "Ecograf�a de las mamas", "para mostrar si la tumoraci�n es s�lida o est� llena de l�quido.Biopsia de mama: usando m�todos como biopsia aspirativa, guiada por ecograf�a, estereot�ctica o abierta." , null, true));
	}
	
	
	static{
		citas.add(new Cita(1, new Date(), "2:30", pacientes.get(0), clinicas.get(0), estudios.get(0), true));
		citas.add(new Cita(2, new Date(), "6:30", pacientes.get(1), clinicas.get(2), estudios.get(1), true));
		citas.add(new Cita(3, new Date(), "2:00", pacientes.get(0), clinicas.get(0), estudios.get(0), true));
		citas.add(new Cita(4, new Date(), "3:30", pacientes.get(1), clinicas.get(1), estudios.get(1), true));
		citas.add(new Cita(5, new Date(), "8:15", pacientes.get(0), clinicas.get(2), estudios.get(1), true));
		citas.add(new Cita(6, new Date(), "3:30", pacientes.get(1), clinicas.get(1), estudios.get(1), true));
		citas.add(new Cita(7, new Date(), "1:30", pacientes.get(0), clinicas.get(0), estudios.get(0), true));
		citas.add(new Cita(8, new Date(), "0:30", pacientes.get(1), clinicas.get(2), estudios.get(1), true));
	}
	

	public static List<Paciente> getAllPacientes(){
		return new ArrayList<Paciente>(pacientes);
	}
	
	public static List<Cita> getAllCitas(){
		return new ArrayList<Cita>(citas);
	}
	
	public static Paciente[] getAllPacienteArray(){
		return pacientes.toArray(new Paciente[pacientes.size()]);
	}
	
	public static List<Paciente> getFilterPacientes(PacienteFilter pacienteFilter){
		List<Paciente> somePacientes = new ArrayList<Paciente>();
		String nombre = pacienteFilter.getNombre().toLowerCase();
		String apellido = pacienteFilter.getApellido().toLowerCase();
		String cedula = pacienteFilter.getCedula().toLowerCase();
		
		for(Iterator<Paciente> i = pacientes.iterator(); i.hasNext();){
			Paciente tmp = i.next();
			if(tmp.getNombre().toLowerCase().contains(nombre) &&
			   tmp.getApellido().toLowerCase().contains(apellido)&&
			   tmp.getCedula().toLowerCase().contains(cedula)){
			
				somePacientes.add(tmp);
			}
				
		}
		return somePacientes;
	}
	
	
	public static List<Cita> getFilterCitas(CitaFilter citaFilter){
		List<Cita> someCitas = new ArrayList<Cita>();		
		String nombrePaciente = citaFilter.getNombrePaciente().toLowerCase();
		String nombreClinica = citaFilter.getNombreClinica().toLowerCase();
		String nombreEstudio = citaFilter.getNombreEstudio().toLowerCase();
						
		
		for(Iterator<Cita> i = citas.iterator(); i.hasNext();){
			Cita cta = i.next();
			if(cta.getPaciente().getNombre().toLowerCase().contains(nombrePaciente) &&
			   cta.getClinica().getNombre().toLowerCase().contains(nombreClinica) &&
			   cta.getEstudio().getNombre().toLowerCase().contains(nombreEstudio)){
				someCitas.add(cta);
			}
		}
		return someCitas;
	}
	
	
	
	
	public static List<Paciente> getPacientesByCedula(String cedula){
		List<Paciente> somePacientes = new ArrayList<Paciente>();
		for (Iterator<Paciente> i = pacientes.iterator(); i.hasNext();){
			Paciente tmp = i.next();
			if(tmp.getCedula().equalsIgnoreCase(cedula)){
				somePacientes.add(tmp);
			}
		}
		return somePacientes;
	}

}
