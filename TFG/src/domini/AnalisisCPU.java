package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class AnalisisCPU {

    private float avgPercentatge;

    private long avgTotal;

    private ArrayList<Long> graf;

    private float maxPercentatge;

    private long maxTotal;

    private float minPercentatge;

    private long minTotal;

    private Sigar cpuSigar;

    private long contador;

    public AnalisisCPU() {
	this.avgPercentatge = 0;
	this.avgTotal = 0;
	this.graf = new ArrayList<Long>();
	this.maxPercentatge = 0;
	this.maxTotal = 0;
	this.minPercentatge = 100;
	this.minTotal = Long.MAX_VALUE;
	this.cpuSigar = new Sigar();
	this.contador = 0;

    }

    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    public long getAvgTotal() {
	return avgTotal;
    }

    public ArrayList<Long> getGraf() {
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

    public void setAvgPercentatge(float avgPercentatge) {
	this.avgPercentatge = avgPercentatge;
    }

    public void setAvgTotal(long avgTotal) {
	this.avgTotal = avgTotal;
    }

    public void setGraf(ArrayList<Long> graf) {
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

    public void updateCPU() {
	Cpu cpu = null;
	try {
	    cpu = cpuSigar.getCpu();
	} catch (SigarException se) {
	    se.printStackTrace();
	}
	long used = (cpu.getIdle() + cpu.getIrq() + cpu.getNice()
		+ cpu.getSoftIrq() + cpu.getStolen() + cpu.getSys()
		+ cpu.getUser() + cpu.getWait()) / 1024 / 1024;
	graf.add(used);
	if (used > maxTotal) {
	    maxPercentatge = (float) used * 100
		    / (cpu.getTotal() / 1024 / 1024);
	    maxTotal = used;
	}
	if (used < minTotal) {
	    minPercentatge = (float) used * 100
		    / (cpu.getTotal() / 1024 / 1024);
	    minTotal = used;
	}
	++contador;
	avgTotal += used;
	avgPercentatge = (float) (avgTotal * 100 / contador)
		/ (cpu.getTotal() / 1024 / 1024);
    }

    public long getContador() {
	return contador;
    }
}
