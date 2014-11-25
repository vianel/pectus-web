package vistas;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.CategoryModel;
import org.zkoss.zul.Chart;
import org.zkoss.zul.SimpleCategoryModel;
import org.zkoss.zul.Window;


public class resultadocharla  extends SelectorComposer<Window>{
	
    @Wire
    Chart resultadoscharla;
 
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
 
        // Create a predefined implementation category model
        CategoryModel model = new SimpleCategoryModel();
 
        // Set value to the model
        model.setValue("Asistencia", "1 Semana", new Integer(11));
        model.setValue("Asistencia", "2 Semana", new Integer(20));
        model.setValue("Asistencia", "3 Semana", new Integer(16));
        model.setValue("Asistencia", "4 Semana", new Integer(-2));
        model.setValue("Ventas", "1 Semana", new Integer(6));
        model.setValue("Ventas", "2 Semana", new Integer(12));
        model.setValue("Ventas", "3 Semana", new Integer(10));
        model.setValue("Ventas", "4 Semana", new Integer(2));
 
        // Set model to the chart
        resultadoscharla.setModel(model);
    }

}
