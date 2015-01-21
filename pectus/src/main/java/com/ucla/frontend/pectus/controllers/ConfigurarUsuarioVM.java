package com.ucla.frontend.pectus.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;

import com.ucla.frontend.pectus.models.Grupo;
import com.ucla.frontend.pectus.models.Modulo;
import com.ucla.frontend.pectus.models.Tarea;
import com.ucla.frontend.pectus.models.Usuario;
import com.ucla.frontend.pectus.services.ServicioModulo;
import com.ucla.frontend.pectus.services.ServicioTarea;
import com.ucla.frontend.pectus.services.ServicioTipoEstudio;
import com.ucla.frontend.pectus.services.ServicioUsuario;
import com.ucla.frontend.pectus.services.ServicioGrupo;

public class ConfigurarUsuarioVM {
	private String nombregrupo;
	private String descripciongrupo;
	
	private List<Usuario> currentUsuario;
	private List<Modulo> currentModulo;
	private List<Grupo> currentGrupo;
	private List<Tarea> currentTarea;
	private Usuario usuarioSelected;
	private Grupo grupoSelected;
	private boolean grupoComun;
	
	public ConfigurarUsuarioVM() {
		// TODO Auto-generated constructor stub
	}
	
	@Init
	public void init(){
		this.currentUsuario = ServicioUsuario.buscarUsuarios();
		this.currentModulo = ServicioModulo.buscarModulos();
		this.currentGrupo = ServicioGrupo.buscarGrupos();
		this.currentTarea = ServicioTarea.buscarTareas();
	
	}
	@Command
	@NotifyChange("modelgrupo")
	public void guardargrupo()
	{
			String response = null;
			Grupo grupo = new Grupo();
			grupo.setNombre(nombregrupo);
			grupo.setDescripcion(descripciongrupo);
			response = ServicioGrupo.agregarGrupo(grupo);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentGrupo.add(grupo);
				Clients.showNotification("Grupo Guardado", null, true);
				nombregrupo=null;
				descripciongrupo= null;
				
	
			}else
			{
				Clients.showNotification("Error al guardar", true);
			}

		}
	
	@Command
	@NotifyChange("modelmodulo")
	public void compararmodulos()
	{
		List<Modulo> aux = new ArrayList<Modulo>();
			aux.addAll(currentModulo);
			
			for (int i=0; i<7; i++)
			{
				aux.remove(i).getClass();
			}

		
	
		
		aux.retainAll(currentModulo);
		
	
		
		for (int i=0; i< currentModulo.size() ; i++)
		{
			for (int j=0; j<aux.size(); j++)
			{
				if (aux.get(j).getId() == currentModulo.get(i).getId())
				{
					currentModulo.get(i).setStatus(true);
					
				}
			}
		}
		
	}
	
	public List<Usuario> getmodelusuario() {
	      return currentUsuario;
	    }
	public List<Modulo> getmodelmodulo() {
		return currentModulo;
	}
	public List<Grupo> getmodelgrupo(){
		return currentGrupo;
	}
	public List<Tarea> getmodeltarea(){
		return currentTarea;
	}
	public Usuario getUsuarioSelected() {
		return usuarioSelected;
	}

	public void setUsuarioSelected(Usuario usuarioSelected) {
		this.usuarioSelected = usuarioSelected;
	}

	public Grupo getGrupoSelected() {
		return grupoSelected;
	}

	public void setGrupoSelected(Grupo grupoSelected) {
		this.grupoSelected = grupoSelected;
	}

	public boolean isGrupoComun() {
		return grupoComun;
	}

	public void setGrupoComun(boolean grupoComun) {
		this.grupoComun = grupoComun;
	}

	public String getNombregrupo() {
		return nombregrupo;
	}

	public void setNombregrupo(String nombregrupo) {
		this.nombregrupo = nombregrupo;
	}

	public String getDescripciongrupo() {
		return descripciongrupo;
	}

	public void setDescripciongrupo(String descripciongrupo) {
		this.descripciongrupo = descripciongrupo;
	}
	    
	


}
