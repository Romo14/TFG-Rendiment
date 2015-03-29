package domini;

import java.io.File;
import java.util.ArrayList;

import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * The Class AnalisisHDD.
 */
public class AnalisisHDD {

    /** Ús total. */
    private long avgTotal;

    /** Llistat de valors d'ús del disc dur. */
    private ArrayList<Float> graf;

    /** Element que obté les dades del disc dur. */
    private Sigar hddSigar;

    /** Valor inicial de lectures i escriptures a disc dur. */
    private float inicial;

    /** Máxim d'ús del disc dur en total. */
    private float maxTotal;

    /** Mínim d'ús del disc dur en total. */
    private float minTotal;

    /** Número d'escriptures totals a disc. */
    private long numEscriptures;

    /** Número d'escriptures totals a disc a l'iniciar l'anàlisi. */
    private long numEscripturesInicial;

    /** Número de lectures totals a disc. */
    private long numLectures;

    /** Número de lectures totals a disc a l'iniciar l'anàlisi. */
    private long numLecturesInicial;

    /** Segon en que es realitza l'anàlisi. */
    private Second segon;

    /** Temps durant el que es realitza l'anàlisi. */
    private ArrayList<Second> temps;

    /**
     * Creadora per defecte de la classe.
     */
    public AnalisisHDD() {
	this.avgTotal = 0;
	this.graf = new ArrayList<Float>();
	this.maxTotal = 0;
	this.minTotal = Long.MAX_VALUE;
	this.hddSigar = new Sigar();
	this.temps = new ArrayList<Second>();
	this.segon = new Second();
	this.inicial = 0;
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
     * Obté el llistat amb els valors d'ús del disc dur.
     * 
     * @return Llistat de valors
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Obté la informació bàsica del disc dur.
     * 
     * @return Informació del disc dur
     */
    public String getHddInfo() {
	String s = "";

	long diskSize = (new File("/").getTotalSpace()) / 1024 / 1024 / 1024;
	long diskFree = (new File("/").getFreeSpace()) / 1024 / 1024 / 1024;
	s = "Tamany del disc: " + diskSize + "GB Espai disponible: " + diskFree
		+ "GB \r\n Número de lectures: "
		+ (numLectures - numLecturesInicial)
		+ " Número d'escriptures: "
		+ (numEscriptures - numEscripturesInicial);

	return s;
    }

    /**
     * Obté l'ús inicial del disc dur.
     * 
     * @return Ús inicial
     */
    public float getInicial() {
	return inicial;
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
     * Obté el número d'escriptures.
     * 
     * @return Número d'escriptures
     */
    public long getNumEscriptures() {
	return numEscriptures;
    }

    /**
     * Obté el número d'escriptures inicial.
     * 
     * @return Número escriptures inicial
     */
    public long getNumEscripturesInicial() {
	return numEscripturesInicial;
    }

    /**
     * Obté el número de lectures.
     * 
     * @return Número de lectures
     */
    public long getNumLectures() {
	return numLectures;
    }

    /**
     * Obté el número de lectures inicial.
     * 
     * @return Número de lectures inicial
     */
    public long getNumLecturesInicial() {
	return numLecturesInicial;
    }

    /**
     * Obté la llista amb els instants en què s'ha realitzat l'anàlisi.
     * 
     * @return Llistat de temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Obté totes les dades de l'anàlisi.
     * 
     * @return Dades de l'anàlisi
     */
    public Object[] getTot() {
	Object[] tot = new Object[10];
	tot[0] = avgTotal;
	tot[1] = inicial;
	tot[2] = maxTotal;
	tot[3] = minTotal;
	tot[4] = numEscriptures;
	tot[5] = numEscripturesInicial;
	tot[6] = numLectures;
	tot[7] = numLecturesInicial;
	tot[8] = graf;
	tot[9] = temps;
	return tot;
    }

    /**
     * Restaura les dades de l'anàlisi del disc dur. S'utilitza quan carreguem
     * un anàlisi ja realitzat.
     * 
     * @param dadesHdd
     *            Dades del disc dur
     */
    @SuppressWarnings("unchecked")
    public void setTot(Object[] dadesHdd) {
	avgTotal = (long) dadesHdd[0];
	inicial = (float) dadesHdd[1];
	maxTotal = (float) dadesHdd[2];
	minTotal = (float) dadesHdd[3];
	numEscriptures = (long) dadesHdd[4];
	numEscripturesInicial = (long) dadesHdd[5];
	numLectures = (long) dadesHdd[6];
	numLecturesInicial = (long) dadesHdd[7];
	graf = (ArrayList<Float>) dadesHdd[8];
	temps = (ArrayList<Second>) dadesHdd[9];
    }

    /**
     * Actualitza les dades de l'ús del disc dur. Obté els valors actuals i
     * comprova actualitza els paràmetres adients.
     */
    public void updateHDD() {
	FileSystemUsage hdd = null;
	try {
	    hdd = hddSigar.getFileSystemUsage("C:");
	} catch (SigarException se) {
	    se.printStackTrace();
	}
	float used = 0;
	long aux = hdd.getDiskReadBytes() + hdd.getDiskWriteBytes();
	if (avgTotal != 0) {
	    used = (float) ((hdd.getDiskReadBytes() + hdd.getDiskWriteBytes() - avgTotal) / 1024.00 / 1024.00);
	    numEscriptures = hdd.getDiskWrites();
	    numLectures = hdd.getDiskReads();
	} else {
	    avgTotal = aux;
	    inicial = avgTotal;
	    numEscripturesInicial = hdd.getDiskWrites();
	    numLecturesInicial = hdd.getDiskReads();
	}
	if (used < 0) {
	    used = 0;
	    inicial = aux;
	}
	graf.add(used);
	temps.add(segon);
	segon = (Second) segon.next();
	if (used > maxTotal)
	    maxTotal = used;
	if (used < minTotal)
	    minTotal = used;
	avgTotal = aux;

    }
}
