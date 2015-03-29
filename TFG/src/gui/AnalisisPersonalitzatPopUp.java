/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisPersonalitzatPopUp.
 */
public class AnalisisPersonalitzatPopUp extends JPanel {
	
	/** The cpu check box. */
	private static JCheckBox cpuCheckBox;
	
	/** The disc dur check box. */
	private static JCheckBox discDurCheckBox;
	
	/** The internet check box. */
	private static JCheckBox internetCheckBox;
	
	/** The ram check box. */
	private static JCheckBox ramCheckBox;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1893117450756795448L;
	
	/** The analitza listener. */
	private ActionListener analitzaListener = new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			if (!cpuCheckBox.isSelected() && !discDurCheckBox.isSelected()
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
					ViewOpcionsController.setNet(internetCheckBox.isSelected());
					ViewOpcionsController.setHdd(discDurCheckBox.isSelected());
					ViewOpcionsController.setCpu(cpuCheckBox.isSelected());
					ViewOpcionsController.setRam(ramCheckBox.isSelected());
					new AnalisisView();
					options.dispose();
					MainController.view.dispose();
				}
			}

		}
	};
	
	/** The cancela listener. */
	private ActionListener cancelaListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			options.dispose();
			MainController.view.panel.activaBotons();
		}
	};
	
	/** The font. */
	private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);
	
	/** The opcions. */
	private Object[] opcions = { "Si", "No" };
	
	/** The options. */
	private JFrame options;

	/**
	 * Instantiates a new analisis personalitzat pop up.
	 */
	public AnalisisPersonalitzatPopUp() {
		options = new JFrame("Anàlisi Personalitzat" + "");
		options.setFont(font);
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		options.setIconImage(img);
		Point aqui = new Point(
				MainController.view.getLocationOnScreen().x + 50,
				MainController.view.getLocationOnScreen().y + 50);
		Box popUpLayout = Box.createVerticalBox();
		options.setLocation(aqui);
		options.setSize(new Dimension(340, 249));
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
		ramCheckBox = new JCheckBox("Mermòria Ram");

		ramCheckBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		ramCheckBox.setFont(font);
		JLabel analisiPersonalitzatLabel = new JLabel(
				"Quins components vol analitzar?");
		analisiPersonalitzatLabel.setFont(font);
		analisiPersonalitzatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout gl_popUpPanel = new GroupLayout(popUpPanel);
		gl_popUpPanel.setHorizontalGroup(
			gl_popUpPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_popUpPanel.createSequentialGroup()
					.addGroup(gl_popUpPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_popUpPanel.createSequentialGroup()
							.addGroup(gl_popUpPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_popUpPanel.createSequentialGroup()
									.addGap(49)
									.addComponent(analitzaButton)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_popUpPanel.createSequentialGroup()
									.addContainerGap(50, Short.MAX_VALUE)
									.addGroup(gl_popUpPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_popUpPanel.createSequentialGroup()
											.addComponent(discDurCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(Alignment.TRAILING, gl_popUpPanel.createSequentialGroup()
											.addComponent(cpuCheckBox)
											.addGap(48)))))
							.addGroup(gl_popUpPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_popUpPanel.createSequentialGroup()
									.addGroup(gl_popUpPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(cancelaButton)
										.addComponent(internetCheckBox))
									.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
								.addGroup(gl_popUpPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ramCheckBox, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
						.addGroup(gl_popUpPanel.createSequentialGroup()
							.addGap(31)
							.addComponent(analisiPersonalitzatLabel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_popUpPanel.setVerticalGroup(
			gl_popUpPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_popUpPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(analisiPersonalitzatLabel)
					.addGap(18)
					.addGroup(gl_popUpPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cpuCheckBox)
						.addComponent(ramCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_popUpPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(discDurCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(internetCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(27)
					.addGroup(gl_popUpPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelaButton)
						.addComponent(analitzaButton))
					.addGap(57))
		);
		popUpPanel.setLayout(gl_popUpPanel);
		analitzaButton.addActionListener(analitzaListener);
		cancelaButton.addActionListener(cancelaListener);
		WindowPanel.setLayout(gl_WindowPanel);
		options.getContentPane().setLayout(groupLayout);
	}
}
