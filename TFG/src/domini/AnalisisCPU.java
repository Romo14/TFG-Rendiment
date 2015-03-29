package domini;

import java.util.ArrayList;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * Classe encarregada de realitzar l'anàlisi del processador
 */
/**
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisCPU {

    /** Mitjana d'ús en percentatge. */
    private float avgPercentatge;

    /** Mitjana d'ús en valor total. */
    private long avgTotal;

    /** Comptador d'execucions de l'anàlisi. */
    private long comptador;

    /** Element encarregat d'accedir a les dades de la cpu. */
    private Sigar cpuSigar;

    /** Llista amb els valors d'ús de cpu per segon. */
    private ArrayList<Float> graf;

    /** Màxim ús de la cpu en percentatge. */
    private float maxPercentatge;

    /** Màxim ús de la cpu en total. */
    private long maxTotal;

    /** Mínim ús de la cpu en percentatge. */
    private float minPercentatge;

    /** Mínim ús de la cpu en total. */
    private long minTotal;

    /** Segon en què es realitza el seguiment. */
    private Second segon;

    /** Llistat de segons durant el que es realitza l'anàlisi. */
    private ArrayList<Second> temps;

    /**
     * Creadora per defecte de l'objecte AnalisisCPU
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
     * @return avg percentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Obté el cpu info.
     * 
     * @return cpu info
     */
    public String getCpuInfo() {
	try {
	    CpuInfo info = cpuSigar.getCpuInfoList()[0];
	    return "Fabricant: " + info.getVendor() + " Model: "
		    + info.getModel() + ", Nuclis: " + info.getTotalCores()
		    + ", Freqüència màxima: " + info.getMhz()
		    + " MHz, Sockets:" + info.getTotalSockets()
		    + ", Nuclis per socket: " + info.getCoresPerSocket();
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return "";
    }

    /**
     * Obté el graf.
     * 
     * @return graf
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Obté el max percentatge.
     * 
     * @return max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obté el min percentatge.
     * 
     * @return min percentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Obté el temps.
     * 
     * @return temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Obté el tot.
     * 
     * @return tot
     */
    public Object[] getTot() {
	Object[] tot = new Object[5];
	tot[0] = avgPercentatge;
	tot[1] = maxPercentatge;
	tot[2] = minPercentatge;
	tot[3] = graf;
	tot[4] = temps;
	return tot;
    }

    /**
     * Restaura les dades de l'anàlisi de la cpu. S'utilitza quan carreguem un
     * anàlisi ja realitzat.
     * 
     * @param dadesCpu
     *            Dades de la cpu
     */
    @SuppressWarnings("unchecked")
    public void setTot(Object[] dadesCpu) {
	avgPercentatge = (float) dadesCpu[0];
	maxPercentatge = (float) dadesCpu[1];
	minPercentatge = (float) dadesCpu[2];
	graf = (ArrayList<Float>) dadesCpu[3];
	temps = (ArrayList<Second>) dadesCpu[4];
    }

    /**
     * Actualitza les dades de l'ús de la cpu. Obté els valors actuals i 
     * comprova actualitza els paràmetres adients.
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
