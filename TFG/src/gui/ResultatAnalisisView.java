package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.Component;

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
	private JButton btnInici;
	private JButton pdfButton;
	private JPanel panelDadesGenerals;
	private ChartPanel graficaPanel;
	private JLabel ramAvg;
	private JLabel ramMax;
	private JLabel ramMin;
	private JLabel netAvg;
	private JLabel netMax;
	private JLabel netMin;
	private JLabel gpuAvg;
	private JLabel gpuMax;
	private JLabel gpuMin;
	private JLabel cpuAvg;
	private JLabel cpuMax;
	private JLabel cpuMin;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	public ResultatAnalisisView() {
		resultat = new JDialog(MainController.view.getOwner(),
				"Resultats de l'anàlisi");
		Image img = new ImageIcon(this.getClass().getResource(
				"/images/app-icon.png")).getImage();
		resultat.setIconImage(img);
		resultat.setSize(new Dimension(650, 430));
		resultat.setResizable(false);
		resultat.setLocationRelativeTo(null);

		ok = new JLabel(new ImageIcon(this.getClass().getResource(
				"/images/ok-icon.png")));
		ko = new JLabel(new ImageIcon(this.getClass().getResource(
				"/images/ko-icon.png")));

		grafica = crearGrafica();
		ImageIcon icon = new ImageIcon("/images/ok-icon.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100,
				BufferedImage.SCALE_SMOOTH));
		resultat.setVisible(true);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setAlignmentY(Component.TOP_ALIGNMENT);
		tabbedPane.setAlignmentX(Component.RIGHT_ALIGNMENT);

		panelGrafiques = new JPanel();
		panelGrafiques.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelGrafiques.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelGrafiques.setMaximumSize(new Dimension(100, 100));
		tabbedPane.addTab("Gràfica", panelGrafiques);
		
		chckbxGpu = new JCheckBox("GPU");
		chckbxRam = new JCheckBox("RAM");
		chckbxDiscDur = new JCheckBox("Disc Dur");
		chckbxXarxa = new JCheckBox("Xarxa");
		chckbxCpu = new JCheckBox("CPU");
		btnInici = new JButton();
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
				Date t = new Date();
				System.out.println(t);
				writeChartToPDF(grafica, 500, 400, String.valueOf(t) + ".pdf");
			}
		});
		pdfButton.setBorder(null);
		pdfButton.setContentAreaFilled(false);
		pdfButton.setIcon(new ImageIcon(ResultatAnalisisView.class
				.getResource("/images/pdf-icon.png")));

		graficaPanel = new ChartPanel(grafica);
		GroupLayout gl_panelGrafiques = new GroupLayout(panelGrafiques);
		gl_panelGrafiques.setHorizontalGroup(
			gl_panelGrafiques.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGrafiques.createSequentialGroup()
					.addGap(7)
					.addComponent(graficaPanel, GroupLayout.PREFERRED_SIZE, 529, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelGrafiques.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxCpu)
						.addComponent(chckbxGpu)
						.addComponent(chckbxRam)
						.addComponent(chckbxDiscDur)
						.addGroup(gl_panelGrafiques.createParallelGroup(Alignment.TRAILING)
							.addComponent(chckbxXarxa)
							.addGroup(gl_panelGrafiques.createParallelGroup(Alignment.LEADING)
								.addComponent(pdfButton)
								.addComponent(btnInici, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_panelGrafiques.setVerticalGroup(
			gl_panelGrafiques.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGrafiques.createSequentialGroup()
					.addGroup(gl_panelGrafiques.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelGrafiques.createSequentialGroup()
							.addGap(50)
							.addComponent(chckbxCpu)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxGpu)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxRam)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxDiscDur)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxXarxa))
						.addGroup(gl_panelGrafiques.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panelGrafiques.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelGrafiques.createSequentialGroup()
									.addComponent(pdfButton)
									.addGap(18)
									.addComponent(btnInici, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addComponent(graficaPanel, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(153, Short.MAX_VALUE))
		);
		panelGrafiques.setLayout(gl_panelGrafiques);

		panelDadesGenerals = new JPanel();
		tabbedPane.addTab("Dades Generals", null, panelDadesGenerals, null);

		panelCPU = new JPanel();
		panelCPU.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelCPU.setBorder(new TitledBorder(null, "CPU", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		panelGPU = new JPanel();
		panelGPU.setAlignmentX(Component.LEFT_ALIGNMENT);
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
					.addGroup(gl_panelDadesGenerals.createParallelGroup(Alignment.LEADING)
						.addComponent(panelRAM, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelHDD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelCPU, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
						.addComponent(panelGPU, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
						.addComponent(panelNET, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelDadesGenerals.setVerticalGroup(
			gl_panelDadesGenerals.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDadesGenerals.createSequentialGroup()
					.addComponent(panelCPU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelGPU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelRAM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelHDD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelNET, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(150, Short.MAX_VALUE))
		);

		JLabel hddAvg = new JLabel("Mitjana: ");
		hddAvg.setFont(font);
		JLabel hddMax = new JLabel("M\u00E0xim:  ");
		hddMax.setFont(font);
		JLabel hddMin = new JLabel("M\u00EDnim:   ");
		hddMin.setFont(font);

		JLabel estatHDD = new JLabel("");

		JPanel panel_2 = new JPanel();
		panelHDD.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelHDD.add(hddAvg);
		panelHDD.add(hddMin);
		panelHDD.add(hddMax);
		panelHDD.add(estatHDD);
		panelHDD.add(panel_2);

		ramAvg = new JLabel("Mitjana: ");
		ramAvg.setFont(font);
		ramMax = new JLabel("M\u00E0xim:  ");
		ramMax.setFont(font);
		ramMin = new JLabel("M\u00EDnim:   ");
		ramMin.setFont(font);

		JPanel estatRAM = new JPanel();
		panelRAM.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelRAM.add(ramMax);
		panelRAM.add(ramAvg);
		panelRAM.add(ramMin);
		panelRAM.add(estatRAM);

		netAvg = new JLabel("Mitjana: ");
		netAvg.setFont(font);
		netMax = new JLabel("M\u00E0xim:  ");
		netMax.setFont(font);
		netMin = new JLabel("M\u00EDnim:   ");
		netMin.setFont(font);

		JLabel estatNET = new JLabel("");

		JPanel panel_3 = new JPanel();
		panelNET.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelNET.add(estatNET);
		panelNET.add(netMax);
		panelNET.add(netAvg);
		panelNET.add(netMin);
		panelNET.add(panel_3);

		gpuAvg = new JLabel("Mitjana: ");
		gpuAvg.setFont(font);
		gpuMax = new JLabel("M\u00E0xim:  ");
		gpuMax.setFont(font);
		gpuMin = new JLabel("M\u00EDnim:   ");
		gpuMin.setFont(font);

		JLabel estatGPU = new JLabel("");

		JPanel panel_1 = new JPanel();
		panelGPU.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelGPU.add(gpuMin);
		panelGPU.add(estatGPU);
		panelGPU.add(gpuAvg);
		panelGPU.add(gpuMax);
		panelGPU.add(panel_1);

		cpuAvg = new JLabel("Mitjana: ");
		cpuAvg.setAlignmentX(Component.CENTER_ALIGNMENT);
		cpuAvg.setFont(font);
		cpuMax = new JLabel("M\u00E0xim:  ");
		cpuMax.setAlignmentX(Component.CENTER_ALIGNMENT);
		cpuMax.setFont(font);
		cpuMin = new JLabel("M\u00EDnim:   ");
		cpuMin.setAlignmentX(Component.CENTER_ALIGNMENT);
		cpuMin.setFont(font);

		JLabel estatCPU = new JLabel("");

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCPU.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panelCPU.add(cpuMin);
		panelCPU.add(estatCPU);
		panelCPU.add(cpuMax);
		panelCPU.add(cpuAvg);
		panelCPU.add(panel);
		panelDadesGenerals.setLayout(gl_panelDadesGenerals);
		GroupLayout groupLayout = new GroupLayout(resultat.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE,
								641, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE,
								470, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(163, Short.MAX_VALUE)));
		resultat.getContentPane().setLayout(groupLayout);
		mostraResultats();
	}

	private void mostraResultats() {
		if (!ViewOpcionsController.isCpu()) {
			chckbxCpu.setVisible(false);
			panelCPU.setVisible(false);
		} else {
			String[] cpuStats = MainController.analisisController.getCpuInfo();
			cpuAvg.setText(cpuAvg.getText() + cpuStats[0]);
			cpuMax.setText(cpuMax.getText() + cpuStats[1]);
			cpuMin.setText(cpuMin.getText() + cpuStats[2]);
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
		} else {
			String[] ramStats = MainController.analisisController.getRamInfo();
			ramAvg.setText(ramAvg.getText() + ramStats[0]);
			ramMax.setText(ramMax.getText() + ramStats[1]);
			ramMin.setText(ramMin.getText() + ramStats[2]);
		}
	}

	public JFreeChart crearGrafica() {

		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries cpu = new XYSeries("CPU");
		XYSeries gpu = new XYSeries("GPU");
		XYSeries hdd = new XYSeries("Disc dur");
		XYSeries ram = new XYSeries("Memòria RAM");
		XYSeries net = new XYSeries("Xarxa");
		dataset.addSeries(cpu);
		dataset.addSeries(gpu);
		dataset.addSeries(hdd);
		dataset.addSeries(ram);
		dataset.addSeries(net);

		/* Step -2:Define the JFreeChart object to create line chart */
		JFreeChart lineChartObject = ChartFactory.createXYLineChart(
				"Us dels components", "Temps (hores)", "Percentatge d'ús (%)",
				dataset);
		return lineChartObject;
	}

	@SuppressWarnings("deprecation")
	public static void writeChartToPDF(JFreeChart chart, int width, int height,
			String fileName) {
		PdfWriter writer = null;

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					fileName));
			document.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height,
					new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
					height);

			chart.draw(graphics2d, rectangle2d);

			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}
}
