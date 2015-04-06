/*
 * 
 */
package gui;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

/**
 * Classe principal del programa, el qual es comença a executar desde aquí,
 * genera la vista principal i els controladors de vistes i també defineix el
 * look and feel del programa.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class MainController {

    /** Vista principal. */
    static View view;

    /** Controlador de vista de l'anàlisi. */
    static ViewAnalisisController analisisController;

    /** Controlador de vista de les opcions. */
    static ViewOpcionsController opcionsController;

    /**
     * Executa l'aplicació
     * 
     * @param args
     *            arguments
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    @SuppressWarnings("unused")
		    MainController main = new MainController();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		view.initComponents();
		view.Run();
	    }
	});
    }

    /**
     * Crea l'aplicació. Inicialitza els controladors i la pantalla principal.
     * 
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws UnsupportedLookAndFeelException
     *             the unsupported look and feel exception
     */
    public MainController() throws ClassNotFoundException,
	    InstantiationException, IllegalAccessException,
	    UnsupportedLookAndFeelException {
	try {
	    System.setProperty("org.hyperic.sigar.path", "./lib/");
	    UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
	    UIManager.put("Synthetica.tabbedPane.keepOpacity", true);

	} catch (ParseException e) {
	    e.printStackTrace();
	}
	view = new View();
	opcionsController = new ViewOpcionsController();

    }

}
