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

/**
 * Classe encarregada de definir les caracterísitques i carregar la pantalla principal.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class View extends JFrame {

    /** El Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Objecte de la pantalla principal. */
    PanelView panel;

    /**
     * Creadora per defecte. Defineix l'aspecte i el tamany de la pantalla i
     * carrega el seu contingut.
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
     * Inicia els components.
     */
    public void initComponents() {
	getContentPane().add(panel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Mostra la pantalla
     */
    public void Run() {
	pack();
	setLocationRelativeTo(null);
	this.setVisible(true);
    }

}
