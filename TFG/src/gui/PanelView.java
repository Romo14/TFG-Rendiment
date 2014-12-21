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
    private JButton analisiCompletButton;
    private JLabel analisiCompletLabel;
    private ActionListener analisiCompletListener = new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
	    int confirmar = JOptionPane
		    .showConfirmDialog(
			    null,
			    "<html> Vol realitzar un anàlisi de tots<br>  els components del sistema <br> durant "
				    + t + "?", "Anàlisi complet",
			    JOptionPane.YES_NO_OPTION);
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
			.createParallelGroup(Alignment.TRAILING)
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(25)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.TRAILING)
							.addComponent(
								analisiCompletLabel,
								GroupLayout.DEFAULT_SIZE,
								415,
								Short.MAX_VALUE)
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addGroup(
										groupLayout
											.createParallelGroup(
												Alignment.LEADING)
											.addComponent(
												analisiPersonalitzatLabel,
												GroupLayout.DEFAULT_SIZE,
												415,
												Short.MAX_VALUE)
											.addGroup(
												groupLayout
													.createSequentialGroup()
													.addComponent(
														duracioLabel)
													.addPreferredGap(
														ComponentPlacement.UNRELATED)
													.addComponent(
														duracioLabelEdita,
														GroupLayout.PREFERRED_SIZE,
														260,
														GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(
										ComponentPlacement.RELATED)))
					.addGap(166))
			.addGroup(
				groupLayout.createSequentialGroup().addGap(285)
					.addComponent(lblOriolGassetRomo)
					.addContainerGap(184, Short.MAX_VALUE))
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(174)
					.addComponent(analisiCompletButton,
						GroupLayout.PREFERRED_SIZE,
						193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
			.addGroup(
				groupLayout.createSequentialGroup().addGap(72)
					.addComponent(duracioButton)
					.addContainerGap(455, Short.MAX_VALUE))
			.addGroup(
				Alignment.LEADING,
				groupLayout
					.createSequentialGroup()
					.addGap(173)
					.addComponent(
						analisiPersonalitzatButton,
						GroupLayout.PREFERRED_SIZE,
						193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE)));
	groupLayout
		.setVerticalGroup(groupLayout
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(24)
					.addComponent(analisiCompletLabel,
						GroupLayout.DEFAULT_SIZE, 23,
						Short.MAX_VALUE)
					.addPreferredGap(
						ComponentPlacement.UNRELATED)
					.addComponent(analisiCompletButton,
						GroupLayout.PREFERRED_SIZE, 35,
						GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						ComponentPlacement.UNRELATED)
					.addComponent(
						analisiPersonalitzatLabel,
						GroupLayout.DEFAULT_SIZE, 23,
						Short.MAX_VALUE)
					.addPreferredGap(
						ComponentPlacement.UNRELATED)
					.addComponent(
						analisiPersonalitzatButton,
						GroupLayout.PREFERRED_SIZE, 38,
						GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.BASELINE)
							.addComponent(
								duracioLabel)
							.addComponent(
								duracioLabelEdita))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(duracioButton,
						GroupLayout.PREFERRED_SIZE, 39,
						GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblOriolGassetRomo)
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
