/*
 * 
 */
package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.Color;

/**
 * Classe que cont� la pantalla on es permet canviar la duraci� de l'an�lisi.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class DuracioPopUp extends JPanel {

    /** El Constant serialVersionUID. */
    private static final long serialVersionUID = 2653787619021439964L;

    /**
     * Listener per modificar el temps de l'an�lisi tant a domini com a
     * presentaci�
     */
    private ActionListener accepta = new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (((int) horesSpinner.getValue()) == 0
		    && (int) minutsSpinner.getValue() == 0
		    && (int) diesSpinner.getValue() == 0) {
		JOptionPane
			.showMessageDialog(
				null,
				"No es pot fer un an�lisi de durada inferior a 1 minut",
				"Error", JOptionPane.ERROR_MESSAGE);
	    } else {
		hores = ((int) horesSpinner.getValue());
		dies = ((int) diesSpinner.getValue());
		minuts = ((int) minutsSpinner.getValue());
		MainController.view.panel.updateDuracioLabel();
		MainController.view.panel.activaBotons();
		ViewOpcionsController.setHores(hores);
		ViewOpcionsController.setMinuts(minuts);
		ViewOpcionsController.setDies(dies);
		duracio.dispose();
	    }
	}
    };

    /** Duraci� de l'analisi en dies. */
    public int dies;

    /** Text de la duraci� de l'an�lis en dies. */
    private JLabel diesLabel;

    /** Element de selecci� de dies. */
    private JSpinner diesSpinner;

    /** Contenidor dels element de la pantalla. */
    private JFrame duracio;

    /** Font utilitzada en els textos dels elements de la pantalla. */
    private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

    /** Duraci� de l'an�lisi en hores. */
    public int hores;

    /** Text de la duraci� de l'an�lis en hores. */
    private JLabel horesLabel;

    /** Element de selecci� d'hores. */
    private JSpinner horesSpinner;

    /** Duraci� de l'an�lisi en minuts. */
    public int minuts;

    /** Text de la duraci� de l'an�lis en minuts. */
    private JLabel minutsLabel;

    /** Element de selecci� dels minuts. */
    private JSpinner minutsSpinner;

    /**
     * Creadora per defecte de la classe. S'inicien tots els elements i es
     * col�loquen a la posici� adequada a la pantalla.
     */
    public DuracioPopUp() {
	duracio = new JFrame("Duraci� de l'an�lisi" + "");
	duracio.getContentPane().setBackground(Color.WHITE);
	Image img = new ImageIcon(this.getClass().getResource(
		"/images/app-icon.png")).getImage();
	duracio.setIconImage(img);
	Point aqui = new Point(
		MainController.view.getLocationOnScreen().x + 50,
		MainController.view.getLocationOnScreen().y + 50);
	Dimension min = new Dimension(300, 230);
	duracio.setLocation(aqui);
	duracio.setSize(min);
	duracio.setResizable(false);
	minutsSpinner = new JSpinner();
	minutsSpinner.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent arg0) {
		if ((int) minutsSpinner.getValue() >= 60) {
		    minutsSpinner.setValue(0);
		    horesSpinner.setValue((int) horesSpinner.getValue() + 1);
		}
	    }
	});
	minutsSpinner.setModel(new SpinnerNumberModel(ViewOpcionsController
		.getMinuts(), 0, 60, 1));
	minutsSpinner.setFont(font);
	horesLabel = new JLabel("Hores (0-23):");
	horesLabel.setFont(font);
	minutsLabel = new JLabel("Minuts (0-59):");
	minutsLabel.setFont(font);
	diesLabel = new JLabel("Dies (0-7):");
	diesLabel.setFont(font);
	horesSpinner = new JSpinner();
	horesSpinner.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent arg0) {
		if ((int) horesSpinner.getValue() >= 24) {
		    horesSpinner.setValue(0);
		    diesSpinner.setValue((int) diesSpinner.getValue() + 1);
		}
	    }
	});
	horesSpinner.setModel(new SpinnerNumberModel(ViewOpcionsController
		.getHores(), 0, 24, 1));
	horesSpinner.setFont(font);
	diesSpinner = new JSpinner();
	diesSpinner.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent arg0) {
		if ((int) diesSpinner.getValue() > 7) {
		    limitTemps();
		    diesSpinner.setValue(7);
		} else if (((int) diesSpinner.getValue() == 7 && ((int) horesSpinner
			.getValue() != 0 || (int) minutsSpinner.getValue() != 0))) {
		    limitTemps();
		    diesSpinner.setValue(6);
		}
	    }
	});
	diesSpinner.setModel(new SpinnerNumberModel(ViewOpcionsController
		.getDies(), 0, null, 1));
	diesSpinner.setFont(font);
	JButton acceptaButton = new JButton("Accepta");
	acceptaButton.setBackground(Color.DARK_GRAY);
	acceptaButton.setFont(font);
	acceptaButton.addActionListener(accepta);
	JButton btnCancellar = new JButton("Cancel\u00B7lar");
	btnCancellar.setBackground(Color.DARK_GRAY);
	btnCancellar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		duracio.dispose();
		MainController.view.panel.activaBotons();
	    }
	});
	btnCancellar.setFont(font);
	GroupLayout groupLayout = new GroupLayout(duracio.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(43)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(minutsLabel)
					.addComponent(diesLabel)
					.addComponent(horesLabel))
				.addGap(32)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
					.addComponent(minutsSpinner, Alignment.TRAILING)
					.addComponent(horesSpinner, Alignment.TRAILING)
					.addComponent(diesSpinner, Alignment.TRAILING))
				.addGap(66))
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(41)
				.addComponent(acceptaButton, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnCancellar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGap(57))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(28)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(minutsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(horesSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(horesLabel)))
					.addComponent(minutsLabel))
				.addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(diesSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(diesLabel))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnCancellar)
					.addComponent(acceptaButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(31))
	);
	duracio.getContentPane().setLayout(groupLayout);
	duracio.setVisible(true);

    }

    /**
     * Obt� els dies.
     * 
     * @return Els dies
     */
    public int getDies() {
	return dies;
    }

    /**
     * Obt� les hores.
     * 
     * @return Les hores
     */
    public int getHores() {
	return hores;
    }

    /**
     * Obt� els minuts.
     * 
     * @return Els minuts
     */
    public int getMinuts() {
	return minuts;
    }

    /**
     * Missatge d'error en superar el l�mit del temps perm�s.
     */
    public void limitTemps() {
	JOptionPane.showMessageDialog(null,
		"No es pot fer un an�lisi de durada superior a 7 dies",
		"Error", JOptionPane.ERROR_MESSAGE);
    }

}
