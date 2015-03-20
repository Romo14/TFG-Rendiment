/*
 * 
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Dialog.ModalExclusionType;

// TODO: Auto-generated Javadoc
/**
 * The Class View.
 */
public class View extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The panel. */
	PanelView panel;

	/**
	 * Instantiates a new view.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UnsupportedLookAndFeelException the unsupported look and feel exception
	 */
	public View() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		super("Análisi de rendiment");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setPreferredSize(new Dimension(400, 300));
		panel = new PanelView();
	}

	/**
	 * Inits the components.
	 */
	public void initComponents() {
		getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Run.
	 */
	public void Run() {
		pack();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
