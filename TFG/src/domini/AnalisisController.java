package domini;

import java.text.DecimalFormat;

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
			ram.updateRAM();
		}
		if (opcionsController.isNet()) {
			net.updateNET();
		}
		if (opcionsController.isRam()) {
			hdd.updateHDD();
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

}
