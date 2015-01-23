package vistas;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;

public class SidebarComposer  {

	boolean respaldo;
	//Cuando se instancia el VM le pongo false
	public SidebarComposer() {
		super();
		// TODO Auto-generated constructor stub
		respaldo = false;
		System.out.println("Se instancio");
		
		
	}
	
	
	@Command
	public void loguearse()
	{
		Executions.sendRedirect("../home/index.zul");

	}

	public boolean getRespaldo() {
		return respaldo;
	}

	public void setRespaldo(boolean respaldo) {
		this.respaldo = respaldo;
	}
	


	


	
	

}