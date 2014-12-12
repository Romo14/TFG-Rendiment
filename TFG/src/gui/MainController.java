package gui;

import java.awt.EventQueue;

import javax.swing.UnsupportedLookAndFeelException;

public class MainController {

    static View view;
    static ViewAnalisisController analisisController;
    static ViewOpcionsController opcionsController;

    /**
     * Launch the application.
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
     * Create the application.
     * @throws UnsupportedLookAndFeelException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     */
    public MainController() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	view = new View();
	opcionsController = new ViewOpcionsController();
	analisisController = new ViewAnalisisController();
	
    }

}
