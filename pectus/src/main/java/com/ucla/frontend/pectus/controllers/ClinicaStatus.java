package com.ucla.frontend.pectus.controllers;

import com.ucla.frontend.pectus.models.Clinica;

	
	public class ClinicaStatus {
		private Clinica lc;
	    private boolean editingStatus;
	     
	    public ClinicaStatus(Clinica lc, boolean editingStatus) {
	        this.lc = lc;
	        this.editingStatus = editingStatus;
	    }
	     
	    public Clinica getClinica() {
	        return lc;
	    }
	     
	    public boolean getEditingStatus() {
	        return editingStatus;
	    }
	     
	    public void setEditingStatus(boolean editingStatus) {
	        this.editingStatus = editingStatus;
	    }
	

}
	
	

