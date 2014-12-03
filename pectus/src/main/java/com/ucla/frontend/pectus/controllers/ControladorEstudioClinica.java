package com.ucla.frontend.pectus.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.impl.Attribute;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ucla.frontend.pectus.models.Clinica;
import com.ucla.frontend.pectus.models.Estudio;
import com.ucla.frontend.pectus.models.EstudioClinica;
import com.ucla.frontend.pectus.services.ServicioEstudioClinica;


public class ControladorEstudioClinica  extends SelectorComposer<Component> {

	@Wire
	Label lblseleccionClinica;
	
	@Wire
	Label lblseleccionEstudio;
	
	@Wire
	Textbox montoEstudioClinica;
	
	
	ServicioEstudioClinica servicioEstudioClinica;
	Double monto;
	
	@Listen("onClick= #btnRegistrarEstudioClinica")
	public void abrirDialogoRegistrarClinica1(Event e){

		Window window = (Window)Executions.createComponents("/vistas/dialogos/dlgRegistrarEstudioClinica.zul", null, null);
		window.doModal();
	}

    @Listen("onClick = #btnbuscarestudioporclinica")
    public void buscarEstudioPorClinica(Event e) {
      //create a window programmatically and use it as a modal dialog.
    	EstudioClinica estudioClinica = new EstudioClinica();

    	if (lblseleccionClinica.getValue().toString().equals("ninguno"))
    	{
    		System.out.println("Debe seleccionar una Clinica");
    	}
    	else if(lblseleccionEstudio.getValue().toString().equals("ninguno"))
    	{
    		System.out.println("Debe seleccionar un Estudio");
    	}
    	else
    	{
    		estudioClinica= servicioEstudioClinica.BuscarEstudioClinica(lblseleccionClinica.getValue(), lblseleccionEstudio.getValue());
    		montoEstudioClinica.setDisabled(false);
    		if(estudioClinica.getId()!=null)
    			montoEstudioClinica.setValue(estudioClinica.getMonto().toString());
    		else
    			montoEstudioClinica.setValue("0");
    	
    	}
    }
    
	@Listen("onClick= #btnregistrarmontoestudioporclinica")
	public void registrarEstudioClinica(Event e){
		Clinica clinica = new Clinica();
		Estudio estudio = new Estudio();
		EstudioClinica estudioClinica = new EstudioClinica();
		estudioClinica= servicioEstudioClinica.BuscarEstudioClinica(lblseleccionClinica.getValue(), lblseleccionEstudio.getValue());
		if(estudioClinica.getId()==null)
		{
		clinica = servicioEstudioClinica.BuscarClinica(lblseleccionClinica.getValue());
		estudio= servicioEstudioClinica.BuscarEstudio(lblseleccionEstudio.getValue());
		
		estudioClinica.setClinica(clinica);
		estudioClinica.setEstudio(estudio);
		estudioClinica.setMonto((Double.parseDouble(montoEstudioClinica.getValue())));
		
		servicioEstudioClinica.agregarEstudioClinica(estudioClinica);
		System.out.println("agregado");
		}
		else
		{

			clinica = servicioEstudioClinica.BuscarClinica(lblseleccionClinica.getValue());
			estudio= servicioEstudioClinica.BuscarEstudio(lblseleccionEstudio.getValue());
			
			estudioClinica.setClinica(clinica);
			estudioClinica.setEstudio(estudio);
				
			estudioClinica.setMonto((Double.parseDouble(montoEstudioClinica.getValue())));
			servicioEstudioClinica.editarEstudioClinica(estudioClinica);
			System.out.println("editado");
			System.out.println(estudioClinica.getMonto().toString());
		}
		montoEstudioClinica.setDisabled(true);
	}


	public void Limpiar()
	{
		//radioclinica.setChecked(false);
	//	radioestudio.setChecked(false);
		montoEstudioClinica.setValue("0");
	}






	private ClinicaFilter clinicaFilter = new ClinicaFilter();
	//private ServicioEstudioClinica servicioEstudioClinica1;
	public ClinicaFilter getClinicaFilter(){
		return clinicaFilter;
	}

	List<Clinica> currentClinica1 = servicioEstudioClinica.BuscarClinicas();
	

    @Command
    @NotifyChange({"modelclinica", "footer"})
    public void changeFilter() {
        currentClinica1 = ClinicaFilter.getFilterClinica(clinicaFilter);
    }
    
    
    List<EstudioClinica> currentEstudioClinica = servicioEstudioClinica.buscarEstudioClinica();

    public ListModelList<EstudioClinica> getmodelestudioclinica() {
        return new ListModelList<EstudioClinica>(currentEstudioClinica);
    }
    
  //*********************Clinica**************************
  	private static final String footerMensajeClinica = "Estas son todas las Clinicas";
  	//private ClinicaFilter clinicaFilter = new ClinicaFilter();
  	//List<Paciente> currentPaciente = PacienteData.getAllPacientes();
  	// ############ AQUI HAGO LA CONEXION CON EL SERVICIO #################
  	List<Clinica> currentClinica = servicioEstudioClinica.BuscarClinicas();

      
      public ListModel<Clinica> getmodelclinica() {
          return new ListModelList<Clinica>(currentClinica1);
      }

      public String getFooterClinica() {
          return String.format(footerMensajeClinica, currentClinica1.size());
      }


      //*********************TIPO ESTUDIO**************************
    	private static final String footerMensajeEstudio = "Estos son todos los Estudios";
    	//private ClinicaFilter clinicaFilter = new ClinicaFilter();
    	//List<Paciente> currentPaciente = PacienteData.getAllPacientes();
    	// ############ AQUI HAGO LA CONEXION CON EL SERVICIO #################
    	List<Estudio> currentEstudio = servicioEstudioClinica.BuscarEstudios();

        
        public ListModel<Estudio> getmodelestudio() {
            return new ListModelList<Estudio>(currentEstudio);
        }

        public String getFooterEstudio() {
            return String.format(footerMensajeEstudio, currentEstudio.size());
        }
      
      





}

