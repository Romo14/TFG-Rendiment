package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

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

import javax.swing.BoxLayout;

public class ResultatAnalisisView extends JPanel {

	private static final long serialVersionUID = 9073846157870757887L;
	private JButton btnInici;
	private JCheckBox chckbxCpu;
	private JCheckBox chckbxDiscDur;
	private JCheckBox chckbxGpu;
	private JCheckBox chckbxRam;
	private JCheckBox chckbxXarxa;
	private TimeSeries cpu;
	private JLabel cpuAvg;
	private JLabel cpuMax;
	private JLabel cpuMin;
	private TimeSeriesCollection dataset;
	private JPanel estatCPU;
	private JPanel estatGPU;
	private JPanel estatRAM;
	private Font font = new Font(getFont().getName(), getFont().getStyle(), 16);
	private TimeSeries gpu;
	private JLabel gpuAvg;
	private JLabel gpuMax;
	private JLabel gpuMin;
	private JFreeChart grafica;
	private ChartPanel graficaPanel;
	private TimeSeries hdd;
	private JLabel hddAvg;
	private JLabel hddMax;
	private JLabel hddMin;
	private ImageIcon koIcon;
	private String koText = "El dispositiu té una mitjana d'ús de més del 75%, pel que es pot determinar que és necessari revisar";
	private TimeSeries net;
	private JLabel netAvg;
	private JLabel netMax;
	private JLabel netMin;
	private ImageIcon okIcon;
	private String okText = "El dispositiu funciona correctament i aguanta perfectament la càrrega de treball que s'hi realitza";
	private JPanel panelCPU;
	private JPanel panelDadesGenerals;
	private JPanel panelGPU;
	private JPanel panelGrafiques;
	private JPanel panelHDD;
	private JPanel panelNET;
	private JPanel panelRAM;
	private JButton pdfButton;
	private TimeSeries ram;
	private JLabel ramAvg;
	private JLabel ramMax;
	private JLabel ramMin;
	private JFrame resultat;
	private JTabbedPane tabbedPane;

	public ResultatAnalisisView() {
		resultat = new JFrame("Resultats de l'anàlisi");
		resultat.setResizable(false);
		resultat.getContentPane().setBackground(Color.WHITE);
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		resultat.setIconImage(img);
		resultat.setSize(new Dimension(697, 430));
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
		tabbedPane.addTab("Gràfica", panelGrafiques);
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		chckbxGpu = new JCheckBox("GPU");
		chckbxGpu.setSelected(true);
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
				int a = JOptionPane.showConfirmDialog(null,
						"Vols tornar al menú principal?", "",
						JOptionPane.YES_NO_OPTION);
				if (a == 0) {
					MainController.main(null);
					resultat.dispose();
				}
			}
		});
		pdfButton = new JButton("");
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
		pdfButton.setBorder(null);
		pdfButton.setContentAreaFilled(false);
		pdfButton.setIcon(new ImageIcon(ResultatAnalisisView.class
				.getResource("/images/pdf-icon.png")));

		graficaPanel = new ChartPanel(grafica);
		graficaPanel.setBackground(Color.WHITE);
		GroupLayout gl_panelGrafiques = new GroupLayout(panelGrafiques);
		gl_panelGrafiques
				.setHorizontalGroup(gl_panelGrafiques
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelGrafiques
										.createSequentialGroup()
										.addGap(7)
										.addComponent(graficaPanel,
												GroupLayout.PREFERRED_SIZE,
												529, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
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
																								Alignment.LEADING)
																						.addGroup(
																								gl_panelGrafiques
																										.createSequentialGroup()
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addGroup(
																												gl_panelGrafiques
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																chckbxCpu)
																														.addComponent(
																																chckbxGpu)
																														.addComponent(
																																chckbxRam)
																														.addComponent(
																																chckbxDiscDur)
																														.addComponent(
																																chckbxXarxa))
																										.addPreferredGap(
																												ComponentPlacement.RELATED))
																						.addGroup(
																								gl_panelGrafiques
																										.createSequentialGroup()
																										.addGap(18)
																										.addComponent(
																												pdfButton)))
																		.addGap(19))
														.addGroup(
																gl_panelGrafiques
																		.createSequentialGroup()
																		.addComponent(
																				btnInici,
																				GroupLayout.PREFERRED_SIZE,
																				39,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		gl_panelGrafiques
				.setVerticalGroup(gl_panelGrafiques
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelGrafiques
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_panelGrafiques
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																Alignment.LEADING,
																gl_panelGrafiques
																		.createSequentialGroup()
																		.addGap(41)
																		.addComponent(
																				chckbxCpu)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				chckbxGpu)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				chckbxRam)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				chckbxDiscDur)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				chckbxXarxa)
																		.addGap(29)
																		.addComponent(
																				pdfButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnInici,
																				GroupLayout.PREFERRED_SIZE,
																				41,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																graficaPanel,
																GroupLayout.PREFERRED_SIZE,
																356,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(72, Short.MAX_VALUE)));
		panelGrafiques.setLayout(gl_panelGrafiques);
		panelDadesGenerals = new JPanel();
		panelDadesGenerals.setBackground(Color.WHITE);
		tabbedPane.addTab("Dades Generals", panelDadesGenerals);
		panelCPU = new JPanel();
		panelCPU.setPreferredSize(new Dimension(619, 67));
		panelCPU.setBackground(Color.WHITE);
		panelCPU.setBorder(new TitledBorder(null, "CPU", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		resultat.getContentPane().setBackground(Color.WHITE);
		cpuAvg = new JLabel("Mitjana: ");
		cpuAvg.setAlignmentX(Component.CENTER_ALIGNMENT);
		cpuAvg.setFont(font);
		cpuMax = new JLabel("M\u00E0xim:  ");
		cpuMax.setAlignmentX(Component.CENTER_ALIGNMENT);
		cpuMax.setFont(font);
		cpuMin = new JLabel("M\u00EDnim:   ");
		cpuMin.setToolTipText("");
		cpuMin.setAlignmentX(Component.CENTER_ALIGNMENT);
		cpuMin.setFont(font);

		estatCPU = new JPanel();
		estatCPU.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) estatCPU.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCPU.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelCPU.add(cpuMin);
		panelCPU.add(cpuMax);
		panelCPU.add(cpuAvg);
		panelCPU.add(estatCPU);

		panelGPU = new JPanel();
		panelGPU.setPreferredSize(new Dimension(619, 67));
		panelGPU.setBackground(Color.WHITE);
		panelGPU.setBorder(new TitledBorder(null, "GPU", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		gpuAvg = new JLabel("Mitjana: ");
		gpuAvg.setFont(font);
		gpuMax = new JLabel("M\u00E0xim:  ");
		gpuMax.setFont(font);
		gpuMin = new JLabel("M\u00EDnim:   ");
		gpuMin.setFont(font);

		estatGPU = new JPanel();
		estatGPU.setOpaque(false);
		panelGPU.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelGPU.add(gpuMin);
		panelGPU.add(gpuAvg);
		panelGPU.add(gpuMax);
		panelGPU.add(estatGPU);

		panelRAM = new JPanel();
		panelRAM.setPreferredSize(new Dimension(619, 67));
		panelRAM.setBackground(Color.WHITE);
		panelRAM.setBorder(new TitledBorder(null, "RAM", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		ramAvg = new JLabel("Mitjana: ");
		ramAvg.setFont(font);
		ramMax = new JLabel("M\u00E0xim:  ");
		ramMax.setFont(font);
		ramMin = new JLabel("M\u00EDnim:   ");
		ramMin.setFont(font);

		estatRAM = new JPanel();
		estatRAM.setOpaque(false);
		panelRAM.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelRAM.add(ramMax);
		panelRAM.add(ramAvg);
		panelRAM.add(ramMin);
		panelRAM.add(estatRAM);

		panelHDD = new JPanel();
		panelHDD.setPreferredSize(new Dimension(619, 67));
		panelHDD.setBackground(Color.WHITE);
		panelHDD.setBorder(new TitledBorder(null, "Disc Dur",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		hddAvg = new JLabel("Mitjana: ");
		hddAvg.setFont(font);
		hddMin = new JLabel("M\u00EDnim:   ");
		hddMin.setFont(font);

		JPanel estatHDD = new JPanel();
		estatHDD.setOpaque(false);
		panelHDD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelHDD.add(hddAvg);
		panelHDD.add(hddMin);
		hddMax = new JLabel("M\u00E0xim:  ");
		hddMax.setFont(font);
		panelHDD.add(hddMax);
		panelHDD.add(estatHDD);

		panelNET = new JPanel();
		panelNET.setPreferredSize(new Dimension(619, 67));
		panelNET.setBackground(Color.WHITE);
		panelNET.setBorder(new TitledBorder(null, "Xarxa",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		netAvg = new JLabel("Mitjana: ");
		netAvg.setFont(font);
		netMax = new JLabel("M\u00E0xim:  ");
		netMax.setFont(font);
		netMin = new JLabel("M\u00EDnim:   ");
		netMin.setFont(font);

		JPanel estatNET = new JPanel();
		estatNET.setOpaque(false);
		panelNET.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelNET.add(netMax);
		panelNET.add(netAvg);
		panelNET.add(netMin);
		panelNET.add(estatNET);
		panelDadesGenerals.setLayout(new BoxLayout(panelDadesGenerals,
				BoxLayout.Y_AXIS));
		panelDadesGenerals.add(panelCPU);
		panelDadesGenerals.add(panelGPU);
		panelDadesGenerals.add(panelRAM);
		panelDadesGenerals.add(panelHDD);
		panelDadesGenerals.add(panelNET);
		GroupLayout groupLayout = new GroupLayout(resultat.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE,
								668, Short.MAX_VALUE).addGap(0)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 401, Short.MAX_VALUE));
		resultat.getContentPane().setLayout(groupLayout);
		resultat.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		mostraResultats();
		addCheckBoxListeners();
	}

	private void actualitzaColors() {
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
		renderer.setSeriesPaint(0, Color.BLUE);
		renderer.setSeriesPaint(1, Color.RED);
		renderer.setSeriesPaint(2, Color.GREEN);
		renderer.setSeriesPaint(3, Color.YELLOW);
		renderer.setSeriesPaint(4, Color.CYAN);
		XYPlot p = grafica.getXYPlot();
		p.setRenderer(renderer);
	}

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
		chckbxGpu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxGpu.isSelected()) {
					dataset.addSeries(gpu);
				} else {
					dataset.removeSeries(gpu);
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

	public JFreeChart crearGrafica() {
		dataset = new TimeSeriesCollection();
		if (ViewOpcionsController.isCpu()) {
			cpu = MainController.analisisController.getEvol("CPU");
			dataset.addSeries(cpu);
		}
		if (ViewOpcionsController.isGpu()) {
			gpu = MainController.analisisController.getEvol("GPU");
			dataset.addSeries(gpu);
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
				"Us dels components", "Temps (segons)", "Percentatge d'ús (%)",
				dataset);
		lineChartObject.setBackgroundPaint(Color.WHITE);
		return lineChartObject;
	}

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
			} else {
				a.setIcon(okIcon);
				a.setToolTipText(okText);
				estatCPU.add(a);
			}
		}
		if (!ViewOpcionsController.isGpu()) {
			chckbxGpu.setVisible(false);
			panelGPU.setVisible(false);
		}
		if (!ViewOpcionsController.isHdd()) {
			chckbxDiscDur.setVisible(false);
			panelHDD.setVisible(false);
		} else {
			Float[] hddStats = MainController.analisisController.getHddInfo();
			hddAvg.setText(cpuAvg.getText() + df.format(hddStats[0]) + "%");
			hddMax.setText(cpuMax.getText() + df.format(hddStats[1]) + "%");
			hddMin.setText(cpuMin.getText() + df.format(hddStats[2]) + "%");
			JLabel a = new JLabel();
			if (hddStats[0] > 70) {
				a.setIcon(koIcon);
				a.setToolTipText(koText);
				estatRAM.add(a);
			} else {
				a.setIcon(okIcon);
				a.setToolTipText(okText);
				estatRAM.add(a);
			}
		}
		if (!ViewOpcionsController.isNet()) {
			chckbxXarxa.setVisible(false);
			panelNET.setVisible(false);
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
			} else {
				a.setIcon(okIcon);
				a.setToolTipText(okText);
				estatRAM.add(a);
			}
		}
	}

	public void writeChartToPDF(JFreeChart chart, String fileName) {
		// Document document = new Document();
		// PdfWriter writer;
		// try {
		// writer = PdfWriter.getInstance(document, new FileOutputStream(
		// fileName + ".pdf"));
		// document.open();
		// PdfContentByte cb = writer.getDirectContent();
		// float width = PageSize.A4.getWidth()- 20;
		// float height = PageSize.A4.getHeight() / 2;
		// PdfTemplate bar = cb.createTemplate(width, height);
		// Graphics2D g2d2 = new PdfGraphics2D(bar, width, height);
		// Rectangle2D r2d2 = new Rectangle2D.Double(0, 0, width, height);
		// grafica.draw(g2d2, r2d2);
		// g2d2.dispose();
		// cb.addTemplate(bar, 0, 0);
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (DocumentException e) {
		// e.printStackTrace();
		// }
		// document.close();

		// try {
		// report()
		// .setTemplate(Templates.reportTemplate)
		// .columns(orderDateColumn, quantityColumn, priceColumn)
		// .title(Templates.createTitleComponent("TimeSeriesChart"))
		// .summary(
		// cht.timeSeriesChart()
		// .setTitle("Time series chart")
		// .setTitleFont(boldFont)
		// .setTimePeriod(orderDateColumn)
		// .setTimePeriodType(TimePeriod.MONTH)
		// .series(
		// cht.serie(quantityColumn), cht.serie(priceColumn))
		// .setTimeAxisFormat(
		// cht.axisFormat().setLabel("Date")))
		// .pageFooter(Templates.footerComponent)
		// .setDataSource(createDataSource())
		// .show();
		// } catch (DRException e) {
		// e.printStackTrace();
		// }

	}
}
