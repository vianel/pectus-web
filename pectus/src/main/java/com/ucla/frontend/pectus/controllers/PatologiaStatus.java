package com.ucla.frontend.pectus.controllers;


import com.ucla.frontend.pectus.models.Patologia;

public class PatologiaStatus {
	private Patologia patolo;
    private boolean editingStatus;
     
    public PatologiaStatus(Patologia patolo, boolean editingStatus) {
        this.patolo = patolo;
        this.editingStatus = editingStatus;
    }
     
    public Patologia getPatologia() {
        return patolo;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
