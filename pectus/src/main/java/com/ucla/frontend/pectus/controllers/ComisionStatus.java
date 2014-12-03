package com.ucla.frontend.pectus.controllers;


import com.ucla.frontend.pectus.models.Comision;

public class ComisionStatus {
	private Comision comi;
    private boolean editingStatus;
     
    public ComisionStatus(Comision comi, boolean editingStatus) {
        this.comi = comi;
        this.editingStatus = editingStatus;
    }
     
    public Comision getComision() {
        return comi;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
