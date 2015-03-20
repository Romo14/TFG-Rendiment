package domini;

import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisController.
 *
 * @author Oriol
 */
/**
 * @author Oriol
 *
 */
/**
 * @author Oriol
 *
 */
public class AnalisisController {

	/** The opcions controller. */
	private static OpcionsController opcionsController;
	
	/** The cpu. */
	private AnalisisCPU cpu;
	
	/** The gpu. */
	private AnalisisGPU gpu;
	
	/** The hdd. */
	private AnalisisHDD hdd;
	
	/** The net. */
	private AnalisisNET net;
	
	/** The ram. */
	private AnalisisRAM ram;

	/**
	 * Instantiates a new analisis controller.
	 *
	 * @param oc the oc
	 */
	public AnalisisController(OpcionsController oc) {
		cpu = new AnalisisCPU();
		gpu = new AnalisisGPU();
		hdd = new AnalisisHDD();
		net = new AnalisisNET();
		ram = new AnalisisRAM();
		opcionsController = oc;
	}

	/**
	 * Analitzar.
	 */
	public void analitzar() {
		if (opcionsController.isCpu()) {
			cpu.updateCPU();
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
	}

	/**
	 * Gets the cpu info.
	 *
	 * @return the cpu info
	 */
	public Float[] getCpuInfo() {
		Float[] res = new Float[3];
		res[0] = cpu.getAvgPercentatge();
		res[1] = cpu.getMaxPercentatge();
		res[2] = cpu.getMinPercentatge();
		return res;
	}

	/**
	 * Gets the evol.
	 *
	 * @param string the string
	 * @return the evol
	 */
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
		case "HDD":
			aux = hdd.getGraf();
			temps = hdd.getTemps();
			break;
		case "RAM":
			aux = ram.getGraf();
			temps = ram.getTemps();
			break;
		case "NET":
			aux = net.getGraf();
			temps = net.getTemps();
			break;
		}
		itAux = aux.iterator();
		itTemps = temps.iterator();
		while (itAux.hasNext() && itTemps.hasNext()) {
			res.add(itTemps.next(), itAux.next());
		}
		return res;
	}

	/**
	 * Gets the hdd info.
	 *
	 * @return the hdd info
	 */
	public float[] getHddInfo() {
		float[] res = new float[3];
		res[0] = (float) ((hdd.getAvgTotal() - hdd.getInicial()) / 1024 / 1024);
		res[1] = hdd.getMaxTotal();
		res[2] = hdd.getMinTotal();
		return res;
	}

	/**
	 * Gets the net info.
	 *
	 * @return the net info
	 */
	public Float[] getNetInfo() {
		Float[] res = new Float[6];
		res[0] = net.getAvgPercentatge();
		res[1] = (float) (net.getAvgTotal() / net.getContador());
		res[2] = net.getMaxPercentatge();
		res[3] = (float) net.getMaxTotal();
		res[4] = net.getMinPercentatge();
		res[5] = (float) net.getMinTotal();
		return res;
	}

	/**
	 * Gets the ram info.
	 *
	 * @return the ram info
	 */
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

	/**
	 * Gets the gpu info.
	 *
	 * @return the gpu info
	 */
	public String getGpuInfo() {
		return gpu.getInfo();
	}
}
