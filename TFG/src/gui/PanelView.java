package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelView extends JPanel {
	private Object[] opcions = { "Si", "No" };
	private JButton analisiCompletButton;
	private JLabel analisiCompletLabel;
	private ActionListener analisiCompletListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int confirmar = JOptionPane
					.showOptionDialog(
							null,
							"<html> Vol realitzar un anàlisi de tots<br>  els components del sistema <br> durant "
									+ t + "?", "Anàlisi complet",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, opcions,
							opcions[0]);
			if (confirmar == 0) {
				ViewOpcionsController.setAllTrue();
				new AnalisisView();
				tanca();
			}
		}
	};
	private JButton analisiPersonalitzatButton;
	private JLabel analisiPersonalitzatLabel;
	private JButton duracioButton;
	private JLabel duracioLabel;
	private ActionListener editarDuracio = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			duracioPopUp = new DuracioPopUp();
		}
	};
	private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);
	private static ActionListener analisiPersonalitzatListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new AnalisisPersonalitzatPopUp();
		}
	};
	private static int dies;
	private static JLabel duracioLabelEdita;
	private static DuracioPopUp duracioPopUp;
	private static int hores;
	private static int minuts;
	private static final long serialVersionUID = 2924456615397502338L;
	private static String t = "1 hora/es";

	public PanelView() {
		analisiCompletButton = new JButton("An\u00E0lisi Complet");
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
		analisiPersonalitzatButton.setMinimumSize(new Dimension(140, 23));
		analisiPersonalitzatButton.setMaximumSize(new Dimension(105, 23));
		analisiPersonalitzatButton
				.addActionListener(analisiPersonalitzatListener);
		analisiPersonalitzatButton.setFont(font);

		duracioLabel = new JLabel("Duraci\u00F3 de l'an\u00E0lisi:");
		duracioLabel.setFont(font);
		duracioButton = new JButton("Canvia");
		duracioButton.addActionListener(editarDuracio);
		duracioButton.setFont(font);

		duracioLabelEdita = new JLabel("1 hora/es");
		duracioLabelEdita.setFont(font);
		JLabel lblOriolGassetRomo = new JLabel("Oriol Gasset Romo - UPC FIB");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
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
										.addGap(102)
										.addComponent(duracioButton,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(29)
										.addComponent(lblOriolGassetRomo,
												GroupLayout.PREFERRED_SIZE,
												156, GroupLayout.PREFERRED_SIZE)
										.addGap(196))
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
										.addContainerGap(286, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(analisiCompletLabel,
												GroupLayout.DEFAULT_SIZE, 25,
												Short.MAX_VALUE)
										.addGap(1)
										.addComponent(analisiCompletButton,
												GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(
												analisiPersonalitzatLabel,
												GroupLayout.DEFAULT_SIZE, 25,
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
																				duracioButton,
																				GroupLayout.PREFERRED_SIZE,
																				37,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(42)
																		.addComponent(
																				lblOriolGassetRomo)))
										.addContainerGap()));
		setLayout(groupLayout);
	}

	public static void updateDuracioLabel() {
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

	private void tanca() {
		MainController.view.dispose();
	}

	public String getTempsLabel() {
		return t;
	}
}
