package domini;

import java.util.ArrayList;

import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * The Class AnalisisHDD.
 */
public class AnalisisHDD {

	/** La mitjana d'ús del HDD en percentatge. */
	private long avgTotal;

	/** Comptador de les vegades que s'actualitza la informació del HDD. */
	private long comptador;

	/** Llista de valors obtinguts en el temps. */
	private ArrayList<Float> graf;

	/** Element que ens permet accedir a la informació del disc dur. */
	private Sigar hddSigar;

	/** Dade. */
	private float inicial;

	/** The max total. */
	private float maxTotal;

	/** The min total. */
	private float minTotal;

	/** The segon. */
	private Second segon;

	/** The temps. */
	private ArrayList<Second> temps;

	/**
	 * Instantiates a nou analisis hdd.
	 */
	public AnalisisHDD() {
		this.avgTotal = 0;
		this.graf = new ArrayList<Float>();
		this.maxTotal = 0;
		this.minTotal = Long.MAX_VALUE;
		this.hddSigar = new Sigar();
		this.comptador = 0;
		this.temps = new ArrayList<Second>();
		this.segon = new Second();
		this.inicial = 0;
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
	public long getcomptador() {
		return comptador;
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
	 * Obté el inicial.
	 *
	 * @return el inicial
	 */
	public float getInicial() {
		return inicial;
	}

	/**
	 * Obté el max total.
	 *
	 * @return el max total
	 */
	public float getMaxTotal() {
		return maxTotal;
	}

	/**
	 * Obté el min total.
	 *
	 * @return el min total
	 */
	public float getMinTotal() {
		return minTotal;
	}

	/**
	 * Obté el ram sigar.
	 *
	 * @return el ram sigar
	 */
	public Sigar getRamSigar() {
		return hddSigar;
	}

	/**
	 * Obté el segon.
	 *
	 * @return el segon
	 */
	public Second getSegon() {
		return segon;
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
	 * Defineix el avg total.
	 *
	 * @param avgTotal el nou avg total
	 */
	public void setAvgTotal(long avgTotal) {
		this.avgTotal = avgTotal;
	}

	/**
	 * Defineix el comptador.
	 *
	 * @param comptador el nou comptador
	 */
	public void setcomptador(long comptador) {
		this.comptador = comptador;
	}

	/**
	 * Defineix el graf.
	 *
	 * @param graf el nou graf
	 */
	public void setGraf(ArrayList<Float> graf) {
		this.graf = graf;
	}

	/**
	 * Defineix el inicial.
	 *
	 * @param inicial el nou inicial
	 */
	public void setInicial(float inicial) {
		this.inicial = inicial;
	}

	/**
	 * Defineix el max total.
	 *
	 * @param maxTotal el nou max total
	 */
	public void setMaxTotal(float maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * Defineix el max total.
	 *
	 * @param maxTotal el nou max total
	 */
	public void setMaxTotal(long maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * Defineix el min total.
	 *
	 * @param minTotal el nou min total
	 */
	public void setMinTotal(float minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * Defineix el min total.
	 *
	 * @param minTotal el nou min total
	 */
	public void setMinTotal(long minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * Defineix el ram sigar.
	 *
	 * @param hddSigar el nou ram sigar
	 */
	public void setRamSigar(Sigar hddSigar) {
		this.hddSigar = hddSigar;
	}

	/**
	 * Defineix el segon.
	 *
	 * @param segon el nou segon
	 */
	public void setSegon(Second segon) {
		this.segon = segon;
	}

	/**
	 * Defineix el temps.
	 *
	 * @param temps el nou temps
	 */
	public void setTemps(ArrayList<Second> temps) {
		this.temps = temps;
	}

	/**
	 * Update hdd.
	 */
	public void updateHDD() {
		FileSystemUsage hdd = null;
		float used = 0;
		for (char c = 'A'; c <= 'Z'; c++) {
			try {
				hdd = null;
				String s = c + ":";
				hdd = hddSigar.getFileSystemUsage(s);
				if (inicial > 0) {
					used += hdd.getDiskReadBytes() + hdd.getDiskWriteBytes();
				} else {
					avgTotal += (hdd.getDiskReadBytes() + hdd
							.getDiskWriteBytes());
				}
			} catch (SigarException se) {
			}

		}
		if (inicial == 0)
			inicial = avgTotal;
		else {
			long aux = (long) used;
			used = (float) ((used - avgTotal) / 1024 / 1024);
			avgTotal = aux;
		}
		graf.add(used);
		temps.add(segon);
		segon = (Second) segon.next();
		if (used > maxTotal)
			maxTotal = used;
		if (used < minTotal)
			minTotal = used;
		++comptador;
	}
}
