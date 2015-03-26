package domini;

import java.util.ArrayList;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * Classe encarregada d'obtenir la informació de la CPU de l'ordinador i guardar les dades obtenides.
 */
/**
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisCPU {

    /** La mitjana d'ús de CPU en percentatge. */
    private float avgPercentatge;

    /** La mitjana d'ús de CPU en valors totals. */
    private long avgTotal;

    /** Comptador de les vegades que s'actualitza la informació de la CPU. */
    private long comptador;

    /** Element que ens permet accedir a la informació de la CPU. */
    private Sigar cpuSigar;

    /** Llista de valors obtinguts en el temps */
    private ArrayList<Float> graf;

    /** Màxim ús de la CPU en percentatge. */
    private float maxPercentatge;

    /** Màxim ús de la CPU en total. */
    private long maxTotal;

    /** Mínim ús de la CPU en percentatge. */
    private float minPercentatge;

    /** Mínim ús de la CPU en total. */
    private long minTotal;

    /** Hora del dia en què es realitza l'obtenció de les dades. */
    private Second segon;

    /** Llistat de les hores en que s'obté les dades. */
    private ArrayList<Second> temps;

    /**
     * Inicialitza tots els valors dels atributs per defecte.
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
	this.comptador = 0;
	this.temps = new ArrayList<Second>();
	this.segon = new Second();
    }

    /**
     * Obté el avg percentatge.
     * 
     * @return el avg percentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Obté el avg total.
     * 
     * @return el avg total
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Obté el comptador.
     * 
     * @return el comptador
     */
    public long getComptador() {
	return comptador;
    }

    /**
     * Consulta la informació del processador
     */
    public String getCpuInfo() {
	try {
	    CpuInfo info = cpuSigar.getCpuInfoList()[0];
	    return "Fabricant: " + info.getVendor() + " Model: "
		    + info.getModel() + ", Nuclis: " + info.getTotalCores()
		    + ", Freqüència màxima: " + info.getMhz() + ", Sockets:"
		    + info.getTotalSockets() + ", Nuclis per socket: "
		    + info.getCoresPerSocket();
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return "";
    }

    /**
     * Obté el graf.
     * 
     * @return el graf
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Obté el max percentatge.
     * 
     * @return el max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obté el max total.
     * 
     * @return el max total
     */
    public long getMaxTotal() {
	return maxTotal;
    }

    /**
     * Obté el min percentatge.
     * 
     * @return el min percentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Obté el min total.
     * 
     * @return el min total
     */
    public long getMinTotal() {
	return minTotal;
    }

    /**
     * Obté el ram sigar.
     * 
     * @return el ram sigar
     */
    public Sigar getRamSigar() {
	return cpuSigar;
    }

    /**
     * Obté el temps.
     * 
     * @return el temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Defineix el avg percentatge.
     * 
     * @param avgPercentatge
     *            el nou avg percentatge
     */
    public void setAvgPercentatge(float avgPercentatge) {
	this.avgPercentatge = avgPercentatge;
    }

    /**
     * Defineix el avg total.
     * 
     * @param avgTotal
     *            el nou avg total
     */
    public void setAvgTotal(long avgTotal) {
	this.avgTotal = avgTotal;
    }

    /**
     * Defineix el graf.
     * 
     * @param graf
     *            el nou graf
     */
    public void setGraf(ArrayList<Float> graf) {
	this.graf = graf;
    }

    /**
     * Defineix el max percentatge.
     * 
     * @param maxPercentatge
     *            el nou max percentatge
     */
    public void setMaxPercentatge(float maxPercentatge) {
	this.maxPercentatge = maxPercentatge;
    }

    /**
     * Defineix el max total.
     * 
     * @param maxTotal
     *            el nou max total
     */
    public void setMaxTotal(long maxTotal) {
	this.maxTotal = maxTotal;
    }

    /**
     * Defineix el min percentatge.
     * 
     * @param minPercentatge
     *            el nou min percentatge
     */
    public void setMinPercentatge(float minPercentatge) {
	this.minPercentatge = minPercentatge;
    }

    /**
     * Defineix el min total.
     * 
     * @param minTotal
     *            el nou min total
     */
    public void setMinTotal(long minTotal) {
	this.minTotal = minTotal;
    }

    /**
     * Defineix el ram sigar.
     * 
     * @param cpuSigar
     *            el nou ram sigar
     */
    public void setRamSigar(Sigar cpuSigar) {
	this.cpuSigar = cpuSigar;
    }

    /**
     * Defineix el temps.
     * 
     * @param temps
     *            el nou temps
     */
    public void setTemps(ArrayList<Second> temps) {
	this.temps = temps;
    }

    /**
     * Obté i actualitza la informació de la CPU.
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
	++comptador;
	avgTotal += used;
	avgPercentatge = (float) (avgTotal / comptador);
    }
}
