package domini;

import java.util.ArrayList;

import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

public class AnalisisHDD {

	private long avgTotal;

	private long contador;

	private ArrayList<Float> graf;

	private Sigar hddSigar;

	private float inicial;

	private float maxTotal;

	private float minTotal;

	private Second segon;

	private ArrayList<Second> temps;

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

	public long getAvgTotal() {
		return avgTotal;
	}

	public long getContador() {
		return contador;
	}

	public ArrayList<Float> getGraf() {
		return graf;
	}

	public float getInicial() {
		return inicial;
	}

	public float getMaxTotal() {
		return maxTotal;
	}

	public float getMinTotal() {
		return minTotal;
	}

	public Sigar getRamSigar() {
		return hddSigar;
	}

	public Second getSegon() {
		return segon;
	}

	public ArrayList<Second> getTemps() {
		return temps;
	}

	public void setAvgTotal(long avgTotal) {
		this.avgTotal = avgTotal;
	}

	public void setContador(long contador) {
		this.contador = contador;
	}

	public void setGraf(ArrayList<Float> graf) {
		this.graf = graf;
	}

	public void setInicial(float inicial) {
		this.inicial = inicial;
	}

	public void setMaxTotal(float maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMaxTotal(long maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMinTotal(float minTotal) {
		this.minTotal = minTotal;
	}

	public void setMinTotal(long minTotal) {
		this.minTotal = minTotal;
	}

	public void setRamSigar(Sigar hddSigar) {
		this.hddSigar = hddSigar;
	}

	public void setSegon(Second segon) {
		this.segon = segon;
	}

	public void setTemps(ArrayList<Second> temps) {
		this.temps = temps;
	}

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
