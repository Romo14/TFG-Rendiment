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

    /** �s total. */
    private long avgTotal;

    /** Llistat de valors d'�s del disc dur. */
    private ArrayList<Float> graf;

    /** Element que obt� les dades del disc dur. */
    private Sigar hddSigar;

    /** Valor inicial de lectures i escriptures a disc dur. */
    private float inicial;

    /** M�xim d'�s del disc dur en total. */
    private float maxTotal;

    /** M�nim d'�s del disc dur en total. */
    private float minTotal;

    /** N�mero d'escriptures totals a disc. */
    private long numEscriptures;

    /** N�mero d'escriptures totals a disc a l'iniciar l'an�lisi. */
    private long numEscripturesInicial;

    /** N�mero de lectures totals a disc. */
    private long numLectures;

    /** N�mero de lectures totals a disc a l'iniciar l'an�lisi. */
    private long numLecturesInicial;

    /** Segon en que es realitza l'an�lisi. */
    private Second segon;

    /** Temps durant el que es realitza l'an�lisi. */
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
     * Obt� la mitjana d'�s del disc dur.
     * 
     * @return Mitjana d'�s del disc dur
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Obt� el llistat amb els valors d'�s del disc dur.
     * 
     * @return Llistat de valors
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Obt� la informaci� b�sica del disc dur.
     * 
     * @return Informaci� del disc dur
     */
    public String getHddInfo() {
	String s = "";

	long diskSize = (new File("/").getTotalSpace()) / 1024 / 1024 / 1024;
	long diskFree = (new File("/").getFreeSpace()) / 1024 / 1024 / 1024;
	s = "Tamany del disc: " + diskSize + "GB Espai disponible: " + diskFree
		+ "GB \r\n N�mero de lectures: "
		+ (numLectures - numLecturesInicial)
		+ " N�mero d'escriptures: "
		+ (numEscriptures - numEscripturesInicial);

	return s;
    }

    /**
     * Obt� l'�s inicial del disc dur.
     * 
     * @return �s inicial
     */
    public float getInicial() {
	return inicial;
    }

    /**
     * Obt� el m�xim �s del disc dur durant l'an�lisi.
     * 
     * @return M�xim �s
     */
    public float getMaxTotal() {
	return maxTotal;
    }

    /**
     * Obt� el m�nim �s del disc dur durant l'an�lisi.
     * 
     * @return M�nim �s
     */
    public float getMinTotal() {
	return minTotal;
    }

    /**
     * Obt� el n�mero d'escriptures.
     * 
     * @return N�mero d'escriptures
     */
    public long getNumEscriptures() {
	return numEscriptures;
    }

    /**
     * Obt� el n�mero d'escriptures inicial.
     * 
     * @return N�mero escriptures inicial
     */
    public long getNumEscripturesInicial() {
	return numEscripturesInicial;
    }

    /**
     * Obt� el n�mero de lectures.
     * 
     * @return N�mero de lectures
     */
    public long getNumLectures() {
	return numLectures;
    }

    /**
     * Obt� el n�mero de lectures inicial.
     * 
     * @return N�mero de lectures inicial
     */
    public long getNumLecturesInicial() {
	return numLecturesInicial;
    }

    /**
     * Obt� la llista amb els instants en qu� s'ha realitzat l'an�lisi.
     * 
     * @return Llistat de temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Obt� totes les dades de l'an�lisi.
     * 
     * @return Dades de l'an�lisi
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
     * Restaura les dades de l'an�lisi del disc dur. S'utilitza quan carreguem
     * un an�lisi ja realitzat.
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
     * Actualitza les dades de l'�s del disc dur. Obt� els valors actuals i
     * comprova actualitza els par�metres adients.
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
