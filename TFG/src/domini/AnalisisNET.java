package domini;

import java.util.ArrayList;

import org.hyperic.sigar.Sigar;

public class AnalisisNET {

	private float avgPercentatge;

	private long avgTotal;

	private ArrayList<Float> graf;

	private float maxPercentatge;

	private long maxTotal;

	private float minPercentatge;

	private long minTotal;

	private Sigar netSigar;

	private long contador;
	
	private ArrayList<Float> temps;

	public AnalisisNET() {
		this.avgPercentatge = 0;
		this.avgTotal = 0;
		this.graf = new ArrayList<Float>();
		this.maxPercentatge = 0;
		this.maxTotal = 0;
		this.minPercentatge = 100;
		this.minTotal = Long.MAX_VALUE;
		this.netSigar = new Sigar();
		this.contador = 0;
		this.temps = new ArrayList<Float>();
	}

	public float getAvgPercentatge() {
		return avgPercentatge;
	}

	public long getAvgTotal() {
		return avgTotal;
	}

	public ArrayList<Float> getGraf() {
		return graf;
	}

	public float getMaxPercentatge() {
		return maxPercentatge;
	}

	public long getMaxTotal() {
		return maxTotal;
	}

	public float getMinPercentatge() {
		return minPercentatge;
	}

	public long getMinTotal() {
		return minTotal;
	}

	public Sigar getRamSigar() {
		return netSigar;
	}

	public void setAvgPercentatge(float avgPercentatge) {
		this.avgPercentatge = avgPercentatge;
	}

	public void setAvgTotal(long avgTotal) {
		this.avgTotal = avgTotal;
	}

	public void setGraf(ArrayList<Float> graf) {
		this.graf = graf;
	}

	public void setMaxPercentatge(float maxPercentatge) {
		this.maxPercentatge = maxPercentatge;
	}

	public void setMaxTotal(long maxTotal) {
		this.maxTotal = maxTotal;
	}

	public void setMinPercentatge(float minPercentatge) {
		this.minPercentatge = minPercentatge;
	}

	public void setMinTotal(long minTotal) {
		this.minTotal = minTotal;
	}

	public void setRamSigar(Sigar netSigar) {
		this.netSigar = netSigar;
	}

	public void updateNET() {

	}

	public long getContador() {
		return contador;
	}

	public ArrayList<Float> getTemps() {
		// TODO Auto-generated method stub
		return null;
	}
}
