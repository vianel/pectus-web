package com.ucla.frontend.pectus.controllers;

import com.ucla.frontend.pectus.models.Lugar;

public class LugarStatus {
	private Lugar lu;
    private boolean editingStatus;
     
    public LugarStatus(Lugar lu, boolean editingStatus) {
        this.lu = lu;
        this.editingStatus = editingStatus;
    }
     
    public Lugar getLugar() {
        return lu;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }
}
