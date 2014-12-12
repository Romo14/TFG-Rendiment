package gui;

import domini.AnalisisController;

public class ViewAnalisisController {
    
    private static AnalisisController analisisController;
    
    public ViewAnalisisController() {
	analisisController = new AnalisisController(MainController.opcionsController.getOpcions());
    }

    public static void analisisPersonalitzat() {
	analisisController.analisisPersonalitzat();
	
    }
    
    public static void analisisComplet(){
	analisisController.analisisComplet();
    }

}
