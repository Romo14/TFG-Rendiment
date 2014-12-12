package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JDialog;
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

public class DuracioPopUp extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 2653787619021439964L;
    private ActionListener accepta = new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    setHores((int) horesSpinner.getValue());
	    setDies((int) diesSpinner.getValue());
	    setMinuts((int) minutsSpinner.getValue());
	    PanelView.updateDuracioLabel();
	    ViewOpcionsController.setHores(hores);
	    ViewOpcionsController.setMinuts(minuts);
	    ViewOpcionsController.setDies(dies);
	    // TODO Tancar popup
	}
    };
    public int dies;
    private JLabel diesLabel;
    private JSpinner diesSpinner;
    private JDialog duracio;
    public int hores;
    private JLabel horesLabel;
    private JSpinner horesSpinner;
    public int minuts;
    private JLabel minutsLabel;
    Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

    private JSpinner minutsSpinner;

    public DuracioPopUp() {
	duracio = new JDialog(MainController.view.getOwner(),
		"Anàlisi Personalitzat" + "");
	Point aqui = new Point(
		MainController.view.getLocationOnScreen().x + 50,
		MainController.view.getLocationOnScreen().y + 50);
	Dimension min = new Dimension(300, 230);
	duracio.setLocation(aqui);
	duracio.setSize(min);
	duracio.setResizable(false);
	duracio.setVisible(true);
	minutsSpinner = new JSpinner();
	minutsSpinner.addChangeListener(new ChangeListener() {
	    public void stateChanged(ChangeEvent arg0) {
		if ((int) minutsSpinner.getValue() >= 60) {
		    minutsSpinner.setValue(0);
		    horesSpinner.setValue((int) horesSpinner.getValue() + 1);
		}
	    }
	});
	minutsSpinner.setModel(new SpinnerNumberModel(0, 0, 60, 1));
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
	horesSpinner.setModel(new SpinnerNumberModel(1, 0, 24, 1));
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
	diesSpinner.setModel(new SpinnerNumberModel(0, 0, null, 1));
	diesSpinner.setFont(font);

	JButton acceptaButton = new JButton("Accepta");
	acceptaButton.setFont(font);
	acceptaButton.addActionListener(accepta);

	JButton btnCancellar = new JButton("Cancel\u00B7lar");
	btnCancellar.setFont(font);
	GroupLayout groupLayout = new GroupLayout(duracio.getContentPane());
	groupLayout.setHorizontalGroup(groupLayout
		.createParallelGroup(Alignment.TRAILING)
		.addGroup(
			Alignment.LEADING,
			groupLayout
				.createSequentialGroup()
				.addGap(43)
				.addGroup(
					groupLayout
						.createParallelGroup(
							Alignment.LEADING)
						.addComponent(minutsLabel)
						.addComponent(diesLabel)
						.addComponent(horesLabel))
				.addPreferredGap(ComponentPlacement.RELATED,
					38, Short.MAX_VALUE)
				.addGroup(
					groupLayout
						.createParallelGroup(
							Alignment.LEADING,
							false)
						.addComponent(minutsSpinner,
							Alignment.TRAILING)
						.addComponent(horesSpinner,
							Alignment.TRAILING)
						.addComponent(diesSpinner,
							Alignment.TRAILING))
				.addGap(66))
		.addGroup(
			Alignment.LEADING,
			groupLayout.createSequentialGroup().addGap(29)
				.addComponent(btnCancellar).addGap(18)
				.addComponent(acceptaButton)
				.addContainerGap(50, Short.MAX_VALUE)));
	groupLayout
		.setVerticalGroup(groupLayout
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addGap(28)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								groupLayout
									.createSequentialGroup()
									.addComponent(
										minutsSpinner,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addGroup(
										groupLayout
											.createParallelGroup(
												Alignment.BASELINE)
											.addComponent(
												horesSpinner,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
											.addComponent(
												horesLabel)))
							.addComponent(
								minutsLabel))
					.addGap(10)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.BASELINE)
							.addComponent(
								diesSpinner,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
							.addComponent(diesLabel))
					.addGap(18)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.BASELINE)
							.addComponent(
								btnCancellar)
							.addComponent(
								acceptaButton,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addGap(31)));
	duracio.getContentPane().setLayout(groupLayout);

    }

    public int getDies() {
	return dies;
    }

    public int getHores() {
	return hores;
    }

    public int getMinuts() {
	return minuts;
    }

    public void setDies(int dies) {
	this.dies = dies;
    }

    public void setHores(int hores) {
	this.hores = hores;
    }

    public void setMinuts(int minuts) {
	this.minuts = minuts;
    }

    public void limitTemps() {
	JOptionPane.showMessageDialog(null,
		"No es pot fer una anàlisis de durada superior a 7 dies");
    }
}
