/*
 * 
 */
package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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

/**
 * Classe que conté antalla que es mostra durant l'execució de l'anàlisi. En
 * aquesta pantalla es mostrarà l'evolució de l'anàlisi mitjançant una barra de
 * progrés i un compte enrere. També es mostren les accions que es podran
 * realitzar: aturar, seguir, mostrar resultats, tornar a començar i tornar al
 * menú principal.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class AnalisisView extends JPanel {

    /** Comptador de la duració de l'anàlisi. */
    private static long comptador;

    /** Temps restant en segons. */
    private static long restant;

    /** El Constant serialVersionUID. */
    private static final long serialVersionUID = 7388902221110453680L;

    /** The temps. */
    private static long temps;

    /** Contenidor de la pantallar. */
    private JFrame analisis;

    /** Listener del botó aturar l'anàlisi. */
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

    /** El botó atura. */
    private JButton btnAtura;

    /** El botó inici. */
    private JButton btnInici;

    /** El botó torna a començar. */
    private JButton btnTornaAComenar;

    /** El botó veure resultats. */
    private JButton btnVeureResultats;

    /** Font utilitzada en els textos de la pantalla. */
    private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

    /** L'etiqueta temps restant. */
    private JLabel lblTempsRestant;

    /** L'etiqueta temps restant descripcio. */
    private JLabel lblTempsRestantDescripcio;

    /** Opcions del popup de tornar al menú inicial. */
    private Object[] opcions = { "Si", "No" };

    /** Barra de progrés de l'anàlisi. */
    private JProgressBar progressBar;

    /**
     * El timer que controla quan s'han d'actualitzar les dades, tant de la
     * pantalla com de l'anàlisi.
     */
    private Timer t;

    /**
     * Creadora per defecte de la classe AnalisisView. En aquesta funció es
     * defineix la pantalla que es mostrarà durant l'execució de l'anàlisi i les
     * funcions associades als seus components
     */
    public AnalisisView() {
	MainController.analisisController = new ViewAnalisisController();
	analisis = new JFrame("Anàlisis del sistema");
	analisis.getContentPane().setBackground(Color.WHITE);
	Image img = new ImageIcon(this.getClass().getResource(
		"/images/app-icon.png")).getImage();
	analisis.setIconImage(img);
	Point aqui = new Point(
		MainController.view.getLocationOnScreen().x + 50,
		MainController.view.getLocationOnScreen().y + 50);
	analisis.setLocation(aqui);
	analisis.setSize(new Dimension(474, 230));
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
	temps = MainController.opcionsController.getTemps();
	comptador = 0;
	lblTempsRestant.setText(getTemps());
	progressBar = new JProgressBar(0, (int) temps);
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
		    progressBar.setValue((int) comptador);
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
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(23)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(369)
								.addComponent(btnInici, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)))
						.addGap(41))
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAtura, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnVeureResultats, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnTornaAComenar, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblTempsRestantDescripcio, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTempsRestant, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
						.addGap(35))))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
				.addGap(35)
				.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblTempsRestantDescripcio)
					.addComponent(lblTempsRestant, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(61)
						.addComponent(btnInici, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAtura, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnTornaAComenar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnVeureResultats, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap())
	);
	analisis.getContentPane().setLayout(groupLayout);
	analisis.setVisible(true);
	t.start();
	analisis.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});
    }

    /**
     * Obté la duració restant de l'anàlisi en segons.
     * 
     * @return La duracio restant
     */
    public static long getDuracioRestant() {
	return temps - comptador;
    }

    /**
     * Obté la duració restant de l'anàlisi en format text.
     * 
     * @return La duració restant
     */
    public static String getTemps() {
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

    /**
     * Obté la duració de l'anàlisi en un moment concret.
     * 
     * @return La duració de l'anàlisi
     */
    public static String getTempsParcial() {
	restant = comptador;
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
