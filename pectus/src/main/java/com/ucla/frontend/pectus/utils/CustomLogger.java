package com.ucla.frontend.pectus.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase genera una instancia para imprimir en consola a traves del logger, de tal forma
 * que las salidas por consola puedan ser encendidas o apagadas de acuerdo a las necesidades del 
 * desarrollador desde el archivo de propiedades config.properties ubicado en src/main/resources
 * 
 * @author Leonardo Figueroa
 *
 */
public class CustomLogger {

	private static Logger generalLogger;

    public static Logger getGeneralLogger(String nombre) {
        generalLogger = Logger.getLogger(nombre);
        if (PropertiesLocator.getProperty("logger").compareTo("off") == 0) {
            generalLogger.setLevel(Level.OFF);
        }else{
            generalLogger.setLevel(Level.ALL);
        }
        return generalLogger;
    }

    public static void setGeneralLogger(Logger generalLogger) {
        CustomLogger.generalLogger = generalLogger;
    }
}
