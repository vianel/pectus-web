package com.ucla.frontend.pectus.controllers;


import com.ucla.frontend.pectus.models.TipoEstudio;

public class TipoEstudioStatus {
	private TipoEstudio te;
    private boolean editingStatus;
     
    public TipoEstudioStatus(TipoEstudio te, boolean editingStatus) {
        this.te = te;
        this.editingStatus = editingStatus;
    }
     
    public TipoEstudio getTipoEstudio() {
        return te;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
