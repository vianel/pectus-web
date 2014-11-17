Pectus
==============================

DEBEMOS ESTAR CONECTADOS A INTERNET PARA QUE AUTOMATICAMENTE SE BAJEN LAS LIBRERIAS QUE NO TENGAN

Para importar el proyecto debemos ir 

1) En eclipse o spring  import->maven-> Existing maven project

2)Seleccionamos el proyecto y finish en caso que requiera actualizacion de maven seleccionamos "si"

3)Despues de esperar un buen rato dependiendo de la conexion a internet se nos deberian desmarcar los errores en el proyecto y estariamos listos para correr



###Estructura
	
JAva Resources aqui estan los paquetes y las clases javas donde estaran los DAO, modelos, controladores. Ahorita solo estan los composer que ayudan a las barras de navegacion y a la barra lateral a funcionar

SRC/main/webapp (Aqui estan los archivos de zk para que funcione el proyecto)

-Assets se estan colocando los recursos como las imagenes y iconos 

-barras aqui estan los archivos del la barra de navegacion y la lateral...

-bootstrap las librerias de bootstrap NOO TOCAR

-content aqui hay unos archivos que venian en el ejemplo que utilice

-less para implementar css y bootstrap se utulizaron archivos .less y dsp que hacen el css mas dinamico (por aqui se cambia el aspecto del proyecto)

-portalfundamama aqui estan los archivos html y css de la vista curiosa

-Vistas aqui estan los .zul cada opcion del menu lateral debera tener una carpeta aqui como lo hice en la gestion de charlas detro de esa carpeta como deberan haber minimo 3 arcvhivos

	el index de ese menu donde se cargaran las librerias bootstrap y las barras de navegacion
	
	el "archivos de gestion" en mi caso puse gestioncharla.zul que 	es donde se agregaran todos los paneles que se mostraran en cada menu  y se podra el layout que se usara en ese menu
	
	 y por ultimo el archivo del panel aqui implementaran los label,form o sea el panel que se va a mostrar su codigo 			estara aqui en mi caso son 2 registrocharla y 	resultadoscharla, dicho de otra manera aqui estaran las pantallas.
 
 
 Para que los menus de la barra lateral hagan llamado a la pantalla basta con irse a sidebar.zul ubicar la opcion que quieran y colocarle un id, luego irse a sidebarcomposer.java crear una instancia de ese item de esta manera
 
@Wire
Navitem idquecolocaronenelarchivozul;

y crean un metodo asi

	@Listen("onClick = #btnmostrarcharla")
	public void mostrarpantalla()
	{
		Executions.sendRedirect("/vistas/gestioncharlas/index_gestioncharla.zul");
		btnmostrarcharla.setSelected(true);
		btnmostrarcharla.isSelected();
	}
	
tambien podrian usar un href en la etiqueta navitem es mas facil y rapido, asi <navitem label="click me" href="/another_page.zul"/> tambien funciona yo lo probe pero deje la otra manera para que vieran como comunicarse con los archivos JAVA


Para saber que propiedades tiene cada etiqueta pueden usar los siguientes links

http://books.zkoss.org/wiki/ZK_Component_Reference
http://www.zkoss.org/zkdemo/grid?zkn=1



