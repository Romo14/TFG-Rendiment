/*
 * 
 */
package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * Classe encarregada de realitzar l'anàlisi de la memòria RAM.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class AnalisisRAM {

    /** Mitjana d'ús en percentatge de la RAM. */
    private float avgPercentatge;

    /** Mitjana total d'ús de la RAM. */
    private long avgTotal;

    /** Comptador. */
    private long comptador;

    /** Llistat d'ús de la RAM. */
    private ArrayList<Float> graf;

    /** Màxim ús en percentatge de la RAM. */
    private float maxPercentatge;

    /** Màxim total d'ús de la RAM. */
    private long maxTotal;

    /** Mínim ús en percentatge de la RAM. */
    private float minPercentatge;

    /** Mínim total d'ús de la RAM. */
    private long minTotal;

    /** Element que accedeix a la informació de la memòria RAM */
    private Sigar ramSigar;

    /** segon. */
    private Second segon;

    /** temps. */
    private ArrayList<Second> temps;

    /**
     * Creadora per defecte de la classe. Inicialitza els atributs amb valors
     * predeterminats
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
     * Obté el avgpercentatge.
     * 
     * @return avgpercentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Obté el avgtotal.
     * 
     * @return avgtotal
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Obté el comptador.
     * 
     * @return comptador
     */
    public long getComptador() {
	return comptador;
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
     * Obté el maxpercentatge.
     * 
     * @return maxpercentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obté el maxtotal.
     * 
     * @return maxtotal
     */
    public long getMaxTotal() {
	return maxTotal;
    }

    /**
     * Obté el minpercentatge.
     * 
     * @return minpercentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Obté el mintotal.
     * 
     * @return elmin total
     */
    public long getMinTotal() {
	return minTotal;
    }

    /**
     * Obté informació de la memòria RAM.
     * 
     * @return informació de la RAM
     */
    public String getRamInfo() {
	String s = "";
	try {
	    s = "Memòria RAM total: "
		    + String.valueOf(ramSigar.getMem().getRam() + " MB");
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return s;
    }

    /**
     * Obté el maxram.
     * 
     * @return maxram
     */
    public long getMaxRam() {
	long res = 0;
	try {
	    res = ramSigar.getMem().getRam();
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return res;
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
     * Actualitza la informació de l'anàlisi de la RAM.
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

    /**
     * Obté totes les dades de la memòria RAM.
     * 
     * @return totes les dades de la memòria RAM
     */
    public Object[] getTot() {
	Object[] tot = new Object[10];
	tot[0] = avgPercentatge;
	tot[1] = avgTotal;
	tot[2] = comptador;
	tot[3] = maxPercentatge;
	tot[4] = maxTotal;
	tot[5] = minPercentatge;
	tot[6] = minTotal;
	tot[7] = maxTotal;
	tot[8] = graf;
	tot[9] = temps;
	return tot;
    }

    /**
     * Defineix el valor de tots els atributs de la memòria RAM.
     * 
     * @param dadesRam
     *            Noves dades
     */
    @SuppressWarnings("unchecked")
    public void setTot(Object[] dadesRam) {
	avgPercentatge = (float) dadesRam[0];
	avgTotal = (long) dadesRam[1];
	comptador = (long) dadesRam[2];
	maxPercentatge = (float) dadesRam[3];
	maxTotal = (long) dadesRam[4];
	minPercentatge = (float) dadesRam[5];
	minTotal = (long) dadesRam[6];
	maxTotal = (long) dadesRam[7];
	graf = (ArrayList<Float>) dadesRam[8];
	temps = (ArrayList<Second>) dadesRam[9];
    }
}
