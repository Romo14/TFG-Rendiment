/*
 * 
 */
package gui;

import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
public class MainController {

	/** The view. */
	static View view;
	
	/** The analisis controller. */
	static ViewAnalisisController analisisController;
	
	/** The opcions controller. */
	static ViewOpcionsController opcionsController;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
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
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 */
	public MainController() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		try {
			UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
			UIManager.put("Synthetica.tabbedPane.keepOpacity", true);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		view = new View();
		opcionsController = new ViewOpcionsController();
		analisisController = new ViewAnalisisController();

	}

}
