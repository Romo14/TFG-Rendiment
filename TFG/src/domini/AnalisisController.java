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

    public float[] getHddInfo() {
	float[] res = new float[3];
	res[0] = (float) ((hdd.getAvgTotal() - hdd.getInicial()) / 1024 / 1024);
	res[1] = hdd.getMaxTotal();
	res[2] = hdd.getMinTotal();
	return res;
    }

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

    public String getGpuInfo() {
	return gpu.getInfo();
    }
}
