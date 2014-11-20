package com.ucla.frontend.pectus.controllers;

import com.ucla.frontend.pectus.models.Paciente;

public class PacienteStatus {
	private Paciente lc;
    private boolean editingStatus;
     
    public PacienteStatus(Paciente lc, boolean editingStatus) {
        this.lc = lc;
        this.editingStatus = editingStatus;
    }
     
    public Paciente getPaciente() {
        return lc;
    }
     
    public boolean getEditingStatus() {
        return editingStatus;
    }
     
    public void setEditingStatus(boolean editingStatus) {
        this.editingStatus = editingStatus;
    }

}
