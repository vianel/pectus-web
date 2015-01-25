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
	private String nombremodulo;
	private String descripcionmodulo;
	private String nombretarea;
	private String descripciontarea;
	private String chequeogrupo;
	
	private List<Usuario> currentUsuario;
	private List<Modulo> currentModulo;
	private List<Grupo> currentGrupo;
	private List<Tarea> currentTarea;
	private List<Grupo> auxgrupo = new ArrayList<Grupo>();
	private List<Tarea> auxtarea = new ArrayList<Tarea>();
	private Usuario usuarioSelected;
	private Modulo moduloSelected;
	private Grupo grupoSelected;

	
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
	@NotifyChange("modelmodulo")
	public void guardarmodulo()
	{
		String response = null;
		Modulo modulo = new Modulo();
		modulo.setNombre(nombremodulo);
		modulo.setDescripcion(descripcionmodulo);
		response = ServicioModulo.agregarModulo(modulo);
		if (response.equalsIgnoreCase("true"))
		{
			
			currentModulo = ServicioModulo.buscarModulos();
			Clients.showNotification("Modulo Guardado", null, true);

			

		}else
		{
			Clients.showNotification("Error al guardar", true);
		}

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
				
				currentGrupo = ServicioGrupo.buscarGrupos();
				Clients.showNotification("Grupo Guardado", null, true);
				nombregrupo=" ";
				descripciongrupo=" ";
				
	
			}else
			{
				Clients.showNotification("Error al guardar", true);
			}

	}
	@Command
	@NotifyChange("modeltarea")
	public void guardartarea()
	{
			String response = null;
			Tarea tarea = new Tarea();
			tarea.setNombre(nombretarea);
			tarea.setDescripcion(descripciontarea);
			response = ServicioTarea.agregarTarea(tarea);
			if (response.equalsIgnoreCase("true"))
			{
				
				currentTarea = ServicioTarea.buscarTareas();
				Clients.showNotification("Tarea Guardada", null, true);
				
	
			}else
			{
				Clients.showNotification("Error al guardar", true);
			}

	}
	@Command
	@NotifyChange({"modelgrupo","modelusuario"})
	public void asociarusuarios()
	{
		List<Grupo> temporal = new ArrayList<Grupo>();
		String response = null;
		if (auxgrupo.isEmpty() == false)
		{
			for (int i=0; i<currentGrupo.size() ; i++)
			{
				
					if (currentGrupo.get(i).isStatus() == true)
					{
						for (int j=0; j< auxgrupo.size(); j++)
						{
							if (currentGrupo.get(i).getId() != auxgrupo.get(j).getId())
							{
								temporal.add(currentGrupo.get(i));
							}
						}
					}	
			}
		}else
		{
			for (int i=0; i<currentGrupo.size(); i++)
			{
				if (currentGrupo.get(i).isStatus() == true)
				{
					temporal.add(currentGrupo.get(i));
				}
			}
		}
		response = ServicioUsuario.asociarGrupos(usuarioSelected,temporal);
		
	}

	@Command
	@NotifyChange({"modelmodulo","modeltarea"})
	public void asociartareas()
	{
		List<Tarea> temporal = new ArrayList<Tarea>();
		String response = null;
		if (auxtarea.isEmpty() == false)
		{
			for (int i=0; i<currentTarea.size() ; i++)
			{
				
					if (currentTarea.get(i).isStatus() == true)
					{
						for (int j=0; j< auxtarea.size(); j++)
						{
							if (currentTarea.get(i).getId() != auxtarea.get(j).getId())
							{
								temporal.add(currentTarea.get(i));
							}
						}
					}	
			}
		}else
		{
			for (int i=0; i<currentTarea.size(); i++)
			{
				if (currentTarea.get(i).isStatus() == true)
				{
					temporal.add(currentTarea.get(i));
				}
			}
		}
		response = ServicioModulo.asociarTareas(moduloSelected,temporal);
		
	}
	
	@Command
	@NotifyChange("modeltarea")
	public void vermoduloxtareas()
	{
		if (moduloSelected != null)
		{
			auxtarea = ServicioModulo.buscarTareas(moduloSelected);
			for (Tarea temp : currentTarea)
			{
				temp.setStatus(false);
			}
			for (int i=0; i< currentTarea.size() ; i++)
			{
				for (int j=0; j<auxtarea.size(); j++)
				{
					if (auxtarea.get(j).getId() == currentTarea.get(i).getId())
					{
						currentTarea.get(i).setStatus(true);
						
					}
				}
			}
		}
	}
	@Command
	@NotifyChange("modelgrupo")
	public void vergruposxusuario()
	{
		if (usuarioSelected != null)
		{
			auxgrupo = ServicioUsuario.buscarGrupos(usuarioSelected);
			
	
			//auxgrupo.retainAll(currentGrupo);
			for (Grupo temp : currentGrupo)
			{
				temp.setStatus(false);
			}
			for (int i=0; i< currentGrupo.size() ; i++)
			{
				for (int j=0; j<auxgrupo.size(); j++)
				{
					if (auxgrupo.get(j).getId() == currentGrupo.get(i).getId())
					{
						currentGrupo.get(i).setStatus(true);
						
					}
				}
			}
		}
	}
	@Command
	@NotifyChange("modelmodulo")
	public void compararmodulos()
	{
		if (grupoSelected != null)
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



	public String getNombremodulo() {
		return nombremodulo;
	}

	public void setNombremodulo(String nombremodulo) {
		this.nombremodulo = nombremodulo;
	}

	public String getDescripcionmodulo() {
		return descripcionmodulo;
	}

	public void setDescripcionmodulo(String descripcionmodulo) {
		this.descripcionmodulo = descripcionmodulo;
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

	public Modulo getModuloSelected() {
		return moduloSelected;
	}

	public void setModuloSelected(Modulo moduloSelected) {
		this.moduloSelected = moduloSelected;
	}

	public String getNombretarea() {
		return nombretarea;
	}

	public void setNombretarea(String nombretarea) {
		this.nombretarea = nombretarea;
	}

	public String getDescripciontarea() {
		return descripciontarea;
	}

	public void setDescripciontarea(String descripciontarea) {
		this.descripciontarea = descripciontarea;
	}

	public String getChequeogrupo() {
		return chequeogrupo;
	}

	public void setChequeogrupo(String chequeogrupo) {
		this.chequeogrupo = chequeogrupo;
	}
	    
	


}
