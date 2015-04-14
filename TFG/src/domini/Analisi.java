package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Sigar;
import org.jfree.data.time.Second;

public class Analisi {
    /** Mitjana d'ús en percentatge. */
    protected float avgPercentatge;

    /** Mitjana d'ús en valor total. */
    protected long avgTotal;

    /** Comptador d'execucions de l'anàlisi. */
    protected long comptador;

    /** Element encarregat d'accedir a les dades. */
    protected Sigar sigar;

    /** Llista amb els valors d'ús per segon. */
    protected ArrayList<Float> graf;

    /** Màxim ús en percentatge. */
    protected float maxPercentatge;

    /** Màxim ús total. */
    protected long maxTotal;

    /** Mínim ús en percentatge. */
    protected float minPercentatge;

    /** Mínim ús en total. */
    protected long minTotal;

    /** Segon en què es realitza el seguiment. */
    protected Second segon;

    /** Llistat de segons durant el que es realitza l'anàlisi. */
    protected ArrayList<Second> temps;

    public Analisi() {
	this.avgPercentatge = 0;
	this.avgTotal = 0;
	this.graf = new ArrayList<Float>();
	this.maxPercentatge = 0;
	this.maxTotal = 0;
	this.minPercentatge = 100;
	this.minTotal = Long.MAX_VALUE;
	this.sigar = new Sigar();
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
     * Obté el máxim ús del disc dur durant l'anàlisi.
     * 
     * @return Màxim ús
     */
    public float getMaxTotal() {
	return maxTotal;
    }

    /**
     * Obté el mínim ús del disc dur durant l'anàlisi.
     * 
     * @return Mínim ús
     */
    public float getMinTotal() {
	return minTotal;
    }

    /**
     * Obté la mitjana d'ús del disc dur.
     * 
     * @return Mitjana d'ús del disc dur
     */
    public long getAvgTotal() {
	return avgTotal;
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
     * Obté el comptador.
     * @return comptador
     */
    public long getComptador() {
	return comptador;
    }
}
