package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

public class AnalisisCPU {

	private float avgPercentatge;

	private long avgTotal;

	private long contador;

	private Sigar cpuSigar;

	private ArrayList<Float> graf;

	private float maxPercentatge;

	private long maxTotal;

	private float minPercentatge;

	private long minTotal;

	private ArrayList<Second> temps;

	private Second segon;

	public AnalisisCPU() {
		this.avgPercentatge = 0;
		this.avgTotal = 0;
		this.graf = new ArrayList<Float>();
		this.maxPercentatge = 0;
		this.maxTotal = 0;
		this.minPercentatge = 100;
		this.minTotal = Long.MAX_VALUE;
		this.cpuSigar = new Sigar();
		this.contador = 0;
		this.temps = new ArrayList<Second>();
		this.segon = new Second();
	}

	public float getAvgPercentatge() {
		return avgPercentatge;
	}

	public long getAvgTotal() {
		return avgTotal;
	}

	public long getContador() {
		return contador;
	}

	public ArrayList<Float> getGraf() {
		return graf;
	}

	public float getMaxPercentatge() {
		return maxPercentatge;
	}

	public long getMaxTotal() {
		return maxTotal;
	}

	public float getMinPercentatge() {
		return minPercentatge;
	}

	public long getMinTotal() {
		return minTotal;
	}

	public Sigar getRamSigar() {
		return cpuSigar;
	}

	public ArrayList<Second> getTemps() {
		return temps;
	}

	public void setAvgPercentatge(float avgPercentatge) {
		this.avgPercentatge = avgPercentatge;
	}

	public void setAvgTotal(long avgTotal) {
		this.avgTotal = avgTotal;
	}

	public void setGraf(ArrayList<Float> graf) {
		this.graf = graf;
	}

	public void setMaxPercentatge(float maxPercentatge) {
		this.maxPercentatge = maxPercentatge;
	}

	public void setMaxTotal(long maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMinPercentatge(float minPercentatge) {
		this.minPercentatge = minPercentatge;
	}

	public void setMinTotal(long minTotal) {
		this.minTotal = minTotal;
	}

	public void setRamSigar(Sigar cpuSigar) {
		this.cpuSigar = cpuSigar;
	}

	public void setTemps(ArrayList<Second> temps) {
		this.temps = temps;
	}

	public void updateCPU() {
		Cpu cpuTotal = null;
		CpuPerc cpuPerc = null;
		try {
			cpuPerc = cpuSigar.getCpuPerc();
			cpuTotal = cpuSigar.getCpu();
		} catch (SigarException se) {
			se.printStackTrace();
		}
		long used = (cpuTotal.getSys() + cpuTotal.getSys()) / 1024 / 1024;
		graf.add((float) (cpuPerc.getCombined() * 100));
		temps.add(segon);
		segon = (Second) segon.next();
		if (used > maxTotal) {
			maxPercentatge = (float) cpuPerc.getCombined() * 100;
			maxTotal = used;
		}
		if (used < minTotal) {
			minPercentatge = (float) cpuPerc.getCombined() * 100;
			minTotal = used;
		}
		++contador;
		avgTotal += used;
		avgPercentatge = (float) (avgTotal * 100 / contador)
				/ (cpuTotal.getTotal() / 1024 / 1024);
	}
}
