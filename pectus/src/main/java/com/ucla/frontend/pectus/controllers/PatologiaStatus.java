package com.ucla.frontend.pectus.controllers;


import com.ucla.frontend.pectus.models.Patologia;

public class PatologiaStatus {
	private Patologia p;
    private boolean editingStatus;
     
    public PatologiaStatus(Patologia p, boolean editingStatus) {
        this.p = p;
        this.editingStatus = editingStatus;
    }
     
    public Patologia getPatologia() {
        return p;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
