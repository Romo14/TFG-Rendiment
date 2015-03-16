package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Dialog.ModalExclusionType;

public class View extends JFrame {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	PanelView panel;

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
