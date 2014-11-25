package com.ucla.frontend.pectus.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Esta clase ayuda al desarrollador a buscar una propiedad predefinida en el archivo config.properties 
 * ubicado en src/main/resources
 * 
 * @author Leonardo Figueroa
 *
 */
public class PropertiesLocator {

	private static Properties propiedades;

	static {
		propiedades = new Properties();
		try {
			PropertiesLocator util = new PropertiesLocator();
			propiedades = util.getPropertiesFromClasspath("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return propiedades.getProperty(key);
	}

	public static Set<Object> getkeys() {
		return propiedades.keySet();
	}

	private Properties getPropertiesFromClasspath(String propFileName)
			throws IOException {
		Properties myProp = new Properties();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream == null) {
			throw new FileNotFoundException("Archivo de Propiedades '" + propFileName
					+ "' no encontrado en el classpath");
		}

		myProp.load(inputStream);
		return myProp;
	}
}
