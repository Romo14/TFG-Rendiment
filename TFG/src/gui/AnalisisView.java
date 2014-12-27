package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Component;
import java.awt.Color;

public class AnalisisView extends JPanel {

	private Object[] opcions = { "Si", "No" };
	private static final long serialVersionUID = 7388902221110453680L;
	private JDialog analisis;
	private JProgressBar progressBar;
	private JLabel lblTempsRestantDescripcio;
	private JLabel lblTempsRestant;
	private int temps;
	private int comptador;
	private JButton btnAtura;
	private JButton btnVeureResultats;
	private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);
	private Timer t;
	private ActionListener atura = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (btnAtura.getText().equalsIgnoreCase("Atura")) {
				btnAtura.setText("Segueix");
				btnTornaAComenar.setEnabled(true);
				btnVeureResultats.setEnabled(true);
				t.stop();
			} else {
				btnAtura.setText("Atura");
				btnVeureResultats.setEnabled(false);
				btnTornaAComenar.setEnabled(false);
				t.start();
			}

		}
	};
	private JButton btnTornaAComenar;
	private JButton btnInici;
	private int restant;

	public AnalisisView() {
		analisis = new JDialog(MainController.view.getOwner(),
				"Anàlisis del sistema");
		analisis.getContentPane().setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		analisis.setIconImage(img);
		Point aqui = new Point(
				MainController.view.getLocationOnScreen().x + 50,
				MainController.view.getLocationOnScreen().y + 50);
		Dimension min = new Dimension(450, 230);
		analisis.setLocation(aqui);
		analisis.setSize(min);
		analisis.setResizable(false);
		lblTempsRestantDescripcio = new JLabel("Temps restant: ");
		lblTempsRestantDescripcio.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTempsRestantDescripcio.setFont(font);
		lblTempsRestant = new JLabel("");
		lblTempsRestant.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTempsRestant.setFont(font);
		btnAtura = new JButton("Atura");
		btnAtura.setBackground(Color.DARK_GRAY);
		btnAtura.addActionListener(atura);
		btnAtura.setFont(font);
		btnVeureResultats = new JButton("Veure resultats");
		btnVeureResultats.setBackground(Color.DARK_GRAY);
		btnVeureResultats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ResultatAnalisisView();
				analisis.dispose();
			}
		});
		btnVeureResultats.setEnabled(false);
		btnVeureResultats.setFont(font);
		temps = ViewOpcionsController.getTemps();
		comptador = 0;
		lblTempsRestant.setText(getTemps());
		progressBar = new JProgressBar(0, temps);
		progressBar.setStringPainted(true);
		progressBar.setFont(font);
		t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comptador == Long.valueOf(temps)) {
					t.stop();
					btnVeureResultats.setEnabled(true);
					btnTornaAComenar.setEnabled(true);
					btnAtura.setEnabled(false);
				} else {
					++comptador;
					progressBar.setValue(comptador);
					lblTempsRestant.setText(getTemps());
					ViewAnalisisController.updateSystemData();
				}

			}
		});
		btnTornaAComenar = new JButton("Torna a comen\u00E7ar");
		btnTornaAComenar.setBackground(Color.DARK_GRAY);
		btnTornaAComenar.setFont(font);
		btnTornaAComenar.setEnabled(false);
		btnTornaAComenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane
						.showOptionDialog(null,
								"Vol tornar a començar l'anàlisi?", "",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, opcions,
								opcions[0]);
				if (a == 0) {
					btnTornaAComenar.setEnabled(false);
					btnVeureResultats.setEnabled(false);
					btnAtura.setEnabled(true);
					btnAtura.setText("Atura");
					progressBar.setValue(0);
					comptador = 0;
					lblTempsRestant.setText(getTemps());
					ViewAnalisisController.updateSystemData();
					t.restart();
				}
			}
		});
		btnInici = new JButton();
		btnInici.setBorder(BorderFactory.createEmptyBorder());
		btnInici.setBorderPainted(false);
		btnInici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane
						.showOptionDialog(
								null,
								"Vol cancel·lar l'anàlisi i tornar al menú principal?",
								"", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, opcions,
								opcions[0]);
				if (a == 0) {
					MainController.main(null);
					analisis.dispose();
					t.stop();
				}
			}
		});
		btnInici.setIcon(new ImageIcon(this.getClass().getResource(
				"/images/home-icon.png")));
		GroupLayout groupLayout = new GroupLayout(analisis.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(23)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				progressBar,
																				GroupLayout.PREFERRED_SIZE,
																				378,
																				GroupLayout.PREFERRED_SIZE)
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
																												btnAtura,
																												GroupLayout.PREFERRED_SIZE,
																												87,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnVeureResultats,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnTornaAComenar)
																										.addPreferredGap(
																												ComponentPlacement.RELATED))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblTempsRestantDescripcio,
																												GroupLayout.DEFAULT_SIZE,
																												131,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												lblTempsRestant,
																												GroupLayout.PREFERRED_SIZE,
																												260,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(49))))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(392, Short.MAX_VALUE)
										.addComponent(btnInici,
												GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addGap(41)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(35)
										.addComponent(progressBar,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblTempsRestantDescripcio)
														.addComponent(
																lblTempsRestant,
																GroupLayout.PREFERRED_SIZE,
																27,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(61)
																		.addComponent(
																				btnInici,
																				GroupLayout.PREFERRED_SIZE,
																				37,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(18)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnAtura,
																								GroupLayout.PREFERRED_SIZE,
																								33,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnVeureResultats,
																								GroupLayout.PREFERRED_SIZE,
																								35,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnTornaAComenar,
																								GroupLayout.PREFERRED_SIZE,
																								34,
																								GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		analisis.getContentPane().setLayout(groupLayout);
		analisis.setVisible(true);
		t.start();
		analisis.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private String getTemps() {
		restant = temps - comptador;
		String t = "";
		if (restant / 86400 >= 1) {
			t += restant / 86400 + " dia/es ";
			restant %= 86400;
		}
		if (restant / 3600 >= 1) {
			t += restant / 3600 + " hora/es ";
			restant %= 3600;
		}
		if (restant / 60 >= 1) {
			t += restant / 60 + " minut/s ";
			restant %= 60;
		}
		t += restant + " segon/s";
		return t;
	}

}
