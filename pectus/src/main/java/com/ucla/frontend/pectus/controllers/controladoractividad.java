package com.ucla.frontend.pectus.controllers;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class controladoractividad extends SelectorComposer<Component> {
	
	@Wire
	Label lblseleccion;

    @Listen("onClick = #btnmostrarregistroresultadoactividad")
    public void showRegistroresultado(Event e) {
        //create a window programmatically and use it as a modal dialog.
    	if (lblseleccion.getValue().toString().equals("ninguno"))
    	{
    		System.out.println("Debe seleccionar una actividad");
    	}else{
		        Window window = (Window)Executions.createComponents(
		                "/vistas/gestionactividades/registroresultadoactividad.zul", null, null);
		        window.doModal();
		        window.setTitle(lblseleccion.getValue().toString());
		    	System.out.println("Resultados actividad");
    	}
    }
    
	@Listen("onClick= #btnmostrardialogoregistrotipoactividad")
	public void abrirDialogoRegistrarTipoActividad(Event e){

		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarTipoActividad.zul", null, null);
		window.doModal();
	}

}
