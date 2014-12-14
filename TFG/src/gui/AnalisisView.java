package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

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
    private JButton btnTornaAComenar;
    private JButton btnInici;

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
	lblTempsRestantDescripcio.setAlignmentX(Component.CENTER_ALIGNMENT);
	lblTempsRestantDescripcio.setFont(font);
	lblTempsRestant = new JLabel("");
	lblTempsRestant.setAlignmentX(Component.CENTER_ALIGNMENT);
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
	temps = ViewOpcionsController.getTemps(); // TODO cambiar
						  // temps!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

	btnTornaAComenar = new JButton("Torna a comen\u00E7ar");
	btnTornaAComenar.setEnabled(false);
	btnTornaAComenar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
	    }
	});

	btnInici = new JButton();
	btnInici.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    int a = JOptionPane.showConfirmDialog(null, "Vols cancel·lar l'anàlisi i tornar al menú principal?", "", JOptionPane.YES_NO_OPTION);
		    if(a==0){
			MainController.main(null);
			analisis.dispose();
			ViewAnalisisController.stop();
		    }
		}
	});
	btnInici.setIcon(new ImageIcon(this.getClass().getResource(
		"/images/home-icon.png")));
	GroupLayout groupLayout = new GroupLayout(analisis.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.TRAILING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(23)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnInici, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAtura, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnVeureResultats, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnTornaAComenar))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblTempsRestantDescripcio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTempsRestant, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
						.addGap(39)))
				.addGap(10))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(40)
				.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblTempsRestantDescripcio)
					.addComponent(lblTempsRestant, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnAtura, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnVeureResultats)
					.addComponent(btnTornaAComenar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(8)
				.addComponent(btnInici, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
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
