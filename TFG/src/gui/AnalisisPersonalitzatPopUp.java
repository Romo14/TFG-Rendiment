package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

public class AnalisisPersonalitzatPopUp extends JPanel {
	private Object[] opcions = { "Si", "No" };
	private ActionListener analitzaListener = new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			if (!cpuCheckBox.isSelected() && !discDurCheckBox.isSelected()
					&& !gpuCheckBox.isSelected()
					&& !internetCheckBox.isSelected()
					&& !ramCheckBox.isSelected()) {
				JOptionPane.showMessageDialog(null,
						"S'ha d'escollir almenys un element per analitzar",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				String components = "";
				if (cpuCheckBox.isSelected())
					components += "CPU";
				if (discDurCheckBox.isSelected()) {
					if (components != "")
						components += ", ";
					components += "disc dur";
				}
				if (gpuCheckBox.isSelected()) {
					if (components != "")
						components += ", ";
					components += "GPU";
				}
				if (ramCheckBox.isSelected()) {
					if (components != "")
						components += ", ";
					components += "memòria RAM";
				}
				if (internetCheckBox.isSelected()) {
					if (components != "")
						components += ", ";
					components += "targeta de xarxa";
				}
				int confirmar = JOptionPane
						.showOptionDialog(
								null,
								"<html> Vol realitzar un anàlisi de: "
										+ components
										+ " <br> durant "
										+ MainController.view.panel
												.getTempsLabel() + "?",
								"Anàlisi personalitzat",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, opcions,
								opcions[0]);
				if (confirmar == 0) {
					ViewOpcionsController.setGpu(gpuCheckBox.isSelected());
					ViewOpcionsController.setNet(internetCheckBox.isSelected());
					ViewOpcionsController.setHdd(discDurCheckBox.isSelected());
					ViewOpcionsController.setCpu(cpuCheckBox.isSelected());
					ViewOpcionsController.setRam(ramCheckBox.isSelected());
					options.dispose();
					new AnalisisView();
					MainController.view.dispose();
				}
			}

		}
	};
	private ActionListener cancelaListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			options.dispose();
		}
	};
	private static JCheckBox cpuCheckBox;
	private static JCheckBox discDurCheckBox;
	private static JCheckBox gpuCheckBox;
	private static JCheckBox internetCheckBox;
	private static JCheckBox ramCheckBox;
	private static final long serialVersionUID = -1893117450756795448L;
	private JDialog options;
	private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

	public AnalisisPersonalitzatPopUp() {
		options = new JDialog(MainController.view.getOwner(),
				"Anàlisi Personalitzat" + "");
		options.setFont(font);
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		options.setIconImage(img);
		Point aqui = new Point(
				MainController.view.getLocationOnScreen().x + 50,
				MainController.view.getLocationOnScreen().y + 50);
		Dimension min = new Dimension(340, 280);
		Box popUpLayout = Box.createVerticalBox();
		options.setLocation(aqui);
		options.setSize(min);
		options.setResizable(false);
		options.setVisible(true);
		JPanel WindowPanel = new JPanel();
		WindowPanel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(options.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(WindowPanel,
				GroupLayout.PREFERRED_SIZE, 294, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(WindowPanel,
				GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE));
		GroupLayout gl_WindowPanel = new GroupLayout(WindowPanel);
		gl_WindowPanel.setHorizontalGroup(gl_WindowPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_WindowPanel
						.createSequentialGroup()
						.addComponent(popUpLayout, GroupLayout.PREFERRED_SIZE,
								295, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(49, Short.MAX_VALUE)));
		gl_WindowPanel.setVerticalGroup(gl_WindowPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_WindowPanel
						.createSequentialGroup()
						.addComponent(popUpLayout, GroupLayout.PREFERRED_SIZE,
								222, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(50, Short.MAX_VALUE)));

		JPanel popUpPanel = new JPanel();
		popUpPanel.setBackground(Color.WHITE);
		popUpLayout.add(popUpPanel);
		JButton cancelaButton = new JButton("Cancel·lar");
		cancelaButton.setBackground(Color.DARK_GRAY);
		cancelaButton.setFont(font);
		JButton analitzaButton = new JButton("Analitzar");
		analitzaButton.setBackground(Color.DARK_GRAY);
		analitzaButton.setFont(font);
		internetCheckBox = new JCheckBox("Targeta de xarxa");

		internetCheckBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		internetCheckBox.setFont(font);
		discDurCheckBox = new JCheckBox("Disc Dur");

		discDurCheckBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		discDurCheckBox.setFont(font);
		cpuCheckBox = new JCheckBox("CPU");

		cpuCheckBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		cpuCheckBox.setFont(font);
		gpuCheckBox = new JCheckBox("GPU");

		gpuCheckBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		gpuCheckBox.setFont(font);
		ramCheckBox = new JCheckBox("Mermòria Ram");

		ramCheckBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		ramCheckBox.setFont(font);
		JLabel analisiPersonalitzatLabel = new JLabel(
				"Quins components vol analitzar?");
		analisiPersonalitzatLabel.setFont(font);
		analisiPersonalitzatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout gl_popUpPanel = new GroupLayout(popUpPanel);
		gl_popUpPanel
				.setHorizontalGroup(gl_popUpPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_popUpPanel
										.createSequentialGroup()
										.addGap(49)
										.addGroup(
												gl_popUpPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_popUpPanel
																		.createSequentialGroup()
																		.addComponent(
																				internetCheckBox)
																		.addContainerGap())
														.addGroup(
																gl_popUpPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_popUpPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_popUpPanel
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addGroup(
																												gl_popUpPanel
																														.createSequentialGroup()
																														.addComponent(
																																discDurCheckBox,
																																GroupLayout.DEFAULT_SIZE,
																																87,
																																Short.MAX_VALUE)
																														.addGap(18))
																										.addComponent(
																												cpuCheckBox))
																						.addGroup(
																								gl_popUpPanel
																										.createSequentialGroup()
																										.addComponent(
																												cancelaButton)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)))
																		.addGroup(
																				gl_popUpPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_popUpPanel
																										.createSequentialGroup()
																										.addComponent(
																												gpuCheckBox)
																										.addGap(168))
																						.addGroup(
																								gl_popUpPanel
																										.createSequentialGroup()
																										.addGap(30)
																										.addComponent(
																												analitzaButton))
																						.addComponent(
																								ramCheckBox))
																		.addGap(102))))
						.addGroup(
								gl_popUpPanel
										.createSequentialGroup()
										.addGap(31)
										.addComponent(
												analisiPersonalitzatLabel,
												GroupLayout.PREFERRED_SIZE,
												274, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(74, Short.MAX_VALUE)));
		gl_popUpPanel
				.setVerticalGroup(gl_popUpPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_popUpPanel
										.createSequentialGroup()
										.addGap(21)
										.addComponent(analisiPersonalitzatLabel)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_popUpPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cpuCheckBox)
														.addComponent(
																ramCheckBox))
										.addGap(6)
										.addGroup(
												gl_popUpPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																discDurCheckBox,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																gpuCheckBox))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(internetCheckBox)
										.addGap(18)
										.addGroup(
												gl_popUpPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cancelaButton)
														.addComponent(
																analitzaButton))
										.addGap(57)));
		popUpPanel.setLayout(gl_popUpPanel);
		analitzaButton.addActionListener(analitzaListener);
		cancelaButton.addActionListener(cancelaListener);
		WindowPanel.setLayout(gl_WindowPanel);
		options.getContentPane().setLayout(groupLayout);
	}
}
