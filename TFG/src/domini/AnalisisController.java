package domini;

import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

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

	public Float[] getRamInfo() {
		Float[] res = new Float[6];
		res[0] = ram.getAvgPercentatge();
		res[1] = (float) (ram.getAvgTotal() / ram.getContador());
		res[2] = ram.getMaxPercentatge();
		res[3] = (float) ram.getMaxTotal();
		res[4] = ram.getMinPercentatge();
		res[5] = (float) ram.getMinTotal();
		return res;
	}

	public Float[] getCpuInfo() {
		Float[] res = new Float[3];
		res[0] = cpu.getAvgPercentatge();
		res[1] = cpu.getMaxPercentatge();
		res[2] = cpu.getMinPercentatge();
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
		// case "HDD":
		// aux = hdd.getGraf();
		// temps = hdd.getTemps();
		// break;
		case "RAM":
			aux = ram.getGraf();
			temps = ram.getTemps();
			break;
		// case "NET":
		// aux = net.getGraf();
		// temps = net.getTemps();
		// break;
		}
		itAux = aux.iterator();
		itTemps = temps.iterator();
		while (itAux.hasNext() && itTemps.hasNext()) {
			res.add(itTemps.next(), itAux.next());
		}
		return res;
	}
}
