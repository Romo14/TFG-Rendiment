package domini;

import java.util.ArrayList;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisCPU.
 */
public class AnalisisCPU {

    /** The avg percentatge. */
    private float avgPercentatge;

    /** The avg total. */
    private long avgTotal;

    /** The contador. */
    private long contador;

    /** The cpu sigar. */
    private Sigar cpuSigar;

    /** The graf. */
    private ArrayList<Float> graf;

    /** The max percentatge. */
    private float maxPercentatge;

    /** The max total. */
    private long maxTotal;

    /** The min percentatge. */
    private float minPercentatge;

    /** The min total. */
    private long minTotal;

    /** The segon. */
    private Second segon;

    /** The temps. */
    private ArrayList<Second> temps;

    /**
     * Instantiates a new analisis cpu.
     */
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

    /**
     * Gets the avg percentatge.
     *
     * @return the avg percentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Gets the avg total.
     *
     * @return the avg total
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Gets the contador.
     *
     * @return the contador
     */
    public long getContador() {
	return contador;
    }

    /**
     * Gets the graf.
     *
     * @return the graf
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Gets the max percentatge.
     *
     * @return the max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Gets the max total.
     *
     * @return the max total
     */
    public long getMaxTotal() {
	return maxTotal;
    }

    /**
     * Gets the min percentatge.
     *
     * @return the min percentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Gets the min total.
     *
     * @return the min total
     */
    public long getMinTotal() {
	return minTotal;
    }

    /**
     * Gets the ram sigar.
     *
     * @return the ram sigar
     */
    public Sigar getRamSigar() {
	return cpuSigar;
    }

    /**
     * Gets the temps.
     *
     * @return the temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Sets the avg percentatge.
     *
     * @param avgPercentatge the new avg percentatge
     */
    public void setAvgPercentatge(float avgPercentatge) {
	this.avgPercentatge = avgPercentatge;
    }

    /**
     * Sets the avg total.
     *
     * @param avgTotal the new avg total
     */
    public void setAvgTotal(long avgTotal) {
	this.avgTotal = avgTotal;
    }

    /**
     * Sets the graf.
     *
     * @param graf the new graf
     */
    public void setGraf(ArrayList<Float> graf) {
	this.graf = graf;
    }

    /**
     * Sets the max percentatge.
     *
     * @param maxPercentatge the new max percentatge
     */
    public void setMaxPercentatge(float maxPercentatge) {
	this.maxPercentatge = maxPercentatge;
    }

    /**
     * Sets the max total.
     *
     * @param maxTotal the new max total
     */
    public void setMaxTotal(long maxTotal) {
	this.maxTotal = maxTotal;
    }

    /**
     * Sets the min percentatge.
     *
     * @param minPercentatge the new min percentatge
     */
    public void setMinPercentatge(float minPercentatge) {
	this.minPercentatge = minPercentatge;
    }

    /**
     * Sets the min total.
     *
     * @param minTotal the new min total
     */
    public void setMinTotal(long minTotal) {
	this.minTotal = minTotal;
    }

    /**
     * Sets the ram sigar.
     *
     * @param cpuSigar the new ram sigar
     */
    public void setRamSigar(Sigar cpuSigar) {
	this.cpuSigar = cpuSigar;
    }

    /**
     * Sets the temps.
     *
     * @param temps the new temps
     */
    public void setTemps(ArrayList<Second> temps) {
	this.temps = temps;
    }

    /**
     * Update cpu.
     */
    public void updateCPU() {
	CpuPerc cpuPerc = null;
	try {
	    cpuPerc = cpuSigar.getCpuPerc();
	} catch (SigarException se) {
	    se.printStackTrace();
	}
	long used = (long) (cpuPerc.getCombined() * 100);
	graf.add((float) (cpuPerc.getCombined() * 100));
	temps.add(segon);
	segon = (Second) segon.next();
	if (used > maxTotal) {
	    maxPercentatge = (float) (cpuPerc.getCombined() * 100);
	    maxTotal = used;
	}
	if (used < minTotal) {
	    minPercentatge = (float) (cpuPerc.getCombined() * 100);
	    minTotal = used;
	}
	++contador;
	avgTotal += used;
	avgPercentatge = (float) (avgTotal / contador);
    }
}
