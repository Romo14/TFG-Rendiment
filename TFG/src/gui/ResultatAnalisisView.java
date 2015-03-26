/*
 * 
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

import java.awt.FlowLayout;
import java.awt.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

/**
 * The Class ResultatAnalisisView.
 */
public class ResultatAnalisisView extends JPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 9073846157870757887L;

    /** The btn inici. */
    private JButton btnInici;

    /** The chckbx cpu. */
    private JCheckBox chckbxCpu;

    /** The chckbx disc dur. */
    private JCheckBox chckbxDiscDur;

    /** The chckbx ram. */
    private JCheckBox chckbxRam;

    /** The chckbx xarxa. */
    private JCheckBox chckbxXarxa;

    /** The cpu. */
    private TimeSeries cpu;

    /** The cpu avg. */
    private JLabel cpuAvg;

    /** The cpu max. */
    private JLabel cpuMax;

    /** The cpu min. */
    private JLabel cpuMin;

    /** The cpu ok. */
    private boolean cpuOk;

    /** The dataset. */
    private TimeSeriesCollection dataset;

    /** The estat cpu. */
    private JPanel estatCPU;

    /** The estat hdd. */
    private JPanel estatHDD;

    /** The estat net. */
    private JPanel estatNET;

    /** The estat ram. */
    private JPanel estatRAM;

    /** The font. */
    private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);

    /** The grafica. */
    private JFreeChart grafica;

    /** The grafica panel. */
    private ChartPanel graficaPanel;

    /** The hdd. */
    private TimeSeries hdd;

    /** The hdd avg. */
    private JLabel hddAvg;

    /** The hdd max. */
    private JLabel hddMax;

    /** The hdd min. */
    private JLabel hddMin;

    /** The hdd ok. */
    private boolean hddOk;

    /** The hdd tool tip. */
    private JLabel hddToolTip;

    /** The ko icon. */
    private ImageIcon koIcon;

    /** The ko text. */
    private String koText = "El dispositiu t� una mitjana d'�s de m�s del 75%, pel que es pot determinar que �s necessari revisar";

    /** The net. */
    private TimeSeries net;

    /** The net avg. */
    private JLabel netAvg;

    /** The net max. */
    private JLabel netMax;

    /** The net min. */
    private JLabel netMin;

    /** The net ok. */
    private boolean netOk;

    /** The ok icon. */
    private ImageIcon okIcon;

    /** The ok text. */
    private String okText = "El dispositiu funciona correctament i aguanta perfectament la c�rrega de treball que s'hi realitza";

    /** The opcions. */
    private Object[] opcions = { "Si", "No" };

    /** The panel cpu. */
    private JPanel panelCPU;

    /** The panel dades generals. */
    private JPanel panelDadesGenerals;

    /** The panel grafiques. */
    private JPanel panelGrafiques;

    /** The panel hdd. */
    private JPanel panelHDD;

    /** The panel net. */
    private JPanel panelNET;

    /** The panel ram. */
    private JPanel panelRAM;

    /** The pdf button. */
    private JButton pdfButton;

    /** The ram. */
    private TimeSeries ram;

    /** The ram avg. */
    private JLabel ramAvg;

    /** The ram max. */
    private JLabel ramMax;

    /** The ram min. */
    private JLabel ramMin;

    /** The ram ok. */
    private boolean ramOk;

    /** The resultat. */
    private JFrame resultat;

    /** The tabbed pane. */
    private JTabbedPane tabbedPane;

    /**
     * Instantiates a new resultat analisis view.
     */
    public ResultatAnalisisView() {
	resultat = new JFrame("Resultats de l'an�lisi");
	resultat.setResizable(false);
	resultat.getContentPane().setBackground(Color.WHITE);
	Image img = new ImageIcon(this.getClass().getResource(
		"/images/app-icon.png")).getImage();
	resultat.setIconImage(img);
	resultat.setSize(new Dimension(734, 411));
	resultat.setLocationRelativeTo(null);
	okIcon = new ImageIcon(this.getClass().getResource(
		"/images/ok-icon.png"));
	koIcon = new ImageIcon(this.getClass().getResource(
		"/images/ko-icon.png"));
	grafica = crearGrafica();
	actualitzaColors();
	resultat.setVisible(true);
	tabbedPane = new JTabbedPane();
	tabbedPane.setForeground(Color.GRAY);
	tabbedPane.setBackground(Color.WHITE);
	tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
	panelGrafiques = new JPanel();
	panelGrafiques.setSize(new Dimension(650, 430));
	panelGrafiques.setBackground(Color.WHITE);
	tabbedPane.addTab("Gr�fica", panelGrafiques);
	tabbedPane.setEnabledAt(0, true);
	tabbedPane.setBackgroundAt(0, Color.WHITE);
	chckbxRam = new JCheckBox("RAM");
	chckbxRam.setSelected(true);
	chckbxDiscDur = new JCheckBox("Disc Dur");
	chckbxDiscDur.setSelected(true);
	chckbxXarxa = new JCheckBox("Xarxa");
	chckbxXarxa.setSelected(true);
	chckbxCpu = new JCheckBox("CPU");
	chckbxCpu.setSelected(true);
	btnInici = new JButton();
	btnInici.setBorder(BorderFactory.createEmptyBorder());
	btnInici.setBorderPainted(false);
	btnInici.setIcon(new ImageIcon(this.getClass().getResource(
		"/images/home-icon.png")));
	btnInici.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int a = JOptionPane
			.showOptionDialog(null,
				"Vol tornar al men� principal?", "",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opcions,
				opcions[0]);
		if (a == 0) {
		    MainController.main(null);
		    resultat.dispose();
		}
	    }
	});
	pdfButton = new JButton("");
	pdfButton.setBorderPainted(false);
	pdfButton.setBorder(new EmptyBorder(0, 0, 0, 0));
	pdfButton.setMnemonic('1');
	pdfButton.setSelectedIcon(new ImageIcon(ResultatAnalisisView.class
		.getResource("/images/pdf-icon.png")));
	pdfButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("Guardar");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showSaveDialog(pdfButton) == JFileChooser.APPROVE_OPTION) {
		    try {
			writeChartToPDF(grafica, chooser.getSelectedFile()
				.toString());
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		}
	    }
	});
	pdfButton.setIcon(new ImageIcon(ResultatAnalisisView.class
		.getResource("/images/pdf-icon.png")));

	graficaPanel = new ChartPanel(grafica);
	graficaPanel.setBackground(Color.WHITE);

	hddToolTip = new JLabel("*Dades del disc dur en MB/s");
	GroupLayout gl_panelGrafiques = new GroupLayout(panelGrafiques);
	gl_panelGrafiques
		.setHorizontalGroup(gl_panelGrafiques
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelGrafiques
					.createSequentialGroup()
					.addGap(7)
					.addGroup(
						gl_panelGrafiques
							.createParallelGroup(
								Alignment.LEADING,
								false)
							.addGroup(
								gl_panelGrafiques
									.createSequentialGroup()
									.addGap(10)
									.addComponent(
										hddToolTip))
							.addGroup(
								gl_panelGrafiques
									.createSequentialGroup()
									.addComponent(
										graficaPanel,
										GroupLayout.PREFERRED_SIZE,
										529,
										GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(
										gl_panelGrafiques
											.createParallelGroup(
												Alignment.LEADING)
											.addGroup(
												gl_panelGrafiques
													.createParallelGroup(
														Alignment.TRAILING)
													.addGroup(
														gl_panelGrafiques
															.createSequentialGroup()
															.addGroup(
																gl_panelGrafiques
																	.createParallelGroup(
																		Alignment.TRAILING,
																		false)
																	.addComponent(
																		chckbxRam,
																		Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																	.addComponent(
																		chckbxCpu,
																		Alignment.LEADING))
															.addGap(33))
													.addComponent(
														chckbxDiscDur,
														Alignment.LEADING))
											.addGroup(
												gl_panelGrafiques
													.createSequentialGroup()
													.addGroup(
														gl_panelGrafiques
															.createParallelGroup(
																Alignment.TRAILING,
																false)
															.addGroup(
																Alignment.LEADING,
																gl_panelGrafiques
																	.createSequentialGroup()
																	.addGap(10)
																	.addComponent(
																		pdfButton,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
															.addComponent(
																chckbxXarxa,
																Alignment.LEADING))
													.addGap(18)
													.addComponent(
														btnInici,
														GroupLayout.PREFERRED_SIZE,
														39,
														GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap()));
	gl_panelGrafiques
		.setVerticalGroup(gl_panelGrafiques
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelGrafiques
					.createSequentialGroup()
					.addGroup(
						gl_panelGrafiques
							.createParallelGroup(
								Alignment.LEADING)
							.addComponent(
								graficaPanel,
								GroupLayout.PREFERRED_SIZE,
								356,
								GroupLayout.PREFERRED_SIZE)
							.addGroup(
								Alignment.TRAILING,
								gl_panelGrafiques
									.createSequentialGroup()
									.addGap(61)
									.addComponent(
										chckbxCpu)
									.addPreferredGap(
										ComponentPlacement.UNRELATED)
									.addComponent(
										chckbxRam)
									.addPreferredGap(
										ComponentPlacement.UNRELATED)
									.addComponent(
										chckbxDiscDur)
									.addPreferredGap(
										ComponentPlacement.UNRELATED)
									.addComponent(
										chckbxXarxa)
									.addPreferredGap(
										ComponentPlacement.RELATED,
										122,
										Short.MAX_VALUE)
									.addGroup(
										gl_panelGrafiques
											.createParallelGroup(
												Alignment.LEADING)
											.addComponent(
												btnInici,
												GroupLayout.PREFERRED_SIZE,
												41,
												GroupLayout.PREFERRED_SIZE)
											.addComponent(
												pdfButton,
												GroupLayout.PREFERRED_SIZE,
												41,
												GroupLayout.PREFERRED_SIZE))
									.addGap(76)))
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addComponent(hddToolTip)));
	panelGrafiques.setLayout(gl_panelGrafiques);
	panelDadesGenerals = new JPanel();
	panelDadesGenerals.setBackground(Color.WHITE);
	tabbedPane.addTab("Dades Generals", panelDadesGenerals);
	panelCPU = new JPanel();
	panelCPU.setAlignmentY(Component.TOP_ALIGNMENT);
	panelCPU.setPreferredSize(new Dimension(615, 40));
	panelCPU.setBackground(Color.WHITE);
	panelCPU.setBorder(new TitledBorder(null, "CPU", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	resultat.getContentPane().setBackground(Color.WHITE);
	cpuAvg = new JLabel("Mitjana: ");
	cpuAvg.setFont(font);
	cpuMax = new JLabel("M\u00E0xim:  ");
	cpuMax.setFont(font);

	estatCPU = new JPanel();
	estatCPU.setAlignmentX(Component.LEFT_ALIGNMENT);
	estatCPU.setOpaque(false);
	panelCPU.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	panelCPU.add(cpuMax);
	cpuMin = new JLabel("M\u00EDnim:   ");
	cpuMin.setToolTipText("");
	cpuMin.setAlignmentX(Component.CENTER_ALIGNMENT);
	cpuMin.setFont(font);
	panelCPU.add(cpuMin);
	panelCPU.add(cpuAvg);
	panelCPU.add(estatCPU);

	panelRAM = new JPanel();
	panelRAM.setAlignmentY(Component.TOP_ALIGNMENT);
	panelRAM.setPreferredSize(new Dimension(615, 40));
	panelRAM.setBackground(Color.WHITE);
	panelRAM.setBorder(new TitledBorder(null, "RAM", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));

	ramAvg = new JLabel("Mitjana: ");
	ramAvg.setFont(font);
	ramMax = new JLabel("M\u00E0xim:  ");
	ramMax.setFont(font);

	estatRAM = new JPanel();
	estatRAM.setOpaque(false);
	panelRAM.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	panelRAM.add(ramMax);
	ramMin = new JLabel("M\u00EDnim:   ");
	ramMin.setFont(font);
	panelRAM.add(ramMin);
	panelRAM.add(ramAvg);
	panelRAM.add(estatRAM);

	panelHDD = new JPanel();
	panelHDD.setAlignmentY(Component.TOP_ALIGNMENT);
	panelHDD.setPreferredSize(new Dimension(615, 40));
	panelHDD.setBackground(Color.WHITE);
	panelHDD.setBorder(new TitledBorder(null, "Disc Dur",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));

	hddAvg = new JLabel("Mitjana: ");
	hddAvg.setFont(font);

	estatHDD = new JPanel();
	estatHDD.setOpaque(false);
	panelHDD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	hddMax = new JLabel("M\u00E0xim:  ");
	hddMax.setFont(font);
	panelHDD.add(hddMax);
	hddMin = new JLabel("M\u00EDnim:   ");
	hddMin.setFont(font);
	panelHDD.add(hddMin);
	panelHDD.add(hddAvg);
	panelHDD.add(estatHDD);

	panelNET = new JPanel();
	panelNET.setAlignmentY(Component.TOP_ALIGNMENT);
	panelNET.setPreferredSize(new Dimension(615, 40));
	panelNET.setBackground(Color.WHITE);
	panelNET.setBorder(new TitledBorder(null, "Xarxa",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));

	netAvg = new JLabel("Mitjana: ");
	netAvg.setFont(font);
	netMax = new JLabel("M\u00E0xim:  ");
	netMax.setFont(font);

	estatNET = new JPanel();
	estatNET.setOpaque(false);
	panelNET.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	panelNET.add(netMax);
	netMin = new JLabel("M\u00EDnim:   ");
	netMin.setFont(font);
	panelNET.add(netMin);
	panelNET.add(netAvg);
	panelNET.add(estatNET);
	panelDadesGenerals.setLayout(new BoxLayout(panelDadesGenerals,
		BoxLayout.Y_AXIS));
	panelDadesGenerals.add(panelCPU);
	panelDadesGenerals.add(panelRAM);
	panelDadesGenerals.add(panelHDD);
	panelDadesGenerals.add(panelNET);
	GroupLayout groupLayout = new GroupLayout(resultat.getContentPane());
	groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
		Alignment.LEADING).addComponent(tabbedPane,
		GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE));
	groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
		Alignment.LEADING).addGroup(
		groupLayout
			.createSequentialGroup()
			.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE,
				384, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE)));
	resultat.getContentPane().setLayout(groupLayout);
	resultat.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});
	mostraResultats();
	addCheckBoxListeners();
    }

    /**
     * Actualitza colors.
     */
    private void actualitzaColors() {
	XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
	for (int i = 0; i < dataset.getSeriesCount(); ++i) {
	    if (dataset.getSeries(i) == cpu) {
		renderer.setSeriesPaint(i, Color.BLUE);
	    } else if (dataset.getSeries(i) == ram) {
		renderer.setSeriesPaint(i, Color.RED);
	    } else if (dataset.getSeries(i) == hdd) {
		renderer.setSeriesPaint(i, Color.GREEN);
	    } else if (dataset.getSeries(i) == net) {
		renderer.setSeriesPaint(i, Color.YELLOW);
	    }
	}
	renderer.setSeriesShapesVisible(0, false);
	renderer.setSeriesShapesVisible(1, false);
	renderer.setSeriesShapesVisible(2, false);
	renderer.setSeriesShapesVisible(3, false);

	for (int i = 0; i < dataset.getSeriesCount(); ++i)
	    renderer.setSeriesStroke(i, new BasicStroke(3f));

	XYPlot p = grafica.getXYPlot();

	p.setRenderer(renderer);
    }

    /**
     * Adds the check box listeners.
     */
    private void addCheckBoxListeners() {
	chckbxCpu.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		if (chckbxCpu.isSelected()) {
		    dataset.addSeries(cpu);
		} else {
		    dataset.removeSeries(cpu);
		}
		actualitzaColors();
	    }
	});
	chckbxRam.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		if (chckbxRam.isSelected()) {
		    dataset.addSeries(ram);
		} else {
		    dataset.removeSeries(ram);
		}
		actualitzaColors();
	    }
	});
	chckbxDiscDur.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		if (chckbxDiscDur.isSelected()) {
		    dataset.addSeries(hdd);
		} else {
		    dataset.removeSeries(hdd);
		}
		actualitzaColors();
	    }
	});
	chckbxXarxa.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		if (chckbxXarxa.isSelected()) {
		    dataset.addSeries(net);
		} else {
		    dataset.removeSeries(net);
		}
		actualitzaColors();
	    }
	});
    }

    /**
     * Crear grafica.
     * 
     * @return the j free chart
     */
    public JFreeChart crearGrafica() {
	dataset = new TimeSeriesCollection();
	if (ViewOpcionsController.isCpu()) {
	    cpu = MainController.analisisController.getEvol("CPU");
	    dataset.addSeries(cpu);
	}
	if (ViewOpcionsController.isHdd()) {
	    hdd = MainController.analisisController.getEvol("HDD");
	    dataset.addSeries(hdd);
	}
	if (ViewOpcionsController.isRam()) {
	    ram = MainController.analisisController.getEvol("RAM");
	    dataset.addSeries(ram);
	}
	if (ViewOpcionsController.isNet()) {
	    net = MainController.analisisController.getEvol("NET");
	    dataset.addSeries(net);
	}
	JFreeChart lineChartObject = ChartFactory.createTimeSeriesChart(
		"Us dels components", "Temps (segons)",
		"Percentatge d'�s (%)*", dataset, true, true, false);
	lineChartObject.setBackgroundPaint(Color.WHITE);

	lineChartObject.getXYPlot().setBackgroundAlpha(0.4f);
	XYPlot xyPlot = lineChartObject.getXYPlot();
	for (int i = 0; i < dataset.getSeriesCount(); ++i)
	    xyPlot.getRenderer().setSeriesStroke(i, new BasicStroke(3f));
	ValueAxis domainAxis = xyPlot.getRangeAxis();
	domainAxis.setRange(0, 100);
	return lineChartObject;
    }

    /**
     * Mostra resultats.
     */
    private void mostraResultats() {
	DecimalFormat df = new DecimalFormat("0.00");
	if (!ViewOpcionsController.isCpu()) {
	    chckbxCpu.setVisible(false);
	    panelCPU.setVisible(false);
	} else {
	    Float[] cpuStats = MainController.analisisController.getCpuInfo();
	    cpuAvg.setText(cpuAvg.getText() + df.format(cpuStats[0]) + "%");
	    cpuMax.setText(cpuMax.getText() + df.format(cpuStats[1]) + "%");
	    cpuMin.setText(cpuMin.getText() + df.format(cpuStats[2]) + "%");
	    JLabel a = new JLabel();
	    if (cpuStats[0] > 70) {
		a.setIcon(koIcon);
		a.setToolTipText(koText);
		estatCPU.add(a);
		cpuOk = false;
	    } else {
		a.setIcon(okIcon);
		a.setToolTipText(okText);
		estatCPU.add(a);
		cpuOk = true;
	    }
	}
	if (!ViewOpcionsController.isHdd()) {
	    chckbxDiscDur.setVisible(false);
	    panelHDD.setVisible(false);
	} else {
	    float[] hddStats = MainController.analisisController.getHddInfo();
	    hddAvg.setText(hddAvg.getText() + df.format(hddStats[0])
		    + " MBytes/s");
	    hddMax.setText(hddMax.getText() + df.format(hddStats[1])
		    + " MBytes/s");
	    hddMin.setText(hddMin.getText() + df.format(hddStats[2])
		    + " MBytes/s");
	    JLabel a = new JLabel();
	    if (hddStats[0] > 30) {
		a.setIcon(koIcon);
		a.setToolTipText(koText);
		estatHDD.add(a);
		hddOk = false;
	    } else {
		a.setIcon(okIcon);
		a.setToolTipText(okText);
		estatHDD.add(a);
		hddOk = true;
	    }
	}
	if (!ViewOpcionsController.isNet()) {
	    chckbxXarxa.setVisible(false);
	    panelNET.setVisible(false);
	} else {
	    Float[] netStats = MainController.analisisController.getNetInfo();
	    netAvg.setText(netAvg.getText() + df.format(netStats[0]) + "% ("
		    + df.format(netStats[1] / 1024) + " KBytes)");
	    netMax.setText(netMax.getText() + df.format(netStats[2]) + "% ("
		    + df.format(netStats[3] / 1024) + " KBytes)");
	    netMin.setText(netMin.getText() + df.format(netStats[4]) + "% ("
		    + df.format(netStats[5] / 1024) + " KBytes)");
	    JLabel a = new JLabel();
	    if (netStats[0] > 30) {
		a.setIcon(koIcon);
		a.setToolTipText(koText);
		estatNET.add(a);
		netOk = false;
	    } else {
		a.setIcon(okIcon);
		a.setToolTipText(okText);
		estatNET.add(a);
		netOk = true;
	    }
	}
	if (!ViewOpcionsController.isRam()) {
	    chckbxRam.setVisible(false);
	    panelRAM.setVisible(false);
	} else {
	    Float[] ramStats = MainController.analisisController.getRamInfo();
	    ramAvg.setText(ramAvg.getText() + df.format(ramStats[0]) + "% ("
		    + ramStats[1] + "MB)");
	    ramMax.setText(ramMax.getText() + df.format(ramStats[2]) + "% ("
		    + ramStats[3] + "MB)");
	    ramMin.setText(ramMin.getText() + df.format(ramStats[4]) + "% ("
		    + ramStats[5] + "MB)");
	    JLabel a = new JLabel();
	    if (ramStats[0] > 70) {
		a.setIcon(koIcon);
		a.setToolTipText(koText);
		estatRAM.add(a);
		ramOk = false;
	    } else {
		a.setIcon(okIcon);
		a.setToolTipText(okText);
		estatRAM.add(a);
		ramOk = true;
	    }
	}
    }

    /**
     * Write chart to pdf.
     * 
     * @param chart
     *            the chart
     * @param fileName
     *            the file name
     * @throws DocumentException
     *             the document exception
     * @throws IOException
     * @throws MalformedURLException
     */
    public void writeChartToPDF(JFreeChart chart, String fileName)
	    throws DocumentException, MalformedURLException, IOException {
	Document doc = new Document(PageSize.A4);
	if (!fileName.endsWith(".pdf"))
	    fileName += ".pdf";
	PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(
		fileName));
	doc.addTitle("Resultat de l'an�lisi de l'ordinador");
	doc.addAuthor("Oriol Gasset Romo");
	doc.addSubject("Resultat de l'an�lisi de l'ordinador");
	doc.addKeywords("An�lisi, PDF, rendiment");
	doc.addCreator("An�lisi del rendiment de PCs");

	doc.setPageSize(PageSize.A4);
	doc.open();

	/** T�tol del pdf */
	Chunk title = new Chunk("Resultat de l'an�lisi");
	title.setFont(FontFactory.getFont(FontFactory.HELVETICA, 34,
		Font.CENTER_BASELINE));
	title.setUnderline(0.1f, -2f);
	Paragraph paragraf = new Paragraph(title);
	paragraf.setAlignment(Element.ALIGN_CENTER);
	doc.add(paragraf);
	doc.add(new Paragraph("   "));
	if (MainController.analisisController.getDuracioRestant() == 0) {
	    doc.add(new Paragraph("Duraci� de l'an�lisis: "
		    + MainController.view.panel.getTempsLabel()));
	} else {
	    doc.add(new Paragraph("Duraci� de l'an�lisis: "
		    + MainController.analisisController.getDuracioParcial()));
	}
	doc.add(new Paragraph("Identificador de l'Ordinador: "
		+ java.net.InetAddress.getLocalHost().getHostName()));
	/** Info CPU */
	if (ViewOpcionsController.isCpu()) {
	    Chunk cpuTitle = new Chunk("�s de la CPU");
	    cpuTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
		    Font.BOLD));
	    cpuTitle.setUnderline(0.1f, -2f);
	    Paragraph cpuParagraf = new Paragraph(cpuTitle);
	    doc.add(cpuParagraf);
	    doc.add(new Paragraph(MainController.analisisController
		    .getInfoComponent("CPU")));
	    doc.add(new Paragraph(cpuAvg.getText() + " " + cpuMax.getText()
		    + " " + cpuMin.getText()));
	    if (cpuOk) {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ok-icon.png"));
		doc.add(i);
		doc.add(new Phrase(okText));
	    } else {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ko-icon.png"));
		doc.add(i);
		doc.add(new Phrase(koText));
	    }
	    doc.add(new Paragraph("   "));
	}
	/** Info RAM */
	if (ViewOpcionsController.isRam()) {
	    Chunk ramTitle = new Chunk("�s de la mem�ria RAM");
	    ramTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
		    Font.BOLD));
	    ramTitle.setUnderline(0.1f, -2f);
	    Paragraph ramParagraf = new Paragraph(ramTitle);
	    doc.add(ramParagraf);
	    doc.add(new Paragraph(MainController.analisisController
		    .getInfoComponent("RAM") + "MB"));
	    doc.add(new Paragraph(ramAvg.getText() + " " + ramMax.getText()
		    + " " + ramMin.getText()));
	    if (ramOk) {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ok-icon.png"));
		doc.add(i);
		doc.add(new Phrase(okText));
	    } else {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ko-icon.png"));
		doc.add(i);
		doc.add(new Phrase(koText));
	    }
	    doc.add(new Paragraph("   "));
	}
	/** Info HDD */
	if (ViewOpcionsController.isHdd()) {
	    Chunk hddTitle = new Chunk("�s del disc dur");
	    hddTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
		    Font.BOLD));
	    hddTitle.setUnderline(0.1f, -2f);
	    Paragraph hddParagraf = new Paragraph(hddTitle);
	    doc.add(hddParagraf);
	    doc.add(new Paragraph(MainController.analisisController
		    .getInfoComponent("HDD")));
	    doc.add(new Paragraph(hddAvg.getText() + " " + hddMax.getText()
		    + " " + hddMin.getText()));
	    if (hddOk) {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ok-icon.png"));
		doc.add(i);
		doc.add(new Phrase(okText));
	    } else {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ko-icon.png"));
		doc.add(i);
		doc.add(new Phrase(koText));
	    }
	    doc.add(new Paragraph("   "));
	}
	/** Info NET */
	if (ViewOpcionsController.isNet()) {
	    Chunk netTitle = new Chunk("�s de la xarxa");
	    netTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
		    Font.BOLD));
	    netTitle.setUnderline(0.1f, -2f);
	    Paragraph netParagraf = new Paragraph(netTitle);
	    doc.add(netParagraf);
	    doc.add(new Paragraph(MainController.analisisController
		    .getInfoComponent("NET")));
	    doc.add(new Paragraph(netAvg.getText() + " " + netMax.getText()
		    + " " + netMin.getText()));
	    if (netOk) {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ok-icon.png"));
		doc.add(i);
		doc.add(new Phrase(okText));
	    } else {
		com.itextpdf.text.Image i = com.itextpdf.text.Image
			.getInstance(this.getClass().getResource(
				"/images/ko-icon.png"));
		doc.add(i);
		doc.add(new Phrase(koText));
	    }
	    doc.add(new Paragraph("   "));
	}
	doc.newPage();
	int width = 500;
	int height = 300;
	PdfContentByte dc = docWriter.getDirectContent();
	PdfTemplate tp = dc.createTemplate(width, height);
	@SuppressWarnings("deprecation")
	Graphics2D g2 = tp.createGraphics(width, height,
		new DefaultFontMapper());
	Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
	TimeSeriesCollection sc = new TimeSeriesCollection();
	if (ViewOpcionsController.isCpu())
	    sc.addSeries(cpu);
	if (ViewOpcionsController.isHdd())
	    sc.addSeries(hdd);
	if (ViewOpcionsController.isRam())
	    sc.addSeries(ram);
	if (ViewOpcionsController.isNet())
	    sc.addSeries(net);
	JFreeChart lineChartObject = ChartFactory.createTimeSeriesChart(
		"Us dels components", "Temps (segons)",
		"Percentatge d'�s (%)*", sc, true, true, false);
	lineChartObject.setBackgroundPaint(Color.WHITE);
	lineChartObject.getXYPlot().setBackgroundAlpha(0.4f);
	doc.add(new Paragraph(hddToolTip.getText()));
	lineChartObject.draw(g2, r2D, null);
	g2.dispose();
	dc.addTemplate(tp, 38, docWriter.getVerticalPosition(true) - height);
	doc.add(new Paragraph(hddToolTip.getText()));
	doc.close();

	Process p;
	try {
	    p = Runtime.getRuntime().exec(
		    "rundll32 url.dll,FileProtocolHandler " + fileName);
	    p.waitFor();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }
}
