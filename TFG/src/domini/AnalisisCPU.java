package domini;

import java.util.ArrayList;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * Classe encarregada de realitzar l'an�lisi del processador
 */
/**
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisCPU {

    /** Mitjana d'�s en percentatge. */
    private float avgPercentatge;

    /** Mitjana d'�s en valor total. */
    private long avgTotal;

    /** Comptador d'execucions de l'an�lisi. */
    private long comptador;

    /** Element encarregat d'accedir a les dades de la cpu. */
    private Sigar cpuSigar;

    /** Llista amb els valors d'�s de cpu per segon. */
    private ArrayList<Float> graf;

    /** M�xim �s de la cpu en percentatge. */
    private float maxPercentatge;

    /** M�xim �s de la cpu en total. */
    private long maxTotal;

    /** M�nim �s de la cpu en percentatge. */
    private float minPercentatge;

    /** M�nim �s de la cpu en total. */
    private long minTotal;

    /** Segon en qu� es realitza el seguiment. */
    private Second segon;

    /** Llistat de segons durant el que es realitza l'an�lisi. */
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
     * Obt� el avg percentatge.
     * 
     * @return avg percentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Obt� el cpu info.
     * 
     * @return cpu info
     */
    public String getCpuInfo() {
	try {
	    CpuInfo info = cpuSigar.getCpuInfoList()[0];
	    return "Fabricant: " + info.getVendor() + " Model: "
		    + info.getModel() + ", Nuclis: " + info.getTotalCores()
		    + ", Freq��ncia m�xima: " + info.getMhz()
		    + " MHz, Sockets:" + info.getTotalSockets()
		    + ", Nuclis per socket: " + info.getCoresPerSocket();
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return "";
    }

    /**
     * Obt� el graf.
     * 
     * @return graf
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Obt� el max percentatge.
     * 
     * @return max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obt� el min percentatge.
     * 
     * @return min percentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Obt� el temps.
     * 
     * @return temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Obt� el tot.
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
     * Restaura les dades de l'an�lisi de la cpu. S'utilitza quan carreguem un
     * an�lisi ja realitzat.
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
     * Actualitza les dades de l'�s de la cpu. Obt� els valors actuals i 
     * comprova actualitza els par�metres adients.
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
