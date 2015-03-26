/*
 * 
 */
package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisRAM.
 */
public class AnalisisRAM {

    /** The avg percentatge. */
    private float avgPercentatge;

    /** The avg total. */
    private long avgTotal;

    /** The comptador. */
    private long comptador;

    /** Llista de valors obtinguts en el temps. */
    private ArrayList<Float> graf;

    /** The max percentatge. */
    private float maxPercentatge;

    /** The max total. */
    private long maxTotal;

    /** The min percentatge. */
    private float minPercentatge;

    /** The min total. */
    private long minTotal;

    /** The ram sigar. */
    private Sigar ramSigar;

    /** The temps. */
    private ArrayList<Second> temps;

    /** The segon. */
    private Second segon;

    /**
     * Instantiates a new analisis ram.
     */
    public AnalisisRAM() {
	this.avgPercentatge = 0;
	this.avgTotal = 0;
	this.graf = new ArrayList<Float>();
	this.maxPercentatge = 0;
	this.maxTotal = 0;
	this.minPercentatge = 100;
	this.minTotal = Long.MAX_VALUE;
	this.ramSigar = new Sigar();
	this.comptador = 0;
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
     * @return the ${e.g(1).rsfl()}
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Gets the comptador.
     * 
     * @return the comptador
     */
    public long getComptador() {
	return comptador;
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
	return ramSigar;
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
     * @param avgPercentatge
     *            the new avg percentatge
     */
    public void setAvgPercentatge(float avgPercentatge) {
	this.avgPercentatge = avgPercentatge;
    }

    /**
     * Sets the avg total.
     * 
     * @param avgTotal
     *            the new avg total
     */
    public void setAvgTotal(long avgTotal) {
	this.avgTotal = avgTotal;
    }

    /**
     * Sets the comptador.
     * 
     * @param comptador
     *            the new comptador
     */
    public void setcomptador(long comptador) {
	this.comptador = comptador;
    }

    /**
     * Sets the graf.
     * 
     * @param graf
     *            the new graf
     */
    public void setGraf(ArrayList<Float> graf) {
	this.graf = graf;
    }

    /**
     * Sets the max percentatge.
     * 
     * @param maxPercentatge
     *            the new max percentatge
     */
    public void setMaxPercentatge(float maxPercentatge) {
	this.maxPercentatge = maxPercentatge;
    }

    /**
     * Sets the max total.
     * 
     * @param maxTotal
     *            the new max total
     */
    public void setMaxTotal(long maxTotal) {
	this.maxTotal = maxTotal;
    }

    /**
     * Sets the min percentatge.
     * 
     * @param minPercentatge
     *            the new min percentatge
     */
    public void setMinPercentatge(float minPercentatge) {
	this.minPercentatge = minPercentatge;
    }

    /**
     * Sets the min total.
     * 
     * @param minTotal
     *            the new min total
     */
    public void setMinTotal(long minTotal) {
	this.minTotal = minTotal;
    }

    /**
     * Sets the ram sigar.
     * 
     * @param ramSigar
     *            the new ram sigar
     */
    public void setRamSigar(Sigar ramSigar) {
	this.ramSigar = ramSigar;
    }

    /**
     * Sets the temps.
     * 
     * @param temps
     *            the new temps
     */
    public void setTemps(ArrayList<Second> temps) {
	this.temps = temps;
    }

    /**
     * Obté informació sobre la memòria ram
     * 
     * @return memòria ram total del dispositiu
     */
    public String getRamInfo() {
	String s = "";
	try {
	    s = "Memòria RAM total: "
		    + String.valueOf(ramSigar.getMem().getRam());
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return s;
    }

    /**
     * Update ram.
     */
    public void updateRAM() {
	Mem mem = null;
	try {
	    mem = ramSigar.getMem();
	} catch (SigarException se) {
	    se.printStackTrace();
	}
	long used = (mem.getActualUsed() / 1024 / 1024);
	graf.add((float) mem.getUsedPercent());
	temps.add(segon);
	segon = (Second) segon.next();
	if (used > maxTotal) {
	    maxPercentatge = (float) used * 100
		    / (mem.getTotal() / 1024 / 1024);
	    maxTotal = used;
	}
	if (used < minTotal) {
	    minPercentatge = (float) used * 100
		    / (mem.getTotal() / 1024 / 1024);
	    minTotal = used;
	}
	++comptador;
	avgTotal += used;
	avgPercentatge = (float) (avgTotal * 100 / comptador)
		/ (mem.getTotal() / 1024 / 1024);
    }
}
