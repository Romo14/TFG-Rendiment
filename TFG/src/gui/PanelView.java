/*
 * 
 */
package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class PanelView.
 */
public class PanelView extends JPanel {

    /** The dies. */
    private static int dies;

    /** The duracio label edita. */
    private static JLabel duracioLabelEdita;

    /** The duracio pop up. */
    private static DuracioPopUp duracioPopUp;

    /** The hores. */
    private static int hores;

    /** The minuts. */
    private static int minuts;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2924456615397502338L;

    /** The t. */
    private static String t = "1 hora/es";

    /** The analisi complet button. */
    private JButton analisiCompletButton;

    /** The analisi complet label. */
    private JLabel analisiCompletLabel;

    /** The analisi complet listener. */
    private ActionListener analisiCompletListener = new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
	    desactivaBotons();
	    int confirmar = JOptionPane
		    .showOptionDialog(
			    MainController.view,
			    "<html> Vol realitzar un anàlisi de tots<br>  els components del sistema <br> durant "
				    + t + "?", "Anàlisi complet",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE, null, opcions,
			    opcions[0]);
	    if (confirmar == 0) {
		ViewOpcionsController.setAllTrue();
		new AnalisisView();
		tanca();
	    } else {
		activaBotons();
	    }
	}

    };

    /** The analisi personalitzat button. */
    private JButton analisiPersonalitzatButton;

    /** The analisi personalitzat label. */
    private JLabel analisiPersonalitzatLabel;

    /** The analisi personalitzat listener. */
    private ActionListener analisiPersonalitzatListener = new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
	    new AnalisisPersonalitzatPopUp();
	    desactivaBotons();
	}
    };

    /**
     * 
     */
    private JButton btnCarregarAnlisi;

    /** The duracio button. */
    private JButton duracioButton;

    /** The duracio label. */
    private JLabel duracioLabel;

    /** The editar duracio. */
    private ActionListener editarDuracio = new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
	    duracioPopUp = new DuracioPopUp();
	    desactivaBotons();
	}
    };

    /** The font. */
    private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

    /** The opcions. */
    private Object[] opcions = { "Si", "No" };

    /**
     * Instantiates a new panel view.
     */
    public PanelView() {
	setBackground(Color.WHITE);
	analisiCompletButton = new JButton("An\u00E0lisi Complet");
	analisiCompletButton.setBackground(Color.DARK_GRAY);
	analisiCompletButton.addActionListener(analisiCompletListener);
	analisiCompletButton.setFont(font);
	analisiCompletLabel = new JLabel(
		"Realitza un an\u00E0lisi complet del sistema");
	analisiCompletLabel.setAlignmentX(1.0f);
	analisiCompletLabel.setFont(font);
	analisiPersonalitzatLabel = new JLabel(
		"Realitza un an\u00E0lisi personalitzat del sistema");
	analisiPersonalitzatLabel.setAlignmentX(1.0f);
	analisiPersonalitzatLabel.setFont(font);
	analisiPersonalitzatButton = new JButton("An\u00E0lisi Personalitzat");
	analisiPersonalitzatButton.setBackground(Color.DARK_GRAY);
	analisiPersonalitzatButton.setMinimumSize(new Dimension(140, 23));
	analisiPersonalitzatButton.setMaximumSize(new Dimension(105, 23));
	analisiPersonalitzatButton
		.addActionListener(analisiPersonalitzatListener);
	analisiPersonalitzatButton.setFont(font);

	duracioLabel = new JLabel("Duraci\u00F3 de l'an\u00E0lisi:");
	duracioLabel.setFont(font);
	duracioButton = new JButton("Canvia");
	duracioButton.setBackground(Color.DARK_GRAY);
	duracioButton.addActionListener(editarDuracio);
	duracioButton.setFont(font);

	duracioLabelEdita = new JLabel("1 hora/es");
	duracioLabelEdita.setFont(font);
	JLabel lblOriolGassetRomo = new JLabel("Oriol Gasset Romo - UPC FIB");

	btnCarregarAnlisi = new JButton("Carregar an\u00E0lisi");
	btnCarregarAnlisi.setBackground(Color.DARK_GRAY);
	btnCarregarAnlisi.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("Carregar");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Anàlisi de rendiment", "ren");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showOpenDialog(btnCarregarAnlisi) == JFileChooser.APPROVE_OPTION) {
		    try {
			MainController.analisisController.carregarAnalisi(chooser
				.getSelectedFile());
		    } catch (NumberFormatException e) {
			e.printStackTrace();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    }
	});

	GroupLayout groupLayout = new GroupLayout(this);
	groupLayout
		.setHorizontalGroup(groupLayout
			.createParallelGroup(Alignment.TRAILING)
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(25)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addComponent(
										analisiPersonalitzatLabel,
										GroupLayout.DEFAULT_SIZE,
										527,
										Short.MAX_VALUE)
									.addContainerGap())
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addGroup(
										groupLayout
											.createParallelGroup(
												Alignment.LEADING)
											.addGroup(
												groupLayout
													.createSequentialGroup()
													.addPreferredGap(
														ComponentPlacement.RELATED)
													.addComponent(
														duracioLabel)
													.addPreferredGap(
														ComponentPlacement.RELATED)
													.addComponent(
														duracioLabelEdita,
														GroupLayout.PREFERRED_SIZE,
														260,
														GroupLayout.PREFERRED_SIZE))
											.addGroup(
												groupLayout
													.createSequentialGroup()
													.addComponent(
														analisiCompletLabel,
														GroupLayout.DEFAULT_SIZE,
														404,
														Short.MAX_VALUE)
													.addPreferredGap(
														ComponentPlacement.RELATED)))
									.addGap(133))))
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(94)
					.addComponent(analisiCompletButton,
						GroupLayout.PREFERRED_SIZE,
						177, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(291, Short.MAX_VALUE))
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(95)
					.addComponent(
						analisiPersonalitzatButton,
						GroupLayout.PREFERRED_SIZE,
						181, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(286, Short.MAX_VALUE))
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.TRAILING)
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(
										lblOriolGassetRomo,
										GroupLayout.PREFERRED_SIZE,
										156,
										GroupLayout.PREFERRED_SIZE))
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addGap(102)
									.addComponent(
										duracioButton,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
					.addGap(49)
					.addComponent(btnCarregarAnlisi)
					.addGap(272)));
	groupLayout
		.setVerticalGroup(groupLayout
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addComponent(analisiCompletLabel,
						GroupLayout.DEFAULT_SIZE, 58,
						Short.MAX_VALUE)
					.addGap(1)
					.addComponent(analisiCompletButton,
						GroupLayout.PREFERRED_SIZE, 35,
						GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(
						analisiPersonalitzatLabel,
						GroupLayout.DEFAULT_SIZE, 58,
						Short.MAX_VALUE)
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(
						analisiPersonalitzatButton,
						GroupLayout.PREFERRED_SIZE, 38,
						GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.BASELINE)
							.addComponent(
								duracioLabel)
							.addComponent(
								duracioLabelEdita,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.TRAILING)
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addComponent(
										duracioButton)
									.addGap(18)
									.addComponent(
										lblOriolGassetRomo))
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addComponent(
										btnCarregarAnlisi)
									.addContainerGap()))));
	setLayout(groupLayout);
    }

    /**
     * Activa botons.
     */
    public void activaBotons() {
	analisiCompletButton.setEnabled(true);
	analisiPersonalitzatButton.setEnabled(true);
	duracioButton.setEnabled(true);
    }

    /**
     * Desactiva botons.
     */
    public void desactivaBotons() {
	analisiCompletButton.setEnabled(false);
	analisiPersonalitzatButton.setEnabled(false);
	duracioButton.setEnabled(false);
    }

    /**
     * Gets the temps label.
     * 
     * @return the temps label
     */
    public String getTempsLabel() {
	return t;
    }

    /**
     * Mostra.
     */
    private void mostra() {
	MainController.view.setVisible(true);
    }

    /**
     * Tanca.
     */
    private void tanca() {
	MainController.view.dispose();
    }

    /**
     * Update duracio label.
     */
    public void updateDuracioLabel() {
	mostra();
	minuts = duracioPopUp.getMinuts();
	hores = duracioPopUp.getHores();
	dies = duracioPopUp.getDies();
	t = "";
	if (dies != 0)
	    t += dies + " dia/es ";
	if (hores != 0)
	    t += hores + " hora/es ";
	if (minuts != 0)
	    t += minuts + " minut/s";
	duracioLabelEdita.setText(t);
    }
}
