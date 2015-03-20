package domini;

import java.util.ArrayList;

import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalisisHDD.
 */
public class AnalisisHDD {

	/** The avg total. */
	private long avgTotal;

	/** The contador. */
	private long contador;

	/** The graf. */
	private ArrayList<Float> graf;

	/** The hdd sigar. */
	private Sigar hddSigar;

	/** The inicial. */
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
	 * Instantiates a new analisis hdd.
	 */
	public AnalisisHDD() {
		this.avgTotal = 0;
		this.graf = new ArrayList<Float>();
		this.maxTotal = 0;
		this.minTotal = Long.MAX_VALUE;
		this.hddSigar = new Sigar();
		this.contador = 0;
		this.temps = new ArrayList<Second>();
		this.segon = new Second();
		this.inicial = 0;
	}

	/**
	 * Gets the avg total.
	 *
	 * @return the avg total
	 */
	public long getAvgTotal() {
		return avgTotal;
	}

	/**
	 * Gets the contador.
	 *
	 * @return the contador
	 */
	public long getContador() {
		return contador;
	}

	/**
	 * Gets the graf.
	 *
	 * @return the graf
	 */
	public ArrayList<Float> getGraf() {
		return graf;
	}

	/**
	 * Gets the inicial.
	 *
	 * @return the inicial
	 */
	public float getInicial() {
		return inicial;
	}

	/**
	 * Gets the max total.
	 *
	 * @return the max total
	 */
	public float getMaxTotal() {
		return maxTotal;
	}

	/**
	 * Gets the min total.
	 *
	 * @return the min total
	 */
	public float getMinTotal() {
		return minTotal;
	}

	/**
	 * Gets the ram sigar.
	 *
	 * @return the ram sigar
	 */
	public Sigar getRamSigar() {
		return hddSigar;
	}

	/**
	 * Gets the segon.
	 *
	 * @return the segon
	 */
	public Second getSegon() {
		return segon;
	}

	/**
	 * Gets the temps.
	 *
	 * @return the temps
	 */
	public ArrayList<Second> getTemps() {
		return temps;
	}

	/**
	 * Sets the avg total.
	 *
	 * @param avgTotal the new avg total
	 */
	public void setAvgTotal(long avgTotal) {
		this.avgTotal = avgTotal;
	}

	/**
	 * Sets the contador.
	 *
	 * @param contador the new contador
	 */
	public void setContador(long contador) {
		this.contador = contador;
	}

	/**
	 * Sets the graf.
	 *
	 * @param graf the new graf
	 */
	public void setGraf(ArrayList<Float> graf) {
		this.graf = graf;
	}

	/**
	 * Sets the inicial.
	 *
	 * @param inicial the new inicial
	 */
	public void setInicial(float inicial) {
		this.inicial = inicial;
	}

	/**
	 * Sets the max total.
	 *
	 * @param maxTotal the new max total
	 */
	public void setMaxTotal(float maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * Sets the max total.
	 *
	 * @param maxTotal the new max total
	 */
	public void setMaxTotal(long maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * Sets the min total.
	 *
	 * @param minTotal the new min total
	 */
	public void setMinTotal(float minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * Sets the min total.
	 *
	 * @param minTotal the new min total
	 */
	public void setMinTotal(long minTotal) {
		this.minTotal = minTotal;
	}

	/**
	 * Sets the ram sigar.
	 *
	 * @param hddSigar the new ram sigar
	 */
	public void setRamSigar(Sigar hddSigar) {
		this.hddSigar = hddSigar;
	}

	/**
	 * Sets the segon.
	 *
	 * @param segon the new segon
	 */
	public void setSegon(Second segon) {
		this.segon = segon;
	}

	/**
	 * Sets the temps.
	 *
	 * @param temps the new temps
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
		++contador;
	}
}
