package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
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
import java.awt.Component;

public class AnalisisView extends JPanel {

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
		ViewAnalisisController.stop();
		btnVeureResultats.setEnabled(true);
		t.stop();
	    } else {
		btnAtura.setText("Atura");
		ViewAnalisisController.start();
		btnVeureResultats.setEnabled(false);
		t.start();
	    }

	}
    };

    public AnalisisView() {
	analisis = new JDialog(MainController.view.getOwner(),
		"Anàlisis del sistema");
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
	lblTempsRestantDescripcio.setFont(font);
	lblTempsRestant = new JLabel("");
	lblTempsRestant.setAlignmentX(Component.RIGHT_ALIGNMENT);
	lblTempsRestant.setFont(font);
	btnAtura = new JButton("Atura");
	btnAtura.addActionListener(atura);
	btnAtura.setFont(font);
	btnVeureResultats = new JButton("Veure resultats");
	btnVeureResultats.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    new ResultatAnalisisView();
		    analisis.dispose();
		}
	});
	btnVeureResultats.setEnabled(false);
	btnVeureResultats.setFont(font);
	temps = 1;//ViewOpcionsController.getTemps(); //TODO cambiar temps!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	comptador = 0;
	lblTempsRestant.setText(getTemps());
	progressBar = new JProgressBar(0, temps);
	progressBar.setStringPainted(true);
	progressBar.setFont(font);
	t = new Timer(1000, new ActionListener() {

	    public void actionPerformed(ActionEvent arg0) {
		if (comptador == Integer.valueOf(temps)) {
		    t.stop();
		    btnVeureResultats.setEnabled(true);
		    btnAtura.setEnabled(false);
		} else {
		    ++comptador;
		    progressBar.setValue(comptador);
		    lblTempsRestant.setText(getTemps());
		}

	    }
	});
	GroupLayout groupLayout = new GroupLayout(analisis.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(23)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(20)
								.addComponent(btnAtura, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblTempsRestantDescripcio, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(0)
								.addComponent(lblTempsRestant, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
								.addGap(181))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(41)
								.addComponent(btnVeureResultats, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())))
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(43, Short.MAX_VALUE))))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(40)
				.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblTempsRestant)
					.addComponent(lblTempsRestantDescripcio))
				.addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnAtura, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnVeureResultats))
				.addGap(33))
	);
	analisis.getContentPane().setLayout(groupLayout);
	analisis.setVisible(true);
	t.start();
    }

    private String getTemps() {
	int restant = temps - comptador;
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

    public void updateTemps() {

    }
}
