package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

public class ResultatAnalisisView extends JPanel {

    private static final long serialVersionUID = 9073846157870757887L;
    private JFreeChart grafica;
    private JDialog resultat;
    private JPanel panelGrafiques;
    private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);
    private JLabel ok;
    private JLabel ko;
    private JPanel panelCPU;
    private JPanel panelGPU;
    private JPanel panelNET;
    private JPanel panelRAM;
    private JPanel panelHDD;
    private JCheckBox chckbxCpu;
    private JCheckBox chckbxGpu;
    private JCheckBox chckbxRam;
    private JCheckBox chckbxDiscDur;
    private JCheckBox chckbxXarxa;

    public ResultatAnalisisView() {
	resultat = new JDialog(MainController.view.getOwner(),
		"Resultats de l'anàlisis");
	Image img = new ImageIcon(this.getClass().getResource(
		"/images/app-icon.png")).getImage();
	resultat.setIconImage(img);
	Dimension min = new Dimension(800, 670);
	resultat.setSize(min);
	resultat.setResizable(false);
	resultat.setLocationRelativeTo(null);
	SpringLayout springLayout = new SpringLayout();
	resultat.getContentPane().setLayout(springLayout);

	ImageIcon image = new ImageIcon(this.getClass().getResource(
		"/images/ok-icon.png"));
	ok = new JLabel(image);
	ko = new JLabel(new ImageIcon(this.getClass().getResource(
		"/images/ko-icon.png")));

	panelGrafiques = new JPanel();
	springLayout.putConstraint(SpringLayout.NORTH, panelGrafiques, 10,
		SpringLayout.NORTH, resultat.getContentPane());
	springLayout.putConstraint(SpringLayout.WEST, panelGrafiques, 343,
		SpringLayout.WEST, resultat.getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, panelGrafiques, -24,
		SpringLayout.EAST, resultat.getContentPane());
	panelGrafiques.setBorder(new TitledBorder(null, "Gr\u00E0fiques",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	resultat.getContentPane().add(panelGrafiques);

	JPanel panelDadesGenerals = new JPanel();
	springLayout.putConstraint(SpringLayout.WEST, panelDadesGenerals, 10,
		SpringLayout.WEST, resultat.getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, panelDadesGenerals, -6,
		SpringLayout.WEST, panelGrafiques);
	springLayout.putConstraint(SpringLayout.SOUTH, panelGrafiques, 0,
		SpringLayout.SOUTH, panelDadesGenerals);
	springLayout.putConstraint(SpringLayout.SOUTH, panelDadesGenerals, -49,
		SpringLayout.SOUTH, resultat.getContentPane());
	springLayout.putConstraint(SpringLayout.NORTH, panelDadesGenerals, 10,
		SpringLayout.NORTH, resultat.getContentPane());

	chckbxCpu = new JCheckBox("CPU");
	chckbxGpu = new JCheckBox("GPU");
	chckbxRam = new JCheckBox("RAM");
	chckbxDiscDur = new JCheckBox("Disc Dur");
	chckbxXarxa = new JCheckBox("Xarxa");

	grafica = crearGrafica();
	ChartPanel graficaPanel = new ChartPanel(grafica);
	GroupLayout gl_panelGrafiques = new GroupLayout(panelGrafiques);
	gl_panelGrafiques.setHorizontalGroup(gl_panelGrafiques
		.createParallelGroup(Alignment.LEADING)
		.addGroup(
			gl_panelGrafiques
				.createSequentialGroup()
				.addGap(19)
				.addComponent(graficaPanel,
					GroupLayout.DEFAULT_SIZE, 386,
					Short.MAX_VALUE).addContainerGap())
		.addGroup(
			Alignment.TRAILING,
			gl_panelGrafiques.createSequentialGroup()
				.addContainerGap(48, Short.MAX_VALUE)
				.addComponent(chckbxCpu).addGap(18)
				.addComponent(chckbxGpu).addGap(18)
				.addComponent(chckbxRam).addGap(18)
				.addComponent(chckbxDiscDur).addGap(18)
				.addComponent(chckbxXarxa).addGap(40)));
	gl_panelGrafiques.setVerticalGroup(gl_panelGrafiques
		.createParallelGroup(Alignment.TRAILING).addGroup(
			Alignment.LEADING,
			gl_panelGrafiques
				.createSequentialGroup()
				.addContainerGap()
				.addComponent(graficaPanel,
					GroupLayout.PREFERRED_SIZE, 351,
					GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(
					gl_panelGrafiques
						.createParallelGroup(
							Alignment.BASELINE)
						.addComponent(chckbxCpu)
						.addComponent(chckbxGpu)
						.addComponent(chckbxRam)
						.addComponent(chckbxDiscDur)
						.addComponent(chckbxXarxa))
				.addContainerGap(157, Short.MAX_VALUE)));
	panelGrafiques.setLayout(gl_panelGrafiques);
	panelDadesGenerals.setBorder(new TitledBorder(null, "Dades generals",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	resultat.getContentPane().add(panelDadesGenerals);

	panelCPU = new JPanel();
	panelCPU.setBorder(new TitledBorder(null, "CPU", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));

	panelGPU = new JPanel();
	panelGPU.setBorder(new TitledBorder(null, "GPU", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));

	panelNET = new JPanel();
	panelNET.setBorder(new TitledBorder(null, "Xarxa",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));

	panelRAM = new JPanel();
	panelRAM.setBorder(new TitledBorder(null, "RAM", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));

	panelHDD = new JPanel();
	panelHDD.setBorder(new TitledBorder(null, "Disc Dur",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	GroupLayout gl_panelDadesGenerals = new GroupLayout(panelDadesGenerals);
	gl_panelDadesGenerals.setHorizontalGroup(
		gl_panelDadesGenerals.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelDadesGenerals.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelDadesGenerals.createParallelGroup(Alignment.TRAILING)
					.addComponent(panelNET, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
					.addComponent(panelHDD, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelCPU, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
					.addComponent(panelGPU, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelRAM, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(8))
	);
	gl_panelDadesGenerals.setVerticalGroup(
		gl_panelDadesGenerals.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelDadesGenerals.createSequentialGroup()
				.addContainerGap()
				.addComponent(panelCPU, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelGPU, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panelRAM, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				.addGap(13)
				.addComponent(panelHDD, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelNET, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	);

	JLabel labelMitjanaHDD = new JLabel("Mitjana: ");
	labelMitjanaHDD.setFont(font);
	JLabel labelMaxHDD = new JLabel("M\u00E0xim:");
	labelMaxHDD.setFont(font);
	JLabel labelMinHDD = new JLabel("M\u00EDnim: ");
	labelMinHDD.setFont(font);

	JLabel estatHDD = new JLabel("");

	JPanel panel_2 = new JPanel();
	GroupLayout gl_panelHDD = new GroupLayout(panelHDD);
	gl_panelHDD
		.setHorizontalGroup(gl_panelHDD
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelHDD
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_panelHDD
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								gl_panelHDD
									.createSequentialGroup()
									.addComponent(
										labelMaxHDD)
									.addPreferredGap(
										ComponentPlacement.RELATED,
										23,
										Short.MAX_VALUE)
									.addComponent(
										estatHDD))
							.addComponent(
								labelMitjanaHDD,
								GroupLayout.PREFERRED_SIZE,
								73,
								GroupLayout.PREFERRED_SIZE)
							.addComponent(
								labelMinHDD))
					.addGap(100)
					.addComponent(panel_2,
						GroupLayout.PREFERRED_SIZE, 90,
						GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));
	gl_panelHDD
		.setVerticalGroup(gl_panelHDD
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelHDD
					.createSequentialGroup()
					.addGroup(
						gl_panelHDD
							.createParallelGroup(
								Alignment.TRAILING,
								false)
							.addGroup(
								gl_panelHDD
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(
										panel_2,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
							.addGroup(
								Alignment.LEADING,
								gl_panelHDD
									.createSequentialGroup()
									.addGap(8)
									.addComponent(
										labelMitjanaHDD)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addGroup(
										gl_panelHDD
											.createParallelGroup(
												Alignment.BASELINE)
											.addComponent(
												labelMaxHDD)
											.addComponent(
												estatHDD))
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										labelMinHDD)))
					.addContainerGap()));
	panelHDD.setLayout(gl_panelHDD);

	JLabel labelMitjanaGPU = new JLabel("Mitjana: ");
	labelMitjanaGPU.setFont(font);
	JLabel labelMaxGPU = new JLabel("M\u00E0xim:");
	labelMaxGPU.setFont(font);
	JLabel labelMinGPU = new JLabel("M\u00EDnim: ");
	labelMinGPU.setFont(font);

	JPanel estatRAM = new JPanel();
	ImageIcon icon = new ImageIcon("/images/ok-icon.png");
	icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100,
		BufferedImage.SCALE_SMOOTH));
	GroupLayout gl_panelRAM = new GroupLayout(panelRAM);
	gl_panelRAM.setHorizontalGroup(gl_panelRAM.createParallelGroup(
		Alignment.LEADING).addGroup(
		gl_panelRAM
			.createSequentialGroup()
			.addContainerGap()
			.addGroup(
				gl_panelRAM
					.createParallelGroup(Alignment.LEADING)
					.addComponent(labelMitjanaGPU)
					.addComponent(labelMaxGPU,
						GroupLayout.DEFAULT_SIZE, 109,
						Short.MAX_VALUE)
					.addComponent(labelMinGPU,
						GroupLayout.DEFAULT_SIZE, 109,
						Short.MAX_VALUE))
			.addGap(66)
			.addComponent(estatRAM, GroupLayout.PREFERRED_SIZE, 90,
				GroupLayout.PREFERRED_SIZE).addContainerGap()));
	gl_panelRAM
		.setVerticalGroup(gl_panelRAM
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelRAM
					.createSequentialGroup()
					.addGap(6)
					.addGroup(
						gl_panelRAM
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								gl_panelRAM
									.createSequentialGroup()
									.addComponent(
										estatRAM,
										GroupLayout.DEFAULT_SIZE,
										70,
										Short.MAX_VALUE)
									.addContainerGap())
							.addGroup(
								gl_panelRAM
									.createSequentialGroup()
									.addComponent(
										labelMitjanaGPU)
									.addPreferredGap(
										ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
									.addComponent(
										labelMaxGPU)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										labelMinGPU)
									.addGap(9)))));
	panelRAM.setLayout(gl_panelRAM);

	JLabel label_3 = new JLabel("Mitjana: ");
	label_3.setFont(font);
	JLabel label_7 = new JLabel("M\u00E0xim:");
	label_7.setFont(font);
	JLabel label_11 = new JLabel("M\u00EDnim: ");
	label_11.setFont(font);

	JLabel estatNET = new JLabel("");

	JPanel panel_3 = new JPanel();
	GroupLayout gl_panelNET = new GroupLayout(panelNET);
	gl_panelNET
		.setHorizontalGroup(gl_panelNET
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelNET
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_panelNET
							.createParallelGroup(
								Alignment.TRAILING)
							.addGroup(
								gl_panelNET
									.createSequentialGroup()
									.addComponent(
										label_3,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
									.addGap(122))
							.addGroup(
								Alignment.LEADING,
								gl_panelNET
									.createParallelGroup(
										Alignment.TRAILING,
										false)
									.addComponent(
										label_7,
										Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
									.addComponent(
										label_11,
										Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE,
										63,
										Short.MAX_VALUE)))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(panel_3,
						GroupLayout.PREFERRED_SIZE, 85,
						GroupLayout.PREFERRED_SIZE)
					.addGap(100).addComponent(estatNET)
					.addGap(81)));
	gl_panelNET
		.setVerticalGroup(gl_panelNET
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelNET
					.createSequentialGroup()
					.addGroup(
						gl_panelNET
							.createParallelGroup(
								Alignment.TRAILING)
							.addGroup(
								gl_panelNET
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(
										panel_3,
										GroupLayout.DEFAULT_SIZE,
										72,
										Short.MAX_VALUE))
							.addGroup(
								Alignment.LEADING,
								gl_panelNET
									.createSequentialGroup()
									.addComponent(
										label_3)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addGroup(
										gl_panelNET
											.createParallelGroup(
												Alignment.BASELINE)
											.addComponent(
												label_7)
											.addComponent(
												estatNET))
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										label_11)))
					.addContainerGap()));
	panelNET.setLayout(gl_panelNET);

	JLabel label = new JLabel("Mitjana: ");
	label.setFont(font);
	JLabel label_4 = new JLabel("M\u00E0xim:");
	label_4.setFont(font);
	JLabel label_8 = new JLabel("M\u00EDnim: ");
	label_8.setFont(font);

	JLabel estatGPU = new JLabel("");

	JPanel panel_1 = new JPanel();
	GroupLayout gl_panelGPU = new GroupLayout(panelGPU);
	gl_panelGPU
		.setHorizontalGroup(gl_panelGPU
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelGPU
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_panelGPU
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								gl_panelGPU
									.createParallelGroup(
										Alignment.TRAILING,
										false)
									.addComponent(
										label,
										Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
									.addComponent(
										label_4,
										Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
							.addComponent(
								label_8,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(estatGPU)
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(panel_1,
						GroupLayout.PREFERRED_SIZE, 90,
						GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));
	gl_panelGPU
		.setVerticalGroup(gl_panelGPU
			.createParallelGroup(Alignment.TRAILING)
			.addGroup(
				Alignment.LEADING,
				gl_panelGPU
					.createSequentialGroup()
					.addGroup(
						gl_panelGPU
							.createParallelGroup(
								Alignment.TRAILING)
							.addComponent(estatGPU)
							.addGroup(
								gl_panelGPU
									.createSequentialGroup()
									.addComponent(
										label)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										label_4)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										label_8)
									.addPreferredGap(
										ComponentPlacement.RELATED)))
					.addGap(26))
			.addComponent(panel_1, Alignment.LEADING,
				GroupLayout.PREFERRED_SIZE, 70,
				GroupLayout.PREFERRED_SIZE));
	panelGPU.setLayout(gl_panelGPU);

	JLabel lblMitjanaCPU = new JLabel("Mitjana: ");
	lblMitjanaCPU.setFont(font);
	JLabel lblMximCPU = new JLabel("M\u00E0xim:");
	lblMximCPU.setFont(font);
	JLabel lblMnimCPU = new JLabel("M\u00EDnim: ");
	lblMnimCPU.setFont(font);

	JLabel estatCPU = new JLabel("");

	JPanel panel = new JPanel();
	GroupLayout gl_panelCPU = new GroupLayout(panelCPU);
	gl_panelCPU
		.setHorizontalGroup(gl_panelCPU
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelCPU
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_panelCPU
							.createParallelGroup(
								Alignment.LEADING)
							.addGroup(
								gl_panelCPU
									.createSequentialGroup()
									.addComponent(
										lblMximCPU)
									.addPreferredGap(
										ComponentPlacement.RELATED,
										177,
										Short.MAX_VALUE)
									.addComponent(
										estatCPU))
							.addGroup(
								gl_panelCPU
									.createParallelGroup(
										Alignment.LEADING)
									.addComponent(
										lblMitjanaCPU)
									.addComponent(
										lblMnimCPU)))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(panel,
						GroupLayout.PREFERRED_SIZE, 90,
						GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));
	gl_panelCPU
		.setVerticalGroup(gl_panelCPU
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelCPU
					.createSequentialGroup()
					.addGap(6)
					.addGroup(
						gl_panelCPU
							.createParallelGroup(
								Alignment.TRAILING)
							.addComponent(
								panel,
								GroupLayout.PREFERRED_SIZE,
								58,
								GroupLayout.PREFERRED_SIZE)
							.addGroup(
								gl_panelCPU
									.createSequentialGroup()
									.addComponent(
										lblMitjanaCPU)
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addGroup(
										gl_panelCPU
											.createParallelGroup(
												Alignment.BASELINE)
											.addComponent(
												lblMximCPU)
											.addComponent(
												estatCPU))
									.addPreferredGap(
										ComponentPlacement.RELATED)
									.addComponent(
										lblMnimCPU)))
					.addContainerGap()));
	panelCPU.setLayout(gl_panelCPU);
	panelDadesGenerals.setLayout(gl_panelDadesGenerals);
	resultat.setVisible(true);
	mostraResultats();
    }

    private void mostraResultats() {
	if (!ViewOpcionsController.isCpu()) {
	    chckbxCpu.setVisible(false);
	    panelCPU.setVisible(false);
	}
	if (!ViewOpcionsController.isGpu()) {
	    chckbxGpu.setVisible(false);
	    panelGPU.setVisible(false);
	}
	if (!ViewOpcionsController.isHdd()) {
	    chckbxDiscDur.setVisible(false);
	    panelHDD.setVisible(false);
	}
	if (!ViewOpcionsController.isNet()) {
	    chckbxXarxa.setVisible(false);
	    panelNET.setVisible(false);
	}
	if (!ViewOpcionsController.isRam()) {
	    chckbxRam.setVisible(false);
	    panelRAM.setVisible(false);
	}
    }

    public JFreeChart crearGrafica() {

	DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
	line_chart_dataset.addValue(15, "schools", "1970");
	line_chart_dataset.addValue(30, "schools", "1980");
	line_chart_dataset.addValue(60, "schools", "1990");
	line_chart_dataset.addValue(120, "schools", "2000");
	line_chart_dataset.addValue(240, "schools", "2010");

	/* Step -2:Define the JFreeChart object to create line chart */
	JFreeChart lineChartObject = ChartFactory
		.createLineChart("Us de components", "Temps (hores)",
			"Percentatge d'ús (%)", line_chart_dataset,
			PlotOrientation.VERTICAL, true, true, false);
	return lineChartObject;
    }

}
