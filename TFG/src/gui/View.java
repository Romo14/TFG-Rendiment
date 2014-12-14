package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.Image;

public class View extends JFrame {

    PanelView panel;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public View() throws ClassNotFoundException, InstantiationException,
	    IllegalAccessException, UnsupportedLookAndFeelException {
	super("Análisis de rendiment");
	Image img = new ImageIcon(this.getClass().getResource(
		"/images/app-icon.png")).getImage();
	setIconImage(img);
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	setResizable(false);
	setPreferredSize(new Dimension(450, 400));
	panel = new PanelView();

    }

    public void initComponents() {
	getContentPane().add(panel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Run() {
	pack();
	setLocationRelativeTo(null);
	this.setVisible(true);
    }

}
