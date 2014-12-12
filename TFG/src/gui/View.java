package gui;



import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;

public class View extends JFrame {

    PanelView panel;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public View() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	super("Análisis de rendiment");
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	setResizable(false);
	setPreferredSize(new Dimension(450,400));
	panel = new PanelView();
	
    }

    public void initComponents() {
	getContentPane().add(panel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Run() {
	pack();
	this.setVisible(true);
    }

}
