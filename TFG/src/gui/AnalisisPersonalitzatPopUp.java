package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

public class AnalisisPersonalitzatPopUp extends JPanel {

    private  ActionListener analitzaListener = new ActionListener() {

	public void actionPerformed(ActionEvent arg0) {
	    if (!cpuCheckBox.isSelected() && !discDurCheckBox.isSelected()
		    && !gpuCheckBox.isSelected()
		    && !internetCheckBox.isSelected()
		    && !ramCheckBox.isSelected()) {
		JOptionPane.showMessageDialog(null,
			"S'ha d'escollir almenys un element per analitzar");
	    } else {
		ViewOpcionsController.setGpu(gpuCheckBox.isSelected());
		ViewOpcionsController.setNet(internetCheckBox.isSelected());
		ViewOpcionsController.setHdd(discDurCheckBox.isSelected());
		ViewOpcionsController.setCpu(cpuCheckBox.isSelected());
		ViewOpcionsController.setRam(ramCheckBox.isSelected());
		ViewAnalisisController.analisisPersonalitzat();
		options.dispose();
	    }

	}
    };
    private  ActionListener cancelaListener = new ActionListener() {
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
    private  JDialog options;
    Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

    public AnalisisPersonalitzatPopUp() {
	options = new JDialog(MainController.view.getOwner(),
		"Anàlisi Personalitzat" + "");
	options.setFont(font);
	Point aqui = new Point(
		MainController.view.getLocationOnScreen().x + 50,
		MainController.view.getLocationOnScreen().y + 50);
	Dimension min = new Dimension(350, 300);
	Box popUpLayout = Box.createVerticalBox();
	options.setLocation(aqui);
	options.setSize(min);
	options.setResizable(false);
	options.setVisible(true);
	JPanel WindowPanel = new JPanel();
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
	popUpLayout.add(popUpPanel);
	JButton cancelaButton = new JButton("Cancel·lar");
	cancelaButton.setFont(font);
	JButton analitzaButton = new JButton("Analitzar");
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
		"Quins components vols analitzar?");
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
									.createParallelGroup(
										Alignment.LEADING)
									.addGroup(
										gl_popUpPanel
											.createSequentialGroup()
											.addComponent(
												cancelaButton)
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
															.createSequentialGroup()
															.addComponent(
																discDurCheckBox,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
															.addGap(18))
													.addComponent(
														cpuCheckBox))
											.addGroup(
												gl_popUpPanel
													.createParallelGroup(
														Alignment.LEADING,
														false)
													.addGroup(
														gl_popUpPanel
															.createSequentialGroup()
															.addComponent(
																gpuCheckBox)
															.addGap(168))
													.addGroup(
														gl_popUpPanel
															.createSequentialGroup()
															.addComponent(
																ramCheckBox)
															.addContainerGap())
													.addGroup(
														Alignment.TRAILING,
														gl_popUpPanel
															.createSequentialGroup()
															.addPreferredGap(
																ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
															.addComponent(
																analitzaButton)
															.addGap(100)))))))
			.addGroup(
				gl_popUpPanel
					.createSequentialGroup()
					.addGap(31)
					.addComponent(
						analisiPersonalitzatLabel,
						GroupLayout.PREFERRED_SIZE,
						274, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(80, Short.MAX_VALUE)));
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
					.addGap(34)
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
