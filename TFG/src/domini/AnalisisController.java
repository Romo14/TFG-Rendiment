package domini;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYSeries;

public class AnalisisController {

	private static OpcionsController opcionsController;
	private AnalisisCPU cpu;
	private AnalisisGPU gpu;
	private AnalisisHDD hdd;
	private AnalisisNET net;
	private AnalisisRAM ram;

	public AnalisisController(OpcionsController oc) {
		cpu = new AnalisisCPU();
		gpu = new AnalisisGPU();
		hdd = new AnalisisHDD();
		net = new AnalisisNET();
		ram = new AnalisisRAM();
		opcionsController = oc;
	}

	public void analitzar() {
		if (opcionsController.isCpu()) {
			cpu.updateCPU();
		}
		if (opcionsController.isGpu()) {
			gpu.updateGPU();
		}
		if (opcionsController.isHdd()) {
			hdd.updateHDD();
		}
		if (opcionsController.isNet()) {
			net.updateNET();
		}
		if (opcionsController.isRam()) {
			ram.updateRAM();
		}
		guardaResultats();
	}

	private void guardaResultats() {

	}

	public String[] getRamInfo() {
		DecimalFormat df = new DecimalFormat("0.00");
		String[] res = new String[3];
		res[0] = df.format(ram.getAvgPercentatge()) + "% (" + ram.getAvgTotal()
				/ ram.getContador() + " MB)";
		res[1] = df.format(ram.getMaxPercentatge()) + "% (" + ram.getMaxTotal()
				+ " MB)";
		res[2] = df.format(ram.getMinPercentatge()) + "% (" + ram.getMinTotal()
				+ " MB)";
		return res;
	}

	public String[] getCpuInfo() {
		DecimalFormat df = new DecimalFormat("0.00");
		String[] res = new String[3];
		res[0] = df.format(cpu.getAvgPercentatge()) + "%";
		res[1] = df.format(cpu.getMaxPercentatge()) + "%";
		res[2] = df.format(cpu.getMinPercentatge()) + "%";
		return res;
	}

	public TimeSeries getEvol(String string) {
		TimeSeries res = new TimeSeries(string);
		ArrayList<Float> aux = new ArrayList<Float>();
		ArrayList<Second> temps = new ArrayList<Second>();
		Iterator<Float> itAux;
		Iterator<Second> itTemps;
		switch (string) {
		case "CPU":
			aux = cpu.getGraf();
			temps = cpu.getTemps();
			break;
		case "GPU":
			aux = gpu.getGraf();
			temps = gpu.getTemps();
			break;
//		case "HDD":
//			aux = hdd.getGraf();
//			temps = hdd.getTemps();
//			break;
		case "RAM":
			aux = ram.getGraf();
			temps = ram.getTemps();
			break;
//		case "NET":
//			aux = net.getGraf();
//			temps = net.getTemps();
//			break;
		}
		itAux = aux.iterator();
		itTemps = temps.iterator();
		while (itAux.hasNext() && itTemps.hasNext()) {
			res.add(itTemps.next(), itAux.next());
		}
		return res;
	}
}
