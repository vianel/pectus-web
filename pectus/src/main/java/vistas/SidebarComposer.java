package vistas;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;

import com.ucla.frontend.pectus.models.Modulo;
import com.ucla.frontend.pectus.services.ServicioModulo;

public class SidebarComposer  {

	private boolean respaldo;
	private boolean tipoestudio;
	private boolean maestros;
	private boolean maestropacientes;
	private boolean maestropatologias;
	private boolean maestropolizas;
	private boolean maestromotivosol;
	private boolean maestrorechazosol;
	private boolean clinicaestudio;
	private boolean maestroestudioclinica;
	private boolean maestroactividadevento;
	private boolean patrocinador;
	private boolean tipocolaboracion;
	private boolean tipoactividad;
	private boolean maestrocomisiones;
	private boolean maestrovoluntario;
	private boolean maestroasignarvolcomisiones;
	private boolean maestrotipolugar;
	private boolean maestrolugar;
	private boolean gestionpaciente;
	private boolean cita;
	private boolean visita;
	private boolean solayuda;
	private boolean evalsolicitud;
	private boolean registrarcita;
	private boolean resulcita;
	private boolean educacionprevencion;
	private boolean gestionactividades;
	private boolean solactividad;
	private boolean registraractividad;
	private boolean asignarvoluntarioactividad;
	private boolean resulactividad;
	private boolean gestionevento;
	private boolean registrarevento;
	private boolean asignarvoluntarioevento;
	private boolean registrarcolaboevento;
	private boolean resulevento;
	private boolean consultas;
	private boolean adminsistema;
	private List<Modulo> modulos;

	public SidebarComposer() {
		super();
		// TODO Auto-generated constructor stub
		modulos = ServicioModulo.buscarModulos();
	//************* PRINCIPALES ****************
		maestros = true;
			maestropacientes = true;
				gestionpaciente = true;
				maestroactividadevento = true;
		adminsistema = true;
		consultas = true;
		educacionprevencion = true;
	// *****************************************
		buscarmodulos(modulos);
		
		
	}
	public void buscarmodulos (List<Modulo> modulos)
	{
		
		for (int i=0;i< modulos.size(); i++)
		{
			if (modulos.get(i).getNombre().equals("Patologia"))  { maestropatologias = true; }
			if (modulos.get(i).getId() == 2) {maestropolizas = true; }
			if (modulos.get(i).getId() == 3) {maestromotivosol = true; }
			if (modulos.get(i).getId() == 4) {maestrorechazosol = true; }
			if (modulos.get(i).getId() == 5) {clinicaestudio = true; }
			if (modulos.get(i).getId() == 6) { tipoestudio = true; }
			if (modulos.get(i).getId() == 7) {maestroestudioclinica = true; }
			// EL 8 dice en la db publicaciones if (modulos.get(i).getId() == 8) {con quien = true; }
			if (modulos.get(i).getId() == 9) {patrocinador = true; }
			if (modulos.get(i).getId() == 10) {tipocolaboracion = true; }
			if (modulos.get(i).getId() == 11) {tipoactividad = true; }
			if (modulos.get(i).getId() == 12) {maestrocomisiones = true; }
			if (modulos.get(i).getId() == 13) {maestrovoluntario = true; }
			if (modulos.get(i).getId() == 14) {maestrotipolugar = true; }
		}
	}

	 
	@Command
	public void loguearse()
	{
		Executions.sendRedirect("../home/index.zul");

	}
	public boolean getRespaldo() {
		return respaldo;
	}

	public void setRespaldo(boolean respaldo) {
		this.respaldo = respaldo;
	}


	public boolean isTipoestudio() {
		return tipoestudio;
	}


	public void setTipoestudio(boolean tipoestudio) {
		this.tipoestudio = tipoestudio;
	}


	public boolean isMaestros() {
		return maestros;
	}


	public void setMaestros(boolean maestros) {
		this.maestros = maestros;
	}


	public boolean isMaestropacientes() {
		return maestropacientes;
	}


	public void setMaestropacientes(boolean maestropacientes) {
		this.maestropacientes = maestropacientes;
	}


	public boolean isMaestropatologias() {
		return maestropatologias;
	}


	public void setMaestropatologias(boolean maestropatologias) {
		this.maestropatologias = maestropatologias;
	}


	public boolean isMaestropolizas() {
		return maestropolizas;
	}


	public void setMaestropolizas(boolean maestropolizas) {
		this.maestropolizas = maestropolizas;
	}


	public boolean isMaestromotivosol() {
		return maestromotivosol;
	}


	public void setMaestromotivosol(boolean maestromotivosol) {
		this.maestromotivosol = maestromotivosol;
	}


	public boolean isMaestrorechazosol() {
		return maestrorechazosol;
	}


	public void setMaestrorechazosol(boolean maestrorechazosol) {
		this.maestrorechazosol = maestrorechazosol;
	}


	public boolean isClinicaestudio() {
		return clinicaestudio;
	}


	public void setClinicaestudio(boolean clinicaestudio) {
		this.clinicaestudio = clinicaestudio;
	}


	public boolean isMaestroestudioclinica() {
		return maestroestudioclinica;
	}


	public void setMaestroestudioclinica(boolean maestroestudioclinica) {
		this.maestroestudioclinica = maestroestudioclinica;
	}


	public boolean isMaestroactividadevento() {
		return maestroactividadevento;
	}


	public void setMaestroactividadevento(boolean maestroactividadevento) {
		this.maestroactividadevento = maestroactividadevento;
	}


	public boolean isPatrocinador() {
		return patrocinador;
	}


	public void setPatrocinador(boolean patrocinador) {
		this.patrocinador = patrocinador;
	}


	public boolean isTipocolaboracion() {
		return tipocolaboracion;
	}


	public void setTipocolaboracion(boolean tipocolaboracion) {
		this.tipocolaboracion = tipocolaboracion;
	}


	public boolean isTipoactividad() {
		return tipoactividad;
	}


	public void setTipoactividad(boolean tipoactividad) {
		this.tipoactividad = tipoactividad;
	}


	public boolean isMaestrocomisiones() {
		return maestrocomisiones;
	}


	public void setMaestrocomisiones(boolean maestrocomisiones) {
		this.maestrocomisiones = maestrocomisiones;
	}


	public boolean isMaestrovoluntario() {
		return maestrovoluntario;
	}


	public void setMaestrovoluntario(boolean maestrovoluntario) {
		this.maestrovoluntario = maestrovoluntario;
	}


	public boolean isMaestroasignarvolcomisiones() {
		return maestroasignarvolcomisiones;
	}


	public void setMaestroasignarvolcomisiones(boolean maestroasignarvolcomisiones) {
		this.maestroasignarvolcomisiones = maestroasignarvolcomisiones;
	}


	public boolean isMaestrotipolugar() {
		return maestrotipolugar;
	}


	public void setMaestrotipolugar(boolean maestrotipolugar) {
		this.maestrotipolugar = maestrotipolugar;
	}


	public boolean isMaestrolugar() {
		return maestrolugar;
	}


	public void setMaestrolugar(boolean maestrolugar) {
		this.maestrolugar = maestrolugar;
	}


	public boolean isGestionpaciente() {
		return gestionpaciente;
	}


	public void setGestionpaciente(boolean gestionpaciente) {
		this.gestionpaciente = gestionpaciente;
	}


	public boolean isCita() {
		return cita;
	}


	public void setCita(boolean cita) {
		this.cita = cita;
	}


	public boolean isVisita() {
		return visita;
	}


	public void setVisita(boolean visita) {
		this.visita = visita;
	}


	public boolean isSolayuda() {
		return solayuda;
	}


	public void setSolayuda(boolean solayuda) {
		this.solayuda = solayuda;
	}


	public boolean isEvalsolicitud() {
		return evalsolicitud;
	}


	public void setEvalsolicitud(boolean evalsolicitud) {
		this.evalsolicitud = evalsolicitud;
	}


	public boolean isRegistrarcita() {
		return registrarcita;
	}


	public void setRegistrarcita(boolean registrarcita) {
		this.registrarcita = registrarcita;
	}


	public boolean isResulcita() {
		return resulcita;
	}


	public void setResulcita(boolean resulcita) {
		this.resulcita = resulcita;
	}


	public boolean isEducacionprevencion() {
		return educacionprevencion;
	}


	public void setEducacionprevencion(boolean educacionprevencion) {
		this.educacionprevencion = educacionprevencion;
	}


	public boolean isGestionactividades() {
		return gestionactividades;
	}


	public void setGestionactividades(boolean gestionactividades) {
		this.gestionactividades = gestionactividades;
	}


	public boolean isSolactividad() {
		return solactividad;
	}


	public void setSolactividad(boolean solactividad) {
		this.solactividad = solactividad;
	}


	public boolean isRegistraractividad() {
		return registraractividad;
	}


	public void setRegistraractividad(boolean registraractividad) {
		this.registraractividad = registraractividad;
	}


	public boolean isAsignarvoluntarioactividad() {
		return asignarvoluntarioactividad;
	}


	public void setAsignarvoluntarioactividad(boolean asignarvoluntarioactividad) {
		this.asignarvoluntarioactividad = asignarvoluntarioactividad;
	}


	public boolean isResulactividad() {
		return resulactividad;
	}


	public void setResulactividad(boolean resulactividad) {
		this.resulactividad = resulactividad;
	}


	public boolean isGestionevento() {
		return gestionevento;
	}


	public void setGestionevento(boolean gestionevento) {
		this.gestionevento = gestionevento;
	}


	public boolean isRegistrarevento() {
		return registrarevento;
	}


	public void setRegistrarevento(boolean registrarevento) {
		this.registrarevento = registrarevento;
	}


	public boolean isAsignarvoluntarioevento() {
		return asignarvoluntarioevento;
	}


	public void setAsignarvoluntarioevento(boolean asignarvoluntarioevento) {
		this.asignarvoluntarioevento = asignarvoluntarioevento;
	}


	public boolean isRegistrarcolaboevento() {
		return registrarcolaboevento;
	}


	public void setRegistrarcolaboevento(boolean registrarcolaboevento) {
		this.registrarcolaboevento = registrarcolaboevento;
	}


	public boolean isResulevento() {
		return resulevento;
	}


	public void setResulevento(boolean resulevento) {
		this.resulevento = resulevento;
	}


	public boolean isConsultas() {
		return consultas;
	}


	public void setConsultas(boolean consultas) {
		this.consultas = consultas;
	}


	public boolean isAdminsistema() {
		return adminsistema;
	}


	public void setAdminsistema(boolean adminsistema) {
		this.adminsistema = adminsistema;
	}
	


	


	
	

}