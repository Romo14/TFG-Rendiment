package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Sigar;
import org.jfree.data.time.Second;

public abstract class Analisi {
    /** Mitjana d'�s en percentatge. */
    protected float avgPercentatge;

    /** Mitjana d'�s en valor total. */
    protected long avgTotal;

    /** Comptador d'execucions de l'an�lisi. */
    protected long comptador;

    /** Llista amb els valors d'�s per segon. */
    protected ArrayList<Float> graf;

    /** M�xim �s en percentatge. */
    protected float maxPercentatge;

    /** M�xim �s total. */
    protected long maxTotal;

    /** M�nim �s en percentatge. */
    protected float minPercentatge;

    /** M�nim �s en total. */
    protected long minTotal;

    /** Segon en qu� es realitza el seguiment. */
    protected Second segon;

    /** Element encarregat d'accedir a les dades. */
    protected Sigar sigar;

    /** Llistat de segons durant el que es realitza l'an�lisi. */
    protected ArrayList<Second> temps;
    
    protected String info;

    public String getInfo() {
        return info;
    }

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
	this.info = getInfoComponent();
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
     * Obt� la mitjana d'�s.
     * 
     * @return Mitjana d'�s
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
     * Obt� el max percentatge.
     * 
     * @return max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obt� el m�xim �s durant l'an�lisi.
     * 
     * @return M�xim �s
     */
    public float getMaxTotal() {
	return maxTotal;
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
     * Obt� el m�nim �s durant l'an�lisi.
     * 
     * @return M�nim �s
     */
    public float getMinTotal() {
	return minTotal;
    }

    /**
     * Obt� el temps.
     * 
     * @return temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    public abstract void update();

    public abstract String getInfoComponent();
    
    public abstract Object[] getTot();
    
    public abstract void setTot(Object[] tot);
}
