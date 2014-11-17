package com.ucla.frontend.pectus.exceptions;

public class ExceptionHttpPersonalizada extends Exception {

    private static final long serialVersionUID = 1023L;
    private int error;

    public ExceptionHttpPersonalizada() {
    }

    public ExceptionHttpPersonalizada(String message, int error) {
	super(message);
	this.error = error;
    }

    public int getError() {
	return error;
    }
}
