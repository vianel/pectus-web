package com.ucla.frontend.pectus.controllers;

import com.ucla.frontend.pectus.models.Cita;


   public class CitaStatus {
	private Cita c;
    private boolean editingStatus;
     
    public CitaStatus(Cita c, boolean editingStatus) {
        this.c = c;
        this.editingStatus = editingStatus;
    }
     
    public Cita getCita() {
        return c;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
