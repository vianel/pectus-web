package com.ucla.frontend.pectus.controllers;

import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Voluntario;
import com.ucla.frontend.pectus.services.ServicioVoluntario;

public class VoluntarioView {

	private VoluntarioFilter voluntarioFilter;
	List<Voluntario> listaVoluntarios = ServicioVoluntario.buscarVoluntario();
	
//	EDITAR
	private ListModelList<Voluntario> voluntarios = new ListModelList<Voluntario>();
	private Voluntario voluntarioSeleccionado = new Voluntario();
	private Window v;
	private boolean nuevo;
	
	@NotifyChange("voluntarios")
	public void getdata(){
		Voluntario v = new Voluntario();
		v.setNombre("pedro");
		v.setApellido("perez");
		voluntarios.add(v);
		
		 v = new Voluntario();
		v.setNombre("d");
		v.setApellido("c");
		voluntarios.add(v);
		
		 v = new Voluntario();
		v.setNombre("pesadsdf");
		v.setApellido("sdfsdf");
		voluntarios.add(v);
	}
	
	public VoluntarioView(){
		getdata();
	}
	
	
	
	  public Voluntario getVoluntarioSeleccionado() {
		return voluntarioSeleccionado;
	}

	public void setVoluntarioSeleccionado(Voluntario voluntarioSeleccionado) {
		this.voluntarioSeleccionado = voluntarioSeleccionado;
	}

	public ListModelList<Voluntario> getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(ListModelList<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}

	public List<Voluntario> getmodelvoluntario() {
	        return new ListModelList<Voluntario>(listaVoluntarios);
	  
	    }
	
	 public VoluntarioFilter getVoluntarioFilter() {
		return voluntarioFilter;
	}



	public void setVoluntarioFilter(VoluntarioFilter voluntarioFilter) {
		this.voluntarioFilter = voluntarioFilter;
	}



	public List<Voluntario> getListaVoluntarios() {
		return listaVoluntarios;
	}



	public void setListaVoluntarios(List<Voluntario> listaVoluntarios) {
		this.listaVoluntarios = listaVoluntarios;
	}



	@Command
	    @NotifyChange({"modelvoluntario", "footer"})
	    public void changeFilter() {
	        listaVoluntarios = VoluntarioFilter.getFilterVoluntarios(voluntarioFilter);
	    }
	
	@Command
	public void abrirDialogoRegistrarVoluntario(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarVoluntario.zul", null, null);
		window.doModal();
	}
	
}
