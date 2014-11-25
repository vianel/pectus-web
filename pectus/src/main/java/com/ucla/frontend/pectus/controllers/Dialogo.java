package com.ucla.frontend.pectus.controllers;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

public class Dialogo extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	
//	@Listen("onClick = #btnBuscarPaciente")
	
	@Command
	public void abrirDialogoPaciente(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscarPaciente.zul", null, null);
		window.doModal();
		
	}
	
	@Listen("onClick = #btnBuscarClinica")
	public void abrirDialogoClinica(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscarClinica.zul", null, null);
		window.doModal();
	}
	
	@Listen("onClick = #btnExamenes")
	public void abrirDialogoExamen(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscarExamen.zul",null,null);
		window.doModal();
	}
	
	@Listen("onClick = #btnBuscarDiagnostico")
	public void abrirDialogoDiagnostico(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgDiagnostico.zul",null,null);
		window.doModal();
	}
	
	@Listen("onClick = #btnVoluntarios")
	public void abrirDialogoVoluntario(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgBuscarVoluntario.zul",null,null);
		window.doModal();
	}
	
	@Listen("onClick= #btnEditarPaciente")
	public void abrirDialogoRegistrarPaciente(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarPaciente.zul", null, null);
		window.doModal();
	}
	@Listen("onClick= #btnRegistrarActividad")
	public void abrirDialogoRegistrarActividad(Event e){
		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarActividad.zul", null, null);
		window.doModal();
	}
	
}
