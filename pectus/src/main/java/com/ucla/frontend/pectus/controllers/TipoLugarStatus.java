package com.ucla.frontend.pectus.controllers;


import com.ucla.frontend.pectus.models.TipoLugar;

public class TipoLugarStatus {
	private TipoLugar tl;
    private boolean editingStatus;
     
    public TipoLugarStatus(TipoLugar tl, boolean editingStatus) {
        this.tl = tl;
        this.editingStatus = editingStatus;
    }
     
    public TipoLugar getTipoLugar() {
        return tl;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
