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

    public ResultatAnalisisView() {
	resultat = new JDialog(MainController.view.getOwner(),
		"Resultats de l'anàlisi");
	Image img = new ImageIcon(this.getClass().getResource(
		"/images/app-icon.png")).getImage();
	resultat.setIconImage(img);
	Dimension min = new Dimension(800, 670);
	resultat.setSize(min);
	resultat.setResizable(false);
	resultat.setLocationRelativeTo(null);
	SpringLayout springLayout = new SpringLayout();
	resultat.getContentPane().setLayout(springLayout);

	ok = new JLabel(new ImageIcon(this.getClass().getResource(
		"/images/ok-icon.png")));
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

	panelDadesGenerals = new JPanel();
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
	graficaPanel = new ChartPanel(grafica);
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
	    @SuppressWarnings("deprecation")
	    public void actionPerformed(ActionEvent e) {
		long t = new Date().getDate();
		System.out.println(t);
		writeChartToPDF(grafica, 500, 400, String.valueOf(t) + ".pdf");
	    }
	});
	pdfButton.setBorder(null);
	pdfButton.setContentAreaFilled(false);
	pdfButton.setIcon(new ImageIcon(ResultatAnalisisView.class
		.getResource("/images/pdf-icon.png")));
	GroupLayout gl_panelGrafiques = new GroupLayout(panelGrafiques);
	gl_panelGrafiques
		.setHorizontalGroup(gl_panelGrafiques
			.createParallelGroup(Alignment.TRAILING)
			.addGroup(
				gl_panelGrafiques
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_panelGrafiques
							.createParallelGroup(
								Alignment.TRAILING)
							.addGroup(
								gl_panelGrafiques
									.createSequentialGroup()
									.addGap(35)
									.addComponent(
										chckbxCpu)
									.addGap(18)
									.addComponent(
										chckbxGpu)
									.addGap(18)
									.addComponent(
										chckbxRam)
									.addGap(18)
									.addComponent(
										chckbxDiscDur)
									.addGap(18)
									.addComponent(
										chckbxXarxa)
									.addGap(40))
							.addGroup(
								gl_panelGrafiques
									.createSequentialGroup()
									.addComponent(
										graficaPanel,
										GroupLayout.DEFAULT_SIZE,
										395,
										Short.MAX_VALUE)
									.addContainerGap())
							.addGroup(
								gl_panelGrafiques
									.createSequentialGroup()
									.addGap(286)
									.addComponent(
										pdfButton,
										GroupLayout.PREFERRED_SIZE,
										32,
										GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
										ComponentPlacement.RELATED,
										29,
										Short.MAX_VALUE)
									.addComponent(
										btnInici,
										GroupLayout.PREFERRED_SIZE,
										48,
										GroupLayout.PREFERRED_SIZE)
									.addContainerGap()))));
	gl_panelGrafiques
		.setVerticalGroup(gl_panelGrafiques
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelGrafiques
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(graficaPanel,
						GroupLayout.PREFERRED_SIZE,
						351, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(
						gl_panelGrafiques
							.createParallelGroup(
								Alignment.BASELINE)
							.addComponent(
								chckbxXarxa)
							.addComponent(
								chckbxDiscDur)
							.addComponent(chckbxRam)
							.addComponent(chckbxGpu)
							.addComponent(chckbxCpu))
					.addPreferredGap(
						ComponentPlacement.RELATED,
						107, Short.MAX_VALUE)
					.addGroup(
						gl_panelGrafiques
							.createParallelGroup(
								Alignment.TRAILING,
								false)
							.addComponent(
								pdfButton, 0,
								0,
								Short.MAX_VALUE)
							.addComponent(
								btnInici,
								GroupLayout.PREFERRED_SIZE,
								39,
								Short.MAX_VALUE))
					.addContainerGap()));
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
	gl_panelDadesGenerals
		.setHorizontalGroup(gl_panelDadesGenerals
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_panelDadesGenerals
					.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_panelDadesGenerals
							.createParallelGroup(
								Alignment.TRAILING)
							.addComponent(
								panelNET,
								Alignment.LEADING,
								0, 0,
								Short.MAX_VALUE)
							.addComponent(
								panelHDD,
								Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
							.addComponent(
								panelCPU,
								Alignment.LEADING,
								0, 0,
								Short.MAX_VALUE)
							.addComponent(
								panelGPU,
								Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
							.addComponent(
								panelRAM,
								Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
					.addGap(8)));
	gl_panelDadesGenerals.setVerticalGroup(gl_panelDadesGenerals
		.createParallelGroup(Alignment.LEADING).addGroup(
			gl_panelDadesGenerals
				.createSequentialGroup()
				.addContainerGap()
				.addComponent(panelCPU,
					GroupLayout.PREFERRED_SIZE, 105,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelGPU,
					GroupLayout.PREFERRED_SIZE, 93,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panelRAM,
					GroupLayout.PREFERRED_SIZE, 110,
					GroupLayout.PREFERRED_SIZE)
				.addGap(13)
				.addComponent(panelHDD,
					GroupLayout.PREFERRED_SIZE, 103,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelNET,
					GroupLayout.PREFERRED_SIZE, 101,
					GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE)));

	JLabel hddAvg = new JLabel("Mitjana: ");
	hddAvg.setFont(font);
	JLabel hddMax = new JLabel("M\u00E0xim:  ");
	hddMax.setFont(font);
	JLabel hddMin = new JLabel("M\u00EDnim:   ");
	hddMin.setFont(font);

	JLabel estatHDD = new JLabel("");

	JPanel panel_2 = new JPanel();
	GroupLayout gl_panelHDD = new GroupLayout(panelHDD);
	gl_panelHDD.setHorizontalGroup(
		gl_panelHDD.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelHDD.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelHDD.createParallelGroup(Alignment.TRAILING)
					.addComponent(hddAvg, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addGroup(gl_panelHDD.createSequentialGroup()
						.addGroup(gl_panelHDD.createParallelGroup(Alignment.TRAILING)
							.addComponent(hddMin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
							.addComponent(hddMax, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(estatHDD)))
				.addGap(18)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
	);
	gl_panelHDD.setVerticalGroup(
		gl_panelHDD.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelHDD.createSequentialGroup()
				.addGroup(gl_panelHDD.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelHDD.createSequentialGroup()
						.addGap(8)
						.addComponent(hddAvg)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelHDD.createParallelGroup(Alignment.BASELINE)
							.addComponent(hddMax)
							.addComponent(estatHDD))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(hddMin))
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap())
	);
	panelHDD.setLayout(gl_panelHDD);

	ramAvg = new JLabel("Mitjana: ");
	ramAvg.setFont(font);
	ramMax = new JLabel("M\u00E0xim:  ");
	ramMax.setFont(font);
	ramMin = new JLabel("M\u00EDnim:   ");
	ramMin.setFont(font);

	JPanel estatRAM = new JPanel();
	ImageIcon icon = new ImageIcon("/images/ok-icon.png");
	icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100,
		BufferedImage.SCALE_SMOOTH));
	GroupLayout gl_panelRAM = new GroupLayout(panelRAM);
	gl_panelRAM.setHorizontalGroup(
		gl_panelRAM.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelRAM.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelRAM.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelRAM.createSequentialGroup()
						.addGroup(gl_panelRAM.createParallelGroup(Alignment.LEADING)
							.addComponent(ramMax, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
							.addComponent(ramAvg, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
						.addGap(10))
					.addGroup(gl_panelRAM.createSequentialGroup()
						.addComponent(ramMin, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)))
				.addComponent(estatRAM, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
	);
	gl_panelRAM.setVerticalGroup(
		gl_panelRAM.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelRAM.createSequentialGroup()
				.addGap(6)
				.addGroup(gl_panelRAM.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelRAM.createSequentialGroup()
						.addComponent(ramAvg)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ramMax)
						.addGap(4)
						.addComponent(ramMin))
					.addComponent(estatRAM, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
				.addContainerGap())
	);
	panelRAM.setLayout(gl_panelRAM);

	netAvg = new JLabel("Mitjana: ");
	netAvg.setFont(font);
	netMax = new JLabel("M\u00E0xim:  ");
	netMax.setFont(font);
	netMin = new JLabel("M\u00EDnim:   ");
	netMin.setFont(font);

	JLabel estatNET = new JLabel("");

	JPanel panel_3 = new JPanel();
	GroupLayout gl_panelNET = new GroupLayout(panelNET);
	gl_panelNET.setHorizontalGroup(
		gl_panelNET.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelNET.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelNET.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panelNET.createSequentialGroup()
						.addComponent(estatNET)
						.addGap(99))
					.addGroup(gl_panelNET.createSequentialGroup()
						.addGroup(gl_panelNET.createParallelGroup(Alignment.LEADING)
							.addComponent(netMax, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
							.addComponent(netAvg, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
							.addComponent(netMin, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
						.addGap(18)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())))
	);
	gl_panelNET.setVerticalGroup(
		gl_panelNET.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelNET.createSequentialGroup()
				.addGroup(gl_panelNET.createParallelGroup(Alignment.TRAILING)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
					.addGroup(Alignment.LEADING, gl_panelNET.createSequentialGroup()
						.addComponent(netAvg)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(netMax)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(netMin)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(estatNET)
				.addGap(46))
	);
	panelNET.setLayout(gl_panelNET);

	gpuAvg = new JLabel("Mitjana: ");
	gpuAvg.setFont(font);
	gpuMax = new JLabel("M\u00E0xim:  ");
	gpuMax.setFont(font);
	gpuMin = new JLabel("M\u00EDnim:   ");
	gpuMin.setFont(font);

	JLabel estatGPU = new JLabel("");

	JPanel panel_1 = new JPanel();
	GroupLayout gl_panelGPU = new GroupLayout(panelGPU);
	gl_panelGPU.setHorizontalGroup(
		gl_panelGPU.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelGPU.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelGPU.createParallelGroup(Alignment.LEADING, false)
					.addComponent(gpuMin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(estatGPU, Alignment.TRAILING)
					.addComponent(gpuAvg, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
					.addComponent(gpuMax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
	);
	gl_panelGPU.setVerticalGroup(
		gl_panelGPU.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelGPU.createSequentialGroup()
				.addGroup(gl_panelGPU.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelGPU.createSequentialGroup()
						.addComponent(gpuAvg)
						.addGap(5)
						.addComponent(gpuMax)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(gpuMin)
						.addGap(12)
						.addComponent(estatGPU))
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGap(26))
	);
	panelGPU.setLayout(gl_panelGPU);

	cpuAvg = new JLabel("Mitjana: ");
	cpuAvg.setFont(font);
	cpuMax = new JLabel("M\u00E0xim:  ");
	cpuMax.setFont(font);
	cpuMin = new JLabel("M\u00EDnim:   ");
	cpuMin.setFont(font);

	JLabel estatCPU = new JLabel("");

	JPanel panel = new JPanel();
	GroupLayout gl_panelCPU = new GroupLayout(panelCPU);
	gl_panelCPU.setHorizontalGroup(
		gl_panelCPU.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelCPU.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelCPU.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCPU.createSequentialGroup()
						.addComponent(cpuMin, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(estatCPU))
					.addGroup(gl_panelCPU.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(cpuMax, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cpuAvg, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
				.addGap(10)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
	);
	gl_panelCPU.setVerticalGroup(
		gl_panelCPU.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panelCPU.createSequentialGroup()
				.addContainerGap()
				.addGroup(gl_panelCPU.createParallelGroup(Alignment.LEADING)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addGroup(gl_panelCPU.createSequentialGroup()
						.addComponent(cpuAvg)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCPU.createParallelGroup(Alignment.BASELINE)
							.addComponent(cpuMax)
							.addComponent(estatCPU))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cpuMin)))
				.addContainerGap())
	);
	panelCPU.setLayout(gl_panelCPU);
	panelDadesGenerals.setLayout(gl_panelDadesGenerals);
	resultat.setVisible(true);
	mostraResultats();
    }

    private void mostraResultats() {
	if (!ViewOpcionsController.isCpu()) {
	    chckbxCpu.setVisible(false);
	    panelCPU.setVisible(false);
	} else {
	    String[] cpuStats = MainController.analisisController.getCpuInfo();
	    cpuAvg.setText(cpuAvg.getText()+cpuStats[0]);
	    cpuMax.setText(cpuMax.getText()+cpuStats[1]);
	    cpuMin.setText(cpuMin.getText()+cpuStats[2]);
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
	    ramAvg.setText(ramAvg.getText()+ramStats[0]);
	    ramMax.setText(ramMax.getText()+ramStats[1]);
	    ramMin.setText(ramMin.getText()+ramStats[2]);
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
