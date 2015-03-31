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
 * Classe encarregada de realitzar l'an�lisi de la mem�ria RAM.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class AnalisisRAM {

    /** Mitjana d'�s en percentatge de la RAM. */
    private float avgPercentatge;

    /** Mitjana total d'�s de la RAM. */
    private long avgTotal;

    /** Comptador. */
    private long comptador;

    /** Llistat d'�s de la RAM. */
    private ArrayList<Float> graf;

    /** M�xim �s en percentatge de la RAM. */
    private float maxPercentatge;

    /** M�xim total d'�s de la RAM. */
    private long maxTotal;

    /** M�nim �s en percentatge de la RAM. */
    private float minPercentatge;

    /** M�nim total d'�s de la RAM. */
    private long minTotal;

    /** Element que accedeix a la informaci� de la mem�ria RAM */
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
     * Obt� el avgpercentatge.
     * 
     * @return avgpercentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Obt� el avgtotal.
     * 
     * @return avgtotal
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Obt� el comptador.
     * 
     * @return comptador
     */
    public long getComptador() {
	return comptador;
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
     * Obt� el maxpercentatge.
     * 
     * @return maxpercentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obt� el maxtotal.
     * 
     * @return maxtotal
     */
    public long getMaxTotal() {
	return maxTotal;
    }

    /**
     * Obt� el minpercentatge.
     * 
     * @return minpercentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Obt� el mintotal.
     * 
     * @return elmin total
     */
    public long getMinTotal() {
	return minTotal;
    }

    /**
     * Obt� informaci� de la mem�ria RAM.
     * 
     * @return informaci� de la RAM
     */
    public String getRamInfo() {
	String s = "";
	try {
	    s = "Mem�ria RAM total: "
		    + String.valueOf(ramSigar.getMem().getRam() + " MB");
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return s;
    }

    /**
     * Obt� el maxram.
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
     * Obt� el temps.
     * 
     * @return temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Actualitza la informaci� de l'an�lisi de la RAM.
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
     * Obt� totes les dades de la mem�ria RAM.
     * 
     * @return totes les dades de la mem�ria RAM
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
     * Defineix el valor de tots els atributs de la mem�ria RAM.
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
