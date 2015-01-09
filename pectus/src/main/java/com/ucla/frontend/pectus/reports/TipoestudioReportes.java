package com.ucla.frontend.pectus.reports;

import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class TipoestudioReportes {
	
	private ConexionBD conBD;
	public TipoestudioReportes() {

	}
	
	public  void generarreporteprueba() throws SQLException
	{
		// TODO Auto-generated constructor stub
		try {
			conBD = new ConexionBD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String reporte = "/home/vianel/Documents/equipo3_02_2014-pectus-web/pectus/src/main/reportes/reportepacientes.jrxml";
		//param.put("varStatus","E");
		JasperReport jasperRe;
		try {
			jasperRe = JasperCompileManager.compileReport(reporte);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperRe, null,conBD.con);
			JasperViewer.viewReport(jasperPrint);
			conBD.con.close();
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
