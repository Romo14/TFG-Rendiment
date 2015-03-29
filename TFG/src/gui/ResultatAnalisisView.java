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

import java.util.Locale;

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
	private int cpuOk;

	/**
	 * Acció que es realitza quan premem el botó de pdf o de tancar. J
	 */
	private ActionListener crearPdfAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fileChooser();
		}
	};

	/** The dataset. */
	private TimeSeriesCollection dataset;

	/** The equal icon. */
	private ImageIcon equalIcon;

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
	private int hddOk;

	/** The hdd tool tip. */
	private JLabel hddToolTip;

	/** The ko icon. */
	private ImageIcon koIcon;

	/** The net. */
	private TimeSeries net;

	/** The net avg. */
	private JLabel netAvg;

	/** The net max. */
	private JLabel netMax;

	/** The net min. */
	private JLabel netMin;

	/** The net ok. */
	private int netOk;

	/** The ok icon. */
	private ImageIcon okIcon;

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

	/** The pdf creat. */
	private boolean pdfCreat = false;

	/** The ram. */
	private TimeSeries ram;

	/** The ram avg. */
	private JLabel ramAvg;

	/** The ram max. */
	private JLabel ramMax;

	/** The ram min. */
	private JLabel ramMin;

	/** The ram ok. */
	private int ramOk;

	/** The resultat. */
	private JFrame resultat;

	/** The tabbed pane. */
	private JTabbedPane tabbedPane;

	/**
	 * Instantiates a new resultat analisis view.
	 */
	public ResultatAnalisisView() {
		resultat = new JFrame("Resultats de l'anàlisi");
		resultat.setMaximumSize(new Dimension(737, 411));
		resultat.setResizable(false);
		resultat.getContentPane().setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		resultat.setIconImage(img);
		resultat.setSize(new Dimension(737, 411));
		resultat.setLocationRelativeTo(null);
		equalIcon = new ImageIcon(this.getClass().getResource(
				"/images/equal-icon.png"));
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
		tabbedPane.addTab("Gràfica", panelGrafiques);
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
						.showOptionDialog(
								null,
								"Vol tornar al menú principal? És obligatori guardar el resultat, en cas"
										+ " de no haver-ho fet serà redirigit a la pantalla de guardar resultat",
								"", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, opcions,
								opcions[0]);
				if (a == 0) {
					if (!pdfCreat)
						fileChooser();
					if (pdfCreat) {
						MainController.main(null);
						resultat.dispose();
					} else {
						MainController.main(null);
						resultat.dispose();
					}
				}
			}
		});
		pdfButton = new JButton("");
		pdfButton.setBorderPainted(false);
		pdfButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		pdfButton.setMnemonic('1');
		pdfButton.setSelectedIcon(new ImageIcon(ResultatAnalisisView.class
				.getResource("/images/pdf-icon.png")));
		pdfButton.addActionListener(crearPdfAction);
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
		cpuMax = new JLabel("M\u00E0xim: ");
		cpuMax.setFont(font);

		estatCPU = new JPanel();
		estatCPU.setMaximumSize(new Dimension(32, 32));
		estatCPU.setAlignmentX(Component.LEFT_ALIGNMENT);
		estatCPU.setOpaque(false);
		panelCPU.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelCPU.add(cpuMax);
		cpuMin = new JLabel("M\u00EDnim: ");
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
		ramMax = new JLabel("M\u00E0xim: ");
		ramMax.setFont(font);

		estatRAM = new JPanel();
		estatRAM.setMaximumSize(new Dimension(32, 32));
		estatRAM.setOpaque(false);
		panelRAM.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelRAM.add(ramMax);
		ramMin = new JLabel("M\u00EDnim: ");
		ramMin.setFont(font);
		panelRAM.add(ramMin);
		panelRAM.add(ramAvg);
		panelRAM.add(estatRAM);

		panelHDD = new JPanel();
		panelHDD.setLocale(new Locale("ca"));
		panelHDD.setAlignmentY(Component.TOP_ALIGNMENT);
		panelHDD.setPreferredSize(new Dimension(615, 40));
		panelHDD.setBackground(Color.WHITE);
		panelHDD.setBorder(new TitledBorder(null, "Disc Dur",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		hddAvg = new JLabel("Mitjana: ");
		hddAvg.setFont(font);

		estatHDD = new JPanel();
		estatHDD.setMaximumSize(new Dimension(32, 32));
		estatHDD.setOpaque(false);
		FlowLayout fl_panelHDD = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panelHDD.setLayout(fl_panelHDD);
		hddMax = new JLabel("M\u00E0xim: ");
		hddMax.setFont(font);
		panelHDD.add(hddMax);
		hddMin = new JLabel("M\u00EDnim: ");
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
		netMax = new JLabel("M\u00E0xim: ");
		netMax.setFont(font);

		estatNET = new JPanel();
		estatNET.setMaximumSize(new Dimension(32, 32));
		estatNET.setOpaque(false);
		panelNET.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelNET.add(netMax);
		netMin = new JLabel("M\u00EDnim: ");
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
		resultat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		resultat.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a = JOptionPane
						.showOptionDialog(
								null,
								"Vol sortir de l'aplicació? És obligatori guardar el resultat, en cas"
										+ " de no haver-ho fet serà redirigit a la pantalla de guardar resultat",
								"", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, opcions,
								opcions[0]);
				if (a == 0) {
					if (!pdfCreat) {
						fileChooser();
						if (pdfCreat)
							System.exit(0);
					} else
						System.exit(0);
				}
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
	 * Afegir dades valoracio.
	 * 
	 * @param doc
	 *            the doc
	 * @param idIcon
	 *            the id icon
	 * @param component
	 *            the component
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DocumentException
	 *             the document exception
	 */
	private void afegirDadesValoracio(Document doc, int idIcon,
			Component component) throws MalformedURLException, IOException,
			DocumentException {
		String icon = "";
		com.itextpdf.text.Image i;
		switch (idIcon) {
		case 0:
			icon = "/images/ko-icon.png";
			break;
		case 1:
			icon = "/images/equal-icon.png";
			break;
		case 2:
			icon = "/images/ok-icon.png";
			break;
		}
		i = com.itextpdf.text.Image.getInstance(this.getClass().getResource(
				icon));
		Paragraph p = new Paragraph();
		JLabel l = (JLabel) component;
		String text = l.getToolTipText().replaceAll("<html>", "");
		text = text.replace("<br>", "");
		p.add(new Chunk(i, 0, 0));
		p.add(new Phrase(text));
		doc.add(new Paragraph("  "));
		doc.add(p);
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
				"Percentatge d'ús (%)*", dataset, true, true, false);
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
	 * Crear pdf.
	 * 
	 * @param chart
	 *            the chart
	 * @param fileName
	 *            the file name
	 * @throws DocumentException
	 *             the document exception
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void crearPDF(JFreeChart chart, String fileName)
			throws DocumentException, MalformedURLException, IOException {
		Document doc = new Document(PageSize.A4);
		if (!fileName.endsWith(".pdf"))
			fileName += ".pdf";
		PdfWriter docWriter = PdfWriter.getInstance(doc, new FileOutputStream(
				fileName));
		doc.addTitle("Resultat de l'anàlisi de l'ordinador");
		doc.addAuthor("Oriol Gasset Romo");
		doc.addSubject("Resultat de l'anàlisi de l'ordinador");
		doc.addKeywords("Anàlisi, PDF, rendiment");
		doc.addCreator("Anàlisi del rendiment de PCs");

		doc.setPageSize(PageSize.A4);
		doc.open();

		/** Títol del pdf */
		Chunk title = new Chunk("Resultat de l'anàlisi");
		title.setFont(FontFactory.getFont(FontFactory.HELVETICA, 34,
				Font.CENTER_BASELINE));
		title.setUnderline(0.1f, -2f);
		Paragraph paragraf = new Paragraph(title);
		paragraf.setAlignment(Element.ALIGN_CENTER);
		doc.add(paragraf);
		doc.add(new Paragraph("   "));
		String s = "";
		if (!MainController.analisisController.getDuracio().equals("-1")) {
			s = MainController.analisisController.getDuracio();
		} else {
			if (MainController.analisisController.getDuracioRestant() == 0) {
				s = MainController.view.panel.getTempsLabel();
			} else {
				s = MainController.analisisController.getDuracioParcial();
			}
		}
		doc.add(new Paragraph("Duració de l'anàlisis: " + s));
		MainController.analisisController.setDuracio(s);
		doc.add(new Paragraph("Informació de l'anàlisi:"));
		doc.add(new Paragraph(MainController.analisisController.getIdPC()));
		/** Info CPU */
		if (ViewOpcionsController.isCpu()) {
			Chunk cpuTitle = new Chunk("Ús de la CPU");
			cpuTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
					Font.BOLD));
			cpuTitle.setUnderline(0.1f, -2f);
			Paragraph cpuParagraf = new Paragraph(cpuTitle);
			doc.add(cpuParagraf);
			doc.add(new Paragraph(MainController.analisisController
					.getInfoComponent("CPU")));
			doc.add(new Paragraph(cpuAvg.getText() + " " + cpuMax.getText()
					+ " " + cpuMin.getText()));
			afegirDadesValoracio(doc, cpuOk, estatCPU.getComponent(0));
			doc.add(new Paragraph("   "));
		}
		/** Info RAM */
		if (ViewOpcionsController.isRam()) {
			Chunk ramTitle = new Chunk("Ús de la memòria RAM");
			ramTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
					Font.BOLD));
			ramTitle.setUnderline(0.1f, -2f);
			Paragraph ramParagraf = new Paragraph(ramTitle);
			doc.add(ramParagraf);
			doc.add(new Paragraph(MainController.analisisController
					.getInfoComponent("RAM") + "MB"));
			doc.add(new Paragraph(ramAvg.getText() + " " + ramMax.getText()
					+ " " + ramMin.getText()));
			afegirDadesValoracio(doc, ramOk, estatRAM.getComponent(0));
			doc.add(new Paragraph("   "));
		}
		/** Info HDD */
		if (ViewOpcionsController.isHdd()) {
			Chunk hddTitle = new Chunk("Ús del disc dur");
			hddTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
					Font.BOLD));
			hddTitle.setUnderline(0.1f, -2f);
			Paragraph hddParagraf = new Paragraph(hddTitle);
			doc.add(hddParagraf);
			doc.add(new Paragraph(MainController.analisisController
					.getInfoComponent("HDD")));
			doc.add(new Paragraph(hddAvg.getText() + " " + hddMax.getText()
					+ " " + hddMin.getText()));
			afegirDadesValoracio(doc, hddOk, estatHDD.getComponent(0));
			doc.add(new Paragraph("   "));
		}
		/** Info NET */
		if (ViewOpcionsController.isNet()) {
			Chunk netTitle = new Chunk("Ús de la xarxa");
			netTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18,
					Font.BOLD));
			netTitle.setUnderline(0.1f, -2f);
			Paragraph netParagraf = new Paragraph(netTitle);
			doc.add(netParagraf);
			doc.add(new Paragraph(MainController.analisisController
					.getInfoComponent("NET")));
			doc.add(new Paragraph(netAvg.getText() + " " + netMax.getText()
					+ " " + netMin.getText()));
			afegirDadesValoracio(doc, netOk, estatNET.getComponent(0));
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
				"Percentatge d'ús (%)*", sc, true, true, false);
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
		MainController.analisisController.guardarAnalisi(fileName, s);
		pdfCreat = true;
	}

	protected void fileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File(System
				.getProperty("user.dir")));
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setDialogTitle("Guardar");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (chooser.showSaveDialog(pdfButton) == JFileChooser.APPROVE_OPTION) {
			try {
				crearPDF(grafica, chooser.getSelectedFile().toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public boolean isPdfCreat() {
		return pdfCreat;
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
			valoracioResultatCpu(cpuStats);
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
			valoracioResultatHdd(hddStats);
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
			valoracioResultatNet(netStats);

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
			valoracioResultatRam(ramStats);
		}
	}

	public void setPdfCreat(boolean pdfCreat) {
		this.pdfCreat = pdfCreat;
	}

	/**
	 * Valoracio resultat cpu.
	 * 
	 * @param cpuStats
	 *            the cpu stats
	 */
	private void valoracioResultatCpu(Float[] cpuStats) {
		JLabel a = new JLabel();
		String s = "";
		if (cpuStats[0] > 75) {
			a.setIcon(koIcon);
			s = "<html> L'ús mitjà de la CPU és molt elevat. Si s'estan executant moltes aplicacions conscientment <br>"
					+ " al mateix temps es recomana canviar el processador. Altrament caldria comprovar els processos <br>"
					+ "que s'estan executant al sistema per eliminar aquells que siguin innecessaris <html>";
			estatCPU.add(a);
			cpuOk = 0;
		} else if (cpuStats[0] > 40) {
			a.setIcon(equalIcon);
			s = "<html> L'ús mitjà de la CPU és superior al 50%. El processador funciona correctament, pero si <br>"
					+ " l'ús que se n'està fent és mínim és possible que hi hagi aplicacions en segon terme consumin recursos <html>";
			cpuOk = 1;
		} else {
			a.setIcon(okIcon);
			s = "L'ús del processador és correcte";
			cpuOk = 2;
		}
		a.setToolTipText(s);
		estatCPU.add(a);
	}

	/**
	 * Valoracio resultat hdd.
	 * 
	 * @param hddStats
	 *            the hdd stats
	 */
	private void valoracioResultatHdd(float[] hddStats) {
		JLabel a = new JLabel();
		String s = "";
		if (hddStats[0] > 30) {
			a.setIcon(koIcon);
			s = "<html> L'ús del disc dur és força elevat. S'estan escribint i llegint moltes dades i de forma constant. Si s'han <br>"
					+ "estat transferint dades des d'un dispositiu extern o des de la xarxa repetidament durant l'anàlisis <br>"
					+ "el resultat es pot donar per bo. <html>";
			hddOk = 0;
		} else if (hddStats[0] > 20) {
			a.setIcon(equalIcon);
			s = "L'ús del disc dur és correcte, encara que s'han realitzar forçes escriptures i lectures.";
			hddOk = 1;
		} else {
			a.setIcon(okIcon);
			s = "<html> L'ús del disc dur és correcte. <html>";
			hddOk = 2;
		}
		if (hddStats[1] > 20) {
			s += "<html> S'ha produit un pic de dades llegides/escrites força alt. Si la mitjana queda molt<br> "
					+ "elevada pot ser degut a un pic concret d'ús del disc dur. S'hauria de comprovar la gràfica per assegurar-se. <html>";
		}
		a.setToolTipText(s);
		estatHDD.add(a);
	}

	/**
	 * Valoracio resultat net.
	 * 
	 * @param netStats
	 *            the net stats
	 */
	private void valoracioResultatNet(Float[] netStats) {
		JLabel a = new JLabel();
		String s = "";
		if (netStats[0] > 20) {
			a.setIcon(koIcon);
			s = "<html> L'ús de la targeta de xarxa és molt elevat. Si l'ordinador està constanment enviant i rebent <br>"
					+ "quantitats molt elevades de dades el resultat es pot considerar bo. Si, per altra banda, l'ús que <br>"
					+ "se li dóna al PC no és principalment aquest, és possible que la targeta de xarxa no suporti <br>"
					+ " l'ample de banda que requereix l'ús de l'ordinador o que hi hagi aplicacions en sogon terme enviant <br>"
					+ "i reben dades constanment.<html>";
			netOk = 0;
		} else if (netStats[0] > 10) {
			a.setIcon(equalIcon);
			s = "<html> L'ús de la targeta xarxa és força elevat. Si l'ordinador està constanment enviant i rebent <br>"
					+ "quantitats molt elevades de dades el resultat es pot considerar bo. També pot ser degut a que en <br>"
					+ " l'execució de l'anàlisi s'hagin consumit molts recursos d'internet, com descàrregues, visualització de <br>"
					+ "contingut multimèdia online, etc. <html>";
			netOk = 1;
		} else {
			a.setIcon(okIcon);
			s = "L'ús de la targeta de xarxa és correcte.";
			netOk = 2;
		}
		a.setToolTipText(s);
		estatNET.add(a);
	}

	/**
	 * Valoracio resultat ram.
	 * 
	 * @param ramStats
	 *            the ram stats
	 */
	private void valoracioResultatRam(Float[] ramStats) {
		JLabel a = new JLabel();
		String s = "";
		if (ramStats[0] > 75) {
			a.setIcon(koIcon);
			s = "<html> L'ús de la memòria RAM és molt elevat. Si s'han estat executant aplicacions <br>"
					+ " que requereixen molta memòria durant l'anàlisis el resultat no és destacable. Si, per altra banda, <br>"
					+ "no s'han executat gaires aplicacions durant l'anàlisi vol dir que hi ha processos consumin <br>"
					+ "molta memòria o bé que la memòria no té la suficient capacitat per executar les aplicacions del <br>"
					+ "PC. <html>";

			ramOk = 0;
		} else if (ramStats[0] > 40) {
			a.setIcon(equalIcon);
			s = "<html> L'ús de la memòria RAM és l'esperat en un ús habitual de l'ordinador. La major part del consum es <br>"
					+ "deu a processos del sistema i, les aplicaions que s'utilitzen no consumeixen excessiva memòria. <br>"
					+ " En en cas que no s'hagi estat utilitzant l'ordinador durant l'anàlisi, és possible que <br>"
					+ "hi hagi algunes aplicacions executan-se en segon terme que consumeixen recursos. <html>";
			ramOk = 1;
		} else {
			a.setIcon(okIcon);
			s = "<html> L'ús de la memòria és força baix. Això es pot deure a que es disposa de molta memòria o que no s'estiguin <br>"
					+ " executant gaires aplicacions. <html>";
			ramOk = 2;
		}
		if (ramStats[6] < 3800)
			s += "<html> La memòria RAM del sistema és: "
					+ ramStats[6]
					+ " . Es recomana com a mínim <br>"
					+ "una memòria de 4096 MB i, en cas d'utilitzar programes que consumeixen molts recursos, una de 8192 MB. <html>";
		a.setToolTipText(s);
		estatRAM.add(a);
	}
}
